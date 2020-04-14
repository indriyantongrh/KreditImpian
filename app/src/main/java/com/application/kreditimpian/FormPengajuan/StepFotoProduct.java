package com.application.kreditimpian.FormPengajuan;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Objects;

public class StepFotoProduct extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    private Context context;
    private UpgradeImpianViewModel upgradeImpianViewModel;

    private ImageView image,
            imgBack;
    private SmartMaterialSpinner smartMaterialSpinner;
    private TextInputEditText txtnamaproduk,
            txtsumberpesanan,
            txtdeskripsi;
    private Button btnAjukansekarang;

    private String idMember,
            idKategori = "";
    private ArrayList<String> listIdKategori;
    private Bitmap decoded;
    private int bitmap_size = 60; // range 1 - 100
    private static final int PERMISSION_REQUEST_CODE = 200;
    private static final int CAMERA_REQUEST = 1888;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stepisi_product);
        Objects.requireNonNull(getSupportActionBar()).hide();
        context = StepFotoProduct.this;
        upgradeImpianViewModel = new ViewModelProvider(getViewModelStore(), new ViewModelFactory(context)).get(UpgradeImpianViewModel.class);
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            requestPermission();
        }

        SharedPrefManager sharedPrefManager = new SharedPrefManager(context);
        idMember = sharedPrefManager.getSpIdMember();

        image = findViewById(R.id.imagefoto);
        smartMaterialSpinner = findViewById(R.id.spinKategori);
        txtnamaproduk = findViewById(R.id.txtnamaproduk);
        txtsumberpesanan = findViewById(R.id.txtsumberpesanan);
        txtdeskripsi = findViewById(R.id.txtdeskripsi);
        btnAjukansekarang = findViewById(R.id.btnAjukansekarang);
        View includeToolbar = findViewById(R.id.includeToolbar);
        imgBack = includeToolbar.findViewById(R.id.imgBack);

        imgBack.setOnClickListener(this);
        image.setOnClickListener(this);
        btnAjukansekarang.setOnClickListener(this);
        smartMaterialSpinner.setOnItemSelectedListener(this);

        loadKategori();

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onClick(View v) {
        if (v == imgBack) {
            finish();
        } else if (v == image) {
            if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_PERMISSION_CODE);
            } else {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_CAMERA_PERMISSION_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "camera permission granted", Toast.LENGTH_LONG).show();
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            } else {
                Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK && data != null) {
            Bitmap photo = (Bitmap) Objects.requireNonNull(data.getExtras()).get("data");
            if (photo != null) {
                setToImageView1(getResizedBitmap(photo));
            }
        }
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.CAMERA},
                PERMISSION_REQUEST_CODE);
    }

    // fungsi resize image
    private Bitmap getResizedBitmap(Bitmap image) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float) width / (float) height;
        if (bitmapRatio > 1) {
            width = 512;
            height = (int) (width / bitmapRatio);
        } else {
            height = 512;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
    }

    //untuk set ke imageview
    private void setToImageView1(Bitmap bmp) {
        //compress image
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, bitmap_size, bytes);
        decoded = BitmapFactory.decodeStream(new ByteArrayInputStream(bytes.toByteArray()));
        //menampilkan gambar yang dipilih dari camera/gallery ke ImageView
        image.setImageBitmap(decoded);
    }

    public String getStringImage(Bitmap bmp) {
        if (bmp != null) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.JPEG, bitmap_size, baos);
            byte[] imageBytes = baos.toByteArray();
            return Base64.encodeToString(imageBytes, Base64.DEFAULT);
        }
        return "";
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

        if (getStringImage(decoded).isEmpty()) {
            Toast.makeText(context, "Silahkan Foto Produk yang anda inginkan", Toast.LENGTH_LONG).show();
        } else if (namaProduk.isEmpty()) {
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
            category.setImage(getStringImage(decoded));
            category.setMethod("foto");

            upgradeImpianViewModel.setCategory(category);
            upgradeImpianViewModel.uploadFotoImpian().observe(this, s -> {
                pDialog.dismiss();
                if (s.equals("200")){
                    startActivity(new Intent(context, SuccessMengajukan.class));
                    finish();
                }
            });
        }
    }

}
