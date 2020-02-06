package com.application.kreditimpian.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.application.kreditimpian.Model.ModelCicilan.CompaniesDataItem;
import com.application.kreditimpian.Model.ModelCicilan.Data;
import com.application.kreditimpian.Model.ModelCicilan.DataCicilanItem;
import com.application.kreditimpian.Model.ModelCicilan.ProductMeta;
import com.application.kreditimpian.R;
import com.application.kreditimpian.TransactionProcess.TransactionSelectTenor;
import com.bumptech.glide.Glide;
import com.chivorn.smartmaterialspinner.SmartMaterialSpinner;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.application.kreditimpian.FormPengajuan.UpgradeImpian.PilihLeasingAdapter.localeID;

/**
 * Created by indriyanto Nugroho on 5 Feb 2020.
 */
public class AdapterSelectedTenor extends RecyclerView.Adapter<AdapterSelectedTenor.HolderTenor> {

    List<CompaniesDataItem> companiesDataItemList;
    Context mContext;
    private String tenor ="", code,
            cicilan;
    private  ProductMeta productMeta;
    public static final Locale localeID = new Locale("in", "ID");

    public AdapterSelectedTenor (Context context, ProductMeta productMeta, ArrayList<CompaniesDataItem> companiesList){
        this.mContext= context;
       companiesDataItemList = companiesList;
        this.productMeta = productMeta;
        Log.v("jajal", "masuk1");
    }


    @NonNull
    @Override
    public HolderTenor onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_pilih_tenor, parent, false);
        return  new HolderTenor(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderTenor holder, int position) {

        Log.v("jajal", "masuk2");
        final  CompaniesDataItem data = companiesDataItemList.get(position);

      //  holder.tvDownpayment.setText(companiesDataItem.get());
        holder.tvNamaKendaraan.setText(productMeta.getName());
        holder.tvPriceSale.setText(productMeta.getPriceSale());
        holder.tvPriceCapital.setText(productMeta.getPriceCapital());
        Glide.with(mContext)
                .load(productMeta.getFilename())
                .placeholder(R.drawable.no_image)
                .error(R.drawable.no_image)
                .into(holder.imgProduct);
        holder.tvMitraKredit.setText(data.getName());

        ArrayList<String> stringArrayList = new ArrayList<>();
        for (int i = 0; i < data.getDataCicilan().size(); i++) {
            DataCicilanItem modelPinjaman = data.getDataCicilan().get(i);
            stringArrayList.add(modelPinjaman.getBulan() + " bulan X Rp. " + String.format(localeID, "%,d", Long.parseLong("2000000"/*String.valueOf(modelPinjaman.getCicilan())*/)));
        }

     holder.spinCicilan.setItem(stringArrayList);
        holder.spinCicilan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tenor = data.getDataCicilan().get(position).getBulan();
                cicilan = data.getDataCicilan().get(position).getCicilan();
                Log.v("jajal", tenor+"tenr");
                Log.v("jajal", cicilan+"cicilan");
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

    public class HolderTenor extends RecyclerView.ViewHolder {

        @BindView(R.id.imgProduct)
        ImageView imgProduct;
        @BindView(R.id.tvNamaKendaraan)
        TextView tvNamaKendaraan;
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

        public HolderTenor(@NonNull View itemView) {

            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
