package com.application.kreditimpian.Model.ModelSelectedTenor;


import com.google.gson.annotations.SerializedName;


public class DataCicilanItem{

	@SerializedName("cicilan")
	private String cicilan;

	@SerializedName("bulan")
	private int bulan;

	@SerializedName("downpayment")
	private String downpayment;

	public void setCicilan(String cicilan){
		this.cicilan = cicilan;
	}

	public String getCicilan(){
		return cicilan;
	}

	public void setBulan(int bulan){
		this.bulan = bulan;
	}

	public int getBulan(){
		return bulan;
	}

	public void setDownpayment(String downpayment){
		this.downpayment = downpayment;
	}

	public String getDownpayment(){
		return downpayment;
	}
}