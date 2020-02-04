package com.application.kreditimpian.Model.ModelMitraSelected;


import com.google.gson.annotations.SerializedName;


public class DataItem{

	public boolean isCheck() {
		return check;
	}

	public void setCheck(boolean check) {
		this.check = check;
	}

	private boolean check = false;

	@SerializedName("tenor")
	private String tenor;

	@SerializedName("interest")
	private String interest;

	@SerializedName("id_company")
	private String idCompany;

	@SerializedName("name")
	private String name;

	@SerializedName("id_product_category")
	private String idProductCategory;

	@SerializedName("downpayment")
	private String downpayment;

	public void setTenor(String tenor){
		this.tenor = tenor;
	}

	public String getTenor(){
		return tenor;
	}

	public void setInterest(String interest){
		this.interest = interest;
	}

	public String getInterest(){
		return interest;
	}

	public void setIdCompany(String idCompany){
		this.idCompany = idCompany;
	}

	public String getIdCompany(){
		return idCompany;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setIdProductCategory(String idProductCategory){
		this.idProductCategory = idProductCategory;
	}

	public String getIdProductCategory(){
		return idProductCategory;
	}

	public void setDownpayment(String downpayment){
		this.downpayment = downpayment;
	}

	public String getDownpayment(){
		return downpayment;
	}
}