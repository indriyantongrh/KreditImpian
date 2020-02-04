package com.application.kreditimpian.FormPengajuan.UpgradeImpian;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.application.kreditimpian.R;
import com.chivorn.smartmaterialspinner.SmartMaterialSpinner;

public class PilihLeasingViewHolder extends RecyclerView.ViewHolder {
    public TextView lblKendaraan,
            lblJmlhPinjaman,
            lblHrgKendaraan,
            lblMerkKendaraan,
            lblTipeKendaraan,
            lblThnKendaraan,
            lblAsuransi;
    public SmartMaterialSpinner spinCicilan;
    public Button btnPilihLeasing;
    public ImageView imgMitra;

    public PilihLeasingViewHolder(@NonNull View itemView) {
        super(itemView);
        imgMitra = itemView.findViewById(R.id.imgLeasing);
        lblKendaraan = itemView.findViewById(R.id.lblKendaraan);
        lblJmlhPinjaman = itemView.findViewById(R.id.lblJmlhPinjaman);
        lblHrgKendaraan = itemView.findViewById(R.id.lblHrgKendaraan);
        lblMerkKendaraan = itemView.findViewById(R.id.lblMerkKendaraan);
        lblTipeKendaraan = itemView.findViewById(R.id.lblTipeKendaraan);
        lblThnKendaraan = itemView.findViewById(R.id.lblThnKendaraan);
        lblAsuransi = itemView.findViewById(R.id.lblAsuransi);
        spinCicilan = itemView.findViewById(R.id.spinCicilan);
        btnPilihLeasing = itemView.findViewById(R.id.btnPilihLeasing);
    }
}
