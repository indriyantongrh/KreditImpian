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
import com.application.kreditimpian.Model.ModelAddress.ResponseAddress;
import com.application.kreditimpian.Model.ModelCityRajaOngkir.ResponseCityRajaOngkir;
import com.application.kreditimpian.Model.ModelCityRajaOngkir.ResultsItem;
import com.application.kreditimpian.Model.ModelGeodirectories.ResponseGeodirectories;
import com.application.kreditimpian.Model.ModelGeodirectories.ResultItem;
import com.application.kreditimpian.Model.ModelGeodirectory.DataItem;
import com.application.kreditimpian.Model.ModelGeodirectory.ResponseGeodirectory;
import com.application.kreditimpian.Model.ModelKecamatan.ResponseKecamatan;
import com.application.kreditimpian.Model.ModelSubDistrictRajaOngkir.ResponseSubDistrictRajaOngkir;
import com.application.kreditimpian.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.text.TextUtils.isEmpty;

public class TambahAlamatPengiriman extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    ProgressDialog pDialog;
    ImageButton btnback;
    SharedPrefManager sharedPrefManager;
    BaseApiService mApiService;
    ProgressDialog loading;
    Switch SwitchAddress;
    TextView textid_distric,textid_geodirectory;
    Spinner spinnerkecamatan_pengiriman, spinnerkota_pengiriman;
    EditText txtnamaalamat,txtnamapenerima,txtnomorhandphone,txtkodepospengiriman,txtalamatpengririman;
    Button btnsimpan_pengiriman;
    String id, nameCity, id_member,id_geodirectory,address_name,phone,receiver,address,postal_code,district;
    private HashMap<String, String> cityvalues;
    private HashMap<String, String> districtvalue;
    private HashMap<String, String> Kecamatanvalues;
    private String cityvaluess;
    private List<DataItem> getCity;

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
        textid_geodirectory = findViewById(R.id.textid_geodirectory);
        textid_distric = findViewById(R.id.textid_distric);



        SwitchAddress = findViewById(R.id.SwitchAddress);
        txtnamaalamat = findViewById(R.id.txtnamaalamat);
        txtnamapenerima = findViewById(R.id.txtnamapenerima);
        txtnomorhandphone = findViewById(R.id.txtnomorhandphone);
        txtkodepospengiriman = findViewById(R.id.txtkodepospengiriman);
        txtalamatpengririman = findViewById(R.id.txtalamatpengririman);
        btnsimpan_pengiriman = findViewById(R.id.btnsimpan_pengiriman);


     ///   getGeoDistrict();
       /* getGeoCity(); */ /*Komen API di buat mas NIght unutk get City*/
        getCityRajaOngkir();

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
                    txtnamaalamat.setError("Masukan nama alamat");
                else if (isEmpty(namereceive))
                    txtnamapenerima.setError("Masukan nama penerima");
                else if (isEmpty(phoneaddress))
                    txtnomorhandphone.setError("Masukan nomor telepon penerima");
                else if (isEmpty(poscodeaddress))
                    txtkodepospengiriman.setError("Masukan Kode pos");
                else if (spinnerkota_pengiriman.getSelectedItem().equals("-- Pilih Kota --")){
                    Toast.makeText(TambahAlamatPengiriman.this, "Kota penerima belum diisi", Toast.LENGTH_LONG).show();
                }
                else if (spinnerkecamatan_pengiriman.getSelectedItem().equals("-- Pilih Kecamatan --")){
                    Toast.makeText(TambahAlamatPengiriman.this, "Kecamatan penerima belum diisi", Toast.LENGTH_LONG).show();
                }
                else if (isEmpty(address))
                    txtalamatpengririman.setError("Masukan alamat penerima");
                else
                ///SwitchAddress();
                insertAddress();
//
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
       /// params.put("avatar", getStringImage(decoded_3));
        params.put("address_name", txtnamaalamat.getText().toString());
        params.put("phone", txtnomorhandphone.getText().toString());
        params.put("receiver", txtnamapenerima.getText().toString());
        params.put("id_geodirectory", textid_geodirectory.getText().toString());
//        params.put("id_geodirectory", spinnerkota_pengiriman.getSelectedItem().toString());
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

        mApiService.InsertAddress(params).enqueue(new Callback<ResponseAddress>() {
            @Override
            public void onResponse(Call<ResponseAddress> call, Response<ResponseAddress> response) {

                pDialog.dismiss();
                if (response.body() !=null) {
                    Toast.makeText(TambahAlamatPengiriman.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(TambahAlamatPengiriman.this, AlamatPengiriman.class);
                    startActivity(intent);
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

    /*Menampilkan data  City (Tidak Kepakai)*/
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
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(TambahAlamatPengiriman.this,
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
                               /// Toast.makeText(TambahAlamatPengiriman.this, " ini id City "+cityvalues, Toast.LENGTH_LONG).show();

                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });


                } else {
                    loading.dismiss();
                    Toast.makeText(TambahAlamatPengiriman.this, "Gagal mengambil data ", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseGeodirectory> call, Throwable t) {

            }
        });


    }

    /*Menampilkan data  Kecamatan(Tidak KEpakai)*/
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
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(TambahAlamatPengiriman.this,
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
                    Toast.makeText(TambahAlamatPengiriman.this, "Gagal mengambil data ", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseKecamatan> call, Throwable t) {

            }
        });


    }


    /*Menampilkan City using API Cirecle Creative */
    private void getCityRajaOngkir(){

        cityvalues = new HashMap<>();
        mApiService.getCityGeodirectories().enqueue(new Callback<ResponseCityRajaOngkir>() {
            @Override
            public void onResponse(Call<ResponseCityRajaOngkir> call, Response<ResponseCityRajaOngkir> response) {

                if(response.body() !=null){
                    //// String citySelected = spinnerkota_pengiriman.getItemAtPosition(p).toString();
                    List<ResultsItem> getCity = response.body().getRajaongkir().getResults();
                    List<String> listSpinner = new ArrayList<String>();
                    String[] idcity = new String[getCity.size() +1];
                    String[] city = new String[getCity.size() +1];
                    String[] type = new String[getCity.size() +1];
                    city[0] = "-- Pilih Kota --";
                    for (int i = 0; i < getCity.size(); i++){
                        ///listSpinner.add(getCity.get(i).getIdParent());
                        city[i + 1] = (getCity.get(i).getType()+" ")+(getCity.get(i).getCityName());
                        type[i + 1] = getCity.get(i).getType();
                        idcity[i + 1] = getCity.get(i).getCityId();
                        cityvalues.put(city[i + 1], idcity[i + 1] );
//                         id = getCity.get(i).getId();
//                         nameCity = getCity.get(i).getName();
                        ///listSpinner.add(nameCity);

                    }
                    // Set hasil result json ke dalam adapter spinner
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(TambahAlamatPengiriman.this,
                            android.R.layout.simple_spinner_item, city);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerkota_pengiriman.setAdapter(adapter);
                    spinnerkota_pengiriman.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                            if(position>0){
                                String cityvalues = getCity.get(position - 1 ).getCityId();
                                //Toast.makeText(TambahAlamatPengiriman.this, "id kota anda "+cityvalues , Toast.LENGTH_LONG).show();
                                textid_geodirectory.setText(cityvalues);
                                getSubDistrict();
                                ///getKecamatan();

                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });


                } else {
                    loading.dismiss();
                    Toast.makeText(TambahAlamatPengiriman.this, "Gagal mengambil data ", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ResponseCityRajaOngkir> call, Throwable t) {

            }
        });


    }


    /*MEnampilkan City using API Cirecle Creative*/
    private void getSubDistrict(){

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        pDialog.setMessage("Loading....");
        pDialog.show();

        Kecamatanvalues = new HashMap<>();
        HashMap<String, String> params = new HashMap<>();
        //String SUBDISTRICT = "SUBDISTRICT";
       /// params.put("TYPE", SUBDISTRICT);
        params.put("id_city", textid_geodirectory.getText().toString());
        mApiService.getSubDistrictGeodirectories(params).enqueue(new Callback<ResponseSubDistrictRajaOngkir>() {
            @Override
            public void onResponse(Call<ResponseSubDistrictRajaOngkir> call, Response<ResponseSubDistrictRajaOngkir> response) {
                pDialog.dismiss();
                if(response.body() !=null){
                    //// String citySelected = spinnerkota_pengiriman.getItemAtPosition(p).toString();
                    List<com.application.kreditimpian.Model.ModelSubDistrictRajaOngkir.ResultsItem> getKecamatan = response.body().getRajaongkir().getResults();
                    List<String> listSpinner = new ArrayList<String>();
                    String[] idKecamatan = new String[getKecamatan.size() +1];
                    String[] Kecamatan = new String[getKecamatan.size() +1];
                    Kecamatan[0] = "-- Pilih Kecamatan --";
                    for (int i = 0; i < getKecamatan.size(); i++){
                        ///listSpinner.add(getCity.get(i).getIdParent());
                        Kecamatan[i + 1] = getKecamatan.get(i).getSubdistrictName();
                        idKecamatan[i + 1] = getKecamatan.get(i).getSubdistrictId();
                        Kecamatanvalues.put(Kecamatan[i + 1], idKecamatan[i + 1] );
//                         id = getCity.get(i).getId();
//                         nameCity = getCity.get(i).getName();
                        ///listSpinner.add(nameCity);

                    }
                    // Set hasil result json ke dalam adapter spinner
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(TambahAlamatPengiriman.this,
                            android.R.layout.simple_spinner_item, Kecamatan);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerkecamatan_pengiriman.setAdapter(adapter);
                    spinnerkecamatan_pengiriman.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                            if(position>0){
                                String Kecamatanvalues = getKecamatan.get(position - 1 ).getSubdistrictId();
                                textid_distric.setText(Kecamatanvalues);
                                ///Toast.makeText(TambahAlamatPengiriman.this, " id KEcamtan anda "+Kecamatanvalues, Toast.LENGTH_LONG).show();

                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });


                } else {
                    pDialog.dismiss();
                    Toast.makeText(TambahAlamatPengiriman.this, "Gagal mengambil data ", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseSubDistrictRajaOngkir> call, Throwable t) {

            }
        });

    }


/*    private void getGeoCityyy(){

        cityvalues = new HashMap<>();
        mApiService.getCity().enqueue(new Callback<ResponseGeodirectory>() {
            @Override
            public void onResponse(Call<ResponseGeodirectory> call, Response<ResponseGeodirectory> response) {
                if(response.body() !=null){
                   //// String citySelected = spinnerkota_pengiriman.getItemAtPosition(p).toString();
                    List<DataItem> getCity = response.body().getData();
                    getCity(getCity);
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
                   /// Toast.makeText(TambahAlamatPengiriman.this, "Gagal mengambil data dosen", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseGeodirectory> call, Throwable t) {

            }
        });
    }*/

/*    private void getCity(List<DataItem> getCity){
        this.getCity = getCity;
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
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(TambahAlamatPengiriman.this,
                android.R.layout.simple_spinner_item, city);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerkota_pengiriman.setAdapter(adapter);
        spinnerkota_pengiriman.setOnItemSelectedListener(this);

    }*/

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
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(TambahAlamatPengiriman.this,
                            android.R.layout.simple_spinner_item, district);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerkecamatan_pengiriman.setAdapter(adapter);
                    spinnerkecamatan_pengiriman.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            if(position>0){
                                String districtvalue = getDistrictArray.get(position - 1 ).getId();
                                textid_distric.setText(districtvalue);
                               /// Toast.makeText(TambahAlamatPengiriman.this, " ini id Kecamatan  "+districtvalue, Toast.LENGTH_LONG).show();

                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                } else {
                    loading.dismiss();
                    Toast.makeText(TambahAlamatPengiriman.this, "Gagal mengambil data dosen", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseGeodirectory> call, Throwable t) {

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
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (view == spinnerkota_pengiriman){
            if(position>0){
                String cityvalues = getCity.get(position - 1 ).getId();
                this.cityvaluess = cityvalues;
                Toast.makeText(TambahAlamatPengiriman.this, " ini id City "+cityvalues, Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

//    @Override
//    public void onBackPressed() {
//        finish();
//    }

}
