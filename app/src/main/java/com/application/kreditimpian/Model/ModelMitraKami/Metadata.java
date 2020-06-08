package com.application.kreditimpian.Model.ModelMitraKami;

import com.google.gson.annotations.SerializedName;

public class Metadata{

	@SerializedName("owner_name")
	private String ownerName;

	@SerializedName("name")
	private String name;

	@SerializedName("category")
	private String category;

	@SerializedName("owner_email")
	private String ownerEmail;

	@SerializedName("company_phone")
	private String companyPhone;

	@SerializedName("owner_taxpayer")
	private String ownerTaxpayer;

	@SerializedName("owner_citizen")
	private String ownerCitizen;

	@SerializedName("company_email")
	private String companyEmail;

	@SerializedName("photo")
	private String photo;

	@SerializedName("owner_address")
	private String ownerAddress;

	@SerializedName("owner_phone")
	private String ownerPhone;

	@SerializedName("company_taxpayer")
	private String companyTaxpayer;

	@SerializedName("company_address")
	private String companyAddress;

	@SerializedName("business_permit")
	private String businessPermit;

	@SerializedName("postal_code")
	private String postalCode;

	public void setOwnerName(String ownerName){
		this.ownerName = ownerName;
	}

	public String getOwnerName(){
		return ownerName;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setCategory(String category){
		this.category = category;
	}

	public String getCategory(){
		return category;
	}

	public void setOwnerEmail(String ownerEmail){
		this.ownerEmail = ownerEmail;
	}

	public String getOwnerEmail(){
		return ownerEmail;
	}

	public void setCompanyPhone(String companyPhone){
		this.companyPhone = companyPhone;
	}

	public String getCompanyPhone(){
		return companyPhone;
	}

	public void setOwnerTaxpayer(String ownerTaxpayer){
		this.ownerTaxpayer = ownerTaxpayer;
	}

	public String getOwnerTaxpayer(){
		return ownerTaxpayer;
	}

	public void setOwnerCitizen(String ownerCitizen){
		this.ownerCitizen = ownerCitizen;
	}

	public String getOwnerCitizen(){
		return ownerCitizen;
	}

	public void setCompanyEmail(String companyEmail){
		this.companyEmail = companyEmail;
	}

	public String getCompanyEmail(){
		return companyEmail;
	}

	public void setPhoto(String photo){
		this.photo = photo;
	}

	public String getPhoto(){
		return photo;
	}

	public void setOwnerAddress(String ownerAddress){
		this.ownerAddress = ownerAddress;
	}

	public String getOwnerAddress(){
		return ownerAddress;
	}

	public void setOwnerPhone(String ownerPhone){
		this.ownerPhone = ownerPhone;
	}

	public String getOwnerPhone(){
		return ownerPhone;
	}

	public void setCompanyTaxpayer(String companyTaxpayer){
		this.companyTaxpayer = companyTaxpayer;
	}

	public String getCompanyTaxpayer(){
		return companyTaxpayer;
	}

	public void setCompanyAddress(String companyAddress){
		this.companyAddress = companyAddress;
	}

	public String getCompanyAddress(){
		return companyAddress;
	}

	public void setBusinessPermit(String businessPermit){
		this.businessPermit = businessPermit;
	}

	public String getBusinessPermit(){
		return businessPermit;
	}

	public void setPostalCode(String postalCode){
		this.postalCode = postalCode;
	}

	public String getPostalCode(){
		return postalCode;
	}
}