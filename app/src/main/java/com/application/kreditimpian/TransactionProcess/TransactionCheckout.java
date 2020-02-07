package com.application.kreditimpian.TransactionProcess;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.application.kreditimpian.Api.SharedPrefManager;
import com.application.kreditimpian.Api.SuccessMessage;
import com.application.kreditimpian.Api.api_v2.BaseApiService;
import com.application.kreditimpian.Api.api_v2.UtilsApi;
import com.application.kreditimpian.FormPengajuan.SuccessMengajukan;
import com.application.kreditimpian.Model.ModelPengajuanCatalog.ResponsePengajuanCatalog;
import com.application.kreditimpian.R;
import com.bumptech.glide.Glide;

import org.jetbrains.annotations.TestOnly;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TransactionCheckout extends AppCompatActivity {

    @BindView(R.id.txt_name_product)
    TextView txt_name_product;
    @BindView(R.id.txt_price_capital)
    TextView txt_price_capital;
    @BindView(R.id.txt_price_sale)
    TextView txt_price_sale;
    @BindView(R.id.tvMitraKredit)
    TextView tvMitraKredit;
    @BindView(R.id.tvCicilan)
    TextView tvCicilan;
    @BindView(R.id.tvDownpayment)
    TextView tvDownpayment;
    @BindView(R.id.tvJasaPengiriman)
    TextView tvJasaPengiriman;
    @BindView(R.id.tvBiayaKirim)
    TextView tvBiayaKirim;

    @BindView(R.id.tvTotalPembayaran)
    TextView tvTotalPembayaran;
    @BindView(R.id.radiomethodpayment)
    RadioButton radiomethodpayment;
    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.tvNomorInvoice)
    TextView tvNomorInvoice;
    @BindView(R.id.tvNote)
    TextView tvNote;
    @BindView(R.id.tvIdCreditor)
    TextView tvIdCreditor;
    @BindView(R.id.tvIdTransaction)
    TextView tvIdTransaction;
    @BindView(R.id.tvTenor2)
    TextView tvTenor2;
    @BindView(R.id.tvInstalment)
    TextView tvInstalment;

    Double Doublebiayakirim, DoubleDownpayment, Totalpembayaran;
    SharedPrefManager sharedPrefManager;
    @BindView(R.id.btnAjukansekarang)
    Button btnAjukansekarang;

    BaseApiService mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_checkout);
        setActionBarTitle("Checkout");
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);// set drawable icon
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ButterKnife.bind(this);
        mApiService = UtilsApi.getAPIService();
        sharedPrefManager = new SharedPrefManager(this);

        Intent intent = getIntent();
        String id_member = intent.getStringExtra("id_member");
        String id_product = intent.getStringExtra("id_product");
        String number = intent.getStringExtra("number");
        String id_product_category = intent.getStringExtra("id_product_category");
        String image_product = intent.getStringExtra("image_product");
        String price_capital = intent.getStringExtra("price_capital");
        String price_sale = intent.getStringExtra("price_sale");
        String name_product = intent.getStringExtra("name_product");
        String estimasipengiman = intent.getStringExtra("estimasipengiman");
        String courier = intent.getStringExtra("courier");
        String note = intent.getStringExtra("note");
        String tenor = intent.getStringExtra("tenor");
        String cicilan = intent.getStringExtra("cicilan");
        String downpayment = intent.getStringExtra("downpayment");
        String name_mitra = intent.getStringExtra("name_mitra");
        String id_transaction = intent.getStringExtra("id_transaction");
        String id_creditor = intent.getStringExtra("id_creditor");


        tvInstalment.setText(cicilan);
        tvTenor2.setText(tenor);

        tvIdTransaction.setText(id_transaction);
        tvIdCreditor.setText(id_creditor);
        txt_name_product.setText(name_product);
        txt_price_capital.setText(price_capital);
        txt_price_sale.setText(price_sale);
        tvMitraKredit.setText("Mitra Kredit :" +name_mitra);
        tvBiayaKirim.setText("Biaya Kirim :" +estimasipengiman);
        tvCicilan.setText("Cicilan :" +tenor +"Bulan x Rp. "+cicilan);
        tvJasaPengiriman.setText("Jasa Pengiriman :" +courier);
        tvNomorInvoice.setText("Nomor Pesanan :" +number);
        tvDownpayment.setText( "Downpayment :"+downpayment);
        txt_price_sale.setText(price_sale);
        tvNote.setText(note);
        tvTotalPembayaran.setText("Total Pembayaran  :"+Totalpembayaran);

        Glide.with(TransactionCheckout.this)
                .load(image_product)
                .placeholder(R.drawable.no_image)
                .error(R.drawable.no_image)
                .into(image);
        Toast.makeText(TransactionCheckout.this, "cek id member "+id_member, Toast.LENGTH_LONG).show();





        btnAjukansekarang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postTransaction();
                Intent intent1 = new Intent(TransactionCheckout.this, SuccessMengajukan.class);
                startActivity(intent1);
                finish();


            }
        });


    }

    private void postTransaction(){

        HashMap<String, String> params = new HashMap<>();
        params.put("id_member",sharedPrefManager.getSpIdMember() );
        params.put("id_transaction", tvIdTransaction.getText().toString());
        params.put("down_payment",  tvDownpayment.getText().toString());
        params.put("tenor", tvTenor2.getText().toString() );
        params.put("note", tvNote.getText().toString());
        params.put("id_creditor", tvIdCreditor.getText().toString());
        params.put("postal_fee", tvBiayaKirim.getText().toString());
        params.put("instalment", tvInstalment.getText().toString());

        mApiService.postPengajuan(params).enqueue(new Callback<ResponsePengajuanCatalog>() {
            @Override
            public void onResponse(Call<ResponsePengajuanCatalog> call, Response<ResponsePengajuanCatalog> response) {
                if(response.body().getResponseCode()==200){
                    Toast.makeText(TransactionCheckout.this, response.body().getMessage() , Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(TransactionCheckout.this, "Gagal" , Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponsePengajuanCatalog> call, Throwable t) {
                Toast.makeText(TransactionCheckout.this, "Internet anda bermasalah" , Toast.LENGTH_LONG).show();
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
}
