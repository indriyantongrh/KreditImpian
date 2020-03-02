package com.application.kreditimpian.Model.ModelSubDistrictRajaOngkir;


import com.google.gson.annotations.SerializedName;


public class Query{

	@SerializedName("city")
	private String city;

	public void setCity(String city){
		this.city = city;
	}

	public String getCity(){
		return city;
	}
}