package com.application.kreditimpian.Model.ModelRequestProduct;

import com.google.gson.annotations.SerializedName;

public class CompanyCity{

	@SerializedName("id_geodirectory")
	private String idGeodirectory;

	public void setIdGeodirectory(String idGeodirectory){
		this.idGeodirectory = idGeodirectory;
	}

	public String getIdGeodirectory(){
		return idGeodirectory;
	}
}