package com.application.kreditimpian.Model.ModelListAlamat;


import com.google.gson.annotations.SerializedName;


public class DataItem{

	@SerializedName("address")
	private String address;

	@SerializedName("receiver")
	private String receiver;

	@SerializedName("phone")
	private String phone;

	@SerializedName("district")
	private String district;

	@SerializedName("id_member")
	private String idMember;

	@SerializedName("main_address")
	private String mainAddress;

	@SerializedName("id")
	private String id;

	@SerializedName("address_name")
	private String addressName;

	@SerializedName("id_geodirectory")
	private String idGeodirectory;

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

	public void setIdMember(String idMember){
		this.idMember = idMember;
	}

	public String getIdMember(){
		return idMember;
	}

	public void setMainAddress(String mainAddress){
		this.mainAddress = mainAddress;
	}

	public String getMainAddress(){
		return mainAddress;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
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