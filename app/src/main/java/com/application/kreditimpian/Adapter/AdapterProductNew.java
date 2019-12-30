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

import com.application.kreditimpian.Model.ModelProductNew.ResultItem;
import com.application.kreditimpian.R;
import com.bumptech.glide.Glide;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by indriyanto Nugroho on 27 Des 2019.
 */
public class AdapterProductNew extends RecyclerView.Adapter<AdapterProductNew.ProductHolder> {

    List<ResultItem> resultItemList;
    Context mContext;

    public AdapterProductNew(Context context, List<ResultItem> resultList){
        this.mContext  = context;
        resultItemList = resultList;

    }

    @NonNull
    @Override
    public ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_product, parent,false);
        return new ProductHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductHolder holder, int position) {
        final ResultItem resultItem = resultItemList.get(position);

        holder.txt_id.setText(resultItem.getId());
        holder.txt_id_currency.setText(resultItem.getCurrency().getId());
        holder.txt_id_product_category.setText(resultItem.getCategory().getId());
        holder.txt_name_product.setText(resultItem.getName());

        ///convert String to Rupiah Curerncy
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        int Price_Capital = (Integer.parseInt(resultItem.getPriceCapital()));
        int Price_Sale = (Integer.parseInt(resultItem.getPriceSale()));

        holder.txt_price_capital.setText(formatRupiah.format(Price_Capital));
        holder.txt_price_sale.setText(formatRupiah.format(Price_Sale));


        holder.txt_description.setText(resultItem.getDescription());
        holder.txt_sku.setText(resultItem.getSku());
        holder.txt_stock.setText(resultItem.getStock());
        //holder.image.setText(resultItem.getImage());
        holder.txt_condition.setText(resultItem.getCondition());
        holder.txt_deliverable.setText(resultItem.getDeliverable());
        holder.txt_downloadable.setText(resultItem.getDownloadable());
        holder.txt_target_gender.setText(resultItem.getTargetGender());
        holder.txt_target_age.setText(resultItem.getTargetAge());
        holder.txt_visibility.setText(resultItem.getVisibility());
        holder.txt_image.setText(resultItem.getImage());
//        holder.txt_image1.setText(imageItem.getImage());
//        holder.txt_image2.setText(imageItem.getImage());
//        holder.txt_image3.setText(imageItem.getImage());
//        holder.txt_image4.setText(imageItem.getImage());
        Glide.with(mContext)
                .load(resultItem.getImage())
                .placeholder(R.drawable.no_image)
                .error(R.drawable.no_image)
                .into(holder.image);


        holder.txt_weight_value.setText(resultItem.getMetadata().getWeightValue());
        holder.txt_weight.setText(resultItem.getMetadata().getWeight());
        holder.txt_name_merchant.setText(resultItem.getMerchant().getName());
        holder.txt_location_merchant.setText(resultItem.getMerchant().getCity());

    }

    @Override
    public int getItemCount() {
        return resultItemList.size();
    }

    public class ProductHolder extends RecyclerView.ViewHolder {

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
        @BindView(R.id.txt_location_merchant)
        TextView txt_location_merchant;
        @BindView(R.id.imagemerchant)
        ImageView imagemerchant;
        @BindView(R.id.txt_image_merchant)
        TextView txt_image_merchant;


        public ProductHolder(@NonNull View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
