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

import com.application.kreditimpian.Api.ApiHeader.AuthorizationResponse;
import com.application.kreditimpian.Api.ApiHeader.ServiceClient;
import com.application.kreditimpian.Api.JWTParser;
import com.application.kreditimpian.Api.PreferenceHelper;
import com.application.kreditimpian.Api.RequestInterface;
import com.application.kreditimpian.Api.SessionManager;
import com.application.kreditimpian.Api.SharedPrefManager;
import com.application.kreditimpian.Api.api_v2.BaseApiService;
import com.application.kreditimpian.Api.api_v2.UtilsApi;
import com.application.kreditimpian.BuildConfig;
import com.application.kreditimpian.DecodeUtils.JWTUtils;
import com.application.kreditimpian.MainActivity;
import com.application.kreditimpian.MenuUtama.MenuUtama;
import com.application.kreditimpian.Model.UserModel.User;
import com.application.kreditimpian.R;
import com.application.kreditimpian.ResponseMessage.ResponseLoginSucces;
import com.auth0.android.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.util.Base64Utils;
import com.google.android.gms.tasks.Task;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.internal.bind.JsonTreeWriter;
import com.google.gson.reflect.TypeToken;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import butterknife.internal.Utils;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.io.Decoder;
import io.jsonwebtoken.io.IOException;
import io.jsonwebtoken.lang.Assert;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


import static android.text.TextUtils.htmlEncode;
import static android.text.TextUtils.isEmpty;
import static com.application.kreditimpian.Api.JWTParser.decoded;
import static com.application.kreditimpian.Api.SharedPrefManager.SP_ID;

import java.io.UnsupportedEncodingException;
import java.util.Base64;


public class LoginUser extends AppCompatActivity {
    ///private String url = "https://demo.kreditimpian.com/ApiAndro/login_member.php";
    private String url = BuildConfig.BASE_URL+"system/users/authenticate";
    private static final String TAG = LoginUser.class.getSimpleName();
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";
    int success;
    ProgressDialog pDialog;

    Context mContext;
    //ProgressDialog progressDialog;

    private PreferenceHelper preferenceHelper;


    public final static String TAG_ID = "id";
    public final static String TAG_EMAIL = "email";
    public final static String TAG_USERNAME = "username";

    String tag_json_obj = "json_obj_req";
    SharedPreferences sharedpreferences;
    Boolean session = false;
    String   email,username,password;
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
    BaseApiService mApiService;
    SessionManager sessionManager;



    private JWT jwt;
    private String decoded;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_user);
        txtusername =findViewById(R.id.txtusername);
        txtpassword =findViewById(R.id.txtpassword);

        mApiService = UtilsApi.getAPIService();
        sharedPrefManager = new SharedPrefManager(this);

        if (sharedPrefManager.getSPSudahLogin()){
            startActivity(new Intent(LoginUser.this, MenuUtama.class)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
            finish();
        }

        ///sessionManager = new SessionManager(this);

        btnregister = findViewById(R.id.btnregister);
        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Register.class);
                startActivity(intent);
            }
        });




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
                    UserLogin();
                   ///LoginUser();
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


    @Override
    protected void onStart() {
        super.onStart();
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);


    }





    public void UserLogin(){

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        pDialog.setMessage("Tunggu proses login ...");
        pDialog.show();

        Call<ResponseLoginSucces> postLogin = mApiService.loginRequest(txtusername.getText().toString(),
                txtpassword.getText().toString());
        postLogin.enqueue(new Callback<ResponseLoginSucces>() {
            @Override
            public void onResponse(Call<ResponseLoginSucces> call, Response<ResponseLoginSucces> response) {
                pDialog.dismiss();

                if (response.body().getStatus()==200) {

                    ResponseLoginSucces LoginSueccess = response.body();

                    sharedPrefManager.saveSPString(SharedPrefManager.SP_TOKEN, LoginSueccess.getResult());
                    sharedPrefManager.saveSPBoolean(SharedPrefManager.SP_SUDAH_LOGIN, true);
                    String username = txtusername.getText().toString();
                    sharedPrefManager.saveSPString(SharedPrefManager.SP_USERNAME, username);

                    Toast.makeText(LoginUser.this, "Selamat datang kembali "+username, Toast.LENGTH_LONG).show();

                    token = sharedPrefManager.getSPToken();
                    try {
                        decoded = JWTParser.decoded(token);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    sharedPrefManager.saveSPString(SharedPrefManager.SP_DECODE, decoded);

                    Gson gson =  new Gson();
                    String json = gson.toJson(decoded);


                    Toast.makeText(LoginUser.this, "Gson anda" + json, Toast.LENGTH_LONG).show();


//
//                    try {
//                        JSONArray array = new JSONArray(json);
//                        JSONObject jsonObject = array.getJSONObject(0);
//                        String id = jsonObject.getString("id");
//
//                        ///sharedPrefManager.saveSPString(SP_ID, id);
//                        Toast.makeText(LoginUser.this, "Percobaan abil id nya bismillah "+id, Toast.LENGTH_LONG).show();
//
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }











//                    try {
//
//
//                        JSONObject jsonObject = new JSONObject(SharedPrefManager.SP_DECODE);
//                        Log.d(TAG_EMAIL , jsonObject.getString("email"));
//                    }
//                    catch (JSONException e) {
//                        e.printStackTrace();
//                    }




                    startActivity(new Intent(LoginUser.this, MenuUtama.class)
                            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                    finish();



                } else {
                    Toast.makeText(LoginUser.this, "Username dan Password anda tidak cocok", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ResponseLoginSucces> call, Throwable t) {
                pDialog.dismiss();
            }
        });



    }



    public void LoginUser() {
        //membuat progress dialog
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        pDialog.setMessage("Tunggu proses login ...");
        pDialog.show();

        //mengambil data dari edittext

        final String username = txtusername.getText().toString().trim();
        final String password = txtpassword.getText().toString().trim();

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(50, TimeUnit.SECONDS)
                .readTimeout(50, TimeUnit.SECONDS).build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL).client(client)
                .addConverterFactory(GsonConverterFactory.create(new Gson())).build();

        RequestInterface api = retrofit.create(RequestInterface.class);
        Call<ResponseLoginSucces> call = api.login_member(username,  password);

        call.enqueue(new Callback<ResponseLoginSucces>() {
            @Override
            public void onResponse(Call<ResponseLoginSucces> call, Response<ResponseLoginSucces> response) {
                if(response.isSuccessful()){
                    pDialog.dismiss();

                    if(response.body().getResult() != null){
//                        try {
//                            JWTUtils.decoded(response.body().getResult());
//                        }
//                        catch (Exception e)
//                        {
//
//                        }


                        // Jika login berhasil
                        String result = response.body().getResult();
                        sharedPrefManager.saveSPString(SharedPrefManager.SP_TOKEN, result);
                        String username = txtusername.getText().toString();
                        sharedPrefManager.saveSPString(SharedPrefManager.SP_USERNAME, username);
//                        String id = response.body().getResult();
//                        String email = response.body().getResult();
//                        String username = response.body().getResult();
//                        String msisdn = response.body().getResult();
//                        sharedPrefManager.saveSPString(SharedPrefManager.SP_ID, id);
//                        sharedPrefManager.saveSPString(SharedPrefManager.SP_EMAIL, email);
//                        sharedPrefManager.saveSPString(SharedPrefManager.SP_USERNAME, username);
//                        sharedPrefManager.saveSPString(SharedPrefManager.SP_MSISDN, msisdn);

                        Toast.makeText(getApplicationContext(), "Berhasil Login", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginUser.this, MenuUtama.class);
                        sharedPrefManager.saveSPBoolean(SharedPrefManager.SP_SUDAH_LOGIN, true);
                        startActivity(intent);
                        finish();




                    } else {
                        Toast.makeText(LoginUser.this, "The username or password is incorrect", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(LoginUser.this, "Error! Please try again!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseLoginSucces> call, Throwable t) {
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
