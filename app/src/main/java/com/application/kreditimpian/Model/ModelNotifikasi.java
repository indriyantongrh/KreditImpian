package com.application.kreditimpian.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class ModelNotifikasi implements Parcelable {
    private String idMember,
            idNotifikasi,
            message,
            tgl,
            status;

    public String getIdMember() {
        return idMember;
    }

    public void setIdMember(String idMember) {
        this.idMember = idMember;
    }

    public String getIdNotifikasi() {
        return idNotifikasi;
    }

    public void setIdNotifikasi(String idNotifikasi) {
        this.idNotifikasi = idNotifikasi;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTgl() {
        return tgl;
    }

    public void setTgl(String tgl) {
        this.tgl = tgl;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.idMember);
        dest.writeString(this.idNotifikasi);
        dest.writeString(this.message);
        dest.writeString(this.tgl);
        dest.writeString(this.status);
    }

    public ModelNotifikasi() {
    }

    protected ModelNotifikasi(Parcel in) {
        this.idMember = in.readString();
        this.idNotifikasi = in.readString();
        this.message = in.readString();
        this.tgl = in.readString();
        this.status = in.readString();
    }

    public static final Parcelable.Creator<ModelNotifikasi> CREATOR = new Parcelable.Creator<ModelNotifikasi>() {
        @Override
        public ModelNotifikasi createFromParcel(Parcel source) {
            return new ModelNotifikasi(source);
        }

        @Override
        public ModelNotifikasi[] newArray(int size) {
            return new ModelNotifikasi[size];
        }
    };
}
