package com.application.kreditimpian.HistoryPesanan.TabProductRequest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.application.kreditimpian.Adapter.AdapterSelectedTenor;
import com.application.kreditimpian.Adapter.AdapterSelectedTenorFitur;
import com.application.kreditimpian.Api.SharedPrefManager;
import com.application.kreditimpian.Api.api_v2.BaseApiService;
import com.application.kreditimpian.Api.api_v2.UtilsApi;
import com.application.kreditimpian.Constan.ConstanTransaction;
import com.application.kreditimpian.Model.ModelCicilan.Data;
import com.application.kreditimpian.Model.ModelCicilan.ProductMeta;
import com.application.kreditimpian.Model.ModelCicilanFitur.CompaniesDataItem;
import com.application.kreditimpian.Model.ModelCicilanFitur.ResponseModelCicilanFitur;
import com.application.kreditimpian.R;
import com.application.kreditimpian.TransactionProcess.TransactionSelectTenor;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TransactionSelectTenorFitur extends AppCompatActivity {


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
    public List<ResponseModelCicilanFitur> data;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_select_tenor_fitur);

        setActionBarTitle("Pilih tenor yang cocok");
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);// set drawable icon
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ButterKnife.bind(this);

        sharedPrefManager = new SharedPrefManager(TransactionSelectTenorFitur.this);
        mApiService = UtilsApi.getAPIService();
        ListSelectedTenor.setLayoutManager(new LinearLayoutManager(this));
        Intent intent = getIntent();
        String imageProduct = intent.getStringExtra("image_product");


        if(getIntent().hasExtra("datalist")) {



            ArrayList<CompaniesDataItem> CompaniesDataItem= getIntent().getParcelableArrayListExtra("datalist");
            ///ProductMeta productMeta = getIntent().getParcelableExtra("productMeta");
//              final List<String> myList = new ArrayList<String>(Arrays.asList(mJsonObject.split(",")));
            AdapterSelectedTenorFitur pilihLeasingAdapter = new AdapterSelectedTenorFitur(TransactionSelectTenorFitur.this, CompaniesDataItem);
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
