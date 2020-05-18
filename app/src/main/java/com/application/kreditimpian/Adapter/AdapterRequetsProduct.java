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

import com.application.kreditimpian.Model.ModelRequestProduct.DataItem;
import com.application.kreditimpian.R;
import com.bumptech.glide.Glide;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by indriyanto Nugroho on 15 Apr 2020.
 */
public class AdapterRequetsProduct extends RecyclerView.Adapter<AdapterRequetsProduct.HolderRequestProduct> {

    List<DataItem> dataItemList;
    Context mContext;

    public AdapterRequetsProduct(Context context, List<DataItem> dataList) {
        this.mContext = context;
        dataItemList = dataList;
    }

    @NonNull
    @Override
    public HolderRequestProduct onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_request_product, parent, false);
        return new HolderRequestProduct(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderRequestProduct holder, int position) {
        final DataItem dataItem = dataItemList.get(position);

        holder.txt_id.setText(dataItem.getIdMember());
        holder.txt_id_transaction.setText(dataItem.getIdTransaction());
        holder.txt_status_pesanan.setText(dataItem.getStatus());
        holder.txt_nomor_invoice.setText("Order ID #" + dataItem.getNumber());
        holder.txt_reference_id.setText(dataItem.getReferenceId());
        holder.txt_id_product_category.setText(dataItem.getIdProductCategory());
        holder.txt_image.setText(dataItem.getContent());
        holder.txt_name_product.setText(dataItem.getName());
        holder.txt_description.setText(dataItem.getDescription());
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        int Price_Capital = (Integer.parseInt((String) dataItem.getPriceSale()));
        holder.txt_price_sale.setText(formatRupiah.format(Price_Capital));
        holder.txt_weight_value.setText( dataItem.getWeightValue());
        holder.txt_id_city.setText(dataItem.getCompanyCity().getIdGeodirectory());
        holder.txt_id_destination.setText( dataItem.getMemberCity());



/*
        int Price_Sale = (Integer.parseInt(dataItem.getPriceSale()));
        holder.txt_price_sale.setText(formatRupiah.format(Price_Sale));
*/

        Glide.with(mContext)
                .load(dataItem.getContent())
                .placeholder(R.drawable.no_image)
                .error(R.drawable.no_image)
                .into(holder.image);


    }

    @Override
    public int getItemCount() {

        return dataItemList.size();
    }

    public class HolderRequestProduct extends RecyclerView.ViewHolder {

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
        @BindView(R.id.txt_id_city)
        TextView txt_id_city;
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
        @BindView(R.id.txt_id_destination)
        TextView txt_id_destination;


        public HolderRequestProduct(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
