package com.application.kreditimpian.FormPengajuan.UpgradeImpian;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.application.kreditimpian.Model.ModelUpgradeImpian.ModelMitraPinjaman;
import com.application.kreditimpian.Model.ModelUpgradeImpian.ModelPinjaman;
import com.application.kreditimpian.Model.ModelUpgradeImpian.ModelUpgradeImpian;
import com.application.kreditimpian.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class PilihLeasingAdapter extends RecyclerView.Adapter<PilihLeasingViewHolder> {
    private final Context context;
    private ModelUpgradeImpian modelUpgradeImpian;
    private OnClickPilihLeasing onClickPilihLeasing;

    private String tenor ="", code,
    cicilan;
    private List<ModelMitraPinjaman> modelUpgradeImpians;

    public static final Locale localeID = new Locale("in", "ID");

    public PilihLeasingAdapter(Context context,String code, ModelUpgradeImpian modelUpgradeImpian, OnClickPilihLeasing onClickPilihLeasing) {
        this.context = context;
        this.code = code;
        this.modelUpgradeImpian = modelUpgradeImpian;
        this.modelUpgradeImpians = modelUpgradeImpian.getModelMitraArrayList();
        this.onClickPilihLeasing = onClickPilihLeasing;
    }

    @NonNull
    @Override
    public PilihLeasingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PilihLeasingViewHolder(LayoutInflater.from(context).inflate(R.layout.list_pilih_leasing, parent, false));
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onBindViewHolder(@NonNull PilihLeasingViewHolder holder, int position) {
        ModelMitraPinjaman modelMitra = modelUpgradeImpians.get(position);
        String idKreditor = modelMitra.getId();
        String idTransaksi = modelUpgradeImpian.getIdTransaksi();
        holder.lblKendaraan.setText(context.getResources().getString(R.string.kendaraan, modelUpgradeImpian.getKendaraan()));
        holder.lblJmlhPinjaman.setText(context.getResources().getString(R.string.jmlhpinjvalue, String.format(localeID, "%,d", Long.parseLong(String.valueOf(modelUpgradeImpian.getJmlhpinjaman())))));
        holder.lblHrgKendaraan.setText(context.getResources().getString(R.string.hrgkendvalue, String.format(localeID, "%,d", Long.parseLong(String.valueOf(modelUpgradeImpian.getHrgkendaraan())))));
        holder.lblMerkKendaraan.setText(context.getResources().getString(R.string.merkkendvalue, modelUpgradeImpian.getMerkkendaraan()));
        holder.lblTipeKendaraan.setText(context.getResources().getString(R.string.tipekendvalue, modelUpgradeImpian.getTipekendaraan()));
        holder.lblThnKendaraan.setText(context.getResources().getString(R.string.thnkendvalue, modelUpgradeImpian.getTahun()));
        if (code.equals("mobil")){
            holder.lblAsuransi.setText(context.getResources().getString(R.string.asuransivalue, modelUpgradeImpian.getAsuransi()));
        } else{
            holder.lblAsuransi.setVisibility(View.GONE);
        }
        Glide.with(context)
                .load("https://kreditimpian.id/images/users/" + modelMitra.getUrl())
                .into(holder.imgMitra);

        ArrayList<String> stringArrayList = new ArrayList<>();
        for (int i = 0; i < modelMitra.getModelPinjamanList().size(); i++) {
            ModelPinjaman modelPinjaman = modelMitra.getModelPinjamanList().get(i);
            stringArrayList.add(modelPinjaman.getBulanTenor() + " bulan X Rp. " + String.format(localeID, "%,d", Long.parseLong(String.valueOf(modelPinjaman.getHrgCicilan()))));
        }
        holder.spinCicilan.setItem(stringArrayList);
        holder.spinCicilan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tenor = modelMitra.getModelPinjamanList().get(position).getBulanTenor();
                cicilan = modelMitra.getModelPinjamanList().get(position).getHrgCicilan();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        holder.btnPilihLeasing.setOnClickListener(v -> {
            if (tenor.isEmpty()){
                Toast.makeText(context,"Pilih jangka waktu cicilan", Toast.LENGTH_LONG).show();
            } else {
                onClickPilihLeasing.onClickPilihLeasing(idTransaksi, tenor, idKreditor, cicilan);
            }
        });
    }

    @Override
    public int getItemCount() {
        return modelUpgradeImpians.size();
    }

    public interface OnClickPilihLeasing{
        void onClickPilihLeasing(String idTransaksi, String Tenor, String idKreditor, String cicilan);
    }
}
