package com.application.kreditimpian.Model.ModelCicilan;

import java.util.List;

import com.google.gson.annotations.SerializedName;


public class Data{

	@SerializedName("product_meta")
	private ProductMeta productMeta;

	@SerializedName("companies_data")
	private List<CompaniesDataItem> companiesData;

	public void setProductMeta(ProductMeta productMeta){
		this.productMeta = productMeta;
	}

	public ProductMeta getProductMeta(){
		return productMeta;
	}

	public void setCompaniesData(List<CompaniesDataItem> companiesData){
		this.companiesData = companiesData;
	}

	public List<CompaniesDataItem> getCompaniesData(){
		return companiesData;
	}
}