package com.application.kreditimpian.ResponseMessage;


import com.application.kreditimpian.Model.UserModel.User;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ResponseLoginSucces{

	@SerializedName("result")
	private String result;

	@SerializedName("reason")
	private String reason;

	@SerializedName("success")
	private boolean success;

	@SerializedName("message")
	private Object message;

	@SerializedName("status")
	private int status;

	@SerializedName("id")
	private String id;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Expose
	@SerializedName("user")
	User user;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setResult(String result){
		this.result = result;
	}

	public String getResult(){
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
			"ResponseLoginSucces{" + 
			"result = '" + result + '\'' + 
			",reason = '" + reason + '\'' + 
			",success = '" + success + '\'' + 
			",message = '" + message + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}