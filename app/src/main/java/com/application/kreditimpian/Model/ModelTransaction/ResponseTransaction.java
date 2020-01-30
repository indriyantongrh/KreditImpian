package com.application.kreditimpian.Model.ModelTransaction;


import com.google.gson.annotations.SerializedName;


public class ResponseTransaction{

	@SerializedName("response_code")
	private int responseCode;

	@SerializedName("data")
	private boolean data;

	@SerializedName("message")
	private String message;

	public void setResponseCode(int responseCode){
		this.responseCode = responseCode;
	}

	public int getResponseCode(){
		return responseCode;
	}

	public void setData(boolean data){
		this.data = data;
	}

	public boolean isData(){
		return data;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}
}