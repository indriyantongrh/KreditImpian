package com.application.kreditimpian.Model;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;


public class Send implements Parcelable {

	@SerializedName("address")
	private String address;

	@SerializedName("receiver")
	private String receiver;

	@SerializedName("city")
	private String city;

	@SerializedName("district")
	private String district;

	@SerializedName("mobile")
	private String mobile;

	@SerializedName("address_label")
	private String addressLabel;

	@SerializedName("postal_code")
	private String postalCode;

	public void setAddress(String address){
		this.address = address;
	}

	public String getAddress(){
		return address;
	}

	public void setReceiver(String receiver){
		this.receiver = receiver;
	}

	public String getReceiver(){
		return receiver;
	}

	public void setCity(String city){
		this.city = city;
	}

	public String getCity(){
		return city;
	}

	public void setDistrict(String district){
		this.district = district;
	}

	public String getDistrict(){
		return district;
	}

	public void setMobile(String mobile){
		this.mobile = mobile;
	}

	public String getMobile(){
		return mobile;
	}

	public void setAddressLabel(String addressLabel){
		this.addressLabel = addressLabel;
	}

	public String getAddressLabel(){
		return addressLabel;
	}

	public void setPostalCode(String postalCode){
		this.postalCode = postalCode;
	}

	public String getPostalCode(){
		return postalCode;
	}



	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.address);
		dest.writeString(this.receiver);
		dest.writeString(this.city);
		dest.writeString(this.district);
		dest.writeString(this.mobile);
		dest.writeString(this.addressLabel);
		dest.writeString(this.postalCode);
	}

	public Send() {
	}

	protected Send(Parcel in) {
		this.address = in.readString();
		this.receiver = in.readString();
		this.city = in.readString();
		this.district = in.readString();
		this.mobile = in.readString();
		this.addressLabel = in.readString();
		this.postalCode = in.readString();
	}

	public static final Parcelable.Creator<Send> CREATOR = new Parcelable.Creator<Send>() {
		@Override
		public Send createFromParcel(Parcel source) {
			return new Send(source);
		}

		@Override
		public Send[] newArray(int size) {
			return new Send[size];
		}
	};
}