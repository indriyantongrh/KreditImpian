package com.application.kreditimpian.Model.UserModel;


import com.google.gson.annotations.SerializedName;


public class User{

	@SerializedName("verifiedMember")
	private Object verifiedMember;

	@SerializedName("profile")
	private Profile profile;

	@SerializedName("id")
	private String id;

	@SerializedName("msisdn")
	private String msisdn;

	@SerializedName("email")
	private String email;

	@SerializedName("username")
	private String username;

	public void setVerifiedMember(Object verifiedMember){
		this.verifiedMember = verifiedMember;
	}

	public Object getVerifiedMember(){
		return verifiedMember;
	}

	public void setProfile(Profile profile){
		this.profile = profile;
	}

	public Profile getProfile(){
		return profile;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
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

	public void setUsername(String username){
		this.username = username;
	}

	public String getUsername(){
		return username;
	}
}