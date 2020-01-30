package com.application.kreditimpian.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.application.kreditimpian.Akun.DataDiri;
import com.application.kreditimpian.Model.ModelOnShoppingCart.DataItem;
import com.application.kreditimpian.R;
import com.application.kreditimpian.TransactionProcess.Cart;
import com.bumptech.glide.Glide;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.application.kreditimpian.Api.network.interceptor.MyApp.getContext;

/**
 * Created by indriyanto Nugroho on 30 Jan 2020.
 */
public class AdapterCart extends RecyclerView.Adapter<AdapterCart.HolderCart> {

    List<DataItem> dataItemList;
    Context mContext;

    public  AdapterCart(Context context , List<DataItem> dataList){
        this.mContext= context;
        dataItemList = dataList;
    }

    @NonNull
    @Override
    public HolderCart onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_cart, parent,false);
        return  new HolderCart(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderCart holder, int position) {
            final DataItem dataItem = dataItemList.get(position);

            holder.txt_id.setText(dataItem.getId());
            holder.txt_id_product_category.setText(dataItem.getIdProductCategory());
            holder.txt_name_product.setText(dataItem.getName());
            holder.txt_reference_id.setText(dataItem.getReferenceId());
            holder.txt_status.setText(dataItem.getStatus());
            holder.txt_number.setText(dataItem.getNumber());
            Locale localeID = new Locale("in", "ID");
            NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
            int Price_Capital = (Integer.parseInt(dataItem.getPriceCapital()));
            int Price_Sale = (Integer.parseInt(dataItem.getPriceSale()));

            if (Price_Capital == Price_Sale) {
                holder.txt_price_capital.setVisibility(View.GONE);
            }else  {
                holder.txt_price_capital.setVisibility(View.VISIBLE);
            }
            holder.txt_price_capital.setText(formatRupiah.format(Price_Capital));
            holder.txt_price_sale.setText(formatRupiah.format(Price_Sale));
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

    public class HolderCart extends RecyclerView.ViewHolder {

        @BindView(R.id.txt_name_product)
        TextView txt_name_product;
        @BindView(R.id.txt_price_capital)
        TextView txt_price_capital;
        @BindView(R.id.txt_price_sale)
        TextView txt_price_sale;
        @BindView(R.id.txt_id)
        TextView txt_id;
        @BindView(R.id.txt_reference_id)
        TextView txt_reference_id;
        @BindView(R.id.txt_id_product_category)
        TextView txt_id_product_category;
        @BindView(R.id.txt_number)
        TextView txt_number;
        @BindView(R.id.txt_image)
        TextView txt_image;
        @BindView(R.id.txt_status)
        TextView txt_status;
        @BindView(R.id.image)
        ImageView image;



        public HolderCart(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
