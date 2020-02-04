package com.application.kreditimpian.TransactionProcess;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.application.kreditimpian.Adapter.AdapterTenorSelected;
import com.application.kreditimpian.Api.SharedPrefManager;
import com.application.kreditimpian.Api.api_v2.BaseApiService;
import com.application.kreditimpian.Api.api_v2.UtilsApi;
import com.application.kreditimpian.Constan.ConstanTransaction;
import com.application.kreditimpian.Model.ModelSelectedTenor.DataItem;
import com.application.kreditimpian.Model.ModelSelectedTenor.ResponseSelectedTenor;
import com.application.kreditimpian.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TransactionSelectTenor extends AppCompatActivity {

    @BindView(R.id.txtid_company)
    TextView txtid_company;
    @BindView(R.id.txtid_product)
    TextView txtid_product;
    @BindView(R.id.txtid_product_category)
    TextView txtid_product_category;

    List<DataItem> dataItemList;
    AdapterTenorSelected adapterTenorSelected;

    RecyclerView ListSelectedTenor;
    SharedPrefManager sharedPrefManager;
    BaseApiService mApiService;

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
        /*Intent membawa string untuk detail*/
        Intent intent = getIntent();
        String id_product = intent.getStringExtra(ConstanTransaction.KEY_ID_PRODUCT);
        String id_product_category = intent.getStringExtra(ConstanTransaction.KEY_ID_PRODUCT_CATEGORY_TRANSACTION);

        txtid_product.setText(id_product);
        txtid_product_category.setText(id_product_category);

        adapterTenorSelected = new AdapterTenorSelected(TransactionSelectTenor.this, dataItemList);
        GridLayoutManager mLayoutManager = new GridLayoutManager(TransactionSelectTenor.this, 2, GridLayoutManager.VERTICAL, false);
        ListSelectedTenor.setLayoutManager(mLayoutManager);
        ListSelectedTenor.setItemAnimator(new DefaultItemAnimator());

    }

//    private void SelectedTenor(){
//
//        String id_product = txtid_product.getText().toString();
//        String id_product_category = txtid_product_category.getText().toString();
//
//
//
//        mApiService.getTenorSelected(id_product,id_product_category).enqueue(new Callback<ResponseSelectedTenor>() {
//            @Override
//            public void onResponse(Call<ResponseSelectedTenor> call, Response<ResponseSelectedTenor> response) {
//
//                if(response.body().getResponseCode()==200){
//                    final List<DataItem> MitraList = response.body().getData();
//                    ListSelectedTenor.setAdapter(new AdapterTenorSelected(mContext, MitraList));
//                    adapterTenorSelected.notifyDataSetChanged();
//                    ///    Log.d("Data result", MitraList.toString());
//                    ///Log.v("jajal", MitraList.toString());
//                    ////Toast.makeText(TransactionSelectMitra.this, response.body().getData().toString(), Toast.LENGTH_LONG).show();
//
//                }else {
//                    Toast.makeText(TransactionSelectTenor.this, "Gagal Load data mitra", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ResponseSelectedTenor> call, Throwable t) {
//
//            }
//        });
//
//
//    }

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
