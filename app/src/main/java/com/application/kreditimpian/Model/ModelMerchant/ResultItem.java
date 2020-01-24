package com.application.kreditimpian.Model.ModelMerchant;


import com.google.gson.annotations.SerializedName;


public class ResultItem{

	@SerializedName("image")
	private String image;

	@SerializedName("metadata")
	private Metadata metadata;

	@SerializedName("code")
	private String code;

	@SerializedName("city")
	private String city;

	@SerializedName("name")
	private String name;

	@SerializedName("id")
	private String id;

	@SerializedName("id_company_category")
	private String idCompanyCategory;

	@SerializedName("id_geodirectory")
	private String idGeodirectory;

	@SerializedName("category")
	private Category category;

	@SerializedName("contacts")
	private Contacts contacts;

	public void setImage(String image){
		this.image = image;
	}

	public String getImage(){
		return image;
	}

	public void setMetadata(Metadata metadata){
		this.metadata = metadata;
	}

	public Metadata getMetadata(){
		return metadata;
	}

	public void setCode(String code){
		this.code = code;
	}

	public String getCode(){
		return code;
	}

	public void setCity(String city){
		this.city = city;
	}

	public String getCity(){
		return city;
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

	public void setCategory(Category category){
		this.category = category;
	}

	public Category getCategory(){
		return category;
	}

	public void setContacts(Contacts contacts){
		this.contacts = contacts;
	}

	public Contacts getContacts(){
		return contacts;
	}
}