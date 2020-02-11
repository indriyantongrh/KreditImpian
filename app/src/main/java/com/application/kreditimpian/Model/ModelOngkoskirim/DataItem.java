package com.application.kreditimpian.Model.ModelOngkoskirim;


import com.google.gson.annotations.SerializedName;


public class DataItem{

	@SerializedName("cost")
	private int cost;

	@SerializedName("company_name")
	private String companyName;

	@SerializedName("name")
	private String name;

	public void setCost(int cost){
		this.cost = cost;
	}

	public int getCost(){
		return cost;
	}

	public void setCompanyName(String companyName){
		this.companyName = companyName;
	}

	public String getCompanyName(){
		return companyName;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}
}