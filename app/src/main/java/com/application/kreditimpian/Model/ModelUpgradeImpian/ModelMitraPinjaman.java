package com.application.kreditimpian.Model.ModelUpgradeImpian;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class ModelMitraPinjaman implements Parcelable {
    private String id,
    name,
    url;

    private List<ModelPinjaman> modelPinjamanList;

    public List<ModelPinjaman> getModelPinjamanList() {
        return modelPinjamanList;
    }

    public void setModelPinjamanList(List<ModelPinjaman> modelPinjamanList) {
        this.modelPinjamanList = modelPinjamanList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ModelMitraPinjaman() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeString(this.url);
        dest.writeTypedList(this.modelPinjamanList);
    }

    protected ModelMitraPinjaman(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.url = in.readString();
        this.modelPinjamanList = in.createTypedArrayList(ModelPinjaman.CREATOR);
    }

    public static final Creator<ModelMitraPinjaman> CREATOR = new Creator<ModelMitraPinjaman>() {
        @Override
        public ModelMitraPinjaman createFromParcel(Parcel source) {
            return new ModelMitraPinjaman(source);
        }

        @Override
        public ModelMitraPinjaman[] newArray(int size) {
            return new ModelMitraPinjaman[size];
        }
    };
}
