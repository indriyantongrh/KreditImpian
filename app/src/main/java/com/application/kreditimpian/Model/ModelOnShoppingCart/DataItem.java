package com.application.kreditimpian.Model.ModelOnShoppingCart;


import com.google.gson.annotations.SerializedName;


public class DataItem{

	@SerializedName("number")
	private String number;

	@SerializedName("filename")
	private String filename;

	@SerializedName("reference_id")
	private String referenceId;

	@SerializedName("price_sale")
	private String priceSale;

	@SerializedName("name")
	private String name;

	@SerializedName("discount")
	private String discount;

	@SerializedName("id_product_category")
	private String idProductCategory;

	@SerializedName("id")
	private String id;

	@SerializedName("price_capital")
	private String priceCapital;

	@SerializedName("status")
	private String status;

	public void setNumber(String number){
		this.number = number;
	}

	public String getNumber(){
		return number;
	}

	public void setFilename(String filename){
		this.filename = filename;
	}

	public String getFilename(){
		return filename;
	}

	public void setReferenceId(String referenceId){
		this.referenceId = referenceId;
	}

	public String getReferenceId(){
		return referenceId;
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

	public void setDiscount(String discount){
		this.discount = discount;
	}

	public String getDiscount(){
		return discount;
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

	public void setPriceCapital(String priceCapital){
		this.priceCapital = priceCapital;
	}

	public String getPriceCapital(){
		return priceCapital;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}
}