package com.application.kreditimpian.DetailProductMitra;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.application.kreditimpian.Adapter.AdapterProductBaru;
import com.application.kreditimpian.Adapter.AdapterProductMitra;
import com.application.kreditimpian.Api.api_v2.BaseApiService;
import com.application.kreditimpian.Api.api_v2.UtilsApi;
import com.application.kreditimpian.Constan.ConstansProductMitra;
import com.application.kreditimpian.Model.ModelProductBaru.ResultItem;
import com.application.kreditimpian.Model.ModelProductMitra.DataItem;
import com.application.kreditimpian.Model.ModelProductMitra.ResponseProductMitra;
import com.application.kreditimpian.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailProductMitra extends AppCompatActivity {

    @BindView(R.id.imageMerchant)
    ImageView imageMerchant;
    @BindView(R.id.tvNameMerchant)
    TextView tvNameMerchant;
    @BindView(R.id.tvCityMerchant)
    TextView tvCityMerchant;
    @BindView(R.id.listProductMitra)
    RecyclerView listProductMitra;
    BaseApiService mApiService;
    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefresh;
    @BindView(R.id.tvIdMerchant)
    TextView tvIdMerchant;
    @BindView(R.id.empty)
    ImageView empty;

    Context mContext;
    List<DataItem> resultItemList = new ArrayList<>();
    AdapterProductMitra adapterProductMitra;
    private GridLayoutManager mLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_product_mitra);

        setActionBarTitle("Detail Merchant");
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);// set drawable icon
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ButterKnife.bind(this);
        mContext = DetailProductMitra.this;
        mApiService = UtilsApi.getAPIService();

        Intent intent = getIntent();
        String idMerchants = intent.getStringExtra(ConstansProductMitra.KEY_ID_MERCHANT);
        String nameMerchants = intent.getStringExtra(ConstansProductMitra.KEY_NAME_MERCHANT);
        String imageMerchants = intent.getStringExtra(ConstansProductMitra.KEY_IMAGE_MERCHANT);
        String cityMerchants = intent.getStringExtra(ConstansProductMitra.KEY_CITYP_MERCHANT);

        tvIdMerchant.setText(idMerchants);
        ///Log.v("jajal data: "+params ,"jajal data");

        tvNameMerchant.setText(nameMerchants);
        tvCityMerchant.setText(cityMerchants);
        Glide.with(DetailProductMitra.this)
                .load(imageMerchants)
                .placeholder(R.drawable.no_image)
                .error(R.drawable.no_image)
                .into(imageMerchant);

        swipeRefresh = findViewById(R.id.swipeRefresh);
        swipeRefresh.setColorScheme(android.R.color.holo_orange_dark,
                android.R.color.holo_orange_light,
                android.R.color.holo_orange_dark,
                android.R.color.holo_orange_light);

        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getProduct();
                /// performPagaination();
                swipeRefresh.setRefreshing(false);
            }
        });


        adapterProductMitra = new AdapterProductMitra(DetailProductMitra.this, resultItemList);
        mLayoutManager = new GridLayoutManager(DetailProductMitra.this, 2, GridLayoutManager.VERTICAL, false);
        listProductMitra.setLayoutManager(mLayoutManager);
       //// invalidateOptionsMenu().setItemAnimator(new DefaultItemAnimator());
        listProductMitra.setItemAnimator(new DefaultItemAnimator());

        getProduct();
    }

    private void getProduct(){
        Intent intent = getIntent();
        String idMerchants = intent.getStringExtra(ConstansProductMitra.KEY_ID_MERCHANT);
        swipeRefresh.setRefreshing(true);
        HashMap<String, String> params = new HashMap<>();
        params.put("id_company",tvIdMerchant.getText().toString() );
       /// Log.v("jajal data: "+params ,"jajal data");
//        Toast.makeText(DetailProductMitra.this, "ini id anda"+params, Toast.LENGTH_LONG).show();


        mApiService.getProductMitra(params).enqueue(new Callback<ResponseProductMitra>() {
            @Override
            public void onResponse(Call<ResponseProductMitra> call, Response<ResponseProductMitra> response) {
                swipeRefresh.setRefreshing(false);
                if(response.body().getResponseCode()==200){
                    final List<DataItem> productMitra = response.body().getData();
                    Toast.makeText(DetailProductMitra.this, "ini data anda"+productMitra, Toast.LENGTH_LONG).show();

                    listProductMitra.setAdapter(new AdapterProductMitra(mContext, productMitra));
                    adapterProductMitra.notifyDataSetChanged();

                }else {
                    swipeRefresh.setRefreshing(false);
                    empty.setVisibility(View.VISIBLE);

                }
            }

            @Override
            public void onFailure(Call<ResponseProductMitra> call, Throwable t) {

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
}