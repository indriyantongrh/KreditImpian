package com.application.kreditimpian.Model.ModelUpgradeImpian;

import android.os.Parcel;
import android.os.Parcelable;

public class ModelPinjaman implements Parcelable {

    private String bulanTenor,
            hrgCicilan;
    public String getBulanTenor() {
        return bulanTenor;
    }

    public void setBulanTenor(String bulanTenor) {
        this.bulanTenor = bulanTenor;
    }

    public String getHrgCicilan() {
        return hrgCicilan;
    }

    public void setHrgCicilan(String hrgCicilan) {
        this.hrgCicilan = hrgCicilan;
    }

    public ModelPinjaman() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.bulanTenor);
        dest.writeString(this.hrgCicilan);
    }

    protected ModelPinjaman(Parcel in) {
        this.bulanTenor = in.readString();
        this.hrgCicilan = in.readString();
    }

    public static final Creator<ModelPinjaman> CREATOR = new Creator<ModelPinjaman>() {
        @Override
        public ModelPinjaman createFromParcel(Parcel source) {
            return new ModelPinjaman(source);
        }

        @Override
        public ModelPinjaman[] newArray(int size) {
            return new ModelPinjaman[size];
        }
    };
}
