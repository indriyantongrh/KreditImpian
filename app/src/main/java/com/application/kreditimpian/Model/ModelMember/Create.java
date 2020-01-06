package com.application.kreditimpian.Model.ModelMember;


import com.google.gson.annotations.SerializedName;


public class Create{

	@SerializedName("user")
	private Object user;

	@SerializedName("timestamp")
	private Timestamp timestamp;

	public void setUser(Object user){
		this.user = user;
	}

	public Object getUser(){
		return user;
	}

	public void setTimestamp(Timestamp timestamp){
		this.timestamp = timestamp;
	}

	public Timestamp getTimestamp(){
		return timestamp;
	}

	@Override
 	public String toString(){
		return 
			"Create{" + 
			"user = '" + user + '\'' + 
			",timestamp = '" + timestamp + '\'' + 
			"}";
		}
}