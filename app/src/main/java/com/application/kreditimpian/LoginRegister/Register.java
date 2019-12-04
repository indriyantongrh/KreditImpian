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

import com.application.kreditimpian.Api.RequestInterface;
import com.application.kreditimpian.Api.ResponseMessage;
import com.application.kreditimpian.Api.SuccessMessage;
import com.application.kreditimpian.BuildConfig;
import com.application.kreditimpian.R;
import com.application.kreditimpian.ResponseMessage.ResponseRegister;
import com.google.gson.Gson;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.internal.http2.ErrorCode;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.text.TextUtils.isEmpty;

public class Register extends AppCompatActivity {

    String id;
    Button btnbuatakun;
    TextView btnLogin;
    TextView txtusername, txtemail,txtpassword,txtconfirmpassword,nomortelepon;

    Intent intent;

    ProgressDialog pDialog;
    private static final String TAG = Register.class.getSimpleName();
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";
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

/*                if (isValidEmail(txtemail.getText().toString())){

                } else {
                    txtemail.setError("Format email salah");
                    Toast.makeText(Register.this,
                            "Format email salah", Toast.LENGTH_SHORT).show();
                }*/

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
        String phone = nomortelepon.getText().toString();
        String password = txtpassword.getText().toString();
        String password_confirm = txtconfirmpassword.getText().toString();

        ///String password = txt_password.getText().toString();

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(100, TimeUnit.SECONDS)
                .readTimeout(100, TimeUnit.SECONDS).build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL).client(client)
                .addConverterFactory(GsonConverterFactory.create(new Gson())).build();

        RequestInterface api = retrofit.create(RequestInterface.class);
        Call<ResponseRegister> call = api.create_member(id, username, email, phone, password, password_confirm);
        call.enqueue(new Callback<ResponseRegister>() {
            @Override
            public void onResponse(Call<ResponseRegister> call, Response<ResponseRegister> response) {
                //String status = response.body().getStatus();
              //  String message = response.body().getMessage();
                pDialog.dismiss();
                if (response.isSuccessful()) {
                    Toast.makeText(Register.this, "Registrasi berhasil, silahkan login.", Toast.LENGTH_SHORT).show();
                   ///Toast.makeText(Register.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        finish();
                } else {
                    Toast.makeText(Register.this, "Periksa kembali data Anda!.", Toast.LENGTH_SHORT).show();
                    //Toast.makeText(Register.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<ResponseRegister> call, Throwable t) {
                t.printStackTrace();
                pDialog.dismiss();
                Toast.makeText(Register.this, "Register member tidak berhasil, Koneksi internet terputus.", Toast.LENGTH_SHORT).show();
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
