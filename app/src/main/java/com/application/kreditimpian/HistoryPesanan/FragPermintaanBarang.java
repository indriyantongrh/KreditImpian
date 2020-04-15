package com.application.kreditimpian.HistoryPesanan;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.application.kreditimpian.Adapter.AdapterRequetsProduct;
import com.application.kreditimpian.Api.SharedPrefManager;
import com.application.kreditimpian.Api.api_v2.BaseApiService;
import com.application.kreditimpian.Api.api_v2.UtilsApi;
import com.application.kreditimpian.Model.ModelRequestProduct.DataItem;
import com.application.kreditimpian.Model.ModelRequestProduct.ResponseRequestProduct;
import com.application.kreditimpian.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FragPermintaanBarang extends Fragment {

    SharedPrefManager sharedPrefManager;

    @BindView(R.id.listRequestProduct)
    RecyclerView listRequestProduct;
    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefresh;
    ProgressDialog progressBar;
    @BindView(R.id.empty)
    ImageView empty;

    BaseApiService mApiService;
    Context mContext;

    List<DataItem> resultItemList = new ArrayList<>();
    AdapterRequetsProduct adapterRequetsProduct;


    public FragPermintaanBarang() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_frag_permintaan_barang, container, false);

        sharedPrefManager = new SharedPrefManager(getActivity());
        swipeRefresh = view.findViewById(R.id.swipeRefresh);
        swipeRefresh.setColorScheme(android.R.color.holo_orange_dark,
                android.R.color.holo_orange_light,
                android.R.color.holo_orange_dark,
                android.R.color.holo_orange_light);

        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getRequestProduct();
                swipeRefresh.setRefreshing(false);
            }
        });


        ButterKnife.bind(this, view);
        mContext = getActivity();
        mApiService = UtilsApi.getAPIService();
        adapterRequetsProduct = new AdapterRequetsProduct(getActivity(), resultItemList);
        //RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        GridLayoutManager mLayoutManager = new GridLayoutManager(getContext(), 1, GridLayoutManager.VERTICAL, false);
        listRequestProduct.setLayoutManager(mLayoutManager);
        listRequestProduct.setItemAnimator(new DefaultItemAnimator());

        getRequestProduct();

        return view;
    }

    private void getRequestProduct(){

        swipeRefresh.setRefreshing(true);

        HashMap<String, String> params = new HashMap<>();
        params.put("id_member", sharedPrefManager.getSpIdMember());

        mApiService.getProductRequest(params).enqueue(new Callback<ResponseRequestProduct>() {
            @Override
            public void onResponse(Call<ResponseRequestProduct> call, Response<ResponseRequestProduct> response) {
                if (response.isSuccessful()){
                    ///progressBar.dismiss();
                    if (response.body().getResponseCode()==200) {
                        swipeRefresh.setRefreshing(false);

                        final List<DataItem> HistoryTransaction = response.body().getData();

                        listRequestProduct.setAdapter(new AdapterRequetsProduct(mContext, HistoryTransaction));
                        adapterRequetsProduct.notifyDataSetChanged();
                        empty.setVisibility(View.GONE);
                        ///initDataIntent(HistoryTransaction);
                    }else {
                        swipeRefresh.setRefreshing(false);

                        empty.setVisibility(View.VISIBLE);
                    }

                } else {
                    swipeRefresh.setRefreshing(false);

                    Toast.makeText(mContext, "Gagal Refresh", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseRequestProduct> call, Throwable t) {
               /// progressBar.dismiss();
                swipeRefresh.setRefreshing(false);
                Toast.makeText(mContext,"Koneksi anda bermasalah", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
