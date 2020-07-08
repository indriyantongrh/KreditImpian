package com.application.kreditimpian.Model.ModelNotifikasiFeatures;

import com.google.gson.annotations.SerializedName;

public class Transaction{

	@SerializedName("number")
	private String number;

	@SerializedName("metadata")
	private Metadata metadata;

	@SerializedName("product")
	private Product product;

	@SerializedName("reference_id")
	private String referenceId;

	@SerializedName("reference_model")
	private String referenceModel;

	@SerializedName("creditor")
	private Creditor creditor;

	@SerializedName("id")
	private String id;

	public void setNumber(String number){
		this.number = number;
	}

	public String getNumber(){
		return number;
	}

	public void setMetadata(Metadata metadata){
		this.metadata = metadata;
	}

	public Metadata getMetadata(){
		return metadata;
	}

	public void setProduct(Product product){
		this.product = product;
	}

	public Product getProduct(){
		return product;
	}

	public void setReferenceId(String referenceId){
		this.referenceId = referenceId;
	}

	public String getReferenceId(){
		return referenceId;
	}

	public void setReferenceModel(String referenceModel){
		this.referenceModel = referenceModel;
	}

	public String getReferenceModel(){
		return referenceModel;
	}

	public void setCreditor(Creditor creditor){
		this.creditor = creditor;
	}

	public Creditor getCreditor(){
		return creditor;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}
}