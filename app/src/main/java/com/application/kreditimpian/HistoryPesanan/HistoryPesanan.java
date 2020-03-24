package com.application.kreditimpian.HistoryPesanan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.application.kreditimpian.Adapter.AdapterHistoryTransaction;
import com.application.kreditimpian.Akun.DataDiri;
import com.application.kreditimpian.Api.SharedPrefManager;
import com.application.kreditimpian.Api.api_v2.BaseApiService;
import com.application.kreditimpian.Api.api_v2.UtilsApi;
import com.application.kreditimpian.Constan.ConstanHistoryPesanan;
import com.application.kreditimpian.Constan.Constans;
import com.application.kreditimpian.Marketplace.FragSemuaKategori.RecyclerItemClickListener;
import com.application.kreditimpian.Model.ModelHistoryPesanan.ResponseHistoryPesanan;

import com.application.kreditimpian.Model.ModelNewHistoryPesanan.DataItem;
import com.application.kreditimpian.Model.ModelNewHistoryPesanan.ResponseNewHistoryPesanan;
import com.application.kreditimpian.R;
import com.application.kreditimpian.TransactionProcess.Cart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoryPesanan extends AppCompatActivity {

    SharedPrefManager sharedPrefManager;

    @BindView(R.id.listHistoryPesanan)
    RecyclerView listHistoryPesanan;
    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefresh;
    ProgressDialog progressBar;
    @BindView(R.id.empty)
    ImageView empty;

    BaseApiService mApiService;
    Context mContext;

    List<DataItem> resultItemList = new ArrayList<>();
    AdapterHistoryTransaction adapterHistoryTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_pesanan);
        setActionBarTitle("History Pesanan");
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);// set drawable icon
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        sharedPrefManager = new SharedPrefManager(HistoryPesanan.this);
        swipeRefresh =findViewById(R.id.swipeRefresh);
        swipeRefresh.setColorScheme(android.R.color.holo_orange_dark,
                android.R.color.holo_orange_light,
                android.R.color.holo_orange_dark,
                android.R.color.holo_orange_light);

        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getHistoryTransaction();
                swipeRefresh.setRefreshing(false);
            }
        });


        ButterKnife.bind(this);
        mContext = HistoryPesanan.this;
        mApiService = UtilsApi.getAPIService();
        adapterHistoryTransaction = new AdapterHistoryTransaction(HistoryPesanan.this, resultItemList);
        //RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        GridLayoutManager mLayoutManager = new GridLayoutManager(HistoryPesanan.this, 1, GridLayoutManager.VERTICAL, false);
        listHistoryPesanan.setLayoutManager(mLayoutManager);
        listHistoryPesanan.setItemAnimator(new DefaultItemAnimator());

        getHistoryTransaction();
    }

    private void getHistoryTransaction(){

        progressBar = ProgressDialog.show(HistoryPesanan.this, null, "Loading...", true, false);

        HashMap<String, String> params = new HashMap<>();
        params.put("id_member", sharedPrefManager.getSpIdMember());

        mApiService.getHistoryTransaction(params).enqueue(new Callback<ResponseNewHistoryPesanan>() {
            @Override
            public void onResponse(Call<ResponseNewHistoryPesanan> call, Response<ResponseNewHistoryPesanan> response) {
                if (response.isSuccessful()){
                    ///progressBar.dismiss();
                    if (response.body().getResponseCode()==200) {
                        swipeRefresh.setRefreshing(false);
                        progressBar.dismiss();
                        final List<DataItem> HistoryTransaction = response.body().getData();

                        listHistoryPesanan.setAdapter(new AdapterHistoryTransaction(mContext, HistoryTransaction));
                        adapterHistoryTransaction.notifyDataSetChanged();
                        empty.setVisibility(View.GONE);
                        initDataIntent(HistoryTransaction);
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
            public void onFailure(Call<ResponseNewHistoryPesanan> call, Throwable t) {
                progressBar.dismiss();
                Toast.makeText(mContext,"Koneksi anda bermasalah", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void initDataIntent(final List<DataItem> detaiList){
        listHistoryPesanan.addOnItemTouchListener(
                new RecyclerItemClickListener(mContext, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {

                String id =detaiList.get(position).getId();
                String id_transactions = detaiList.get(position).getIdTransaction();
                String status = detaiList.get(position).getStatus();
                String timestamp = detaiList.get(position).getTimestamp();
                String expires = detaiList.get(position).getExpires();
                String number = detaiList.get(position).getNumber();
                String id_product = detaiList.get(position).getIdProduct();
                String id_product_category = detaiList.get(position).getIdProductCategory();
                String id_currency = detaiList.get(position).getIdCurrency();
                String name = detaiList.get(position).getName();
                String description = detaiList.get(position).getDescription();
                String sku = detaiList.get(position).getSku();
                String stock = detaiList.get(position).getStock();
                String price_capital = detaiList.get(position).getPriceCapital();
                String price_sale = detaiList.get(position).getPriceSale();
                String discount = detaiList.get(position).getDiscount();
                String condition = detaiList.get(position).getCondition();
                String deliverable = detaiList.get(position).getDeliverable();
                String downloadable = detaiList.get(position).getDownloadable();
                String target_gender = detaiList.get(position).getTargetGender();
                String target_age = detaiList.get(position).getTargetAge();
                String visibility = detaiList.get(position).getVisibility();
                String filename = detaiList.get(position).getFilename();
                String id_merchant = detaiList.get(position).getIdMerchant();
                String name_merchant = detaiList.get(position).getNameMerchant();
                String id_company = detaiList.get(position).getIdCreditor();
                String name_company = detaiList.get(position).getNameCompany();
                String tenor = detaiList.get(position).getTenor();
                String down_payment = detaiList.get(position).getDownPayment();
                String note = detaiList.get(position).getNote();
                String id_creditor = detaiList.get(position).getIdCreditor();
                String postal_fee = detaiList.get(position).getPostalFee();
                String address_label = detaiList.get(position).getShipping().getSend().getAddressLabel();
                String receiver = detaiList.get(position).getShipping().getSend().getReceiver();
                String mobile = detaiList.get(position).getShipping().getSend().getMobile();
                String city = detaiList.get(position).getShipping().getSend().getCity();
                String district = detaiList.get(position).getShipping().getSend().getDistrict();
                String address = detaiList.get(position).getShipping().getSend().getAddress();
                String payment_method = detaiList.get(position).getPaymentMethod();
                String installment = detaiList.get(position).getInstallment().getJsonMember0();
                String total_pembayaran = detaiList.get(position).getTotalPembayaran();
                String courier = detaiList.get(position).getCourier();
                String name_city = detaiList.get(position).getNameCity();
                String name_district = detaiList.get(position).getNameDistrict();
                String postal_code = detaiList.get(position).getShipping().getSend().getPostalCode();

                Intent detailHistoryPesanan = new Intent(mContext, DetailHistoryPesanan.class);
                detailHistoryPesanan.putExtra(ConstanHistoryPesanan.KEY_ID, id);
                detailHistoryPesanan.putExtra(ConstanHistoryPesanan.KEY_ID_TRANSACTION, id_transactions);
                detailHistoryPesanan.putExtra(ConstanHistoryPesanan.KEY_STATUS, status);
                detailHistoryPesanan.putExtra(ConstanHistoryPesanan.KEY_TIMESTAMP, timestamp);
                detailHistoryPesanan.putExtra(ConstanHistoryPesanan.KEY_EXPIRES, expires);
                detailHistoryPesanan.putExtra(ConstanHistoryPesanan.KEY_NUMBER, number);
                detailHistoryPesanan.putExtra(ConstanHistoryPesanan.KEY_ID_PRODUCT, id_product);
                detailHistoryPesanan.putExtra(ConstanHistoryPesanan.KEY_ID_PRODUCT_CATEGORY, id_product_category);
                detailHistoryPesanan.putExtra(ConstanHistoryPesanan.KEY_CURRENCY, id_currency);
                detailHistoryPesanan.putExtra(ConstanHistoryPesanan.KEY_NAME_PRODUCT, name);
                detailHistoryPesanan.putExtra(ConstanHistoryPesanan.KEY_DESCRIPTION, description);
                detailHistoryPesanan.putExtra(ConstanHistoryPesanan.KEY_SKU, sku);
                detailHistoryPesanan.putExtra(ConstanHistoryPesanan.KEY_STOCK, stock);
                detailHistoryPesanan.putExtra(ConstanHistoryPesanan.KEY_PRICE_CAPITAL, price_capital);
                detailHistoryPesanan.putExtra(ConstanHistoryPesanan.KEY_PRICE_SALE, price_sale);
                detailHistoryPesanan.putExtra(ConstanHistoryPesanan.KEY_DISCOUNT, discount);
                detailHistoryPesanan.putExtra(ConstanHistoryPesanan.KEY_CONDITION, condition);
                detailHistoryPesanan.putExtra(ConstanHistoryPesanan.KEY_DELIVERABLE, deliverable);
                detailHistoryPesanan.putExtra(ConstanHistoryPesanan.KEY_DOWNLOADABLE, downloadable);
                detailHistoryPesanan.putExtra(ConstanHistoryPesanan.KEY_TARGET_GENDER, target_gender);
                detailHistoryPesanan.putExtra(ConstanHistoryPesanan.KEY_TARGET_AGE, target_age);
                detailHistoryPesanan.putExtra(ConstanHistoryPesanan.KEY_VISIBILITY, visibility);
                detailHistoryPesanan.putExtra(ConstanHistoryPesanan.KEY_FILENAME, filename);
                detailHistoryPesanan.putExtra(ConstanHistoryPesanan.KEY_ID_MERCHANT, id_merchant);
                detailHistoryPesanan.putExtra(ConstanHistoryPesanan.KEY_NAME_MERCHANT, name_merchant);
                detailHistoryPesanan.putExtra(ConstanHistoryPesanan.KEY_ID_COMPANY, id_company);
                detailHistoryPesanan.putExtra(ConstanHistoryPesanan.KEY_NAME_COMPANY, name_company);
                detailHistoryPesanan.putExtra(ConstanHistoryPesanan.KEY_TENOR, tenor);
                detailHistoryPesanan.putExtra(ConstanHistoryPesanan.KEY_DOWN_PAYMENT, down_payment);
                detailHistoryPesanan.putExtra(ConstanHistoryPesanan.KEY_NOTE, note);
                detailHistoryPesanan.putExtra(ConstanHistoryPesanan.KEY_ID_CREDITOR, id_creditor);
                detailHistoryPesanan.putExtra(ConstanHistoryPesanan.KEY_POSTAL_FEE, postal_fee);
                detailHistoryPesanan.putExtra(ConstanHistoryPesanan.KEY_ADDRESS_LABEL, address_label);
                detailHistoryPesanan.putExtra(ConstanHistoryPesanan.KEY_RECEIVER, receiver);
                detailHistoryPesanan.putExtra(ConstanHistoryPesanan.KEY_MOBILE, mobile);
                detailHistoryPesanan.putExtra(ConstanHistoryPesanan.KEY_CITY, city);
                detailHistoryPesanan.putExtra(ConstanHistoryPesanan.KEY_DISTRICT, district);
                detailHistoryPesanan.putExtra(ConstanHistoryPesanan.KEY_ADDRESS, address);
                detailHistoryPesanan.putExtra(ConstanHistoryPesanan.KEY_PAYMENT_METHOD, payment_method);
                detailHistoryPesanan.putExtra(ConstanHistoryPesanan.KEY_INSTALLMENT, installment);
                detailHistoryPesanan.putExtra(ConstanHistoryPesanan.KEY_TOTAL_PEMBAYARAN, total_pembayaran);
                detailHistoryPesanan.putExtra(ConstanHistoryPesanan.KEY_COURIER, courier);
                detailHistoryPesanan.putExtra(ConstanHistoryPesanan.KEY_NAME_CITY, name_city);
                detailHistoryPesanan.putExtra(ConstanHistoryPesanan.KEY_NAME_DISTRICT, name_district);
                detailHistoryPesanan.putExtra(ConstanHistoryPesanan.KEY_POSTAL_CODE, postal_code);

                startActivity(detailHistoryPesanan);
            }
        }));

    }

    private void setActionBarTitle(String title) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
