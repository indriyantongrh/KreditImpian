package com.application.kreditimpian.FragSemuaKategori;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.application.kreditimpian.Adapter.AdapterAllProduct;

import com.application.kreditimpian.Api.JSONResponse;
import com.application.kreditimpian.Api.RequestInterface;
import com.application.kreditimpian.Api.api_v2.BaseApiService;
import com.application.kreditimpian.Api.api_v2.UtilsApi;
import com.application.kreditimpian.Model.ModelAllProduct.AllProductResponse;
import com.application.kreditimpian.Model.ModelAllProduct.ResultItem;
import com.application.kreditimpian.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.application.kreditimpian.BuildConfig.BASE_URL;


public class FragSemuaKategori extends Fragment {

    @BindView(R.id.listAllProduct)
    RecyclerView listAllProduct;
    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefresh;
    ProgressDialog progressBar;

    Context mContext;
    List<ResultItem> resultItemList = new ArrayList<>();
    AdapterAllProduct adapterAllProduct;
    BaseApiService mApiService;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_frag_semua_kategori, container, false);

        swipeRefresh = rootView.findViewById(R.id.swipeRefresh);
        swipeRefresh.setColorScheme(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getResultList();
                swipeRefresh.setRefreshing(false);
            }
        });


        ButterKnife.bind(this, rootView);
        mContext = getActivity();
        mApiService = UtilsApi.getAPIService();

        adapterAllProduct = new AdapterAllProduct(getActivity(), resultItemList);
        //RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        GridLayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 2, GridLayoutManager.VERTICAL, false);
        listAllProduct.setLayoutManager(mLayoutManager);
        listAllProduct.setItemAnimator(new DefaultItemAnimator());


        getResultList();
        return rootView;


    }


    private void getResultList(){
        progressBar = ProgressDialog.show(getActivity(), null, "Harap Tunggu...", true, false);

        mApiService.getResult().enqueue(new Callback<AllProductResponse>() {
            @Override
            public void onResponse(Call<AllProductResponse> call, Response<AllProductResponse> response) {
                if (response.isSuccessful()){
                    swipeRefresh.setRefreshing(false);
                    progressBar.dismiss();
                    final List<ResultItem> Allproduct = response.body().getResult();

                    listAllProduct.setAdapter(new AdapterAllProduct(mContext, Allproduct));
                    adapterAllProduct.notifyDataSetChanged();
                } else {
                    progressBar.dismiss();
                    Toast.makeText(mContext, "Gagal Refresh", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AllProductResponse> call, Throwable t) {
                progressBar.dismiss();
                Toast.makeText(mContext, "Koneksi Internet Bermasalah", Toast.LENGTH_SHORT).show();
            }
        });
    }

//    private void initViews(){
//
//        ///list_lowonganpelamar.setAdapter(adapterRecyclerViewLowonganPelamar);
//        listAllProduct.setHasFixedSize(true);
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
//        listAllProduct.setLayoutManager(layoutManager);
//
//
//    }
//    private void loadJSON(){
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .client(new OkHttpClient().newBuilder()
//                        .connectTimeout(30, TimeUnit.SECONDS)
//                        .readTimeout(30, TimeUnit.SECONDS)
//                        .writeTimeout(30, TimeUnit.SECONDS)
//                        .build())
//                .build();
//        progressBar.setVisibility(android.view.View.VISIBLE);
//        RequestInterface request = retrofit.create(RequestInterface.class);
//        Call<JSONResponse> call = request.getResult();
//        call.enqueue(new Callback<JSONResponse>() {
//            @Override
//            public void onResponse(Call<JSONResponse> call, Response<JSONResponse> response) {
//                JSONResponse jsonResponse = response.body();
//                progressBar.setVisibility(android.view.View.INVISIBLE);
//                swipeRefresh.setRefreshing(false);
//                mArrayList = new ArrayList<>(Arrays.asList(jsonResponse.getAllProductAdapter()));
//                //mAdapter = new AdapterPencarianMenu(mArrayList);
//
//                allProductAdapter = new AllProductAdapter(getContext(),mArrayList);
//                listAllProduct.setAdapter(allProductAdapter);
//
//            }
//
//            @Override
//            public void onFailure(Call<JSONResponse> call, Throwable t) {
//                Log.d("Error",t.getMessage());
//            }
//        });
//    }




}
