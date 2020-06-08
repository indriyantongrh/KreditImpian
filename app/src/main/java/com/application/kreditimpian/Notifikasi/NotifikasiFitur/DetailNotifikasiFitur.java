package com.application.kreditimpian.Notifikasi.NotifikasiFitur;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.application.kreditimpian.Constan.ConstanHistoryPesanan;
import com.application.kreditimpian.Notifikasi.DetailNotifikasi;
import com.application.kreditimpian.Notifikasi.Notifikasi;
import com.application.kreditimpian.R;
import com.bumptech.glide.Glide;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_notifikasi_fitur);

        ButterKnife.bind(this);
        Intent intent = getIntent();
        String priceSale = intent.getStringExtra("price_sale");
        String metadata = intent.getStringExtra("metadata");
        String content = intent.getStringExtra("content");
        String name = intent.getStringExtra("name");
        String method = intent.getStringExtra("method");


        txt_name_product.setText(name);
        txt_price_sale.setText(priceSale);
        txt_status_pesanan.setText(metadata);
        txt_nomor_invoice.setText(method);
        Glide.with(DetailNotifikasiFitur.this)
                .load(content)
                .placeholder(R.drawable.no_image)
                .error(R.drawable.no_image)
                .into(image);

         if(priceSale==null){
             txt_price_sale.setText("Harga Menunggu Konfirmasi Admin");
        }
    }

    @Override
    public void onBackPressed() {
        Intent back = new Intent(DetailNotifikasiFitur.this, Notifikasi.class);
        startActivity(back);
        finish();
    }
}
