package com.application.kreditimpian.MenuUtama;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.application.kreditimpian.Akun.FragmentAkun;
import com.application.kreditimpian.Api.SessionManager;
import com.application.kreditimpian.Api.SharedPrefManager;
import com.application.kreditimpian.Beranda.FragmentBeranda;
import com.application.kreditimpian.LoginRegister.LoginUser;
import com.application.kreditimpian.Marketplace.FragmentMarketplace;
import com.application.kreditimpian.R;
import com.application.kreditimpian.SimulasiKredit.FragmentSimulasiKredit;
import com.application.kreditimpian.TransactionProcess.Cart;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MenuUtama extends AppCompatActivity {

    private TextView mTextMessage;
    ActionBar toolbar;
    SharedPrefManager sharedPrefManager;
    SharedPreferences sharedpreferences;
    String id,email,username;
    SessionManager sessionManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_utama);
//        sharedpreferences = getApplication().getSharedPreferences(LoginUser.my_shared_preferences, Context.MODE_PRIVATE);
//        id = sharedpreferences.getString("id", "0");
//        Toast.makeText(getApplication(), "ini id ke-"+ id, Toast.LENGTH_SHORT).show();


        sharedPrefManager = new SharedPrefManager(this);
        String username = sharedPrefManager.getSpUsername();

        toolbar = getSupportActionBar();

        Fragment fragment;
        toolbar.setIcon(R.drawable.logoputih);
        toolbar.setTitle("Kategori Impianmu");
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
