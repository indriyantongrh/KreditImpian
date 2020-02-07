package com.application.kreditimpian.Model.ModelOngkoskirim;


import com.google.gson.annotations.SerializedName;


public class Tiki{

	@SerializedName("cost")
	private int cost;

	@SerializedName("name")
	private String name;

	public void setCost(int cost){
		this.cost = cost;
	}

	public int getCost(){
		return cost;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}
}