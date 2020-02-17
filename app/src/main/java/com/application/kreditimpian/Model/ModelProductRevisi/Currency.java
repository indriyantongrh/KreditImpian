package com.application.kreditimpian.Model.ModelProductRevisi;


import com.google.gson.annotations.SerializedName;


public class Currency{

	@SerializedName("iso")
	private String iso;

	@SerializedName("name")
	private String name;

	@SerializedName("id")
	private String id;

	public void setIso(String iso){
		this.iso = iso;
	}

	public String getIso(){
		return iso;
	}

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
}