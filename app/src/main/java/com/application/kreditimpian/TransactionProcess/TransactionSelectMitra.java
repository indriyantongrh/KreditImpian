package com.application.kreditimpian.TransactionProcess;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.application.kreditimpian.Adapter.AdapterMitraSelected;
import com.application.kreditimpian.Akun.AlamatPengiriman;
import com.application.kreditimpian.Api.SharedPrefManager;
import com.application.kreditimpian.Api.api_v2.BaseApiService;
import com.application.kreditimpian.Api.api_v2.UtilsApi;
import com.application.kreditimpian.Constan.ConstanTransaction;
import com.application.kreditimpian.Model.ModelMitra;
import com.application.kreditimpian.Model.ModelMitraSelected.DataItem;
import com.application.kreditimpian.Model.ModelMitraSelected.ResponseMitraSelected;
import com.application.kreditimpian.R;
import com.bumptech.glide.Glide;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TransactionSelectMitra extends AppCompatActivity {
    ProgressDialog pDialog;
    ProgressDialog progressBar;
    @BindView(R.id.rvMitra)
    RecyclerView rvMitra;
    @BindView(R.id.txt_name_product)
    TextView txt_name_product;
    @BindView(R.id.txt_id_transaction)
    TextView txt_id_transaction;
    @BindView(R.id.txt_price_capital)
    TextView txt_price_capital;
    @BindView(R.id.txt_price_sale)
    TextView txt_price_sale;
    @BindView(R.id.txt_id)
    TextView txt_id;
    @BindView(R.id.txt_reference_id)
    TextView txt_reference_id;
    @BindView(R.id.txt_id_product_category)
    TextView txt_id_product_category;
    @BindView(R.id.txt_number)
    TextView txt_number;
    @BindView(R.id.txt_image)
    TextView txt_image;
    @BindView(R.id.txt_status)
    TextView txt_status;
    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.btnSelanjutnya)
    Button btnSelanjutnya;

    // String id_product_category;

    DataItem reqDataItem;
    SharedPrefManager sharedPrefManager;
    BaseApiService mApiService;
    private List<DataItem> dataItemList = new ArrayList<>();


    AdapterMitraSelected adapterMitraSelected;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_select_mitra);

        setActionBarTitle("Pilih mitra yang kamu mau");
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);// set drawable icon
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ButterKnife.bind(this);
        mContext = TransactionSelectMitra.this;
        sharedPrefManager = new SharedPrefManager(TransactionSelectMitra.this);
        mApiService = UtilsApi.getAPIService();


        /*get Selected Mitra*/


        Intent intent = getIntent();
        String id_product = intent.getStringExtra(ConstanTransaction.KEY_ID_PRODUCT);
        String id_product_category = intent.getStringExtra(ConstanTransaction.KEY_ID_PRODUCT_CATEGORY_TRANSACTION);
        String nameProduct = intent.getStringExtra(ConstanTransaction.KEY_NAME_PRODUCT_TRANSACTION);
        String reference_id = intent.getStringExtra(ConstanTransaction.KEY_REFERENCE_ID);
        String id_transaction = intent.getStringExtra(ConstanTransaction.KEY_ID_TRANSACTION);
        String nomor_invoice = intent.getStringExtra(ConstanTransaction.KEY_NUMBER);
        String imageProduct = intent.getStringExtra(ConstanTransaction.KEY_FILENAME_TRANSACTION);
        String status = intent.getStringExtra(ConstanTransaction.KEY_STATUS);
        Log.d("ini id kategorimu ", ConstanTransaction.KEY_ID_PRODUCT_CATEGORY_TRANSACTION);

        ///convert String to Rupiah Curerncy
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        int price_capital = (Integer.parseInt(intent.getStringExtra(ConstanTransaction.KEY_PRICE_CAPITAL_TRANSACTION)));
        int price_sale = (Integer.parseInt(intent.getStringExtra(ConstanTransaction.KEY_PRICE_SALE_TRANSACTION)));

        if (price_capital == price_sale) {
            txt_price_capital.setVisibility(View.GONE);
        } else {
            txt_price_capital.setVisibility(View.VISIBLE);
        }

        txt_id.setText(id_product);
        txt_id_product_category.setText(id_product_category);
        Toast.makeText(TransactionSelectMitra.this, "Kategori product anda " + id_product_category, Toast.LENGTH_LONG).show();
        txt_reference_id.setText(reference_id);
        txt_number.setText(nomor_invoice);
        txt_status.setText(status);
        txt_id_transaction.setText(id_transaction);
        txt_name_product.setText(nameProduct);
        txt_price_capital.setText(formatRupiah.format(price_capital));
        txt_price_sale.setText(formatRupiah.format(price_sale));

        Glide.with(TransactionSelectMitra.this)
                .load(imageProduct)
                .placeholder(R.drawable.no_image)
                .error(R.drawable.no_image)
                .into(image);

        adapterMitraSelected = new AdapterMitraSelected(TransactionSelectMitra.this);
        rvMitra.setAdapter(adapterMitraSelected);
        GridLayoutManager mLayoutManager = new GridLayoutManager(TransactionSelectMitra.this, 2, GridLayoutManager.VERTICAL, false);
        rvMitra.setLayoutManager(mLayoutManager);
        rvMitra.setItemAnimator(new DefaultItemAnimator());
        selectedMitra();


        btnSelanjutnya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NextTenor();
//                Intent intent = new Intent(TransactionSelectMitra.this, TransactionSelectTenor.class);
//                startActivity(intent);

            }
        });

    }

    private void NextTenor() {
        boolean check = false;
        StringBuilder mitraStringBuilder = new StringBuilder();
        List<DataItem> dataItemList = adapterMitraSelected.getDataItemList();
        DataItem dataItem = new DataItem();
        ///ModelMitra modelMitra = new ModelMitra();
        for (int i = 0; i < dataItemList.size(); i++) {
            dataItem = dataItemList.get(i);
            if (dataItem.isCheck()) {
                mitraStringBuilder.append(dataItem.getIdCompany()).append("|");
            }
        }
        Log.v("jajal2", mitraStringBuilder+ " bismillah");
        for (int i = 0; i < dataItemList.size(); i++) {
            if (dataItem.isCheck()) {
                check = true;
                break;
            }
        }


//        StringBuilder mitraStringBuilder = new StringBuilder();
//        List<DataItem> dataItems = adapterMitraSelected.getDataItemList();
//        DataItem dataItem = new DataItem();
//        for (int i = 0; i < dataItems.size(); i++) {
//            dataItem = dataItems.get(i);
//            if (dataItem.isCheck()) {
//                mitraStringBuilder.append(dataItem.getIdCompany()).append("|");
//            }
//        }
//        Log.v("jajal" ,  dataItems+"a");
//        for (int i = 0; i < dataItems.size(); i++) {
//            if (dataItem.isCheck()) {
//                check = true;
//                break;
//            }
//        }

    }


    private void selectedMitra() {
        //// progressBar = ProgressDialog.show(TransactionSelectMitra.this, null, "Harap Tunggu...", true, false);
//        HashMap<String, String> params = new HashMap<>();
//        params.put("id_product_category", txt_id_product_category.getText().toString());

        String id_product_category = txt_id_product_category.getText().toString();

        mApiService.getMitraSelected(id_product_category).enqueue(new Callback<ResponseMitraSelected>() {
            @Override
            public void onResponse(Call<ResponseMitraSelected> call, Response<ResponseMitraSelected> response) {
                ///pDialog.dismiss();

                if (response.body().getResponseCode() == 200) {
                    final List<DataItem> dataItemList = response.body().getData();
                    adapterMitraSelected.setDataItemList(dataItemList);
                    adapterMitraSelected.notifyDataSetChanged();


                    ///    Log.d("Data result", MitraList.toString());
                    ///Log.v("jajal", MitraList.toString());
                    ////Toast.makeText(TransactionSelectMitra.this, response.body().getData().toString(), Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(TransactionSelectMitra.this, "Gagal Load data mitra", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ResponseMitraSelected> call, Throwable t) {

            }
        });

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
}
