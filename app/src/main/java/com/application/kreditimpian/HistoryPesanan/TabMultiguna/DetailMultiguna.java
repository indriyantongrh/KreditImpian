package com.application.kreditimpian.HistoryPesanan.TabMultiguna;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.application.kreditimpian.Api.SharedPrefManager;
import com.application.kreditimpian.Api.api_v2.UtilsApi;
import com.application.kreditimpian.Constan.ConstanHistoryPesanan;
import com.application.kreditimpian.HistoryPesanan.TabRiwayatPesananan.DetailHistoryPesanan;
import com.application.kreditimpian.R;
import com.bumptech.glide.Glide;

import java.text.NumberFormat;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailMultiguna extends AppCompatActivity {

    SharedPrefManager sharedPrefManager;
    @BindView(R.id.txt_status_pesanan)
    TextView txt_status_pesanan;
    @BindView(R.id.txt_nomor_invoice)
    TextView txt_nomor_invoice;
    @BindView(R.id.txt_nama_mitra)
    TextView txt_nama_mitra;
    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.txt_name_product)
    TextView txt_name_product;
    @BindView(R.id.tvPriceKendaraan)
    TextView tvPriceKendaraan;
    @BindView(R.id.tvMerkKendaraan)
    TextView tvMerkKendaraan;
    @BindView(R.id.tvNamaKendaraan)
    TextView tvNamaKendaraan;
    @BindView(R.id.tvTypeKendaraan)
    TextView tvTypeKendaraan;
    @BindView(R.id.tvLokasiAnda)
    TextView tvLokasiAnda;
    @BindView(R.id.tvTahunKenadaraan)
    TextView tvTahunKenadaraan;
    @BindView(R.id.tvJumlahPinjaman)
    TextView tvJumlahPinjaman;
    @BindView(R.id.tvTenor)
    TextView tvTenor;
    @BindView(R.id.tvAlamatPengiriman)
    TextView tvAlamatPengiriman;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_multiguna);

        setActionBarTitle("Detail Transaksi");
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);// set drawable icon
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        sharedPrefManager = new SharedPrefManager(this);

        ButterKnife.bind(this);
        Intent intent = getIntent();
        String id_transaction = intent.getStringExtra("id_transaction");
        String status = intent.getStringExtra("status");
        String tenor = intent.getStringExtra("tenor");
        String number = intent.getStringExtra("number");
        String location = intent.getStringExtra("location");
        String cicilan = intent.getStringExtra("cicilan");
        String vehicle_year = intent.getStringExtra("vehicle_year");
        String vehicle_type = intent.getStringExtra("vehicle_type");
        String vehicle_brand = intent.getStringExtra("vehicle_brand");
        ///String vehicle_price = intent.getStringExtra("vehicle_price");
       /// String loan = intent.getStringExtra("loan");
        String vehicles = intent.getStringExtra("vehicles");
        String mitra_kredit = intent.getStringExtra("mitra_kredit");
        String name_city = intent.getStringExtra("name_city");
        String receiver = intent.getStringExtra("receiver");
        String address_name = intent.getStringExtra("address_name");
        String name_district = intent.getStringExtra("name_district");
        String postal_code = intent.getStringExtra("postal_code");
        String phone = intent.getStringExtra("phone");
        String address = intent.getStringExtra("address");
        String images = intent.getStringExtra("images");
        ///convert String to Rupiah Curerncy
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        int price_motorcycle = (Integer.parseInt(intent.getStringExtra("vehicle_price")));
        int pinjaman = (Integer.parseInt(intent.getStringExtra("loan")));

        txt_status_pesanan.setText(status);
        txt_nomor_invoice.setText("ORDER ID "+number);
        txt_nama_mitra.setText(mitra_kredit);
        if (vehicles.equals("motorcycle")){
            txt_name_product.setText("Multiguna Motor");
            tvTypeKendaraan.setText("Sepeda Motor");
        }else if(vehicles.equals("car")){
            txt_name_product.setText("Multiguna Mobil");
            tvTypeKendaraan.setText("Mobil");
        }

        tvPriceKendaraan.setText(formatRupiah.format(price_motorcycle));
        tvMerkKendaraan.setText(vehicle_brand);
        tvNamaKendaraan.setText(vehicle_type);

        tvLokasiAnda.setText(location);
        tvTahunKenadaraan.setText(vehicle_year);
        tvJumlahPinjaman.setText(formatRupiah.format(pinjaman));
        tvTenor.setText(tenor +"Bulan");
        tvAlamatPengiriman.setText(""+address_name+"\n"+"Nama penerima : " +receiver+ "\n" + "Alamat :" +address+ "\n" +name_district+" , "+name_city+"\n"+"Koder pos : "+postal_code+"\n"+phone);
        Glide.with(DetailMultiguna.this)
                .load(images)
                .placeholder(R.drawable.no_image)
                .error(R.drawable.no_image)
                .into(image);


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
        ///finish();
        super.onBackPressed();
        finish();

    }

}
