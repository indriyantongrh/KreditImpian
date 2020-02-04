package com.application.kreditimpian.FormPengajuan.UpgradeImpian;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.application.kreditimpian.Api.SharedPrefManager;
import com.application.kreditimpian.FormPengajuan.UpgradeImpian.viewmodel.UpgradeImpianViewModel;
import com.application.kreditimpian.FormPengajuan.UpgradeImpian.viewmodel.ViewModelFactory;
import com.application.kreditimpian.Model.ModelMitra;
import com.application.kreditimpian.Model.ModelUpgradeImpian.ModelUpgradeImpian;
import com.application.kreditimpian.R;
import com.chivorn.smartmaterialspinner.SmartMaterialSpinner;
import com.google.android.material.textfield.TextInputEditText;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
    private MitraAdapter mitraAdapter;
    private UpgradeImpianViewModel upgradeImpianViewModel;
    private ProgressDialog pDialog;

    private ImageView imageupload;
    private RecyclerView rvMitra;
    private SmartMaterialSpinner spinnerMerkMotor,
            spinTipeMotor,
            spinThnMotor;
    private TextInputEditText
            edtJumlahPinjaman,
            edtHargaKendaraan,
            edtLokasi;
    private Button btnAjukansekarang;

    //untuk upload gambar
    private String idMember;
    private Bitmap decoded;
    private static final int bitmap_size = 80; // range 1 - 100=
    private static final int PICK_IMAGE_REQUEST_1 = 1;
    private static final int REQUEST_IMAGE_CAPTURE_1 = 11;

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
        spinThnMotor = view.findViewById(R.id.spinThnMotor);
        edtJumlahPinjaman = view.findViewById(R.id.txtjumlahpinjaman);
        edtHargaKendaraan = view.findViewById(R.id.txthargakendaraan);
        edtLokasi = view.findViewById(R.id.txtlokasi);
        btnAjukansekarang = view.findViewById(R.id.btnAjukansekarang);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        context = getContext();
        upgradeImpianViewModel = new ViewModelProvider(getViewModelStore(), new ViewModelFactory()).get(UpgradeImpianViewModel.class);

        SharedPrefManager sharedPrefManager = new SharedPrefManager(context);
        idMember = sharedPrefManager.getSpIdMember();

        imageupload.setOnClickListener(this);
        btnAjukansekarang.setOnClickListener(this);
        rvMitra.setLayoutManager(new LinearLayoutManager(context));
        mitraAdapter = new MitraAdapter(context);

        edtJumlahPinjaman.addTextChangedListener(new NumberTextWatcher(edtJumlahPinjaman));
        edtHargaKendaraan.addTextChangedListener(new NumberTextWatcher(edtHargaKendaraan));

        loadMitra();
        loadMerkKendaraan();
        loadTahunMotor();
    }

    @Override
    public void onClick(View v) {
        if (v == imageupload) {
            tampilDialogGalery();
        } else if (v == btnAjukansekarang) {
            kirimDataPengajuan();
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


    private String getStringImage(Bitmap bmp) {
        if (bmp != null) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.JPEG, bitmap_size, baos);
            byte[] imageBytes = baos.toByteArray();
//        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
            return Base64.encodeToString(imageBytes, Base64.DEFAULT);
        }
        return "";
    }

    private void loadMitra() {
        upgradeImpianViewModel.getMitraUpgradeImpian().observe(getViewLifecycleOwner(), modelMitras -> {
            mitraAdapter.setMitraList(modelMitras, "motor");
            rvMitra.setAdapter(mitraAdapter);
        });
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
                String[] arrayTipeMotor = null;

                switch (spinnerMerkMotor.getSelectedItem().toString()) {
                    case "HONDA":
                        arrayTipeMotor = getResources().getStringArray(R.array.motorTipeHonda);
                        Collections.addAll(tipeKendaraanList, arrayTipeMotor);
                        break;
                    case "KAWAZAKI":
                        arrayTipeMotor = getResources().getStringArray(R.array.motorTipeKawazaki);
                        Collections.addAll(tipeKendaraanList, arrayTipeMotor);
                        break;
                    case "PIAGGIO":
                        arrayTipeMotor = getResources().getStringArray(R.array.motorTipePiaggio);
                        Collections.addAll(tipeKendaraanList, arrayTipeMotor);
                        break;
                    case "SUZUKI":
                        arrayTipeMotor = getResources().getStringArray(R.array.motorTipeYamaha);
                        Collections.addAll(tipeKendaraanList, arrayTipeMotor);
                        break;
                    case "YAMAHA":
                        arrayTipeMotor = getResources().getStringArray(R.array.motorTipeSuzuki);
                        break;
                }

                if (arrayTipeMotor != null) {
                    Collections.addAll(tipeKendaraanList, arrayTipeMotor);
                    spinTipeMotor.setItem(tipeKendaraanList);
                }
                mitraAdapter.setMerkKendaraan(spinnerMerkMotor.getSelectedItem().toString());
                mitraAdapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    @SuppressWarnings("unchecked")
    private void loadTahunMotor() {
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy", Locale.getDefault());
        int tahunMotor = Integer.valueOf(dateFormat.format(date));
        String[] arrayTahun = new String[7];
        arrayTahun[0] = String.valueOf(tahunMotor);
        for (int i = 1; i <= 6; i++) {
            tahunMotor = tahunMotor - 1;
            arrayTahun[i] = String.valueOf(tahunMotor);
        }
        List<String> listTahun = new ArrayList<>();
        Collections.addAll(listTahun, arrayTahun);
        spinThnMotor.setItem(listTahun);
    }

    private void kirimDataPengajuan() {
        boolean check = false;
        String jmlhPinj = String.valueOf(edtJumlahPinjaman.getText()).replace(".", "");
        String hrgKend = String.valueOf(edtHargaKendaraan.getText()).replace(".", "");
        String lokasi = String.valueOf(edtLokasi.getText());
        StringBuilder mitraStringBuilder = new StringBuilder();
        ArrayList<ModelMitra> modelMitraArrayList = mitraAdapter.getMitraList();
        ModelMitra modelMitra = new ModelMitra();
        for (int i = 0; i < modelMitraArrayList.size(); i++) {
            modelMitra = modelMitraArrayList.get(i);
            if (modelMitra.isChecked()) {
                mitraStringBuilder.append(modelMitra.getId()).append("|");
            }
        }
        for (int i = 0; i < modelMitraArrayList.size(); i++) {
            if (modelMitra.isChecked()) {
                check = true;
                break;
            }
        }
        if (jmlhPinj.isEmpty()) {
            edtJumlahPinjaman.setError(getResources().getString(R.string.jmlhpinjkosong));
            edtJumlahPinjaman.requestFocus();
        } else if (hrgKend.isEmpty()) {
            edtHargaKendaraan.setError(getResources().getString(R.string.hrgkendkosong));
            edtHargaKendaraan.requestFocus();
        } else if (spinnerMerkMotor.getSelectedItemPosition() < 0) {
            Toast.makeText(context, "Belum memilih Merk Sepeda Motor", Toast.LENGTH_LONG).show();
        } else if (spinTipeMotor.getSelectedItemPosition() < 0) {
            Toast.makeText(context, "Belum memilih Tipe Sepeda Motor", Toast.LENGTH_LONG).show();
        } else if (spinThnMotor.getSelectedItemPosition() < 0) {
            Toast.makeText(context, "Belum memilih Tahun Sepeda Motor", Toast.LENGTH_LONG).show();
        } else if (lokasi.isEmpty()) {
            edtLokasi.setError(getResources().getString(R.string.lokasikosong));
            edtLokasi.requestFocus();
        } else if (!check) {
            Toast.makeText(context, "Silahkan pilih leasing\nmin 1\nmax 3", Toast.LENGTH_LONG).show();
        } else if (getStringImage(decoded).isEmpty()) {
            Toast.makeText(context, "Silahkan Foto BPKB", Toast.LENGTH_LONG).show();
        } else if (Integer.valueOf(jmlhPinj) > Integer.valueOf(hrgKend)) {
            Toast.makeText(context, "Jumlah pinjaman tidak boleh lebih besar dari harga kendaraan", Toast.LENGTH_LONG).show();
        } else {

            pDialog = new ProgressDialog(context);
            pDialog.setCancelable(false);
            pDialog.setMessage("Loading...");
            pDialog.show();

            ModelUpgradeImpian modelUpgradeImpian = new ModelUpgradeImpian();
            modelUpgradeImpian.setIdmember(idMember);
            modelUpgradeImpian.setJmlhpinjaman(jmlhPinj);
            modelUpgradeImpian.setHrgkendaraan(hrgKend);
            modelUpgradeImpian.setMerkkendaraan(spinnerMerkMotor.getSelectedItem().toString());
            modelUpgradeImpian.setTipekendaraan(spinTipeMotor.getSelectedItem().toString());
            modelUpgradeImpian.setTahun(spinThnMotor.getSelectedItem().toString());
            modelUpgradeImpian.setLokasi(lokasi);
            modelUpgradeImpian.setMitra(mitraStringBuilder.toString());
            modelUpgradeImpian.setImage(getStringImage(decoded));

            upgradeImpianViewModel.setModelUpgradeImpian(modelUpgradeImpian);
            upgradeImpianViewModel.pengajuanMotor().observe(this, modelUpgradeImpians -> {
                pDialog.dismiss();
                ModelUpgradeImpian modelUpgradeImpian1 = modelUpgradeImpians.get(0);
                Intent intent = new Intent(context, PilihLeasingActivity.class);
                intent.putExtra("data", modelUpgradeImpian1);
                intent.putExtra("code", "motor");
                context.startActivity(intent);
                ((Activity) context).finish();
            });
        }
    }

}
