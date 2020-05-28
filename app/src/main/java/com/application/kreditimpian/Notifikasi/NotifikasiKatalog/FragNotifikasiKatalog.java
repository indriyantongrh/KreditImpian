package com.application.kreditimpian.Notifikasi.NotifikasiKatalog;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.application.kreditimpian.Api.SharedPrefManager;
import com.application.kreditimpian.Constan.ConstanHistoryPesanan;
import com.application.kreditimpian.FormPengajuan.UpgradeImpian.viewmodel.UpgradeImpianViewModel;
import com.application.kreditimpian.FormPengajuan.UpgradeImpian.viewmodel.ViewModelFactory;
import com.application.kreditimpian.Marketplace.FragSemuaKategori.RecyclerItemClickListener;
import com.application.kreditimpian.Model.ModelNotifikasi.ModelNotifikasi;
import com.application.kreditimpian.Notifikasi.DetailNotifikasi;
import com.application.kreditimpian.Notifikasi.NotifikasiActivity;
import com.application.kreditimpian.Notifikasi.NotifikasiAdapter;
import com.application.kreditimpian.Notifikasi.ViewHolder.NotifikasiViewHolder;
import com.application.kreditimpian.R;

import java.util.ArrayList;


public class FragNotifikasiKatalog extends Fragment implements NotifikasiAdapter.OnSeenClick {

    private Context context;
    private NotifikasiAdapter notifikasiAdapter;
    private ModelNotifikasi modelNotifikasi;
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

    public FragNotifikasiKatalog() {
        // Required empty public constructor
    }


    private RecyclerView.OnScrollListener recyclerViewOnScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
        }

        @Override
        public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            int visibleItemCount = linearLayoutManager.getChildCount();
            int totalItemCount = linearLayoutManager.getItemCount();
            int firstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();

            if (!isLoading && !isLastPage) {
                if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount && firstVisibleItemPosition >= 0 && totalItemCount >= PAGE_SIZE) {
                    isLoading = true;
                    new Handler().postDelayed(() -> loadPaging(), 2000);
                }
            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_frag_notifikasi_katalog, container, false);

        context = getActivity();
        upgradeImpianViewModel = new ViewModelProvider(getViewModelStore(), new ViewModelFactory(context)).get(UpgradeImpianViewModel.class);

        SharedPrefManager sharedPrefManager = new SharedPrefManager(context);
        idMember = sharedPrefManager.getSpIdMember();

       /* View includeToolbar = findViewById(R.id.includeToolbar);
        imgBack = includeToolbar.findViewById(R.id.imgBack);*/
        rvNotifikasi = view.findViewById(R.id.rvNotifikasi);
        progress_bar = view.findViewById(R.id.progress_bar);
         swipeRefresh = view.findViewById(R.id.swipeRefresh);
        empty = view.findViewById(R.id.empty);

        linearLayoutManager = new LinearLayoutManager(context);
        rvNotifikasi.setLayoutManager(linearLayoutManager);

        notifikasiAdapter = new NotifikasiAdapter(context, this);
        rvNotifikasi.setAdapter(notifikasiAdapter);
        rvNotifikasi.addOnScrollListener(recyclerViewOnScrollListener);

        // imgBack.setOnClickListener(this);

        swipeRefresh.setColorSchemeResources(R.color.colorOrange, R.color.colorOrange);
        swipeRefresh.setOnRefreshListener(() -> new Handler().postDelayed(() -> {
            swipeRefresh.setRefreshing(false);
            getNotifikasi();
        }, 2000));
        getNotifikasi();

        return  view;
    }

    @SuppressWarnings("unchecked")
    private void loadPaging() {
        isLoading = false;
        modelNotifikasi = new ModelNotifikasi();
        modelNotifikasi.setIdMember(idMember);

        upgradeImpianViewModel.setModelNotifikasi(modelNotifikasi);
        upgradeImpianViewModel.getNotifikasi().observe(this, hashMap -> {
            if (hashMap.get("code").toString().equals("200")) {
                ArrayList<ModelNotifikasi> modelNotifikasis = (ArrayList<ModelNotifikasi>) hashMap.get("list");
                if (modelNotifikasis != null) {
                    int index = notifikasiAdapter.getItemCount() - 1;
                    int end;
                    if (index + PAGE_SIZE <= modelNotifikasis.size()) {
                        end = index + PAGE_SIZE;
                    } else {
                        end = modelNotifikasis.size();
                    }
                    if (end <= modelNotifikasis.size()) {
                        ArrayList<ModelNotifikasi> modelNotifikasiArrayList = new ArrayList<>();
                        for (int i = index; i < end; i++) {
                            ModelNotifikasi modelNotifikasi = new ModelNotifikasi();
                            modelNotifikasi.setIdNotifikasi(modelNotifikasis.get(i).getIdNotifikasi());
                            modelNotifikasi.setMessage(modelNotifikasis.get(i).getMessage());
                            modelNotifikasi.setStatus(modelNotifikasis.get(i).getStatus());
                            modelNotifikasi.setMetadata(modelNotifikasis.get(i).getMetadata());
                            modelNotifikasi.setTgl(modelNotifikasis.get(i).getTgl());
                            modelNotifikasi.setNumber(modelNotifikasis.get(i).getNumber());
                            modelNotifikasi.setId_product(modelNotifikasis.get(i).getId_product());
                            modelNotifikasi.setId_product_category(modelNotifikasis.get(i).getId_product_category());
                            modelNotifikasi.setName(modelNotifikasis.get(i).getName());
                            modelNotifikasi.setPrice_capital(modelNotifikasis.get(i).getPrice_capital());
                            modelNotifikasi.setPrice_sale(modelNotifikasis.get(i).getPrice_sale());
                            modelNotifikasi.setFilename(modelNotifikasis.get(i).getFilename());
                            modelNotifikasi.setName_merchant(modelNotifikasis.get(i).getName_merchant());
                            modelNotifikasi.setTenor(modelNotifikasis.get(i).getTenor());
                            modelNotifikasi.setDown_payment(modelNotifikasis.get(i).getDown_payment());
                            modelNotifikasi.setNote(modelNotifikasis.get(i).getNote());
                            modelNotifikasi.setName_company(modelNotifikasis.get(i).getName_company());
                            modelNotifikasi.setPostal_fee(modelNotifikasis.get(i).getPostal_fee());
                            modelNotifikasi.setName_city(modelNotifikasis.get(i).getName_city());
                            modelNotifikasi.setName_district(modelNotifikasis.get(i).getName_district());
                            modelNotifikasi.setPayment_method(modelNotifikasis.get(i).getPayment_method());
                            modelNotifikasi.setTotal_pembayaran(modelNotifikasis.get(i).getTotal_pembayaran());
                            modelNotifikasi.setCourier(modelNotifikasis.get(i).getCourier());
                            modelNotifikasi.setName_city(modelNotifikasis.get(i).getName_city());
                            modelNotifikasi.setName_district(modelNotifikasis.get(i).getName_district());
                            modelNotifikasi.setSend(modelNotifikasis.get(i).getSend());


                            /*Installment modelInstallment = new Installment();
                            modelInstallment.setJsonMember0(modelInstallment.getJsonMember0());*/
                            modelNotifikasi.setInstallment(modelNotifikasis.get(i).getInstallment());
                            // data ne sing nampilke instalment ora daadi siji to??
                            //sing ning respone ? ho oh
                            modelNotifikasi.setSend(modelNotifikasis.get(i).getSend());

                            modelNotifikasiArrayList.add(modelNotifikasi);
                            initDataIntent(modelNotifikasiArrayList);
                        }
                        notifikasiAdapter.addAll(modelNotifikasiArrayList);
                        if (end >= modelNotifikasis.size()) {
                            notifikasiAdapter.setLoading(false);
                        }
                    } else {
                        notifikasiAdapter.setLoading(false);
                    }
                } else {
                    notifikasiAdapter.setLoading(false);
                }
            }
        });
    }


    private void getNotifikasi() {
        progress_bar.setVisibility(View.VISIBLE);
        swipeRefresh.setRefreshing(true);
        notifikasiAdapter.clear();
        modelNotifikasi = new ModelNotifikasi();
        modelNotifikasi.setIdMember(idMember);


        upgradeImpianViewModel.setModelNotifikasi(modelNotifikasi);

        upgradeImpianViewModel.getNotifikasi().observe(getActivity(), hashMap -> {
            if (hashMap.get("code").toString().equals("200")) {
                swipeRefresh.setRefreshing(false);
                ArrayList<ModelNotifikasi> modelNotifikasis = (ArrayList<ModelNotifikasi>) hashMap.get("list");
                if (modelNotifikasis != null) {

                    int batas;
                    if (modelNotifikasis.size() >= PAGE_SIZE) {
                        batas = PAGE_SIZE;
                    } else {
                        batas = modelNotifikasis.size();
                    }
                    ArrayList<ModelNotifikasi> modelNotifikasiArrayList = new ArrayList<>();
                    for (int i = 0; i < batas; i++) {
                        modelNotifikasi = new ModelNotifikasi();
                        modelNotifikasi.setIdNotifikasi(modelNotifikasis.get(i).getIdNotifikasi());
                        modelNotifikasi.setMessage(modelNotifikasis.get(i).getMessage());
                        modelNotifikasi.setStatus(modelNotifikasis.get(i).getStatus());
                        modelNotifikasi.setMetadata(modelNotifikasis.get(i).getMetadata());
                        modelNotifikasi.setTgl(modelNotifikasis.get(i).getTgl());
                        modelNotifikasi.setNumber(modelNotifikasis.get(i).getNumber());
                        modelNotifikasi.setId_product(modelNotifikasis.get(i).getId_product());
                        modelNotifikasi.setId_product_category(modelNotifikasis.get(i).getId_product_category());
                        modelNotifikasi.setName(modelNotifikasis.get(i).getName());
                        modelNotifikasi.setPrice_capital(modelNotifikasis.get(i).getPrice_capital());
                        modelNotifikasi.setPrice_sale(modelNotifikasis.get(i).getPrice_sale());
                        modelNotifikasi.setFilename(modelNotifikasis.get(i).getFilename());
                        modelNotifikasi.setName_merchant(modelNotifikasis.get(i).getName_merchant());
                        modelNotifikasi.setTenor(modelNotifikasis.get(i).getTenor());
                        modelNotifikasi.setDown_payment(modelNotifikasis.get(i).getDown_payment());
                        modelNotifikasi.setNote(modelNotifikasis.get(i).getNote());
                        modelNotifikasi.setName_company(modelNotifikasis.get(i).getName_company());
                        modelNotifikasi.setPostal_fee(modelNotifikasis.get(i).getPostal_fee());
                        modelNotifikasi.setName_city(modelNotifikasis.get(i).getName_city());
                        modelNotifikasi.setName_district(modelNotifikasis.get(i).getName_district());
                        modelNotifikasi.setPayment_method(modelNotifikasis.get(i).getPayment_method());
                        modelNotifikasi.setTotal_pembayaran(modelNotifikasis.get(i).getTotal_pembayaran());
                        modelNotifikasi.setCourier(modelNotifikasis.get(i).getCourier());

                        /*alamat*/
                        modelNotifikasi.setName_city(modelNotifikasis.get(i).getName_city());
                        modelNotifikasi.setName_district(modelNotifikasis.get(i).getName_district());


                        modelNotifikasi.setInstallment(modelNotifikasis.get(i).getInstallment());

                        /*Installment modelInstallment = new Installment();
                        modelInstallment.setJsonMember0(modelInstallment.getJsonMember0());
                        Log.v("jajalLemparInstallment", modelInstallment.getJsonMember0()+"");*/

                        modelNotifikasi.setSend(modelNotifikasis.get(i).getSend());


                        modelNotifikasiArrayList.add(modelNotifikasi);
                        initDataIntent(modelNotifikasiArrayList);
                    }
                    notifikasiAdapter.addAll(modelNotifikasiArrayList);
                    if (batas == modelNotifikasis.size()) {
                        notifikasiAdapter.setLoading(false);
                    }
                } else {
                    notifikasiAdapter.setLoading(false);
                }
            }else{
                empty.setVisibility(View.VISIBLE);
            }

            progress_bar.setVisibility(View.GONE);
        });
    }

    @Override
    public void onSeenClick(String idNotifikasi, NotifikasiViewHolder holder) {
        Log.v("jajal", idNotifikasi + " a");
        ModelNotifikasi modelNotifikasi = new ModelNotifikasi();
        modelNotifikasi.setIdNotifikasi(idNotifikasi);

        upgradeImpianViewModel.setModelNotifikasi(modelNotifikasi);
        upgradeImpianViewModel.updateNotifikasi().observe(this, s -> {
            if (s.equals("200")) {
                holder.layoutKlik.setBackgroundColor(ContextCompat.getColor(context, R.color.white));
            }
        });
    }


    private void initDataIntent(final ArrayList<ModelNotifikasi> modelNotifikasiArrayList){
        rvNotifikasi.addOnItemTouchListener(
                new RecyclerItemClickListener(mContext, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {

                        String status = modelNotifikasiArrayList.get(position).getMetadata();
                        String number = modelNotifikasiArrayList.get(position).getNumber();
                        String id_product = modelNotifikasiArrayList.get(position).getId_product();
                        String id_product_category = modelNotifikasiArrayList.get(position).getId_product_category();
                        String name = modelNotifikasiArrayList.get(position).getName();
                        String price_capital = modelNotifikasiArrayList.get(position).getPrice_capital();
                        String price_sale = modelNotifikasiArrayList.get(position).getPrice_sale();
                        String filename = modelNotifikasiArrayList.get(position).getFilename();
                        String name_merchant = modelNotifikasiArrayList.get(position).getName_merchant();
                        String name_company = modelNotifikasiArrayList.get(position).getName_company();
                        String tenor = modelNotifikasiArrayList.get(position).getTenor();
                        String down_payment = modelNotifikasiArrayList.get(position).getDown_payment();
                        String note = modelNotifikasiArrayList.get(position).getNote();
                        String postal_fee = modelNotifikasiArrayList.get(position).getPostal_fee();
                        String address_label = modelNotifikasiArrayList.get(position).getSend().getAddressLabel();
                        String receiver = modelNotifikasiArrayList.get(position).getSend().getReceiver();
                        String mobile = modelNotifikasiArrayList.get(position).getSend().getMobile();
                        String city = modelNotifikasiArrayList.get(position).getSend().getCity();
                        String district = modelNotifikasiArrayList.get(position).getSend().getDistrict();
                        String address = modelNotifikasiArrayList.get(position).getSend().getAddress();
                        String payment_method = modelNotifikasiArrayList.get(position).getPayment_method();
                        String installment = modelNotifikasiArrayList.get(position).getInstallment().getJsonMember0();
                        String total_pembayaran = modelNotifikasiArrayList.get(position).getTotal_pembayaran();
                        String courier = modelNotifikasiArrayList.get(position).getCourier();
                        String name_city = modelNotifikasiArrayList.get(position).getName_city();
                        String name_district = modelNotifikasiArrayList.get(position).getName_district();
                        String postal_code = modelNotifikasiArrayList.get(position).getSend().getPostalCode();

                        Intent detailNotifikasi = new Intent(getActivity(), DetailNotifikasi.class);

                        detailNotifikasi.putExtra("metadata", status);
                        detailNotifikasi.putExtra(ConstanHistoryPesanan.KEY_NUMBER, number);
                        detailNotifikasi.putExtra(ConstanHistoryPesanan.KEY_ID_PRODUCT, id_product);
                        detailNotifikasi.putExtra(ConstanHistoryPesanan.KEY_ID_PRODUCT_CATEGORY, id_product_category);
                        /// detailNotifikasi.putExtra(ConstanHistoryPesanan.KEY_CURRENCY, id_currency);
                        detailNotifikasi.putExtra(ConstanHistoryPesanan.KEY_NAME_PRODUCT, name);
                        /*detailNotifikasi.putExtra(ConstanHistoryPesanan.KEY_DESCRIPTION, description);
                        detailNotifikasi.putExtra(ConstanHistoryPesanan.KEY_SKU, sku);
                        detailNotifikasi.putExtra(ConstanHistoryPesanan.KEY_STOCK, stock);*/
                        detailNotifikasi.putExtra(ConstanHistoryPesanan.KEY_PRICE_CAPITAL, price_capital);
                        detailNotifikasi.putExtra(ConstanHistoryPesanan.KEY_PRICE_SALE, price_sale);

                        detailNotifikasi.putExtra(ConstanHistoryPesanan.KEY_FILENAME, filename);
                        ///detailNotifikasi.putExtra(ConstanHistoryPesanan.KEY_ID_MERCHANT, id_merchant);
                        detailNotifikasi.putExtra(ConstanHistoryPesanan.KEY_NAME_MERCHANT, name_merchant);
                        ///detailNotifikasi.putExtra(ConstanHistoryPesanan.KEY_ID_COMPANY, id_company);
                        detailNotifikasi.putExtra(ConstanHistoryPesanan.KEY_NAME_COMPANY, name_company);
                        detailNotifikasi.putExtra(ConstanHistoryPesanan.KEY_TENOR, tenor);
                        detailNotifikasi.putExtra(ConstanHistoryPesanan.KEY_DOWN_PAYMENT, down_payment);
                        detailNotifikasi.putExtra(ConstanHistoryPesanan.KEY_NOTE, note);
                        // detailNotifikasi.putExtra(ConstanHistoryPesanan.KEY_ID_CREDITOR, id_creditor);
                        detailNotifikasi.putExtra(ConstanHistoryPesanan.KEY_POSTAL_FEE, postal_fee);
                        detailNotifikasi.putExtra(ConstanHistoryPesanan.KEY_ADDRESS_LABEL, address_label);
                        detailNotifikasi.putExtra(ConstanHistoryPesanan.KEY_RECEIVER, receiver);
                        detailNotifikasi.putExtra(ConstanHistoryPesanan.KEY_MOBILE, mobile);
                        detailNotifikasi.putExtra(ConstanHistoryPesanan.KEY_CITY, city);
                        detailNotifikasi.putExtra(ConstanHistoryPesanan.KEY_DISTRICT, district);
                        detailNotifikasi.putExtra(ConstanHistoryPesanan.KEY_ADDRESS, address);
                        detailNotifikasi.putExtra(ConstanHistoryPesanan.KEY_PAYMENT_METHOD, payment_method);
                        detailNotifikasi.putExtra(ConstanHistoryPesanan.KEY_INSTALLMENT, installment);
                        detailNotifikasi.putExtra(ConstanHistoryPesanan.KEY_TOTAL_PEMBAYARAN, total_pembayaran);
                        detailNotifikasi.putExtra(ConstanHistoryPesanan.KEY_COURIER, courier);
                        detailNotifikasi.putExtra(ConstanHistoryPesanan.KEY_NAME_CITY, name_city);
                        detailNotifikasi.putExtra(ConstanHistoryPesanan.KEY_NAME_DISTRICT, name_district);
                        detailNotifikasi.putExtra(ConstanHistoryPesanan.KEY_POSTAL_CODE, postal_code);

                        startActivity(detailNotifikasi);
                        getActivity().finish();
                    }
                }));

    }
}
