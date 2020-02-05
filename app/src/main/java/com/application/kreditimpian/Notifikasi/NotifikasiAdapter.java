package com.application.kreditimpian.Notifikasi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.application.kreditimpian.Model.ModelNotifikasi;
import com.application.kreditimpian.R;

import java.util.ArrayList;

public class NotifikasiAdapter extends RecyclerView.Adapter<NotifikasiViewHolder> {
    private Context context;
    private ArrayList<ModelNotifikasi> listNotif;

    public NotifikasiAdapter(Context context, ArrayList<ModelNotifikasi> listNotif) {
        this.context = context;
        this.listNotif = listNotif;
    }

    @NonNull
    @Override
    public NotifikasiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NotifikasiViewHolder(LayoutInflater.from(context).inflate(R.layout.list_notifikasi,parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NotifikasiViewHolder holder, int position) {
        ModelNotifikasi modelNotifikasi = listNotif.get(position);
        holder.txtMessageNotif.setText(modelNotifikasi.getMessage());
        holder.txtTglNotif.setText(modelNotifikasi.getTgl());
    }

    @Override
    public int getItemCount() {
        return listNotif.size();
    }
}
