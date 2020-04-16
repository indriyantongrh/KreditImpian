package com.application.kreditimpian.Model.ModelRequestProduct;

import com.google.gson.annotations.SerializedName;

public class DataItem{

	@SerializedName("expires")
	private String expires;

	@SerializedName("reference_id")
	private String referenceId;

	@SerializedName("method")
	private String method;

	@SerializedName("weight_value")
	private String weightValue;

	@SerializedName("id_member")
	private String idMember;

	@SerializedName("id_transaction")
	private String idTransaction;

	@SerializedName("description")
	private String description;

	@SerializedName("id_product_category")
	private String idProductCategory;

	@SerializedName("products_requests")
	private String productsRequests;

	@SerializedName("content")
	private String content;

	@SerializedName("number")
	private String number;

	@SerializedName("price_sale")
	private String priceSale;

	@SerializedName("name")
	private String name;

	@SerializedName("company_city")
	private CompanyCity companyCity;

	@SerializedName("member_transaction")
	private String memberTransaction;

	@SerializedName("status")
	private String status;

	@SerializedName("timestamp")
	private String timestamp;

	public String getMember_city() {
		return member_city;
	}

	public void setMember_city(String member_city) {
		this.member_city = member_city;
	}

	@SerializedName("member_city")
	private String member_city;

	public void setExpires(String expires){
		this.expires = expires;
	}

	public String getExpires(){
		return expires;
	}

	public void setReferenceId(String referenceId){
		this.referenceId = referenceId;
	}

	public String getReferenceId(){
		return referenceId;
	}

	public void setMethod(String method){
		this.method = method;
	}

	public String getMethod(){
		return method;
	}

	public void setWeightValue(String weightValue){
		this.weightValue = weightValue;
	}

	public String getWeightValue(){
		return weightValue;
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

	public void setProductsRequests(String productsRequests){
		this.productsRequests = productsRequests;
	}

	public String getProductsRequests(){
		return productsRequests;
	}

	public void setContent(String content){
		this.content = content;
	}

	public String getContent(){
		return content;
	}

	public void setNumber(String number){
		this.number = number;
	}

	public String getNumber(){
		return number;
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

	public void setCompanyCity(CompanyCity companyCity){
		this.companyCity = companyCity;
	}

	public CompanyCity getCompanyCity(){
		return companyCity;
	}

	public void setMemberTransaction(String memberTransaction){
		this.memberTransaction = memberTransaction;
	}

	public String getMemberTransaction(){
		return memberTransaction;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	public void setTimestamp(String timestamp){
		this.timestamp = timestamp;
	}

	public String getTimestamp(){
		return timestamp;
	}
}