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
import androidx.recyclerview.widget.RecyclerView;

import com.application.kreditimpian.Constan.ConstansProductMitra;
import com.application.kreditimpian.DetailProductMitra.DetailProductMitra;
import com.application.kreditimpian.Model.ModelMerchant.ResultItem;
import com.application.kreditimpian.R;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by indriyanto Nugroho on 17 Jan 2020.
 */
public class AdapterMerchant extends RecyclerView.Adapter<AdapterMerchant.HolderMerchant> {

    List<ResultItem> resultItemList;
    Context mContext;

    public AdapterMerchant(Context context, List<ResultItem> resultList) {
        this.mContext = context;
        resultItemList = resultList;
    }

    @NonNull
    @Override
    public HolderMerchant onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_merchant, parent, false);
        return new HolderMerchant(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderMerchant holder, int position) {
        final ResultItem resultItem = resultItemList.get(position);

        holder.txt_name_merchant.setText(resultItem.getName());
        holder.txt_id.setText(resultItem.getId());
        Glide.with(mContext)
                .load(resultItem.getImage())
                .placeholder(R.drawable.no_image)
                .error(R.drawable.no_image)
                .into(holder.image_merchant);

        holder.btnclickMerchant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ///Toast.makeText(mContext, "Klik merchant "+resultItem.getName(), Toast.LENGTH_LONG).show();

                Intent detatailMerchant = new Intent(mContext , DetailProductMitra.class);
                detatailMerchant.putExtra(ConstansProductMitra.KEY_ID_MERCHANT, resultItem.getId());
                detatailMerchant.putExtra(ConstansProductMitra.KEY_NAME_MERCHANT, resultItem.getName());
                detatailMerchant.putExtra(ConstansProductMitra.KEY_IMAGE_MERCHANT, resultItem.getImage());
                detatailMerchant.putExtra(ConstansProductMitra.KEY_CITYP_MERCHANT, resultItem.getCity());
                mContext.startActivity(detatailMerchant);
            }
        });
    }

    @Override
    public int getItemCount() {
        return resultItemList.size();
    }

    public class HolderMerchant extends RecyclerView.ViewHolder {


        @BindView(R.id.btnclickMerchant)
        CardView btnclickMerchant;
        @BindView(R.id.txt_name_merchant)
        TextView txt_name_merchant;
        @BindView(R.id.txt_id)
        TextView txt_id;
        @BindView(R.id.txt_id_company_category)
        TextView txt_id_company_category;
        @BindView(R.id.txt_id_geodirectory)
        TextView txt_id_geodirectory;
        @BindView(R.id.txt_image)
        TextView txt_image;
        @BindView(R.id.txt_code)
        TextView txt_code;
        @BindView(R.id.txt_category)
        TextView txt_category;
        @BindView(R.id.txt_owner_name)
        TextView txt_owner_name;
        @BindView(R.id.txt_name)
        TextView txt_name;
        @BindView(R.id.txt_owner_phone)
        TextView txt_owner_phone;
        @BindView(R.id.txt_owner_email)
        TextView txt_owner_email;
        @BindView(R.id.txt_owner_taxpayer)
        TextView txt_owner_taxpayer;


        @BindView(R.id.image_merchant)
        ImageView image_merchant;
        @BindView(R.id.txt_owner_citizen)
        TextView txt_owner_citizen;
        @BindView(R.id.txt_owner_address)
        TextView txt_owner_address;
        @BindView(R.id.txt_company_phone)
        TextView txt_company_phone;
        @BindView(R.id.txt_company_email)
        TextView txt_company_email;
        @BindView(R.id.txt_company_taxpayer)
        TextView txt_company_taxpayer;
        @BindView(R.id.txt_business_permit)
        TextView txt_business_permit;
        @BindView(R.id.txt_company_address)
        TextView txt_company_address;
        @BindView(R.id.txt_postal_code)
        TextView txt_postal_code;
        @BindView(R.id.txt_photo)
        TextView txt_photo;
        @BindView(R.id.txt_category_id)
        TextView txt_category_id;
        @BindView(R.id.txt_code_merchant)
        TextView txt_code_merchant;
        @BindView(R.id.txt_title)
        TextView txt_title;
        @BindView(R.id.txt_description)
        TextView txt_description;
        @BindView(R.id.txt_image_category)
        TextView txt_image_category;
        @BindView(R.id.txt_city)
        TextView txt_city;


        public HolderMerchant(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
