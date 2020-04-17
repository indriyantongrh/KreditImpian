package com.application.kreditimpian.HistoryPesanan.TabProductRequest;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.application.kreditimpian.Api.SharedPrefManager;
import com.application.kreditimpian.Api.api_v2.BaseApiService;
import com.application.kreditimpian.Api.api_v2.UtilsApi;
import com.application.kreditimpian.Constan.ConstanHistoryPesanan;
import com.application.kreditimpian.R;
import com.application.kreditimpian.TransactionProcess.TransactionSelectMitra;
import com.bumptech.glide.Glide;

import java.text.NumberFormat;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailHistoryRequestProduct extends AppCompatActivity {
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
    @BindView(R.id.informationNextTransaction)
    LinearLayout informationNextTransaction;
    @BindView(R.id.tvAlamatPengiriman)
    TextView tvAlamatPengiriman;
    @BindView(R.id.btnNext)
    Button btnNext;
    private String KEY_ID_TRANSACTION = "id_transaction";
    BaseApiService mApiService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_history_request_product);

        setActionBarTitle("Detail Transaksi");
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);// set drawable icon
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        sharedPrefManager = new SharedPrefManager(this);
        mApiService = UtilsApi.getAPIService();


        ButterKnife.bind(this);
        Intent intent = getIntent();
        String id = intent.getStringExtra(ConstanHistoryPesanan.KEY_ID);
        String id_transactions = intent.getStringExtra(ConstanHistoryPesanan.KEY_ID_TRANSACTION);
        String status = intent.getStringExtra(ConstanHistoryPesanan.KEY_STATUS);
        String number = intent.getStringExtra(ConstanHistoryPesanan.KEY_NUMBER);
        String name = intent.getStringExtra(ConstanHistoryPesanan.KEY_NAME_PRODUCT);
        String name_city = intent.getStringExtra(ConstanHistoryPesanan.KEY_NAME_CITY);
        String name_district = intent.getStringExtra(ConstanHistoryPesanan.KEY_NAME_DISTRICT);
        String postal_code = intent.getStringExtra(ConstanHistoryPesanan.KEY_POSTAL_CODE);
        String id_product_category = intent.getStringExtra(ConstanHistoryPesanan.KEY_ID_PRODUCT_CATEGORY);
        String id_product = intent.getStringExtra(ConstanHistoryPesanan.KEY_ID_PRODUCT);
        String price_sale = intent.getStringExtra(ConstanHistoryPesanan.KEY_PRICE_SALE);
        String filename = intent.getStringExtra("content");
        String weight_value = intent.getStringExtra("weight_value");
        String id_geodirectory = intent.getStringExtra("id_geodirectory");
        String member_city = intent.getStringExtra("member_city");

        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        int harga_barang = (Integer.parseInt(intent.getStringExtra(ConstanHistoryPesanan.KEY_PRICE_SALE)));


        txt_nomor_invoice.setText("Order ID #"+number);
       txt_status_pesanan.setText(status);
       txt_name_product.setText(name);
       txt_price_sale.setText(formatRupiah.format(harga_barang));


        Glide.with(DetailHistoryRequestProduct.this)
                .load(filename)
                .placeholder(R.drawable.no_image)
                .error(R.drawable.no_image)
                .into(image);

        Log.v("berat ", "Berat barang :"+weight_value);
        Log.v("berat ", "id Kota : "+id_geodirectory);
        /*untuk hide dan unhide bottom*/
        if (txt_status_pesanan.getText().toString().equals("ON_REQUEST_CONFIRM")){
            informationNextTransaction.setVisibility(View.VISIBLE);
        }

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case DialogInterface.BUTTON_POSITIVE:
                                //Yes button clicked

                                Intent intent = new Intent(DetailHistoryRequestProduct.this, TransactionSelectMitra.class);
                                intent.putExtra("id_member", sharedPrefManager.getSpIdMember());
                                intent.putExtra("status", status);
                                intent.putExtra("number", number);
                                intent.putExtra("id_product_category",id_product_category);
                                intent.putExtra("weight",weight_value);
                                intent.putExtra("origin", id_geodirectory);
                                intent.putExtra("price_sale",price_sale);
                                intent.putExtra("price_capital",price_sale);
                                intent.putExtra("filename",filename);
                                intent.putExtra("name",name);
                                intent.putExtra("destination",member_city);

                                startActivity(intent);
                                finish();


                                Log.v("data intent", "status :"+status);
                                Log.v("data intent", "id member : "+sharedPrefManager.getSpIdMember());
                                Log.v("data intent", "nomor invoice :"+number);
                                Log.v("data intent", "id product category : "+id_product_category);
                                Log.v("data intent", "weight : "+weight_value);
                                Log.v("data intent", "geodirectory : "+id_geodirectory);
                                Log.v("data intent", "price sale : "+price_sale);
                                ///Log.v("data intent", "status"+harga_barang);

                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                //No button clicked

                                break;
                        }
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(DetailHistoryRequestProduct.this);
                builder.setMessage("Siap untuk mewujudkan impian anda ?").setPositiveButton("IYA", dialogClickListener)
                        .setNegativeButton("Tidak", dialogClickListener).show();
            }
        });



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
