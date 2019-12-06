package com.application.kreditimpian.FragKategoriFashion;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.application.kreditimpian.Adapter.AdapterAllProduct;
import com.application.kreditimpian.Api.api_v2.BaseApiService;
import com.application.kreditimpian.Api.api_v2.UtilsApi;
import com.application.kreditimpian.Model.ModelAllProduct.AllProductResponse;
import com.application.kreditimpian.Model.ModelAllProduct.ResultItem;
import com.application.kreditimpian.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class KategoriFashion extends Fragment {
    @BindView(R.id.listProductFashion)
    RecyclerView listProductFashion;
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
        View rootView = inflater.inflate(R.layout.fragment_kategori_fashion, container, false);


//        swipeRefresh = rootView.findViewById(R.id.swipeRefresh);
//        swipeRefresh.setColorScheme(android.R.color.holo_blue_bright,
//                android.R.color.holo_green_light,
//                android.R.color.holo_orange_light,
//                android.R.color.holo_red_light);
//
//        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                getResultList();
//                swipeRefresh.setRefreshing(false);
//            }
//        });
//
//
//        ButterKnife.bind(this, rootView);
//        mContext = getActivity();
//        mApiService = UtilsApi.getAPIService();
//
//        adapterAllProduct = new AdapterAllProduct(getActivity(), resultItemList);
//        //RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
//        GridLayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 2, GridLayoutManager.VERTICAL, false);
//        listProductFashion.setLayoutManager(mLayoutManager);
//        listProductFashion.setItemAnimator(new DefaultItemAnimator());
//
//
//        getResultList();
        return rootView;

    }

//    private void getResultList(){
//        progressBar = ProgressDialog.show(getActivity(), null, "Harap Tunggu...", true, false);
//
//        mApiService.getResultFashion().enqueue(new Callback<AllProductResponse>() {
//            @Override
//            public void onResponse(Call<AllProductResponse> call, Response<AllProductResponse> response) {
//                if (response.isSuccessful()){
//                    swipeRefresh.setRefreshing(false);
//                    progressBar.dismiss();
//                    final List<ResultItem> Allproduct = response.body().getResult();
//
//                    listProductFashion.setAdapter(new AdapterAllProduct(mContext, Allproduct));
//                    adapterAllProduct.notifyDataSetChanged();
//                } else {
//                    if(response.body().getResult() != null)
//                    progressBar.dismiss();
//                    Toast.makeText(mContext, "Gagal Refresh", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<AllProductResponse> call, Throwable t) {
//                progressBar.dismiss();
//                Toast.makeText(mContext, "Koneksi Internet Bermasalah", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
}
