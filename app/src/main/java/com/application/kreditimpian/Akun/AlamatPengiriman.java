package com.application.kreditimpian.Akun;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.application.kreditimpian.Adapter.AdapterAddresses;
import com.application.kreditimpian.Api.SharedPrefManager;
import com.application.kreditimpian.Api.api_v2.BaseApiService;
import com.application.kreditimpian.Api.api_v2.UtilsApi;
import com.application.kreditimpian.Constan.ConstansAddress;
import com.application.kreditimpian.Marketplace.FragSemuaKategori.RecyclerItemClickListener;
import com.application.kreditimpian.Model.ModelListAlamat.DataItem;
import com.application.kreditimpian.Model.ModelListAlamat.ResponseListAlamat;
import com.application.kreditimpian.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlamatPengiriman extends AppCompatActivity {
    SwipeRefreshLayout swipeRefresh;
    ProgressDialog progressBar;
    Button btnTambahAlamat;
    RecyclerView ListAlamat;
    BaseApiService mApiService;
    Context mContext;
    ImageView empty;
    SharedPrefManager sharedPrefManager;
    AdapterAddresses adapterAddresses;
    List<DataItem> dataItemList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alamat_pengiriman);
        empty = findViewById(R.id.empty);

        sharedPrefManager = new SharedPrefManager(this);
        swipeRefresh = findViewById(R.id.swipeRefresh);
        swipeRefresh.setColorScheme(android.R.color.holo_orange_dark,
                android.R.color.holo_orange_light,
                android.R.color.holo_orange_dark,
                android.R.color.holo_orange_light);

        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                LoadAdresess();
                swipeRefresh.setRefreshing(false);
            }
        });

        setActionBarTitle("Alamat Pengiriman");
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);// set drawable icon
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mApiService = UtilsApi.getAPIService();
        ListAlamat = findViewById(R.id.ListAlamat);
        adapterAddresses = new AdapterAddresses(this, dataItemList);
        GridLayoutManager mLayoutManager = new GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false);
        ListAlamat.setLayoutManager(mLayoutManager);
        ListAlamat.setItemAnimator(new DefaultItemAnimator());


        LoadAdresess();
        btnTambahAlamat = findViewById(R.id.btnTambahAlamat);
        btnTambahAlamat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TambahAlamatPengiriman.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private void LoadAdresess(){
        progressBar = ProgressDialog.show(AlamatPengiriman.this, null, "Harap Tunggu...", true, false);
        mApiService.getAllAddesses(sharedPrefManager.getSpIdMember()).enqueue(new Callback<ResponseListAlamat>() {
            @Override
            public void onResponse(Call<ResponseListAlamat> call, Response<ResponseListAlamat> response) {
                if(response.body().getResponseCode()==200){
                    swipeRefresh.setRefreshing(false);
                    progressBar.dismiss();
                    final List<DataItem> Addresses = response.body().getData();

                    ListAlamat.setAdapter(new AdapterAddresses(mContext, Addresses));
                    adapterAddresses.notifyDataSetChanged();
                    ///empty.setVisibility(View.GONE);
                   initDataIntent(Addresses);
                }else {
                    progressBar.dismiss();
                    empty.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onFailure(Call<ResponseListAlamat> call, Throwable t) {
                progressBar.dismiss();
                Toast.makeText(mContext, "Gagal Refresh", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void initDataIntent(final List<DataItem> detaiList){
        ListAlamat.addOnItemTouchListener(
                new RecyclerItemClickListener(mContext, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {

                        String id = detaiList.get(position).getId();
                        String id_member = detaiList.get(position).getIdMember();
                        String id_geodirectory = detaiList.get(position).getIdGeodirectory();
                        String receiver = detaiList.get(position).getReceiver();
                        String postal_code = detaiList.get(position).getPostalCode();
                        String district = detaiList.get(position).getDistrict();
                        String address_name = detaiList.get(position).getAddressName();
                        String phone = detaiList.get(position).getPhone();
                        String address = detaiList.get(position).getAddress();
                        String main_address = detaiList.get(position).getMainAddress();
//                        String main_addresses;
//                        if( main_address.equals("NEW")){
//                            main_addresses = main_address.equals()
//                        }


                        Intent detailaddress = new Intent(AlamatPengiriman.this, DetailAlamat.class);
                        detailaddress.putExtra(ConstansAddress.KEY_ID, id);
                        detailaddress.putExtra(ConstansAddress.KEY_ID_MEMBER, id_member);
                        detailaddress.putExtra(ConstansAddress.KEY_ADDRESS_NAME, address_name);
                        detailaddress.putExtra(ConstansAddress.KEY_RECEIVER, receiver);
                        detailaddress.putExtra(ConstansAddress.KEY_PHONE, phone);
                        detailaddress.putExtra(ConstansAddress.KEY_ADDRESS, address);
                        detailaddress.putExtra(ConstansAddress.KEY_POSTAL_CODE, postal_code);
                        detailaddress.putExtra(ConstansAddress.KEY_ID_GEODIRECTORY, id_geodirectory);
                        detailaddress.putExtra(ConstansAddress.KEY_DISTRICT, district);
                        detailaddress.putExtra(ConstansAddress.KEY_MAIN_ADDRESS, main_address);

                        startActivity(detailaddress);
                    }
                }));
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
        ///finish();
        super.onBackPressed();
        finish();

    }
}
