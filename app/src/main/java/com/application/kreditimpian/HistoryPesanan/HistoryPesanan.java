package com.application.kreditimpian.HistoryPesanan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.application.kreditimpian.Adapter.AdapterHistoryTransaction;
import com.application.kreditimpian.Akun.DataDiri;
import com.application.kreditimpian.Api.SharedPrefManager;
import com.application.kreditimpian.Api.api_v2.BaseApiService;
import com.application.kreditimpian.Api.api_v2.UtilsApi;
import com.application.kreditimpian.Model.ModelHistoryTransaction.DataItem;
import com.application.kreditimpian.Model.ModelHistoryTransaction.ResponseHistoryTransaction;
import com.application.kreditimpian.R;
import com.application.kreditimpian.TransactionProcess.Cart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoryPesanan extends AppCompatActivity {

    SharedPrefManager sharedPrefManager;

    @BindView(R.id.listHistoryPesanan)
    RecyclerView listHistoryPesanan;
    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefresh;
    ProgressDialog progressBar;
    @BindView(R.id.empty)
    ImageView empty;

    BaseApiService mApiService;
    Context mContext;

    List<DataItem> resultItemList = new ArrayList<>();
    AdapterHistoryTransaction adapterHistoryTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_pesanan);
        setActionBarTitle("History Pesanan");
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);// set drawable icon
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        sharedPrefManager = new SharedPrefManager(HistoryPesanan.this);
        swipeRefresh =findViewById(R.id.swipeRefresh);
        swipeRefresh.setColorScheme(android.R.color.holo_orange_dark,
                android.R.color.holo_orange_light,
                android.R.color.holo_orange_dark,
                android.R.color.holo_orange_light);

        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getHistoryTransaction();
                swipeRefresh.setRefreshing(false);
            }
        });


        ButterKnife.bind(this);
        mContext = HistoryPesanan.this;
        mApiService = UtilsApi.getAPIService();
        adapterHistoryTransaction = new AdapterHistoryTransaction(HistoryPesanan.this, resultItemList);
        //RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        GridLayoutManager mLayoutManager = new GridLayoutManager(HistoryPesanan.this, 1, GridLayoutManager.VERTICAL, false);
        listHistoryPesanan.setLayoutManager(mLayoutManager);
        listHistoryPesanan.setItemAnimator(new DefaultItemAnimator());

        getHistoryTransaction();
    }

    private void getHistoryTransaction(){

        progressBar = ProgressDialog.show(HistoryPesanan.this, null, "Harap Tunggu...", true, false);

        HashMap<String, String> params = new HashMap<>();
        params.put("id_member", sharedPrefManager.getSpIdMember());

        mApiService.getHistoryTransaction(params).enqueue(new Callback<ResponseHistoryTransaction>() {
            @Override
            public void onResponse(Call<ResponseHistoryTransaction> call, Response<ResponseHistoryTransaction> response) {
                if (response.isSuccessful()){
                    ///progressBar.dismiss();
                    if (response.body().getResponseCode()==200) {
                        swipeRefresh.setRefreshing(false);
                        progressBar.dismiss();
                        final List<DataItem> HistoryTransaction = response.body().getData();

                        listHistoryPesanan.setAdapter(new AdapterHistoryTransaction(mContext, HistoryTransaction));
                        adapterHistoryTransaction.notifyDataSetChanged();
                        empty.setVisibility(View.GONE);
                       //// initDataIntent(Allproduct);
                    }else {
                        progressBar.dismiss();
                        empty.setVisibility(View.VISIBLE);
                    }

                } else {
                    progressBar.dismiss();
                    Toast.makeText(mContext,    "Gagal Refresh", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseHistoryTransaction> call, Throwable t) {

            }
        });

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
        finish();
    }
}
