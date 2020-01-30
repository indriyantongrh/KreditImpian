package com.application.kreditimpian.FormPengajuan.UpgradeImpian;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.application.kreditimpian.R;

import java.util.ArrayList;
import java.util.List;

public class MitraAdapter extends RecyclerView.Adapter<MitraViewHolder> {
    private final Context context;
    private List<String> mitraList;

    public MitraAdapter(Context context, List<String> mitraList) {
        this.context = context;
        this.mitraList = mitraList;
    }

    @NonNull
    @Override
    public MitraViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MitraViewHolder(LayoutInflater.from(context).inflate(R.layout.list_mitra,parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MitraViewHolder holder, int position) {
        holder.checkBoxMitra.setText(mitraList.get(position));
    }

    @Override
    public int getItemCount() {
        return mitraList.size();
    }
}
