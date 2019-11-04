package com.application.kreditimpian.Mitra;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.Toast;

import com.application.kreditimpian.Adapter.AdapterMitra;
import com.application.kreditimpian.Api.JSONResponse;
import com.application.kreditimpian.Api.RequestInterface;
import com.application.kreditimpian.BuildConfig;
import com.application.kreditimpian.Model.ModelMitra;
import com.application.kreditimpian.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListMitra extends AppCompatActivity {

    RecyclerView rv_mitra;
    ConnectivityManager conMgr;
    AdapterMitra adapterMitra;
    private ArrayList<ModelMitra> mArrayList;

    public static final String BASE_URL = BuildConfig.BASE_URL;

    Dialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_mitra);

        conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        {
            if (conMgr.getActiveNetworkInfo() != null
                    && conMgr.getActiveNetworkInfo().isAvailable()
                    && conMgr.getActiveNetworkInfo().isConnected()) {
            } else {
                Toast.makeText(getApplicationContext(), "No Internet Connection",
                        Toast.LENGTH_LONG).show();
            }
        }


        pDialog = new Dialog(ListMitra.this);
        pDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        pDialog.setCanceledOnTouchOutside(false);
        ///pDialog.setContentView(R.layout.custom_pdialog);
        pDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

      //  initViews();
       /// loadJSON();

    }


   /* private void initViews(){
        rv_mitra = findViewById(R.id.rv_mitra);
        rv_mitra.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rv_mitra.setLayoutManager(layoutManager);
    }
    private void loadJSON(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient().newBuilder()
                        .connectTimeout(30, TimeUnit.SECONDS)
                        .readTimeout(30, TimeUnit.SECONDS)
                        .writeTimeout(30, TimeUnit.SECONDS)
                        .build())
                .build();
        pDialog.show();
        RequestInterface request = retrofit.create(RequestInterface.class);
        Call<JSONResponse> call = request.getMitra();
        call.enqueue(new Callback<JSONResponse>() {
            @Override
            public void onResponse(Call<JSONResponse> call, Response<JSONResponse> response) {
                try {
                    if (response.isSuccessful()) {
                        JSONResponse jsonResponse = response.body();
                        pDialog.hide();
                        mArrayList = new ArrayList<>(Arrays.asList(jsonResponse.getMitra()));
                        if(!mArrayList.isEmpty()){
                            adapterMitra = new AdapterMitra(getApplicationContext(),mArrayList);
                            rv_mitra.setAdapter(adapterMitra);
                        }
                    } else {
                        pDialog.hide();
                        Toast.makeText(ListMitra.this, "Periksa koneksi internet anda", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(Call<JSONResponse> call, Throwable t) {
                pDialog.hide();
                Log.d("Error",t.getMessage());
            }
        });
    }*/

}
