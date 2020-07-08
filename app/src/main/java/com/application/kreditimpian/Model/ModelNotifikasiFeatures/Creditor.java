package com.application.kreditimpian.Model.ModelNotifikasiFeatures;

import com.google.gson.annotations.SerializedName;

public class Creditor{

	@SerializedName("code")
	private String code;

	@SerializedName("name")
	private String name;

	@SerializedName("id")
	private String id;

	@SerializedName("id_company_category")
	private String idCompanyCategory;

	@SerializedName("id_geodirectory")
	private String idGeodirectory;

	@SerializedName("0")
	private JsonMember0 jsonMember0;

	public void setCode(String code){
		this.code = code;
	}

	public String getCode(){
		return code;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setIdCompanyCategory(String idCompanyCategory){
		this.idCompanyCategory = idCompanyCategory;
	}

	public String getIdCompanyCategory(){
		return idCompanyCategory;
	}

	public void setIdGeodirectory(String idGeodirectory){
		this.idGeodirectory = idGeodirectory;
	}

	public String getIdGeodirectory(){
		return idGeodirectory;
	}

	public void setJsonMember0(JsonMember0 jsonMember0){
		this.jsonMember0 = jsonMember0;
	}

	public JsonMember0 getJsonMember0(){
		return jsonMember0;
	}
}