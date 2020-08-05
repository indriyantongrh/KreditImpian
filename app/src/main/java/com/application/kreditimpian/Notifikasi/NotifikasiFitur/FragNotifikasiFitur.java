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

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.application.kreditimpian.Adapter.AdapterNotifFitur;
import com.application.kreditimpian.Adapter.AdapterNotifikasiFeature;
import com.application.kreditimpian.Api.SharedPrefManager;
import com.application.kreditimpian.Api.api_v2.BaseApiService;
import com.application.kreditimpian.Api.api_v2.UtilsApi;
import com.application.kreditimpian.Constan.ConstanHistoryPesanan;
import com.application.kreditimpian.FormPengajuan.UpgradeImpian.viewmodel.UpgradeImpianViewModel;
import com.application.kreditimpian.HistoryPesanan.TabMultiguna.DetailMultiguna;
import com.application.kreditimpian.Marketplace.FragSemuaKategori.RecyclerItemClickListener;
import com.application.kreditimpian.Model.ModelNotifFitur.DataItem;
import com.application.kreditimpian.Model.ModelNotifFitur.ResponseNotifFitur;
import com.application.kreditimpian.Model.ModelNotifikasiFeature.ResponseNotifikasiFeature;
//import com.application.kreditimpian.Model.ModelNotifikasiFeature.ResultItem;
import com.application.kreditimpian.Model.ModelNotifikasiFeatures.JsonMember0;
import com.application.kreditimpian.Model.ModelNotifikasiFeatures.Metadata;
import com.application.kreditimpian.Model.ModelNotifikasiFeatures.ResponseNotifikasiFeatures;
import com.application.kreditimpian.Model.ModelNotifikasiFeatures.ResultItem;
import com.application.kreditimpian.Model.ModelNotifikasiFeatures.Send;
import com.application.kreditimpian.Model.ModelNotifikasiFeatures.Shipping;
import com.application.kreditimpian.Notifikasi.DetailNotifikasi;
import com.application.kreditimpian.Notifikasi.NotifikasiAdapter;
import com.application.kreditimpian.R;
import com.shockwave.pdfium.PdfDocument;

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
    AdapterNotifikasiFeature adapterNotifikasiFeature;
    List<ResultItem> resultItemList = new ArrayList<>();

    /*    AdapterNotifFitur adapterNotifFitur;
        List<DataItem> resultItemList = new ArrayList<>();*/
    public FragNotifikasiFitur() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_frag_notifikasi_fitur, container, false);
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
        //adapterNotifFitur = new AdapterNotifFitur(getActivity(), resultItemList);
        adapterNotifikasiFeature = new AdapterNotifikasiFeature(getActivity(), resultItemList);
        //RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        GridLayoutManager mLayoutManager = new GridLayoutManager(getContext(), 1, GridLayoutManager.VERTICAL, false);
        rvNotifikasi.setLayoutManager(mLayoutManager);
        rvNotifikasi.setItemAnimator(new DefaultItemAnimator());
        getNotifFitur();

        return view;
    }

    private void getNotifFitur() {
        swipeRefresh.setRefreshing(true);
        HashMap<String, String> params = new HashMap<>();
        params.put("id_member", sharedPrefManager.getSpIdMember());

        mApiService.getNotifikasiFeature(params).enqueue(new Callback<ResponseNotifikasiFeatures>() {
            @Override
            public void onResponse(Call<ResponseNotifikasiFeatures> call, Response<ResponseNotifikasiFeatures> response) {
                if (response.body().getStatus() == 200) {
                    swipeRefresh.setRefreshing(false);
                    final List<ResultItem> ListNotif = response.body().getResult();
                    rvNotifikasi.setAdapter(new AdapterNotifikasiFeature(mContext, ListNotif));
                    adapterNotifikasiFeature.notifyDataSetChanged();
                    empty.setVisibility(View.GONE);
                    initDataIntent(ListNotif);
                } else {
                    swipeRefresh.setRefreshing(false);
                    empty.setVisibility(View.VISIBLE);

                }
            }

            @Override
            public void onFailure(Call<ResponseNotifikasiFeatures> call, Throwable t) {

            }
        });

    }

    private void initDataIntent(final List<ResultItem> detaiList) {
        rvNotifikasi.addOnItemTouchListener(
                new RecyclerItemClickListener(mContext, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {

                        /*if (detaiList.get(position).getTransaction().getMetadata().getShipping().getSend().getAddressLabel() == null) {
                            Toast.makeText(getContext(), "menunggu konfirmasi user", Toast.LENGTH_LONG).show();
                        }*/
                        /*Handdle null di model*/
                        if (detaiList.get(position).getTransaction().getMetadata().getDownPayment() == null) {
                            Log.v("jajal1", "data dp kosong");
                        }else{
                            Log.v("jajal1", "data dp ada");
                        }
                        if (detaiList.get(position).getTransaction().getMetadata().getShipping() != null) {
                            Log.v("jajal2", "data Shipping2 kosong");

                        }else{
                            Log.v("jajal2", "data Shipping2 ada");
                        }

                        if (detaiList.get(position).getTransaction().getMetadata().getShipping().getSend() !=null){
                            Log.v("jajal3", "data Send kosong");
                        }else {
                            Log.v("jajal3", " data send ada");
                        }

                        /*Handdle null di model*/
                        if (detaiList.get(position).getTransaction().getMetadata().getInstallment() != null){
                            Log.v("jajal4", "data Instalment kosong");

                        }else{
                            Log.v("jajal4", "data Instalment ada");

                        }

                        if (detaiList.get(position).getTransaction().getMetadata().getShipping().getSend().getAddressLabel() !=null){
                            Log.v("jajal5", "data Address Label kosong");
                        }else {
                            Log.v("jajal5", "data Address Label ada");
                        }

                       if( detaiList.get(position).getTransaction()!=null &&
                                detaiList.get(position).getTransaction().getMetadata()!=null&&
                                detaiList.get(position).getTransaction().getMetadata().getShipping()!=null &&
                               detaiList.get(position).getTransaction().getMetadata().getShipping().getSend() !=null
                       ){

                           Log.v("jajal6", "data Kosng nih");

                       }else{
                           Log.v("jajal6", "data ada");
                       }

                        String status = detaiList.get(position).getMetadata();
                        String number = detaiList.get(position).getTransaction().getNumber();
                        String id_product = detaiList.get(position).getTransaction().getProduct().getId();
                        String id_product_category = detaiList.get(position).getTransaction().getProduct().getIdProductCategory();
                        String name = detaiList.get(position).getTransaction().getProduct().getName();
                        String price_capital = detaiList.get(position).getTransaction().getProduct().getPriceSale();
                        String price_sale = detaiList.get(position).getTransaction().getProduct().getPriceSale();
                        String image = detaiList.get(position).getTransaction().getProduct().getImage();

                        String name_company = detaiList.get(position).getTransaction().getCreditor().getName();
                        String tenor = detaiList.get(position).getTransaction().getMetadata().getTenor();
                        String down_payment = detaiList.get(position).getTransaction().getMetadata().getDownPayment();
                        String note = detaiList.get(position).getTransaction().getMetadata().getNote();
                        String postal_fee = detaiList.get(position).getTransaction().getMetadata().getPostalFee();


                        String payment_method = detaiList.get(position).getTransaction().getMetadata().getPaymentMethod();
                        String installment = detaiList.get(position).getTransaction().getMetadata().getInstallment().getJsonMember0();
                        String total_pembayaran = detaiList.get(position).getTransaction().getMetadata().getTotalPembayaran();
                        String courier = detaiList.get(position).getTransaction().getMetadata().getCourier();


                        String address_label = detaiList.get(position).getTransaction().getMetadata().getShipping().getSend().getAddressLabel();
                        String receiver = detaiList.get(position).getTransaction().getMetadata().getShipping().getSend().getReceiver();
                        String mobile = detaiList.get(position).getTransaction().getMetadata().getShipping().getSend().getMobile();
                        String city = detaiList.get(position).getTransaction().getMetadata().getShipping().getSend().getNameCity();
                        String district = detaiList.get(position).getTransaction().getMetadata().getShipping().getSend().getNameDistrict();
                        String address = detaiList.get(position).getTransaction().getMetadata().getShipping().getSend().getAddress();
                        String name_district = detaiList.get(position).getTransaction().getMetadata().getShipping().getSend().getNameDistrict();
                        String name_city = detaiList.get(position).getTransaction().getMetadata().getShipping().getSend().getNameCity();
                        String postal_code = detaiList.get(position).getTransaction().getMetadata().getShipping().getSend().getPostalCode();



                        Intent detailNotifikasi = new Intent(getActivity(), DetailNotifikasiFitur.class);
                        detailNotifikasi.putExtra("metadata", status);
                        detailNotifikasi.putExtra("number", number);
                        detailNotifikasi.putExtra(ConstanHistoryPesanan.KEY_NAME_PRODUCT, name);
                        detailNotifikasi.putExtra(ConstanHistoryPesanan.KEY_PRICE_SALE, price_capital);
                        detailNotifikasi.putExtra(ConstanHistoryPesanan.KEY_PRICE_SALE, price_sale);
                        detailNotifikasi.putExtra("image", image);
                        detailNotifikasi.putExtra(ConstanHistoryPesanan.KEY_NAME_COMPANY, name_company);
                        detailNotifikasi.putExtra(ConstanHistoryPesanan.KEY_DOWN_PAYMENT, down_payment);
                        detailNotifikasi.putExtra(ConstanHistoryPesanan.KEY_TENOR, tenor);
                        detailNotifikasi.putExtra(ConstanHistoryPesanan.KEY_POSTAL_FEE, postal_fee);
                        detailNotifikasi.putExtra(ConstanHistoryPesanan.KEY_PAYMENT_METHOD, payment_method);
                        detailNotifikasi.putExtra(ConstanHistoryPesanan.KEY_INSTALLMENT, installment);
                        detailNotifikasi.putExtra(ConstanHistoryPesanan.KEY_TOTAL_PEMBAYARAN, total_pembayaran);
                        detailNotifikasi.putExtra(ConstanHistoryPesanan.KEY_COURIER, courier);
                        detailNotifikasi.putExtra(ConstanHistoryPesanan.KEY_ADDRESS_LABEL, address_label);
                        detailNotifikasi.putExtra(ConstanHistoryPesanan.KEY_RECEIVER, receiver);
                        detailNotifikasi.putExtra(ConstanHistoryPesanan.KEY_MOBILE, mobile);
                        detailNotifikasi.putExtra(ConstanHistoryPesanan.KEY_CITY, city);
                        detailNotifikasi.putExtra(ConstanHistoryPesanan.KEY_DISTRICT, district);
                        detailNotifikasi.putExtra(ConstanHistoryPesanan.KEY_ADDRESS, address);
                        detailNotifikasi.putExtra(ConstanHistoryPesanan.KEY_NAME_CITY, name_city);
                        detailNotifikasi.putExtra(ConstanHistoryPesanan.KEY_NAME_DISTRICT, name_district);
                        detailNotifikasi.putExtra(ConstanHistoryPesanan.KEY_POSTAL_CODE, postal_code);


                        startActivity(detailNotifikasi);
                        getActivity().finish();

                    }
                }));

    }
}
