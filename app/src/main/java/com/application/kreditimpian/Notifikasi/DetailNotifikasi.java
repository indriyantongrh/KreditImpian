package com.application.kreditimpian.Notifikasi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.application.kreditimpian.Api.api_v2.BaseApiService;
import com.application.kreditimpian.Constan.ConstanHistoryPesanan;
import com.application.kreditimpian.R;
import com.bumptech.glide.Glide;

import java.text.NumberFormat;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailNotifikasi extends AppCompatActivity {

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
    @BindView(R.id.txt_price_capital)
    TextView txt_price_capital;
    @BindView(R.id.txt_price_sale)
    TextView txt_price_sale;
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
    @BindView(R.id.informationTransfer)
    LinearLayout informationTransfer;
    @BindView(R.id.tvAlamatPengiriman)
    TextView tvAlamatPengiriman;
    @BindView(R.id.btnuploaddp)
    Button btnuploaddp;
    private String KEY_ID_TRANSACTION = "id_transaction";
    BaseApiService mApiService;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_notifikasi);

        ButterKnife.bind(this);
        Intent intent = getIntent();

        String status = intent.getStringExtra("metadata");
        String number = intent.getStringExtra(ConstanHistoryPesanan.KEY_NUMBER);
        String id_product = intent.getStringExtra(ConstanHistoryPesanan.KEY_ID_PRODUCT);
        String id_product_category = intent.getStringExtra(ConstanHistoryPesanan.KEY_ID_PRODUCT_CATEGORY);
        String id_currency = intent.getStringExtra(ConstanHistoryPesanan.KEY_CURRENCY);
        String name = intent.getStringExtra(ConstanHistoryPesanan.KEY_NAME_PRODUCT);
        String description = intent.getStringExtra(ConstanHistoryPesanan.KEY_DESCRIPTION);
        String sku = intent.getStringExtra(ConstanHistoryPesanan.KEY_SKU);
        String stock = intent.getStringExtra(ConstanHistoryPesanan.KEY_STOCK);
        String name_city = intent.getStringExtra(ConstanHistoryPesanan.KEY_NAME_CITY);
        String name_district = intent.getStringExtra(ConstanHistoryPesanan.KEY_NAME_DISTRICT);
        String postal_code = intent.getStringExtra(ConstanHistoryPesanan.KEY_POSTAL_CODE);

       /* String price_capital = intent.getStringExtra(ConstanHistoryPesanan.KEY_PRICE_CAPITAL);
        String price_sale = intent.getStringExtra(ConstanHistoryPesanan.KEY_PRICE_SALE);*/

        ///convert String to Rupiah Curerncy
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        int price_capital = (Integer.parseInt(intent.getStringExtra(ConstanHistoryPesanan.KEY_PRICE_CAPITAL)));
        int price_sale = (Integer.parseInt(intent.getStringExtra(ConstanHistoryPesanan.KEY_PRICE_SALE)));

        if (price_capital == price_sale) {
            txt_price_capital.setVisibility(View.GONE);
        } else {
            txt_price_capital.setVisibility(View.VISIBLE);
        }


        String filename = intent.getStringExtra(ConstanHistoryPesanan.KEY_FILENAME);
        String name_merchant = intent.getStringExtra(ConstanHistoryPesanan.KEY_NAME_MERCHANT);
        String name_company = intent.getStringExtra(ConstanHistoryPesanan.KEY_NAME_COMPANY);
        String tenor = intent.getStringExtra(ConstanHistoryPesanan.KEY_TENOR);
        ///String down_payment = intent.getStringExtra(ConstanHistoryPesanan.KEY_DOWN_PAYMENT);
        int down_payment = (Integer.parseInt(intent.getStringExtra(ConstanHistoryPesanan.KEY_DOWN_PAYMENT)));
        String note = intent.getStringExtra(ConstanHistoryPesanan.KEY_NOTE);

        /// String postal_fee = intent.getStringExtra(ConstanHistoryPesanan.KEY_POSTAL_FEE);
        int postal_fee = (Integer.parseInt(intent.getStringExtra(ConstanHistoryPesanan.KEY_POSTAL_FEE)));
        /*String address_label = intent.getStringExtra(ConstanHistoryPesanan.KEY_ADDRESS_LABEL);
        String receiver = intent.getStringExtra(ConstanHistoryPesanan.KEY_RECEIVER);
        String mobile = intent.getStringExtra(ConstanHistoryPesanan.KEY_MOBILE);
        String city = intent.getStringExtra(ConstanHistoryPesanan.KEY_CITY);
        String district = intent.getStringExtra(ConstanHistoryPesanan.KEY_DISTRICT);
        String address = intent.getStringExtra(ConstanHistoryPesanan.KEY_ADDRESS);*/
        String payment_method = intent.getStringExtra(ConstanHistoryPesanan.KEY_PAYMENT_METHOD);
        //String installment = intent.getStringExtra(ConstanHistoryPesanan.KEY_INSTALLMENT);
        ///int installment = (Integer.parseInt(intent.getStringExtra(ConstanHistoryPesanan.KEY_INSTALLMENT)));
        int total_pembayaran = (Integer.parseInt(intent.getStringExtra(ConstanHistoryPesanan.KEY_TOTAL_PEMBAYARAN)));
        /// String total_pembayaran = intent.getStringExtra(ConstanHistoryPesanan.KEY_TOTAL_PEMBAYARAN);
        String courier = intent.getStringExtra(ConstanHistoryPesanan.KEY_COURIER);

        Toast.makeText(DetailNotifikasi.this, "cek value "+price_capital, Toast.LENGTH_LONG).show();


        txt_status_pesanan.setText(status);
        txt_nama_mitra.setText(name_company);
        txt_name_product.setText(name);
        txt_nomor_invoice.setText("Order ID #"+number);
        /*txt_price_capital.setText(price_capital);
        txt_price_sale.setText(price_sale);*/
        tvDownpayment.setText(formatRupiah.format(down_payment));
        ///tvInstallment.setText(formatRupiah.format(installment)+" /bulan");
        tvOngkoskirim.setText(formatRupiah.format(postal_fee));
        tvKurir.setText(courier);
        tvPaymentMethod.setText(payment_method);
        tvTenor.setText(tenor+" Bulan");
        tvTotalPembayaran.setText(formatRupiah.format(total_pembayaran));
        txt_price_capital.setText(formatRupiah.format(price_capital));
        txt_price_sale.setText(formatRupiah.format(price_sale));
        Glide.with(DetailNotifikasi.this)
                .load(filename)
                .placeholder(R.drawable.no_image)
                .error(R.drawable.no_image)
                .into(image);

    }

    @Override
    public void onBackPressed() {
        Intent back = new Intent(DetailNotifikasi.this, NotifikasiActivity.class);
        startActivity(back);
        finish();
    }
}
