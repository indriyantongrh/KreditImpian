package com.application.kreditimpian.Model.ModelNotifikasiProducts;

import com.google.gson.annotations.SerializedName;

public class Front{

	@SerializedName("image")
	private String image;

	public void setImage(String image){
		this.image = image;
	}

	public String getImage(){
		return image;
	}
}