package com.application.kreditimpian.TransactionProcess;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.application.kreditimpian.Api.SharedPrefManager;
import com.application.kreditimpian.Api.SuccessMessage;
import com.application.kreditimpian.Api.api_v2.BaseApiService;
import com.application.kreditimpian.Api.api_v2.UtilsApi;
import com.application.kreditimpian.FormPengajuan.SuccessMengajukan;
import com.application.kreditimpian.LoginRegister.LoginUser;
import com.application.kreditimpian.Model.ModelPengajuanCatalog.ResponsePengajuanCatalog;
import com.application.kreditimpian.Model.ModelTransactionAPI.ResponseTransactionAPI;
import com.application.kreditimpian.R;
import com.application.kreditimpian.TransactionMitra.DuhaSyariah.FormDuhaSyariah;
import com.bumptech.glide.Glide;

import org.jetbrains.annotations.TestOnly;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TransactionCheckout extends AppCompatActivity {

    ProgressDialog pDialog;
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
    RadioGroup radiogroup;
    RadioButton radioButton, radiomethodpayment;
    int Doublebiayakirim, DoubleDownpayment, TotalPembayaran;
    SharedPrefManager sharedPrefManager;
    @BindView(R.id.btnAjukansekarang)
    Button btnAjukansekarang;
    ConnectivityManager conMgr;

    BaseApiService mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_checkout);
        setActionBarTitle("Checkout");
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);// set drawable icon
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        /*Check Conection*/
        CheckCOnection();

        radiogroup = (RadioGroup) findViewById(R.id.radiogroup);
        radiomethodpayment = findViewById(R.id.radiomethodpayment);

        ButterKnife.bind(this);
        mApiService = UtilsApi.getAPIService();
        sharedPrefManager = new SharedPrefManager(this);

        radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

            }
        });

        Intent intent = getIntent();
        String id_member = intent.getStringExtra("id_member");
        String id_product = intent.getStringExtra("id_product");
        String number = intent.getStringExtra("number");
        String id_product_category = intent.getStringExtra("id_product_category");
        String image_product = intent.getStringExtra("image_product");
        String price_capital = intent.getStringExtra("price_capital");
        String price_sale = intent.getStringExtra("price_sale");
        String name_product = intent.getStringExtra("name_product");
        String estimasipengiman = intent.getStringExtra("estimasipengiman").replace("Rp","").replace(".","");
        String courier = intent.getStringExtra("courier");
        String note = intent.getStringExtra("note");
        String tenor = intent.getStringExtra("tenor");
        String cicilan = intent.getStringExtra("cicilan");
        String downpayment = intent.getStringExtra("downpayment").replace("Rp","").replace(".","");
        String name_mitra = intent.getStringExtra("name_mitra");
        String id_transaction = intent.getStringExtra("id_transaction");
        String id_creditor = intent.getStringExtra("id_creditor");
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        int DownPayment = (Integer.parseInt(downpayment));
        int EstimasiPengiriman = (Integer.parseInt(estimasipengiman));


        tvInstalment.setText(cicilan);
        tvTenor2.setText(tenor);
        tvIdTransaction.setText(id_transaction);
        tvIdCreditor.setText(id_creditor);
        txt_name_product.setText(name_product);
        txt_price_capital.setText(price_capital);
        txt_price_sale.setText(price_sale);
        tvMitraKredit.setText(name_mitra);
        tvBiayaKirim.setText(formatRupiah.format(EstimasiPengiriman));
        tvCicilan.setText(tenor + " Bulan x Rp. " + cicilan);
        tvJasaPengiriman.setText(courier);
        tvNomorInvoice.setText("Nomor Invoice : " + number);
        tvDownpayment.setText(formatRupiah.format(DownPayment));
        txt_price_sale.setText(price_sale);
        tvNote.setText(note);


        DoubleDownpayment = Integer.parseInt(downpayment);
        Doublebiayakirim = Integer.parseInt(estimasipengiman);
        TotalPembayaran = (DoubleDownpayment + Doublebiayakirim);
        int totaPembayaran = (Integer.parseInt(String.valueOf(TotalPembayaran)));
        tvTotalPembayaran.setText(formatRupiah.format(totaPembayaran));

        Glide.with(TransactionCheckout.this)
                .load(image_product)
                .placeholder(R.drawable.no_image)
                .error(R.drawable.no_image)
                .into(image);
     /*   Toast.makeText(TransactionCheckout.this, "Value Downpayment"+downpayment, Toast.LENGTH_LONG).show();
        Toast.makeText(TransactionCheckout.this, "Value Installment"+tenor, Toast.LENGTH_LONG).show();
*/


        btnAjukansekarang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ///Checkbtnkeputusan(v);

                if (radiogroup.getCheckedRadioButtonId() == -1) {
                    ///Toast.makeText(TransactionCheckout.this, "Anda belum memilih methode pemabayaran", Toast.LENGTH_LONG).show();
                    AlertDialog alertDialog = new AlertDialog.Builder(TransactionCheckout.this).create();

                    alertDialog.setTitle("Info");
                    alertDialog.setMessage("Anda belum memilih methode pembayaran");
                    alertDialog.setIcon(R.drawable.alert);
                    alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            alertDialog.dismiss();
                        }
                    });

                    alertDialog.show();

                } else {
                    int radioid = radiogroup.getCheckedRadioButtonId();
                    radioButton = findViewById(radioid);
                    ////Toast.makeText(TransactionCheckout.this,"Check button " + radioButton.getText(), Toast.LENGTH_SHORT).show();
                    ///postTransaction();

                    AlertDialog.Builder builder = new AlertDialog.Builder(TransactionCheckout.this);

                    builder.setTitle("Informasi");
                    builder.setMessage("Kredit Impian akan menghubungi kamu melalui WhatsApp untuk konfirmasi pesanan kamu dan meminta beberapa data yang tidak tercantum di Aplikasi.");

                    builder.setPositiveButton("Setuju", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            ////Commant dibawah hilangkan jika Duha sudah ready
                            /*if(tvIdCreditor.getText().equals("24")){
                                Intent formduha = new Intent(TransactionCheckout.this, FormDuhaSyariah.class);
                                formduha.putExtra("id_member", id_member);
                                startActivity(formduha);
                                finish();
                            }else {

                                Log.v("jajal", "id member: " + sharedPrefManager.getSpIdMember());
                                Log.v("jajal", "id trnasaksi: " + id_transaction);
                                Log.v("jajal", "Downpayment: " + downpayment);
                                Log.v("jajal", "Tenor: " + tenor);
                                Log.v("jajal", "catatan: " + note);
                                Log.v("jajal", "id kreditor: " + id_creditor);
                                Log.v("jajal", "biaya kirim: " + estimasipengiman);
                                Log.v("jajal", "Jasa pengiriman:" + courier);
                                Log.v("jajal", "Installment: " + cicilan);
                                Log.v("jajal", "Bank transfer:" + radioButton.getText().toString());
                                Log.v("jajal", "harga barang: " + price_sale.replace(".", "").replace("Rp", ""));


                                postTransaction();
                                dialog.dismiss();
                            }*/

                            //dan jika duha syariah sudah ready maka postTransaction(); di command saja
                            postTransaction();
                        }

                    });
                    builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            // Do nothing
                            dialog.dismiss();
                        }
                    });

                    AlertDialog alert = builder.create();
                    alert.show();
                }

                ///postTransaction();

            }
        });


    }

    public void Checkbtnkeputusan(View v) {

        if (radiogroup.getCheckedRadioButtonId() == -1) {
            Toast.makeText(TransactionCheckout.this, "Anda belum memilih methode pemabayaran", Toast.LENGTH_LONG).show();
        } else {
            int radioid = radiogroup.getCheckedRadioButtonId();

            radioButton = findViewById(radioid);
            ///Toast.makeText(this,"Check button " + radioButton.getText(), Toast.LENGTH_SHORT).show();
        }


    }

    private void postTransaction() {

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        pDialog.setMessage("Tunggu...");
        pDialog.show();
        ///CheckCOnection();

        HashMap<String, String> params = new HashMap<>();
        params.put("id_member", sharedPrefManager.getSpIdMember());
        params.put("id_transaction", tvIdTransaction.getText().toString());
        params.put("down_payment", tvDownpayment.getText().toString().replace(",00","").replace("Rp", ""));
        params.put("tenor", tvTenor2.getText().toString());
        params.put("note", tvNote.getText().toString());
        params.put("id_company", tvIdCreditor.getText().toString());
        params.put("postal_fee", tvBiayaKirim.getText().toString().replace(".", "" ).replace("Rp", "").replace(",00", ""));
        params.put("courier", tvJasaPengiriman.getText().toString());
        params.put("installment", tvInstalment.getText().toString());
        params.put("payment_method", radioButton.getText().toString());
        params.put("price_sale", txt_price_sale.getText().toString().replace(".", "" ).replace("Rp", ""));

        mApiService.postPengajuanCheckout(params).enqueue(new Callback<ResponseTransactionAPI>() {
            @Override
            public void onResponse(Call<ResponseTransactionAPI> call, Response<ResponseTransactionAPI> response) {
                pDialog.dismiss();
                if (response.body().getResponseCode() == 200) {
                    //Toast.makeText(TransactionCheckout.this, response.body().getMessage() , Toast.LENGTH_LONG).show();
                    Intent intent1 = new Intent(TransactionCheckout.this, SuccessMengajukan.class);
                    startActivity(intent1);
                    finish();
                } else {
                    pDialog.dismiss();
                    Toast.makeText(TransactionCheckout.this, "Gagal Checkout, harap cek koneksi anda.", Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Call<ResponseTransactionAPI> call, Throwable t) {
                pDialog.dismiss();
                Toast.makeText(TransactionCheckout.this, "Internet anda bermasalah", Toast.LENGTH_LONG).show();

            }
        });
    }

    private void CheckCOnection() {

        conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        {
            if (conMgr.getActiveNetworkInfo() != null
                    && conMgr.getActiveNetworkInfo().isAvailable()
                    && conMgr.getActiveNetworkInfo().isConnected()) {
            } else {
                ///Toast.makeText(getApplicationContext(), "Tidak ada akses Internet",Toast.LENGTH_LONG).show();
                try {
                    AlertDialog alertDialog = new AlertDialog.Builder(this).create();

                    alertDialog.setTitle("Info");
                    alertDialog.setMessage("Internet tidak tersedia, Periksa konektivitas internet Anda dan coba lagi");
                    alertDialog.setIcon(R.drawable.no_connection);
                    alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    });

                    alertDialog.show();
                } catch (Exception e) {
                    /// Log.d(Constants. , "Show Dialog: " + e.getMessage());
                }

            }
        }
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
