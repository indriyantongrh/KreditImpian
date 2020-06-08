package com.application.kreditimpian.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.application.kreditimpian.Model.ModelMitraKami.ResultItem;
import com.application.kreditimpian.R;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by indriyanto Nugroho on 8 Jun 2020.
 */
public class AdapterMitraKami extends RecyclerView.Adapter<AdapterMitraKami.HolderMitraKami> {

     List<ResultItem> resultItemList;
    Context mContext;

    public AdapterMitraKami(Context context, List<ResultItem> resultList){
        this.mContext = context;
        resultItemList = resultList;
    }

    @NonNull
    @Override
    public AdapterMitraKami.HolderMitraKami onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_merchant, parent, false);
        return new HolderMitraKami(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterMitraKami.HolderMitraKami holder, int position) {

        final ResultItem resultItem = resultItemList.get(position);

        holder.txt_name_merchant.setText(resultItem.getName());
        holder.txt_id.setText(resultItem.getId());
        Glide.with(mContext)
                .load(resultItem.getImage())
                .placeholder(R.drawable.no_image)
                .error(R.drawable.no_image)
                .into(holder.image_merchant);


    }

    @Override
    public int getItemCount() {
        return resultItemList.size();
    }

    public class HolderMitraKami extends RecyclerView.ViewHolder {
        @BindView(R.id.txt_name_merchant)
        TextView txt_name_merchant;
        @BindView(R.id.txt_id)
        TextView txt_id;
        @BindView(R.id.image_merchant)
        ImageView image_merchant;

        public HolderMitraKami(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
