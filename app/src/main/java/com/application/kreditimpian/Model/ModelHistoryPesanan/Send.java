package com.application.kreditimpian.Model.ModelHistoryPesanan;


import com.google.gson.annotations.SerializedName;


public class Send{

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


	public String getPostal_code() {
		return postal_code;
	}

	public void setPostal_code(String postal_code) {
		this.postal_code = postal_code;
	}

	@SerializedName("postal_code")
	private String postal_code;

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
}