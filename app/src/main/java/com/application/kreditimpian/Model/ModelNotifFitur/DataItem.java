package com.application.kreditimpian.Model.ModelNotifFitur;

import com.google.gson.annotations.SerializedName;

public class DataItem{

	@SerializedName("metadata")
	private String metadata;

	@SerializedName("method")
	private String method;

	@SerializedName("id_member")
	private String idMember;

	@SerializedName("record_create_timestamp")
	private String recordCreateTimestamp;

	@SerializedName("message")
	private String message;

	@SerializedName("id_product_request")
	private String idProductRequest;

	@SerializedName("content")
	private String content;

	@SerializedName("status")
	private String status;

	public void setMetadata(String metadata){
		this.metadata = metadata;
	}

	public String getMetadata(){
		return metadata;
	}

	public void setMethod(String method){
		this.method = method;
	}

	public String getMethod(){
		return method;
	}

	public void setIdMember(String idMember){
		this.idMember = idMember;
	}

	public String getIdMember(){
		return idMember;
	}

	public void setRecordCreateTimestamp(String recordCreateTimestamp){
		this.recordCreateTimestamp = recordCreateTimestamp;
	}

	public String getRecordCreateTimestamp(){
		return recordCreateTimestamp;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	public void setIdProductRequest(String idProductRequest){
		this.idProductRequest = idProductRequest;
	}

	public String getIdProductRequest(){
		return idProductRequest;
	}

	public void setContent(String content){
		this.content = content;
	}

	public String getContent(){
		return content;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}
}