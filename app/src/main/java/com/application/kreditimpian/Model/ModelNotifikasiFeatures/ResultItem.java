package com.application.kreditimpian.Model.ModelNotifikasiFeatures;

import com.google.gson.annotations.SerializedName;

public class ResultItem{

	@SerializedName("sys_module_user_sender_id")
	private String sysModuleUserSenderId;

	@SerializedName("metadata")
	private String metadata;

	@SerializedName("reference_id")
	private String referenceId;

	@SerializedName("sys_module_user_recipient_id")
	private String sysModuleUserRecipientId;

	@SerializedName("reference_model")
	private String referenceModel;

	@SerializedName("id")
	private String id;

	@SerializedName("message")
	private String message;

	@SerializedName("transaction")
	private Transaction transaction;

	@SerializedName("status")
	private String status;

	public void setSysModuleUserSenderId(String sysModuleUserSenderId){
		this.sysModuleUserSenderId = sysModuleUserSenderId;
	}

	public String getSysModuleUserSenderId(){
		return sysModuleUserSenderId;
	}

	public void setMetadata(String metadata){
		this.metadata = metadata;
	}

	public String getMetadata(){
		return metadata;
	}

	public void setReferenceId(String referenceId){
		this.referenceId = referenceId;
	}

	public String getReferenceId(){
		return referenceId;
	}

	public void setSysModuleUserRecipientId(String sysModuleUserRecipientId){
		this.sysModuleUserRecipientId = sysModuleUserRecipientId;
	}

	public String getSysModuleUserRecipientId(){
		return sysModuleUserRecipientId;
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

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	public void setTransaction(Transaction transaction){
		this.transaction = transaction;
	}

	public Transaction getTransaction(){
		return transaction;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}
}