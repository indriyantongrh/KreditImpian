package com.application.kreditimpian.Model.ModelProductNew;


import com.google.gson.annotations.SerializedName;


public class Merchant{

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@SerializedName("city")
	private String city;

	@SerializedName("image")
	private String image;

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

	public void setImage(String image){
		this.image = image;
	}

	public String getImage(){
		return image;
	}

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