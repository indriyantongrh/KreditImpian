package com.application.kreditimpian.Akun;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.application.kreditimpian.Api.SharedPrefManager;
import com.application.kreditimpian.Api.api_v2.BaseApiService;
import com.application.kreditimpian.Api.api_v2.UtilsApi;
import com.application.kreditimpian.Constan.ConstansAddress;
import com.application.kreditimpian.Model.ModelAddress.ResponseAddress;
import com.application.kreditimpian.Model.ModelCityRajaOngkir.ResponseCityRajaOngkir;
import com.application.kreditimpian.Model.ModelCityRajaOngkir.ResultsItem;
import com.application.kreditimpian.Model.ModelCitySubDistrict.ResponseCitySubDistrict;
import com.application.kreditimpian.Model.ModelGeodirectory.DataItem;
import com.application.kreditimpian.Model.ModelGeodirectory.ResponseGeodirectory;
import com.application.kreditimpian.Model.ModelKecamatan.ResponseKecamatan;
import com.application.kreditimpian.Model.ModelSubDistrict.ResponseSubdistrict;
import com.application.kreditimpian.Model.ModelSubDistrictRajaOngkir.ResponseSubDistrictRajaOngkir;
import com.application.kreditimpian.R;
import com.chivorn.smartmaterialspinner.SmartMaterialSpinner;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailAlamat extends AppCompatActivity {
    TextView textid_distric, textid_geodirectory;
    ProgressDialog pDialog;
    ImageButton btnback;
    SharedPrefManager sharedPrefManager;
    BaseApiService mApiService;
    ProgressDialog loading;
    Switch SwitchAddress;
    TextView text_mainaddress, text_kecamatan, text_kota, text_id;
    private HashMap<String, String> Kecamatanvalues;
    Spinner spinnerkecamatan_pengiriman, spinnerkota_pengiriman;
    TextInputEditText txtnamaalamat, txtnamapenerima, txtnomorhandphone, txtkodepospengiriman, txtalamatpengririman;
    Button btnupdate, btndelete, btnUbah, btnUbahKecamatan;
    String id, nameCity, id_member, id_geodirectory, address_name, phone, receiver, address, postal_code, district, main_address;
    private HashMap<String, String> cityvalues;
    private HashMap<String, String> districtvalue;
    ConnectivityManager conMgr;
    LinearLayout LinearKota, LinearKecamatan;
    SmartMaterialSpinner spinKota, spinKecamatan;
    ConstraintLayout layout6, layout5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_alamat);

        setActionBarTitle("Detail Alamat");
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);// set drawable icon
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        {
            if (conMgr.getActiveNetworkInfo() != null
                    && conMgr.getActiveNetworkInfo().isAvailable()
                    && conMgr.getActiveNetworkInfo().isConnected()) {
            } else {
                ///Toast.makeText(getApplicationContext(), "Tidak ada akses Internet", Toast.LENGTH_LONG).show();
                try {
                    AlertDialog alertDialog = new AlertDialog.Builder(this).create();

                    alertDialog.setTitle("Info");
                    alertDialog.setMessage("Internet tidak tersedia, Periksa konektivitas internet Anda dan coba lagi");
                    alertDialog.setIcon(R.drawable.no_connection);
                    alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            finish();

                        }
                    });

                    alertDialog.show();
                } catch (Exception e) {
                    /// Log.d(Constants. , "Show Dialog: " + e.getMessage());
                }

            }
        }

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
        btnUbah = findViewById(R.id.btnUbah);
        LinearKota = findViewById(R.id.LinearKota);
        btnUbahKecamatan = findViewById(R.id.btnUbahKecamatan);
        LinearKecamatan = findViewById(R.id.LinearKecamatan);
        spinKota = findViewById(R.id.spinKota);
        spinKecamatan = findViewById(R.id.spinKecamatan);
        layout6 = findViewById(R.id.layout6);
        layout5 = findViewById(R.id.layout5);



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
        if (main_address.equals("YES")) {
            btndelete.setVisibility(View.GONE);
            SwitchAddress.setChecked(true);
        } else if (main_address.equals("NO")) {
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
        text_mainaddress.setText(main_address);
        ;


        getCityRajaOngkir();
        getKotaKecamatan();
        getSubdistrict();
        ///   getGeoDistrict();


        btnUbah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ///LinearKota.setVisibility(View.VISIBLE);
                layout5.setVisibility(View.VISIBLE);
                btnUbah.setVisibility(View.GONE);
                text_kota.setVisibility(View.GONE);
            }
        });

        btnUbahKecamatan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //LinearKecamatan.setVisibility(View.VISIBLE);
                layout6.setVisibility(View.VISIBLE);
                btnUbahKecamatan.setVisibility(View.GONE);
                text_kecamatan.setVisibility(View.GONE);
            }
        });

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

    /*Menampilkan City using API Cirecle Creative */
    private void getCityRajaOngkir() {

        cityvalues = new HashMap<>();
        mApiService.getCityGeodirectories().enqueue(new Callback<ResponseCityRajaOngkir>() {
            @Override
            public void onResponse(Call<ResponseCityRajaOngkir> call, Response<ResponseCityRajaOngkir> response) {

                if (response.body() != null) {
                    //// String citySelected = spinnerkota_pengiriman.getItemAtPosition(p).toString();
                    List<ResultsItem> getCity = response.body().getRajaongkir().getResults();
                    List<String> listSpinner = new ArrayList<String>();
                    String[] idcity = new String[getCity.size() + 1];
                    String[] city = new String[getCity.size() + 1];
                    String[] type = new String[getCity.size() + 1];
                    city[0] = "-- Pilih Kota --";
                    for (int i = 0; i < getCity.size(); i++) {
                        ///listSpinner.add(getCity.get(i).getIdParent());
                        city[i + 1] = (getCity.get(i).getType() + " ") + (getCity.get(i).getCityName());
                        type[i + 1] = getCity.get(i).getType();
                        idcity[i + 1] = getCity.get(i).getCityId();
                        cityvalues.put(city[i + 1], idcity[i + 1]);
//                         id = getCity.get(i).getId();
//                         nameCity = getCity.get(i).getName();
                        ///listSpinner.add(nameCity);

                    }
                    // Set hasil result json ke dalam adapter spinner
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(DetailAlamat.this,
                            android.R.layout.simple_spinner_item, city);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinKota.setAdapter(adapter);
                    spinKota.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            if (position > 0) {
                                String cityvalues = getCity.get(position - 1).getCityId();
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

                    spinnerkota_pengiriman.setAdapter(adapter);
                    spinnerkota_pengiriman.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                            if (position > 0) {
                                String cityvalues = getCity.get(position - 1).getCityId();
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
                    Toast.makeText(DetailAlamat.this, "Gagal mengambil data ", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ResponseCityRajaOngkir> call, Throwable t) {

            }
        });


    }


    /*MEnampilkan City using API Cirecle Creative*/
    private void getSubDistrict() {

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
                if (response.body() != null) {
                    //// String citySelected = spinnerkota_pengiriman.getItemAtPosition(p).toString();
                    List<com.application.kreditimpian.Model.ModelSubDistrictRajaOngkir.ResultsItem> getKecamatan = response.body().getRajaongkir().getResults();
                    List<String> listSpinner = new ArrayList<String>();
                    String[] idKecamatan = new String[getKecamatan.size() + 1];
                    String[] Kecamatan = new String[getKecamatan.size() + 1];
                    Kecamatan[0] = "-- Pilih Kecamatan --";
                    for (int i = 0; i < getKecamatan.size(); i++) {
                        ///listSpinner.add(getCity.get(i).getIdParent());
                        Kecamatan[i + 1] = getKecamatan.get(i).getSubdistrictName();
                        idKecamatan[i + 1] = getKecamatan.get(i).getSubdistrictId();
                        Kecamatanvalues.put(Kecamatan[i + 1], idKecamatan[i + 1]);
//                         id = getCity.get(i).getId();
//                         nameCity = getCity.get(i).getName();
                        ///listSpinner.add(nameCity);

                    }
                    // Set hasil result json ke dalam adapter spinner
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(DetailAlamat.this,
                            android.R.layout.simple_spinner_item, Kecamatan);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinKecamatan.setAdapter(adapter);
                    spinKecamatan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            if (position > 0) {
                                String Kecamatanvalues = getKecamatan.get(position - 1).getSubdistrictId();
                                textid_distric.setText(Kecamatanvalues);
                                ///Toast.makeText(TambahAlamatPengiriman.this, " id KEcamtan anda "+Kecamatanvalues, Toast.LENGTH_LONG).show();

                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });

                    spinnerkecamatan_pengiriman.setAdapter(adapter);
                    spinnerkecamatan_pengiriman.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                            if (position > 0) {
                                String Kecamatanvalues = getKecamatan.get(position - 1).getSubdistrictId();
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
                    Toast.makeText(DetailAlamat.this, "Gagal mengambil data ", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseSubDistrictRajaOngkir> call, Throwable t) {

            }
        });

    }


    /*Fungsi untk mengambil data kota dan kecamatan*/
    private void getKotaKecamatan() {

        HashMap<String, String> params = new HashMap<>();
        params.put("id_addresses", text_id.getText().toString());

        mApiService.getKotaKecamatan(params).enqueue(new Callback<ResponseCitySubDistrict>() {
            @Override
            public void onResponse(Call<ResponseCitySubDistrict> call, Response<ResponseCitySubDistrict> response) {
                if (response.body().getRajaongkir().getStatus().getCode() == 200) {
                    ResponseCitySubDistrict responseKotaKecamatan = response.body();
                    /// List<Results> detail = responseKotaKecamatan.getRajaongkir().getResults();

                    text_kota.setText("Kota Pengirim : " + responseKotaKecamatan.getRajaongkir().getResults().getCityName());
                    //text_kecamatan.setText("Kecamatan pengirim: "+detail.get(0).getNamaKecamatan());

                    if (text_kota.equals("Kota yang anda pilih : " + responseKotaKecamatan.getRajaongkir().getResults().getCityName())) {
                        btnUbah.setVisibility(View.VISIBLE);
                    } else if (text_kota.equals("Kota yang anda pilih : " + null)) {
                        ///LinearKota.setVisibility(View.VISIBLE);
                        layout5.setVisibility(View.VISIBLE);
                        btnUbah.setVisibility(View.GONE);
                    }

                  /*  if(text_kecamatan.equals("Kota yang anda pilih : "+detail.get(0).getCityName())){
                        btnUbahKecamatan.setVisibility(View.VISIBLE);
                    }else if (text_kecamatan.equals("Kota yang anda pilih : "+null)){
                        LinearKecamatan.setVisibility(View.VISIBLE);
                        btnUbahKecamatan.setVisibility(View.GONE);
                    }*/

                } else {
                    Toast.makeText(DetailAlamat.this, response.body().getRajaongkir().getStatus().getDescription(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseCitySubDistrict> call, Throwable t) {

            }
        });

    }


    private void UpdateAddreses() {
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
                if (response.body().getResponseCode() == 200) {
                    AlertDialog alertDialog = new AlertDialog.Builder(DetailAlamat.this).create();

                    alertDialog.setTitle("Sukses");
                    alertDialog.setMessage("Alamat berhasil di perbaharui.");
                    alertDialog.setIcon(R.drawable.successfully);
                    alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            finish();

                        }
                    });

                    alertDialog.show();
                    // finish();
                    ///Toast.makeText(DetailAlamat.this, response.body().getMessage() , Toast.LENGTH_LONG).show();
                    /*Intent intent = new Intent(DetailAlamat.this, AlamatPengiriman.class);
                    startActivity(intent);*/

                } else {
                    Toast.makeText(DetailAlamat.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseAddress> call, Throwable t) {

            }
        });

    }

    private void DeleteAddreses() {

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        pDialog.setMessage("Menghapus data Alamat");
        pDialog.show();

        mApiService.deleteAddreses(id, sharedPrefManager.getSpIdMember()).enqueue(new Callback<ResponseAddress>() {
            @Override
            public void onResponse(Call<ResponseAddress> call, Response<ResponseAddress> response) {
                if (response.body().getResponseCode() == 200) {
                    AlertDialog alertDialog = new AlertDialog.Builder(DetailAlamat.this).create();
                    alertDialog.setTitle("Sukses");
                    alertDialog.setMessage("Alamat berhasil dihapus.");
                    alertDialog.setIcon(R.drawable.successfully);
                    alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            finish();

                        }
                    });

                    alertDialog.show();
                    /* Toast.makeText(DetailAlamat.this, response.body().getMessage() , Toast.LENGTH_LONG).show();
                     *//*Intent intent = new Intent(DetailAlamat.this, AlamatPengiriman.class);
                    startActivity(intent);
                    DetailAlamat.super.onBackPressed();*//*
                    finish();
                    onBackPressed();*/
                } else {
                    Toast.makeText(DetailAlamat.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseAddress> call, Throwable t) {
                Toast.makeText(DetailAlamat.this, "Koneksi anda bermasalah, coba ulangi lagi", Toast.LENGTH_SHORT).show();
            }
        });

    }


    private void getSubdistrict() {
        HashMap<String, String> params = new HashMap<>();
        params.put("id_addresses", text_id.getText().toString());

        mApiService.getSubdistrcit(params).enqueue(new Callback<ResponseSubdistrict>() {
            @Override
            public void onResponse(Call<ResponseSubdistrict> call, Response<ResponseSubdistrict> response) {
                if (response.body().getRajaongkir().getStatus().getCode() == 200) {
                    ResponseSubdistrict responseSubdistrict = response.body();
                    /// List<Results> detail = responseKotaKecamatan.getRajaongkir().getResults();

                    text_kecamatan.setText("Kecamatan Pengirim : " + responseSubdistrict.getRajaongkir().getResults().getSubdistrictName());
                    //text_kecamatan.setText("Kecamatan pengirim: "+detail.get(0).getNamaKecamatan());

                    if (text_kecamatan.equals("Kecamatan yang anda pilih : " + responseSubdistrict.getRajaongkir().getResults().getSubdistrictName())) {
                        btnUbahKecamatan.setVisibility(View.VISIBLE);
                    } else if (text_kecamatan.equals("Kecamatan yang anda pilih : " + null)) {
                        LinearKecamatan.setVisibility(View.VISIBLE);
                        btnUbahKecamatan.setVisibility(View.GONE);
                    }

                  /*  if(text_kecamatan.equals("Kota yang anda pilih : "+detail.get(0).getCityName())){
                        btnUbahKecamatan.setVisibility(View.VISIBLE);
                    }else if (text_kecamatan.equals("Kota yang anda pilih : "+null)){
                        LinearKecamatan.setVisibility(View.VISIBLE);
                        btnUbahKecamatan.setVisibility(View.GONE);
                    }*/

                } else {
                    Toast.makeText(DetailAlamat.this, response.body().getRajaongkir().getStatus().getDescription(), Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onFailure(Call<ResponseSubdistrict> call, Throwable t) {

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
        /*Intent intent = new Intent(DetailAlamat.this, AlamatPengiriman.class);
        startActivity(intent);
        finish();*/
        super.onBackPressed();
    }


}
