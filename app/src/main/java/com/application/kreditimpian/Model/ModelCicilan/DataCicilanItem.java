package com.application.kreditimpian.Model.ModelCicilan;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;


public class DataCicilanItem implements Parcelable {

	@SerializedName("cicilan")
	private String cicilan;

	public String getCicilan() {
		return cicilan;
	}

	public void setCicilan(String cicilan) {
		this.cicilan = cicilan;
	}

	public String getBulan() {
		return bulan;
	}

	public void setBulan(String bulan) {
		this.bulan = bulan;
	}

	@SerializedName("bulan")
	private String bulan;

//	public void setCicilan(int cicilan){
//		this.cicilan = cicilan;
//	}
//
//	public int getCicilan(){
//		return cicilan;
//	}
//
//	public void setBulan(int bulan){
//		this.bulan = bulan;
//	}
//
//	public int getBulan(){
//		return bulan;
//	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.cicilan);
		dest.writeString(this.bulan);
	}

	public DataCicilanItem() {
	}

	protected DataCicilanItem(Parcel in) {
		this.cicilan = in.readString();
		this.bulan = in.readString();
	}

	public static final Parcelable.Creator<DataCicilanItem> CREATOR = new Parcelable.Creator<DataCicilanItem>() {
		@Override
		public DataCicilanItem createFromParcel(Parcel source) {
			return new DataCicilanItem(source);
		}

		@Override
		public DataCicilanItem[] newArray(int size) {
			return new DataCicilanItem[size];
		}
	};
}