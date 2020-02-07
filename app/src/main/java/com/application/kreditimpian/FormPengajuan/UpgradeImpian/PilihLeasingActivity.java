package com.application.kreditimpian.FormPengajuan.UpgradeImpian;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.application.kreditimpian.Api.SharedPrefManager;
import com.application.kreditimpian.FormPengajuan.SuccessMengajukan;
import com.application.kreditimpian.FormPengajuan.UpgradeImpian.viewmodel.UpgradeImpianViewModel;
import com.application.kreditimpian.FormPengajuan.UpgradeImpian.viewmodel.ViewModelFactory;
import com.application.kreditimpian.Model.ModelMitra;
import com.application.kreditimpian.Model.ModelUpgradeImpian.ModelUpgradeImpian;
import com.application.kreditimpian.R;

import java.util.ArrayList;

public class PilihLeasingActivity extends AppCompatActivity implements PilihLeasingAdapter.OnClickPilihLeasing {

    private String idMember;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilih_leasing);
        SharedPrefManager sharedPrefManager = new SharedPrefManager(PilihLeasingActivity.this);
        idMember = sharedPrefManager.getSpIdMember();
        RecyclerView rvLeasing = findViewById(R.id.rvPilihLeasing);
        rvLeasing.setLayoutManager(new LinearLayoutManager(PilihLeasingActivity.this));

        if (getIntent().hasExtra("data")) {
            ModelUpgradeImpian modelUpgradeImpian = getIntent().getParcelableExtra("data");
            String code = getIntent().getStringExtra("code");
            if (modelUpgradeImpian != null) {
                PilihLeasingAdapter pilihLeasingAdapter = new PilihLeasingAdapter(PilihLeasingActivity.this, code, modelUpgradeImpian, this);
                rvLeasing.setAdapter(pilihLeasingAdapter);
            }
        }
    }

    @Override
    public void onClickPilihLeasing(String idTransaksi, String Tenor, String idKreditor, String cicilan) {

        ProgressDialog pDialog = new ProgressDialog(PilihLeasingActivity.this);
        pDialog.setCancelable(false);
        pDialog.setMessage("Loading...");
        pDialog.show();

        ModelUpgradeImpian modelUpgradeImpian = new ModelUpgradeImpian();
        modelUpgradeImpian.setIdmember(idMember);
        modelUpgradeImpian.setIdTransaksi(idTransaksi);
        modelUpgradeImpian.setMitra(idKreditor);
        modelUpgradeImpian.setTahun(Tenor);
        modelUpgradeImpian.setJmlhpinjaman(cicilan);
        UpgradeImpianViewModel upgradeImpianViewModel = new ViewModelProvider(getViewModelStore(), new ViewModelFactory(PilihLeasingActivity.this)).get(UpgradeImpianViewModel.class);
        upgradeImpianViewModel.setModelUpgradeImpian(modelUpgradeImpian);
        upgradeImpianViewModel.pilihLeasing().observe(this, modelUpgradeImpians -> {
            pDialog.dismiss();
            ModelUpgradeImpian modelUpgradeImpian1 = modelUpgradeImpians.get(0);
            if (modelUpgradeImpian1 != null) {
                if (modelUpgradeImpian1.getCode().equals("200")) {
                    startActivity(new Intent(PilihLeasingActivity.this, SuccessMengajukan.class));
                    finish();
                }
            }
        });
    }
}
