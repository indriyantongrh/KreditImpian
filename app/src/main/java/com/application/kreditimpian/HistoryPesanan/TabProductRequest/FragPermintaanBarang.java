package com.application.kreditimpian.HistoryPesanan.TabProductRequest;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
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
import com.application.kreditimpian.Constan.ConstanHistoryPesanan;
import com.application.kreditimpian.Marketplace.FragSemuaKategori.RecyclerItemClickListener;
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
                    if (response.body().getResponseCode()==200 || response.body().getData()==null){
                        swipeRefresh.setRefreshing(false);
                        ///progressBar.dismiss();
                        empty.setVisibility(View.VISIBLE);
                    }
                    else if (response.body().getResponseCode()==200) {
                        swipeRefresh.setRefreshing(false);

                        final List<DataItem> HistoryTransaction = response.body().getData();

                        listRequestProduct.setAdapter(new AdapterRequetsProduct(mContext, HistoryTransaction));
                        adapterRequetsProduct.notifyDataSetChanged();
                        empty.setVisibility(View.GONE);
                        initDataIntent(HistoryTransaction);
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

    private void initDataIntent(final List<DataItem> detaiList){
        listRequestProduct.addOnItemTouchListener(
                new RecyclerItemClickListener(mContext, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {


                        String id_transactions = detaiList.get(position).getIdTransaction();
                        String status = detaiList.get(position).getStatus();
                        String timestamp = detaiList.get(position).getTimestamp();
                        String number = detaiList.get(position).getNumber();
                        String id_product = detaiList.get(position).getReferenceId();
                        String id_product_category = detaiList.get(position).getIdProductCategory();
                        String name = detaiList.get(position).getName();
                        String description = detaiList.get(position).getDescription();
                        String price_sale = detaiList.get(position).getPriceSale();
                        String filename = detaiList.get(position).getContent();
                        String berat = detaiList.get(position).getWeightValue();
                        String id_city = detaiList.get(position).getCompanyCity().getIdGeodirectory();
                        String id_destination = detaiList.get(position).getMemberCity();



                        Intent detailProductRequest = new Intent(mContext, DetailHistoryRequestProduct.class);
                        detailProductRequest.putExtra(ConstanHistoryPesanan.KEY_ID_TRANSACTION, id_transactions);
                        detailProductRequest.putExtra(ConstanHistoryPesanan.KEY_STATUS, status);
                        detailProductRequest.putExtra(ConstanHistoryPesanan.KEY_TIMESTAMP, timestamp);
                        detailProductRequest.putExtra(ConstanHistoryPesanan.KEY_NUMBER, number);
                        detailProductRequest.putExtra(ConstanHistoryPesanan.KEY_ID_PRODUCT, id_product);
                        detailProductRequest.putExtra(ConstanHistoryPesanan.KEY_ID_PRODUCT_CATEGORY, id_product_category);
                        detailProductRequest.putExtra(ConstanHistoryPesanan.KEY_NAME_PRODUCT, name);
                        detailProductRequest.putExtra(ConstanHistoryPesanan.KEY_DESCRIPTION, description);
                        detailProductRequest.putExtra(ConstanHistoryPesanan.KEY_PRICE_SALE, price_sale);
                        detailProductRequest.putExtra("content", filename);
                        detailProductRequest.putExtra("weight_value", berat);
                        detailProductRequest.putExtra("id_geodirectory", id_city);
                        detailProductRequest.putExtra("member_city", id_destination);


                        startActivity(detailProductRequest);
                    }
                }));
    }
}
