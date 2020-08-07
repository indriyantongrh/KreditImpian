package com.application.kreditimpian.Model.ModelNotifikasiFeatures;

import com.google.gson.annotations.SerializedName;

public class JsonMember0{

	private static JsonMember0 JsonMember0;
	public static JsonMember0 getInstance(){
		if (JsonMember0 == null){
			JsonMember0 = new JsonMember0();
		}
		return JsonMember0;
	}

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
}