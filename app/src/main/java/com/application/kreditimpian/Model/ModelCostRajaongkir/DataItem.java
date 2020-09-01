package com.application.kreditimpian.Model.ModelCostRajaongkir;

import com.google.gson.annotations.SerializedName;

public class DataItem{

	@SerializedName("cost")
	private Integer cost;

	@SerializedName("etd")
	private String etd;

	@SerializedName("company_name")
	private String companyName;

	@SerializedName("name")
	private String name;

	@SerializedName("service")
	private String service;

	public void setCost(Integer cost){
		this.cost = cost;
	}

	public Integer getCost(){
		return cost;
	}

	public void setEtd(String etd){
		this.etd = etd;
	}

	public String getEtd(){
		return etd;
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

	public void setService(String service){
		this.service = service;
	}

	public String getService(){
		return service;
	}
}