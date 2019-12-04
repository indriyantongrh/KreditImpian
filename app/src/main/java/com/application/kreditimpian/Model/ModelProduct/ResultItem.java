package com.application.kreditimpian.Model.ModelProduct;

import java.util.List;

import com.google.gson.annotations.SerializedName;


public class ResultItem{

	@SerializedName("image")
	private String image;

	@SerializedName("downloadable")
	private String downloadable;

	@SerializedName("images")
	private List<ImagesItem> images;

	@SerializedName("metadata")
	private Metadata metadata;

	@SerializedName("visibility")
	private String visibility;

	@SerializedName("description")
	private String description;

	@SerializedName("discount")
	private Object discount;

	@SerializedName("deliverable")
	private String deliverable;

	@SerializedName("id_product_category")
	private String idProductCategory;

	@SerializedName("price_capital")
	private String priceCapital;

	@SerializedName("condition")
	private String condition;

	@SerializedName("price_sale")
	private String priceSale;

	@SerializedName("target_age")
	private String targetAge;

	@SerializedName("name")
	private String name;

	@SerializedName("id")
	private String id;

	@SerializedName("id_currency")
	private String idCurrency;

	@SerializedName("sku")
	private String sku;

	@SerializedName("stock")
	private String stock;

	@SerializedName("slug")
	private String slug;

	@SerializedName("target_gender")
	private String targetGender;

	public void setImage(String image){
		this.image = image;
	}

	public String getImage(){
		return image;
	}

	public void setDownloadable(String downloadable){
		this.downloadable = downloadable;
	}

	public String getDownloadable(){
		return downloadable;
	}

	public void setImages(List<ImagesItem> images){
		this.images = images;
	}

	public List<ImagesItem> getImages(){
		return images;
	}

	public void setMetadata(Metadata metadata){
		this.metadata = metadata;
	}

	public Metadata getMetadata(){
		return metadata;
	}

	public void setVisibility(String visibility){
		this.visibility = visibility;
	}

	public String getVisibility(){
		return visibility;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
	}

	public void setDiscount(Object discount){
		this.discount = discount;
	}

	public Object getDiscount(){
		return discount;
	}

	public void setDeliverable(String deliverable){
		this.deliverable = deliverable;
	}

	public String getDeliverable(){
		return deliverable;
	}

	public void setIdProductCategory(String idProductCategory){
		this.idProductCategory = idProductCategory;
	}

	public String getIdProductCategory(){
		return idProductCategory;
	}

	public void setPriceCapital(String priceCapital){
		this.priceCapital = priceCapital;
	}

	public String getPriceCapital(){
		return priceCapital;
	}

	public void setCondition(String condition){
		this.condition = condition;
	}

	public String getCondition(){
		return condition;
	}

	public void setPriceSale(String priceSale){
		this.priceSale = priceSale;
	}

	public String getPriceSale(){
		return priceSale;
	}

	public void setTargetAge(String targetAge){
		this.targetAge = targetAge;
	}

	public String getTargetAge(){
		return targetAge;
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

	public void setIdCurrency(String idCurrency){
		this.idCurrency = idCurrency;
	}

	public String getIdCurrency(){
		return idCurrency;
	}

	public void setSku(String sku){
		this.sku = sku;
	}

	public String getSku(){
		return sku;
	}

	public void setStock(String stock){
		this.stock = stock;
	}

	public String getStock(){
		return stock;
	}

	public void setSlug(String slug){
		this.slug = slug;
	}

	public String getSlug(){
		return slug;
	}

	public void setTargetGender(String targetGender){
		this.targetGender = targetGender;
	}

	public String getTargetGender(){
		return targetGender;
	}

	@Override
 	public String toString(){
		return 
			"ResultItem{" + 
			"image = '" + image + '\'' + 
			",downloadable = '" + downloadable + '\'' + 
			",images = '" + images + '\'' + 
			",metadata = '" + metadata + '\'' + 
			",visibility = '" + visibility + '\'' + 
			",description = '" + description + '\'' + 
			",discount = '" + discount + '\'' + 
			",deliverable = '" + deliverable + '\'' + 
			",id_product_category = '" + idProductCategory + '\'' + 
			",price_capital = '" + priceCapital + '\'' + 
			",condition = '" + condition + '\'' + 
			",price_sale = '" + priceSale + '\'' + 
			",target_age = '" + targetAge + '\'' + 
			",name = '" + name + '\'' + 
			",id = '" + id + '\'' + 
			",id_currency = '" + idCurrency + '\'' + 
			",sku = '" + sku + '\'' + 
			",stock = '" + stock + '\'' + 
			",slug = '" + slug + '\'' + 
			",target_gender = '" + targetGender + '\'' + 
			"}";
		}
}