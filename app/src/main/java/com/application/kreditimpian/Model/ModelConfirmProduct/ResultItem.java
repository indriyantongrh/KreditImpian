package com.application.kreditimpian.Model.ModelConfirmProduct;

import com.google.gson.annotations.SerializedName;

public class ResultItem{

	@SerializedName("expires")
	private String expires;

	@SerializedName("id_transaction")
	private String idTransaction;

	@SerializedName("record_create_timestamp")
	private String recordCreateTimestamp;

	@SerializedName("record_update_timestamp")
	private String recordUpdateTimestamp;

	@SerializedName("record_update_user")
	private String recordUpdateUser;

	@SerializedName("record_create_user")
	private String recordCreateUser;

	@SerializedName("id")
	private Integer id;

	@SerializedName("status")
	private String status;

	@SerializedName("timestamp")
	private String timestamp;

	public void setExpires(String expires){
		this.expires = expires;
	}

	public String getExpires(){
		return expires;
	}

	public void setIdTransaction(String idTransaction){
		this.idTransaction = idTransaction;
	}

	public String getIdTransaction(){
		return idTransaction;
	}

	public void setRecordCreateTimestamp(String recordCreateTimestamp){
		this.recordCreateTimestamp = recordCreateTimestamp;
	}

	public String getRecordCreateTimestamp(){
		return recordCreateTimestamp;
	}

	public void setRecordUpdateTimestamp(String recordUpdateTimestamp){
		this.recordUpdateTimestamp = recordUpdateTimestamp;
	}

	public String getRecordUpdateTimestamp(){
		return recordUpdateTimestamp;
	}

	public void setRecordUpdateUser(String recordUpdateUser){
		this.recordUpdateUser = recordUpdateUser;
	}

	public Object getRecordUpdateUser(){
		return recordUpdateUser;
	}

	public void setRecordCreateUser(String recordCreateUser){
		this.recordCreateUser = recordCreateUser;
	}

	public Object getRecordCreateUser(){
		return recordCreateUser;
	}

	public void setId(Integer id){
		this.id = id;
	}

	public Integer getId(){
		return id;
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