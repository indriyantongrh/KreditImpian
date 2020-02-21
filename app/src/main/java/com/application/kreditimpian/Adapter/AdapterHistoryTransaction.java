package com.application.kreditimpian.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.application.kreditimpian.Model.ModelHistoryTransaction.DataItem;
import com.application.kreditimpian.R;
import com.bumptech.glide.Glide;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by indriyanto Nugroho on 19 Feb 2020.
 */
public class AdapterHistoryTransaction extends RecyclerView.Adapter<AdapterHistoryTransaction.HolderHistoryTransaction> {

    List<DataItem> dataItemList;
    Context mContext;

    public AdapterHistoryTransaction(Context context, List<DataItem> dataList){
        this.mContext = context;
        dataItemList = dataList;
    }

    @NonNull
    @Override
    public HolderHistoryTransaction onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_history_pesanan, parent, false);
        return new HolderHistoryTransaction(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderHistoryTransaction holder, int position) {
        final DataItem dataItem =  dataItemList.get(position);

        holder.txt_id.setText(dataItem.getId());
        holder.txt_id_transaction.setText(dataItem.getIdTransactions());
        holder.txt_status_pesanan.setText(dataItem.getStatus());
        //holder.txt_timestamp.setText((Integer) dataItem.getTimestamp());
       /// holder.txt_expires.setText((Integer) dataItem.getExpires());
        holder.txt_nomor_invoice.setText("Order ID #"+dataItem.getNumber());
        holder.txt_id_product_category.setText(dataItem.getIdProductCategory());
        holder.txt_id_product.setText(dataItem.getIdProduct());
        holder.txt_name_product.setText(dataItem.getName());
        holder.txt_description.setText(dataItem.getDescription());
        holder.txt_sku.setText(dataItem.getSku());
        holder.txt_stock.setText(dataItem.getStock());
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        int Price_Capital = (Integer.parseInt(dataItem.getPriceCapital()));
        int Price_Sale = (Integer.parseInt(dataItem.getPriceSale()));

        if (Price_Capital == Price_Sale) {
            holder.txt_price_capital.setVisibility(View.GONE);
        } else {
            holder.txt_price_capital.setVisibility(View.VISIBLE);
        }
        holder.txt_price_capital.setText(formatRupiah.format(Price_Capital));
        holder.txt_price_sale.setText(formatRupiah.format(Price_Sale));
        holder.txt_condition.setText(dataItem.getCondition());
        holder.txt_nama_mitra.setText(dataItem.getName_company());
        holder.txt_image.setText(dataItem.getFilename());

        Glide.with(mContext)
                .load(dataItem.getFilename())
                .placeholder(R.drawable.no_image)
                .error(R.drawable.no_image)
                .into(holder.image);




    }

    @Override
    public int getItemCount() {
        return dataItemList.size();
    }

    public class HolderHistoryTransaction extends RecyclerView.ViewHolder {

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
        @BindView(R.id.txt_id_currency)
        TextView txt_id_currency;
        @BindView(R.id.txt_image)
        TextView txt_image;
        @BindView(R.id.txt_description)
        TextView txt_description;
        @BindView(R.id.txt_sku)
        TextView txt_sku;
        @BindView(R.id.txt_stock)
        TextView txt_stock;
        @BindView(R.id.txt_condition)
        TextView txt_condition;
        @BindView(R.id.txt_deliverable)
        TextView txt_deliverable;
        @BindView(R.id.txt_downloadable)
        TextView txt_downloadable;
        @BindView(R.id.txt_weight_value)
        TextView txt_weight_value;
        @BindView(R.id.txt_weight)
        TextView txt_weight;
        @BindView(R.id.txt_length_value)
        TextView txt_length_value;
        @BindView(R.id.txt_length)
        TextView txt_length;
        @BindView(R.id.txt_width_value)
        TextView txt_width_value;
        @BindView(R.id.txt_width)
        TextView txt_width;
        @BindView(R.id.txt_height_value)
        TextView txt_height_value;
        @BindView(R.id.txt_height)
        TextView txt_height;
        @BindView(R.id.txt_location_merchant)
        TextView txt_location_merchant;
        @BindView(R.id.imagemerchant)
        ImageView imagemerchant;
        @BindView(R.id.txt_image_merchant)
        TextView txt_image_merchant;
        @BindView(R.id.txt_number)
        TextView txt_number;
        @BindView(R.id.txt_courier)
        TextView txt_courier;
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



        public HolderHistoryTransaction(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
