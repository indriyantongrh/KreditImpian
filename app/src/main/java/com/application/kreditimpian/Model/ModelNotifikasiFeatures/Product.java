package com.application.kreditimpian.Model.ModelNotifikasiFeatures;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Product{

	@SerializedName("image")
	private String image;

	@SerializedName("images")
	private List<Object> images;

	@SerializedName("method")
	private String method;

	@SerializedName("price_sale")
	private String priceSale;

	@SerializedName("name")
	private String name;

	@SerializedName("description")
	private String description;

	@SerializedName("id_product_category")
	private String idProductCategory;

	@SerializedName("id")
	private String id;

	@SerializedName("id_currency")
	private String idCurrency;

	@SerializedName("content")
	private String content;

	@SerializedName("timestamp")
	private Timestamp timestamp;

	public void setImage(String image){
		this.image = image;
	}

	public String getImage(){
		return image;
	}

	public void setImages(List<Object> images){
		this.images = images;
	}

	public List<Object> getImages(){
		return images;
	}

	public void setMethod(String method){
		this.method = method;
	}

	public String getMethod(){
		return method;
	}

	public void setPriceSale(String priceSale){
		this.priceSale = priceSale;
	}

	public String getPriceSale(){
		return priceSale;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
	}

	public void setIdProductCategory(String idProductCategory){
		this.idProductCategory = idProductCategory;
	}

	public String getIdProductCategory(){
		return idProductCategory;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setIdCurrency(String idCurrency){
		this.idCurrency = idCurrency;
	}

	public String getIdCurrency(){
		return idCurrency;
	}

	public void setContent(String content){
		this.content = content;
	}

	public String getContent(){
		return content;
	}

	public void setTimestamp(Timestamp timestamp){
		this.timestamp = timestamp;
	}

	public Timestamp getTimestamp(){
		return timestamp;
	}
}