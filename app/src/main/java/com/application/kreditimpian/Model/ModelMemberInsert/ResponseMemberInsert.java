package com.application.kreditimpian.Model.ModelMemberInsert;


import com.google.gson.annotations.SerializedName;


public class ResponseMemberInsert{

	@SerializedName("response_code")
	private int responseCode;

	@SerializedName("message")
	private String message;

	public void setResponseCode(int responseCode){
		this.responseCode = responseCode;
	}

	public int getResponseCode(){
		return responseCode;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}
}