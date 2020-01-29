package com.application.kreditimpian.Akun;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.application.kreditimpian.Api.SharedPrefManager;
import com.application.kreditimpian.Api.api_v2.BaseApiService;
import com.application.kreditimpian.Api.api_v2.UtilsApi;
import com.application.kreditimpian.Marketplace.FragSemuaKategori.Constans;
import com.application.kreditimpian.Model.ModelAddress.ResponseAddress;
import com.application.kreditimpian.Model.ModelGeodirectories.ResponseGeodirectories;
import com.application.kreditimpian.Model.ModelGeodirectories.ResultItem;
import com.application.kreditimpian.Model.ModelMemberInsert.ResponseMemberInsert;
import com.application.kreditimpian.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.text.TextUtils.isEmpty;

public class TambahAlamatPengiriman extends AppCompatActivity {
    ProgressDialog pDialog;
    ImageButton btnback;
    SharedPrefManager sharedPrefManager;
    BaseApiService mApiService;
    ProgressDialog loading;
    Switch SwitchAddress;
    Spinner spinnerkecamatan_pengiriman, spinnerkota_pengiriman;
    EditText txtnamaalamat,txtnamapenerima,txtnomorhandphone,txtkodepospengiriman,txtalamatpengririman;
    Button btnsimpan_pengiriman;
    String id, nameCity, id_member,id_geodirectory,address_name,phone,receiver,address,postal_code,district;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_alamat_pengiriman);
        setActionBarTitle("Tambah Alamat Pengiriman");
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);// set drawable icon
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mApiService = UtilsApi.getAPIService();
        sharedPrefManager = new SharedPrefManager(TambahAlamatPengiriman.this);
        spinnerkecamatan_pengiriman = findViewById(R.id.spinnerkecamatan_pengiriman);
        spinnerkota_pengiriman = findViewById(R.id.spinnerkota_pengiriman);


        SwitchAddress = findViewById(R.id.SwitchAddress);
        txtnamaalamat = findViewById(R.id.txtnamaalamat);
        txtnamapenerima = findViewById(R.id.txtnamapenerima);
        txtnomorhandphone = findViewById(R.id.txtnomorhandphone);
        txtkodepospengiriman = findViewById(R.id.txtkodepospengiriman);
        txtalamatpengririman = findViewById(R.id.txtalamatpengririman);
        btnsimpan_pengiriman = findViewById(R.id.btnsimpan_pengiriman);


        getGeoDistrict();
        getGeoCity();

//
//        /*Get Detail Addreses*/
//        Intent intent = getIntent();
//        id = intent.getStringExtra(ConstansAddress.KEY_ID);
//        id_member = intent.getStringExtra(ConstansAddress.KEY_ID_MEMBER);
//        address_name = intent.getStringExtra(ConstansAddress.KEY_ADDRESS_NAME);
//        receiver = intent.getStringExtra(ConstansAddress.KEY_RECEIVER);
//        phone = intent.getStringExtra(ConstansAddress.KEY_PHONE);
//        address = intent.getStringExtra(ConstansAddress.KEY_ADDRESS);
//        postal_code = intent.getStringExtra(ConstansAddress.KEY_POSTAL_CODE);
//        id_geodirectory = intent.getStringExtra(ConstansAddress.KEY_ID_GEODIRECTORY);
//        district = intent.getStringExtra(ConstansAddress.KEY_DISTRICT);
//
//        txtnamapenerima.setText(receiver);
//        txtnamaalamat.setText(address_name);
//        txtnomorhandphone.setText(phone);
//        txtalamatpengririman.setText(address);
//        txtkodepospengiriman.setText(postal_code);

        btnsimpan_pengiriman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameaddress = txtnamaalamat.getText().toString();
                String namereceive = txtnamapenerima.getText().toString();
                String phoneaddress = txtnomorhandphone.getText().toString();
                String poscodeaddress = txtkodepospengiriman.getText().toString();
                String address = txtalamatpengririman.getText().toString();

                if (isEmpty(nameaddress))
                    txtnamaalamat.setError("Masukan password baru");
                else if (isEmpty(namereceive))
                    txtnamapenerima.setError("Ulangi password anda");
                else if (isEmpty(phoneaddress))
                    txtnomorhandphone.setError("Ulangi password anda");
                else if (isEmpty(poscodeaddress))
                    txtkodepospengiriman.setError("Ulangi password anda");
                else if (isEmpty(address))
                    txtalamatpengririman.setError("Ulangi password anda");
                else
                ///SwitchAddress();
                insertAddress();
            }
        });

    }

    /*Method Insert Data Alamat*/
    private void insertAddress(){

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        pDialog.setMessage("Menyimpan data Alamat");
        pDialog.show();

        HashMap<String, String> params = new HashMap<>();
        params.put("id_member",sharedPrefManager.getSpIdMember() );
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

        mApiService.InsertAddress(params).enqueue(new Callback<ResponseAddress>() {
            @Override
            public void onResponse(Call<ResponseAddress> call, Response<ResponseAddress> response) {

                pDialog.dismiss();
                if (response.body() !=null) {
                    Toast.makeText(TambahAlamatPengiriman.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(TambahAlamatPengiriman.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }



            @Override
            public void onFailure(Call<ResponseAddress> call, Throwable t) {
                Toast.makeText(TambahAlamatPengiriman.this, "Koneksi anda bermasalah, coba ulangi lagi", Toast.LENGTH_SHORT).show();
            }
        });




    }

//    private AdapterView.OnItemSelectedListener cityListener = new AdapterView.OnItemSelectedListener() {
//        @Override
//        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//            List<ResultItem> getCity = response.body().getResult();   String citySelected = spinnerkota_pengiriman.getItemAtPosition(position).toString();
//            if(respo)
//
//        }
//
//
//        @Override
//        public void onNothingSelected(AdapterView<?> parent) {
//
//        }
//    };

    private void getGeoCity(){

        mApiService.getGeoCity().enqueue(new Callback<ResponseGeodirectories>() {
            @Override
            public void onResponse(Call<ResponseGeodirectories> call, Response<ResponseGeodirectories> response) {
                if(response.body() !=null){
                   //// String citySelected = spinnerkota_pengiriman.getItemAtPosition(p).toString();
                    List<ResultItem> getCity = response.body().getResult();
                    List<String> listSpinner = new ArrayList<String>();
                    for (int i = 0; i < getCity.size(); i++){
                        ///listSpinner.add(getCity.get(i).getIdParent());
                         id = getCity.get(i).getId();
                         nameCity = getCity.get(i).getName();
                        listSpinner.add(nameCity);
//                        if(getCity.equals(getCity.get(i).getName())){
//                                id = getCity.get
//                        }


                    }
                    // Set hasil result json ke dalam adapter spinner
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(TambahAlamatPengiriman.this,
                            android.R.layout.simple_spinner_item, listSpinner);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerkota_pengiriman.setAdapter(adapter);
//
//                    spinnerkota_pengiriman.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                        @Override
//                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                            int city_id = Integer.parseInt(listSpinner.get(position));
//                            Log.i("your_city_id", String.valueOf(city_id));
////                            int item = spinnerkota_pengiriman.getSelectedItemPosition();
////
////                            id = spinnerkota_pengiriman.getSelectedItem().toString()
//                        }
//                    });
                } else {
                    loading.dismiss();
                    Toast.makeText(TambahAlamatPengiriman.this, "Gagal mengambil data dosen", Toast.LENGTH_SHORT).show();
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
                        listSpinner.add(getCity.get(i).getId());
                        listSpinner.add(getCity.get(i).getName());
                    }
                    // Set hasil result json ke dalam adapter spinner
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(TambahAlamatPengiriman.this,
                            android.R.layout.simple_spinner_item, listSpinner);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerkecamatan_pengiriman.setAdapter(adapter);
                } else {
                    loading.dismiss();
                    Toast.makeText(TambahAlamatPengiriman.this, "Gagal mengambil data dosen", Toast.LENGTH_SHORT).show();
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
