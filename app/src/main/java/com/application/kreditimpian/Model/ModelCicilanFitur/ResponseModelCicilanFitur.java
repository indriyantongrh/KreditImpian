package com.application.kreditimpian.Model.ModelCicilanFitur;

import com.google.gson.annotations.SerializedName;

public class ResponseModelCicilanFitur{

	@SerializedName("response_code")
	private int responseCode;

	@SerializedName("data")
	private Data data;

	@SerializedName("message")
	private String message;

	public void setResponseCode(int responseCode){
		this.responseCode = responseCode;
	}

	public int getResponseCode(){
		return responseCode;
	}

	public void setData(Data data){
		this.data = data;
	}

	public Data getData(){
		return data;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}
}