package com.application.kreditimpian.LoginRegister;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.application.kreditimpian.Akun.DataDiri;
import com.application.kreditimpian.Api.RequestInterface;
import com.application.kreditimpian.Api.ResponseMessage;
import com.application.kreditimpian.Api.SharedPrefManager;
import com.application.kreditimpian.Api.SuccessMessage;
import com.application.kreditimpian.Api.api_v2.BaseApiService;
import com.application.kreditimpian.BuildConfig;
import com.application.kreditimpian.Model.ModelErrorMessage.APIError;
import com.application.kreditimpian.Model.RegisterModel.RegisterResponse;
import com.application.kreditimpian.Model.ResponseRegisterBaru.NewResponseRegister;
import com.application.kreditimpian.R;
import com.application.kreditimpian.ResponseMessage.ResponseRegister;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.internal.http2.ErrorCode;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.text.TextUtils.isEmpty;
import static com.application.kreditimpian.Api.network.interceptor.MyApp.getContext;
import static java.lang.Boolean.FALSE;

public class Register extends AppCompatActivity {
    private int mCounter = 0;
    String id;
    Button btnbuatakun;
    TextView btnLogin;
    TextView txtusername, txtemail,txtpassword,txtconfirmpassword,nomortelepon;
     private String URL = "https://demo.kreditimpian.com/api/";
    Intent intent;
    SharedPrefManager sharedPrefManager;
    ProgressDialog pDialog;
    private static final String TAG = Register.class.getSimpleName();
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";
    String tag_json_obj = "json_obj_req";
    Intent i;
    int success;
    BaseApiService mApiService;
    private String KEY_USERNAME = "username";
    private String KEY_EMAIL = "email";
    private String KEY_NOMORHP = "phone";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        txtusername = (EditText) findViewById(R.id.txtusername);
        txtemail = (EditText) findViewById(R.id.txtemail);
        txtpassword = (EditText) findViewById(R.id.txtpassword);
        txtconfirmpassword =(EditText) findViewById(R.id.txtconfirmpassword);
        nomortelepon = (EditText) findViewById(R.id.nomortelepon);

        btnbuatakun = findViewById(R.id.btnbuatakun);
        btnbuatakun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = txtusername.getText().toString();
                String email = txtemail.getText().toString();
                String phone = nomortelepon.getText().toString();
                String password = txtpassword.getText().toString();
                String password_confirm = txtconfirmpassword.getText().toString();


                 if (isEmpty(username))
                    txtusername.setError("Username harap diisi");
                else if (isEmpty(email))
                    txtemail.setError("Email harap diisi");
                 else if (isEmpty(password))
                     txtpassword.setError("Password harap diisi");
                 else if (isEmpty(password_confirm))
                     txtconfirmpassword.setError("Konfirmasi harap diisi");
                else if (isEmpty(phone))
                    nomortelepon.setError("Nomor Telepon harap diisi");
                else
                     TambahUser();
                     ///RegisterCheck();
                ///TambahUser();
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
        pDialog.setMessage("Loading. ..");
        pDialog.show();

        //mengambil data dari edittext
        String username = txtusername.getText().toString();
        String email = txtemail.getText().toString();
        String phone = nomortelepon.getText().toString();
        String password = txtpassword.getText().toString();
        String password_confirm = txtconfirmpassword.getText().toString();

        ///String password = txt_password.getText().toString();

        OkHttpClient client = new OkHttpClient.Builder()
                .followRedirects(FALSE)
                .followSslRedirects(FALSE)
                .connectTimeout(100, TimeUnit.SECONDS)
                .readTimeout(100, TimeUnit.SECONDS).build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL).client(client)
                .addConverterFactory(GsonConverterFactory.create()).build();

/*        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();*/


        RequestInterface api = retrofit.create(RequestInterface.class);
        Call<RegisterResponse> call = api.create_member(username, email, phone, password, password_confirm);
        call.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
              /////  Log.v("jajal", response.body().toString());
                //String status = response.body().getStatus();
              //  String message = response.body().getMessage();
                pDialog.dismiss();
                if (response.isSuccessful()) {
                    ///Toast.makeText(Register.this, "Registrasi berhasil, silahkan login.", Toast.LENGTH_SHORT).show();
                    ///Toast.makeText(Register.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), SmsOTPRegister.class);
                    intent.putExtra(KEY_USERNAME, username);
                    intent.putExtra(KEY_EMAIL, email);
                    intent.putExtra(KEY_NOMORHP, phone);
                    startActivity(intent);
                    finish();
                } else {
                    /*Handle Error code 400*/
                    if (response.code() == 400) {
                        Gson gson = new GsonBuilder().create();
                        APIError pojo = new APIError();
                        try {
                            pojo = gson.fromJson(response.errorBody().string(), APIError.class);
                            Toast.makeText(getApplicationContext(), pojo.getMessage(), Toast.LENGTH_LONG).show();
                        } catch (IOException e) { }
                    }


                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                Log.v("jajal", t.getMessage());
                t.printStackTrace();
                pDialog.dismiss();
                Toast.makeText(Register.this, "Register member tidak berhasil, Koneksi internet terputus.", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void registerMember(){


        String username = txtusername.getText().toString();
        String email = txtemail.getText().toString();
        String phone = nomortelepon.getText().toString();
        String password = txtpassword.getText().toString();
        String password_confirm = txtconfirmpassword.getText().toString();

        Toast.makeText(Register.this, ""+username+email+phone+password+password_confirm, Toast.LENGTH_LONG).show();
       /* mApiService.registerMember(username, email, phone, password, password_confirm).enqueue(new Callback<NewResponseRegister>() {
            @Override
            public void onResponse(Call<NewResponseRegister> call, Response<NewResponseRegister> response) {

                if(response.body().getResponseCode()==200){
                    Toast.makeText(Register.this, response.body().getMessage(), Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(Register.this, "Data yang anda masukan salah", Toast.LENGTH_LONG).show();

                }

            }

            @Override
            public void onFailure(Call<NewResponseRegister> call, Throwable t) {
                Toast.makeText(Register.this, "Koneksi anda terputus", Toast.LENGTH_LONG).show();
            }
        });*/
    }



/*    private void RegisterCheck(){

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        pDialog.setMessage("Loading. ..");
        pDialog.show();

        //mengambil data dari edittext
        String username = txtusername.getText().toString();
        String email = txtemail.getText().toString();
        String phone = nomortelepon.getText().toString();
        String password = txtpassword.getText().toString();
        String password_confirm = txtconfirmpassword.getText().toString();


        OkHttpClient client = new OkHttpClient.Builder()
                .followRedirects(FALSE)
                .followSslRedirects(FALSE)
                .connectTimeout(100, TimeUnit.SECONDS)
                .readTimeout(100, TimeUnit.SECONDS).build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL).client(client)
                .addConverterFactory(GsonConverterFactory.create()).build();

        RequestInterface api = retrofit.create(RequestInterface.class);
        Call<RegisterResponse> callRegister = api.create_member(username, email, phone, password, password_confirm);
            callRegister.enqueue(new Callback<RegisterResponse>() {
                @Override
                public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {

                    if(response.isSuccessful()){
                        pDialog.dismiss();
                        if(response.body().getResult() != null){
                            Toast.makeText(Register.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(Register.this, "The username or password is incorrect", Toast.LENGTH_SHORT).show();
                        }
                    } else {

                        Toast.makeText(Register.this, "Error! Please try again!", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<RegisterResponse> call, Throwable t) {
                    pDialog.dismiss();
                    Log.v("jajal", t.getMessage());
                    Toast.makeText(Register.this, "Tidak response", Toast.LENGTH_LONG).show();
                }
            });


    }*/
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


    public static boolean isValidEmail(String email) {
        boolean validate;
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        String emailPattern2 = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+\\.+[a-z]+";

        if (email.matches(emailPattern)) {
            validate = true;
        } else if (email.matches(emailPattern2)) {
            validate = true;
        } else {
            validate = false;
        }

        return validate;
    }



}
