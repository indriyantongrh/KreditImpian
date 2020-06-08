package com.application.kreditimpian.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.application.kreditimpian.Model.ModelNotifFitur.DataItem;
import com.application.kreditimpian.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by indriyanto Nugroho on 28 Mei 2020.
 */
public class AdapterNotifFitur extends RecyclerView.Adapter<AdapterNotifFitur.HolderNotifFitur> {

    List<DataItem> dataItemList;
    Context mContext;

    public AdapterNotifFitur(Context context, List<DataItem> dataList){
        this.mContext = context;
        dataItemList = dataList;

    }

    @NonNull
    @Override
    public HolderNotifFitur onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_notifikasi, parent, false);
        return new HolderNotifFitur(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderNotifFitur holder, int position) {
        final DataItem dataItem = dataItemList.get(position);

        holder.txtMessageNotif.setText(dataItem.getMessage());
        holder.txtTglNotif.setText(dataItem.getRecordCreateTimestamp());

        if(dataItem.getName()==null){
            holder.name.setText("");

        }else if(dataItem.getPrice_sale()==null){
            holder.price_sale.setText("Harga Menunggu Konfirmasi Admin");
        }

        if(dataItem.getMethod().equals("MULTIFUNCTION")||dataItem.getName()==null){
            holder.name.setVisibility(View.GONE);
            holder.price_sale.setVisibility(View.GONE);
        }else if(dataItem.getMethod().equals("MULTIFUNCTION")||dataItem.getPrice_sale()==null){
            holder.name.setVisibility(View.GONE);
            holder.price_sale.setVisibility(View.GONE);
        }
}

    @Override
    public int getItemCount() {
        return dataItemList.size();
    }

    public class HolderNotifFitur extends RecyclerView.ViewHolder {


        @BindView(R.id.txtMessageNotif)
        TextView txtMessageNotif;
        @BindView(R.id.txtTglNotif)
        TextView txtTglNotif;
        @BindView(R.id.status)
        TextView status;
        @BindView(R.id.number)
        TextView number;
        @BindView(R.id.id_product_category)
        TextView id_product_category;
        @BindView(R.id.filename)
        TextView filename;
        @BindView(R.id.id_product)
        TextView id_product;
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.price_sale)
        TextView price_sale;


        public HolderNotifFitur(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
