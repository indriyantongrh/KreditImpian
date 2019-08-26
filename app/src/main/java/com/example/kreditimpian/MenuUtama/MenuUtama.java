package com.example.kreditimpian.MenuUtama;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kreditimpian.Akun.FragmentAkun;
import com.example.kreditimpian.Beranda.FragmentBeranda;
import com.example.kreditimpian.Marketplace.FragmentMarketplace;
import com.example.kreditimpian.R;
import com.example.kreditimpian.SimulasiKredit.FragmentSimulasiKredit;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MenuUtama extends AppCompatActivity {

    private TextView mTextMessage;
    ActionBar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_utama);

        toolbar = getSupportActionBar();
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
        // bundle.putString("kode", kode);
        //bundle.putString("key", key);
        fragment.setArguments(bundle);
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.frame_container, fragment);
        //transaction.addToBackStack(null);
        transaction.commit();


    }

    boolean doubleBackToExitPressedOnce = false;

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



}
