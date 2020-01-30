package com.application.kreditimpian.Akun;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.application.kreditimpian.Api.SharedPrefManager;
import com.application.kreditimpian.Api.api_v2.BaseApiService;
import com.application.kreditimpian.Api.api_v2.UtilsApi;
import com.application.kreditimpian.Constan.ConstansAddress;
import com.application.kreditimpian.Model.ModelAddress.ResponseAddress;
import com.application.kreditimpian.R;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailAlamat extends AppCompatActivity {

    ProgressDialog pDialog;
    ImageButton btnback;
    SharedPrefManager sharedPrefManager;
    BaseApiService mApiService;
    ProgressDialog loading;
    Switch SwitchAddress;
    Spinner spinnerkecamatan_pengiriman, spinnerkota_pengiriman;
    EditText txtnamaalamat,txtnamapenerima,txtnomorhandphone,txtkodepospengiriman,txtalamatpengririman;
    Button btnupdate, btndelete;
    String id, nameCity, id_member,id_geodirectory,address_name,phone,receiver,address,postal_code,district;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_alamat);

        setActionBarTitle("Detail Alamat");
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);// set drawable icon
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mApiService = UtilsApi.getAPIService();
        sharedPrefManager = new SharedPrefManager(DetailAlamat.this);
        spinnerkecamatan_pengiriman = findViewById(R.id.spinnerkecamatan_pengiriman);
        spinnerkota_pengiriman = findViewById(R.id.spinnerkota_pengiriman);


        SwitchAddress = findViewById(R.id.SwitchAddress);
        txtnamaalamat = findViewById(R.id.txtnamaalamat);
        txtnamapenerima = findViewById(R.id.txtnamapenerima);
        txtnomorhandphone = findViewById(R.id.txtnomorhandphone);
        txtkodepospengiriman = findViewById(R.id.txtkodepospengiriman);
        txtalamatpengririman = findViewById(R.id.txtalamatpengririman);
        btnupdate = findViewById(R.id.btnupdate);
        btndelete = findViewById(R.id.btndelete);




        /*Get Detail Addreses*/
        Intent intent = getIntent();
        id = intent.getStringExtra(ConstansAddress.KEY_ID);
        id_member = intent.getStringExtra(ConstansAddress.KEY_ID_MEMBER);
        address_name = intent.getStringExtra(ConstansAddress.KEY_ADDRESS_NAME);
        receiver = intent.getStringExtra(ConstansAddress.KEY_RECEIVER);
        phone = intent.getStringExtra(ConstansAddress.KEY_PHONE);
        address = intent.getStringExtra(ConstansAddress.KEY_ADDRESS);
        postal_code = intent.getStringExtra(ConstansAddress.KEY_POSTAL_CODE);
        id_geodirectory = intent.getStringExtra(ConstansAddress.KEY_ID_GEODIRECTORY);
        district = intent.getStringExtra(ConstansAddress.KEY_DISTRICT);

        Toast.makeText(DetailAlamat.this, "id anda" +id , Toast.LENGTH_LONG).show();

        txtnamapenerima.setText(receiver);
        txtnamaalamat.setText(address_name);
        txtnomorhandphone.setText(phone);
        txtalamatpengririman.setText(address);
        txtkodepospengiriman.setText(postal_code);

        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeleteAddreses();
            }
        });

        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateAddreses();
            }
        });

    }

    private void UpdateAddreses(){
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        pDialog.setMessage("Memberbarui data Alamat");
        pDialog.show();

        HashMap<String, String> params = new HashMap<>();
        //params.put("id_member",sharedPrefManager.getSpIdMember() );
        /// params.put("avatar", getStringImage(decoded_3));
        params.put("address_name", txtnamaalamat.getText().toString());
        params.put("phone", txtnomorhandphone.getText().toString());
        params.put("receiver", txtnamapenerima.getText().toString());
        params.put("id_geodirectory", "56");
        params.put("district", "5503");
        params.put("postal_code", txtkodepospengiriman.getText().toString());
        params.put("address", txtalamatpengririman.getText().toString());
        /*Method post Main addresses from switch button*/
        String AddressMain;
        if (SwitchAddress.isChecked())
            AddressMain = SwitchAddress.getTextOn().toString();
        else
            AddressMain = SwitchAddress.getTextOff().toString();
        params.put("main_address", AddressMain.trim());

        mApiService.updateAddreses(id, params).enqueue(new Callback<ResponseAddress>() {
            @Override
            public void onResponse(Call<ResponseAddress> call, Response<ResponseAddress> response) {
                if(response.body().getResponseCode() == 200){
                    Toast.makeText(DetailAlamat.this, response.body().getMessage() , Toast.LENGTH_LONG).show();
                    finish();
                }else {
                    Toast.makeText(DetailAlamat.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseAddress> call, Throwable t) {

            }
        });

    }

    private void DeleteAddreses(){

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        pDialog.setMessage("Menghapus data Alamat");
        pDialog.show();

        mApiService.deleteAddreses(id, sharedPrefManager.getSpIdMember()).enqueue(new Callback<ResponseAddress>() {
            @Override
            public void onResponse(Call<ResponseAddress> call, Response<ResponseAddress> response) {
                if(response.body().getResponseCode() == 200){
                    Toast.makeText(DetailAlamat.this, response.body().getMessage() , Toast.LENGTH_LONG).show();
                    finish();
                }else {
                    Toast.makeText(DetailAlamat.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseAddress> call, Throwable t) {
                Toast.makeText(DetailAlamat.this, "Koneksi anda bermasalah, coba ulangi lagi", Toast.LENGTH_SHORT).show();
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
