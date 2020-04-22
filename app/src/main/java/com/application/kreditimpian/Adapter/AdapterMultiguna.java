package com.application.kreditimpian.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.application.kreditimpian.Model.ModelMultiguna.DataItem;
import com.application.kreditimpian.R;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by indriyanto Nugroho on 22 Apr 2020.
 */
public class AdapterMultiguna  extends RecyclerView.Adapter<AdapterMultiguna.HolderMultiguna> {

    List<DataItem> dataItemList;
    Context mContext;

    public AdapterMultiguna(Context context, List<DataItem> dataList){
        this.mContext = context;
        dataItemList = dataList;
    }


    @NonNull
    @Override
    public HolderMultiguna onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_history_multiguna, parent, false);
        return new HolderMultiguna(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderMultiguna holder, int position) {
        final DataItem  dataItem =  dataItemList.get(position);

        holder.txt_id.setText(dataItem.getIdMember());
        holder.txt_id_transaction.setText(dataItem.getIdTransaction());
        holder.txt_reference_id.setText(dataItem.getReferenceRow4id());
        holder.txt_id_creditor.setText(dataItem.getIdCreditor());
        holder.txt_tenor.setText(dataItem.getTenor());
        holder.txt_installment.setText(dataItem.getCicilan());
        holder.txt_tahun_kendaraan.setText(dataItem.getVehicleYear());
        holder.txt_nama_kenadaraan.setText(dataItem.getVehicleType());
        holder.txt_merk_kendaraan.setText(dataItem.getVehicleBrand());
        holder.txt_harga_kendaraan.setText(dataItem.getVehiclePrice());
        holder.txt_pinjaman.setText(dataItem.getLoan());
        holder.txt_type_multiguna.setText(dataItem.getVehicles());
        holder.txt_image.setText(dataItem.getImages());
        holder.txt_nomor_invoice.setText("Order ID "+dataItem.getNumber());
        holder.txt_status_pesanan.setText(dataItem.getStatus());
        String typeMultiguna = dataItem.getVehicles();
        String textMultiguna = "Multiguna Motor";
        if (typeMultiguna.equals("motorcycle")){
            holder.txt_name_product.setText("Multiguna Motor");
        }else {
            holder.txt_name_product.setText("Multiguna Motor");
        }




        Glide.with(mContext)
                .load(dataItem.getImages())
                .placeholder(R.drawable.no_image)
                .error(R.drawable.no_image)
                .into(holder.image);




    }

    @Override
    public int getItemCount() {
        return dataItemList.size();
    }

    public class HolderMultiguna extends RecyclerView.ViewHolder {

        @BindView(R.id.btnclick)
        CardView btnclick;
        @BindView(R.id.txt_status_pesanan)
        TextView txt_status_pesanan;
        @BindView(R.id.image)
        ImageView image;
        @BindView(R.id.txt_name_product)
        TextView txt_name_product;
        @BindView(R.id.txt_price_capital)
        TextView txt_price_capital;
        @BindView(R.id.txt_price_sale)
        TextView txt_price_sale;
        @BindView(R.id.txt_name_merchant)
        TextView txt_name_merchant;
        @BindView(R.id.txt_id)
        TextView txt_id;
        @BindView(R.id.txt_reference_id)
        TextView txt_reference_id;
        @BindView(R.id.txt_id_product_category)
        TextView txt_id_product_category;
        @BindView(R.id.txt_image)
        TextView txt_image;
        @BindView(R.id.txt_description)
        TextView txt_description;
        @BindView(R.id.txt_tipe_kendaraan)
        TextView txt_tipe_kendaraan;
        @BindView(R.id.txt_harga_kendaraan)
        TextView txt_harga_kendaraan;
        @BindView(R.id.txt_merk_kendaraan)
        TextView txt_merk_kendaraan;
        @BindView(R.id.txt_nama_kenadaraan)
        TextView txt_nama_kenadaraan;
        @BindView(R.id.txt_tahun_kendaraan)
        TextView txt_tahun_kendaraan;
        @BindView(R.id.txt_asuransi)
        TextView txt_asuransi;
        @BindView(R.id.txt_lokasi)
        TextView txt_lokasi;
        @BindView(R.id.txt_pinjaman)
        TextView txt_pinjaman;
        @BindView(R.id.txt_number)
        TextView txt_number;
        @BindView(R.id.txt_type_multiguna)
        TextView txt_type_multiguna;
        @BindView(R.id.txt_tenor)
        TextView txt_tenor;
        @BindView(R.id.txt_down_payment)
        TextView txt_down_payment;
        @BindView(R.id.txt_note)
        TextView txt_note;
        @BindView(R.id.txt_id_creditor)
        TextView txt_id_creditor;
        @BindView(R.id.txt_postal_fee)
        TextView txt_postal_fee;
        @BindView(R.id.txt_id_transaction)
        TextView txt_id_transaction;
        @BindView(R.id.txt_timestamp)
        TextView txt_timestamp;
        @BindView(R.id.txt_expires)
        TextView txt_expires;
        @BindView(R.id.txt_id_product)
        TextView txt_id_product;
        @BindView(R.id.txt_nomor_invoice)
        TextView txt_nomor_invoice;
        @BindView(R.id.txt_nama_mitra)
        TextView txt_nama_mitra;
        @BindView(R.id.txt_ongkos_kirim)
        TextView txt_ongkos_kirim;
        @BindView(R.id.txt_installment)
        TextView txt_installment;
        @BindView(R.id.txt_total_pembayaran)
        TextView txt_total_pembayaran;
        @BindView(R.id.txt_address)
        TextView txt_address;
        @BindView(R.id.txt_city)
        TextView txt_city;
        @BindView(R.id.txt_mobile)
        TextView txt_mobile;
        @BindView(R.id.txt_receive)
        TextView txt_receive;
        @BindView(R.id.txt_address_label)
        TextView txt_address_label;
        @BindView(R.id.txt_district)
        TextView txt_district;




        public HolderMultiguna(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
