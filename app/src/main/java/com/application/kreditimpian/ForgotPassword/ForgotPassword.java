package com.application.kreditimpian.ForgotPassword;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.application.kreditimpian.Akun.DataDiri;
import com.application.kreditimpian.Api.api_v2.BaseApiService;
import com.application.kreditimpian.Api.api_v2.UtilsApi;
import com.application.kreditimpian.LoginRegister.LoginUser;
import com.application.kreditimpian.Model.ModelFOrgotPassword.ResponseForgotPassword;
import com.application.kreditimpian.R;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotPassword extends AppCompatActivity {

    EditText txtemail, Edusername;
    Button btnSend, btnOK;
    ProgressDialog pDialog;
    BaseApiService mApiservices;
    TextView textalert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        setActionBarTitle("Lupa sandi");
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);// set drawable icon
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mApiservices = UtilsApi.getAPIService();

        txtemail = findViewById(R.id.txtemail);
        Edusername = findViewById(R.id.Edusername);
        btnSend = findViewById(R.id.btnSend);
        textalert = findViewById(R.id.textalert);


        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ForgotPassword();
            }
        });
    }

    private void ForgotPassword() {

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        pDialog.setMessage("Loading...");
        pDialog.show();


        String username = Edusername.getText().toString();

        mApiservices.ForgotPassword(username).enqueue(new Callback<ResponseForgotPassword>() {
            @Override
            public void onResponse(Call<ResponseForgotPassword> call, Response<ResponseForgotPassword> response) {
                pDialog.dismiss();
                if (response.body().getStatus() == 200) {
                    ///Toast.makeText(ForgotPassword.this, response.body().getResult().toString(), Toast.LENGTH_LONG).show();

                    textalert.setVisibility(View.GONE);
                    AlertDialog alertDialog = new AlertDialog.Builder(ForgotPassword.this).create();

                    alertDialog.setTitle("Email berhasil terkriim");
                    alertDialog.setMessage("Cek pada Inbox anda dan klik link yang ada pada isi email tersebut");
                    alertDialog.setIcon(R.drawable.successfully);
                    alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            finish();

                        }
                    });

                    alertDialog.show();





                } else {
                    pDialog.dismiss();
                    textalert.setVisibility(View.VISIBLE);
                    ////Toast.makeText(ForgotPassword .this,"Email tidak ditemukan",Toast.LENGTH_LONG).show();


                }


            }

            @Override
            public void onFailure(Call<ResponseForgotPassword> call, Throwable t) {

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
