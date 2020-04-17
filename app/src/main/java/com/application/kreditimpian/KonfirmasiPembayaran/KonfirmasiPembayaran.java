package com.application.kreditimpian.KonfirmasiPembayaran;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.application.kreditimpian.Api.api_v2.BaseApiService;
import com.application.kreditimpian.Api.api_v2.UtilsApi;
import com.application.kreditimpian.Model.ModelMemberInsert.ResponseMemberInsert;
import com.application.kreditimpian.R;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KonfirmasiPembayaran extends AppCompatActivity {

    ImageButton btnback;
    TextView id_transaction;
    EditText txt_transfer_date, txtnamalengkap,txtnamabank,txtnominal;
    private int mYear, mMonth, mDay;
    ImageView imageupload;
    private String KEY_ID_TRANSACTION = "id_transaction";
    Bitmap bitmap, decoded_1;
    int bitmap_size = 80; // range 1 - 100=
    int PICK_IMAGE_REQUEST_1 = 1;
    int REQUEST_IMAGE_CAPTURE_1 = 13;
    BaseApiService mApiService;
    Button btnkonfirmasi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_konfirmasi_pembayaran);
        setActionBarTitle("Konfirmasi Pembayaran");
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);// set drawable icon
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mApiService = UtilsApi.getAPIService();
        txtnamalengkap = findViewById(R.id.txtnamalengkap);
        txtnamabank = findViewById(R.id.txtnamabank);
        txtnominal = findViewById(R.id.txtnominal);
        imageupload = findViewById(R.id.imageupload);
        txt_transfer_date = findViewById(R.id.txt_transfer_date);
        id_transaction = findViewById(R.id.id_transaction);
        btnkonfirmasi = findViewById(R.id.btnkonfirmasi);
        txt_transfer_date.setOnClickListener(this::onClick);

        Bundle extras = getIntent().getExtras();
        String id_transactions = extras.getString(KEY_ID_TRANSACTION);
        id_transaction.setText(id_transactions);
        Toast.makeText(KonfirmasiPembayaran.this, "id transaction "+id_transactions, Toast.LENGTH_LONG).show();

        btnkonfirmasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postBuktiTransfer();
            }
        });


        imageupload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //pakai alert dialog
                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(KonfirmasiPembayaran.this);
                builder.setTitle("Pilih");
                builder.setMessage("Silahkan memilih kamera atau galeri");
                builder.setPositiveButton("Kamera", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        if (takePictureIntent.resolveActivity(KonfirmasiPembayaran.this.getPackageManager()) != null) {
                            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE_1);
                        }
                    }
                });
                builder.setNegativeButton("Galeri", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        showFileChooser1();
                    }
                });

                android.app.AlertDialog alert = builder.create();
                alert.show();
            }
        });

    }

    /*Method Selected date*/
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txt_transfer_date:

                Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                                txt_transfer_date.setText( year + "-" + convertDate(monthOfYear + 1) + "-"+ convertDate(dayOfMonth));

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
                break;
        }
    }

    /*for add zero if under 10*/
    public String convertDate(int input) {
        if (input >= 10) {
            return String.valueOf(input);
        } else {
            return "0" + String.valueOf(input);
        }
    }


    //untuk memilih gambar dari galeri
    private void showFileChooser1() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST_1);
    }

    //untuk set ke imageview
    private void setToImageView1(Bitmap bmp) {
        //compress image
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, bitmap_size, bytes);
        decoded_1 = BitmapFactory.decodeStream(new ByteArrayInputStream(bytes.toByteArray()));
        //menampilkan gambar yang dipilih dari camera/gallery ke ImageView
        imageupload.setImageBitmap(decoded_1);

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //ini untuk gambar
        if (requestCode == PICK_IMAGE_REQUEST_1 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            try {
                //mengambil fambar dari Gallery
                bitmap = MediaStore.Images.Media.getBitmap(KonfirmasiPembayaran.this.getContentResolver(), filePath);
                // 512 adalah resolusi tertinggi setelah image di resize, bisa di ganti.
                setToImageView1(getResizedBitmap(bitmap, 512));
            } catch (IOException e) {
                e.printStackTrace();
            }

            //ini untuk dari kamera
        } else if (requestCode == REQUEST_IMAGE_CAPTURE_1 && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            //imageView.setImageBitmap(imageBitmap);
            setToImageView1(getResizedBitmap(imageBitmap, 512));
        }
    }

    private void postBuktiTransfer(){

        HashMap<String, String> params = new HashMap<>();
        params.put("id_transaction", id_transaction.getText().toString() );
        params.put("confirm", getStringImage(decoded_1));
        params.put("bank_account_owner_name", txtnamalengkap.getText().toString() );
        params.put("bank_name", txtnamabank.getText().toString() );
        params.put("transfer_date", txt_transfer_date.getText().toString() );
        params.put("transfer_amount", txtnominal.getText().toString() );

        mApiService.postBuktiTransfer(params).enqueue(new Callback<ResponseMemberInsert>() {
            @Override
            public void onResponse(Call<ResponseMemberInsert> call, Response<ResponseMemberInsert> response) {
                if(response.body().getResponseCode()==200){
                    //Toast.makeText(KonfirmasiPembayaran.this, "Tunggu Konfirmasi dari admin", Toast.LENGTH_LONG).show();
                    AlertDialog alertDialog = new AlertDialog.Builder(KonfirmasiPembayaran.this).create();

                    alertDialog.setTitle("Sukses");
                    alertDialog.setMessage("Pembayaran Down Payment berhasil, Tunggu Konfirmasi dari admin.");
                    alertDialog.setIcon(R.drawable.successfully);
                    alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            finish();

                        }
                    });

                    alertDialog.show();
                }else {
                    Toast.makeText(KonfirmasiPembayaran.this, "Upload gagal", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseMemberInsert> call, Throwable t) {

            }
        });
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

    //untuk upload image, compress .JPEG ke bitmap
    private String getStringImage(Bitmap bmp) {
        if (bmp != null) {

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.JPEG, bitmap_size, baos);
            byte[] imageBytes = baos.toByteArray();
//        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
/*            imgktp = "2";
            imgphoto ="2";*/

            return Base64.encodeToString(imageBytes, Base64.DEFAULT);

        }
        return "";
    }

    private void setActionBarTitle(String title) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
