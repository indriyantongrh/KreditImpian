package com.application.kreditimpian.FormPengajuan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
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
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class StepUploadProduct extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    private Context context;
    private UpgradeImpianViewModel upgradeImpianViewModel;

    private SmartMaterialSpinner smartMaterialSpinner;
    private TextInputEditText txtnamaproduk,
            txtsumberpesanan,
            txtdeskripsi;
    private Button btnAjukansekarang;
    private ImageView image,
            imgBack;

    private static final int PICK_IMAGE_REQUEST = 1;
    private static final int bitmap_size = 60; // range 1 - 100
    private Bitmap decoded;
    private String idMember,
            idKategori = "";
    private ArrayList<String> listIdKategori;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_upload_product);
        Objects.requireNonNull(getSupportActionBar()).hide();
        context = StepUploadProduct.this;
        upgradeImpianViewModel = new ViewModelProvider(getViewModelStore(), new ViewModelFactory(context)).get(UpgradeImpianViewModel.class);

        SharedPrefManager sharedPrefManager = new SharedPrefManager(context);
        idMember = sharedPrefManager.getSpIdMember();

        txtnamaproduk = findViewById(R.id.txtnamaproduk);
        txtsumberpesanan = findViewById(R.id.txtsumberpesanan);
        txtdeskripsi = findViewById(R.id.txtdeskripsi);
        btnAjukansekarang = findViewById(R.id.btnAjukansekarang);
        image = findViewById(R.id.imageupload);
        smartMaterialSpinner = findViewById(R.id.spinKategori);
        View includeToolbar = findViewById(R.id.includeToolbar);
        imgBack = includeToolbar.findViewById(R.id.imgBack);

        imgBack.setOnClickListener(this);
        image.setOnClickListener(this);
        btnAjukansekarang.setOnClickListener(this);
        smartMaterialSpinner.setOnItemSelectedListener(this);

        loadKategori();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            try {
                //mengambil fambar dari Gallery
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                // 512 adalah resolusi tertinggi setelah image di resize, bisa di ganti.
                setToImageView(getResizedBitmap(bitmap, 512));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onClick(View v) {
        if (v == imgBack) {
            finish();
        } else if (v == image) {
            showFileChooser();
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
    //untuk upload image, compress .JPEG ke bitmap
    public String getStringImage(Bitmap bmp) {
        if (bmp != null) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.JPEG, bitmap_size, baos);
            byte[] imageBytes = baos.toByteArray();
            String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
            return encodedImage;
        }
        return "";
    }

    //untuk memilih gambar
    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    /*Menampilkan gambar*/
    private void setToImageView(Bitmap bmp) {
        //compress image
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, bitmap_size, bytes);
        decoded = BitmapFactory.decodeStream(new ByteArrayInputStream(bytes.toByteArray()));

        //menampilkan gambar yang dipilih dari camera/gallery ke ImageView
        image.setImageBitmap(decoded);
    }

    // fungsi resize image
    public Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float) width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
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
            Toast.makeText(context, "Silahkan pilih gambar produk yang anda inginkan", Toast.LENGTH_LONG).show();
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
