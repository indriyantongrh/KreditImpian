package com.application.kreditimpian;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.application.kreditimpian.Api.SharedPrefManager;
import com.application.kreditimpian.Api.api_v2.BaseApiService;
import com.application.kreditimpian.Api.api_v2.UtilsApi;
import com.application.kreditimpian.FormPengajuan.UpgradeImpian.ViewPagerAdapter;
import com.application.kreditimpian.Constan.Constans;
import com.application.kreditimpian.Model.ModelProduct.ImagesItem;
//import com.application.kreditimpian.Model.ModelProduct.ResultItem;
import com.application.kreditimpian.Model.ModelProductNew.ResultItem;
import com.application.kreditimpian.Model.ModelTransaction.ResponseTransaction;
import com.application.kreditimpian.TransactionProcess.Cart;
import com.bumptech.glide.Glide;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailProduct extends AppCompatActivity {
    ProgressDialog pDialog;
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
    @BindView(R.id.txt_name_merchant)
    TextView txt_name_merchant;
    @BindView(R.id.txt_location_merchant)
    TextView txt_location_merchant;
//    @BindView(R.id.imagemerchant)
//    ImageView imagemerchant;
//    @BindView(R.id.txt_image_merchant)
//    TextView txt_image_merchant;
    @BindView(R.id.btnBelisekarang)
    Button btnBelisekarang;

    String id, id_product_category,id_currency,nameProduct,price_capital, price_sale,description , condition,stock, imageProduct, weight_value,
            weight, nameMerchant, city, imageMerchant;
    ///Integer image;

    //List<ResultItem> resultItemList = new ArrayList<ResultItem>();
//    List<ResultItem> resultItemList;
    List<ResultItem> resultItemList;
    //private ArrayList<ResultItem> mArrayListResult;
    List<ImagesItem> imageItemList;

    DecimalFormat kursindonesia;
    Double rupiah,rupiahspinner;
    DecimalFormatSymbols formatRp;
    Double pricerCapital;

    //untukviewpager
    ViewPager viewPager;
    CircleIndicator indicator;
    ViewPagerAdapter viewPagerAdapter;

    BaseApiService mApiService;
    SharedPrefManager sharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_product);

        setActionBarTitle("Detail Produk");
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);// set drawable icon
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ButterKnife.bind(this);

        mApiService = UtilsApi.getAPIService();
        sharedPrefManager = new SharedPrefManager(DetailProduct.this);
        String id_member = sharedPrefManager.getSpIdMember();
        final ResultItem resultItem = new ResultItem();

//        id = getIntent().getStringExtra("id");
//        id_product_category = getIntent().getStringExtra("id_product_category");
//        id_currency = getIntent().getStringExtra("id_currency");
//        nameProduct = getIntent().getStringExtra("name");
//        price_capital = getIntent().getStringExtra("price_capital");
//        price_sale = getIntent().getStringExtra("price_sale");
//        description = getIntent().getStringExtra("description");
//        condition = getIntent().getStringExtra("condition");
//        stock = getIntent().getStringExtra("stock");
//        imageProduct = getIntent().getStringExtra("image");
//        weight_value = getIntent().getStringExtra("weight_value");
//        weight = getIntent().getStringExtra("weight");
//        nameMerchant = getIntent().getStringExtra("name");
//        city = getIntent().getStringExtra("city");
//        ///imageMerchant = getIntent().getStringExtra("image");
//
//
//
        Intent intent = getIntent();
        id = intent.getStringExtra(Constans.KEY_ID);
        id_currency = intent.getStringExtra(Constans.KEY_ID_CURRENCY);
        id_product_category = intent.getStringExtra(Constans.KEY_ID_PRODUCT_CATEGORY);
        nameProduct = intent.getStringExtra(Constans.KEY_NAME_PRODUCT);

        ///convert String to Rupiah Curerncy
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        int price_capital = (Integer.parseInt(intent.getStringExtra(Constans.KEY_PRICE_CAPITAL)));
        int price_sale = (Integer.parseInt(intent.getStringExtra(Constans.KEY_PRICE_SALE)));

        if (price_capital == price_sale ) {
            txt_price_capital.setVisibility(View.GONE);
        } else {
            txt_price_capital.setVisibility(View.VISIBLE);
        }

        //price_capital = intent.getStringExtra(Constans.KEY_PRICE_CAPITAL);
        ///price_sale = intent.getStringExtra(Constans.KEY_PRICE_SALE);
        description = intent.getStringExtra(Constans.KEY_DESCRIPTIOM);
        condition = intent.getStringExtra(Constans.KEY_CONDITION);
        stock = intent.getStringExtra(Constans.KEY_STOCK);
        imageProduct = intent.getStringExtra(Constans.KEY_IMAGE);
        weight_value = intent.getStringExtra(Constans.KEY_WEIGHT_VALUE);
        weight = intent.getStringExtra(Constans.KEY_WEIGHT);
        nameMerchant = intent.getStringExtra(Constans.KEY_NAME_MERCHNAT);
        city = intent.getStringExtra(Constans.KEY_CITY_MERCHANT);



        txt_id.setText(id);
        txt_id_product_category.setText(id_currency);
        txt_id_currency.setText(id_product_category);
        txt_name_product.setText(nameProduct);
        txt_price_capital.setText(formatRupiah.format(price_capital));
        txt_price_sale.setText(formatRupiah.format(price_sale));

//        if (txt_price_capital.getVisibility() == View.GONE) {
//           price_capital == price_sale;
//        }


        ///txt_description.loadDataWithBaseURL(null, String.valueOf(Html.fromHtml(getIntent().getStringExtra("description"))), "text/html", "utf-8", null);
        txt_description.loadDataWithBaseURL(null, description, "text/html", "utf-8", null);
        txt_condition.setText(condition);
        txt_stock.setText(stock);
        ///image.setImageResource(imageproduct);

        Glide.with(DetailProduct.this)
                .load(imageProduct)
                .placeholder(R.drawable.no_image)
                .error(R.drawable.no_image)
                .into(imageView);

        txt_weight_value.setText(weight_value+weight);
        txt_name_merchant.setText(nameMerchant);
        txt_location_merchant.setText(city);
///        txt_image_merchant.setText(imageMerchant);
//        Glide.with(DetailProduct.this)
//                .load(imageMerchant)
//                .placeholder(R.drawable.store)
//                .error(R.drawable.store)
//                .into(imagemerchant);

        btnBelisekarang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InsertShoppingCart();
            }
        });


    }

    private void InsertShoppingCart(){

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        pDialog.setMessage("Mohon tunggu");
        pDialog.show();


        HashMap<String, String> params = new HashMap<>();
        params.put("id_member",sharedPrefManager.getSpIdMember() );
        params.put("reference_id", id);

        mApiService.InsertShoppingCart(params).enqueue(new Callback<ResponseTransaction>() {
            @Override
            public void onResponse(Call<ResponseTransaction> call, Response<ResponseTransaction> response) {

                pDialog.dismiss();
                if (response.body() !=null) {
                    ////Toast.makeText(DetailProduct.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(DetailProduct.this, Cart.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(DetailProduct.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
            @Override
            public void onFailure(Call<ResponseTransaction> call, Throwable t) {
                Toast.makeText(DetailProduct.this, "Keneksi terputus", Toast.LENGTH_LONG);
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
