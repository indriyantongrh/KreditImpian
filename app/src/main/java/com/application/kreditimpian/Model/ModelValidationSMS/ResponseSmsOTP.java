package com.application.kreditimpian.Model.ModelValidationSMS;


import com.google.gson.annotations.SerializedName;


public class ResponseSmsOTP{

	@SerializedName("number")
	private String number;

	@SerializedName("response_code")
	private int responseCode;

	@SerializedName("fullname")
	private String fullname;

	@SerializedName("message")
	private String message;

	public void setNumber(String number){
		this.number = number;
	}

	public String getNumber(){
		return number;
	}

	public void setResponseCode(int responseCode){
		this.responseCode = responseCode;
	}

	public int getResponseCode(){
		return responseCode;
	}

	public void setFullname(String fullname){
		this.fullname = fullname;
	}

	public String getFullname(){
		return fullname;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}
}