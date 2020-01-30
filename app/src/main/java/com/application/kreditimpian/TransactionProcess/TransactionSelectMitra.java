package com.application.kreditimpian.TransactionProcess;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.application.kreditimpian.Constan.ConstanTransaction;
import com.application.kreditimpian.Constan.Constans;
import com.application.kreditimpian.DetailProduct;
import com.application.kreditimpian.R;
import com.bumptech.glide.Glide;

import java.text.NumberFormat;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TransactionSelectMitra extends AppCompatActivity {

    @BindView(R.id.txt_name_product)
    TextView txt_name_product;
    @BindView(R.id.txt_price_capital)
    TextView txt_price_capital;
    @BindView(R.id.txt_price_sale)
    TextView txt_price_sale;
    @BindView(R.id.txt_id)
    TextView txt_id;
    @BindView(R.id.txt_reference_id)
    TextView txt_reference_id;
    @BindView(R.id.txt_id_product_category)
    TextView txt_id_product_category;
    @BindView(R.id.txt_number)
    TextView txt_number;
    @BindView(R.id.txt_image)
    TextView txt_image;
    @BindView(R.id.txt_status)
    TextView txt_status;
    @BindView(R.id.image)
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_select_mitra);

        setActionBarTitle("Pilih mitra yang kamu mau");
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);// set drawable icon
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ButterKnife.bind(this);


        Intent intent = getIntent();
        String id = intent.getStringExtra(ConstanTransaction.KEY_ID_TRANSACTION);
        String id_product_category = intent.getStringExtra(ConstanTransaction.KEY_ID_PRODUCT_CATEGORY_TRANSACTION);
        String nameProduct = intent.getStringExtra(ConstanTransaction.KEY_NAME_PRODUCT_TRANSACTION);
        String reference_id = intent.getStringExtra(ConstanTransaction.KEY_REFERENCE_ID);
        String nomor_invoice = intent.getStringExtra(ConstanTransaction.KEY_NUMBER);
        String imageProduct = intent.getStringExtra(ConstanTransaction.KEY_FILENAME_TRANSACTION);
        String status = intent.getStringExtra(ConstanTransaction.KEY_STATUS);
        ///convert String to Rupiah Curerncy
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        int price_capital = (Integer.parseInt(intent.getStringExtra(ConstanTransaction.KEY_PRICE_CAPITAL_TRANSACTION)));
        int price_sale = (Integer.parseInt(intent.getStringExtra(ConstanTransaction.KEY_PRICE_SALE_TRANSACTION)));

        if (price_capital == price_sale ) {
            txt_price_capital.setVisibility(View.GONE);
        } else {
            txt_price_capital.setVisibility(View.VISIBLE);
        }

        txt_id.setText(id);
        txt_id_product_category.setText(id_product_category);
        txt_reference_id.setText(reference_id);
        txt_number.setText(nomor_invoice);
        txt_status.setText(status);
        txt_name_product.setText(nameProduct);
        txt_price_capital.setText(formatRupiah.format(price_capital));
        txt_price_sale.setText(formatRupiah.format(price_sale));

        Glide.with(TransactionSelectMitra.this)
                .load(imageProduct)
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
}
