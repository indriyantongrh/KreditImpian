package com.application.kreditimpian.Notifikasi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;

import com.application.kreditimpian.FormPengajuan.UpgradeImpian.ViewPagerAdapter;
import com.application.kreditimpian.HistoryPesanan.TabProductRequest.FragPermintaanBarang;
import com.application.kreditimpian.HistoryPesanan.TabRiwayatPesananan.FragRiwayatPesanan;
import com.application.kreditimpian.MenuUtama.MenuUtama;
import com.application.kreditimpian.Notifikasi.NotifikasiFitur.FragNotifikasiFitur;
import com.application.kreditimpian.Notifikasi.NotifikasiKatalog.FragNotifikasiKatalog;
import com.application.kreditimpian.Notifikasi.NotifikasiProduct.FragNotifikasiProduct;
import com.application.kreditimpian.R;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;

public class Notifikasi extends AppCompatActivity {

    private TabLayout tabLayout;
    private AppBarLayout appBarLayout;
    private ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifikasi2);

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewpager_tablayout);

        setActionBarTitle("Notifikasi");
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);// set drawable icon
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.AddFragment( new FragNotifikasiProduct(), "Katalog");
        ///adapter.AddFragment( new FragNotifikasiKatalog(), "Katalog");
        adapter.AddFragment(new FragNotifikasiFitur(), "Fitur Kredit Impian");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setActionBarTitle(String title) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(Notifikasi.this, MenuUtama.class));
        finish();
        super.onBackPressed();
    }
}
