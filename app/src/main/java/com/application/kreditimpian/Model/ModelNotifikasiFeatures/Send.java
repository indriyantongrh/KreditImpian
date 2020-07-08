package com.application.kreditimpian.Model.ModelNotifikasiFeatures;

import com.google.gson.annotations.SerializedName;

public class Send{

	@SerializedName("address")
	private String address;

	@SerializedName("receiver")
	private String receiver;

	@SerializedName("city")
	private String city;

	@SerializedName("name_district")
	private String nameDistrict;

	@SerializedName("district")
	private String district;

	@SerializedName("mobile")
	private String mobile;

	@SerializedName("address_label")
	private String addressLabel;

	@SerializedName("name_city")
	private String nameCity;

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

	public void setNameDistrict(String nameDistrict){
		this.nameDistrict = nameDistrict;
	}

	public String getNameDistrict(){
		return nameDistrict;
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

	public void setNameCity(String nameCity){
		this.nameCity = nameCity;
	}

	public String getNameCity(){
		return nameCity;
	}

	public void setPostalCode(String postalCode){
		this.postalCode = postalCode;
	}

	public String getPostalCode(){
		return postalCode;
	}
}