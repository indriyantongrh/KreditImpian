package com.application.kreditimpian.Model.ModelPengajuanCatalog;


import com.google.gson.annotations.SerializedName;


public class ResponsePengajuanCatalog{

	@SerializedName("response_code")
	private int responseCode;

	@SerializedName("data")
	private Object data;



	@SerializedName("message")
	private String message;

	public void setResponseCode(int responseCode){
		this.responseCode = responseCode;
	}

	public int getResponseCode(){
		return responseCode;
	}

	public void setData(Object data){
		this.data = data;
	}

	public Object getData(){
		return data;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}
}