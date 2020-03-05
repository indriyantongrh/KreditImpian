package com.application.kreditimpian.Akun;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.application.kreditimpian.Adapter.AdapterProduct;
import com.application.kreditimpian.Api.JWTParser;
import com.application.kreditimpian.Api.SharedPrefManager;
import com.application.kreditimpian.Api.api_v2.BaseApiService;
import com.application.kreditimpian.Favorite.Favorite;
import com.application.kreditimpian.GantidanRisetPassword.GantiPassword;
import com.application.kreditimpian.HistoryPesanan.HistoryPesanan;
import com.application.kreditimpian.KonfirmasiPembayaran.KonfirmasiPembayaran;
import com.application.kreditimpian.LoginRegister.LoginUser;
import com.application.kreditimpian.Model.ModelProduct.ResponseProduct;
import com.application.kreditimpian.Model.ModelUser.UserResponse;

import com.application.kreditimpian.R;
import com.application.kreditimpian.ResponseMessage.ResponseLoginSucces;
import com.application.kreditimpian.StatusPesanan.StatusPesanan;
import com.auth0.android.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.bumptech.glide.Glide;
import com.fasterxml.jackson.databind.ser.Serializers;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.Context.MODE_PRIVATE;


public class FragmentAkun extends Fragment {

    ImageView image;
    CardView btndetailakun, btnstatuspesanan,btnchat ,btnhistorypesanan,btnfavorite,btnkonfirmasi, btngantipassword,btnlogout,btnalamatpengiriman ;
    TextView txt_nama_akun,textchat;

    GoogleSignInClient mGoogleSignInClient;
    private GoogleApiClient mGoogleApiClient;

    SharedPrefManager sharedPrefManager;

    public final static String TAG_ID = "id";
    public final static String TAG_EMAIL = "email";


    String tag_json_obj = "json_obj_req";
    SharedPreferences sharedpreferences;
    Boolean session = false;
    String id, value_email, value_token, value_nomorhp, email, username;;
    public static final String my_shared_preferences = "my_shared_preferences";
    public static final String session_status = "session_status";
    BaseApiService mApiService;
    Context mContext;

    private JWT jwt;
    private String decoded;
    private String token;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_fragment_akun, container, false);

        txt_nama_akun = view.findViewById(R.id.txt_nama_akun);
        image = view.findViewById(R.id.image);


        sharedPrefManager = new SharedPrefManager(getActivity());
       /// String id = sharedPrefManager.getSPID();
        String email = sharedPrefManager.getSPEmail();
        String token = sharedPrefManager.getSPToken();
        String usernameMember = sharedPrefManager.getSpUserUsername();
        String idprof = sharedPrefManager.getSpIdprofile();
        String id_user = sharedPrefManager.getSpIdUser();
        String id_member = sharedPrefManager.getSpIdMember();

     ////   Toast.makeText(getActivity(), "Id member anda "+id_member, Toast.LENGTH_LONG).show();

        ///Toast.makeText(getActivity(),token, Toast.LENGTH_SHORT).show();
        txt_nama_akun.setText(usernameMember);

//        //String JWTToken = sharedPrefManager.getSPToken();
//        byte[] encodeJTW = android.util.Base64.decode(token, android.util.Base64.DEFAULT);

//        DecodedJWT jwtIdent = JWT.decode(token);
//        Toast.makeText(getActivity(),"ini hasil decode"+jwtIdent, Toast.LENGTH_SHORT).show();

            /// getUsername();



        try {
            decoded = JWTParser.decoded(token);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Gson gson =  new Gson();
        String json = gson.toJson(decoded);
        ///System.out.println(json);
        //Toast.makeText(LoginUser.this, "Gson anda" + json, Toast.LENGTH_LONG).show();


        JSONArray ja = null;
        try {
            ja = new JSONArray(json);
            String result = ja.getJSONObject(0).getString("email");
            System.out.println(result);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ///Toast.makeText(getActivity(),"ini hasil decode"+decoded, Toast.LENGTH_SHORT).show();



        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(getContext(), gso);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(getActivity());
        if (acct != null) {
            String personName = acct.getDisplayName();
            String personGivenName = acct.getGivenName();
            String personFamilyName = acct.getFamilyName();
            String personEmail = acct.getEmail();
            String personId = acct.getId();
            Uri personPhoto = acct.getPhotoUrl();

            txt_nama_akun.setText(personName);
            Glide.with(this).load(String.valueOf(personPhoto)).into(image);

        }

        btndetailakun = view.findViewById(R.id.btndetailakun);
        btndetailakun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), DataDiri.class);
                getActivity().startActivity(intent);
            }

        });
        textchat = view.findViewById(R.id.textchat);
        btnchat = view.findViewById(R.id.btnchat);
            btnchat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                Toast.makeText(getActivity(), "Fitur segera hadir", Toast.LENGTH_LONG).show();
//                    StringtoJson();
                }
            });


        btnalamatpengiriman = view.findViewById(R.id.btnalamatpengiriman);
        btnalamatpengiriman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), AlamatPengiriman.class);
                getActivity().startActivity(intent);
            }

        });


        btnhistorypesanan = view.findViewById(R.id.btnhistorypesanan);
        btnhistorypesanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               //// Toast.makeText(getActivity(), "Fitur segera hadir", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(getActivity(), HistoryPesanan.class);
                getActivity().startActivity(intent);
            }

        });

        btnfavorite = view.findViewById(R.id.btnfavorite);
        btnfavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Fitur segera hadir", Toast.LENGTH_LONG).show();

//                Intent intent = new Intent(getActivity(), Favorite.class);
//                getActivity().startActivity(intent);
            }

        });

        btnkonfirmasi = view.findViewById(R.id.btnkonfirmasi);
        btnkonfirmasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Fitur segera hadir", Toast.LENGTH_LONG).show();
                /*Intent intent = new Intent(getActivity(), KonfirmasiPembayaran.class);
                getActivity().startActivity(intent);*/
            }

        });

        btngantipassword = view.findViewById(R.id.btngantipassword);
        btngantipassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), GantiPassword.class);
                getActivity().startActivity(intent);
            }

        });

        btnlogout = view.findViewById(R.id.btnlogout);
        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                builder.setTitle("Logout");
                builder.setMessage("Apa yakin ingin Logout ?");

                builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {



                        sharedPrefManager.saveSPBoolean(SharedPrefManager.SP_SUDAH_LOGIN, false);
                        startActivity(new Intent(getActivity(), LoginUser.class)
                                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
                        getActivity().finish();


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






               /* switch (v.getId()) {
                    // ...
                    case R.id.btnlogout:
                        signOut();
                        break;
                    // ...
                }*/
            }
        });

        return view;
    }



    private void signOut() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(getActivity(), new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(getActivity(),"Anda berhasil keluar!", Toast.LENGTH_LONG).show();
                        getActivity().finish();
                    }
                });
    }


    private void StringtoJson(){
        try {
            decoded = JWTParser.decoded(token);
            Log.d("My App", decoded);
        } catch (Exception e) {
            e.printStackTrace();
        }


        try {

            JSONObject obj = new JSONObject(decoded);
            String id = obj.getString("id");
            String email = obj.getString("email");
            String username = obj.getString("username");
            Log.d("My Id", id+email+username);
            Log.d("My App", obj.toString());

        } catch (Throwable t) {
            Log.e("My App", "Could not parse malformed JSON: \"" + decoded + "\"");
        }

    }

    @Override
    public void onStart() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        mGoogleApiClient.connect();
        super.onStart();
    }


//    private void getUsername(){
//        Call<ResponseLoginSucces> getUser = mApiService.getUsermember("Bearer "+sharedPrefManager.getSPToken());
//        getUser.enqueue(new Callback<ResponseLoginSucces>() {
//            @Override
//            public void onResponse(Call<ResponseLoginSucces> call, Response<ResponseLoginSucces> response) {
//                if (response != null) {
//                    Toast.makeText(getActivity(), response.body().getResultItem().getEmail(), Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ResponseLoginSucces> call, Throwable t) {
//
//            }
//        });
//
//    }



//    private void getResultUser() {
//        ///progressBar = ProgressDialog.show(getActivity(), null, "Harap Tunggu...", true, false);
//
//        Call<UserResponse> getUser = mApiService.getUsermember(sharedPrefManager.getSPToken());
//        getUser.enqueue(new Callback<UserResponse>() {
//            @Override
//            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
//                if (response.body().isSuccess()) {
//                    Toast.makeText(getActivity(), response.body().getReason(), Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<UserResponse> call, Throwable t) {
//
//            }
//        });
//
//        mApiService.getUsermember(SharedPrefManager.SP_TOKEN).enqueue(new Callback<UserResponse>() {
//            @Override
//            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
//                if (response.isSuccessful()) {
//                    if (response.body().getResult()) {
//                        Toast.makeText(mContext, "Behasil NOl", Toast.LENGTH_SHORT).show();
//                    } else {
//                        txt_nama_akun.setText(response.body().getResult().toString());
//                    }
//                } else {
//                    Toast.makeText(mContext, "Gagal mengambil data detail", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<UserResponse> call, Throwable t) {
//                Toast.makeText(mContext, "Koneksi internet bermasalah", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
}
