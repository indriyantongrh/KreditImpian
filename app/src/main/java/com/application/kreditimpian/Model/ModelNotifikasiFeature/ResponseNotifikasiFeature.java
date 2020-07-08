package com.application.kreditimpian.Model.ModelNotifikasiFeature;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseNotifikasiFeature{

	@SerializedName("result")
	private List<ResultItem> result;

	@SerializedName("reason")
	private String reason;

	@SerializedName("success")
	private Boolean success;

	@SerializedName("message")
	private Object message;

	@SerializedName("status")
	private Integer status;

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

	public void setSuccess(Boolean success){
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

	public void setStatus(Integer status){
		this.status = status;
	}

	public Integer getStatus(){
		return status;
	}
}