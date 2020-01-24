package com.application.kreditimpian.Akun;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.application.kreditimpian.Api.api_v2.BaseApiService;
import com.application.kreditimpian.Api.api_v2.UtilsApi;
import com.application.kreditimpian.Model.ModelGeodirectories.ResponseGeodirectories;
import com.application.kreditimpian.Model.ModelGeodirectories.ResultItem;
import com.application.kreditimpian.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlamatPengiriman extends AppCompatActivity {

    ImageButton btnback;
    BaseApiService mApiService;
    ProgressDialog loading;
    Spinner spinnerkecamatan_pengiriman, spinnerkota_pengiriman;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alamat_pengiriman);
        setActionBarTitle("Alamat Pengiriman");
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);// set drawable icon
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mApiService = UtilsApi.getAPIService();
        spinnerkecamatan_pengiriman = findViewById(R.id.spinnerkecamatan_pengiriman);
        spinnerkota_pengiriman = findViewById(R.id.spinnerkota_pengiriman);

        getGeoDistrict();
        getGeoCity();


    }


    private void getGeoCity(){

        mApiService.getGeoCity().enqueue(new Callback<ResponseGeodirectories>() {
            @Override
            public void onResponse(Call<ResponseGeodirectories> call, Response<ResponseGeodirectories> response) {
                if(response.body() !=null){
                    List<ResultItem> getCity = response.body().getResult();
                    List<String> listSpinner = new ArrayList<String>();
                    for (int i = 0; i < getCity.size(); i++){
                        listSpinner.add(getCity.get(i).getName());
                    }
                    // Set hasil result json ke dalam adapter spinner
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(AlamatPengiriman.this,
                            android.R.layout.simple_spinner_item, listSpinner);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerkota_pengiriman.setAdapter(adapter);
                } else {
                    loading.dismiss();
                    Toast.makeText(AlamatPengiriman.this, "Gagal mengambil data dosen", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseGeodirectories> call, Throwable t) {

            }
        });


    }

    private void getGeoDistrict(){

        mApiService.getGeoDistrict().enqueue(new Callback<ResponseGeodirectories>() {
            @Override
            public void onResponse(Call<ResponseGeodirectories> call, Response<ResponseGeodirectories> response) {
                if(response.body() !=null){
                    List<com.application.kreditimpian.Model.ModelGeodirectories.ResultItem> getCity = response.body().getResult();
                    List<String> listSpinner = new ArrayList<String>();
                    for (int i = 0; i < getCity.size(); i++){
                        listSpinner.add(getCity.get(i).getName());
                    }
                    // Set hasil result json ke dalam adapter spinner
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(AlamatPengiriman.this,
                            android.R.layout.simple_spinner_item, listSpinner);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerkecamatan_pengiriman.setAdapter(adapter);
                } else {
                    loading.dismiss();
                    Toast.makeText(AlamatPengiriman.this, "Gagal mengambil data dosen", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseGeodirectories> call, Throwable t) {

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

    @Override
    public void onBackPressed() {
        finish();
    }

}
