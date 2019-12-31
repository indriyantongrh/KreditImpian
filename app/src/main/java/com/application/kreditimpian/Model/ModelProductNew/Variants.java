package com.application.kreditimpian.Model.ModelProductNew;


import com.google.gson.annotations.SerializedName;


public class Variants{

	@SerializedName("0")
	private JsonMember0 jsonMember0;

	@SerializedName("1")
	private JsonMember1 jsonMember1;

	public void setJsonMember0(JsonMember0 jsonMember0){
		this.jsonMember0 = jsonMember0;
	}

	public JsonMember0 getJsonMember0(){
		return jsonMember0;
	}

	public void setJsonMember1(JsonMember1 jsonMember1){
		this.jsonMember1 = jsonMember1;
	}

	public JsonMember1 getJsonMember1(){
		return jsonMember1;
	}
}