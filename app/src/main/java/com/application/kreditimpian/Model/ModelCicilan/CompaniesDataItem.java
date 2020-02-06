package com.application.kreditimpian.Model.ModelCicilan;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class CompaniesDataItem implements Parcelable {

	@SerializedName("data_cicilan")
	private List<DataCicilanItem> dataCicilan;

	public void setDownPayment(String downPayment) {
		this.downPayment = downPayment;
	}

	@SerializedName("downPayment")
	private String downPayment;

	@SerializedName("id_company")
	private String idCompany;

	@SerializedName("name")
	private String name;

	public void setDataCicilan(List<DataCicilanItem> dataCicilan){
		this.dataCicilan = dataCicilan;
	}

	public List<DataCicilanItem> getDataCicilan(){
		return dataCicilan;
	}

//	public void setDownPayment(int downPayment){
//		this.downPayment = downPayment;
//	}
//
//	public int getDownPayment(){
//		return downPayment;
//	}

	public void setIdCompany(String idCompany){
		this.idCompany = idCompany;
	}

	public String getIdCompany(){
		return idCompany;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeList(this.dataCicilan);
		dest.writeString(this.downPayment);
		dest.writeString(this.idCompany);
		dest.writeString(this.name);
	}

	public CompaniesDataItem() {
	}

	protected CompaniesDataItem(Parcel in) {
		this.dataCicilan = new ArrayList<DataCicilanItem>();
		in.readList(this.dataCicilan, DataCicilanItem.class.getClassLoader());
		this.downPayment = in.readString();
		this.idCompany = in.readString();
		this.name = in.readString();
	}

	public static final Parcelable.Creator<CompaniesDataItem> CREATOR = new Parcelable.Creator<CompaniesDataItem>() {
		@Override
		public CompaniesDataItem createFromParcel(Parcel source) {
			return new CompaniesDataItem(source);
		}

		@Override
		public CompaniesDataItem[] newArray(int size) {
			return new CompaniesDataItem[size];
		}
	};
}