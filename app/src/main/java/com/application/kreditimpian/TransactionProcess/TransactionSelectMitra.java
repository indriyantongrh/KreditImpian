package com.application.kreditimpian.TransactionProcess;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.application.kreditimpian.Adapter.AdapterMitraSelected;
import com.application.kreditimpian.Api.SharedPrefManager;
import com.application.kreditimpian.Api.api_v2.BaseApiService;
import com.application.kreditimpian.Api.api_v2.UtilsApi;
import com.application.kreditimpian.Constan.ConstanTransaction;
import com.application.kreditimpian.Model.ModelCicilan.CompaniesDataItem;
import com.application.kreditimpian.Model.ModelCicilan.DataCicilanItem;
import com.application.kreditimpian.Model.ModelCicilan.ProductMeta;
import com.application.kreditimpian.Model.ModelCicilan.ResponseCicilan;
import com.application.kreditimpian.Model.ModelCostRajaongkir.ResponseCostRajaongkir;
import com.application.kreditimpian.Model.ModelMitraSelected.DataItem;
import com.application.kreditimpian.Model.ModelMitraSelected.ResponseMitraSelected;
import com.application.kreditimpian.Model.ModelOngkoskirim.ResponseOngkir;
import com.application.kreditimpian.R;
import com.bumptech.glide.Glide;
import com.chivorn.smartmaterialspinner.SmartMaterialSpinner;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TransactionSelectMitra extends AppCompatActivity {
    private Context context;
    ProgressDialog pDialog;
    ProgressDialog progressBar;
    @BindView(R.id.rvMitra)
    RecyclerView rvMitra;
    @BindView(R.id.txt_name_product)
    TextView txt_name_product;
    @BindView(R.id.txt_id_transaction)
    TextView txt_id_transaction;
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
    @BindView(R.id.btnSelanjutnya)
    Button btnSelanjutnya;
    @BindView(R.id.spinnercourier)
    Spinner spinnercourier;
    @BindView(R.id.txt_note)
    EditText txt_note;
    @BindView(R.id.tv_estimasipengiriman)
    TextView tv_estimasipengiriman;
    @BindView(R.id.txt_weight)
    TextView txt_weight;
    @BindView(R.id.txt_origin)
    TextView txt_origin;
    @BindView(R.id.txt_destination)
    TextView txt_destination;
    @BindView(R.id.text_image)
     TextView text_image;
    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefresh;
    // String id_product_category;

    private SmartMaterialSpinner spinCostOngkir;

    DataItem reqDataItem;
    SharedPrefManager sharedPrefManager;
    BaseApiService mApiService;
    private List<DataItem> dataItemList = new ArrayList<>();
    private HashMap<Integer, String> logisticvalue;
    private HashMap<Integer, String> CostOngkirvalue;
    private String responses;
    private JSONObject jsonObject, jsonObject1, jsonObject3,jsonObject2;
    AdapterMitraSelected adapterMitraSelected;
    Context mContext;
    boolean check = false;
    private JSONArray jsonArray, jsonArray1;

    private ProductMeta productMeta;
    private CompaniesDataItem companiesDataItem;
    private DataCicilanItem dataCicilanItem;
    private ArrayList<ResponseCicilan> responseCicilans;
    private ArrayList<CompaniesDataItem> companiesDataItemArrayList;
    private ArrayList<DataCicilanItem> dataCicilanItemArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_select_mitra);

        spinCostOngkir = findViewById(R.id.spinCostOngkir);


        setActionBarTitle("Pilih mitra yang kamu mau");
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);// set drawable icon
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ButterKnife.bind(this);
        mContext = TransactionSelectMitra.this;
        sharedPrefManager = new SharedPrefManager(TransactionSelectMitra.this);
        mApiService = UtilsApi.getAPIService();


        /*get Selected Mitra*/
        /*get ongkir by API*/
        getOngkir();
        //getCostOngkir();

        Intent intent = getIntent();
        String id_product = intent.getStringExtra(ConstanTransaction.KEY_ID_PRODUCT);
        String id_product_category = intent.getStringExtra(ConstanTransaction.KEY_ID_PRODUCT_CATEGORY_TRANSACTION);
        String nameProduct = intent.getStringExtra(ConstanTransaction.KEY_NAME_PRODUCT_TRANSACTION);
        String reference_id = intent.getStringExtra(ConstanTransaction.KEY_REFERENCE_ID);
        String id_transaction = intent.getStringExtra(ConstanTransaction.KEY_ID_TRANSACTION);
        String nomor_invoice = intent.getStringExtra(ConstanTransaction.KEY_NUMBER);
        String imageProduct = intent.getStringExtra(ConstanTransaction.KEY_FILENAME_TRANSACTION);
        String status = intent.getStringExtra(ConstanTransaction.KEY_STATUS);
        String weight = intent.getStringExtra(ConstanTransaction.KEY_WEIGHT_TRANSACTION);
        String origin = intent.getStringExtra(ConstanTransaction.KEY_ORIGIN_TRANSACTION);
        String destination = intent.getStringExtra(ConstanTransaction.KEY_DESTINATION_TRANSACTION);
        String priceSale = intent.getStringExtra(ConstanTransaction.KEY_PRICE_CAPITAL_TRANSACTION);
        String priceCapital = intent.getStringExtra(ConstanTransaction.KEY_PRICE_SALE_TRANSACTION);

        ////Toast.makeText(TransactionSelectMitra.this, ""+nomor_invoice, Toast.LENGTH_LONG).show();

       /// Log.d("ini id kategorimu ", ConstanTransaction.KEY_ID_PRODUCT_CATEGORY_TRANSACTION);

        ///convert String to Rupiah Curerncy
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        int price_capital = (Integer.parseInt(intent.getStringExtra(ConstanTransaction.KEY_PRICE_CAPITAL_TRANSACTION)));
        int price_sale = (Integer.parseInt(intent.getStringExtra(ConstanTransaction.KEY_PRICE_SALE_TRANSACTION)));

        if (price_capital == price_sale) {
            txt_price_capital.setVisibility(View.GONE);
        } else {
            txt_price_capital.setVisibility(View.VISIBLE);
        }

        txt_id.setText(id_product);
        txt_id_product_category.setText(id_product_category);
        ////Toast.makeText(TransactionSelectMitra.this, "Kategori product anda " + id_product_category, Toast.LENGTH_LONG).show();
        txt_reference_id.setText(reference_id);
        txt_number.setText(nomor_invoice);
        txt_status.setText(status);
        txt_id_transaction.setText(id_transaction);
        txt_name_product.setText(nameProduct);
        txt_price_capital.setText(formatRupiah.format(price_capital));
        txt_price_sale.setText(formatRupiah.format(price_sale));
        txt_weight.setText(weight);
        txt_origin.setText(origin);
        txt_destination.setText(destination);
        text_image.setText(imageProduct);
        Log.v("jajal",  sharedPrefManager.getSpIdMember()+": idMember");
        Log.v("jajal", id_product+": idproduct");
        Log.v("jajal", id_product_category+": idproductcategory");
        Log.v("jajal", reference_id+": idproductreferenceid");
        Log.v("jajal", nomor_invoice+": idnumber");
        Log.v("jajal", status+": idstatus");
        Log.v("jajal", id_transaction+": idTransaction");
        Log.v("jajal", nameProduct+": nameproduk");
        Log.v("jajal", price_capital+": pricecapital");
        Log.v("jajal", price_sale+": pricesale");
        Log.v("jajal", weight+": weight");
        Log.v("jajal", origin+": origin");
        Log.v("jajal", destination+": destination");
        Log.v("jajal", imageProduct+"image");

        Glide.with(TransactionSelectMitra.this)
                .load(imageProduct)
                .placeholder(R.drawable.no_image)
                .error(R.drawable.no_image)
                .into(image);




        adapterMitraSelected = new AdapterMitraSelected(TransactionSelectMitra.this);
        rvMitra.setAdapter(adapterMitraSelected);
        GridLayoutManager mLayoutManager = new GridLayoutManager(TransactionSelectMitra.this, 1, GridLayoutManager.VERTICAL, false);
        rvMitra.setLayoutManager(mLayoutManager);
        rvMitra.setItemAnimator(new DefaultItemAnimator());
        swipeRefresh.setColorScheme(android.R.color.holo_orange_dark,
                android.R.color.holo_orange_light,
                android.R.color.holo_orange_dark,
                android.R.color.holo_orange_light);

        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                selectedMitra();
                swipeRefresh.setRefreshing(false);
            }
        });
        selectedMitra();



        btnSelanjutnya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                /*if (!check) {
                    Log.v("Bismillah", check + "cekcb");
                    Toast.makeText(TransactionSelectMitra.this, "Silahkan pilih mitra\nminimal 1\nmaximal 3", Toast.LENGTH_LONG).show();
                } else */if(spinnercourier.getSelectedItem().equals("-- Pilih Pengiriman --")) {
                    //Toast.makeText(TransactionSelectMitra.this, "Pilih jasa pengiriman barang anda", Toast.LENGTH_LONG).show();
                    AlertDialog alertDialog = new AlertDialog.Builder(TransactionSelectMitra.this).create();
                    alertDialog.setTitle("Info");
                    alertDialog.setMessage("Anda belum mengisi jasa pengiriman.");
                    alertDialog.setIcon(R.drawable.alert);
                    alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            alertDialog.dismiss();

                        }
                    });
                    alertDialog.show();
                } else {
                    NextTenor();
                }

                /*if(dataItem.isCheck()){
                    NextTenor();
                }else {
                    Toast.makeText(TransactionSelectMitra.this, "Anda belum memilih Tenor", Toast.LENGTH_LONG).show();

                }*/


//                Intent intent = new Intent(TransactionSelectMitra.this  , TransactionSelectTenor.class );
//                startActivity(intent);
//                Intent intent = new Intent(TransactionSelectMitra.this, TransactionSelectTenor.class);
//                startActivity(intent);

            }
        });

    }

    private void NextTenor() {
        String id_member = sharedPrefManager.getSpIdMember();
        String id_product_category = txt_id_product_category.getText().toString();
        String id_product = txt_id.getText().toString();
        String number = txt_number.getText().toString();
        String courier = spinnercourier.getSelectedItem().toString();
        String note = txt_note.getText().toString();
        String estimasipengiman = tv_estimasipengiriman.getText().toString();
        String price_capital = txt_price_capital.getText().toString();
        String price_sale = txt_price_sale.getText().toString();
        String name_product = txt_name_product.getText().toString();
        String image_product = text_image.getText().toString();
        String id_transaction = txt_id_transaction.getText().toString();

        Log.v("btnNext", id_member+": id_member");
        Log.v("btnNext", id_product_category+": id_member");
        Log.v("btnNext", id_product+": id_product");
        Log.v("btnNext", number+": number");
        Log.v("btnNext", courier+": courier");
        Log.v("btnNext", note+": note");
        Log.v("btnNext", estimasipengiman+": estimasipengiman");
        Log.v("btnNext", price_capital+": price_capital");
        Log.v("btnNext", price_sale+": price_sale");
        Log.v("btnNext", name_product+": name_product");
        Log.v("btnNext", image_product+": image_product");
        Log.v("btnNext", id_transaction+": id_transaction");

        /*get Response API and save in Intent*/
        StringBuilder mitraStringBuilder = new StringBuilder();
        List<DataItem> dataItemList = adapterMitraSelected.getDataItemList();
        DataItem dataItem = new DataItem();
        ///ModelMitra modelMitra = new ModelMitra();
        for (int i = 0; i < dataItemList.size(); i++) {
            dataItem = dataItemList.get(i);
            if (dataItem.isCheck()) {
                mitraStringBuilder.append(dataItem.getIdCompany()).append("|");
            }
        }
       /// Toast.makeText(TransactionSelectMitra.this, ""+mitraStringBuilder+id_member+id_product_category+id_product+number+courier+note+estimasipengiman, Toast.LENGTH_LONG ).show();
        ///Log.v("jajal2", number+ " bismillah");
        for (int i = 0; i < dataItemList.size(); i++) {
            if (dataItem.isCheck()) {
                check = true;

                break;
            }
        }

        String list_id_company = mitraStringBuilder.toString();


        HashMap<String, String> params = new HashMap<>();
        params.put("id_member",sharedPrefManager.getSpIdMember() );
        params.put("id_product_category", txt_id_product_category.getText().toString());
        params.put("id_product",  txt_id.getText().toString());
        params.put("number", txt_number.getText().toString());
        params.put("list_id_company", mitraStringBuilder.toString());

        Call<ResponseBody> getCicilan = mApiService.getCicilanProduct(params);

        getCicilan.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    if(response.body()!=null)
                        try {
                            responses = response.body().string();
                            jsonObject = new JSONObject(responses);
                            responseCicilans = new ArrayList<>();
                            if(jsonObject.getString("response_code").equals("200")){
                                jsonObject = new JSONObject(jsonObject.getString("data"));
                                productMeta = new ProductMeta();
                                jsonObject3 = new JSONObject(jsonObject.getString("product_meta"));
                                productMeta.setIdProduct(jsonObject3.getString("id_product"));
                                productMeta.setNumber(jsonObject3.getString("number"));
                                productMeta.setReferenceId(jsonObject3.getString("reference_id"));
                                productMeta.setIdTransaction(jsonObject3.getString("id_transaction"));
                                productMeta.setName(jsonObject3.getString("name"));
                                productMeta.setFilename(jsonObject3.getString("filename"));
                                productMeta.setIdProductCategory(jsonObject3.getString("id_product_category"));
                                productMeta.setPriceCapital(jsonObject3.getString("price_capital"));
                                productMeta.setPriceSale(jsonObject3.getString("price_sale"));

                                    companiesDataItemArrayList = new ArrayList<>();
                                    jsonArray = new JSONArray(jsonObject.getString("companies_data"));
                                    for (int i = 0; i < jsonArray.length(); i++){
                                        jsonObject = jsonArray.getJSONObject(i);
                                        companiesDataItem = new CompaniesDataItem();
                                        companiesDataItem.setIdCompany(jsonObject.getString("id_company"));
                                        companiesDataItem.setName(jsonObject.getString("name"));
                                        companiesDataItem.setDownPayment(jsonObject.getString("downPayment"));

                                        dataCicilanItemArrayList = new ArrayList<>();
                                        jsonArray1 = new JSONArray(jsonObject.getString("data_cicilan"));
                                        for (int j = 0; j < jsonArray1.length(); j++) {
                                            jsonObject1 = jsonArray1.getJSONObject(j);
                                            dataCicilanItem = new DataCicilanItem();
                                            dataCicilanItem.setBulan(jsonObject1.getString("bulan"));
                                            dataCicilanItem.setCicilan(jsonObject1.getString("cicilan"));
                                            dataCicilanItemArrayList.add(dataCicilanItem);
                                        }
                                        companiesDataItem.setDataCicilan(dataCicilanItemArrayList);
                                        companiesDataItemArrayList.add(companiesDataItem);
                                    }


                               // Log.v("jajal product", jsonObject1+ "");
                               /// Log.v("jajal mitra", jsonObject1+ "");

                                Intent intent = new Intent(TransactionSelectMitra.this, TransactionSelectTenor.class);
                                intent.putExtra("datalist", companiesDataItemArrayList);
                                intent.putExtra("productMeta", productMeta);
                                intent.putExtra("id_member",id_member);
                                intent.putExtra("id_product_category",id_product_category);
                                intent.putExtra("id_product",id_product);
                                intent.putExtra("number", number);
                                intent.putExtra("courier", courier);
                                intent.putExtra("note", note);
                                intent.putExtra("estimasipengiman", estimasipengiman);
                                intent.putExtra("image_product", image_product);
                                intent.putExtra("price_capital", price_capital);
                                intent.putExtra("price_sale", price_sale);
                                intent.putExtra("name_product", name_product);
                                intent.putExtra("id_transaction", id_transaction);
                                startActivity(intent);
                                finish();
                            }


                        }catch (Exception e){
                            e.printStackTrace();
                        }


                        //Log.v("jajal", ""+response.body().string()+ "bismillah");
                        //Toast.makeText(TransactionSelectMitra.this," response message "+response.body().string(),Toast.LENGTH_LONG).show();
                    if(response.errorBody()!=null)
                        Toast.makeText(TransactionSelectMitra.this," response message Error "+response.errorBody().string(),Toast.LENGTH_LONG).show();
                }catch (Exception e){
                    e.printStackTrace();
                }

           }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(TransactionSelectMitra.this, "Invalid response from server 2", Toast.LENGTH_SHORT).show();
            }
        });




    }

    private void getCostOngkir(){
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);

        CostOngkirvalue = new HashMap<Integer, String>();
        HashMap<String, String> params = new HashMap<>();
        params.put("origin", txt_origin.getText().toString() );
        params.put("destination", txt_destination.getText().toString() );
        params.put("weight", txt_weight.getText().toString() );

        mApiService.getCostOngkir(params).enqueue(new Callback<ResponseCostRajaongkir>() {
            @Override
            public void onResponse(Call<ResponseCostRajaongkir> call, Response<ResponseCostRajaongkir> response) {
                if(response.body() !=null){
                    List<com.application.kreditimpian.Model.ModelCostRajaongkir.DataItem> getCostOngkir = response.body().getData();
                    String[] company_name = new String[getCostOngkir.size() +1];
                    Integer[] cost = new Integer[getCostOngkir.size() +1];
                    company_name[0] = "Pilih Jasa Pengiriman";
                    for (int i = 0; i < getCostOngkir.size(); i++) {
                        ///listSpinner.add(getCity.get(i).getIdParent());
                        company_name[i + 1] = getCostOngkir.get(i).getCompanyName();
                        cost[i + 1] = getCostOngkir.get(i).getCost();
                        CostOngkirvalue.put(cost[i + 1], company_name[i + 1] );
                    }

                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(TransactionSelectMitra.this,
                            android.R.layout.simple_spinner_item, company_name);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnercourier.setAdapter(adapter);
                    spinnercourier.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                            if(position>0){
                                Integer Costlogisticvalue = getCostOngkir.get(position - 1 ).getCost();
                                //tv_estimasipengiriman.setText(String.valueOf(logisticvalue));
                                if(Costlogisticvalue.equals("null")){
                                    tv_estimasipengiriman.setText("Logistik tidak mendukung");

                                }else{
                                    tv_estimasipengiriman.setText(formatRupiah.format(Costlogisticvalue));

                                }
                                ///// Toast.makeText(TransactionSelectMitra.this, " "+logisticvalue, Toast.LENGTH_LONG).show();

                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<ResponseCostRajaongkir> call, Throwable t) {

            }
        });

    }

    private void getOngkir(){
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);

        logisticvalue = new HashMap<Integer, String>();
        HashMap<String, String> params = new HashMap<>();
        params.put("origin", txt_origin.getText().toString() );
        params.put("destination", txt_destination.getText().toString() );
        params.put("weight", txt_weight.getText().toString() );

        mApiService.getOngkir(params).enqueue(new Callback<ResponseOngkir>() {
            @Override
            public void onResponse(Call<ResponseOngkir> call, Response<ResponseOngkir> response) {
                if(response.body() !=null){


                    List<com.application.kreditimpian.Model.ModelOngkoskirim.DataItem> getLogistic =  response.body().getData();
                    String[] name = new String[getLogistic.size() +1];
                    Integer[] cost = new Integer[getLogistic.size() +1];
                    name[0] = "-- Pilih Pengiriman --";
                    for (int i = 0; i < getLogistic.size(); i++) {
                        ///listSpinner.add(getCity.get(i).getIdParent());
                        name[i + 1] = getLogistic.get(i).getName();
                        cost[i + 1] = getLogistic.get(i).getCost();
                        logisticvalue.put(cost[i + 1], name[i + 1] );
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(TransactionSelectMitra.this,
                            android.R.layout.simple_spinner_item, name);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnercourier.setAdapter(adapter);
                    spinnercourier.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                            if(position>0){
                                Integer logisticvalue = getLogistic.get(position - 1 ).getCost();
                                //tv_estimasipengiriman.setText(String.valueOf(logisticvalue));
                                tv_estimasipengiriman.setText(formatRupiah.format(logisticvalue));
                               ///// Toast.makeText(TransactionSelectMitra.this, " "+logisticvalue, Toast.LENGTH_LONG).show();

                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                        //
                    ///Toast.makeText(TransactionSelectMitra.this, response.body().getData().toString(), Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<ResponseOngkir> call, Throwable t) {
                Toast.makeText(TransactionSelectMitra.this, "Perikasa Internet Anda" , Toast.LENGTH_LONG).show();
            }
        });

    }


    private void selectedMitra() {
        //// progressBar = ProgressDialog.show(TransactionSelectMitra.this, null, "Harap Tunggu...", true, false);
//        HashMap<String, String> params = new HashMap<>();
//        params.put("id_product_category", txt_id_product_category.getText().toString());
        swipeRefresh.setRefreshing(true);
        String id_product_category = txt_id_product_category.getText().toString();

        mApiService.getMitraSelected(id_product_category).enqueue(new Callback<ResponseMitraSelected>() {
            @Override
            public void onResponse(Call<ResponseMitraSelected> call, Response<ResponseMitraSelected> response) {
                ///pDialog.dismiss();

                if (response.body().getResponseCode() == 200) {
                    swipeRefresh.setRefreshing(false);
                    final List<DataItem> dataItemList = response.body().getData();
                    adapterMitraSelected.setDataItemList(dataItemList);
                    adapterMitraSelected.notifyDataSetChanged();


                    ///    Log.d("Data result", MitraList.toString());
                    ///Log.v("jajal", MitraList.toString());
                    ////Toast.makeText(TransactionSelectMitra.this, response.body().getData().toString(), Toast.LENGTH_LONG).show();

                } else {
                    swipeRefresh.setRefreshing(false);
                    Toast.makeText(TransactionSelectMitra.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ResponseMitraSelected> call, Throwable t) {
                swipeRefresh.setRefreshing(false);

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
        super.onBackPressed();
        finish();
    }

}
