package com.application.kreditimpian.Model.ModelGeodirectory;


import com.google.gson.annotations.SerializedName;


public class DataItem{

	@SerializedName("name")
	private String name;

	@SerializedName("id")
	private String id;

	@SerializedName("type")
	private String type;

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setType(String type){
		this.type = type;
	}

	public String getType(){
		return type;
	}
}