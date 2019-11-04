package com.application.kreditimpian.Akun;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.application.kreditimpian.Favorite.Favorite;
import com.application.kreditimpian.GantidanRisetPassword.GantiPassword;
import com.application.kreditimpian.HistoryPesanan.HistoryPesanan;
import com.application.kreditimpian.KonfirmasiPembayaran.KonfirmasiPembayaran;
import com.application.kreditimpian.LoginRegister.LoginUser;
import com.application.kreditimpian.R;
import com.application.kreditimpian.StatusPesanan.StatusPesanan;
import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.concurrent.Executor;


public class FragmentAkun extends Fragment {

    ImageView image;
    CardView btndetailakun, btnstatuspesanan, btnhistorypesanan,btnfavorite,btnkonfirmasi, btngantipassword,btnlogout ;
    TextView txt_nama_akun;

    GoogleSignInClient mGoogleSignInClient;
    private GoogleApiClient mGoogleApiClient;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_fragment_akun, container, false);

        txt_nama_akun = view.findViewById(R.id.txt_nama_akun);
        image = view.findViewById(R.id.image);

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

                Intent intent = new Intent(getActivity(), DetailAkun.class);
                getActivity().startActivity(intent);
            }

        });


        btnstatuspesanan = view.findViewById(R.id.btnstatuspesanan);
        btnstatuspesanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), StatusPesanan.class);
                getActivity().startActivity(intent);
            }

        });


        btnhistorypesanan = view.findViewById(R.id.btnhistorypesanan);
        btnhistorypesanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), HistoryPesanan.class);
                getActivity().startActivity(intent);
            }

        });

        btnfavorite = view.findViewById(R.id.btnfavorite);
        btnfavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), Favorite.class);
                getActivity().startActivity(intent);
            }

        });

        btnkonfirmasi = view.findViewById(R.id.btnkonfirmasi);
        btnkonfirmasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), KonfirmasiPembayaran.class);
                getActivity().startActivity(intent);
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
                switch (v.getId()) {
                    // ...
                    case R.id.btnlogout:
                        signOut();
                        break;
                    // ...
                }
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
}
