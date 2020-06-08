package com.application.kreditimpian.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.application.kreditimpian.Model.ModelCicilanFitur.DataCicilanItem;
import com.application.kreditimpian.Model.ModelCicilanFitur.CompaniesDataItem;
import com.application.kreditimpian.Model.ModelCicilanFitur.Data;
import com.application.kreditimpian.R;
import com.application.kreditimpian.TransactionProcess.TransactionCheckout;
import com.bumptech.glide.Glide;
import com.chivorn.smartmaterialspinner.SmartMaterialSpinner;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.zip.CheckedOutputStream;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by indriyanto Nugroho on 5 Jun 2020.
 */
public class AdapterSelectedTenorFitur extends RecyclerView.Adapter<AdapterSelectedTenorFitur.HolderCicilanFitur> {

    List<CompaniesDataItem> companiesDataItemList;
    Context mContext;
    private String tenor = "", code,
            cicilan;
    public static final Locale localeID = new Locale("in", "ID");

    public AdapterSelectedTenorFitur(Context context, ArrayList<CompaniesDataItem> companiesList){
        this.mContext = context;
        companiesDataItemList = companiesList;
        Log.v("jajal", "masuk1");
    }

    @NonNull
    @Override
    public AdapterSelectedTenorFitur.HolderCicilanFitur onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_pilih_tenor, parent, false);
        return new HolderCicilanFitur(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderCicilanFitur holder, int position) {
        Log.v("jajal", "masuk2");
///        final CompaniesDataItem data = companiesDataItemList.get(position);
        Intent intent = ((Activity) mContext).getIntent();
        String imageProduct = intent.getStringExtra("image_product");
        String nameProduct = intent.getStringExtra("name_product");
        String priceProduct = intent.getStringExtra("price_sale");
        String priceCapProduct = intent.getStringExtra("price_sale");


        final CompaniesDataItem dataItem = companiesDataItemList.get(position);
        Log.v("jajal", "masuk2"+dataItem);
        holder.tvIdCreditor.setText(dataItem.getIdCompany());
        holder.tvDownpayment.setText(dataItem.getDownPayment());
        holder.tvMitraKredit.setText(dataItem.getName());
        Glide.with(mContext)
                .load(imageProduct)
                .placeholder(R.drawable.no_image)
                .error(R.drawable.no_image)
                .into(holder.imgProduct);
        holder.tvNamaKendaraan.setText(nameProduct);
        holder.tvPriceSale.setText(priceProduct);
        //holder.tvPriceCapital.setText(priceProduct);

        if (priceProduct == priceCapProduct) {
            holder.tvPriceCapital.setVisibility(View.GONE);
        } else {
            holder.tvPriceCapital.setVisibility(View.VISIBLE);
        }


        ArrayList<String> stringArrayList = new ArrayList<>();
        for (int i = 0; i < dataItem.getDataCicilan().size(); i++) {
            DataCicilanItem modelPinjaman = dataItem.getDataCicilan().get(i);
            stringArrayList.add(modelPinjaman.getBulan() + " bulan X Rp. " + String.format(localeID, "%,d", Long.parseLong(String.valueOf(modelPinjaman.getCicilan()))));
        }

        holder.spinCicilan.setItem(stringArrayList);
        holder.spinCicilan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tenor = dataItem.getDataCicilan().get(position).getBulan();
                cicilan = dataItem.getDataCicilan().get(position).getCicilan();
                Log.v("jajal", tenor + "tenr");
                Log.v("jajal", cicilan + "cicilan");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    @Override
    public int getItemCount() {

            return companiesDataItemList.size();
    }

    public class HolderCicilanFitur extends RecyclerView.ViewHolder {

        @BindView(R.id.imgProduct)
        ImageView imgProduct;
        @BindView(R.id.tvNamaKendaraan)
        TextView tvNamaKendaraan;
        @BindView(R.id.tvIdCreditor)
        TextView tvIdCreditor;
        @BindView(R.id.tvPriceSale)
        TextView tvPriceSale;
        @BindView(R.id.tvPriceCapital)
        TextView tvPriceCapital;
        @BindView(R.id.tvDownpayment)
        TextView tvDownpayment;
        @BindView(R.id.tvMitraKredit)
        TextView tvMitraKredit;
        @BindView(R.id.spinCicilan)
        SmartMaterialSpinner spinCicilan;
        @BindView(R.id.btnPilihLeasing)
        Button btnPilihLeasing;


        public HolderCicilanFitur(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            Intent intent = ((Activity) mContext).getIntent();
            String id_member = intent.getStringExtra("id_member");
            String id_product = intent.getStringExtra("id_product");
            String number = intent.getStringExtra("number");
            String id_product_category = intent.getStringExtra("id_product_category");
            String image_product = intent.getStringExtra("image_product");
            String price_sale = intent.getStringExtra("price_sale");
            String name_product = intent.getStringExtra("name_product");
            String estimasipengiman = intent.getStringExtra("estimasipengiman");
            String courier = intent.getStringExtra("courier");
            String note = intent.getStringExtra("note");
            String id_transaction = intent.getStringExtra("id_transaction");

            btnPilihLeasing.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int SpinnerText = spinCicilan.getSelectedItemPosition();
                    ////Toast.makeText(mContext, ""+SpinnerText , Toast.LENGTH_LONG).show();
                    if (SpinnerText != -1) {
                        Intent intent = new Intent(mContext, TransactionCheckout.class);
                        intent.putExtra("id_member", id_member);
                        intent.putExtra("id_product", id_product);
                        intent.putExtra("number", number);
                        intent.putExtra("id_product_category", id_product_category);
                        intent.putExtra("image_product", image_product);
                        intent.putExtra("price_sale", price_sale);
                        intent.putExtra("name_product", name_product);
                        intent.putExtra("estimasipengiman", estimasipengiman);
                        intent.putExtra("courier", courier);
                        intent.putExtra("note", note);
                        intent.putExtra("id_transaction", id_transaction);
                        intent.putExtra("tenor", tenor);
                        intent.putExtra("cicilan", cicilan);
                        intent.putExtra("downpayment", tvDownpayment.getText().toString());
                        intent.putExtra("name_mitra", tvMitraKredit.getText().toString());
                        intent.putExtra("id_creditor", tvIdCreditor.getText().toString());
                        mContext.startActivity(intent);
                    } else {

                        ///Toast.makeText(mContext, "Anda belum memilih tenor" , Toast.LENGTH_LONG).show();
                        AlertDialog alertDialog = new AlertDialog.Builder(mContext).create();

                        alertDialog.setTitle("Info");
                        alertDialog.setMessage("Pilih jangka waktu cicilan");
                        alertDialog.setIcon(R.drawable.alert);
                        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                alertDialog.dismiss();

                            }
                        });

                        alertDialog.show();
                    }

                    ///  Toast.makeText(mContext, "Downpayment "+tvDownpayment.getText().toString(), Toast.LENGTH_LONG).show();

                }
            });

        }
    }
}
