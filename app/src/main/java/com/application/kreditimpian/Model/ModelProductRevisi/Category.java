package com.application.kreditimpian.Model.ModelProductRevisi;


import com.google.gson.annotations.SerializedName;


public class Category{

	@SerializedName("image")
	private String image;

	@SerializedName("code")
	private String code;

	@SerializedName("check_child")
	private Object checkChild;

	@SerializedName("id_google_product_taxonomy")
	private Object idGoogleProductTaxonomy;

	@SerializedName("name")
	private String name;

	@SerializedName("description")
	private Object description;

	@SerializedName("photo")
	private String photo;

	@SerializedName("id")
	private String id;

	@SerializedName("id_parent")
	private String idParent;

	@SerializedName("slug")
	private String slug;

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

	public void setCheckChild(Object checkChild){
		this.checkChild = checkChild;
	}

	public Object getCheckChild(){
		return checkChild;
	}

	public void setIdGoogleProductTaxonomy(Object idGoogleProductTaxonomy){
		this.idGoogleProductTaxonomy = idGoogleProductTaxonomy;
	}

	public Object getIdGoogleProductTaxonomy(){
		return idGoogleProductTaxonomy;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setDescription(Object description){
		this.description = description;
	}

	public Object getDescription(){
		return description;
	}

	public void setPhoto(String photo){
		this.photo = photo;
	}

	public String getPhoto(){
		return photo;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setIdParent(String idParent){
		this.idParent = idParent;
	}

	public String getIdParent(){
		return idParent;
	}

	public void setSlug(String slug){
		this.slug = slug;
	}

	public String getSlug(){
		return slug;
	}
}