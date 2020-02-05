package com.application.kreditimpian.FormPengajuan.UpgradeImpian;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.application.kreditimpian.Model.ModelUpgradeImpian.ModelMitraMultiguna;
import com.application.kreditimpian.R;

import java.util.ArrayList;

public class MitraAdapter extends RecyclerView.Adapter<MitraViewHolder> {
    private final Context context;

    private int count = 0;
    private String merkKendaraan = "",
            motormobil;
    private ArrayList<ModelMitraMultiguna> mitraList;
    private boolean onBind = false;

    public MitraAdapter(Context context, ArrayList<ModelMitraMultiguna> mitraList, String motormobil) {
        this.context = context;
        this.mitraList = mitraList;
        this.motormobil = motormobil;
    }

    @NonNull
    @Override
    public MitraViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MitraViewHolder(LayoutInflater.from(context).inflate(R.layout.list_mitra, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MitraViewHolder holder, int position) {
        onBind = true;
        ModelMitraMultiguna modelMitra = mitraList.get(position);
        holder.checkBoxMitra.setText(modelMitra.getNama());
        holder.checkBoxMitra.setId(position);
        holder.checkBoxMitra.setOnClickListener(v -> {
            mitraList.get(position);
            modelMitra.setChecked(!modelMitra.isChecked());

            // bila mitra lebih dari 3. digunakan untuk memilih mitra maksimal 3
            if (modelMitra.isChecked()) {
                count++;
            } else {
                count--;
            }
            notifyDataSetChanged();
        });

        // bila mitra lebih dari 3. digunakan untuk memilih mitra maksimal 3
        if (count == 3) {
            if (!modelMitra.isChecked()) {
                holder.checkBoxMitra.setEnabled(modelMitra.isChecked());
                holder.checkBoxMitra.setAlpha(0.5f);
                holder.checkBoxMitra.setEnabled(false);
            } else {
                holder.checkBoxMitra.setAlpha(1f);
                holder.checkBoxMitra.setEnabled(true);
            }
        } else if (count < 3) {
            holder.checkBoxMitra.setAlpha(1f);
            holder.checkBoxMitra.setEnabled(true);
        }

        if (motormobil.equals("mobil")) {
            if (modelMitra.getNama().equals("baf")) {
                holder.checkBoxMitra.setVisibility(View.GONE);
            }
        } else {
            if (modelMitra.getNama().equalsIgnoreCase("baf")) {
                holder.checkBoxMitra.setEnabled(modelMitra.isDisable());
                if (modelMitra.isDisable()){
                    holder.checkBoxMitra.setAlpha(1f);
                } else{
                    holder.checkBoxMitra.setAlpha(0.5f);
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        return mitraList.size();
    }

    public ArrayList<ModelMitraMultiguna> getMitraList() {
        return mitraList;
    }

}
