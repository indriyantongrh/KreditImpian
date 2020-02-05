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
import com.application.kreditimpian.Model.ModelUpgradeImpian.ModelMitraMultiguna;
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

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class MultigunaMobil extends Fragment implements View.OnClickListener {
    private Context context;
    private MitraAdapter mitraAdapter;
    private UpgradeImpianViewModel upgradeImpianViewModel;
    private ProgressDialog pDialog;

    private ImageView imageupload;
    private SmartMaterialSpinner spinMerkMobil,
            spinTipeMobil,
            spinThnMobil;
    private Button btnAjukansekarang;
    private TextInputEditText
            edtJumlahPinjaman,
            edtHargaKendaraan,
            edtLokasi,
            edtAsuransi;
    private RecyclerView rvMitra;

    //untuk upload gambar
    private Bitmap decoded;
    private static final int bitmap_size = 80; // range 1 - 100=
    private static final int PICK_IMAGE_REQUEST_1 = 1;
    private static final int REQUEST_IMAGE_CAPTURE_1 = 11;
    private String idMember;

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
        spinThnMobil = view.findViewById(R.id.spinThnMobil);
        edtJumlahPinjaman = view.findViewById(R.id.txtjumlahpinjaman);
        edtHargaKendaraan = view.findViewById(R.id.txthargakendaraan);
        edtLokasi = view.findViewById(R.id.txtlokasi);
        edtAsuransi = view.findViewById(R.id.txtAsuransi);
        btnAjukansekarang = view.findViewById(R.id.btnAjukansekarang);
        rvMitra = view.findViewById(R.id.rvMitra);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        context = getContext();
        upgradeImpianViewModel = new ViewModelProvider(getViewModelStore(), new ViewModelFactory()).get(UpgradeImpianViewModel.class);

        SharedPrefManager sharedPrefManager = new SharedPrefManager(context);
        idMember = sharedPrefManager.getSpIdMember();

        imageupload.setOnClickListener(this);
        rvMitra.setLayoutManager(new LinearLayoutManager(context));
        btnAjukansekarang.setOnClickListener(this);

        edtJumlahPinjaman.addTextChangedListener(new NumberTextWatcher(edtJumlahPinjaman));
        edtHargaKendaraan.addTextChangedListener(new NumberTextWatcher(edtHargaKendaraan));

        loadMitra();
        loadKendaraan();
        loadTahunMotor();
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
        } else if (v == btnAjukansekarang) {
            kirimDataPengajuan();
        }
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
            mitraAdapter = new MitraAdapter(context,modelMitras, "mobil");
            mitraAdapter.notifyDataSetChanged();
            rvMitra.setAdapter(mitraAdapter);
        });
    }

    @SuppressWarnings("unchecked")
    private void loadKendaraan() {
        ArrayList<String> merkList = new ArrayList<>();
        String[] arrayMerk = getResources().getStringArray(R.array.listMerkMobil);
        Collections.addAll(merkList, arrayMerk);
        spinMerkMobil.setItem(merkList);
        spinMerkMobil.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ArrayList<String> tipeKendaraanList = new ArrayList<>();
                String[] arrayTipeMobil = null;
                switch (spinMerkMobil.getSelectedItem().toString()) {
                    case "AUDI":
                        arrayTipeMobil = getResources().getStringArray(R.array.mobilTipeAudi);
                        break;
                    case "BIMANTARA":
                        arrayTipeMobil = getResources().getStringArray(R.array.mobilTipeBimantara);
                        break;
                    case "BMW":
                        arrayTipeMobil = getResources().getStringArray(R.array.mobilTipeBmw);
                        break;
                    case "CADILLAC":
                        arrayTipeMobil = getResources().getStringArray(R.array.mobilTipeCadillac);
                        break;
                    case "CHEROKEE":
                        arrayTipeMobil = getResources().getStringArray(R.array.mobilTipeCherokee);
                        break;
                    case "CHERY":
                        arrayTipeMobil = getResources().getStringArray(R.array.mobilTipeChery);
                        break;
                    case "CHEVROLET":
                        arrayTipeMobil = getResources().getStringArray(R.array.mobilTipeChevrolet);
                        break;
                    case "CJ - 7":
                        arrayTipeMobil = getResources().getStringArray(R.array.mobilTipeCj7);
                        break;
                    case "DAIHATSU":
                        arrayTipeMobil = getResources().getStringArray(R.array.mobilTipeDaihatsu);
                        break;
                    case "DATSUN":
                        arrayTipeMobil = getResources().getStringArray(R.array.mobilTipeDatsun);
                        break;
                    case "DONGFENG":
                        arrayTipeMobil = getResources().getStringArray(R.array.mobilTipeDongfeng);
                        break;
                    case "FIAT":
                        arrayTipeMobil = getResources().getStringArray(R.array.mobilTipeFiat);
                        break;
                    case "FORD":
                        arrayTipeMobil = getResources().getStringArray(R.array.mobilTipeFord);
                        break;
                    case "GEELY":
                        arrayTipeMobil = getResources().getStringArray(R.array.mobilTipeGeely);
                        break;
                    case "HONDA":
                        arrayTipeMobil = getResources().getStringArray(R.array.mobilTipeHonda);
                        break;
                    case "HUMMER":
                        arrayTipeMobil = getResources().getStringArray(R.array.mobilTipeHummer);
                        break;
                    case "ISUZU":
                        arrayTipeMobil = getResources().getStringArray(R.array.mobilTipeIsuzu);
                        break;
                    case "LAND ROVER":
                        arrayTipeMobil = getResources().getStringArray(R.array.mobilTipeLandrover);
                        break;
                    case "LEXUS":
                        arrayTipeMobil = getResources().getStringArray(R.array.mobilTipeLexus);
                        break;
                    case "MAZDA":
                        arrayTipeMobil = getResources().getStringArray(R.array.mobilTipeMazda);
                        break;
                    case "MERCEDES":
                        arrayTipeMobil = getResources().getStringArray(R.array.mobilTipeMercedes);
                        break;
                    case "MERCEDES BENZ":
                        arrayTipeMobil = getResources().getStringArray(R.array.mobilTipeMercedes);
                        break;
                    case "MINI":
                        arrayTipeMobil = getResources().getStringArray(R.array.mobilTipeMini);
                        break;
                    case "MITSUBISHI":
                        arrayTipeMobil = getResources().getStringArray(R.array.mobilTipeMiitsubishi);
                        break;
                    case "MORRIS":
                        arrayTipeMobil = getResources().getStringArray(R.array.mobilTipeMorris);
                        break;
                    case "NISSAN":
                        arrayTipeMobil = getResources().getStringArray(R.array.mobilTipeNissan);
                        break;
                    case "OPEL":
                        arrayTipeMobil = getResources().getStringArray(R.array.mobilTipeOpel);
                        break;
                    case "PEUGEOT":
                        arrayTipeMobil = getResources().getStringArray(R.array.mobilTipePeugeot);
                        break;
                    case "PORSCHE":
                        arrayTipeMobil = getResources().getStringArray(R.array.mobilTipePorsche);
                        break;
                    case "PROTON":
                        arrayTipeMobil = getResources().getStringArray(R.array.mobilTipeProton);
                        break;
                    case "RANGE ROVER":
                        arrayTipeMobil = getResources().getStringArray(R.array.mobilTipeRangerover);
                        break;
                    case "RENAULT":
                        arrayTipeMobil = getResources().getStringArray(R.array.mobilTipeRenault);
                        break;
                    case "SCANIA":
                        arrayTipeMobil = getResources().getStringArray(R.array.mobilTipeScania);
                        break;
                    case "SUBARU":
                        arrayTipeMobil = getResources().getStringArray(R.array.mobilTipeSubaru);
                        break;
                    case "SUZUKI":
                        arrayTipeMobil = getResources().getStringArray(R.array.mobilTipeSuzuki);
                        break;
                    case "TATA":
                        arrayTipeMobil = getResources().getStringArray(R.array.mobilTipeTata);
                        break;
                    case "TIMOR":
                        arrayTipeMobil = getResources().getStringArray(R.array.mobilTipeTimor);
                        break;
                    case "TOYOTA":
                        arrayTipeMobil = getResources().getStringArray(R.array.mobilTipeToyota);
                        break;
                    case "UD TRUCKS":
                        arrayTipeMobil = getResources().getStringArray(R.array.mobilTipeUdtrucks);
                        break;
                    case "VOLVO":
                        arrayTipeMobil = getResources().getStringArray(R.array.mobilTipeVolvo);
                        break;
                    case "VW":
                        arrayTipeMobil = getResources().getStringArray(R.array.mobilTipeVw);
                        break;
                    case "WRANGLER":
                        arrayTipeMobil = getResources().getStringArray(R.array.mobilTipeWrangler);
                        break;
                    case "WULING":
                        arrayTipeMobil = getResources().getStringArray(R.array.mobilTipeWuling);
                        break;
                }

                if (arrayTipeMobil != null) {
                    Collections.addAll(tipeKendaraanList, arrayTipeMobil);
                    spinTipeMobil.setItem(tipeKendaraanList);
                }
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
        spinThnMobil.setItem(listTahun);
    }

    private void kirimDataPengajuan() {
        boolean check = false;
        String jmlhPinj = String.valueOf(edtJumlahPinjaman.getText()).replace(".", "");
        String hrgKend = String.valueOf(edtHargaKendaraan.getText()).replace(".", "");
        String lokasi = String.valueOf(edtLokasi.getText());
        String asuransi = String.valueOf(edtAsuransi.getText());
        StringBuilder mitraStringBuilder = new StringBuilder();
        ArrayList<ModelMitraMultiguna> modelMitraArrayList = mitraAdapter.getMitraList();
        ModelMitraMultiguna modelMitra = new ModelMitraMultiguna();
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
        } else if (spinMerkMobil.getSelectedItemPosition() < 0) {
            Toast.makeText(context, "Belum memilih Merk Sepeda Motor", Toast.LENGTH_LONG).show();
        } else if (spinTipeMobil.getSelectedItemPosition() < 0) {
            Toast.makeText(context, "Belum memilih Tipe Sepeda Motor", Toast.LENGTH_LONG).show();
        } else if (spinThnMobil.getSelectedItemPosition() < 0) {
            Toast.makeText(context, "Belum memilih Tahun mobil", Toast.LENGTH_LONG).show();
        } else if (lokasi.isEmpty()) {
            edtLokasi.setError(getResources().getString(R.string.lokasikosong));
            edtLokasi.requestFocus();
        } else if (asuransi.isEmpty()) {
            edtLokasi.setError(getResources().getString(R.string.asuransikosong));
            edtLokasi.requestFocus();
        } else if (!check) {
            Toast.makeText(context, "Silahkan pilih leasing\nminimal 1\nmaximal 3", Toast.LENGTH_LONG).show();
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
            modelUpgradeImpian.setMerkkendaraan(spinMerkMobil.getSelectedItem().toString());
            modelUpgradeImpian.setTipekendaraan(spinTipeMobil.getSelectedItem().toString());
            modelUpgradeImpian.setTahun(spinThnMobil.getSelectedItem().toString());
            modelUpgradeImpian.setAsuransi(asuransi);
            modelUpgradeImpian.setLokasi(lokasi);
            modelUpgradeImpian.setMitra(mitraStringBuilder.toString());
            modelUpgradeImpian.setImage(getStringImage(decoded));

            upgradeImpianViewModel.setModelUpgradeImpian(modelUpgradeImpian);
            upgradeImpianViewModel.pengajuanMobil().observe(this, modelUpgradeImpians -> {
                pDialog.dismiss();
                ModelUpgradeImpian modelUpgradeImpian1 = modelUpgradeImpians.get(0);
                Intent intent = new Intent(context, PilihLeasingActivity.class);
                intent.putExtra("data", modelUpgradeImpian1);
                intent.putExtra("code", "mobil");
                context.startActivity(intent);
                ((Activity) context).finish();
            });
        }
    }
}
