package com.application.kreditimpian.FormPengajuan.UpgradeImpian;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.application.kreditimpian.Model.ModelMitra;
import com.application.kreditimpian.R;

import java.util.ArrayList;

public class MitraAdapter extends RecyclerView.Adapter<MitraViewHolder> {
    private final Context context;

    private int count = 0;
    private String merkKendaraan = "",
            motormobil;
    private ArrayList<ModelMitra> mitraList;

    public MitraAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public MitraViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MitraViewHolder(LayoutInflater.from(context).inflate(R.layout.list_mitra, parent, false));
    }

    @SuppressWarnings("EqualsBetweenInconvertibleTypes")
    @Override
    public void onBindViewHolder(@NonNull MitraViewHolder holder, int position) {
        ModelMitra modelMitra = mitraList.get(position);
        holder.checkBoxMitra.setText(modelMitra.getName());
        holder.checkBoxMitra.setOnClickListener(v -> {
            setCheckbox(position);
            notifyDataSetChanged();
        });


        if (count == 3) {
            if (!modelMitra.isChecked()) {
                holder.checkBoxMitra.setEnabled(modelMitra.isChecked());
                holder.checkBoxMitra.setAlpha(0.5f);
            } else {
                holder.checkBoxMitra.setEnabled(true);
            }
        } else if (count < 3) {
            holder.checkBoxMitra.setEnabled(true);
        }

        if (motormobil.equals("mobil")) {
            if (modelMitra.getName().equals("baf")) {
                holder.checkBoxMitra.setVisibility(View.GONE);
            } else{
                holder.checkBoxMitra.setChecked(true);
                setCheckbox(position);
            }
        } else {
            if (!merkKendaraan.isEmpty()) {
                if (!merkKendaraan.equals("YAMAHA")) {
                    if (modelMitra.getName().equals("baf")) {
                        holder.checkBoxMitra.setChecked(false);
                        holder.checkBoxMitra.setEnabled(false);
                        holder.checkBoxMitra.setAlpha(0.5f);
                    }
                } else {
                    holder.checkBoxMitra.setEnabled(true);
                    holder.checkBoxMitra.setAlpha(1f);
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        return mitraList.size();
    }

    public void setMitraList(ArrayList<ModelMitra> mitraList, String motormobil) {
        this.mitraList = mitraList;
        this.motormobil = motormobil;
    }

    public void setMerkKendaraan(String merkKendaraan) {
        this.merkKendaraan = merkKendaraan;
    }

    public ArrayList<ModelMitra> getMitraList() {
        return mitraList;
    }

    private void setCheckbox(int position) {
        ModelMitra modelMitra = mitraList.get(position);
        modelMitra.setChecked(!modelMitra.isChecked());
        Log.v("jajal", modelMitra.getName());
        if (modelMitra.isChecked()) {
            count++;
        } else {
            count--;
        }
    }
}
