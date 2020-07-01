package com.application.kreditimpian.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.application.kreditimpian.Model.ModelNotifikasiV2.DataItem;

import java.util.List;

/**
 * Created by indriyanto Nugroho on 30 Jun 2020.
 */
public class AdapterNotifikasiV2 extends RecyclerView.Adapter<AdapterNotifikasiV2.HolderNotifV2>  {

    List<DataItem> dataItemList;
    Context mContext;

    public AdapterNotifikasiV2(Context context, List<DataItem> dataList){
        this.mContext = context;
        dataItemList = dataList;
    }

    @NonNull
    @Override
    public AdapterNotifikasiV2.HolderNotifV2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterNotifikasiV2.HolderNotifV2 holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class HolderNotifV2 extends RecyclerView.ViewHolder {
        public HolderNotifV2(@NonNull View itemView) {
            super(itemView);
        }
    }
}
