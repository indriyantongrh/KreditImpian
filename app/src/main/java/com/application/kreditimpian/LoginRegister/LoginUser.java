package com.application.kreditimpian.LoginRegister;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.application.kreditimpian.Api.RequestInterface;
import com.application.kreditimpian.Api.api.SharedPrefManager;
import com.application.kreditimpian.BuildConfig;
import com.application.kreditimpian.MenuUtama.MenuUtama;
import com.application.kreditimpian.R;
import com.application.kreditimpian.ResponseMessage.ResponseLogin;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.gson.Gson;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.text.TextUtils.isEmpty;

public class LoginUser extends AppCompatActivity {
    ///private String url = "https://demo.kreditimpian.com/ApiAndro/login_member.php";
    private String url = BuildConfig.BASE_URL+"system/users/authenticate";
    private static final String TAG = LoginUser.class.getSimpleName();
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";
    int success;
    ProgressDialog pDialog;

    Context mContext;


    public final static String TAG_ID = "id";
    public final static String TAG_EMAIL = "email";


    String tag_json_obj = "json_obj_req";
    SharedPreferences sharedpreferences;
    Boolean session = false;
    String id, value_email, value_token, value_nomorhp, email;
    public static final String my_shared_preferences = "my_shared_preferences";
    public static final String session_status = "session_status";

    ProgressDialog loading;

    Button btnLogin;
    TextView btnregister;
    EditText txtusername, txtpassword;

    SignInButton signin;
    GoogleSignInClient mGoogleSignInClient;
    int RC_SIGN_IN=0;
    SharedPrefManager sharedPrefManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_user);
        txtusername =findViewById(R.id.txtusername);
        txtpassword =findViewById(R.id.txtpassword);



        btnregister = findViewById(R.id.btnregister);
        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Register.class);
                startActivity(intent);
            }
        });




/*        sharedPrefManager = new SharedPrefManager(this);
        if (sharedPrefManager.getSPSudahLogin()){
            startActivity(new Intent(LoginUser.this, MenuUtama.class)
            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
            finish();
        }*/


        // Cek session login jika TRUE maka langsung buka MainActivity
        sharedpreferences = getSharedPreferences(my_shared_preferences, Context.MODE_PRIVATE);
        session = sharedpreferences.getBoolean(session_status, false);
        id = sharedpreferences.getString(TAG_ID, "id tidak ditemukan");
        email = sharedpreferences.getString(TAG_EMAIL, "email tidak ditemukan");



        if (session) {
            Intent intent = new Intent(LoginUser.this, MenuUtama.class);
            intent.putExtra(TAG_ID, id);
            intent.putExtra(TAG_EMAIL, email);


            finish();
            startActivity(intent);
        }


        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               String username = txtusername.getText().toString();
                String password = txtpassword.getText().toString();
                if (isEmpty(username))
                    txtusername.setError("Username harap diisi");
                else if (isEmpty(password))
                    txtpassword.setError("Password harap diisi");

                else

                LoginUser();
                //Intent intent = new Intent(LoginUser.this, MenuUtama.class);
                ///startActivity(intent);


            }
        });


        signin = findViewById(R.id.sign_in_button);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.sign_in_button:
                        signIn();
                        break;
                    // ...
                }
            }
        });

        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);


    }


    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            // Signed in successfully, show authenticated UI.
            Intent intent = new Intent(LoginUser.this, MenuUtama.class);
            startActivity(intent);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("error", "signInResult:failed code=" + e.getStatusCode());

        }
    }

/*
    @Override
    protected void onStart() {
        super.onStart();
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);


    }
*/








    public void LoginUser() {
        //membuat progress dialog
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        pDialog.setMessage("Tunggu proses login ...");
        pDialog.show();

        //mengambil data dari edittext
        ///String namalengkap = txtnamalengkap.getText().toString();
        final String username = txtusername.getText().toString();
        String password = txtpassword.getText().toString();

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(50, TimeUnit.SECONDS)
                .readTimeout(50, TimeUnit.SECONDS).build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL).client(client)
                .addConverterFactory(GsonConverterFactory.create(new Gson())).build();

        RequestInterface api = retrofit.create(RequestInterface.class);
        Call<ResponseLogin> call = api.login_member(id, username ,  password);
        call.enqueue(new Callback<ResponseLogin>() {
            @Override
            public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                pDialog.dismiss();

                if(response.isSuccessful()){
                   /// ResObj resObj = response.body();
                    if(response.body().getResult() != null){


                        // menyimpan login ke session
                        sharedpreferences = getSharedPreferences(my_shared_preferences, Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedpreferences.edit();
                        editor.putBoolean(session_status, true);
                        editor.putString(TAG_ID, id);
                        editor.putString(TAG_EMAIL, email);

                        editor.apply();

                        /// sharedPrefManager.saveSPString(SharedPrefManager.SP_EMAIL, email);
                        // Shared Pref ini berfungsi untuk menjadi trigger session login
                        ///sharedPrefManager.saveSPBoolean(SharedPrefManager.SP_SUDAH_LOGIN, true);
                        //login start main activity
                        Intent intent = new Intent(LoginUser.this, MenuUtama.class);
                        Toast.makeText(LoginUser.this, "Selamat datang "+username, Toast.LENGTH_SHORT).show();
                       // Toast.makeText(LoginUser.this, "Selamat datang "+id, Toast.LENGTH_SHORT).show();
                        intent.putExtra("email", email);
                        intent.putExtra("id", id);
                        intent.putExtra(TAG_EMAIL, email);
                        startActivity(intent);
                        finish();

                    } else {
                        Toast.makeText(LoginUser.this, "The username or password is incorrect", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(LoginUser.this, "Error! Please try again!", Toast.LENGTH_SHORT).show();
                }
            }


                /*if (response.isSuccessful()) {
                    if (response.body().getResult() != null) {
                        Toast.makeText(getApplicationContext(), "gagal login" + response.body().getStatus(), Toast.LENGTH_SHORT).show();



                       *//* Toast.makeText(LoginUser.this, "Login berhasil", Toast.LENGTH_SHORT).show();
                    //LoginUser.this.finish();
                    // Toast.makeText(Register.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginUser.this, MenuUtama.class);
                    intent.putExtra("id",id);
                    startActivity(intent);
                    finish();*//*
                    } else {
                        Toast.makeText(LoginUser.this, "Login berhasil" + response.body().getStatus(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginUser.this, MenuUtama.class);
                        intent.putExtra("email", email);
                        startActivity(intent);
                        ///Log.i("onEmptyResponse", "Returned empty response");//Toast.makeText(getContext(),"Nothing returned",Toast.LENGTH_LONG).show();

                        //Toast.makeText(Register.this, "", Toast.LENGTH_SHORT).show();

                    }else{
                        Toast.makeText(LoginUser.this, "Error! Please try again!", Toast.LENGTH_SHORT).show();

                    }

                }*/



            @Override
            public void onFailure(Call<ResponseLogin> call, Throwable t) {
                t.printStackTrace();
                pDialog.dismiss();
                Toast.makeText(LoginUser.this, "Koneksi internet terputus.", Toast.LENGTH_SHORT).show();
            }
        });



    }




    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(LoginUser.this);

        /// builder.setTitle("Keluar ");
        builder.setMessage("Apakah kamu yakin ingin keluar dari aplikasi ?");

        builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {



                moveTaskToBack(true);
                finish();
                //// new DetailAplikasiSaya.HapusData().execute();
                dialog.dismiss();
            }

        });
        builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                // Do nothing
                dialog.dismiss();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
        //// Toast.makeText(this,"Keluar aplikasi!", Toast.LENGTH_LONG).show();

    }
}
