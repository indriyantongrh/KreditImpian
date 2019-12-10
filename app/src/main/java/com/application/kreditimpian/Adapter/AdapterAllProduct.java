package com.application.kreditimpian.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.application.kreditimpian.DetailProduct;
import com.application.kreditimpian.Model.ModelAllProduct.ResultItem;
import com.application.kreditimpian.R;
import com.bumptech.glide.Glide;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.media.CamcorderProfile.get;

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

        final String id = resultItem.getId();
        final String id_currency = resultItem.getIdCurrency();
        final String id_product_category = resultItem.getIdProductCategory();
        final String name = resultItem.getName();
        final String description = resultItem.getDescription();
        final String stock = resultItem.getStock();
        final String price_capital = resultItem.getPriceCapital();
        final String price_sale = resultItem.getPriceSale();
        final String condition = resultItem.getCondition();
        final String image = resultItem.getImage();

        holder.btnclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent detailproduct = new Intent(v.getContext(), DetailProduct.class);
                detailproduct.putExtra("id", id);
                detailproduct.putExtra("id_product_category", id_product_category);
                detailproduct.putExtra("id_currency", id_currency);
                detailproduct.putExtra("name", name);
                detailproduct.putExtra("price_capital", price_capital);
                detailproduct.putExtra("price_sale", price_sale);
                detailproduct.putExtra("description", description);
                detailproduct.putExtra("stock", stock);
                detailproduct.putExtra("condition", condition);
                detailproduct.putExtra("image", image);
                v.getContext().startActivity(detailproduct);
            }
        });


    }

    @Override
    public int getItemCount() {
        return resultItemList.size();
    }

    public class AllproductHolder extends RecyclerView.ViewHolder {

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



        public AllproductHolder(@NonNull View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);

/*

            //Inisialisasi onclick pada itemview dan memanggil interface yang sudah kita buat tadi.
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    *//*
                    Memanggil interface dan juga methodnya. getAdapterPosition ini adalah method bawaan
                    adapter untuk memanggil index posisi.
                     *//*
                    Intent intent = new Intent(v.getContext(), DetailProduct.class) ;

                    intent.putExtra("id", txt_id.getText());
                    intent.putExtra("id_product_category", txt_id_product_category.getText());
                    intent.putExtra("id_currency", txt_id_currency.getText());
                    intent.putExtra("name", txt_name_product.getText());
                    intent.putExtra("price_capital", txt_price_capital.getText());
                    intent.putExtra("price_sale", txt_price_sale.getText());
                    intent.putExtra("description", txt_description.getText());
//                    intent.putExtra("sku", txt_sku.getText());
                    intent.putExtra("stock", txt_stock.getText());
                    intent.putExtra("condition", txt_condition.getText());
//                    intent.putExtra("deliverable", txt_deliverable.getText());
//                    intent.putExtra("downloadable", txt_downloadable.getText());
//                    intent.putExtra("target_gender", txt_target_gender.getText());
//                    intent.putExtra("target_age", txt_target_age.getText());
//                    intent.putExtra("visibility", txt_visibility.getText());
//                    intent.putExtra("image", txt_image.getText());
                    //intent.putExtra("id", image.getImageAlpha());

                    v.getContext().startActivity(intent);

                }
            });*/
        }




    }



}
