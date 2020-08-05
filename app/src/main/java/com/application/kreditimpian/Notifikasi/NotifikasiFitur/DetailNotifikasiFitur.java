package com.application.kreditimpian.Notifikasi.NotifikasiFitur;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.application.kreditimpian.Constan.ConstanHistoryPesanan;
import com.application.kreditimpian.Model.ModelNotifikasiFeatures.Metadata;
import com.application.kreditimpian.Notifikasi.DetailNotifikasi;
import com.application.kreditimpian.Notifikasi.Notifikasi;
import com.application.kreditimpian.R;
import com.bumptech.glide.Glide;

import java.text.NumberFormat;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailNotifikasiFitur extends AppCompatActivity {

    @BindView(R.id.txt_price_sale)
    TextView txt_price_sale;
    @BindView(R.id.txt_status_pesanan)
    TextView txt_status_pesanan;
    @BindView(R.id.txt_nomor_invoice)
    TextView txt_nomor_invoice;
    @BindView(R.id.txt_name_product)
    TextView txt_name_product;
    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.txt_nama_mitra)
    TextView txt_nama_mitra;
    @BindView(R.id.tvDownpayment)
    TextView tvDownpayment;
    @BindView(R.id.tvTenor)
    TextView tvTenor;
    @BindView(R.id.tvInstallment)
    TextView tvInstallment;
    @BindView(R.id.tvOngkoskirim)
    TextView tvOngkoskirim;
    @BindView(R.id.tvKurir)
    TextView tvKurir;
    @BindView(R.id.tvTotalPembayaran)
    TextView tvTotalPembayaran;
    @BindView(R.id.tvPaymentMethod)
    TextView tvPaymentMethod;
    @BindView(R.id.tvAlamatPengiriman)
    TextView tvAlamatPengiriman;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_notifikasi_fitur);

        ButterKnife.bind(this);
        Intent intent = getIntent();
        if (intent != null) {
            // do something you want
            String priceSale = intent.getStringExtra("price_sale");
            String downpayment = intent.getStringExtra("down_payment");
            String metadata = intent.getStringExtra("metadata");
            String content = intent.getStringExtra("image");
            String name = intent.getStringExtra("name");
            String method = intent.getStringExtra("method");
            String number = intent.getStringExtra("number");
            String namecompay = intent.getStringExtra("name_company");
            ///convert String to Rupiah Curerncy
            Locale localeID = new Locale("in", "ID");
            NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
            int price_capital = (Integer.parseInt(intent.getStringExtra("price_sale")));
            int price_sale = (Integer.parseInt(intent.getStringExtra("price_sale")));
            int down_payment = (Integer.parseInt(intent.getStringExtra("down_payment")));
            String tenor = intent.getStringExtra("tenor");
            int installment = (Integer.parseInt(intent.getStringExtra("installment")));
            int total_pembayaran = (Integer.parseInt(intent.getStringExtra("total_pembayaran")));
            int postal_fee = (Integer.parseInt(intent.getStringExtra("postal_fee")));
            String courier = intent.getStringExtra("courier");
            String payment_method = intent.getStringExtra("payment_method");
            String address_label = intent.getStringExtra("address_label");
            String receiver = intent.getStringExtra("receiver");
            String address = intent.getStringExtra("address");
            String name_district = intent.getStringExtra("name_district");
            String name_city = intent.getStringExtra("name_city");
            String postal_code = intent.getStringExtra("postal_code");
            String mobile = intent.getStringExtra("mobile");


            if (priceSale == null) {
                txt_price_sale.setText("Harga Menunggu Konfirmasi Admin");
            }
            if (namecompay == null) {
                txt_nama_mitra.setText("Anda belum memilih Mitra");
            }
            if (downpayment == null) {
                tvDownpayment.setText("Anda belum memi;ih mitra");
            }
            if (formatRupiah.format(down_payment) == null) {
                tvDownpayment.setText("Anda belum memi;ih mitra");
            }else{
                tvDownpayment.setText(formatRupiah.format(down_payment));
            }

            if (formatRupiah.format(installment) == null) {
                tvInstallment.setText("Anda belum memilih Instalment");
            }

            txt_name_product.setText(name);
            /// txt_price_sale.setText(priceSale);
            txt_price_sale.setText(formatRupiah.format(price_sale));
            txt_status_pesanan.setText(metadata);
            txt_nama_mitra.setText(namecompay);
            txt_nomor_invoice.setText("Order ID #" + number);

            tvTenor.setText(tenor + " Bulan");
            //   tvInstallment.setText(formatRupiah.format(installment));
            tvTotalPembayaran.setText(formatRupiah.format(total_pembayaran));
            tvOngkoskirim.setText(formatRupiah.format(postal_fee));
            tvKurir.setText(courier);
            tvPaymentMethod.setText(payment_method);
            Glide.with(DetailNotifikasiFitur.this)
                    .load(content)
                    .placeholder(R.drawable.no_image)
                    .error(R.drawable.no_image)
                    .into(image);


            tvAlamatPengiriman.setText("" + address_label + "\n" + "Nama penerima : " + receiver + "\n" + "Alamat :" + address + "\n" + name_district + " , " + name_city + "\n" + "Koder pos : " + postal_code + "\n" + mobile);

        } else {

            String priceSale = intent.getStringExtra("price_sale");
            String metadata = intent.getStringExtra("metadata");
            String content = intent.getStringExtra("image");
            String name = intent.getStringExtra("name");
            String method = intent.getStringExtra("method");
            String number = intent.getStringExtra("number");
            String namecompay = intent.getStringExtra("name_company");
            ///convert String to Rupiah Curerncy
            Locale localeID = new Locale("in", "ID");
            NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
            int price_capital = (Integer.parseInt(intent.getStringExtra("price_sale")));
            int price_sale = (Integer.parseInt(intent.getStringExtra("price_sale")));
            int down_payment = (Integer.parseInt(intent.getStringExtra("down_payment")));
            String tenor = intent.getStringExtra("tenor");
            int installment = (Integer.parseInt(intent.getStringExtra("installment")));
            int total_pembayaran = (Integer.parseInt(intent.getStringExtra("total_pembayaran")));
            int postal_fee = (Integer.parseInt(intent.getStringExtra("postal_fee")));
            String courier = intent.getStringExtra("courier");
            String payment_method = intent.getStringExtra("payment_method");
            String address_label = intent.getStringExtra("address_label");
            String receiver = intent.getStringExtra("receiver");
            String address = intent.getStringExtra("address");
            String name_district = intent.getStringExtra("name_district");
            String name_city = intent.getStringExtra("name_city");
            String postal_code = intent.getStringExtra("postal_code");
            String mobile = intent.getStringExtra("mobile");


            /*if (priceSale == null) {
                txt_price_sale.setText("Harga Menunggu Konfirmasi Admin");
            }
            if (namecompay == null) {
                txt_nama_mitra.setText("Anda belum memilih Mitra");
            }
            if (formatRupiah.format(down_payment) == null) {
                tvDownpayment.setText("Anda belum memilih Downpayment");
            }

            if (formatRupiah.format(installment) == null) {
                tvInstallment.setText("Anda belum memilih Installment");
            }*/

            txt_name_product.setText(name);
            /// txt_price_sale.setText(priceSale);
            txt_price_sale.setText(formatRupiah.format(price_sale));
            txt_status_pesanan.setText(metadata);
            txt_nama_mitra.setText(namecompay);
            txt_nomor_invoice.setText("Order ID #" + number);
            tvDownpayment.setText(formatRupiah.format(down_payment));
            tvTenor.setText(tenor + " Bulan");
            tvInstallment.setText(formatRupiah.format(installment));
            tvTotalPembayaran.setText(formatRupiah.format(total_pembayaran));
            tvOngkoskirim.setText(formatRupiah.format(postal_fee));
            tvKurir.setText(courier);
            tvPaymentMethod.setText(payment_method);
            Glide.with(DetailNotifikasiFitur.this)
                    .load(content)
                    .placeholder(R.drawable.no_image)
                    .error(R.drawable.no_image)
                    .into(image);




            tvAlamatPengiriman.setText("" + address_label + "\n" + "Nama penerima : " + receiver + "\n" + "Alamat :" + address + "\n" + name_district + " , " + name_city + "\n" + "Koder pos : " + postal_code + "\n" + mobile);


        }
    }

    @Override
    public void onBackPressed() {
        Intent back = new Intent(DetailNotifikasiFitur.this, Notifikasi.class);
        startActivity(back);
        finish();
    }
}
