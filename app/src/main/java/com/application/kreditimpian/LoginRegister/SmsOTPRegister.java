package com.application.kreditimpian.LoginRegister;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.application.kreditimpian.Api.SharedPrefManager;
import com.application.kreditimpian.Api.api_v2.BaseApiService;
import com.application.kreditimpian.Api.api_v2.UtilsApi;
import com.application.kreditimpian.MenuUtama.MenuUtama;
import com.application.kreditimpian.Model.ModelValidationSMS.ResponseOTP;
import com.application.kreditimpian.Model.ModelValidationSMS.ResponseSmsOTP;
import com.application.kreditimpian.R;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.text.TextUtils.isEmpty;

public class SmsOTPRegister extends AppCompatActivity {
    SharedPrefManager sharedPrefManager;
    TextView tvnomortelpon;
    BaseApiService mApiService;
    private String username, email, phone;
    private String KEY_USERNAME = "username";
    private String KEY_EMAIL = "email";
    private String KEY_NOMORHP = "phone" ;

    EditText txtkodeotp, txtfullname;
    Button btnverifikasi;
    ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms_otpregister);
        setActionBarTitle("Verifikasi Akun");
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);// set drawable icon
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mApiService = UtilsApi.getAPIService();
        tvnomortelpon = findViewById(R.id.tvnomortelpon);

        Bundle extras = getIntent().getExtras();
        username = extras.getString(KEY_USERNAME);
        email = extras.getString(KEY_EMAIL);
        phone = extras.getString(KEY_NOMORHP);

        tvnomortelpon.setText(phone);

        txtkodeotp = findViewById(R.id.txtkodeotp);
        txtfullname = findViewById(R.id.txtfullname);
        txtfullname.setText(username);
        btnverifikasi = findViewById(R.id.btnverifikasi);
        btnverifikasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String kodeotp = txtkodeotp.getText().toString();
                if (isEmpty(kodeotp))
                    txtkodeotp.setError("Masukan Kode OTP");
                else
                SMSOTPValidation();
            }
        });

    }

    public void SMSOTPValidation(){

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        pDialog.setMessage("Verifikasi akun...");
        pDialog.show();


        HashMap<String, String> params = new HashMap<>();
        params.put("fullname", txtfullname.getText().toString());
        params.put("number", txtkodeotp.getText().toString());

        Call<ResponseSmsOTP> Validation = mApiService.getValidation(params) ;
        Validation.enqueue(new Callback<ResponseSmsOTP>() {
            @Override
            public void onResponse(Call<ResponseSmsOTP> call, Response<ResponseSmsOTP> response) {
                pDialog.dismiss();

                try {
                    if(response.body()!=null)
                        Toast.makeText(SmsOTPRegister.this, response.body().getMessage(), Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(SmsOTPRegister.this, LoginUser.class);
                    startActivity(intent);
                    finish();
                }catch (Exception e ){
                    e.printStackTrace();
                }
//
//                if (response.body().getResponseCode()==201) {
//                    Toast.makeText(SmsOTPRegister.this, response.body().getMessage(), Toast.LENGTH_LONG).show();
//                } else {
//                    Toast.makeText(SmsOTPRegister.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
//                }

            }

            @Override
            public void onFailure(Call<ResponseSmsOTP> call, Throwable t) {
                pDialog.dismiss();
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

}
