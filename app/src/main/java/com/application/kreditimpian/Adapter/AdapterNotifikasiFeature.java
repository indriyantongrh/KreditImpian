package com.application.kreditimpian.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

//import com.application.kreditimpian.Model.ModelNotifikasiFeature.ResultItem;
import com.application.kreditimpian.Model.ModelNotifikasiFeatures.ResultItem;
import com.application.kreditimpian.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by indriyanto Nugroho on 6 Jul 2020.
 */
public class AdapterNotifikasiFeature extends RecyclerView.Adapter<AdapterNotifikasiFeature.HolderNotifikasi> {

    List<ResultItem> resultItemList;
    Context mContext;

    public AdapterNotifikasiFeature(Context context, List<ResultItem> dataList) {
        this.mContext = context;
        resultItemList = dataList;
    }

    @NonNull
    @Override
    public HolderNotifikasi onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_notifikasi, parent, false);
        return new HolderNotifikasi(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderNotifikasi holder, int position) {

        final ResultItem resultItem = resultItemList.get(position);

        holder.txtMessageNotif.setText(resultItem.getMessage());
        holder.txtTglNotif.setText(resultItem.getTransaction().getProduct().getTimestamp().getDate());

    }

    @Override
    public int getItemCount() {
        return resultItemList.size();
    }

    public class HolderNotifikasi extends RecyclerView.ViewHolder {
        @BindView(R.id.txtMessageNotif)
        TextView txtMessageNotif;
        @BindView(R.id.txtTglNotif)
        TextView txtTglNotif;
        @BindView(R.id.status)
        TextView status;
        @BindView(R.id.number)
        TextView number;
        @BindView(R.id.id_product)
        TextView id_product;
        @BindView(R.id.id_product_category)
        TextView id_product_category;
        @BindView(R.id.id_currency)
        TextView id_currency;
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.price_capital)
        TextView price_capital;
        @BindView(R.id.price_sale)
        TextView price_sale;
        @BindView(R.id.filename)
        TextView filename;
        @BindView(R.id.tenor)
        TextView tenor;
        @BindView(R.id.down_payment)
        TextView down_payment;
        @BindView(R.id.note)
        TextView note;
        @BindView(R.id.name_company)
        TextView name_company;
        @BindView(R.id.postal_fee)
        TextView postal_fee;
        @BindView(R.id.address_label)
        TextView address_label;
        @BindView(R.id.receiver)
        TextView receiver;
        @BindView(R.id.mobile)
        TextView mobile;
        @BindView(R.id.city)
        TextView city;
        @BindView(R.id.address)
        TextView address;
        @BindView(R.id.postal_code)
        TextView postal_code;
        @BindView(R.id.name_city)
        TextView name_city;
        @BindView(R.id.name_district)
        TextView name_district;
        @BindView(R.id.payment_method)
        TextView payment_method;
        @BindView(R.id.total_pembayaran)
        TextView total_pembayaran;
        @BindView(R.id.courier)
        TextView courier;
        @BindView(R.id.name_merchant)
        TextView name_merchant;
        @BindView(R.id.text_image)
        TextView text_image;
        @BindView(R.id.installment)
        TextView installment;
        @BindView(R.id.layoutKlik)
        ConstraintLayout layoutKlik;

        public HolderNotifikasi(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
