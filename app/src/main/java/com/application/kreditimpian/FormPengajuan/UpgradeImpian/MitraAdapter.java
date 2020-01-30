package com.application.kreditimpian.FormPengajuan.UpgradeImpian;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.application.kreditimpian.R;

import java.util.List;

public class MitraAdapter extends RecyclerView.Adapter<MitraViewHolder> {
    private final Context context;
    private OnClickCheckBox onClickCheckBox;

    private String merkKendaraan = "";
    private List<String> mitraList;

    public MitraAdapter(Context context, OnClickCheckBox onClickCheckBox) {
        this.context = context;
        this.onClickCheckBox = onClickCheckBox;
    }

    @NonNull
    @Override
    public MitraViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MitraViewHolder(LayoutInflater.from(context).inflate(R.layout.list_mitra,parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MitraViewHolder holder, int position) {
        holder.checkBoxMitra.setText(mitraList.get(position));
        holder.checkBoxMitra.setOnClickListener(v -> {
            if (holder.checkBoxMitra.isChecked()) {
                onClickCheckBox.CheckedBoxMitra(mitraList.get(position));
            }
        });
        if (!merkKendaraan.isEmpty()) {
            if (merkKendaraan.equals("YAMAHA")) {
                if (mitraList.get(position).equals("Baff")) {
                    holder.checkBoxMitra.setChecked(false);
                    holder.checkBoxMitra.setEnabled(false);
                    holder.checkBoxMitra.setAlpha(0.5f);
                }
            } else{
                holder.checkBoxMitra.setEnabled(false);
                holder.checkBoxMitra.setAlpha(1f);
            }
        }
    }

    @Override
    public int getItemCount() {
        return mitraList.size();
    }

    public void setMitraList(List<String> mitraList) {
        this.mitraList = mitraList;
    }

    public void setMerkKendaraan(String merkKendaraan) {
        this.merkKendaraan = merkKendaraan;
    }

    public interface OnClickCheckBox{
        void CheckedBoxMitra(String name);
    }
}
