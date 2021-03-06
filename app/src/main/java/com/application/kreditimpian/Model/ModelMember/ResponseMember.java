package com.application.kreditimpian.Model.ModelMember;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class ResponseMember{

	@SerializedName("result")
	private ResultItem[] result;

	@SerializedName("reason")
	private String reason;

	@SerializedName("success")
	private boolean success;

	@SerializedName("message")
	private Object message;

	@SerializedName("status")
	private int status;

	public void setResult(ResultItem[] result){
		this.result = result;
	}

	public ResultItem[] getResult(){
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

	@Override
 	public String toString(){
		return 
			"ResponseMember{" + 
			"result = '" + result + '\'' + 
			",reason = '" + reason + '\'' + 
			",success = '" + success + '\'' + 
			",message = '" + message + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}