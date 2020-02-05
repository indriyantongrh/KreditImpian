package com.application.kreditimpian.Notifikasi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.application.kreditimpian.Api.SharedPrefManager;
import com.application.kreditimpian.MenuUtama.MenuUtama;
import com.application.kreditimpian.Model.ModelNotifikasi;
import com.application.kreditimpian.R;

import java.util.ArrayList;
import java.util.Objects;

public class NotifikasiActivity extends AppCompatActivity implements View.OnClickListener {
    private Context context;

    private ImageView imgBack;
    private RecyclerView rvNotifikasi;

    private String idMember;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifikasi);
        Objects.requireNonNull(getSupportActionBar()).hide();
        context = NotifikasiActivity.this;

        SharedPrefManager sharedPrefManager = new SharedPrefManager(context);
        idMember = sharedPrefManager.getSpIdMember();

        View includeToolbar = findViewById(R.id.includeToolbar);
        imgBack = includeToolbar.findViewById(R.id.imgBack);
        rvNotifikasi = findViewById(R.id.rvNotifikasi);

        rvNotifikasi.setLayoutManager(new LinearLayoutManager(context));

        imgBack.setOnClickListener(this);
        loadNotifikasi();
    }

    @Override
    public void onClick(View v) {
        if (v == imgBack){
            startActivity(new Intent(context, MenuUtama.class));
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(context, MenuUtama.class));
        finish();
        super.onBackPressed();
    }

    private void loadNotifikasi(){
        ArrayList<ModelNotifikasi> modelNotifikasis = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ModelNotifikasi modelNotifikasi = new ModelNotifikasi();
            modelNotifikasi.setMessage("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer");
            modelNotifikasi.setTgl("5 Februari 2020");
            modelNotifikasis.add(modelNotifikasi);
        }
        NotifikasiAdapter notifikasiAdapter = new NotifikasiAdapter(context, modelNotifikasis);
        rvNotifikasi.setAdapter(notifikasiAdapter);
    }
}
