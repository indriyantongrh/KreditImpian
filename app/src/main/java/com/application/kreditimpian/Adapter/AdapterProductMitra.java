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

import com.application.kreditimpian.Model.ModelProductMitra.DataItem;
import com.application.kreditimpian.R;
import com.bumptech.glide.Glide;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Indriyantongrh on 23/09/20.
 */
public class AdapterProductMitra extends RecyclerView.Adapter<AdapterProductMitra.HolderProductMitra> {

    List<DataItem> resultItemList;
    Context mContext;

    public AdapterProductMitra(Context context, List<DataItem> resultList){
        this.mContext = context;
        resultItemList = resultList;
    }

    @NonNull
    @Override
    public HolderProductMitra onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View view  =  LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_product, parent, false);
      return new HolderProductMitra(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterProductMitra.HolderProductMitra holder, int position) {
        final DataItem dataItem  = resultItemList.get(position);

        holder.txt_id.setText(dataItem.getIdProduct());
        holder.txt_id_product_category.setText(dataItem.getIdProductCategory());
        holder.txt_id_currency.setText(dataItem.getIdCurrency());
        holder.txt_name_product.setText(dataItem.getNameProduct());
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
        holder.txt_deliverable.setText(dataItem.getDeliverable());
        holder.txt_target_gender.setText(dataItem.getTargetGender());
        holder.txt_target_age.setText(dataItem.getTargetAge());
        holder.txt_visibility.setText(dataItem.getVisibility());
        Glide.with(mContext)
                .load(dataItem.getImage())
                .placeholder(R.drawable.no_image)
                .error(R.drawable.no_image)
                .into(holder.image);
        holder.txt_image.setText(dataItem.getImage());
        holder.txt_weight.setText(dataItem.getWeight());
        holder.txt_weight_value.setText(dataItem.getWeightValue());
        holder.txt_name_merchant.setText(dataItem.getNameCompany());
      ///  holder.txt_id_company_category.setText(dataItem.id());
        holder.txt_id_merchant.setText(dataItem.getIdCompany());
        //holder.txt_location_merchant.setText(dataItem.getCity());


    }

    @Override
    public int getItemCount() {
        return resultItemList.size();
    }

    public class HolderProductMitra extends RecyclerView.ViewHolder {

        @BindView(R.id.btnclick)
        CardView btnclick;
        @BindView(R.id.txt_id)
        TextView txt_id;
        @BindView(R.id.txt_id_product_category)
        TextView txt_id_product_category;
        @BindView(R.id.txt_id_currency)
        TextView txt_id_currency;
        @BindView(R.id.txt_name_product)
        TextView txt_name_product;
        @BindView(R.id.txt_price_capital)
        TextView txt_price_capital;
        @BindView(R.id.txt_price_sale)
        TextView txt_price_sale;
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
        @BindView(R.id.txt_target_gender)
        TextView txt_target_gender;
        @BindView(R.id.txt_target_age)
        TextView txt_target_age;
        @BindView(R.id.txt_visibility)
        TextView txt_visibility;
        @BindView(R.id.image)
        ImageView image;
        @BindView(R.id.txt_image)
        TextView txt_image;
        @BindView(R.id.txt_image1)
        TextView txt_image1;
        @BindView(R.id.txt_image2)
        TextView txt_image2;
        @BindView(R.id.txt_image3)
        TextView txt_image3;
        @BindView(R.id.txt_image4)
        TextView txt_image4;
        @BindView(R.id.txt_weight_value)
        TextView txt_weight_value;
        @BindView(R.id.txt_weight)
        TextView txt_weight;
        @BindView(R.id.txt_name_merchant)
        TextView txt_name_merchant;
        @BindView(R.id.txt_id_merchant)
        TextView txt_id_merchant;
        @BindView(R.id.txt_id_company_category)
        TextView txt_id_company_category;
        @BindView(R.id.txt_location_merchant)
        TextView txt_location_merchant;
        @BindView(R.id.imagemerchant)
        ImageView imagemerchant;
        @BindView(R.id.txt_image_merchant)
        TextView txt_image_merchant;

        public HolderProductMitra(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
