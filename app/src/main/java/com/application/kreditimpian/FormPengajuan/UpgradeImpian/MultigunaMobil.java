package com.application.kreditimpian.FormPengajuan.UpgradeImpian;


import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.application.kreditimpian.R;
import com.chivorn.smartmaterialspinner.SmartMaterialSpinner;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class MultigunaMobil extends Fragment implements View.OnClickListener {

    private ImageView imageupload;
    private SmartMaterialSpinner spinMerkMobil,
    spinTipeMobil;
    private Button btnAjukansekarang;
    //untuk upload gambar
    private Bitmap decoded;
    private static final int bitmap_size = 80; // range 1 - 100=
    private static final int PICK_IMAGE_REQUEST_1 = 1;
    private static final int REQUEST_IMAGE_CAPTURE_1 = 11;

    public MultigunaMobil() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_multiguna_mobil, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imageupload = view.findViewById(R.id.imageupload);
        spinMerkMobil = view.findViewById(R.id.spinMerkMobil);
        spinTipeMobil = view.findViewById(R.id.spinTipeMobil);
        btnAjukansekarang = view.findViewById(R.id.btnAjukansekarang);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        imageupload.setOnClickListener(this);
        loadKendaraan();
    }

    @Override
    public void onClick(View v) {
        if (v == imageupload) {
            android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(getActivity());
            builder.setTitle("Unggah BPKB Mobil anda");
            builder.setMessage("Silahkan memilih kamera atau galeri");
            builder.setPositiveButton("Kamera", (dialog, which) -> {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE_1);
                }
            });
            builder.setNegativeButton("Galeri", (dialog, which) -> showFileChooser1());

            android.app.AlertDialog alert = builder.create();
            alert.show();
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
        decoded = BitmapFactory.decodeStream(new ByteArrayInputStream(bytes.toByteArray()));
        //menampilkan gambar yang dipilih dari camera/gallery ke ImageView
        imageupload.setImageBitmap(decoded);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //ini untuk gambar
        if (requestCode == PICK_IMAGE_REQUEST_1 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            try {
                //mengambil fambar dari Gallery
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), filePath);
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

    // fungsi resize image
    private Bitmap getResizedBitmap(Bitmap image, int maxSize) {
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
    private void loadKendaraan(){
        ArrayList<String> merkList = new ArrayList<>();
        String[] arrayMerk = getResources().getStringArray(R.array.listMerkMobil);
        Collections.addAll(merkList, arrayMerk);
        spinMerkMobil.setItem(merkList);
    }
}
