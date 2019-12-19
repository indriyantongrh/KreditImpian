package com.application.kreditimpian.Marketplace.FragKategoriOtomotif;

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

import com.application.kreditimpian.Adapter.AdapterAllProduct;
import com.application.kreditimpian.Adapter.AdapterProduct;
import com.application.kreditimpian.Api.api_v2.BaseApiService;
import com.application.kreditimpian.Api.api_v2.UtilsApi;
;
import com.application.kreditimpian.DetailProduct;
import com.application.kreditimpian.Marketplace.FragSemuaKategori.Constans;
import com.application.kreditimpian.Marketplace.FragSemuaKategori.RecyclerItemClickListener;
import com.application.kreditimpian.Model.ModelProduct.ResponseProduct;
import com.application.kreditimpian.Model.ModelProduct.ResultItem;
import com.application.kreditimpian.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class KategoriOtomotif extends Fragment {

    @BindView(R.id.listProductOtomotif)
    RecyclerView listProductOtomotif;
    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefresh;
    ProgressDialog progressBar;
    @BindView(R.id.empty)
    ImageView empty;


    Context mContext;
    List<ResultItem> resultItemList = new ArrayList<>();
    AdapterProduct adapterProduct;
    BaseApiService mApiService;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_kategori_otomotif, container, false);

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

        adapterProduct = new AdapterProduct(getActivity(), resultItemList);
        //RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        GridLayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 2, GridLayoutManager.VERTICAL, false);
        listProductOtomotif.setLayoutManager(mLayoutManager);
        listProductOtomotif.setItemAnimator(new DefaultItemAnimator());


        getResultList();
        return rootView;

    }

    private void getResultList(){
        progressBar = ProgressDialog.show(getActivity(), null, "Harap Tunggu...", true, false);

        mApiService.getResultOtomotif().enqueue(new Callback<ResponseProduct>() {
            @Override
            public void onResponse(Call<ResponseProduct> call, Response<ResponseProduct> response) {
                if (response.isSuccessful()){
                    ///progressBar.dismiss();
                    if (response.body().getStatus()==200) {
                        swipeRefresh.setRefreshing(false);
                        progressBar.dismiss();
                        final List<ResultItem> Allproduct = response.body().getResult();

                        listProductOtomotif.setAdapter(new AdapterProduct(mContext, Allproduct));
                        adapterProduct.notifyDataSetChanged();
                        empty.setVisibility(View.GONE);
                        initDataIntent(Allproduct);
                    }else {
                        progressBar.dismiss();
                        empty.setVisibility(View.VISIBLE);
                    }
                } else {
                    progressBar.dismiss();
                    Toast.makeText(mContext, "Gagal Refresh", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseProduct> call, Throwable t) {
                progressBar.dismiss();
                Toast.makeText(mContext, "Koneksi Internet Bermasalah", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initDataIntent(final List<ResultItem> detaiList){
        listProductOtomotif.addOnItemTouchListener(
                new RecyclerItemClickListener(mContext, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {

                        String id = detaiList.get(position).getId();
                        String id_currency = detaiList.get(position).getIdCurrency();
                        String id_product_category = detaiList.get(position).getIdProductCategory();
                        String nameProduct = detaiList.get(position).getName();
                        String description = detaiList.get(position).getDescription();
                        String stock = detaiList.get(position).getStock();
                        String price_capital = detaiList.get(position).getPriceCapital();
                        String price_sale = detaiList.get(position).getPriceSale();
                        String condition = detaiList.get(position).getCondition();
                        String imageProduct = detaiList.get(position).getImage();
                        String weight_value = detaiList.get(position).getMetadata().getWeightValue();
                        String weight = detaiList.get(position).getMetadata().getWeight();
                        //String nameMerchant = detaiList.get(position).getMerchant().getName();
                        //String city = detaiList.get(position).getMerchant().getCity();


                        Intent detailproduct = new Intent(mContext, DetailProduct.class);
                        detailproduct.putExtra(Constans.KEY_ID, id);
                        detailproduct.putExtra(Constans.KEY_ID_PRODUCT_CATEGORY, id_product_category);
                        detailproduct.putExtra(Constans.KEY_ID_CURRENCY, id_currency);
                        detailproduct.putExtra(Constans.KEY_NAME_PRODUCT, nameProduct);
                        detailproduct.putExtra(Constans.KEY_PRICE_CAPITAL, price_capital);
                        detailproduct.putExtra(Constans.KEY_PRICE_SALE, price_sale);
                        detailproduct.putExtra(Constans.KEY_DESCRIPTIOM, description);
                        detailproduct.putExtra(Constans.KEY_STOCK, stock);
                        detailproduct.putExtra(Constans.KEY_CONDITION, condition);
                        detailproduct.putExtra(Constans.KEY_IMAGE, imageProduct);
                        detailproduct.putExtra(Constans.KEY_WEIGHT_VALUE, weight_value);
                        detailproduct.putExtra(Constans.KEY_WEIGHT, weight);
                        //detailproduct.putExtra(Constans.KEY_NAME_MERCHNAT, nameMerchant);
                        ///detailproduct.putExtra(Constans.KEY_CITY_MERCHANT, city);
                        startActivity(detailproduct);
                    }
                }));
    }
}
