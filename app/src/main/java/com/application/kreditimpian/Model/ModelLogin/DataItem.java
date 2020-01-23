package com.application.kreditimpian.Model.ModelLogin;


import com.google.gson.annotations.SerializedName;


public class DataItem{

	@SerializedName("user_username")
	private String userUsername;

	@SerializedName("id_member")
	private String idMember;

	@SerializedName("id_user")
	private String idUser;

	@SerializedName("msisdn")
	private String msisdn;

	@SerializedName("email")
	private String email;

	public void setUserUsername(String userUsername){
		this.userUsername = userUsername;
	}

	public String getUserUsername(){
		return userUsername;
	}

	public void setIdMember(String idMember){
		this.idMember = idMember;
	}

	public String getIdMember(){
		return idMember;
	}

	public void setIdUser(String idUser){
		this.idUser = idUser;
	}

	public String getIdUser(){
		return idUser;
	}

	public void setMsisdn(String msisdn){
		this.msisdn = msisdn;
	}

	public String getMsisdn(){
		return msisdn;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}
}