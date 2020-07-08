package com.application.kreditimpian.Model.ModelNotifikasiV2;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseNotfifikasiV2{

	@SerializedName("response_code")
	private Integer responseCode;

	@SerializedName("data")
	private List<DataItem> data;

	@SerializedName("message")
	private String message;

	public void setResponseCode(Integer responseCode){
		this.responseCode = responseCode;
	}

	public Integer getResponseCode(){
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