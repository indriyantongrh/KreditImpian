package com.application.kreditimpian.Notifikasi.NotifikasiV2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.application.kreditimpian.Api.SharedPrefManager;
import com.application.kreditimpian.Api.api_v2.BaseApiService;
import com.application.kreditimpian.MenuUtama.MenuUtama;
import com.application.kreditimpian.Notifikasi.Notifikasi;
import com.application.kreditimpian.R;

public class NotifikasiV2 extends AppCompatActivity {
    SharedPrefManager sharedPrefManager;
    private RecyclerView rvNotifikasi;
    ImageView empty;
    Context mContext;
    private SwipeRefreshLayout swipeRefresh;
    BaseApiService mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifikasi_v2);

        setActionBarTitle("Notifikasi");
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);// set drawable icon
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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
        startActivity(new Intent(NotifikasiV2.this, MenuUtama.class));
        finish();
        super.onBackPressed();
    }
}
