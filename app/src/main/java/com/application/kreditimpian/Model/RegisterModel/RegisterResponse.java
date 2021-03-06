package com.application.kreditimpian.Model.RegisterModel;

import java.util.List;
import com.google.gson.annotations.SerializedName;


public class RegisterResponse{

	@SerializedName("result")
	private List<Object> result;

	@SerializedName("reason")
	private String reason;

	@SerializedName("success")
	private boolean success;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private int status;

	public void setResult(List<Object> result){
		this.result = result;
	}

	public List<Object> getResult(){
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

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	public void setStatus(int status){
		this.status = status;
	}

	public int getStatus(){
		return status;
	}
}