package com.application.kreditimpian.Model.ModelMultiguna;

import com.google.gson.annotations.SerializedName;

public class MainAddress{

	@SerializedName("address")
	private String address;

	@SerializedName("receiver")
	private String receiver;

	@SerializedName("phone")
	private String phone;

	@SerializedName("district")
	private String district;

	@SerializedName("main_address")
	private String mainAddress;

	@SerializedName("address_name")
	private String addressName;

	@SerializedName("id_geodirectory")
	private String idGeodirectory;

	@SerializedName("postal_code")
	private String postalCode;

	@SerializedName("name_city")
	private String name_city;

	@SerializedName("name_district")
	private String name_district;

	public String getName_city() {
		return name_city;
	}

	public void setName_city(String name_city) {
		this.name_city = name_city;
	}

	public String getName_district() {
		return name_district;
	}

	public void setName_district(String name_district) {
		this.name_district = name_district;
	}



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

	public void setPhone(String phone){
		this.phone = phone;
	}

	public String getPhone(){
		return phone;
	}

	public void setDistrict(String district){
		this.district = district;
	}

	public String getDistrict(){
		return district;
	}

	public void setMainAddress(String mainAddress){
		this.mainAddress = mainAddress;
	}

	public String getMainAddress(){
		return mainAddress;
	}

	public void setAddressName(String addressName){
		this.addressName = addressName;
	}

	public String getAddressName(){
		return addressName;
	}

	public void setIdGeodirectory(String idGeodirectory){
		this.idGeodirectory = idGeodirectory;
	}

	public String getIdGeodirectory(){
		return idGeodirectory;
	}

	public void setPostalCode(String postalCode){
		this.postalCode = postalCode;
	}

	public String getPostalCode(){
		return postalCode;
	}
}