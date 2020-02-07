package com.application.kreditimpian.Model.ModelOngkoskirim;


import com.google.gson.annotations.SerializedName;


public class Jne{

	@SerializedName("cost")
	private Object cost;

	@SerializedName("name")
	private String name;

	public void setCost(Object cost){
		this.cost = cost;
	}

	public Object getCost(){
		return cost;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}
}