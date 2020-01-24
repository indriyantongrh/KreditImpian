package com.application.kreditimpian.Model.ModelTransaksi;


import com.google.gson.annotations.SerializedName;


public class Send{

	@SerializedName("receive")
	private String receive;

	@SerializedName("address")
	private String address;

	@SerializedName("city")
	private String city;

	@SerializedName("district")
	private String district;

	@SerializedName("mobile")
	private String mobile;

	@SerializedName("address_label")
	private String addressLabel;

	public void setReceive(String receive){
		this.receive = receive;
	}

	public String getReceive(){
		return receive;
	}

	public void setAddress(String address){
		this.address = address;
	}

	public String getAddress(){
		return address;
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
}