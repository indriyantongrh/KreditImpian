package com.application.kreditimpian.Model.ModelProductNew;

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
	private String discount;

	@SerializedName("deliverable")
	private String deliverable;

	@SerializedName("merchant")
	private Merchant merchant;

	@SerializedName("variants")
	private Variants variants;

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

	@SerializedName("currency")
	private Currency currency;

	@SerializedName("id")
	private String id;

	@SerializedName("sku")
	private String sku;

	@SerializedName("stock")
	private String stock;

	@SerializedName("category")
	private Category category;

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

	public void setDiscount(String discount){
		this.discount = discount;
	}

	public String getDiscount(){
		return discount;
	}

	public void setDeliverable(String deliverable){
		this.deliverable = deliverable;
	}

	public String getDeliverable(){
		return deliverable;
	}

	public void setMerchant(Merchant merchant){
		this.merchant = merchant;
	}

	public Merchant getMerchant(){
		return merchant;
	}

	public void setVariants(Variants variants){
		this.variants = variants;
	}

	public Variants getVariants(){
		return variants;
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

	public void setCurrency(Currency currency){
		this.currency = currency;
	}

	public Currency getCurrency(){
		return currency;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
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

	public void setCategory(Category category){
		this.category = category;
	}

	public Category getCategory(){
		return category;
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
}