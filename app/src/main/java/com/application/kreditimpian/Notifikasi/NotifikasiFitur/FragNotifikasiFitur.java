package com.application.kreditimpian.Notifikasi.NotifikasiFitur;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.application.kreditimpian.Adapter.AdapterNotifFitur;
import com.application.kreditimpian.Api.SharedPrefManager;
import com.application.kreditimpian.Api.api_v2.BaseApiService;
import com.application.kreditimpian.Api.api_v2.UtilsApi;
import com.application.kreditimpian.FormPengajuan.UpgradeImpian.viewmodel.UpgradeImpianViewModel;
import com.application.kreditimpian.HistoryPesanan.TabMultiguna.DetailMultiguna;
import com.application.kreditimpian.Marketplace.FragSemuaKategori.RecyclerItemClickListener;
import com.application.kreditimpian.Model.ModelNotifFitur.DataItem;
import com.application.kreditimpian.Model.ModelNotifFitur.ResponseNotifFitur;
import com.application.kreditimpian.Notifikasi.NotifikasiAdapter;
import com.application.kreditimpian.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FragNotifikasiFitur extends Fragment {
    SharedPrefManager sharedPrefManager;
    private Context context;
    private NotifikasiAdapter notifikasiAdapter;
    private DataItem modelNotifikasi;
    private UpgradeImpianViewModel upgradeImpianViewModel;
    ImageView empty;
    Context mContext;
    private ImageView imgBack;
    private RecyclerView rvNotifikasi;
    private LinearLayoutManager linearLayoutManager;
    private ProgressBar progress_bar;
    private SwipeRefreshLayout swipeRefresh;
    private String idMember;
    private static final int PAGE_SIZE = 10;
    private boolean isLastPage = false,
            isLoading = false;
    BaseApiService mApiService;
    AdapterNotifFitur adapterNotifFitur;
    List<DataItem> resultItemList = new ArrayList<>();
    public FragNotifikasiFitur() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=  inflater.inflate(R.layout.fragment_frag_notifikasi_fitur, container, false);
        context = getActivity();
        empty = view.findViewById(R.id.empty);
        rvNotifikasi = view.findViewById(R.id.rvNotifikasi);
        sharedPrefManager = new SharedPrefManager(getActivity());
        ////Toast.makeText(getActivity(), "id member anda : "+sharedPrefManager.getSpIdMember(), Toast.LENGTH_LONG).show();

        swipeRefresh = view.findViewById(R.id.swipeRefresh);
        swipeRefresh.setColorScheme(android.R.color.holo_orange_dark,
                android.R.color.holo_orange_light,
                android.R.color.holo_orange_dark,
                android.R.color.holo_orange_light);

        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getNotifFitur();
                swipeRefresh.setRefreshing(false);
            }
        });


        ButterKnife.bind(this, view);
        mContext = getActivity();
        mApiService = UtilsApi.getAPIService();
        adapterNotifFitur = new AdapterNotifFitur(getActivity(), resultItemList);
        //RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        GridLayoutManager mLayoutManager = new GridLayoutManager(getContext(), 1, GridLayoutManager.VERTICAL, false);
        rvNotifikasi.setLayoutManager(mLayoutManager);
        rvNotifikasi.setItemAnimator(new DefaultItemAnimator());

        getNotifFitur();



        return  view;
    }

    private void getNotifFitur(){

        swipeRefresh.setRefreshing(true);
        HashMap<String, String> params = new HashMap<>();
        params.put("id_member", sharedPrefManager.getSpIdMember());

        mApiService.getnotifikasiFitur(params).enqueue(new Callback<ResponseNotifFitur>() {
            @Override
            public void onResponse(Call<ResponseNotifFitur> call, Response<ResponseNotifFitur> response) {
                if(response.body().getResponseCode()==200){
                    swipeRefresh.setRefreshing(false);
                    final List<DataItem> ListNotif = response.body().getData();
                    rvNotifikasi.setAdapter(new AdapterNotifFitur(mContext, ListNotif));
                    adapterNotifFitur.notifyDataSetChanged();
                    empty.setVisibility(View.GONE);
                    initDataIntent(ListNotif);
                }else {
                    Toast.makeText(getContext(), "Error 1", Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Call<ResponseNotifFitur> call, Throwable t) {
                Toast.makeText(getContext(), "Error 2", Toast.LENGTH_LONG).show();

            }
        });

   /*     HashMap<String, String> params = new HashMap<>();
        params.put("id_member", sharedPrefManager.getSpIdMember());

        mApiService.getnotifikasiFitur(params).enqueue(new Callback<ResponseNotifFitur>() {
            @Override
            public void onResponse(Call<ResponseNotifFitur> call, Response<ResponseNotifFitur> response) {
                    if (response.body().getResponseCode()==200) {
                        swipeRefresh.setRefreshing(false);

                        final List<DataItem> ListNotif = response.body().getData();
                        Toast.makeText(getContext(), "data list : "+ListNotif, Toast.LENGTH_LONG).show();
                       *//* rvNotifikasi.setAdapter(new AdapterNotifFitur(mContext, HistoryTransaction));
                        adapterNotifFitur.notifyDataSetChanged();
                        empty.setVisibility(View.GONE);*//*
                        ///initDataIntent(HistoryTransaction);
                    }else {
                        swipeRefresh.setRefreshing(false);
                        empty.setVisibility(View.VISIBLE);
                    }
            }

            @Override
            public void onFailure(Call<ResponseNotifFitur> call, Throwable t) {
                /// progressBar.dismiss();
                swipeRefresh.setRefreshing(false);
                Toast.makeText(mContext,"Koneksi anda bermasalah", Toast.LENGTH_SHORT).show();
            }
        });*/
    }

    private void initDataIntent(final List<DataItem> detaiList){
        rvNotifikasi.addOnItemTouchListener(
                new RecyclerItemClickListener(mContext, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {

                        String id_member = detaiList.get(position).getIdMember();
                        String message = detaiList.get(position).getMessage();
                        String metadata = detaiList.get(position).getMetadata();
                        String status = detaiList.get(position).getStatus();
                        String id_product_request = detaiList.get(position).getIdProductRequest();
                        String content = detaiList.get(position).getContent();
                        String method = detaiList.get(position).getMethod();
                        String record_create_timestamp = detaiList.get(position).getRecordCreateTimestamp();
                        String name = detaiList.get(position).getName();
                        String price_sale = detaiList.get(position).getPrice_sale();


                        Intent detailProductRequest = new Intent(mContext, DetailNotifikasiFitur.class);
                        detailProductRequest.putExtra("id_member", id_member);
                        detailProductRequest.putExtra("message", message);
                        detailProductRequest.putExtra("metadata", metadata);
                        detailProductRequest.putExtra("status", status);
                        detailProductRequest.putExtra("id_product_request", id_product_request);
                        detailProductRequest.putExtra("content", content);
                        detailProductRequest.putExtra("method", method);
                        detailProductRequest.putExtra("record_create_timestamp", record_create_timestamp);
                        detailProductRequest.putExtra("name", name);
                        detailProductRequest.putExtra("price_sale", price_sale);
                        startActivity(detailProductRequest);
                    }
                }));
    }


}
