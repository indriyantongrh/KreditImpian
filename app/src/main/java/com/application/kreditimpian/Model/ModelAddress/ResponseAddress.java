package com.application.kreditimpian.Model.ModelAddress;


import com.google.gson.annotations.SerializedName;


public class ResponseAddress{

	@SerializedName("response_code")
	private int responseCode;

	@SerializedName("data")
	private int data;

	@SerializedName("message")
	private String message;

	public void setResponseCode(int responseCode){
		this.responseCode = responseCode;
	}

	public int getResponseCode(){
		return responseCode;
	}

	public void setData(int data){
		this.data = data;
	}

	public int getData(){
		return data;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}
}