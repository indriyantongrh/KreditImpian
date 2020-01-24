package com.application.kreditimpian.Akun;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
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
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.application.kreditimpian.Api.JSONResponse;
import com.application.kreditimpian.Api.RequestInterface;
import com.application.kreditimpian.Api.SharedPrefManager;
import com.application.kreditimpian.Api.api_v2.BaseApiService;
import com.application.kreditimpian.Api.api_v2.RetrofitClient;
import com.application.kreditimpian.Api.api_v2.UtilsApi;
import com.application.kreditimpian.BuildConfig;
import com.application.kreditimpian.LoginRegister.Register;
import com.application.kreditimpian.Model.ModelGeodirectories.ResponseGeodirectories;
import com.application.kreditimpian.Model.ModelMember.ResponseMember;

import com.application.kreditimpian.Model.ModelUser.UserResponse;
import com.application.kreditimpian.Model.ModelUserDetail.ResponseMembers;
import com.application.kreditimpian.Model.ModelUserDetail.ResultItem;
import com.application.kreditimpian.R;
import com.application.kreditimpian.ResponseMessage.ResponseRegister;
import com.bumptech.glide.Glide;
import com.fasterxml.jackson.databind.ser.Serializers;
import com.google.android.gms.common.internal.Objects;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;

import static com.application.kreditimpian.Api.SharedPrefManager.SP_TOKEN;
import static com.application.kreditimpian.Api.api_v2.UtilsApi.BASE_URL_API;

public class DataDiri extends AppCompatActivity implements View.OnClickListener {
    private static final int PERMISSION_REQUEST_CODE = 200;
    private int mYear, mMonth, mDay;
    ImageButton btnback;
    ImageView imagektp,imagenpwp, imageself;
    Spinner spinnerjeniskelamin,spinnerstatus,spinneragama,spinnerstatusrumah,spinnerkredit,spinnerkota_saudaraa,spinnerkecamatn_saudara;
    Button btnsimpan;
    EditText txtnamalengkap,txttempatlahir,txttanggallahir,txtnikktp,txtnomornpwp,txtpekerjaan,txtpendapatan,
            txtjumlahtanggungan,txtalamatemail,txtibukandung,txtnomorhandphone,txtnomortlp,txtfacebook,txttwitter,txtinstagram,
            txtnamasaudara,txtnomorhandphonesaudara,txtkodepos_saudara, txtalamat_saudara;

    ProgressDialog loading;
    SharedPrefManager sharedPrefManager;
    Context mContext;
    BaseApiService mApiService;
    String fullname, idprofile;

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
        String idprofile = sharedPrefManager.getSpIdprofile();
        Log.d("Id Profile Data diri ", idprofile);





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
        btnsimpan = findViewById(R.id.btnsimpan);

        ///formdatasaudara tidaq serumah

         txtnamasaudara = findViewById(R.id.txtnamasaudara);
         txtnomorhandphonesaudara= findViewById(R.id.txtnomorhandphonesaudara);
         spinnerkota_saudaraa = findViewById(R.id.spinnerkota_saudaraa);
         spinnerkecamatn_saudara = findViewById(R.id.spinnerkecamatn_saudara);
         txtkodepos_saudara= findViewById(R.id.txtkodepos_saudara);
         txtalamat_saudara= findViewById(R.id.txtalamat_saudara);

        txtalamatemail.setText(email);
        txtnomorhandphone.setText(msisdn);

        /// spinner tagihan perbulan
        String [] values =
                {"Jenis Kelamin","FEMALE","MALE"};

        List<String> gender = new ArrayList<String>();
        gender.add(0, "Jenis Kelamin");
        gender.add("FEMALE");
        gender.add("MALE");

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(DataDiri.this, android.R.layout.simple_spinner_item, gender);
            adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
            spinnerjeniskelamin.setAdapter(adapter);

        getGeoDistrictSaudara();
        getGeoCitySaudara();
        getmemberDetail();

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

            }
        });

        btnsimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//
//                //mengambil data dari edittext
//                String fullname = txtnamalengkap.getText().toString();
//                String birthplace = txttempatlahir.getText().toString();
//                String birthday = txttanggallahir.getText().toString();
//                String gender = spinnerjeniskelamin.toString();
//                String marital = spinnerstatus.toString();
//                String religion = spinneragama.toString();
//                String number_citizen = txtnikktp.getText().toString();
//                String number_taxpayer = txtnomornpwp.getText().toString();
//                String job = txtpekerjaan.getText().toString();
//                String income = txtpendapatan.getText().toString();
//                String family_dependent = txtjumlahtanggungan.getText().toString();
//                String email = txtalamatemail.getText().toString();
//                String phone = txtnomorhandphone.getText().toString();
//                String installment = spinnerkredit.toString();
//                String residence_status = spinnerstatusrumah.toString();
//                String contact_office = txtnomortlp.getText().toString();
//                String facebook = txtfacebook.getText().toString();
//                String twitter = txttwitter.getText().toString();
//                String instagram = txtinstagram.getText().toString();

               /// updatemember();
               //getMember();
                ///getmemberDetail();

            }
        });

        ///getMember();

        txttanggallahir.setOnClickListener(this);
        btnback = findViewById(R.id.btnback);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        if (ContextCompat.checkSelfPermission(DataDiri.this,
                Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED){

        }else {
            requestPermission();
        }

   }


        @Override
    public void onBackPressed() {
        finish();
    }

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


    private void updatemember(){
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

        mApiService.postDataDiri("Bearer " + sharedPrefManager.getSPToken(), fullname,birthday ,birthplace,job,income, family_dependent, installment,residence_status , parent_name, contact_office,facebook
               ,twitter , instagram,gender ,marital ,religion ,number_citizen ,number_taxpayer).enqueue(new Callback<ResponseMember>() {
            @Override
            public void onResponse(Call<ResponseMember> call, Response<ResponseMember> response) {

                if (response.isSuccessful()){
                    Toast.makeText(DataDiri.this, "Data berhasil di simpan...", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(DataDiri.this, "Gagal Upload", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ResponseMember> call, Throwable t) {

            }
        });

    }


    private void getmemberDetail(){
       /// loading = ProgressDialog.show(mContext, null, "Harap Tunggu...", true, false);

        mApiService.getMemberDetail().enqueue(new Callback<ResponseMembers>() {
            @Override
            public void onResponse(Call<ResponseMembers> call, Response<ResponseMembers> response) {
                if(response.body() !=null){
                    ResponseMembers responseMembers = response.body();
                    List<ResultItem> details = responseMembers.getResult();
                    for(ResultItem d : details){
                        if(d.getId().equals(sharedPrefManager.getSpIdMember())){
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
                                    txtnikktp.setText(reqresultItem.getMetadata().getNumberCitizen());
                                    txtnomornpwp.setText(reqresultItem.getMetadata().getNumberTaxpayer());
                                    txtibukandung.setText(reqresultItem.getMetadata().getParentName());
                                    txtnomortlp.setText(reqresultItem.getMetadata().getContactOffice());
                                    txtfacebook.setText(reqresultItem.getMetadata().getFacebook());
                                    txttwitter.setText(reqresultItem.getMetadata().getTwitter());
                                    txtinstagram.setText(reqresultItem.getMetadata().getInstagram());
                                    Glide.with(DataDiri.this)
                                            .load(reqresultItem.getImage())
                                            .placeholder(R.drawable.icon_user)
                                            .error(R.drawable.icon_user)
                                            .into(imageself);

                                    Glide.with(DataDiri.this)
                                            .load("https://development.kreditimpian.id/images/members/"+reqresultItem.getMetadata().getTaxpayer())
                                            .placeholder(R.drawable.upload)
                                            .error(R.drawable.upload)
                                            .into(imagenpwp);


                                    Glide.with(DataDiri.this)
                                            .load("https://development.kreditimpian.id/images/members/"+reqresultItem.getMetadata().getCitizen())
                                            .placeholder(R.drawable.upload)
                                            .error(R.drawable.upload)
                                            .into(imagektp);

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
                        }

                    }


                }else {
                   /// loading.dismiss();
                    Toast.makeText(mContext, "Gagal Refresh", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseMembers> call, Throwable t) {
                Toast.makeText(DataDiri.this, "Koneksi Anda bermasalah", Toast.LENGTH_LONG).show();
            }
        });


    }

    private void getGeoCitySaudara(){

        mApiService.getGeoCity().enqueue(new Callback<ResponseGeodirectories>() {
            @Override
            public void onResponse(Call<ResponseGeodirectories> call, Response<ResponseGeodirectories> response) {
               if(response.body() !=null){
                   List<com.application.kreditimpian.Model.ModelGeodirectories.ResultItem> getCity = response.body().getResult();
                   List<String> listSpinner = new ArrayList<String>();
                   for (int i = 0; i < getCity.size(); i++){
                       listSpinner.add(getCity.get(i).getName());
                   }
                   // Set hasil result json ke dalam adapter spinner
                   ArrayAdapter<String> adapter = new ArrayAdapter<String>(DataDiri.this,
                           android.R.layout.simple_spinner_item, listSpinner);
                   adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                   spinnerkota_saudaraa.setAdapter(adapter);
               } else {
                   loading.dismiss();
                   Toast.makeText(mContext, "Gagal mengambil data dosen", Toast.LENGTH_SHORT).show();
               }
            }

            @Override
            public void onFailure(Call<ResponseGeodirectories> call, Throwable t) {

            }
        });


    }

    private void getGeoDistrictSaudara(){

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
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(DataDiri.this,
                            android.R.layout.simple_spinner_item, listSpinner);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerkecamatn_saudara.setAdapter(adapter);
                } else {
                    loading.dismiss();
                    Toast.makeText(mContext, "Gagal mengambil data dosen", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseGeodirectories> call, Throwable t) {

            }
        });


    }


//    private void getMember(){
//
//        mApiService.getMemberDetail(sharedPrefManager.getSpIdprofile()).enqueue(new Callback<ResponseMembers>() {
//
//            @Override
//            public void onResponse(Call<ResponseMembers> call, Response<ResponseMembers> response) {
//
//
//                if (response.isSuccessful()) {
//
//                    ResponseMembers responseMembers = response.body();
//                    List<ResultItem> detail = responseMembers.getResult();
//                    Log.d("Member Detail", detail.toString());
//
//
//                   for (ResultItem d : detail){
//                       if(d.getId().equals(sharedPrefManager.getSpIdprofile()));
//                        resultItem =d;
//
//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                              txtibukandung.setText(resultItem.getMetadata().getParentName());
//
//                            }
//                        });
//
//                   }
//
//                   // final List<ResultItem> Detailmember = Arrays.asList(response.body().getResult());
//
//
//                    //ResultItem userResponse = response.body().getResult();
//                    ///txtnamalengkap.setText(Detailmember.);
//
//                }
//            }
//            @Override
//            public void onFailure(Call call, Throwable t) {
//                /*
//                Error callback
//                */
//            }
//        });
//
//    }

//    private void requestRegister(){
//
//        //membuat progress dialog
//        loading = new ProgressDialog(this);
//        loading.setCancelable(false);
//        loading.setMessage("Update Data Diri ...");
//        loading.show();
//
//
//        //mengambil data dari edittext
//        String token = sharedPrefManager.getSPToken().trim();
//        String fullname = txtnamalengkap.getText().toString().trim();
//        String birthplace = txttempatlahir.getText().toString().trim();
//        String birthday = txttanggallahir.getText().toString().trim();
//        String gender = spinnerjeniskelamin.getSelectedItem().toString().trim();
//        String marital = spinnerstatus.getSelectedItem().toString().trim();
//        String religion = spinneragama.getSelectedItem().toString().trim();
//        String number_citizen = txtnikktp.getText().toString().trim();
//        String number_taxpayer = txtnomornpwp.getText().toString().trim();
//        String job = txtpekerjaan.getText().toString().trim();
//        String income = txtpendapatan.getText().toString().trim();
//        String parent_name = txtibukandung.getText().toString().trim();
//        String family_dependent = txtjumlahtanggungan.getText().toString().trim();
//        String email = txtalamatemail.getText().toString().trim();
//        String phone = txtnomorhandphone.getText().toString().trim();
//        String installment = spinnerkredit.getSelectedItem().toString().trim();
//        String residence_status = spinnerstatusrumah.getSelectedItem().toString().trim();
//        String contact_office = txtnomortlp.getText().toString().trim();
//        String facebook = txtfacebook.getText().toString().trim();
//        String twitter = txttwitter.getText().toString().trim();
//        String instagram = txtinstagram.getText().toString().trim();
//
//        OkHttpClient.Builder okhttpBuilder = new OkHttpClient.Builder();
//        okhttpBuilder.addInterceptor(new Interceptor() {
//            @NotNull
//            @Override
//            public okhttp3.Response intercept(@NotNull Chain chain) throws IOException {
//
//                Request request = chain.request();
//                Request.Builder newRequest = request.newBuilder()
//                        .addHeader("Authorization","Bearer " + SP_TOKEN);
//
//                return chain.proceed(newRequest.build());
//
//            }
//        });
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(BuildConfig.BASE_URL).client(okhttpBuilder.build())
//                .addConverterFactory(GsonConverterFactory.create(new Gson())).build();
//
//        BaseApiService api = retrofit.create(BaseApiService.class);
//
//
//        Call<ResponseMember> call = api.postDataDiri(fullname,birthday ,birthplace,job,income, family_dependent, installment,residence_status , parent_name, contact_office,facebook
//               ,twitter , instagram,gender ,marital ,religion ,number_citizen ,number_taxpayer);
//
//        call.enqueue(new Callback<ResponseMember>() {
//                    @Override
//                    public void onResponse(Call<ResponseMember> call, Response<ResponseMember> response) {
//
//                        loading.dismiss();
//
//
//                        if (response.isSuccessful()){
//                            ///Toast.makeText(Register.this, "Registrasi berhasil, silahkan login.", Toast.LENGTH_SHORT).show();
//                            Toast.makeText(DataDiri.this , "Update Data Member Berhasil", Toast.LENGTH_SHORT).show();
//                            finish();
//                        } else {
//                            Toast.makeText(DataDiri.this, "Gagal Update data.", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<ResponseMember> call, Throwable t) {
//                        Toast.makeText(mContext, "Koneksi Internet Bermasalah", Toast.LENGTH_SHORT).show();
//                        loading.hide();
//                    }
//                });
//    }


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
        imagektp.setImageBitmap(decoded_1);
    }

    //untuk set ke imageview
    private void setToImageView2(Bitmap bmp) {
        //compress image
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, bitmap_size, bytes);
        decoded_2 = BitmapFactory.decodeStream(new ByteArrayInputStream(bytes.toByteArray()));
        //menampilkan gambar yang dipilih dari camera/gallery ke ImageView
        imagenpwp.setImageBitmap(decoded_2);
    }

    //untuk set ke imageview
    private void setToImageView3(Bitmap bmp) {
        //compress image
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, bitmap_size, bytes);
        decoded_3 = BitmapFactory.decodeStream(new ByteArrayInputStream(bytes.toByteArray()));
        //menampilkan gambar yang dipilih dari camera/gallery ke ImageView
        imageself.setImageBitmap(decoded_3);
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
        }else if (requestCode == PICK_IMAGE_REQUEST_2 && resultCode == RESULT_OK && data != null && data.getData() != null) {
                Uri filePath = data.getData();
                try {
                    //mengambil fambar dari Gallery
                    bitmap = MediaStore.Images.Media.getBitmap(DataDiri.this.getContentResolver(), filePath);
                    // 512 adalah resolusi tertinggi setelah image di resize, bisa di ganti.
                    setToImageView2(getResizedBitmap(bitmap, 512));
                } catch (IOException e) {
                    e.printStackTrace();
                }       }else if (requestCode == PICK_IMAGE_REQUEST_3 && resultCode == RESULT_OK && data != null && data.getData() != null) {
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
        }else if (requestCode == REQUEST_IMAGE_CAPTURE_2 && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            //imageView.setImageBitmap(imageBitmap);
            setToImageView2(getResizedBitmap(imageBitmap, 512));
        }else if (requestCode == REQUEST_IMAGE_CAPTURE_3 && resultCode == RESULT_OK) {
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

}
