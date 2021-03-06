package com.application.kreditimpian.TransactionProcess;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.application.kreditimpian.Adapter.AdapterSelectedTenor;
import com.application.kreditimpian.Api.SharedPrefManager;
import com.application.kreditimpian.Api.api_v2.BaseApiService;
import com.application.kreditimpian.Api.api_v2.UtilsApi;
import com.application.kreditimpian.Model.ModelCicilan.CompaniesDataItem;
import com.application.kreditimpian.Model.ModelCicilan.Data;
import com.application.kreditimpian.Model.ModelCicilan.DataCicilanItem;
import com.application.kreditimpian.Model.ModelCicilan.ProductMeta;
import com.application.kreditimpian.Model.ModelCicilan.ResponseCicilan;
import com.application.kreditimpian.R;
import com.google.gson.JsonObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TransactionSelectTenor extends AppCompatActivity {

    @BindView(R.id.txtlist_id_company)
    TextView txtlist_id_company;
    @BindView(R.id.txtid_product)
    TextView txtid_product;
    @BindView(R.id.txtid_product_category)
    TextView txtid_product_category;
    @BindView(R.id.txtnumber)
    TextView txtnumber;
    @BindView(R.id.txtid_member)
    TextView txtid_member;
    ProgressDialog progressBar;
    @BindView(R.id.ListSelectedTenor)
    RecyclerView ListSelectedTenor;
    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefresh;


    //    List<DataItem> dataItemList;
//    AdapterTenorSelected adapterTenorSelected;
    List<Data> dataList;
    AdapterSelectedTenor adapterSelectedTenor;

    ////RecyclerView ListSelectedTenor;
    SharedPrefManager sharedPrefManager;
    BaseApiService mApiService;
    public List<ResponseCicilan> data;
    Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_select_tenor);
        setActionBarTitle("Pilih tenor yang cocok");
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);// set drawable icon
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ButterKnife.bind(this);

        sharedPrefManager = new SharedPrefManager(TransactionSelectTenor.this);
        mApiService = UtilsApi.getAPIService();
        ListSelectedTenor.setLayoutManager(new LinearLayoutManager(this));

        if(getIntent().hasExtra("productMeta")) {

            ////String note = getIntent().getStringExtra("note");
           //// Toast.makeText(TransactionSelectTenor.this, ""+note, Toast.LENGTH_LONG).show();

            ArrayList<CompaniesDataItem>  CompaniesDataItem= getIntent().getParcelableArrayListExtra("datalist");
            ProductMeta productMeta = getIntent().getParcelableExtra("productMeta");
//              final List<String> myList = new ArrayList<String>(Arrays.asList(mJsonObject.split(",")));
                AdapterSelectedTenor pilihLeasingAdapter = new AdapterSelectedTenor(TransactionSelectTenor.this, productMeta, CompaniesDataItem);
                ListSelectedTenor.setAdapter(pilihLeasingAdapter);



        }

        }



        private void setActionBarTitle (String title){
            if (getSupportActionBar() != null) {
                getSupportActionBar().setTitle(title);
            }
        }

        @Override
        public boolean onSupportNavigateUp () {
            onBackPressed();
            return true;
        }

}
