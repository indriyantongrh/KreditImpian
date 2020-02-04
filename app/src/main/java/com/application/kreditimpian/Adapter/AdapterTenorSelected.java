package com.application.kreditimpian.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.application.kreditimpian.Model.ModelSelectedTenor.DataItem;
import com.application.kreditimpian.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by indriyanto Nugroho on 3 Feb 2020.
 */
public class AdapterTenorSelected  extends RecyclerView.Adapter<AdapterTenorSelected.HolderTenorSelected> {

    List<DataItem> dataItemList;
    Context mContext;

    public AdapterTenorSelected(Context context, List<DataItem>  datalist){
        this.mContext = context;
        dataItemList = datalist;
    }


    @NonNull
    @Override
    public AdapterTenorSelected.HolderTenorSelected onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_tenor_selected, parent,false);
        return  new HolderTenorSelected(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterTenorSelected.HolderTenorSelected holder, int position) {
        final DataItem dataItem = dataItemList.get(position);

        holder.txt_name_mitra.setText(dataItem.getPrice());



    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class HolderTenorSelected extends RecyclerView.ViewHolder {

        @BindView(R.id.txt_name_mitra)
        TextView txt_name_mitra;

        public HolderTenorSelected(@NonNull View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
