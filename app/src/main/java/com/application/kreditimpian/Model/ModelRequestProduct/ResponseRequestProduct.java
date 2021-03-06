package com.application.kreditimpian.Model.ModelRequestProduct;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseRequestProduct{

	@SerializedName("response_code")
	private int responseCode;

	@SerializedName("data")
	private List<DataItem> data;

	@SerializedName("message")
	private String message;

	public void setResponseCode(int responseCode){
		this.responseCode = responseCode;
	}

	public int getResponseCode(){
		return responseCode;
	}

	public void setData(List<DataItem> data){
		this.data = data;
	}

	public List<DataItem> getData(){
		return data;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}
}