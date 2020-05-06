package com.application.kreditimpian.Model.ModelImagePromo;

import com.google.gson.annotations.SerializedName;

public class DataItem{

	@SerializedName("images")
	private String images;

	@SerializedName("id")
	private String id;

	public void setImages(String images){
		this.images = images;
	}

	public String getImages(){
		return images;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}
}