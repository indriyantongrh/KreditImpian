package com.application.kreditimpian.HistoryPesanan.TabRiwayatPesananan;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.application.kreditimpian.Akun.DataDiri;
import com.application.kreditimpian.Api.SharedPrefManager;
import com.application.kreditimpian.Api.api_v2.BaseApiService;
import com.application.kreditimpian.Api.api_v2.UtilsApi;
import com.application.kreditimpian.Constan.ConstanHistoryPesanan;
import com.application.kreditimpian.FormPengajuan.SuccessMengajukan;
import com.application.kreditimpian.KonfirmasiPembayaran.KonfirmasiPembayaran;
import com.application.kreditimpian.LoginRegister.LoginUser;
import com.application.kreditimpian.Model.ModelConfirmProduct.ResponseConfirmProduct;
import com.application.kreditimpian.R;
import com.application.kreditimpian.TransactionProcess.TransactionCheckout;
import com.bumptech.glide.Glide;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailHistoryPesanan extends AppCompatActivity {

    ProgressDialog pDialog;
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
    @BindView(R.id.informationDelivered)
    LinearLayout informationDelivered;
    @BindView(R.id.tvAlamatPengiriman)
    TextView tvAlamatPengiriman;
    @BindView(R.id.btnuploaddp)
    Button btnuploaddp;
    @BindView(R.id.btnKonfrimasiBarang)
    Button btnKonfrimasiBarang;

    private String KEY_ID_TRANSACTION = "id_transaction";
    BaseApiService mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_history_pesanan);
        setActionBarTitle("Detail Transaksi");
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);// set drawable icon
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mApiService = UtilsApi.getAPIService();

        ButterKnife.bind(this);
        Intent intent = getIntent();
        String id = intent.getStringExtra(ConstanHistoryPesanan.KEY_ID);
        String id_transactions = intent.getStringExtra(ConstanHistoryPesanan.KEY_ID_TRANSACTION);
        String status = intent.getStringExtra(ConstanHistoryPesanan.KEY_STATUS);
        String timestamp = intent.getStringExtra(ConstanHistoryPesanan.KEY_TIMESTAMP);
        String expires = intent.getStringExtra(ConstanHistoryPesanan.KEY_EXPIRES);
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

        String discount = intent.getStringExtra(ConstanHistoryPesanan.KEY_DISCOUNT);
        String condition = intent.getStringExtra(ConstanHistoryPesanan.KEY_CONDITION);
        String deliverable = intent.getStringExtra(ConstanHistoryPesanan.KEY_DELIVERABLE);
        String downloadable = intent.getStringExtra(ConstanHistoryPesanan.KEY_DOWNLOADABLE);
        String target_gender = intent.getStringExtra(ConstanHistoryPesanan.KEY_TARGET_GENDER);
        String target_age = intent.getStringExtra(ConstanHistoryPesanan.KEY_TARGET_AGE);
        String visibility = intent.getStringExtra(ConstanHistoryPesanan.KEY_VISIBILITY);
        String filename = intent.getStringExtra(ConstanHistoryPesanan.KEY_FILENAME);
        String id_merchant = intent.getStringExtra(ConstanHistoryPesanan.KEY_ID_MERCHANT);
        String name_merchant = intent.getStringExtra(ConstanHistoryPesanan.KEY_NAME_MERCHANT);
        String id_company = intent.getStringExtra(ConstanHistoryPesanan.KEY_ID_COMPANY);
        String name_company = intent.getStringExtra(ConstanHistoryPesanan.KEY_NAME_COMPANY);
        String tenor = intent.getStringExtra(ConstanHistoryPesanan.KEY_TENOR);
        //String down_payment = intent.getStringExtra(ConstanHistoryPesanan.KEY_DOWN_PAYMENT);
        int down_payment = (Integer.parseInt(intent.getStringExtra(ConstanHistoryPesanan.KEY_DOWN_PAYMENT)));
        String note = intent.getStringExtra(ConstanHistoryPesanan.KEY_NOTE);
        String id_creditor = intent.getStringExtra(ConstanHistoryPesanan.KEY_ID_CREDITOR);
        //String postal_fee = intent.getStringExtra(ConstanHistoryPesanan.KEY_POSTAL_FEE);
        int postal_fee = (Integer.parseInt(intent.getStringExtra(ConstanHistoryPesanan.KEY_POSTAL_FEE)));
        String address_label = intent.getStringExtra(ConstanHistoryPesanan.KEY_ADDRESS_LABEL);
        String receiver = intent.getStringExtra(ConstanHistoryPesanan.KEY_RECEIVER);
        String mobile = intent.getStringExtra(ConstanHistoryPesanan.KEY_MOBILE);
        String city = intent.getStringExtra(ConstanHistoryPesanan.KEY_CITY);
        String district = intent.getStringExtra(ConstanHistoryPesanan.KEY_DISTRICT);
        String address = intent.getStringExtra(ConstanHistoryPesanan.KEY_ADDRESS);
        String payment_method = intent.getStringExtra(ConstanHistoryPesanan.KEY_PAYMENT_METHOD);
        //String installment = intent.getStringExtra(ConstanHistoryPesanan.KEY_INSTALLMENT);
        int installment = (Integer.parseInt(intent.getStringExtra(ConstanHistoryPesanan.KEY_INSTALLMENT)));
        int total_pembayaran = (Integer.parseInt(intent.getStringExtra(ConstanHistoryPesanan.KEY_TOTAL_PEMBAYARAN)));
       // String total_pembayaran = intent.getStringExtra(ConstanHistoryPesanan.KEY_TOTAL_PEMBAYARAN);
        String courier = intent.getStringExtra(ConstanHistoryPesanan.KEY_COURIER);

        ///Toast.makeText(DetailHistoryPesanan.this, ""+mobile, Toast.LENGTH_LONG).show();
        txt_status_pesanan.setText(status);
        txt_nama_mitra.setText(name_company);
        txt_name_product.setText(name);
        txt_nomor_invoice.setText("Order ID #"+number);
        /*txt_price_capital.setText(price_capital);
        txt_price_sale.setText(price_sale);*/
        tvDownpayment.setText(formatRupiah.format(down_payment));
        tvInstallment.setText(formatRupiah.format(installment)+" /bulan");
        tvOngkoskirim.setText(formatRupiah.format(postal_fee));
        tvKurir.setText(courier);
        tvPaymentMethod.setText(payment_method);
        tvTenor.setText(tenor+" Bulan");
        tvTotalPembayaran.setText(formatRupiah.format(total_pembayaran));
        txt_price_capital.setText(formatRupiah.format(price_capital));
        txt_price_sale.setText(formatRupiah.format(price_sale));

        Glide.with(DetailHistoryPesanan.this)
                .load(filename)
                .placeholder(R.drawable.no_image)
                .error(R.drawable.no_image)
                .into(image);

        if (txt_status_pesanan.getText().toString().equals("CONFIRMED_CREDITOR")){
            informationTransfer.setVisibility(View.VISIBLE);
        }


        tvAlamatPengiriman.setText(""+address_label+"\n"+"Nama penerima : " +receiver+ "\n" + "Alamat :" +address+ "\n" +name_district+" , "+name_city+"\n"+"Koder pos : "+postal_code+"\n"+mobile);

        btnuploaddp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Klik = new Intent(DetailHistoryPesanan.this, KonfirmasiPembayaran.class);
                Klik.putExtra(KEY_ID_TRANSACTION, id_transactions);
                startActivity(Klik);
                finish();
            }
        });

        if (txt_status_pesanan.getText().toString().equals("ON_DELIVERY")){
            informationDelivered.setVisibility(View.VISIBLE);
        }

        btnKonfrimasiBarang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(DetailHistoryPesanan.this);

                builder.setTitle("Konfirmasi Barang");
                builder.setMessage("Apa yakin sudah menerima barang ?");

                builder.setPositiveButton("Konfirmasi", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        String millisInString  = dateFormat.format(new Date());

                        pDialog = new ProgressDialog(DetailHistoryPesanan.this);
                        pDialog.setCancelable(false);
                        pDialog.setMessage("Tunggu...");
                        pDialog.show();

                        HashMap<String, String> params = new HashMap<>();
                        params.put("id_transaction", id_transactions);
                        params.put("status", "DELIVERED");
                        params.put("timestamp", millisInString);
                        params.put("expires", millisInString);
                        Toast.makeText(DetailHistoryPesanan.this, " "+params, Toast.LENGTH_LONG).show();

                        mApiService.postConfirmProduct(params).enqueue(new Callback<ResponseConfirmProduct>() {
                            @Override
                            public void onResponse(Call<ResponseConfirmProduct> call, Response<ResponseConfirmProduct> response) {
                                pDialog.dismiss();
                                if(response.body().getStatus()==200){
                                    //Toast.makeText(TransactionCheckout.this, response.body().getMessage() , Toast.LENGTH_LONG).show();
                                    AlertDialog alertDialog = new AlertDialog.Builder(DetailHistoryPesanan.this).create();

                                    alertDialog.setTitle("Sukses");
                                    alertDialog.setMessage("Data diri berhasil disimpan.");
                                    alertDialog.setIcon(R.drawable.successfully);
                                    alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            alertDialog.dismiss();

                                        }
                                    });

                                    alertDialog.show();
                                }else {
                                    pDialog.dismiss();

                                    Toast.makeText(DetailHistoryPesanan.this, "Gagal konfirmasi, harap cek koneksi anda." , Toast.LENGTH_LONG).show();

                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseConfirmProduct> call, Throwable t) {

                            }
                        });

                        //Toast.makeText(DetailHistoryPesanan.this, "Tanggal  "+millisInString, Toast.LENGTH_LONG).show();


                        ///dialog.dismiss();
                    }

                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        // Do nothing
                        dialog.dismiss();
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();
            }
        });

    }




//    private void ConfirmBarang(){
//        HashMap<String, String> params = new HashMap<>();
//        params.put("id_transaction", id_transactions);
//    }



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
