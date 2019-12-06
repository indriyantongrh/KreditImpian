package com.application.kreditimpian.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.application.kreditimpian.Model.ModelAllProduct.ResultItem;
import com.application.kreditimpian.R;
import com.bumptech.glide.Glide;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterAllProduct extends RecyclerView.Adapter<AdapterAllProduct.AllproductHolder> {

   List<ResultItem> resultItemList;
   Context mContext;

    public AdapterAllProduct(Context context, List<ResultItem> dosenList){
        this.mContext = context;
        resultItemList = dosenList;
    }

    @NonNull
    @Override
    public AllproductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_product, parent,false);
        return new AllproductHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AllproductHolder holder, int position) {
        final ResultItem resultItem = resultItemList.get(position);
        holder.txt_id.setText(resultItem.getId());
        holder.txt_id_currency.setText(resultItem.getIdCurrency());
        holder.txt_id_product_category.setText(resultItem.getIdCurrency());
        holder.txt_name_product.setText(resultItem.getName());
        holder.txt_price_capital.setText(resultItem.getPriceCapital());
//        Locale localeID = new Locale("in", "ID");
//        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
//        holder.txt_price_capital.setText(formatRupiah.format(resultItem.getPriceCapital()));

        holder.txt_price_sale.setText(resultItem.getPriceSale());
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
        Glide.with(mContext)
                .load(resultItem.getImage())
                .placeholder(R.drawable.no_image)
                .error(R.drawable.no_image)
                .into(holder.image);




    }

    @Override
    public int getItemCount() {
        return resultItemList.size();
    }

    public class AllproductHolder extends RecyclerView.ViewHolder {

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




        public AllproductHolder(@NonNull View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
