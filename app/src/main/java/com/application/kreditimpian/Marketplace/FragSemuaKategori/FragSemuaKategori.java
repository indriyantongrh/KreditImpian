package com.application.kreditimpian.Marketplace.FragSemuaKategori;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.application.kreditimpian.Adapter.AdapterProductBaru;
import com.application.kreditimpian.Api.SharedPrefManager;
import com.application.kreditimpian.Api.api_v2.BaseApiService;
import com.application.kreditimpian.Api.api_v2.UtilsApi;
import com.application.kreditimpian.Constan.Constans;
import com.application.kreditimpian.DetailProduct.DetailProduct;

import com.application.kreditimpian.Model.ModelProductBaru.ResponseProductBaru;
import com.application.kreditimpian.Model.ModelProductBaru.ResultItem;
import com.application.kreditimpian.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FragSemuaKategori extends Fragment {

    @BindView(R.id.listAllProduct)
    RecyclerView listAllProduct;
    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefresh;
    ProgressDialog progressBar;
    @BindView(R.id.empty)
    ImageView empty;
    @BindView(R.id.pbLoading)
    ProgressBar pbLoading;

    Context mContext;

    List<ResultItem> resultItemList = new ArrayList<>();
    AdapterProductBaru adapterProductBaru;
    BaseApiService mApiService;
    SharedPrefManager sharedPrefManager;
    private GridLayoutManager mLayoutManager;
    private int limit = 10;
    private int page = 1 ;

    //Variable Pagingnation
    private boolean isLoading = true;
    private boolean isLastpage = false;
    private int pastVisibleItems, visibleItemCount, totallItemCount, previous_total = 0;
    private int view_threshold = 10;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_frag_semua_kategori, container, false);

        swipeRefresh = rootView.findViewById(R.id.swipeRefresh);
        swipeRefresh.setColorScheme(android.R.color.holo_orange_dark,
                android.R.color.holo_orange_light,
                android.R.color.holo_orange_dark,
                android.R.color.holo_orange_light);

        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getResultList();
               /// performPagaination();
                swipeRefresh.setRefreshing(false);
            }
        });
        sharedPrefManager = new SharedPrefManager(getActivity());

        ButterKnife.bind(this, rootView);
        mContext = getActivity();
        mApiService = UtilsApi.getAPIService();

        adapterProductBaru = new AdapterProductBaru(getActivity(), resultItemList);
        //RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
         mLayoutManager = new GridLayoutManager(getActivity(), 2, GridLayoutManager.VERTICAL, false);
        listAllProduct.setLayoutManager(mLayoutManager);
        listAllProduct.setItemAnimator(new DefaultItemAnimator());


        getResultList();

        /*listAllProduct.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                visibleItemCount = mLayoutManager.getChildCount();
                totallItemCount = mLayoutManager.getItemCount();
                pastVisibleItems = mLayoutManager.findFirstVisibleItemPosition();


                if(dy>0)
                {
                    if(isLoading)
                    {
                        if(totallItemCount > previous_total)
                         ///if((visibleItemCount + pastVisibleItems) >= totallItemCount)
                        {
                            isLoading = false;
                            ///performPagaination();
                            previous_total = totallItemCount;
                        }
                    }

                    if(!isLoading&&(totallItemCount-visibleItemCount)<=(pastVisibleItems+view_threshold))
                    {
                        page++;
                        performPagaination();
                        isLoading = true;
                    }

                }



            }
        });*/   // get page in product category
        return rootView;


    }


    private void getResultList(){
      ////  progressBar = ProgressDialog.show(getActivity(), null, "Loading...", true, false);
        swipeRefresh.setRefreshing(true);
        /*mApiService.getResult(limit,page).enqueue(new Callback<ResponseProductBaru>() {*/ /*get data use limit*/

        mApiService.getResult().enqueue(new Callback<ResponseProductBaru>() {
            @Override
            public void onResponse(Call<ResponseProductBaru> call, Response<ResponseProductBaru> response) {
                ////pbLoading.setVisibility(View.GONE);
                    ///progressBar.dismiss();
                swipeRefresh.setRefreshing(false);
                if (response.body().getStatus()==200) {


                         ///swipeRefresh.setRefreshing(false);
                        //progressBar.dismiss();
                        ///pbLoading.setVisibility(View.GONE);

                        final List<ResultItem> Allproduct = response.body().getResult();
                        Log.v("jajal" , Allproduct+ "list");

                        listAllProduct.setAdapter(new AdapterProductBaru(mContext, Allproduct));
                        adapterProductBaru.notifyDataSetChanged();
                        empty.setVisibility(View.GONE);
                        initDataIntent(Allproduct);
                    }else {
                        //progressBar.dismiss();
                    swipeRefresh.setRefreshing(false);
                        ///pbLoading.setVisibility(View.GONE);
                        empty.setVisibility(View.VISIBLE);
                    }

            }

            @Override
            public void onFailure(Call<ResponseProductBaru> call, Throwable t) {
                Log.v("jajal" , t.getMessage()+ "list");
                ///progressBar.dismiss();
                //pbLoading.setVisibility(View.GONE);
                swipeRefresh.setRefreshing(false);
                Toast.makeText(mContext, "Koneksi Internet Bermasalah", Toast.LENGTH_SHORT).show();
            }
        });


    }


    private void initDataIntent(final List<ResultItem> detaiList){
        listAllProduct.addOnItemTouchListener(
                new RecyclerItemClickListener(mContext, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {

                         String id = detaiList.get(position).getId();
                         String id_currency = detaiList.get(position).getCurrency().getId();
                         String id_product_category = detaiList.get(position).getCategory().getId();
                         String nameProduct = detaiList.get(position).getName();
                         String description = detaiList.get(position).getDescription();
                         String stock = detaiList.get(position).getStock();
                         String price_capital = detaiList.get(position).getPriceCapital();
                         String price_sale = detaiList.get(position).getPriceSale();
                         String condition = detaiList.get(position).getCondition();
                         String imageProduct = detaiList.get(position).getImage();
                         String weight_value = detaiList.get(position).getMetadata().getWeightValue();
                         String weight = detaiList.get(position).getMetadata().getWeight();
                         String nameMerchant = detaiList.get(position).getMerchant().getName();
                         String city = detaiList.get(position).getMerchant().getCity();


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
                        detailproduct.putExtra(Constans.KEY_NAME_MERCHNAT, nameMerchant);
                        detailproduct.putExtra(Constans.KEY_CITY_MERCHANT, city);
                        startActivity(detailproduct);
                    }
                }));
    }


    /*private void performPagaination(){

        swipeRefresh.setRefreshing(true);

        mApiService.getResult(limit, page).enqueue(new Callback<ResponseProductBaru>() {
            @Override
            public void onResponse(Call<ResponseProductBaru> call, Response<ResponseProductBaru> response) {

                if(response.body().getStatus()==200){

                    List<ResultItem> dataList = response.body().getResult();
                    adapterProductBaru.addListBarang(dataList);
                    adapterProductBaru.notifyDataSetChanged();
                    Toast.makeText(getContext(), "Page "+page+" is Loaded", Toast.LENGTH_LONG).show();

                }else{
                    Toast.makeText(getContext(), "Toas Paging", Toast.LENGTH_LONG).show();
                }
                swipeRefresh.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<ResponseProductBaru> call, Throwable t) {
                Log.v("jajal" , t.getMessage()+ "list");
                ///progressBar.dismiss();
                pbLoading.setVisibility(View.GONE);
                Toast.makeText(mContext, "Koneksi Internet Bermasalah", Toast.LENGTH_SHORT).show();
            }
        });

    }*/  // methode get page in product

}
