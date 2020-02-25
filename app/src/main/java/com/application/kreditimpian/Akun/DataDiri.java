package com.application.kreditimpian.Akun;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.application.kreditimpian.Api.SharedPrefManager;
import com.application.kreditimpian.Api.api_v2.BaseApiService;

import com.application.kreditimpian.Api.api_v2.UtilsApi;

import com.application.kreditimpian.Model.ModelDetailMember.ResponseDetailMember;
import com.application.kreditimpian.Model.ModelGeodirectory.DataItem;
import com.application.kreditimpian.Model.ModelGeodirectory.ResponseGeodirectory;

import com.application.kreditimpian.Model.ModelKecamatan.ResponseKecamatan;
import com.application.kreditimpian.Model.ModelKotaKecamatan.ResponseKotaKecamatan;
import com.application.kreditimpian.Model.ModelMember.ResponseMember;

import com.application.kreditimpian.Model.ModelMemberInsert.ResponseMemberInsert;

import com.application.kreditimpian.Model.ModelUploadImage.ResponseUploadImage;
import com.application.kreditimpian.Model.ModelUserDetail.ResponseMembers;
import com.application.kreditimpian.Model.ModelUserDetail.ResultItem;
import com.application.kreditimpian.R;

import com.bumptech.glide.Glide;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import java.util.Calendar;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


import static android.text.TextUtils.isEmpty;


public class DataDiri extends AppCompatActivity implements View.OnClickListener {
    private static final int PERMISSION_REQUEST_CODE = 200;
    private int mYear, mMonth, mDay;
    ImageButton btnback;
    Button btnuploadfoto,btnuploadktp,btnuploadnpwp;
    ImageView imagektp, imagenpwp, imageself;
    Spinner spinnerjeniskelamin, spinnerstatus, spinneragama, spinnerstatusrumah,
            spinnerkredit, spinnerkota_saudaraa, spinnerkecamatn_saudara;
    Button btnsimpan;
    TextView id_kota, id_kecamatan, kecamatanSelected,kotaSelected;
    EditText txtnamalengkap, txttempatlahir, txttanggallahir, txtnikktp, txtnomornpwp, txtpekerjaan, txtpendapatan,
            txtjumlahtanggungan, txtalamatemail, txtibukandung, txtnomorhandphone, txtnomortlp, txtfacebook,
            txttwitter, txtinstagram, txtnamasaudara, txtnomorhandphonesaudara, txtkodepos_saudara, txtalamat_saudara;
    private HashMap<String, String> cityvalues;
    private HashMap<String, String> Kecamatanvalues;
    private HashMap<String, String> districtvalue;

    ProgressDialog loading;
    SharedPrefManager sharedPrefManager;
    Context mContext;
    BaseApiService mApiService;
    String fullname, idprofile,
    imgphoto ="",
    imgktp = "";
    ProgressDialog pDialog;
    //untuk upload gambar
    Bitmap bitmap, decoded_1, decoded_2, decoded_3;
    int bitmap_size = 80; // range 1 - 100=
    int PICK_IMAGE_REQUEST_1 = 1;
    int PICK_IMAGE_REQUEST_2 = 2;
    int PICK_IMAGE_REQUEST_3 = 3;
    int REQUEST_IMAGE_CAPTURE_1 = 11;
    int REQUEST_IMAGE_CAPTURE_2 = 12;
    int REQUEST_IMAGE_CAPTURE_3 = 13;

    ResultItem reqresultItem;
    List<ResultItem> resultItemList = new ArrayList<>();


    com.application.kreditimpian.Model.ModelGeodirectories.ResultItem reqResultCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_diri);

        setActionBarTitle("Data Diri");
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);// set drawable icon
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        mApiService = UtilsApi.getAPIService();
        sharedPrefManager = new SharedPrefManager(DataDiri.this);
        String token = sharedPrefManager.getSPToken();
        String email = sharedPrefManager.getSPEmail();
        String msisdn = sharedPrefManager.getSpMsisdn();
        String id_member = sharedPrefManager.getSpIdMember();
        ///getmemberDetail();
        getDetailMember();
        /// Toast.makeText(DataDiri.this, "Id member anda "+id_member, Toast.LENGTH_LONG).show();


        imageself = findViewById(R.id.imageself);
        imagenpwp = findViewById(R.id.imagenpwp);
        imagektp = findViewById(R.id.imagektp);
        txtnamalengkap = findViewById(R.id.txtnamalengkap);
        txttempatlahir = findViewById(R.id.txttempatlahir);
        txttanggallahir = findViewById(R.id.txttanggallahir);
        spinnerjeniskelamin = findViewById(R.id.spinnerjeniskelamin);
        spinnerstatus = findViewById(R.id.spinnerstatus);
        spinneragama = findViewById(R.id.spinneragama);
        txtnikktp = findViewById(R.id.txtnikktp);
        txtnomornpwp = findViewById(R.id.txtnomornpwp);
        txtpekerjaan = findViewById(R.id.txtpekerjaan);
        txtpendapatan = findViewById(R.id.txtpendapatan);
        txtjumlahtanggungan = findViewById(R.id.txtjumlahtanggungan);
        txtalamatemail = findViewById(R.id.txtalamatemail);
        txtibukandung = findViewById(R.id.txtibukandung);
        txtnomorhandphone = findViewById(R.id.txtnomorhandphone);
        txtnomortlp = findViewById(R.id.txtnomortlp);
        spinnerstatusrumah = findViewById(R.id.spinnerstatusrumah);
        spinnerkredit = findViewById(R.id.spinnerkredit);
        txtfacebook = findViewById(R.id.txtfacebook);
        txttwitter = findViewById(R.id.txttwitter);
        txtinstagram = findViewById(R.id.txtinstagram);
        id_kota = findViewById(R.id.id_kota);
        id_kecamatan = findViewById(R.id.id_kecamatan);
        btnsimpan = findViewById(R.id.btnsimpan);

        ///formdatasaudara tidaq serumah

        txtnamasaudara = findViewById(R.id.txtnamasaudara);
        txtnomorhandphonesaudara = findViewById(R.id.txtnomorhandphonesaudara);
        spinnerkota_saudaraa = findViewById(R.id.spinnerkota_saudaraa);
        spinnerkecamatn_saudara = findViewById(R.id.spinnerkecamatn_saudara);
        kotaSelected = findViewById(R.id.kotaSelected);
        kecamatanSelected = findViewById(R.id.kecamatanSelected);
        txtkodepos_saudara = findViewById(R.id.txtkodepos_saudara);
        txtalamat_saudara = findViewById(R.id.txtalamat_saudara);
        btnuploadfoto = findViewById(R.id.btnuploadfoto);
        btnuploadktp = findViewById(R.id.btnuploadktp);
        btnuploadnpwp = findViewById(R.id.btnuploadnpwp);




        txtalamatemail.setText(email);
        txtnomorhandphone.setText(msisdn);


        List<String> gender = new ArrayList<String>();
        gender.add(0, "Jenis Kelamin");
        gender.add("FEMALE");
        gender.add("MALE");

        List<String> religion = new ArrayList<String>();
        religion.add(0, "Pilih Agama");
        religion.add("HINDU");
        religion.add("ISLAM");
        religion.add("KRISTEN");
        religion.add("KATOLIK");
        religion.add("BUDHA");

        List<String> Status = new ArrayList<String>();
        Status.add(0, "Pilih Status");
        Status.add("LAJANG");
        Status.add("MENIKAH");
        Status.add("CERAI");

        List<String> Kredit = new ArrayList<String>();
        Kredit.add(0, "Apakah Anda memiliki kredit/cicilan yang sedang berjalan?");
        Kredit.add("YES");
        Kredit.add("NO");

        List<String> TempatTinggal = new ArrayList<String>();
        TempatTinggal.add(0, "Status Tempat Tinggal");
        TempatTinggal.add("KONTRAK");
        TempatTinggal.add("RUMAH SENDIRI");
        TempatTinggal.add("KOS");
        TempatTinggal.add("IKUT ORANG TUA");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(DataDiri.this, android.R.layout.simple_spinner_item, gender);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinnerjeniskelamin.setAdapter(adapter);

        ArrayAdapter<String> adapterStatus = new ArrayAdapter<String>(DataDiri.this, android.R.layout.simple_spinner_item, Status);
        adapterStatus.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinnerstatus.setAdapter(adapterStatus);

        ArrayAdapter<String> adapterReligion = new ArrayAdapter<String>(DataDiri.this, android.R.layout.simple_spinner_item, religion);
        adapterReligion.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinneragama.setAdapter(adapterReligion);

        ArrayAdapter<String> adapterRumah = new ArrayAdapter<String>(DataDiri.this, android.R.layout.simple_spinner_item, TempatTinggal);
        adapterRumah.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinnerstatusrumah.setAdapter(adapterRumah);

        ArrayAdapter<String> adapterKredit = new ArrayAdapter<String>(DataDiri.this, android.R.layout.simple_spinner_item, Kredit);
        adapterKredit.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinnerkredit.setAdapter(adapterKredit);

        /*untuk upload gambar wajah*/
        btnuploadfoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(imgphoto.equals("")){
                    Toast.makeText(DataDiri.this, "Silahkan masukan foto wajah ", Toast.LENGTH_LONG).show();
                } else {
                    UploadFoto();
                }
            }
        });

        /*untuk upload gambar KTP*/
        btnuploadktp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imgktp.equals("")) {
                    Toast.makeText(DataDiri.this, "Silahkan masukan KTP anda", Toast.LENGTH_LONG).show();
                }else {
                    UploadKtp();
                }
            }
        });

        /*untuk upload gambar NPWP*/
        btnuploadnpwp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UploadNpwp();
            }
        });

        getGeoCity();
        /// getGeoDistrict();

        imageself.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //pakai alert dialog
                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(DataDiri.this);
                builder.setTitle("Pilih");
                builder.setMessage("Silahkan memilih kamera atau galeri");
                builder.setPositiveButton("Kamera", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        if (takePictureIntent.resolveActivity(DataDiri.this.getPackageManager()) != null) {
                            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE_1);
                        }
                    }
                });
                builder.setNegativeButton("Galeri", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        showFileChooser1();
                    }
                });

                android.app.AlertDialog alert = builder.create();
                alert.show();

                btnuploadfoto.setVisibility(View.VISIBLE);
            }


        });


        imagektp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //pakai alert dialog
                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(DataDiri.this);
                builder.setTitle("Pilih");
                builder.setMessage("Silahkan memilih kamera atau galeri");
                builder.setPositiveButton("Kamera", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        if (takePictureIntent.resolveActivity(DataDiri.this.getPackageManager()) != null) {
                            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE_2);
                        }
                    }
                });
                builder.setNegativeButton("Galeri", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        showFileChooser2();
                    }
                });

                android.app.AlertDialog alert = builder.create();
                alert.show();
                btnuploadktp.setVisibility(View.VISIBLE);

            }
        });


        imagenpwp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //pakai alert dialog
                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(DataDiri.this);
                builder.setTitle("Pilih");
                builder.setMessage("Silahkan memilih kamera atau galeri");
                builder.setPositiveButton("Kamera", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        if (takePictureIntent.resolveActivity(DataDiri.this.getPackageManager()) != null) {
                            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE_3);
                        }
                    }
                });
                builder.setNegativeButton("Galeri", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        showFileChooser3();
                    }
                });

                android.app.AlertDialog alert = builder.create();
                alert.show();
                btnuploadnpwp.setVisibility(View.VISIBLE);


            }
        });


        txttanggallahir.setOnClickListener(this);
        btnback = findViewById(R.id.btnback);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        if (ContextCompat.checkSelfPermission(DataDiri.this,
                Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {

        } else {
            requestPermission();
        }

        /*Btn simpan */
        btnsimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //mengambil data dari edittext
                String fullname = txtnamalengkap.getText().toString();
                String birthplace = txttempatlahir.getText().toString();
                String birthday = txttanggallahir.getText().toString();
                String gender = spinnerjeniskelamin.toString();
                String marital = spinnerstatus.toString();
                String religion = spinneragama.toString();
                String number_citizen = txtnikktp.getText().toString();
                String number_taxpayer = txtnomornpwp.getText().toString();
                String job = txtpekerjaan.getText().toString();
                String income = txtpendapatan.getText().toString();
                String family_dependent = txtjumlahtanggungan.getText().toString();
                String email = txtalamatemail.getText().toString();
                String phone = txtnomorhandphone.getText().toString();
                String installment = spinnerkredit.toString();
                String residence_status = spinnerstatusrumah.toString();
                String contact_office = txtnomortlp.getText().toString();
                String facebook = txtfacebook.getText().toString();
                String twitter = txttwitter.getText().toString();
                String instagram = txtinstagram.getText().toString();
                String namasaudara = txtnamasaudara.getText().toString();
                String nomorhpsaudara = txtnomorhandphonesaudara.getText().toString();
                String kodepossaudara = txtkodepos_saudara.getText().toString();
                String alamatsaudara = txtalamat_saudara.getText().toString();

                if (isEmpty(fullname))
                    txtnamalengkap.setError("Tidak boleh kosong");
                else if (isEmpty(birthplace))
                    txttempatlahir.setError("Tidak boleh kosong");
                else if (isEmpty(birthday))
                    txttanggallahir.setError("Tidak boleh kosong");
                else if (isEmpty(number_citizen))
                    txtnikktp.setError("Tidak boleh kosong");
                else if (isEmpty(job))
                    txtpekerjaan.setError("Tidak boleh kosong");
                else if (isEmpty(income))
                    txtpendapatan.setError("Tidak boleh kosong");
                else if (isEmpty(family_dependent))
                    txtjumlahtanggungan.setError("Tidak boleh kosong");
                else if (isEmpty(contact_office))
                    txtnomortlp.setError("Tidak boleh kosong");
                else if (isEmpty(family_dependent))
                    txtjumlahtanggungan.setError("Tidak boleh kosong");
                else if (isEmpty(contact_office))
                    txtnomortlp.setError("Tidak boleh kosong");
                else if (isEmpty(namasaudara))
                    txtnamasaudara.setError("Tidak boleh kosong");
                else if (isEmpty(nomorhpsaudara))
                    txtnomorhandphonesaudara.setError("Tidak boleh kosongg");
                else if (isEmpty(kodepossaudara))
                    txtkodepos_saudara.setError("Tidak boleh kosong");
                else if (isEmpty(alamatsaudara))
                    txtalamat_saudara.setError("Tidak boleh kosong");
                /*else if (imgphoto.equals("")) {
                    Toast.makeText(DataDiri.this, "Silahkan masukan foto wajah ", Toast.LENGTH_LONG).show();
                } else if (imgktp.equals("")) {
                    Toast.makeText(DataDiri.this, "Silahkan masukan KTP anda", Toast.LENGTH_LONG).show();*/
                else
                    InsertMember();

            }
        });

    }


    @Override
    public void onBackPressed() {
        finish();
    }


    /*Method Selected date*/
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txttanggallahir:

                Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                                txttanggallahir.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
                break;
        }
    }


    private void updatemember() {
        loading = new ProgressDialog(this);
        loading.setCancelable(false);
        loading.setMessage("Menyimpan data....");
        loading.show();

        //mengambil data dari edittext
        String token = sharedPrefManager.getSPToken().trim();
        String fullname = txtnamalengkap.getText().toString().trim();
        String birthplace = txttempatlahir.getText().toString().trim();
        String birthday = txttanggallahir.getText().toString().trim();
        String gender = spinnerjeniskelamin.getSelectedItem().toString().trim();
        String marital = spinnerstatus.getSelectedItem().toString().trim();
        String religion = spinneragama.getSelectedItem().toString().trim();
        String number_citizen = txtnikktp.getText().toString().trim();
        String number_taxpayer = txtnomornpwp.getText().toString().trim();
        String job = txtpekerjaan.getText().toString().trim();
        String income = txtpendapatan.getText().toString().trim();
        String parent_name = txtibukandung.getText().toString().trim();
        String family_dependent = txtjumlahtanggungan.getText().toString().trim();
        String email = txtalamatemail.getText().toString().trim();
        String phone = txtnomorhandphone.getText().toString().trim();
        String installment = spinnerkredit.getSelectedItem().toString().trim();
        String residence_status = spinnerstatusrumah.getSelectedItem().toString().trim();
        String contact_office = txtnomortlp.getText().toString().trim();
        String facebook = txtfacebook.getText().toString().trim();
        String twitter = txttwitter.getText().toString().trim();
        String instagram = txtinstagram.getText().toString().trim();

        mApiService.postDataDiri("Bearer " + sharedPrefManager.getSPToken(), fullname, birthday, birthplace, job, income, family_dependent, installment, residence_status, parent_name, contact_office, facebook
                , twitter, instagram, gender, marital, religion, number_citizen, number_taxpayer).enqueue(new Callback<ResponseMember>() {
            @Override
            public void onResponse(Call<ResponseMember> call, Response<ResponseMember> response) {

                if (response.isSuccessful()) {
                    Toast.makeText(DataDiri.this, "Data berhasil di simpan...", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(DataDiri.this, "Gagal Upload", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ResponseMember> call, Throwable t) {

            }
        });

    }

    /*Insert data member*/
    private void InsertMember() {

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        pDialog.setMessage("Loading...");
        pDialog.show();

        HashMap<String, String> params = new HashMap<>();
        params.put("id_member", sharedPrefManager.getSpIdMember());
/*        if (imgktp.equals("2")) {
            params.put("photo", getStringImage(decoded_1));
        }
        if (imgphoto.equals("2")) {
            params.put("citizen", getStringImage(decoded_2));
        }*/
        /*params.put("taxpayer", getStringImage(decoded_3));*/
        params.put("fullname", txtnamalengkap.getText().toString());
        params.put("phone", txtnomorhandphone.getText().toString());
        params.put("birthplace", txttempatlahir.getText().toString());
        params.put("birthday", txttanggallahir.getText().toString());
        params.put("gender", spinnerjeniskelamin.getSelectedItem().toString());
        params.put("marital", spinnerstatus.getSelectedItem().toString());
        params.put("religion", spinneragama.getSelectedItem().toString());
        params.put("family_dependent", txtjumlahtanggungan.getText().toString());
        params.put("installment", spinnerkredit.getSelectedItem().toString());
        params.put("residence_status", spinnerstatusrumah.getSelectedItem().toString());
        params.put("job", txtpekerjaan.getText().toString());
        params.put("income", txtpendapatan.getText().toString());
        params.put("number_citizen", txtnikktp.getText().toString());
        params.put("number_taxpayer", txtnomornpwp.getText().toString());
        params.put("parent_name", txtibukandung.getText().toString());
        params.put("email", txtalamatemail.getText().toString());
        params.put("contact_office", txtnomortlp.getText().toString());
        params.put("facebook", txtfacebook.getText().toString());
        params.put("instagram", txtinstagram.getText().toString());
        params.put("twitter", txttwitter.getText().toString());
        params.put("nonsibling_name", txtnamasaudara.getText().toString());
        params.put("nonsibling_mobile", txtnomorhandphonesaudara.getText().toString());
        params.put("nonsibling_id_geodirectory", id_kota.getText().toString());
        params.put("nonsibling_id_district", id_kecamatan.getText().toString());
        params.put("postal_code", txtkodepos_saudara.getText().toString());
        params.put("nonsibling_address", txtalamat_saudara.getText().toString());


        mApiService.InsertMember(params).enqueue(new Callback<ResponseMemberInsert>() {
            @Override
            public void onResponse(Call<ResponseMemberInsert> call, Response<ResponseMemberInsert> response) {
                pDialog.dismiss();
                if (response.body() != null) {
                    //Toast.makeText(DataDiri.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    AlertDialog alertDialog = new AlertDialog.Builder(DataDiri.this).create();

                    alertDialog.setTitle("Sukses");
                    alertDialog.setMessage("Data diri berhasil disimpan.");
                    alertDialog.setIcon(R.drawable.successfully);
                    alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            finish();

                        }
                    });

                    alertDialog.show();


                } else {
                    Toast.makeText(DataDiri.this, "Gagal update member", Toast.LENGTH_SHORT).show();
                }
            }


            @Override
            public void onFailure(Call<ResponseMemberInsert> call, Throwable t) {

            }
        });

    }

    /*Upload Foto */
    private void UploadFoto(){

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        pDialog.setMessage("Loading...");
        pDialog.show();

        HashMap<String, String> params = new HashMap<>();
        params.put("id_member", sharedPrefManager.getSpIdMember());
        params.put("photo", getStringImage(decoded_1));

        mApiService.UploadFoto(params).enqueue(new Callback<ResponseUploadImage>() {
            @Override
            public void onResponse(Call<ResponseUploadImage> call, Response<ResponseUploadImage> response) {
                pDialog.dismiss();
                if (response.body() != null) {
                    Toast.makeText(DataDiri.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(DataDiri.this, "Gagal mengunggah foto", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseUploadImage> call, Throwable t) {

            }
        });

    }

    /*Upload KTP */
    private void UploadKtp(){

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        pDialog.setMessage("Loading...");
        pDialog.show();

        HashMap<String, String> params = new HashMap<>();
        params.put("id_member", sharedPrefManager.getSpIdMember());
        params.put("citizen", getStringImage(decoded_2));

        mApiService.UploadKtp(params).enqueue(new Callback<ResponseUploadImage>() {
            @Override
            public void onResponse(Call<ResponseUploadImage> call, Response<ResponseUploadImage> response) {
                pDialog.dismiss();
                if (response.body() != null) {
                    Toast.makeText(DataDiri.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(DataDiri.this, "Gagal mengunggah foto KTP", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseUploadImage> call, Throwable t) {

            }
        });

    }

    /*Upload NPWP */
    private void UploadNpwp(){

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        pDialog.setMessage("Loading...");
        pDialog.show();

        HashMap<String, String> params = new HashMap<>();
        params.put("id_member", sharedPrefManager.getSpIdMember());
        params.put("taxpayer", getStringImage(decoded_3));

        mApiService.UploadNpwp(params).enqueue(new Callback<ResponseUploadImage>() {
            @Override
            public void onResponse(Call<ResponseUploadImage> call, Response<ResponseUploadImage> response) {
                pDialog.dismiss();
                if (response.body() != null) {

                    Toast.makeText(DataDiri.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();


                } else {
                    Toast.makeText(DataDiri.this, "Gagal mengunggah foto NPWP                                                                          ", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseUploadImage> call, Throwable t) {

            }
        });

    }


    /*Menampilkan data memebr sesuai ID*/
    private void getmemberDetail() {
        ///// loading = ProgressDialog.show(mContext, null, "Harap Tunggu...", true, false);
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        pDialog.setMessage("Loading ...");
        pDialog.show();


        mApiService.getMemberDetail().enqueue(new Callback<ResponseMembers>() {
            @Override
            public void onResponse(Call<ResponseMembers> call, Response<ResponseMembers> response) {
                pDialog.dismiss();
                if (response.body() != null) {
                    ResponseMembers responseMembers = response.body();
                    List<ResultItem> details = responseMembers.getResult();
                    for (ResultItem d : details) {
                        if (d.getId().equals(sharedPrefManager.getSpIdMember())) {
                            reqresultItem = d;

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {

                                    txtnamalengkap.setText(reqresultItem.getMetadata().getFullname());
                                    txttempatlahir.setText(reqresultItem.getMetadata().getBirthplace());
                                    txttanggallahir.setText(reqresultItem.getMetadata().getBirthday());
                                    txtpekerjaan.setText(reqresultItem.getMetadata().getJob());
                                    txtpendapatan.setText(reqresultItem.getMetadata().getIncome());
                                    txtjumlahtanggungan.setText(reqresultItem.getMetadata().getFamilyDependent());

                                    String genderData = new String(reqresultItem.getMetadata().getGender());
                                    if (genderData.equals("MALE")) {
                                        spinnerjeniskelamin.setSelection(2);
                                    } else if (genderData.equals("FEMALE")) {
                                        spinnerjeniskelamin.setSelection(1);
                                    }/*else if(genderData !=null){
                                        spinnerjeniskelamin.setSelection(0);

                                    }*/


/*                                    String Status = new String(reqresultItem.getMetadata().getMarital());
                                    if(Status.equals("LAJANG")){
                                        spinnerstatus.setSelection(1);
                                    }else if (Status.equals("MENIKAH")){
                                        spinnerstatus.setSelection(2);
                                    }else if(Status.equals("CERAI")){
                                        spinnerstatus.setSelection(3);
                                    }else if(Status.equals(null)){
                                        spinnerstatus.setSelection(0);

                                    }

                                    String Agama = new String(reqresultItem.getMetadata().getReligion());
                                    if(Agama.equals("HINDU")){
                                        spinneragama.setSelection(1);
                                    }else if (Agama.equals("ISLAM")){
                                        spinneragama.setSelection(2);
                                    }else if(Agama.equals("KRISTEN")){
                                        spinneragama.setSelection(3);

                                    }else if(Agama.equals("KATOLIK")){
                                        spinneragama.setSelection(4);

                                    }else if(Agama.equals("BUDHA")){
                                        spinneragama.setSelection(4);
                                    }else if(Agama.equals(null)){
                                        spinneragama.setSelection(0);
                                    }

                                    String StatusTempatTinggal = new String(reqresultItem.getMetadata().getResidenceStatus());
                                    if(StatusTempatTinggal.equals("KONTRAK")){
                                        spinnerstatusrumah.setSelection(1);
                                    }else if (StatusTempatTinggal.equals("RUMAH SENDIRI")){
                                        spinnerstatusrumah.setSelection(2);
                                    }else if(StatusTempatTinggal.equals("KOS")){
                                        spinnerstatusrumah.setSelection(3);

                                    }else if(StatusTempatTinggal.equals("IKUT ORANG TUA")){
                                        spinnerstatusrumah.setSelection(4);
                                    }else if(StatusTempatTinggal.equals(null)){
                                        spinnerstatusrumah.setSelection(0);
                                    }

                                    String TanggunganKredit = new String(reqresultItem.getMetadata().getInstallment());
                                    if(TanggunganKredit.equals("YES")){
                                        spinnerkredit.setSelection(1);
                                    }else if (TanggunganKredit.equals("NO")){
                                        spinnerkredit.setSelection(2);
                                    }else if (TanggunganKredit.equals(null)){
                                        spinnerkredit.setSelection(0);
                                    }*/

                                    txtnikktp.setText(reqresultItem.getMetadata().getNumberCitizen());
                                    txtnomornpwp.setText(reqresultItem.getMetadata().getNumberTaxpayer());
                                    txtibukandung.setText(reqresultItem.getMetadata().getParentName());
                                    txtnomortlp.setText(reqresultItem.getMetadata().getContactOffice());
                                    txtfacebook.setText(reqresultItem.getMetadata().getFacebook());
                                    txttwitter.setText(reqresultItem.getMetadata().getTwitter());
                                    txtinstagram.setText(reqresultItem.getMetadata().getInstagram());
                                    txtnamasaudara.setText(reqresultItem.getMetadata().getNonsiblingName());
                                    txtnomorhandphonesaudara.setText(reqresultItem.getMetadata().getNonsiblingMobile());
                                    txtalamat_saudara.setText(reqresultItem.getMetadata().getNonsiblingAddress());
                                    txtkodepos_saudara.setText(reqresultItem.getMetadata().getPostalCode());

                                    /*set URL*/
                                    Uri.Builder builder = new Uri.Builder();
                                    builder.scheme("https")
                                            .authority("development.kreditimpian.com")
                                            .appendPath("images")
                                            .appendPath("members");
                                    String myUrl = builder.build().toString();

                                    Glide.with(DataDiri.this)
                                            .load(reqresultItem.getImage())
                                            ///.placeholder(R.drawable.icon_user)
                                            // .error(R.drawable.icon_user)
                                            .into(imageself);

                                    Glide.with(DataDiri.this)
                                            .load("https://development.kreditimpian.com/images/members/" + reqresultItem.getMetadata().getCitizen())
                                            //.placeholder(R.drawable.upload)
                                            ///.error(R.drawable.upload)
                                            .into(imagektp);

                                    Glide.with(DataDiri.this)
                                            .load("https://development.kreditimpian.com/images/members/" + reqresultItem.getMetadata().getTaxpayer())
                                            //.placeholder(R.drawable.upload)
                                            ///.error(R.drawable.upload)
                                            .into(imagenpwp);
//
//


//                                    List<String> userType = new ArrayList<String>();
//                                    userType.add(reqresultItem.getMetadata().getGender());
//                                    userType.add("FEMALE");
//                                    userType.add("MALE");
//
//
//                                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(DataDiri.this,
//                                                android.R.layout.simple_spinner_item,userType );
//                                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//
//                                        spinnerjeniskelamin.setAdapter(adapter);


                                }
                            });
                        }/*else {
                            Toast.makeText(DataDiri.this, "Silahkan isi form data diri", Toast.LENGTH_SHORT).show();
                        }*/

                    }


                } else {
                    /// loading.dismiss();
                    Toast.makeText(DataDiri.this, "Gagal mengambil data, silahkan ulangi lagi", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseMembers> call, Throwable t) {
                Toast.makeText(DataDiri.this, "Koneksi Anda bermasalah,silahkan ulangi lagi", Toast.LENGTH_LONG).show();
                onBackPressed();
            }
        });


    }

    private void getDetailMember() {

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        pDialog.setMessage("Tunggu...");
        pDialog.show();


        HashMap<String, String> params = new HashMap<>();
        params.put("id_member", sharedPrefManager.getSpIdMember());

        mApiService.getDetailMember(params).enqueue(new Callback<ResponseDetailMember>() {
            @Override
            public void onResponse(Call<ResponseDetailMember> call, Response<ResponseDetailMember> response) {

                pDialog.dismiss();
                if (response.body().getResponseCode() == 200) {
                    ResponseDetailMember responseDetailMember = response.body();
                    List<com.application.kreditimpian.Model.ModelDetailMember.DataItem> detail = responseDetailMember.getData();

                    txtnamalengkap.setText(detail.get(0).getFullname());
                    txttempatlahir.setText(detail.get(0).getBirthplace());
                    txttanggallahir.setText(detail.get(0).getBirthday());
                    txtpekerjaan.setText(detail.get(0).getJob());
                    txtpendapatan.setText(detail.get(0).getIncome());
                    txtjumlahtanggungan.setText(detail.get(0).getFamilyDependent());
                    String genderData = new String(detail.get(0).getGender());
                    if (genderData.equals("MALE")) {
                        spinnerjeniskelamin.setSelection(2);
                    } else if (genderData.equals("FEMALE")) {
                        spinnerjeniskelamin.setSelection(1);
                    } else if (genderData.equals("Jenis Kelamin")) {
                        spinnerjeniskelamin.setSelection(0);
                    }

                    String Status = new String(detail.get(0).getMarital());
                    if (Status.equals("LAJANG")) {
                        spinnerstatus.setSelection(1);
                    } else if (Status.equals("MENIKAH")) {
                        spinnerstatus.setSelection(2);
                    } else if (Status.equals("CERAI")) {
                        spinnerstatus.setSelection(3);
                    } /*else if (Status.equals(null)) {
                        spinnerstatus.setSelection(0);

                    }*/

                    String Agama = new String(detail.get(0).getReligion());
                    if (Agama.equals("HINDU")) {
                        spinneragama.setSelection(1);
                    } else if (Agama.equals("ISLAM")) {
                        spinneragama.setSelection(2);
                    } else if (Agama.equals("KRISTEN")) {
                        spinneragama.setSelection(3);

                    } else if (Agama.equals("KATOLIK")) {
                        spinneragama.setSelection(4);

                    } else if (Agama.equals("BUDHA")) {
                        spinneragama.setSelection(4);
                    } /*else if (Agama.equals(null)) {
                        spinneragama.setSelection(0);
                    }*/

                    String StatusTempatTinggal = new String(detail.get(0).getResidenceStatus());
                    if (StatusTempatTinggal.equals("KONTRAK")) {
                        spinnerstatusrumah.setSelection(1);
                    } else if (StatusTempatTinggal.equals("RUMAH SENDIRI")) {
                        spinnerstatusrumah.setSelection(2);
                    } else if (StatusTempatTinggal.equals("KOS")) {
                        spinnerstatusrumah.setSelection(3);

                    } else if (StatusTempatTinggal.equals("IKUT ORANG TUA")) {
                        spinnerstatusrumah.setSelection(4);
                    } /*else if (StatusTempatTinggal.equals(null)) {
                        spinnerstatusrumah.setSelection(0);
                    }*/

                    String TanggunganKredit = new String(detail.get(0).getInstallment());
                    if (TanggunganKredit.equals("YES")) {
                        spinnerkredit.setSelection(1);
                    } else if (TanggunganKredit.equals("NO")) {
                        spinnerkredit.setSelection(2);
                    }

                    txtnikktp.setText(detail.get(0).getNumberCitizen());
                    txtnomornpwp.setText(detail.get(0).getNumberTaxpayer());
                    txtibukandung.setText(detail.get(0).getParentName());
                    txtnomortlp.setText(detail.get(0).getContactOffice());
                    txtfacebook.setText(detail.get(0).getFacebook());
                    txttwitter.setText(detail.get(0).getTwitter());
                    txtinstagram.setText(detail.get(0).getInstagram());
                    txtnamasaudara.setText(detail.get(0).getNonsiblingName());
                    txtnomorhandphonesaudara.setText(detail.get(0).getNonsiblingMobile());
                    txtalamat_saudara.setText(detail.get(0).getNonsiblingAddress());
                    txtkodepos_saudara.setText(detail.get(0).getPostalCode());

                    Glide.with(DataDiri.this)
                            .load(detail.get(0).getPhoto())
                            .into(imageself);

                  // imgphoto = "1";
                    Glide.with(DataDiri.this)
                            .load(detail.get(0).getCitizen())
                            .into(imagektp);

                   /// imgktp = "1";
                    Glide.with(DataDiri.this)
                            .load(detail.get(0).getTaxpayer())
                            .into(imagenpwp);

                } else {
                    Toast.makeText(DataDiri.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ResponseDetailMember> call, Throwable t) {
                Toast.makeText(DataDiri.this, "Keneksi terputus", Toast.LENGTH_LONG);
            }
        });

    }

    /*Menampilkan data  City*/
    private void getGeoCity() {

        cityvalues = new HashMap<>();
        mApiService.getCity().enqueue(new Callback<ResponseGeodirectory>() {
            @Override
            public void onResponse(Call<ResponseGeodirectory> call, Response<ResponseGeodirectory> response) {
                if (response.body() != null) {
                    //// String citySelected = spinnerkota_pengiriman.getItemAtPosition(p).toString();
                    List<com.application.kreditimpian.Model.ModelGeodirectory.DataItem> getCity = response.body().getData();
                    List<String> listSpinner = new ArrayList<String>();
                    String[] idcity = new String[getCity.size() + 1];
                    String[] city = new String[getCity.size() + 1];
                    city[0] = "-- Pilih Kota --";
                    for (int i = 0; i < getCity.size(); i++) {
                        ///listSpinner.add(getCity.get(i).getIdParent());
                        city[i + 1] = getCity.get(i).getName();
                        idcity[i + 1] = getCity.get(i).getId();
                        cityvalues.put(city[i + 1], idcity[i + 1]);
//                         id = getCity.get(i).getId();
//                         nameCity = getCity.get(i).getName();
                        ///listSpinner.add(nameCity);

                    }
                    // Set hasil result json ke dalam adapter spinner
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(DataDiri.this,
                            android.R.layout.simple_spinner_item, city);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerkota_saudaraa.setAdapter(adapter);
                    spinnerkota_saudaraa.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                            if (position > 0) {
                                String cityvalues = getCity.get(position - 1).getId();
                                id_kota.setText(cityvalues);
                                getKecamatan();
                                ///Toast.makeText(TambahAlamatPengiriman.this, " ini id City "+cityvalues, Toast.LENGTH_LONG).show();

                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });


                } else {
                    loading.dismiss();
                    Toast.makeText(DataDiri.this, "Gagal mengambil data ", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseGeodirectory> call, Throwable t) {

            }
        });


    }

    /*Menampilkan data  Kecamatan*/
    private void getKecamatan() {

        Kecamatanvalues = new HashMap<>();
        HashMap<String, String> params = new HashMap<>();
        params.put("id_kota", id_kota.getText().toString());
        mApiService.getKecamatan(params).enqueue(new Callback<ResponseKecamatan>() {
            @Override
            public void onResponse(Call<ResponseKecamatan> call, Response<ResponseKecamatan> response) {
                if (response.body() != null) {
                    //// String citySelected = spinnerkota_pengiriman.getItemAtPosition(p).toString();
                    List<com.application.kreditimpian.Model.ModelKecamatan.DataItem> getKecamatan = response.body().getData();
                    List<String> listSpinner = new ArrayList<String>();
                    String[] idKecamatan = new String[getKecamatan.size() + 1];
                    String[] Kecamatan = new String[getKecamatan.size() + 1];
                    Kecamatan[0] = "-- Pilih Kecamatan --";
                    for (int i = 0; i < getKecamatan.size(); i++) {
                        ///listSpinner.add(getCity.get(i).getIdParent());
                        Kecamatan[i + 1] = getKecamatan.get(i).getName();
                        idKecamatan[i + 1] = getKecamatan.get(i).getId();
                        Kecamatanvalues.put(Kecamatan[i + 1], idKecamatan[i + 1]);
//                         id = getCity.get(i).getId();
//                         nameCity = getCity.get(i).getName();
                        ///listSpinner.add(nameCity);

                    }
                    // Set hasil result json ke dalam adapter spinner
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(DataDiri.this,
                            android.R.layout.simple_spinner_item, Kecamatan);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerkecamatn_saudara.setAdapter(adapter);
                    spinnerkecamatn_saudara.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                            if (position > 0) {
                                String Kecamatanvalues = getKecamatan.get(position - 1).getId();
                                id_kecamatan.setText(Kecamatanvalues);
                                ///Toast.makeText(TambahAlamatPengiriman.this, " ini id City "+cityvalues, Toast.LENGTH_LONG).show();

                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });


                } else {
                    loading.dismiss();
                    Toast.makeText(DataDiri.this, "Gagal mengambil data ", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseKecamatan> call, Throwable t) {

            }
        });


    }


/*    private void getKotaKecamatan(){

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
                    Toast.makeText(DataDiri.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseKotaKecamatan> call, Throwable t) {

            }
        });

    }*/


    /*set Permision*/
    private void requestPermission() {

        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.CAMERA},
                PERMISSION_REQUEST_CODE);
    }

    //untuk memilih gambar dari galeri
    private void showFileChooser1() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST_1);
    }

    //untuk memilih gambar dari galeri
    private void showFileChooser2() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST_2);
    }

    //untuk memilih gambar dari galeri
    private void showFileChooser3() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST_3);
    }

    //untuk set ke imageview
    private void setToImageView1(Bitmap bmp) {
        //compress image
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, bitmap_size, bytes);
        decoded_1 = BitmapFactory.decodeStream(new ByteArrayInputStream(bytes.toByteArray()));
        //menampilkan gambar yang dipilih dari camera/gallery ke ImageView
        imageself.setImageBitmap(decoded_1);

    }

    //untuk set ke imageview
    private void setToImageView2(Bitmap bmp) {
        //compress image
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, bitmap_size, bytes);
        decoded_2 = BitmapFactory.decodeStream(new ByteArrayInputStream(bytes.toByteArray()));
        //menampilkan gambar yang dipilih dari camera/gallery ke ImageView
        imagektp.setImageBitmap(decoded_2);
    }

    //untuk set ke imageview
    private void setToImageView3(Bitmap bmp) {
        //compress image
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, bitmap_size, bytes);
        decoded_3 = BitmapFactory.decodeStream(new ByteArrayInputStream(bytes.toByteArray()));
        //menampilkan gambar yang dipilih dari camera/gallery ke ImageView
        imagenpwp.setImageBitmap(decoded_3);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //ini untuk gambar
        if (requestCode == PICK_IMAGE_REQUEST_1 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            try {
                //mengambil fambar dari Gallery
                bitmap = MediaStore.Images.Media.getBitmap(DataDiri.this.getContentResolver(), filePath);
                // 512 adalah resolusi tertinggi setelah image di resize, bisa di ganti.
                setToImageView1(getResizedBitmap(bitmap, 512));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (requestCode == PICK_IMAGE_REQUEST_2 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            try {
                //mengambil fambar dari Gallery
                bitmap = MediaStore.Images.Media.getBitmap(DataDiri.this.getContentResolver(), filePath);
                // 512 adalah resolusi tertinggi setelah image di resize, bisa di ganti.
                setToImageView2(getResizedBitmap(bitmap, 512));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (requestCode == PICK_IMAGE_REQUEST_3 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            try {
                //mengambil fambar dari Gallery
                bitmap = MediaStore.Images.Media.getBitmap(DataDiri.this.getContentResolver(), filePath);
                // 512 adalah resolusi tertinggi setelah image di resize, bisa di ganti.
                setToImageView3(getResizedBitmap(bitmap, 512));
            } catch (IOException e) {
                e.printStackTrace();
            }
            //ini untuk dari kamera
        } else if (requestCode == REQUEST_IMAGE_CAPTURE_1 && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            //imageView.setImageBitmap(imageBitmap);
            setToImageView1(getResizedBitmap(imageBitmap, 512));
        } else if (requestCode == REQUEST_IMAGE_CAPTURE_2 && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            //imageView.setImageBitmap(imageBitmap);
            setToImageView2(getResizedBitmap(imageBitmap, 512));
        } else if (requestCode == REQUEST_IMAGE_CAPTURE_3 && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            //imageView.setImageBitmap(imageBitmap);
            setToImageView3(getResizedBitmap(imageBitmap, 512));
        }
    }

    // fungsi resize image
    public Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float) width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
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

    //untuk upload image, compress .JPEG ke bitmap
    private String getStringImage(Bitmap bmp) {
        if (bmp != null) {

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.JPEG, bitmap_size, baos);
            byte[] imageBytes = baos.toByteArray();
//        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
/*            imgktp = "2";
            imgphoto ="2";*/

            return Base64.encodeToString(imageBytes, Base64.DEFAULT);

        }
        return "";
    }
}
