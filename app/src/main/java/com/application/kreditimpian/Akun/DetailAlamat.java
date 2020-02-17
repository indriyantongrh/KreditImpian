package com.application.kreditimpian.Akun;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.application.kreditimpian.Api.SharedPrefManager;
import com.application.kreditimpian.Api.api_v2.BaseApiService;
import com.application.kreditimpian.Api.api_v2.UtilsApi;
import com.application.kreditimpian.Constan.ConstansAddress;
import com.application.kreditimpian.Model.ModelAddress.ResponseAddress;
import com.application.kreditimpian.Model.ModelGeodirectory.DataItem;
import com.application.kreditimpian.Model.ModelGeodirectory.ResponseGeodirectory;
import com.application.kreditimpian.Model.ModelKecamatan.ResponseKecamatan;
import com.application.kreditimpian.Model.ModelKotaKecamatan.ResponseKotaKecamatan;
import com.application.kreditimpian.Model.ModelProductRevisi.Detail;
import com.application.kreditimpian.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailAlamat extends AppCompatActivity {
    TextView textid_distric,textid_geodirectory;
    ProgressDialog pDialog;
    ImageButton btnback;
    SharedPrefManager sharedPrefManager;
    BaseApiService mApiService;
    ProgressDialog loading;
    Switch SwitchAddress;
    TextView text_mainaddress, text_kecamatan, text_kota, text_id;
    private HashMap<String, String> Kecamatanvalues;
    Spinner spinnerkecamatan_pengiriman, spinnerkota_pengiriman;
    EditText txtnamaalamat,txtnamapenerima,txtnomorhandphone,txtkodepospengiriman,txtalamatpengririman;
    Button btnupdate, btndelete;
    String id, nameCity, id_member,id_geodirectory,address_name,phone,receiver,address,postal_code,district,main_address;
    private HashMap<String, String> cityvalues;
    private HashMap<String, String> districtvalue;


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
        textid_geodirectory = findViewById(R.id.textid_geodirectory);
        textid_distric = findViewById(R.id.textid_distric);

        SwitchAddress = findViewById(R.id.SwitchAddress);

        text_kecamatan = findViewById(R.id.text_kecamatan);
        text_kota = findViewById(R.id.text_kota);
        text_id = findViewById(R.id.text_id);
        SwitchAddress = findViewById(R.id.SwitchAddress);
        txtnamaalamat = findViewById(R.id.txtnamaalamat);
        txtnamapenerima = findViewById(R.id.txtnamapenerima);
        txtnomorhandphone = findViewById(R.id.txtnomorhandphone);
        txtkodepospengiriman = findViewById(R.id.txtkodepospengiriman);
        txtalamatpengririman = findViewById(R.id.txtalamatpengririman);
        text_mainaddress = findViewById(R.id.text_mainaddress);
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
        main_address = intent.getStringExtra(ConstansAddress.KEY_MAIN_ADDRESS);
        if(main_address.equals("YES")){
            btndelete.setVisibility(View.GONE);
            SwitchAddress.setChecked(true);
        }else if (main_address.equals("NO")){
            btndelete.setVisibility(View.VISIBLE);
            SwitchAddress.setChecked(false);
        }
        /// Toast.makeText(DetailAlamat.this, "id anda" +id , Toast.LENGTH_LONG).show();

        text_id.setText(id);
        txtnamapenerima.setText(receiver);
        txtnamaalamat.setText(address_name);
        txtnomorhandphone.setText(phone);
        txtalamatpengririman.setText(address);
        txtkodepospengiriman.setText(postal_code);
        text_mainaddress.setText(main_address);;


        getGeoCity();
        getKotaKecamatan();
     ///   getGeoDistrict();

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


    private void getKotaKecamatan(){

        HashMap<String, String> params = new HashMap<>();
        params.put("id_addresses", text_id.getText().toString());

        mApiService.getKotaKecamatan(params).enqueue(new Callback<ResponseKotaKecamatan>() {
            @Override
            public void onResponse(Call<ResponseKotaKecamatan> call, Response<ResponseKotaKecamatan> response) {
                if (response.body().getResponseCode() == 200) {
                    ResponseKotaKecamatan responseKotaKecamatan = response.body();
                    List<com.application.kreditimpian.Model.ModelKotaKecamatan.DataItem> detail = responseKotaKecamatan.getData();

                    text_kota.setText("Kota yang anda pilih : "+detail.get(0).getNamaKota());
                    text_kecamatan.setText("Kecamatan yang anda pilih : "+detail.get(0).getNamaKecamatan());



                } else {
                    Toast.makeText(DetailAlamat.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseKotaKecamatan> call, Throwable t) {

            }
        });

    }

    /*Menampilkan data  City*/
    private void getGeoCity(){

        cityvalues = new HashMap<>();
        mApiService.getCity().enqueue(new Callback<ResponseGeodirectory>() {
            @Override
            public void onResponse(Call<ResponseGeodirectory> call, Response<ResponseGeodirectory> response) {
                if(response.body() !=null){
                    //// String citySelected = spinnerkota_pengiriman.getItemAtPosition(p).toString();
                    List<com.application.kreditimpian.Model.ModelGeodirectory.DataItem> getCity = response.body().getData();
                    List<String> listSpinner = new ArrayList<String>();
                    String[] idcity = new String[getCity.size() +1];
                    String[] city = new String[getCity.size() +1];
                    city[0] = "-- Pilih Kota --";
                    for (int i = 0; i < getCity.size(); i++){
                        ///listSpinner.add(getCity.get(i).getIdParent());
                        city[i + 1] = getCity.get(i).getName();
                        idcity[i + 1] = getCity.get(i).getId();
                        cityvalues.put(city[i + 1], idcity[i + 1] );
//                         id = getCity.get(i).getId();
//                         nameCity = getCity.get(i).getName();
                        ///listSpinner.add(nameCity);

                    }
                    // Set hasil result json ke dalam adapter spinner
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(DetailAlamat.this,
                            android.R.layout.simple_spinner_item, city);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerkota_pengiriman.setAdapter(adapter);
                    spinnerkota_pengiriman.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                            if(position>0){
                                String cityvalues = getCity.get(position - 1 ).getId();
                                textid_geodirectory.setText(cityvalues);
                                getKecamatan();
                               /// Toast.makeText(DetailAlamat.this, " ini id City "+cityvalues, Toast.LENGTH_LONG).show();

                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });


                } else {
                    loading.dismiss();
                    Toast.makeText(DetailAlamat.this, "Gagal mengambil data ", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseGeodirectory> call, Throwable t) {

            }
        });


    }

    /*Menampilkan data  Kecamatan*/
    private void getKecamatan(){

        Kecamatanvalues = new HashMap<>();
        HashMap<String, String> params = new HashMap<>();
        params.put("id_kota", textid_geodirectory.getText().toString());
        mApiService.getKecamatan(params).enqueue(new Callback<ResponseKecamatan>() {
            @Override
            public void onResponse(Call<ResponseKecamatan> call, Response<ResponseKecamatan> response) {
                if(response.body() !=null){
                    //// String citySelected = spinnerkota_pengiriman.getItemAtPosition(p).toString();
                    List<com.application.kreditimpian.Model.ModelKecamatan.DataItem> getKecamatan = response.body().getData();
                    List<String> listSpinner = new ArrayList<String>();
                    String[] idKecamatan = new String[getKecamatan.size() +1];
                    String[] Kecamatan = new String[getKecamatan.size() +1];
                    Kecamatan[0] = "-- Pilih Kecamatan --";
                    for (int i = 0; i < getKecamatan.size(); i++){
                        ///listSpinner.add(getCity.get(i).getIdParent());
                        Kecamatan[i + 1] = getKecamatan.get(i).getName();
                        idKecamatan[i + 1] = getKecamatan.get(i).getId();
                        Kecamatanvalues.put(Kecamatan[i + 1], idKecamatan[i + 1] );
//                         id = getCity.get(i).getId();
//                         nameCity = getCity.get(i).getName();
                        ///listSpinner.add(nameCity);

                    }
                    // Set hasil result json ke dalam adapter spinner
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(DetailAlamat.this,
                            android.R.layout.simple_spinner_item, Kecamatan);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerkecamatan_pengiriman.setAdapter(adapter);
                    spinnerkecamatan_pengiriman.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                            if(position>0){
                                String Kecamatanvalues = getKecamatan.get(position - 1 ).getId();
                                textid_distric.setText(Kecamatanvalues);
                                ///Toast.makeText(TambahAlamatPengiriman.this, " ini id City "+cityvalues, Toast.LENGTH_LONG).show();

                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });


                } else {
                    loading.dismiss();
                    Toast.makeText(DetailAlamat.this, "Gagal mengambil data ", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseKecamatan> call, Throwable t) {

            }
        });


    }


    private void getGeoDistrict(){

        districtvalue = new HashMap<>();
        mApiService.getDistrict().enqueue(new Callback<ResponseGeodirectory>() {
            @Override
            public void onResponse(Call<ResponseGeodirectory> call, Response<ResponseGeodirectory> response) {
                if(response.body() !=null){
                    List<DataItem> getDistrictArray = response.body().getData();
                    List<String> listSpinner = new ArrayList<String>();
                    String[] iddistrict = new String[getDistrictArray.size() +1];
                    String[] district = new String[getDistrictArray.size() +1];
                    district[0] = "-- Pilih Kecamatan --";
                    for (int i = 0; i < getDistrictArray.size(); i++){
                        ///listSpinner.add(getCity.get(i).getIdParent());
                        district[i + 1] = getDistrictArray.get(i).getName();
                        iddistrict[i + 1] = getDistrictArray.get(i).getId();
                        cityvalues.put(district[i + 1], iddistrict[i + 1] );

//                        listSpinner.add(getDistrict.get(i).getId());
//                        listSpinner.add(getDistrict.get(i).getName());
                    }
                    // Set hasil result json ke dalam adapter spinner
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(DetailAlamat.this,
                            android.R.layout.simple_spinner_item, district);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerkecamatan_pengiriman.setAdapter(adapter);
                    spinnerkecamatan_pengiriman.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            if(position>0){
                                String districtvalue = getDistrictArray.get(position - 1 ).getId();
                                textid_distric.setText(districtvalue);
                                Toast.makeText(DetailAlamat.this, " ini id Kecamatan  "+districtvalue, Toast.LENGTH_LONG).show();

                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                } else {
                    loading.dismiss();
                    Toast.makeText(DetailAlamat.this, "Gagal mengambil data dosen", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseGeodirectory> call, Throwable t) {

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
        params.put("id_geodirectory", textid_geodirectory.getText().toString());
        params.put("district", textid_distric.getText().toString());
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
                    Intent intent = new Intent(DetailAlamat.this, AlamatPengiriman.class);
                    startActivity(intent);
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
                    Intent intent = new Intent(DetailAlamat.this, AlamatPengiriman.class);
                    startActivity(intent);
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
