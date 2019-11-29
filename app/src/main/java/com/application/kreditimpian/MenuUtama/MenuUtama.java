package com.application.kreditimpian.MenuUtama;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.application.kreditimpian.Akun.FragmentAkun;
import com.application.kreditimpian.Api.api.SharedPrefManager;
import com.application.kreditimpian.Beranda.FragmentBeranda;
import com.application.kreditimpian.LoginRegister.LoginUser;
import com.application.kreditimpian.Marketplace.FragmentMarketplace;
import com.application.kreditimpian.R;
import com.application.kreditimpian.SimulasiKredit.FragmentSimulasiKredit;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MenuUtama extends AppCompatActivity {

    private TextView mTextMessage;
    ActionBar toolbar;
    SharedPrefManager sharedPrefManager;
    SharedPreferences sharedpreferences;
    String id,email,username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_utama);
        //sharedpreferences = getApplicationContext().getSharedPreferences(LoginUser.my_shared_preferences, Context.MODE_PRIVATE);
        ///Toast.makeText(getApplicationContext(), "ini id ke-"+sharedpreferences +username, Toast.LENGTH_SHORT).show();
        toolbar = getSupportActionBar();


        SharedPreferences preferences = getSharedPreferences("my_shared_preferences", Context.MODE_PRIVATE);
        String username = preferences.getString("username", "Data not Found");
        Toast.makeText(getApplicationContext(), "ini id ke-" +username, Toast.LENGTH_SHORT).show();
/*        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setLogo(R.drawable.logoputih);*/

        Fragment fragment;
       // toolbar.setLogo(R.drawable.logoputih);
        //toolbar.setTitle("@drawable/logoputih");
        fragment = new FragmentBeranda();
        loadFragment(fragment);
        //return true;

        //mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    }


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.navigation_beranda:
                  //  toolbar.setLogo(R.drawable.logoputih);
                    //   toolbar.setTitle("Beranda");
                    fragment = new FragmentBeranda();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_marketplace:
                 //   toolbar.setLogo(R.drawable.logoputih);
                 //   toolbar.setTitle("Marketplace");
                    fragment = new FragmentMarketplace();
                    loadFragment(fragment);
                    return true;

                case R.id.navigation_simulasikredit:
                //    toolbar.setLogo(R.drawable.logoputih);
                 //   toolbar.setTitle("Simulasi Kredit");
                    fragment = new FragmentSimulasiKredit();
                    loadFragment(fragment);
                    return true;

                case R.id.navigation_akun:
                //    toolbar.setLogo(R.drawable.logoputih);
                 //   toolbar.setTitle("Akun");
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

  /*  boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {

        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Tekan tombol kembali lagi untuk keluar", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
*/


    @Override
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

    }

}
