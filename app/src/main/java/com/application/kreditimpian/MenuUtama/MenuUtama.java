package com.application.kreditimpian.MenuUtama;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.application.kreditimpian.Adapter.AdapterCart;
import com.application.kreditimpian.Akun.DataDiri;
import com.application.kreditimpian.Akun.DataDiriPopup;
import com.application.kreditimpian.Akun.FragmentAkun;
import com.application.kreditimpian.Api.SessionManager;
import com.application.kreditimpian.Api.SharedPrefManager;
import com.application.kreditimpian.Api.api_v2.BaseApiService;
import com.application.kreditimpian.Api.api_v2.UtilsApi;
import com.application.kreditimpian.Beranda.FragmentBeranda;
import com.application.kreditimpian.BuildConfig;
import com.application.kreditimpian.LoginRegister.LoginUser;
import com.application.kreditimpian.MainActivity;
import com.application.kreditimpian.Marketplace.FragmentMarketplace;
import com.application.kreditimpian.Model.ModelDetailMember.DataItem;
import com.application.kreditimpian.Model.ModelDetailMember.ResponseDetailMember;
import com.application.kreditimpian.Model.ModelImagePromo.ResponseImagePromo;
import com.application.kreditimpian.Model.ModelOnShoppingCart.ResponseOnShoppingCart;
import com.application.kreditimpian.R;
import com.application.kreditimpian.SimulasiKredit.FragmentSimulasiKredit;
import com.application.kreditimpian.TransactionProcess.Cart;
import com.application.kreditimpian.TransactionProcess.TransactionSelectMitra;
import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.play.core.appupdate.AppUpdateInfo;
import com.google.android.play.core.appupdate.AppUpdateManager;
import com.google.android.play.core.appupdate.AppUpdateManagerFactory;
import com.google.android.play.core.install.InstallState;
import com.google.android.play.core.install.InstallStateUpdatedListener;
import com.google.android.play.core.install.model.AppUpdateType;
import com.google.android.play.core.install.model.InstallStatus;
import com.google.android.play.core.install.model.UpdateAvailability;
import com.google.android.play.core.tasks.Task;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.w3c.dom.Document;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import butterknife.internal.Constants;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.crashlytics.android.Crashlytics.log;
import static com.google.android.play.core.install.model.AppUpdateType.IMMEDIATE;

public class MenuUtama extends AppCompatActivity {
    BaseApiService mApiService;

    private TextView mTextMessage;
    ActionBar toolbar;
    SharedPrefManager sharedPrefManager;
    SharedPreferences sharedpreferences;
    String id, email, username;
    SessionManager sessionManager;
    ConnectivityManager conMgr;
    AlertDialog alertDialog;

    private AppUpdateManager mAppUpdateManager;
    String newVersion;
    String sCurrentVersion, sLatestVersion;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_utama);
        mApiService = UtilsApi.getAPIService();
        sharedPrefManager = new SharedPrefManager(MenuUtama.this);
        LoadDataDiri();
        conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        {
            if (conMgr.getActiveNetworkInfo() != null
                    && conMgr.getActiveNetworkInfo().isAvailable()
                    && conMgr.getActiveNetworkInfo().isConnected()) {
            } else {
                /// Toast.makeText(getApplicationContext(), "Tidak ada akses Internet", Toast.LENGTH_LONG).show();
                try {
                    AlertDialog alertDialog = new AlertDialog.Builder(this).create();

                    alertDialog.setTitle("Info");
                    alertDialog.setMessage("Internet tidak tersedia, Periksa konektivitas internet Anda dan coba lagi");
                    alertDialog.setIcon(R.drawable.no_connection);
                    alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            alertDialog.dismiss();

                        }
                    });

                    alertDialog.show();
                } catch (Exception e) {
                    /// Log.d(Constants. , "Show Dialog: " + e.getMessage());
                }

            }
        }

        ///for Update Google Play store
        new GetLatestVersion().execute();

        //CheckUpdate();
        getPromotion();
        sharedPrefManager = new SharedPrefManager(this);
        String username = sharedPrefManager.getSpUsername();
        toolbar = getSupportActionBar();
        Fragment fragment;
        toolbar.setIcon(R.drawable.logoputih);
        toolbar.setTitle("Kredit Impianmu");
        fragment = new FragmentBeranda();
        loadFragment(fragment);
        //return true;
        Log.d("ID user", sharedPrefManager.getSpIdUser()+" id user");

        //mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);



    }


    /*Untuk Load data diri jika belm lengkap diminta unutk mengisi*/
    public void LoadDataDiri() {



        HashMap<String, String> params = new HashMap<>();
        params.put("id_member", sharedPrefManager.getSpIdMember());

        mApiService.getDetailMember(params).enqueue(new Callback<ResponseDetailMember>() {
            @Override
            public void onResponse(Call<ResponseDetailMember> call, Response<ResponseDetailMember> response) {

                if (response.body().getResponseCode() == 200) {
                    ResponseDetailMember responseDetailMember = response.body();
                    List<DataItem> detail = responseDetailMember.getData();

                    AlertDialog alertDialog = new AlertDialog.Builder(MenuUtama.this).create();
                    alertDialog.setTitle("Info");
                    alertDialog.setMessage("Data diri anda sudah lengkap, silahkan wujudkan impian anda!");
                    alertDialog.setIcon(R.drawable.successfully);
                    alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            alertDialog.dismiss();

                        }
                    });
                    alertDialog.show();


                } else {

                    AlertDialog alertDialog = new AlertDialog.Builder(MenuUtama.this).create();
                    alertDialog.setTitle("Info");
                    alertDialog.setCancelable(false);
                    alertDialog.setMessage("Lengkapi data diri anda sebelum bertransaksi. Dan ayo wujudkan impian anda!");
                    alertDialog.setIcon(R.drawable.alert);
                    alertDialog.setButton("Lengkapi Sekarang", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            //alertDialog.dismiss();
                            Intent OpenDatadiri = new Intent(MenuUtama.this, DataDiriPopup.class);
                            startActivity(OpenDatadiri);
                            finish();
                        }
                    });

                    alertDialog.show();
                    Toast.makeText(MenuUtama.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ResponseDetailMember> call, Throwable t) {
                Toast.makeText(MenuUtama.this, "Keneksi terputus", Toast.LENGTH_LONG);
            }
        });
    }


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;

            switch (item.getItemId()) {
                case R.id.navigation_beranda:
                    //toolbar.setIcon(R.drawable.logoputih);
                    toolbar.setTitle("Kredit Impian");
                    fragment = new FragmentBeranda();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_marketplace:
                    //   toolbar.setLogo(R.drawable.logoputih);
                    toolbar.setTitle("Cari Impian Anda Yuk");
                    fragment = new FragmentMarketplace();
                    loadFragment(fragment);
                    return true;

                case R.id.navigation_simulasikredit:
                    //    toolbar.setLogo(R.drawable.logoputih);
                    toolbar.setTitle("Simulasi Kredit");
                    fragment = new FragmentSimulasiKredit();
                    loadFragment(fragment);
                    return true;

                case R.id.navigation_akun:
                    //    toolbar.setLogo(R.drawable.logoputih);
                    toolbar.setTitle("Akun Saya");
                    fragment = new FragmentAkun();
                    loadFragment(fragment);
                    return true;
            }
            return false;
        }
    };

    private void loadFragment(Fragment fragment) {
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.frame_container, fragment);
        //transaction.addToBackStack(null);
        transaction.commit();


    }

    @Override
    public void onBackPressed() {
        Intent setIntent = new Intent(Intent.ACTION_MAIN);
        setIntent.addCategory(Intent.CATEGORY_HOME);
        setIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(setIntent);
    }

    private void getPromotion() {

        mApiService.getImageSlider().enqueue(new Callback<ResponseImagePromo>() {
            @Override
            public void onResponse(Call<ResponseImagePromo> call, Response<ResponseImagePromo> response) {

            }

            @Override
            public void onFailure(Call<ResponseImagePromo> call, Throwable t) {

            }
        });
    }



    private void CheckUpdate() {


        // Creates instance of the manager.
        AppUpdateManager appUpdateManager = AppUpdateManagerFactory.create(this);

        // Returns an intent object that you use to check for an update.
        Task<AppUpdateInfo> appUpdateInfoTask = appUpdateManager.getAppUpdateInfo();

        // Checks that the platform will allow the specified type of update.
        appUpdateInfoTask.addOnSuccessListener(appUpdateInfo -> {
            if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE
                    // For a flexible update, use AppUpdateType.FLEXIBLE
                    && appUpdateInfo.isUpdateTypeAllowed(IMMEDIATE)) {
                // Request the update.
            }
        });


    }

    private class GetLatestVersion extends AsyncTask<String, Void,String> {
        @Override
        protected String doInBackground(String... strings) {
            try {
                sLatestVersion = Jsoup
                        .connect("https://play.google.com/store/apps/details?id=" +getPackageName())
                        .timeout(3000)
                        .get()
                        .select("div.hAyfc:nth-child(4)>"+
                                "span:nth-child(2) > div:nth-child(1)"+
                                "> span:nth-child(1)")
                        .first()
                        .ownText();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return sLatestVersion;
        }

        @Override
        protected void onPostExecute(String s) {
            ///get Current Version
            sCurrentVersion = BuildConfig.VERSION_NAME;

            if(sLatestVersion != null){
                /// version conver float
                float cVersion = Float.parseFloat(sCurrentVersion);
                float lVersion = Float.parseFloat(sLatestVersion);
                //check condition version greater than curren version
                if(lVersion > cVersion){
                    // Create update
                    updateAlertDIalog();
                }
            }

        }

        private void updateAlertDIalog() {

            AlertDialog.Builder builder = new AlertDialog.Builder(MenuUtama.this);
            builder.setTitle("Update Kredit Impian");
            builder.setCancelable(false);
            builder.setMessage("Update versi terbaru tersedia");
            builder.setPositiveButton("Update Sekarang", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("market://details?id=" +getPackageName())));
                    dialog.dismiss();
                }
            });

            /*builder.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });*/

            builder.show();

        }
    }





 /*   @Override
    protected void onResume() {
        super.onResume();
        AppUpdateManager appUpdateManager = AppUpdateManagerFactory.create(this);
        appUpdateManager
                .getAppUpdateInfo()
                .addOnSuccessListener(
                        appUpdateInfo -> {
                            if (appUpdateInfo.updateAvailability()
                                    == UpdateAvailability.DEVELOPER_TRIGGERED_UPDATE_IN_PROGRESS) {
                                // If an in-app update is already running, resume the update.
                                appUpdateManager.startUpdateFlowForResult(
                                        appUpdateInfo,
                                        IMMEDIATE,
                                        this,
                                            MY_REQUEST_CODE);
                            }
                        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == MY_REQUEST_CODE) {
            if (resultCode != RESULT_OK) {
                log("Update flow failed! Result code: " + resultCode);
                // If the update is cancelled or fails,
                // you can request to start the update again.
            }
        }
    }*/

/*    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MenuUtama.this);

        /// builder.setTitle("Keluar ");
        builder.setMessage("Apakah kamu yakin ingin keluar dari aplikasi ?");

        builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {



                moveTaskToBack(true);
                finish();
                //// new DetailAplikasiSaya.HapusData().execute();
                dialog.dismiss();
            }

        });
        builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                // Do nothing
                dialog.dismiss();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
        //// Toast.makeText(this,"Keluar aplikasi!", Toast.LENGTH_LONG).show();

    }*/

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menutopbar, menu);
//        return true;
//    }
//
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//
//        int id = item.getItemId();
//
//        if (id == R.id.notifikasi){
//            Toast.makeText(this, "ini Notifikasi", Toast.LENGTH_SHORT).show();
//
//        }
//        if (id == R.id.cartshop){
//            gotocartshop();
//        }
//        return super.onOptionsItemSelected(item);
//
//
//
//    }
//
//    private void gotocartshop() {
//        Intent intent_cart = new Intent(this, Cart.class);
//        intent_cart.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        startActivity(intent_cart);
//    }
//


}
