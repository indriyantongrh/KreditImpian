package com.example.kreditimpian.LoginRegister;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kreditimpian.Api.RequestInterface;
import com.example.kreditimpian.Api.SuccessMessage;
import com.example.kreditimpian.BuildConfig;
import com.example.kreditimpian.R;
import com.google.gson.Gson;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Register extends AppCompatActivity {

    String id;
    Button btnbuatakun;
    TextView btnLogin;
    TextView txtusername, txtemail,txtpassword,nomortelepon;

    Intent intent;

    ProgressDialog pDialog;
    private static final String TAG = Register.class.getSimpleName();
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";
    private String url = "https://api.kreditimpian.com/";  //directory php ning server
    String tag_json_obj = "json_obj_req";
    Intent i;
    int success;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        txtusername = (EditText) findViewById(R.id.txtusername);
        txtemail = (EditText) findViewById(R.id.txtemail);
        txtpassword = (EditText) findViewById(R.id.txtpassword);

        btnbuatakun = findViewById(R.id.btnbuatakun);
        btnbuatakun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TambahUser();
            }
        });

        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginUser.class);
                startActivity(intent);
            }
        });

    }

    public void TambahUser() {
        //membuat progress dialog
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        pDialog.setMessage("Proses tambah user ...");
        pDialog.show();

        //mengambil data dari edittext
        String username = txtusername.getText().toString();
        String email = txtemail.getText().toString();
        String password = txtpassword.getText().toString();
        ///String password = txt_password.getText().toString();

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(100, TimeUnit.SECONDS)
                .readTimeout(100, TimeUnit.SECONDS).build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url).client(client)
                .addConverterFactory(GsonConverterFactory.create(new Gson())).build();

        RequestInterface api = retrofit.create(RequestInterface.class);
        Call<SuccessMessage> call = api.registrasi_user(id, username, email, password);
        call.enqueue(new Callback<SuccessMessage>() {
            @Override
            public void onResponse(Call<SuccessMessage> call, Response<SuccessMessage> response) {
                String success = response.body().getSuccess();
                String message = response.body().getMessage();
                pDialog.dismiss();
                if (success.equals("0")) {
                    Toast.makeText(Register.this, message, Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(DetailBookingTempat.this, Riwayat_booking.class);
//                    intent.putExtra("user_id",id_user);
//                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(Register.this, message, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SuccessMessage> call, Throwable t) {
                t.printStackTrace();
                pDialog.dismiss();
                Toast.makeText(Register.this, "Registrasi gagal, cek koneksi dan form anda", Toast.LENGTH_SHORT).show();
            }
        });

    }


    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }


    private void goToLogin(){

        intent = new Intent(Register.this, LoginUser.class);
        finish();
        startActivity(intent);
    }

}
