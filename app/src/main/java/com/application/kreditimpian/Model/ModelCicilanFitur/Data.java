package com.application.kreditimpian.Model.ModelCicilanFitur;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Data{

	@SerializedName("companies_data")
	private List<CompaniesDataItem> companiesData;

	public void setCompaniesData(List<CompaniesDataItem> companiesData){
		this.companiesData = companiesData;
	}

	public List<CompaniesDataItem> getCompaniesData(){
		return companiesData;
	}
}