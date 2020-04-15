package com.application.kreditimpian.HistoryPesanan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.application.kreditimpian.FormPengajuan.UpgradeImpian.MultigunaMobil;
import com.application.kreditimpian.FormPengajuan.UpgradeImpian.MultigunaMotor;
import com.application.kreditimpian.FormPengajuan.UpgradeImpian.ViewPagerAdapter;
import com.application.kreditimpian.R;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;

public class HistoryPesanan extends AppCompatActivity {

    private TabLayout tabLayout;
    private AppBarLayout appBarLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_pesanan);

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewpager_tablayout);

        setActionBarTitle("History Pesanan");
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);// set drawable icon
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.AddFragment( new FragRiwayatPesanan(), "Riwayat Pesanan");
        adapter.AddFragment(new FragPermintaanBarang(), "Permintaan Produk");

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
}
