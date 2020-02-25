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
///import com.application.kreditimpian.Model.ModelHistoryTransaction.DataItem;
import com.application.kreditimpian.Model.ModelHistoryPesanan.DataItem;
//import com.application.kreditimpian.Model.ModelHistoryTransaction.ResponseHistoryTransaction;
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

        progressBar = ProgressDialog.show(HistoryPesanan.this, null, "Harap Tunggu...", true, false);

        HashMap<String, String> params = new HashMap<>();
        params.put("id_member", sharedPrefManager.getSpIdMember());

        mApiService.getHistoryTransaction(params).enqueue(new Callback<ResponseHistoryPesanan>() {
            @Override
            public void onResponse(Call<ResponseHistoryPesanan> call, Response<ResponseHistoryPesanan> response) {
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
                    Toast.makeText(mContext,    "Gagal Refresh", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseHistoryPesanan> call, Throwable t) {
                progressBar.dismiss();
                Toast.makeText(mContext,    "Koneksi anda bermasalah", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void initDataIntent(List<DataItem> historyTransaction) {
        listHistoryPesanan.addOnItemTouchListener(new RecyclerItemClickListener(mContext, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                String id =historyTransaction.get(position).getId();
                String id_transactions = historyTransaction.get(position).getIdTransactions();
                String status = historyTransaction.get(position).getStatus();
                String timestamp = historyTransaction.get(position).getTimestamp();
                String expires = historyTransaction.get(position).getExpires();
                String number = historyTransaction.get(position).getNumber();
                String id_product = historyTransaction.get(position).getIdProduct();
                String id_product_category = historyTransaction.get(position).getIdProductCategory();
                String id_currency = historyTransaction.get(position).getIdCurrency();
                String name = historyTransaction.get(position).getName();
                String description = historyTransaction.get(position).getDescription();
                String sku = historyTransaction.get(position).getSku();
                String stock = historyTransaction.get(position).getStock();
                String price_capital = historyTransaction.get(position).getPriceCapital();
                String price_sale = historyTransaction.get(position).getPriceSale();
                String discount = historyTransaction.get(position).getDiscount();
                String condition = historyTransaction.get(position).getCondition();
                String deliverable = historyTransaction.get(position).getDeliverable();
                String downloadable = historyTransaction.get(position).getDownloadable();
                String target_gender = historyTransaction.get(position).getTargetGender();
                String target_age = historyTransaction.get(position).getTargetAge();
                String visibility = historyTransaction.get(position).getVisibility();
                String filename = historyTransaction.get(position).getFilename();
                String id_merchant = historyTransaction.get(position).getIdMerchant();
                String name_merchant = historyTransaction.get(position).getNameMerchant();
                String id_company = historyTransaction.get(position).getIdCompany();
                String name_company = historyTransaction.get(position).getNameCompany();
                String tenor = historyTransaction.get(position).getTenor();
                String down_payment = historyTransaction.get(position).getDownPayment();
                String note = historyTransaction.get(position).getNote();
                String id_creditor = historyTransaction.get(position).getIdCreditor();
                String postal_fee = historyTransaction.get(position).getPostalFee();
                String address_label = historyTransaction.get(position).getShipping().getSend().getAddressLabel();
                String receiver = historyTransaction.get(position).getShipping().getSend().getReceiver();
                String mobile = historyTransaction.get(position).getShipping().getSend().getMobile();
                String city = historyTransaction.get(position).getShipping().getSend().getCity();
                String district = historyTransaction.get(position).getShipping().getSend().getDistrict();
                String address = historyTransaction.get(position).getShipping().getSend().getAddress();
                String payment_method = historyTransaction.get(position).getPaymentMethod();
                String installment = historyTransaction.get(position).getInstallment();
                String total_pembayaran = historyTransaction.get(position).getTotalPembayaran();
                String courier = historyTransaction.get(position).getCourier();

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
