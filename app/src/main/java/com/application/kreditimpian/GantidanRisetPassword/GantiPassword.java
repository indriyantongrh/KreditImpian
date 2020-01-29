package com.application.kreditimpian.GantidanRisetPassword;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.application.kreditimpian.Akun.DetailAlamat;
import com.application.kreditimpian.Akun.TambahAlamatPengiriman;
import com.application.kreditimpian.Api.SharedPrefManager;
import com.application.kreditimpian.Api.api_v2.BaseApiService;
import com.application.kreditimpian.Api.api_v2.UtilsApi;
import com.application.kreditimpian.Model.ModelAddress.ResponseAddress;
import com.application.kreditimpian.R;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.text.TextUtils.isEmpty;

public class GantiPassword extends AppCompatActivity {
    SharedPrefManager sharedPrefManager;
    BaseApiService mApiService;
    ImageButton btnback;
    Button btnreset;
    ProgressDialog pDialog;
    String id_sysuser;
    EditText txtpasswordbaru,txtkonfirmasipasswordbaru;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ganti_password);

        mApiService = UtilsApi.getAPIService();
        sharedPrefManager = new SharedPrefManager(GantiPassword.this);
        setActionBarTitle("Ganti Password");
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);// set drawable icon
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        id_sysuser = sharedPrefManager.getSpIdUser();
        Toast.makeText(GantiPassword.this, "Iki nggon mu "+id_sysuser, Toast.LENGTH_LONG).show();

        txtpasswordbaru = findViewById(R.id.txtpasswordbaru);
        txtkonfirmasipasswordbaru = findViewById(R.id.txtkonfirmasipasswordbaru);
        btnreset = findViewById(R.id.btnreset);
        btnreset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String passwordbaru = txtpasswordbaru.getText().toString();
                String ulangipasswordbaru = txtkonfirmasipasswordbaru.getText().toString();

                if (isEmpty(passwordbaru))
                    txtpasswordbaru.setError("Masukan password baru");
                else if (isEmpty(ulangipasswordbaru))
                    txtkonfirmasipasswordbaru.setError("Ulangi password anda");
                else
                ResetPassword();
            }
        });


    }

    private void ResetPassword(){

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        pDialog.setMessage("Proses....");
        pDialog.show();

        HashMap<String, String> params = new HashMap<>();
        ///params.put("id_sysuser",sharedPrefManager.getSpIdUser() );
        /// params.put("avatar", getStringImage(decoded_3));
        params.put("passwordbaru", txtpasswordbaru.getText().toString());
        params.put("ulangipasswordbaru", txtkonfirmasipasswordbaru.getText().toString());

        mApiService.ResetPassword(id_sysuser,params).enqueue(new Callback<ResponseAddress>() {
            @Override
            public void onResponse(Call<ResponseAddress> call, Response<ResponseAddress> response) {
                if (response.body().getResponseCode()==200) {
                    Toast.makeText(GantiPassword.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(GantiPassword.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseAddress> call, Throwable t) {
                Toast.makeText(GantiPassword.this,"Gagal, Koneksi anda bermasalah", Toast.LENGTH_SHORT).show();
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
