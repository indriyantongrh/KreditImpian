package com.application.kreditimpian.Model.ModelSelectedTenor;

import java.util.List;

import com.google.gson.annotations.SerializedName;


public class DataItem{


	@SerializedName("data_cicilan")
	private List<DataCicilanItem> dataCicilan;

	@SerializedName("price")
	private String price;

	public void setDataCicilan(List<DataCicilanItem> dataCicilan){
		this.dataCicilan = dataCicilan;
	}

	public List<DataCicilanItem> getDataCicilan(){
		return dataCicilan;
	}

	public void setPrice(String price){
		this.price = price;
	}

	public String getPrice(){
		return price;
	}
}