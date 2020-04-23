package com.application.kreditimpian.HistoryPesanan.TabMultiguna;

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

import com.application.kreditimpian.Adapter.AdapterMultiguna;
import com.application.kreditimpian.Adapter.AdapterRequetsProduct;
import com.application.kreditimpian.Api.SharedPrefManager;
import com.application.kreditimpian.Api.api_v2.BaseApiService;
import com.application.kreditimpian.Api.api_v2.UtilsApi;
import com.application.kreditimpian.Constan.ConstanHistoryPesanan;
import com.application.kreditimpian.HistoryPesanan.TabProductRequest.DetailHistoryRequestProduct;
import com.application.kreditimpian.Marketplace.FragSemuaKategori.RecyclerItemClickListener;
import com.application.kreditimpian.Model.ModelMultiguna.DataItem;
import com.application.kreditimpian.Model.ModelMultiguna.ResponseMultiguna;
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


public class FragHistoryMultiguna extends Fragment {

    SharedPrefManager sharedPrefManager;

    @BindView(R.id.listMultiguna)
    RecyclerView listMultiguna;
    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefresh;
    ProgressDialog progressBar;
    @BindView(R.id.empty)
    ImageView empty;

    BaseApiService mApiService;
    Context mContext;

    List<DataItem> resultItemList = new ArrayList<>();
    AdapterMultiguna adapterMultiguna;

    public FragHistoryMultiguna() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_history_multiguna, container, false);

        sharedPrefManager = new SharedPrefManager(getActivity());
        swipeRefresh = view.findViewById(R.id.swipeRefresh);
        swipeRefresh.setColorScheme(android.R.color.holo_orange_dark,
                android.R.color.holo_orange_light,
                android.R.color.holo_orange_dark,
                android.R.color.holo_orange_light);

        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getListMultiguna();
                swipeRefresh.setRefreshing(false);
            }
        });


        ButterKnife.bind(this, view);
        mContext = getActivity();
        mApiService = UtilsApi.getAPIService();
        adapterMultiguna = new AdapterMultiguna(getActivity(), resultItemList);
        //RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        GridLayoutManager mLayoutManager = new GridLayoutManager(getContext(), 1, GridLayoutManager.VERTICAL, false);
        listMultiguna.setLayoutManager(mLayoutManager);
        listMultiguna.setItemAnimator(new DefaultItemAnimator());

        getListMultiguna();


        return view;
    }

    private void getListMultiguna(){
        swipeRefresh.setRefreshing(true);

        HashMap<String, String> params = new HashMap<>();
        params.put("id_member", sharedPrefManager.getSpIdMember());

        mApiService.getMultiguna(params).enqueue(new Callback<ResponseMultiguna>() {
            @Override
            public void onResponse(Call<ResponseMultiguna> call, Response<ResponseMultiguna> response) {
                if (response.isSuccessful()){
                    ///progressBar.dismiss();
                    if (response.body().getResponseCode()==200) {
                        swipeRefresh.setRefreshing(false);

                        final List<DataItem> HistoryTransaction = response.body().getData();

                        listMultiguna.setAdapter(new AdapterMultiguna(mContext, HistoryTransaction));
                        adapterMultiguna.notifyDataSetChanged();
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
            public void onFailure(Call<ResponseMultiguna> call, Throwable t) {
                /// progressBar.dismiss();
                swipeRefresh.setRefreshing(false);
                Toast.makeText(mContext,"Koneksi anda bermasalah", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initDataIntent(final List<DataItem> detaiList){
        listMultiguna.addOnItemTouchListener(
                new RecyclerItemClickListener(mContext, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {

                        String id_transactions = detaiList.get(position).getIdTransaction();
                        String status = detaiList.get(position).getStatus();
                        String number = detaiList.get(position).getNumber();
                        String tenor = detaiList.get(position).getTenor();
                        String location = detaiList.get(position).getLocation();
                        String cicilan = detaiList.get(position).getCicilan();
                        String vehicle_year = detaiList.get(position).getVehicleYear();
                        String vehicle_type = detaiList.get(position).getVehicleType();
                        String vehicle_brand = detaiList.get(position).getVehicleBrand();
                        String vehicle_price = detaiList.get(position).getVehiclePrice();
                        String loan = detaiList.get(position).getLoan();
                        String vehicles = detaiList.get(position).getVehicles();
                        String images = detaiList.get(position).getImages();
                        String mitra_kredit = detaiList.get(position).getMitra_kredit();
                        String name_city = detaiList.get(position).getMain_address().getName_city();
                        String receiver = detaiList.get(position).getMain_address().getReceiver();
                        String address_name = detaiList.get(position).getMain_address().getAddressName();
                        String phone = detaiList.get(position).getMain_address().getPhone();
                        String name_district = detaiList.get(position).getMain_address().getName_district();
                        String postal_code = detaiList.get(position).getMain_address().getPostalCode();
                        String address = detaiList.get(position).getMain_address().getAddress();



                        Intent detailProductRequest = new Intent(mContext, DetailMultiguna.class);
                        detailProductRequest.putExtra("id_transaction", id_transactions);
                        detailProductRequest.putExtra("status", status);
                        detailProductRequest.putExtra("tenor", tenor);
                        detailProductRequest.putExtra("number", number);
                        detailProductRequest.putExtra("location", location);
                        detailProductRequest.putExtra("cicilan", cicilan);
                        detailProductRequest.putExtra("vehicle_year", vehicle_year);
                        detailProductRequest.putExtra("vehicle_type", vehicle_type);
                        detailProductRequest.putExtra("vehicle_brand", vehicle_brand);
                        detailProductRequest.putExtra("vehicle_price", vehicle_price);
                        detailProductRequest.putExtra("loan", loan);
                        detailProductRequest.putExtra("vehicles", vehicles);
                        detailProductRequest.putExtra("mitra_kredit", mitra_kredit);
                        detailProductRequest.putExtra("name_city", name_city);
                        detailProductRequest.putExtra("receiver", receiver);
                        detailProductRequest.putExtra("address_name", address_name);
                        detailProductRequest.putExtra("name_district", name_district);
                        detailProductRequest.putExtra("postal_code", postal_code);
                        detailProductRequest.putExtra("phone", phone);
                        detailProductRequest.putExtra("address", address);
                        detailProductRequest.putExtra("images", images);


                        startActivity(detailProductRequest);
                    }
                }));
    }

}
