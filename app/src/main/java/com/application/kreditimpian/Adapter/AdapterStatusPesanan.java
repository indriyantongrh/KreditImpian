package com.application.kreditimpian.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.application.kreditimpian.Model.ModelTransaksi.ResultItem;
import com.application.kreditimpian.R;

import java.util.List;

/**
 * Created by indriyanto Nugroho on 9 Jan 2020.
 */
public class AdapterStatusPesanan extends RecyclerView.Adapter<AdapterStatusPesanan.StatusPesananHolder>  {

    List<ResultItem> resultItemList;
    Context mContext;

    public AdapterStatusPesanan(Context context, List<ResultItem> resultList){
        this.mContext  = context;
        resultItemList = resultList;

    }

    @NonNull
    @Override
    public StatusPesananHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_status_pesanan, parent,false);
        return new StatusPesananHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StatusPesananHolder holder, int position) {
        final ResultItem resultItem = resultItemList.get(position);




    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class StatusPesananHolder extends RecyclerView.ViewHolder {
        public StatusPesananHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
