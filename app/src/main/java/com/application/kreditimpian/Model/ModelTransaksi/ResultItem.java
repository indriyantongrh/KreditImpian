package com.application.kreditimpian.Model.ModelTransaksi;


import com.google.gson.annotations.SerializedName;


public class ResultItem{

	@SerializedName("number")
	private String number;

	@SerializedName("metadata")
	private Metadata metadata;

	@SerializedName("reference_id")
	private String referenceId;

	@SerializedName("reference_model")
	private String referenceModel;

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

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}
}