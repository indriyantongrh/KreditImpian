package com.application.kreditimpian.Model.ModelCicilanFitur;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.SerializedName;

public class CompaniesDataItem implements Parcelable {

	@SerializedName("data_cicilan")
	private List<DataCicilanItem> dataCicilan;

	@SerializedName("downPayment")
	private String downPayment;

	@SerializedName("id_company")
	private String idCompany;

	@SerializedName("name")
	private String name;


	public List<DataCicilanItem> getDataCicilan() {
		return dataCicilan;
	}

	public void setDataCicilan(List<DataCicilanItem> dataCicilan) {
		this.dataCicilan = dataCicilan;
	}

	public String getDownPayment() {
		return downPayment;
	}

	public void setDownPayment(String downPayment) {
		this.downPayment = downPayment;
	}

	public String getIdCompany() {
		return idCompany;
	}

	public void setIdCompany(String idCompany) {
		this.idCompany = idCompany;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeTypedList(this.dataCicilan);
		dest.writeString(this.downPayment);
		dest.writeString(this.idCompany);
		dest.writeString(this.name);
	}

	public CompaniesDataItem() {
	}

	protected CompaniesDataItem(Parcel in) {
		this.dataCicilan = in.createTypedArrayList(DataCicilanItem.CREATOR);
		this.downPayment = in.readString();
		this.idCompany = in.readString();
		this.name = in.readString();
	}

	public static final Creator<CompaniesDataItem> CREATOR = new Creator<CompaniesDataItem>() {
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