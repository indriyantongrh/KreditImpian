package com.application.kreditimpian.Model.ModelNotifikasiV2;

import com.google.gson.annotations.SerializedName;

public class DataItem{

	@SerializedName("name_product_req")
	private String nameProductReq;

	@SerializedName("downloadable")
	private Object downloadable;

	@SerializedName("reference_id")
	private String referenceId;

	@SerializedName("name_product")
	private Object nameProduct;

	@SerializedName("product_condition")
	private Object productCondition;

	@SerializedName("id_transaction")
	private String idTransaction;

	@SerializedName("description")
	private Object description;

	@SerializedName("discount")
	private Object discount;

	@SerializedName("deliverable")
	private Object deliverable;

	@SerializedName("message")
	private String message;

	@SerializedName("price_capital")
	private Object priceCapital;

	@SerializedName("product_images")
	private String productImages;

	@SerializedName("number")
	private String number;

	@SerializedName("price_sale")
	private Object priceSale;

	@SerializedName("target_age")
	private Object targetAge;

	@SerializedName("record_create_timestamp")
	private RecordCreateTimestamp recordCreateTimestamp;

	@SerializedName("sku")
	private Object sku;

	@SerializedName("stock")
	private Object stock;

	@SerializedName("slug")
	private Object slug;

	@SerializedName("status")
	private String status;

	@SerializedName("target_gender")
	private Object targetGender;

	public void setNameProductReq(String nameProductReq){
		this.nameProductReq = nameProductReq;
	}

	public String getNameProductReq(){
		return nameProductReq;
	}

	public void setDownloadable(Object downloadable){
		this.downloadable = downloadable;
	}

	public Object getDownloadable(){
		return downloadable;
	}

	public void setReferenceId(String referenceId){
		this.referenceId = referenceId;
	}

	public String getReferenceId(){
		return referenceId;
	}

	public void setNameProduct(Object nameProduct){
		this.nameProduct = nameProduct;
	}

	public Object getNameProduct(){
		return nameProduct;
	}

	public void setProductCondition(Object productCondition){
		this.productCondition = productCondition;
	}

	public Object getProductCondition(){
		return productCondition;
	}

	public void setIdTransaction(String idTransaction){
		this.idTransaction = idTransaction;
	}

	public String getIdTransaction(){
		return idTransaction;
	}

	public void setDescription(Object description){
		this.description = description;
	}

	public Object getDescription(){
		return description;
	}

	public void setDiscount(Object discount){
		this.discount = discount;
	}

	public Object getDiscount(){
		return discount;
	}

	public void setDeliverable(Object deliverable){
		this.deliverable = deliverable;
	}

	public Object getDeliverable(){
		return deliverable;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	public void setPriceCapital(Object priceCapital){
		this.priceCapital = priceCapital;
	}

	public Object getPriceCapital(){
		return priceCapital;
	}

	public void setProductImages(String productImages){
		this.productImages = productImages;
	}

	public String getProductImages(){
		return productImages;
	}

	public void setNumber(String number){
		this.number = number;
	}

	public String getNumber(){
		return number;
	}

	public void setPriceSale(Object priceSale){
		this.priceSale = priceSale;
	}

	public Object getPriceSale(){
		return priceSale;
	}

	public void setTargetAge(Object targetAge){
		this.targetAge = targetAge;
	}

	public Object getTargetAge(){
		return targetAge;
	}

	public void setRecordCreateTimestamp(RecordCreateTimestamp recordCreateTimestamp){
		this.recordCreateTimestamp = recordCreateTimestamp;
	}

	public RecordCreateTimestamp getRecordCreateTimestamp(){
		return recordCreateTimestamp;
	}

	public void setSku(Object sku){
		this.sku = sku;
	}

	public Object getSku(){
		return sku;
	}

	public void setStock(Object stock){
		this.stock = stock;
	}

	public Object getStock(){
		return stock;
	}

	public void setSlug(Object slug){
		this.slug = slug;
	}

	public Object getSlug(){
		return slug;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	public void setTargetGender(Object targetGender){
		this.targetGender = targetGender;
	}

	public Object getTargetGender(){
		return targetGender;
	}
}