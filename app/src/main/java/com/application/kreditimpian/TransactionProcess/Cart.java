package com.application.kreditimpian.TransactionProcess;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.application.kreditimpian.Adapter.AdapterAddresses;
import com.application.kreditimpian.Adapter.AdapterCart;
import com.application.kreditimpian.Api.SharedPrefManager;
import com.application.kreditimpian.Api.api_v2.BaseApiService;
import com.application.kreditimpian.Api.api_v2.UtilsApi;
import com.application.kreditimpian.Constan.ConstanTransaction;
import com.application.kreditimpian.Constan.Constans;
import com.application.kreditimpian.DetailProduct;
import com.application.kreditimpian.Marketplace.FragSemuaKategori.RecyclerItemClickListener;
import com.application.kreditimpian.Model.ModelOnShoppingCart.DataItem;
import com.application.kreditimpian.Model.ModelOnShoppingCart.ResponseOnShoppingCart;
import com.application.kreditimpian.Model.ModelProductBaru.ResultItem;
import com.application.kreditimpian.Model.ModelSelectedTenor.DataCicilanItem;
import com.application.kreditimpian.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Cart extends AppCompatActivity {
    SwipeRefreshLayout swipeRefresh;
    ProgressDialog progressBar;
    RecyclerView ListCart;
    String number;

    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    BaseApiService mApiService;
    Context mContext;
    ImageView empty;
    SharedPrefManager sharedPrefManager;

    AdapterCart adapterCart;
    List<DataItem> dataItemList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        setActionBarTitle("Keranjang");
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);// set drawable icon
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        empty = findViewById(R.id.empty);
        mContext = Cart.this;
        sharedPrefManager = new SharedPrefManager(this);
        swipeRefresh = findViewById(R.id.swipeRefresh);
        swipeRefresh.setColorScheme(android.R.color.holo_orange_dark,
                android.R.color.holo_orange_light,
                android.R.color.holo_orange_dark,
                android.R.color.holo_orange_light);

        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
               /// LoadAdresess();
                getOnShoppingCart();
                swipeRefresh.setRefreshing(false);
            }
        });
        mApiService = UtilsApi.getAPIService();
        ListCart = findViewById(R.id.ListCart);
        adapterCart = new AdapterCart(this, dataItemList);
        dataItemList = new ArrayList<>();
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false);
        ListCart.setLayoutManager(mLayoutManager);
        ListCart.setItemAnimator(new DefaultItemAnimator());

        getOnShoppingCart();

        /*Swipe to Delete in chart */
      /*  ItemTouchHelper swipeToDismissTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(
                0, ItemTouchHelper.LEFT ) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction)
            {
                int pos = viewHolder.getAdapterPosition();
                adapter.notifyItemRemoved(pos);

               // deleteCart();


            }

        });

        swipeToDismissTouchHelper.attachToRecyclerView(ListCart);*/

    }



    private void getOnShoppingCart(){
        progressBar = ProgressDialog.show(Cart.this, null, "Harap Tunggu...", true, false);
        mApiService.getOnShoppingCart(sharedPrefManager.getSpIdMember()).enqueue(new Callback<ResponseOnShoppingCart>() {
            @Override
            public void onResponse(Call<ResponseOnShoppingCart> call, Response<ResponseOnShoppingCart> response) {

                    if (response.body().getResponseCode() == 200) {
                        swipeRefresh.setRefreshing(false);
                        progressBar.dismiss();
                        final List<DataItem> OnShippingCart = response.body().getData();

                        ListCart.setAdapter(new AdapterCart(mContext, OnShippingCart));
                        adapterCart.notifyDataSetChanged();
                        empty.setVisibility(View.GONE);
                        ///initDataIntent(OnShippingCart);
                    } else {
                        progressBar.dismiss();
                        Toast.makeText(Cart.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        ////Toast.makeText(Cart.this, "Koneksi internet terputus", Toast.LENGTH_SHORT).show();
                        empty.setVisibility(View.VISIBLE);

                    }


            }

            @Override
            public void onFailure(Call<ResponseOnShoppingCart> call, Throwable t) {
                progressBar.dismiss();
                Toast.makeText(Cart.this, "Koneksi anda terputus", Toast.LENGTH_SHORT).show();
            }
        });

    }


    private void initDataIntent(final List<DataItem> detaiList){
        ListCart.addOnItemTouchListener(
                new RecyclerItemClickListener(mContext, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {

                        String id_product = detaiList.get(position).getId_product();
                        String id_product_category = detaiList.get(position).getIdProductCategory();
                        String nameProduct = detaiList.get(position).getName();
                        String reference_id = detaiList.get(position).getReferenceId();
                        String id_transaction = detaiList.get(position).getId_transaction();
                        String nomor_invoice = detaiList.get(position).getNumber();
                        String price_capital = detaiList.get(position).getPriceCapital();
                        String price_sale = detaiList.get(position).getPriceSale();
                        String imageProduct = detaiList.get(position).getFilename();
                        String weight = detaiList.get(position).getWeight();
                        String origin = detaiList.get(position).getOrigin();
                        String destination = detaiList.get(position).getDestination();

                        Intent detailproduct = new Intent(Cart.this, TransactionSelectMitra.class);
                        detailproduct.putExtra(ConstanTransaction.KEY_ID_PRODUCT, id_product);
                        detailproduct.putExtra(ConstanTransaction.KEY_ID_PRODUCT_CATEGORY_TRANSACTION, id_product_category);
                        detailproduct.putExtra(ConstanTransaction.KEY_NAME_PRODUCT_TRANSACTION, nameProduct);
                        detailproduct.putExtra(ConstanTransaction.KEY_REFERENCE_ID, reference_id);
                        detailproduct.putExtra(ConstanTransaction.KEY_ID_TRANSACTION, id_transaction);
                        detailproduct.putExtra(ConstanTransaction.KEY_NUMBER, nomor_invoice);
                        detailproduct.putExtra(ConstanTransaction.KEY_PRICE_CAPITAL_TRANSACTION, price_capital);
                        detailproduct.putExtra(ConstanTransaction.KEY_PRICE_SALE_TRANSACTION, price_sale);
                        detailproduct.putExtra(ConstanTransaction.KEY_FILENAME_TRANSACTION, imageProduct);
                        detailproduct.putExtra(ConstanTransaction.KEY_WEIGHT_TRANSACTION, weight);
                        detailproduct.putExtra(ConstanTransaction.KEY_ORIGIN_TRANSACTION, origin);
                        detailproduct.putExtra(ConstanTransaction.KEY_DESTINATION_TRANSACTION, destination);


                        startActivity(detailproduct);
                    }
                }));
    }


    /*private void deleteCart(){

        mApiService.deleteCart(number).enqueue(new Callback<ResponseOnShoppingCart>() {
            @Override
            public void onResponse(Call<ResponseOnShoppingCart> call, Response<ResponseOnShoppingCart> response) {
                if(response.body().getResponseCode()==200){
                    Toast.makeText(Cart.this, "terhapus", Toast.LENGTH_LONG);
                }else {

                }

            }

            @Override
            public void onFailure(Call<ResponseOnShoppingCart> call, Throwable t) {
                progressBar.dismiss();
                Toast.makeText(Cart.this, "Gagal Refresh", Toast.LENGTH_SHORT).show();
            }
        });

    }*/


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
