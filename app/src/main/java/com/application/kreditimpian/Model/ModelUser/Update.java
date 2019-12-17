package com.application.kreditimpian.Model.ModelUser;


import com.google.gson.annotations.SerializedName;


public class Update{

	@SerializedName("user")
	private Object user;

	@SerializedName("timestamp")
	private Object timestamp;

	public void setUser(Object user){
		this.user = user;
	}

	public Object getUser(){
		return user;
	}

	public void setTimestamp(Object timestamp){
		this.timestamp = timestamp;
	}

	public Object getTimestamp(){
		return timestamp;
	}
}