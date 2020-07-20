package com.application.kreditimpian.Model.ModelHistoryCatalogV2;

import com.google.gson.annotations.SerializedName;

public class DataItem{

	@SerializedName("name_merchant")
	private String nameMerchant;

	@SerializedName("note")
	private String note;

	@SerializedName("expires")
	private String expires;

	@SerializedName("downloadable")
	private String downloadable;

	@SerializedName("id_member")
	private String idMember;

	@SerializedName("id_transaction")
	private String idTransaction;

	@SerializedName("discount")
	private String discount;

	@SerializedName("deliverable")
	private String deliverable;

	@SerializedName("id_creditor")
	private String idCreditor;

	@SerializedName("total_pembayaran")
	private String totalPembayaran;

	@SerializedName("number")
	private String number;

	@SerializedName("tenor")
	private String tenor;

	@SerializedName("target_age")
	private String targetAge;

	@SerializedName("shipping")
	private Shipping shipping;

	@SerializedName("name_district")
	private String nameDistrict;

	@SerializedName("id")
	private String id;

	@SerializedName("id_currency")
	private String idCurrency;

	@SerializedName("sku")
	private String sku;

	@SerializedName("stock")
	private String stock;

	@SerializedName("name_city")
	private String nameCity;

	@SerializedName("slug")
	private String slug;

	@SerializedName("payment_method")
	private String paymentMethod;

	@SerializedName("timestamp")
	private String timestamp;

	@SerializedName("visibility")
	private String visibility;

	@SerializedName("id_merchant")
	private String idMerchant;

	@SerializedName("id_product_category")
	private String idProductCategory;

	@SerializedName("down_payment")
	private String downPayment;

	@SerializedName("price_capital")
	private String priceCapital;

	@SerializedName("name_company")
	private String nameCompany;

	@SerializedName("condition")
	private String condition;

	@SerializedName("id_product")
	private String idProduct;

	@SerializedName("filename")
	private String filename;

	@SerializedName("price_sale")
	private String priceSale;

	@SerializedName("courier")
	private String courier;

/*	@SerializedName("installment")
	private Installment installment;*/

	@SerializedName("name")
	private String name;

	@SerializedName("postal_fee")
	private String postalFee;

	@SerializedName("status")
	private String status;

	@SerializedName("target_gender")
	private String targetGender;

	public void setNameMerchant(String nameMerchant){
		this.nameMerchant = nameMerchant;
	}

	public String getNameMerchant(){
		return nameMerchant;
	}

	public void setNote(String note){
		this.note = note;
	}

	public String getNote(){
		return note;
	}

	public void setExpires(String expires){
		this.expires = expires;
	}

	public String getExpires(){
		return expires;
	}

	public void setDownloadable(String downloadable){
		this.downloadable = downloadable;
	}

	public String getDownloadable(){
		return downloadable;
	}

	public void setIdMember(String idMember){
		this.idMember = idMember;
	}

	public String getIdMember(){
		return idMember;
	}

	public void setIdTransaction(String idTransaction){
		this.idTransaction = idTransaction;
	}

	public String getIdTransaction(){
		return idTransaction;
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

	public void setIdCreditor(String idCreditor){
		this.idCreditor = idCreditor;
	}

	public String getIdCreditor(){
		return idCreditor;
	}

	public void setTotalPembayaran(String totalPembayaran){
		this.totalPembayaran = totalPembayaran;
	}

	public String getTotalPembayaran(){
		return totalPembayaran;
	}

	public void setNumber(String number){
		this.number = number;
	}

	public String getNumber(){
		return number;
	}

	public void setTenor(String tenor){
		this.tenor = tenor;
	}

	public String getTenor(){
		return tenor;
	}

	public void setTargetAge(String targetAge){
		this.targetAge = targetAge;
	}

	public String getTargetAge(){
		return targetAge;
	}

	public void setShipping(Shipping shipping){
		this.shipping = shipping;
	}

	public Shipping getShipping(){
		return shipping;
	}

	public void setNameDistrict(String nameDistrict){
		this.nameDistrict = nameDistrict;
	}

	public String getNameDistrict(){
		return nameDistrict;
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

	public void setNameCity(String nameCity){
		this.nameCity = nameCity;
	}

	public String getNameCity(){
		return nameCity;
	}

	public void setSlug(String slug){
		this.slug = slug;
	}

	public String getSlug(){
		return slug;
	}

	public void setPaymentMethod(String paymentMethod){
		this.paymentMethod = paymentMethod;
	}

	public String getPaymentMethod(){
		return paymentMethod;
	}

	public void setTimestamp(String timestamp){
		this.timestamp = timestamp;
	}

	public String getTimestamp(){
		return timestamp;
	}

	public void setVisibility(String visibility){
		this.visibility = visibility;
	}

	public String getVisibility(){
		return visibility;
	}

	public void setIdMerchant(String idMerchant){
		this.idMerchant = idMerchant;
	}

	public String getIdMerchant(){
		return idMerchant;
	}

	public void setIdProductCategory(String idProductCategory){
		this.idProductCategory = idProductCategory;
	}

	public String getIdProductCategory(){
		return idProductCategory;
	}

	public void setDownPayment(String downPayment){
		this.downPayment = downPayment;
	}

	public String getDownPayment(){
		return downPayment;
	}

	public void setPriceCapital(String priceCapital){
		this.priceCapital = priceCapital;
	}

	public String getPriceCapital(){
		return priceCapital;
	}

	public void setNameCompany(String nameCompany){
		this.nameCompany = nameCompany;
	}

	public String getNameCompany(){
		return nameCompany;
	}

	public void setCondition(String condition){
		this.condition = condition;
	}

	public String getCondition(){
		return condition;
	}

	public void setIdProduct(String idProduct){
		this.idProduct = idProduct;
	}

	public String getIdProduct(){
		return idProduct;
	}

	public void setFilename(String filename){
		this.filename = filename;
	}

	public String getFilename(){
		return filename;
	}

	public void setPriceSale(String priceSale){
		this.priceSale = priceSale;
	}

	public String getPriceSale(){
		return priceSale;
	}

	public void setCourier(String courier){
		this.courier = courier;
	}

	public String getCourier(){
		return courier;
	}

/*	public void setInstallment(Installment installment){
		this.installment = installment;
	}

	public Installment getInstallment(){
		return installment;
	}*/

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setPostalFee(String postalFee){
		this.postalFee = postalFee;
	}

	public String getPostalFee(){
		return postalFee;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	public void setTargetGender(String targetGender){
		this.targetGender = targetGender;
	}

	public String getTargetGender(){
		return targetGender;
	}
}