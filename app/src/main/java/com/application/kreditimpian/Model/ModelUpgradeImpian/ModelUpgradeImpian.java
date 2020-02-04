package com.application.kreditimpian.Model.ModelUpgradeImpian;

import android.os.Parcel;
import android.os.Parcelable;

import com.application.kreditimpian.Model.ModelMitra;

import java.util.ArrayList;
import java.util.List;

public class ModelUpgradeImpian implements Parcelable {
    private String jmlhpinjaman,
    hrgkendaraan,
    lokasi,
    idmember,
    asuransi,
    image,
    merkkendaraan,
    tipekendaraan,
    tahun,
    mitra,
    idTransaksi,
    kendaraan,
    code,
    result;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    private List<ModelMitraPinjaman> modelMitraArrayList;

    public List<ModelMitraPinjaman> getModelMitraArrayList() {
        return modelMitraArrayList;
    }

    public void setModelMitraArrayList(List<ModelMitraPinjaman> modelMitraArrayList) {
        this.modelMitraArrayList = modelMitraArrayList;
    }

    public String getKendaraan() {
        return kendaraan;
    }

    public void setKendaraan(String kendaraan) {
        this.kendaraan = kendaraan;
    }

    public String getIdTransaksi() {
        return idTransaksi;
    }

    public void setIdTransaksi(String idTransaksi) {
        this.idTransaksi = idTransaksi;
    }

    public String getMitra() {
        return mitra;
    }

    public void setMitra(String mitra) {
        this.mitra = mitra;
    }

    public String getMerkkendaraan() {
        return merkkendaraan;
    }

    public void setMerkkendaraan(String merkkendaraan) {
        this.merkkendaraan = merkkendaraan;
    }

    public String getTipekendaraan() {
        return tipekendaraan;
    }

    public void setTipekendaraan(String tipekendaraan) {
        this.tipekendaraan = tipekendaraan;
    }

    public String getTahun() {
        return tahun;
    }

    public void setTahun(String tahun) {
        this.tahun = tahun;
    }

    public String getJmlhpinjaman() {
        return jmlhpinjaman;
    }

    public void setJmlhpinjaman(String jmlhpinjaman) {
        this.jmlhpinjaman = jmlhpinjaman;
    }

    public String getHrgkendaraan() {
        return hrgkendaraan;
    }

    public void setHrgkendaraan(String hrgkendaraan) {
        this.hrgkendaraan = hrgkendaraan;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getIdmember() {
        return idmember;
    }

    public void setIdmember(String idmember) {
        this.idmember = idmember;
    }

    public String getAsuransi() {
        return asuransi;
    }

    public void setAsuransi(String asuransi) {
        this.asuransi = asuransi;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public ModelUpgradeImpian() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.jmlhpinjaman);
        dest.writeString(this.hrgkendaraan);
        dest.writeString(this.lokasi);
        dest.writeString(this.idmember);
        dest.writeString(this.asuransi);
        dest.writeString(this.image);
        dest.writeString(this.merkkendaraan);
        dest.writeString(this.tipekendaraan);
        dest.writeString(this.tahun);
        dest.writeString(this.mitra);
        dest.writeString(this.idTransaksi);
        dest.writeString(this.kendaraan);
        dest.writeString(this.code);
        dest.writeString(this.result);
        dest.writeTypedList(this.modelMitraArrayList);
    }

    protected ModelUpgradeImpian(Parcel in) {
        this.jmlhpinjaman = in.readString();
        this.hrgkendaraan = in.readString();
        this.lokasi = in.readString();
        this.idmember = in.readString();
        this.asuransi = in.readString();
        this.image = in.readString();
        this.merkkendaraan = in.readString();
        this.tipekendaraan = in.readString();
        this.tahun = in.readString();
        this.mitra = in.readString();
        this.idTransaksi = in.readString();
        this.kendaraan = in.readString();
        this.code = in.readString();
        this.result = in.readString();
        this.modelMitraArrayList = in.createTypedArrayList(ModelMitraPinjaman.CREATOR);
    }

    public static final Creator<ModelUpgradeImpian> CREATOR = new Creator<ModelUpgradeImpian>() {
        @Override
        public ModelUpgradeImpian createFromParcel(Parcel source) {
            return new ModelUpgradeImpian(source);
        }

        @Override
        public ModelUpgradeImpian[] newArray(int size) {
            return new ModelUpgradeImpian[size];
        }
    };
}
