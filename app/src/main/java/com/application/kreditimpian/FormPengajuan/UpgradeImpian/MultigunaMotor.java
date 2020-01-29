package com.application.kreditimpian.FormPengajuan.UpgradeImpian;


import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.Toast;

import com.application.kreditimpian.R;
import com.chivorn.smartmaterialspinner.SmartMaterialSpinner;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class MultigunaMotor extends Fragment implements View.OnClickListener {
    private Context context;

    private ImageView imageupload;
    private RecyclerView rvMitra;
    private SmartMaterialSpinner spinnerMerkMotor,
            spinTipeMotor;
    //untuk upload gambar
    Bitmap bitmap, decoded_1, decoded_2, decoded_3;
    int bitmap_size = 80; // range 1 - 100=
    int PICK_IMAGE_REQUEST_1 = 1;
    int REQUEST_IMAGE_CAPTURE_1 = 11;

    public MultigunaMotor() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_multiguna_motor, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imageupload = view.findViewById(R.id.imageupload);
        rvMitra = view.findViewById(R.id.rvMitra);
        spinnerMerkMotor = view.findViewById(R.id.spinMerkMotor);
        spinTipeMotor = view.findViewById(R.id.spinTipeMotor);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        context = getContext();
        imageupload.setOnClickListener(this);
        rvMitra.setLayoutManager(new LinearLayoutManager(context));
        loadMitra();
        loadMerkKendaraan();
    }

    @Override
    public void onClick(View v) {
        if (v == imageupload) {
            tampilDialogGalery();
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
                bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), filePath);
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


    private void tampilDialogGalery() {
        //pakai alert dialog
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(getActivity());
        builder.setTitle("Unggah BPKB Motor anda");
        builder.setMessage("Silahkan memilih kamera atau galeri");
        builder.setPositiveButton("Kamera", (dialog, which) -> {
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (takePictureIntent.resolveActivity(Objects.requireNonNull(getActivity()).getPackageManager()) != null) {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE_1);
            }
        });
        builder.setNegativeButton("Galeri", (dialog, which) -> showFileChooser1());
        android.app.AlertDialog alert = builder.create();
        alert.show();
    }

    private void loadMitra() {
        List<String> mitraList = Arrays.asList(getResources().getStringArray(R.array.mitra));
        MitraAdapter mitraAdapter = new MitraAdapter(context, mitraList);
        rvMitra.setAdapter(mitraAdapter);
    }

    @SuppressWarnings("unchecked")
    private void loadMerkKendaraan() {
        ArrayList<String> merkList = new ArrayList<>();
        String[] arrayMerk = getResources().getStringArray(R.array.listMerkMotor);
        Collections.addAll(merkList, arrayMerk);
        spinnerMerkMotor.setItem(merkList);
        spinnerMerkMotor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ArrayList<String> tipeKendaraanList = new ArrayList<>();
                String[] arrayTipeHonda = getResources().getStringArray(R.array.motorTipeHonda);
                String[] arrayTipeKawazaki = getResources().getStringArray(R.array.motorTipeKawazaki);
                String[] arrayTipePiaggio = getResources().getStringArray(R.array.motorTipePiaggio);

                if (spinnerMerkMotor.getSelectedItem().toString().equals("HONDA")) {
                    Collections.addAll(tipeKendaraanList, arrayTipeHonda);
                    spinTipeMotor.setItem(tipeKendaraanList);
                } else if (spinnerMerkMotor.getSelectedItem().toString().equals("KAWAZAKI")) {
                    Collections.addAll(tipeKendaraanList, arrayTipeKawazaki);
                    spinTipeMotor.setItem(tipeKendaraanList);
                } else if (spinnerMerkMotor.getSelectedItem().toString().equals("PIAGGIO")) {
                    Collections.addAll(tipeKendaraanList, arrayTipePiaggio);
                    spinTipeMotor.setItem(tipeKendaraanList);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
