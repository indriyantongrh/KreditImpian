package com.application.kreditimpian.Notifikasi;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.application.kreditimpian.R;

public class NotifikasiViewHolder extends RecyclerView.ViewHolder {
    public TextView txtMessageNotif,
            txtTglNotif;
    public ConstraintLayout layoutKlik;
    public NotifikasiViewHolder(@NonNull View itemView) {
        super(itemView);
        txtTglNotif = itemView.findViewById(R.id.txtTglNotif);
        txtMessageNotif = itemView.findViewById(R.id.txtMessageNotif);
        layoutKlik = itemView.findViewById(R.id.layoutKlik);
    }
}
