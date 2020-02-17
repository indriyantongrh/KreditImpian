package com.application.kreditimpian.Model.ModelProductRevisi;


import com.google.gson.annotations.SerializedName;


public class ResultItem{

	@SerializedName("downloadable")
	private String downloadable;

	@SerializedName("metadata")
	private Metadata metadata;

	@SerializedName("description")
	private String description;

	@SerializedName("discount")
	private String discount;

	@SerializedName("deliverable")
	private String deliverable;

	@SerializedName("variants")
	private Variants variants;

	@SerializedName("target_age")
	private String targetAge;

	@SerializedName("record")
	private Record record;

	@SerializedName("currency")
	private Currency currency;

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

	@SerializedName("image")
	private String image;

	@SerializedName("images")
	private Images images;

	@SerializedName("visibility")
	private String visibility;

	@SerializedName("merchant")
	private Merchant merchant;

	@SerializedName("id_product_category")
	private String idProductCategory;

	@SerializedName("price_capital")
	private String priceCapital;

	@SerializedName("condition")
	private String condition;

	@SerializedName("price_sale")
	private String priceSale;

	@SerializedName("name")
	private String name;

	@SerializedName("category")
	private Category category;

	@SerializedName("target_gender")
	private String targetGender;

	public void setDownloadable(String downloadable){
		this.downloadable = downloadable;
	}

	public String getDownloadable(){
		return downloadable;
	}

	public void setMetadata(Metadata metadata){
		this.metadata = metadata;
	}

	public Metadata getMetadata(){
		return metadata;
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

	public void setVariants(Variants variants){
		this.variants = variants;
	}

	public Variants getVariants(){
		return variants;
	}

	public void setTargetAge(String targetAge){
		this.targetAge = targetAge;
	}

	public String getTargetAge(){
		return targetAge;
	}

	public void setRecord(Record record){
		this.record = record;
	}

	public Record getRecord(){
		return record;
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

	public void setImage(String image){
		this.image = image;
	}

	public String getImage(){
		return image;
	}

	public void setImages(Images images){
		this.images = images;
	}

	public Images getImages(){
		return images;
	}

	public void setVisibility(String visibility){
		this.visibility = visibility;
	}

	public String getVisibility(){
		return visibility;
	}

	public void setMerchant(Merchant merchant){
		this.merchant = merchant;
	}

	public Merchant getMerchant(){
		return merchant;
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

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setCategory(Category category){
		this.category = category;
	}

	public Category getCategory(){
		return category;
	}

	public void setTargetGender(String targetGender){
		this.targetGender = targetGender;
	}

	public String getTargetGender(){
		return targetGender;
	}
}