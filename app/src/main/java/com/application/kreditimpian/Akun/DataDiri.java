package com.application.kreditimpian.Akun;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.application.kreditimpian.Api.RequestInterface;
import com.application.kreditimpian.Api.SharedPrefManager;
import com.application.kreditimpian.Api.api_v2.BaseApiService;
import com.application.kreditimpian.BuildConfig;
import com.application.kreditimpian.LoginRegister.Register;
import com.application.kreditimpian.Model.ModelMember.ResponseMember;
import com.application.kreditimpian.R;
import com.application.kreditimpian.ResponseMessage.ResponseRegister;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Calendar;
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

public class DataDiri extends AppCompatActivity implements View.OnClickListener {
    private int mYear, mMonth, mDay;
    ImageButton btnback;
    Spinner spinnerjeniskelamin,spinnerstatus,spinneragama,spinnerstatusrumah,spinnerkredit;
    Button btnsimpan;
    EditText txtnamalengkap,txttempatlahir,txttanggallahir,txtnikktp,txtnomornpwp,txtpekerjaan,txtpendapatan,
            txtjumlahtanggungan,txtalamatemail,txtibukandung,txtnomorhandphone,txtnomortlp,txtfacebook,txttwitter,txtinstagram;

    ProgressDialog loading;
    SharedPrefManager sharedPrefManager;
    Context mContext;
    BaseApiService mApiService;
    String fullname;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_diri);

        sharedPrefManager = new SharedPrefManager(DataDiri.this);
        String token = sharedPrefManager.getSPToken();

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

                updatemember();

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




}
