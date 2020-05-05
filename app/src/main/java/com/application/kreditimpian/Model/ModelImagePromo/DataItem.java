package com.application.kreditimpian.Model.ModelImagePromo;

import com.google.gson.annotations.SerializedName;

public class DataItem{

	@SerializedName("images")
	private String images;

	public void setImages(String images){
		this.images = images;
	}

	public String getImages(){
		return images;
	}
}