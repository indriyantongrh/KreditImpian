package com.application.kreditimpian.Model.ModelMerchant;


import com.google.gson.annotations.SerializedName;


public class Category{

	@SerializedName("image")
	private Object image;

	@SerializedName("code")
	private String code;

	@SerializedName("description")
	private Object description;

	@SerializedName("id")
	private String id;

	@SerializedName("title")
	private String title;

	public void setImage(Object image){
		this.image = image;
	}

	public Object getImage(){
		return image;
	}

	public void setCode(String code){
		this.code = code;
	}

	public String getCode(){
		return code;
	}

	public void setDescription(Object description){
		this.description = description;
	}

	public Object getDescription(){
		return description;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}
}