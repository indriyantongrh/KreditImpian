package com.application.kreditimpian.Model.ModelProductBaru;

import java.util.List;

import com.google.gson.annotations.SerializedName;


public class ResponseProductBaru{

	@SerializedName("result")
	private List<ResultItem> result;

	@SerializedName("reason")
	private String reason;

	@SerializedName("success")
	private boolean success;

	@SerializedName("message")
	private Object message;

	@SerializedName("status")
	private int status;

	public void setResult(List<ResultItem> result){
		this.result = result;
	}

	public List<ResultItem> getResult(){
		return result;
	}

	public void setReason(String reason){
		this.reason = reason;
	}

	public String getReason(){
		return reason;
	}

	public void setSuccess(boolean success){
		this.success = success;
	}

	public boolean isSuccess(){
		return success;
	}

	public void setMessage(Object message){
		this.message = message;
	}

	public Object getMessage(){
		return message;
	}

	public void setStatus(int status){
		this.status = status;
	}

	public int getStatus(){
		return status;
	}
}