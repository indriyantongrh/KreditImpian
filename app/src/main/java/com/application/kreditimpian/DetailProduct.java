package com.application.kreditimpian;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Html;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailProduct extends AppCompatActivity {

    @BindView(R.id.txt_id)
    TextView txt_id;
    @BindView(R.id.txt_id_product_category)
    TextView txt_id_product_category;
    @BindView(R.id.txt_id_currency)
    TextView txt_id_currency;
    @BindView(R.id.txt_name_product)
    TextView txt_name_product;
    @BindView(R.id.txt_price_capital)
    TextView txt_price_capital;
    @BindView(R.id.txt_price_sale)
    TextView txt_price_sale;
    @BindView(R.id.txt_description)
    WebView txt_description;
//    @BindView(R.id.txt_sku)
//    TextView txt_sku;
    @BindView(R.id.txt_stock)
    TextView txt_stock;
    @BindView(R.id.txt_condition)
    TextView txt_condition;
//    @BindView(R.id.txt_deliverable)
//    TextView txt_deliverable;
//    @BindView(R.id.txt_downloadable)
//    TextView txt_downloadable;
//    @BindView(R.id.txt_target_gender)
//    TextView txt_target_gender;
//    @BindView(R.id.txt_target_age)
//    TextView txt_target_age;
//    @BindView(R.id.txt_visibility)
//    TextView txt_visibility;
    @BindView(R.id.imageView)
    ImageView imageView;
//    @BindView(R.id.txt_image)
//    TextView txt_image;
    @BindView(R.id.txt_weight_value)
    TextView txt_weight_value;
//    @BindView(R.id.txt_weight)
//    TextView txt_weight;


    String id, id_product_category,id_currency,name,price_capital, price_sale,description , condition,stock, image, weight_value,weight;
    ///Integer image;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_product);

        setActionBarTitle("Detail Produk");
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);// set drawable icon
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ButterKnife.bind(this);

        id = getIntent().getStringExtra("id");
        id_product_category = getIntent().getStringExtra("id_product_category");
        id_currency = getIntent().getStringExtra("id_currency");
        name = getIntent().getStringExtra("name");
        price_capital = getIntent().getStringExtra("price_capital");
        price_sale = getIntent().getStringExtra("price_sale");
        description = getIntent().getStringExtra("description");
        condition = getIntent().getStringExtra("condition");
        stock = getIntent().getStringExtra("stock");
        image = getIntent().getStringExtra("image");
        weight_value = getIntent().getStringExtra("weight_value");
        weight = getIntent().getStringExtra("weight");



        txt_id.setText(id);
        txt_id_product_category.setText(id_product_category);
        txt_id_currency.setText(id_currency);
        txt_name_product.setText(name);
        txt_price_capital.setText(price_capital);
        txt_price_sale.setText(price_sale);
        txt_description.loadDataWithBaseURL(null, String.valueOf(Html.fromHtml(getIntent().getStringExtra("description"))), "text/html", "utf-8", null);
        txt_condition.setText(condition);
        txt_stock.setText(stock);
        ///image.setImageResource(imageproduct);

        Glide.with(DetailProduct.this)
                .load(image)
                .placeholder(R.drawable.no_image)
                .error(R.drawable.no_image)
                .into(imageView);
//
        txt_weight_value.setText(weight_value+weight);


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
