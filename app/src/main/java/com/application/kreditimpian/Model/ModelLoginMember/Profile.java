package com.application.kreditimpian.Model.ModelLoginMember;


import com.google.gson.annotations.SerializedName;


public class Profile{

	@SerializedName("number")
	private String number;

	@SerializedName("id")
	private String id;

	@SerializedName("fullname")
	private String fullname;

	@SerializedName("id_geodirectory")
	private Object idGeodirectory;

	public void setNumber(String number){
		this.number = number;
	}

	public String getNumber(){
		return number;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setFullname(String fullname){
		this.fullname = fullname;
	}

	public String getFullname(){
		return fullname;
	}

	public void setIdGeodirectory(Object idGeodirectory){
		this.idGeodirectory = idGeodirectory;
	}

	public Object getIdGeodirectory(){
		return idGeodirectory;
	}

	@Override
 	public String toString(){
		return 
			"Profile{" + 
			"number = '" + number + '\'' + 
			",id = '" + id + '\'' + 
			",fullname = '" + fullname + '\'' + 
			",id_geodirectory = '" + idGeodirectory + '\'' + 
			"}";
		}
}