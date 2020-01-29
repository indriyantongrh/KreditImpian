package com.application.kreditimpian.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.application.kreditimpian.Model.ModelListAlamat.DataItem;
import com.application.kreditimpian.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by indriyanto Nugroho on 28 Jan 2020.
 */
public class AdapterAddresses extends RecyclerView.Adapter<AdapterAddresses.HolderAdresses> {

    List<DataItem> dataItemList;
    Context mContext;

    public  AdapterAddresses(Context context , List<DataItem> dataList){
        this.mContext= context;
        dataItemList = dataList;
    }

    @NonNull
    @Override
    public HolderAdresses onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_addresses, parent,false);
        return new HolderAdresses(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderAdresses holder, int position) {
        final DataItem dataItem = dataItemList.get(position);

        holder.txt_id.setText(dataItem.getId());
        holder.txt_member.setText(dataItem.getIdMember());
        holder.txt_nama_alamat.setText(dataItem.getAddressName());
        holder.txt_name_penerima.setText(dataItem.getReceiver());
        holder.txt_nomor_handphone.setText(dataItem.getPhone());
        holder.txt_alamat.setText(dataItem.getAddress());
        holder.txt_main_address.setText(dataItem.getMainAddress());
        holder.txt_kodepost.setText(dataItem.getPostalCode());
        holder.txt_alamat_utama.setText(dataItem.getMainAddress());


    }

    @Override
    public int getItemCount() {
        return dataItemList.size();
    }

    public class HolderAdresses extends RecyclerView.ViewHolder {

        @BindView(R.id.txt_nama_alamat)
        TextView txt_nama_alamat;
        @BindView(R.id.txt_name_penerima)
        TextView txt_name_penerima;
        @BindView(R.id.txt_nomor_handphone)
        TextView txt_nomor_handphone;
        @BindView(R.id.txt_alamat)
        TextView txt_alamat;
        @BindView(R.id.txt_kodepost)
        TextView txt_kodepost;
        @BindView(R.id.txt_id)
        TextView txt_id;
        @BindView(R.id.txt_member)
        TextView txt_member;
        @BindView(R.id.id_geodirectory)
        TextView id_geodirectory;
        @BindView(R.id.district)
        TextView district;
        @BindView(R.id.txt_main_address)
        TextView txt_main_address;
        @BindView(R.id.txt_alamat_utama)
        TextView txt_alamat_utama;
        @BindView(R.id.text_alamat_utama)
        TextView text_alamat_utama;



        public HolderAdresses(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }
}
