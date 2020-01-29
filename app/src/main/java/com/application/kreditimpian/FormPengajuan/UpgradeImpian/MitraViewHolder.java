package com.application.kreditimpian.FormPengajuan.UpgradeImpian;

import android.view.View;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.application.kreditimpian.R;

public class MitraViewHolder extends RecyclerView.ViewHolder {
    public CheckBox checkBoxMitra;
    public MitraViewHolder(@NonNull View itemView) {
        super(itemView);
        checkBoxMitra = itemView.findViewById(R.id.checkboxMitra);
    }
}
