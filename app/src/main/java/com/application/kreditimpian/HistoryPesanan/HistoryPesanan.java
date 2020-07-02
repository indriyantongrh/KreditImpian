package com.application.kreditimpian.HistoryPesanan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.os.Bundle;

import com.application.kreditimpian.FormPengajuan.UpgradeImpian.ViewPagerAdapter;
import com.application.kreditimpian.HistoryPesanan.TabMultiguna.FragHistoryMultiguna;
import com.application.kreditimpian.HistoryPesanan.TabProductRequest.FragPermintaanBarang;
import com.application.kreditimpian.HistoryPesanan.TabRiwayatPesananan.FragRiwayatPesanan;
import com.application.kreditimpian.R;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;

public class HistoryPesanan extends AppCompatActivity {
    ConnectivityManager conMgr;
    private TabLayout tabLayout;
    private AppBarLayout appBarLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_pesanan);
        CheckCOnection();
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewpager_tablayout);

        setActionBarTitle("History Pesanan");
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);// set drawable icon
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.AddFragment( new FragRiwayatPesanan(), "Katalog");
        adapter.AddFragment(new FragPermintaanBarang(), "Permintaan Produk");
        adapter.AddFragment(new FragHistoryMultiguna(), "Multiguna");

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

    private void CheckCOnection(){

        conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        {
            if (conMgr.getActiveNetworkInfo() != null
                    && conMgr.getActiveNetworkInfo().isAvailable()
                    && conMgr.getActiveNetworkInfo().isConnected()) {
            } else {
                ///Toast.makeText(getApplicationContext(), "Tidak ada akses Internet",Toast.LENGTH_LONG).show();
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
    }
}
