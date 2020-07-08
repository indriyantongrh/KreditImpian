package com.application.kreditimpian.Notifikasi.NotifikasiProduct;

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
import android.widget.TextView;

import com.application.kreditimpian.Adapter.AdapterNotifikasiProduct;
import com.application.kreditimpian.Api.SharedPrefManager;
import com.application.kreditimpian.Api.api_v2.BaseApiService;
import com.application.kreditimpian.Api.api_v2.UtilsApi;
import com.application.kreditimpian.Constan.ConstanHistoryPesanan;
import com.application.kreditimpian.Marketplace.FragSemuaKategori.RecyclerItemClickListener;

//import com.application.kreditimpian.Model.ModelNotifikasiProduct.ResultItem;
import com.application.kreditimpian.Model.ModelNotifikasiProducts.ResponseNotifikasiProducts;
import com.application.kreditimpian.Model.ModelNotifikasiProducts.ResultItem;
import com.application.kreditimpian.Notifikasi.DetailNotifikasi;
import com.application.kreditimpian.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FragNotifikasiProduct extends Fragment {

    @BindView(R.id.rvNotifikasi)
    RecyclerView rvNotifikasi;
    @BindView(R.id.empty)
    ImageView empty;
    SwipeRefreshLayout swipeRefresh;
    SharedPrefManager sharedPrefManager;
    List<ResultItem> resultItemList = new ArrayList<>();
    AdapterNotifikasiProduct adapterNotifikasiProduct;
    BaseApiService mApiService;
    Context mContext;
    private Context context;

    public FragNotifikasiProduct() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_frag_notifikasi_product, container, false);

        context = getContext();
        swipeRefresh = view.findViewById(R.id.swipeRefresh);
        sharedPrefManager = new SharedPrefManager(getActivity());
        swipeRefresh.setColorScheme(android.R.color.holo_orange_dark,
                android.R.color.holo_orange_light,
                android.R.color.holo_orange_dark,
                android.R.color.holo_orange_light);

        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getNotifProduct();
                swipeRefresh.setRefreshing(false);
            }
        });

        ButterKnife.bind(this, view);
        mContext = getActivity();
        mApiService = UtilsApi.getAPIService();
        adapterNotifikasiProduct = new AdapterNotifikasiProduct(getActivity(), resultItemList);
        //RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        GridLayoutManager mLayoutManager = new GridLayoutManager(getContext(), 1, GridLayoutManager.VERTICAL, false);
        rvNotifikasi.setLayoutManager(mLayoutManager);
        rvNotifikasi.setItemAnimator(new DefaultItemAnimator());


        getNotifProduct();
        return view;
    }

    private void getNotifProduct() {

        swipeRefresh.setRefreshing(true);
        HashMap<String, String> params = new HashMap<>();
        params.put("id_member", sharedPrefManager.getSpIdMember());

        mApiService.getNotifikasiProduct(params).enqueue(new Callback<ResponseNotifikasiProducts>() {
            @Override
            public void onResponse(Call<ResponseNotifikasiProducts> call, Response<ResponseNotifikasiProducts> response) {
                if(response.body().getStatus()==200){
                    swipeRefresh.setRefreshing(false);
                    final List<ResultItem> ListNotifikasiProduct = response.body().getResult();
                    rvNotifikasi.setAdapter(new AdapterNotifikasiProduct(mContext, ListNotifikasiProduct));
                    adapterNotifikasiProduct.notifyDataSetChanged();
                    empty.setVisibility(View.GONE);
                    initDataIntent(ListNotifikasiProduct);
                }else{
                    swipeRefresh.setRefreshing(false);
                    ///progressBar.dismiss();
                    empty.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<ResponseNotifikasiProducts> call, Throwable t) {

            }
        });
    }

    private void initDataIntent(final List<ResultItem> detaiList){
        rvNotifikasi.addOnItemTouchListener(
                new RecyclerItemClickListener(mContext, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {

                        String status = detaiList.get(position).getMetadata();
                        String number = detaiList.get(position).getTransaction().getNumber();
                        String id_product = detaiList.get(position).getTransaction().getProduct().getId();
                        String id_product_category = detaiList.get(position).getTransaction().getProduct().getIdProductCategory();
                        String name = detaiList.get(position).getTransaction().getProduct().getName();
                        String price_capital = detaiList.get(position).getTransaction().getProduct().getPriceCapital();
                        String price_sale = detaiList.get(position).getTransaction().getProduct().getPriceSale();
                        String filename = detaiList.get(position).getTransaction().getProduct().getImage();
                        String name_merchant = detaiList.get(position).getTransaction().getProduct().getMerchant().getName();
                        String name_company = detaiList.get(position).getTransaction().getCreditor().getName();
                        String tenor = detaiList.get(position).getTransaction().getMetadata().getTenor();
                        String down_payment = detaiList.get(position).getTransaction().getMetadata().getDownPayment();
                        String note = detaiList.get(position).getTransaction().getMetadata().getNote();
                        String postal_fee = detaiList.get(position).getTransaction().getMetadata().getPostalFee();
                        String address_label = detaiList.get(position).getTransaction().getMetadata().getShipping().getSend().getAddressLabel();
                        String receiver = detaiList.get(position).getTransaction().getMetadata().getShipping().getSend().getReceiver();
                        String mobile = detaiList.get(position).getTransaction().getMetadata().getShipping().getSend().getMobile();
                        String city = detaiList.get(position).getTransaction().getMetadata().getShipping().getSend().getNameCity();
                        String district = detaiList.get(position).getTransaction().getMetadata().getShipping().getSend().getNameDistrict();
                        String address = detaiList.get(position).getTransaction().getMetadata().getShipping().getSend().getAddress();
                        String payment_method = detaiList.get(position).getTransaction().getMetadata().getPaymentMethod();
                        String installment = detaiList.get(position).getTransaction().getMetadata().getInstallment().getJsonMember0();
                        String total_pembayaran = detaiList.get(position).getTransaction().getMetadata().getTotalPembayaran();
                        String courier = detaiList.get(position).getTransaction().getMetadata().getCourier();
                        String name_district= detaiList.get(position).getTransaction().getMetadata().getShipping().getSend().getNameDistrict();
                        String name_city = detaiList.get(position).getTransaction().getMetadata().getShipping().getSend().getNameCity();
                        String postal_code = detaiList.get(position).getTransaction().getMetadata().getShipping().getSend().getPostalCode();

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
