package com.application.kreditimpian.FormPengajuan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.application.kreditimpian.Api.SharedPrefManager;
import com.application.kreditimpian.FormPengajuan.UpgradeImpian.viewmodel.UpgradeImpianViewModel;
import com.application.kreditimpian.FormPengajuan.UpgradeImpian.viewmodel.ViewModelFactory;
import com.application.kreditimpian.Model.ModelProductNew.Category;
import com.application.kreditimpian.R;
import com.chivorn.smartmaterialspinner.SmartMaterialSpinner;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Objects;

public class StepIsiCariProduct extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {
    private Context context;
    private UpgradeImpianViewModel upgradeImpianViewModel;

    private ImageView imgBack;
    private SmartMaterialSpinner smartMaterialSpinner;
    private TextInputEditText txtnamaproduk,
            txtsumberpesanan;
    private EditText txtdeskripsi;
    private Button btnAjukansekarang;

    private String idMember,
            idKategori = "";
    private ArrayList<String> listIdKategori;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_isi_product_cari);
        Objects.requireNonNull(getSupportActionBar()).hide();
        context = StepIsiCariProduct.this;
        upgradeImpianViewModel = new ViewModelProvider(getViewModelStore(), new ViewModelFactory(context)).get(UpgradeImpianViewModel.class);

        SharedPrefManager sharedPrefManager = new SharedPrefManager(context);
        idMember = sharedPrefManager.getSpIdMember();

        smartMaterialSpinner = findViewById(R.id.spinKategori);
        txtnamaproduk = findViewById(R.id.txtnamaproduk);
        txtsumberpesanan = findViewById(R.id.txtsumberpesanan);
        txtdeskripsi = findViewById(R.id.txtdeskripsi);
        btnAjukansekarang = findViewById(R.id.btnAjukansekarang);
        smartMaterialSpinner = findViewById(R.id.spinKategori);
        View includeToolbar = findViewById(R.id.includeToolbar);
        imgBack = includeToolbar.findViewById(R.id.imgBack);

        btnAjukansekarang.setOnClickListener(this);
        smartMaterialSpinner.setOnItemSelectedListener(this);
        imgBack.setOnClickListener(this);

        loadKategori();
    }

    @Override
    public void onClick(View v) {
        if (v == imgBack) {
            finish();
        } else if (v == btnAjukansekarang) {
            kirimData();
        }
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        idKategori = listIdKategori.get(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @SuppressWarnings("unchecked")
    private void loadKategori() {
        upgradeImpianViewModel.getKategoriFotoImpian().observe(this, categories -> {
            ArrayList<String> listKategori = new ArrayList<>();
            listIdKategori = new ArrayList<>();
            for (int i = 0; i < categories.size(); i++) {
                listKategori.add(categories.get(i).getName());
                listIdKategori.add(categories.get(i).getId());
            }
            smartMaterialSpinner.setItem(listKategori);
        });
    }

    private void kirimData() {
        String namaProduk = String.valueOf(txtnamaproduk.getText());
        String sumberPesanan = String.valueOf(txtsumberpesanan.getText());
        String deskripsi = String.valueOf(txtdeskripsi.getText());

        if (namaProduk.isEmpty()) {
            txtnamaproduk.setError(getResources().getString(R.string.namaprodukkosong));
            txtnamaproduk.requestFocus();
        } else if (idKategori.isEmpty()) {
            Toast.makeText(context, "Kategori belum dipilih", Toast.LENGTH_LONG).show();
        } else if (deskripsi.isEmpty()) {
            txtdeskripsi.setError(getResources().getString(R.string.deskripsikosong));
            txtdeskripsi.requestFocus();
        } else {
            ProgressDialog pDialog = new ProgressDialog(context);
            pDialog.setCancelable(false);
            pDialog.setMessage("Loading...");
            pDialog.show();

            Category category = new Category();
            category.setIdParent(idMember);
            category.setId(idKategori);
            category.setName(namaProduk);
            category.setSlug(sumberPesanan);
            category.setDescription(deskripsi);
            category.setImage("");

            upgradeImpianViewModel.setCategory(category);
            upgradeImpianViewModel.uploadFotoImpian().observe(this, s -> {
                pDialog.dismiss();
                if (s.equals("200")) {
                    startActivity(new Intent(context, SuccessMengajukan.class));
                    finish();
                }
            });
        }
    }

}
