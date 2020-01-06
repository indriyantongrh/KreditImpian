package com.application.kreditimpian.Model.ModelMember;


import com.google.gson.annotations.SerializedName;


public class Record{

	@SerializedName("create")
	private Create create;

	@SerializedName("update")
	private Update update;

	@SerializedName("status")
	private String status;

	public void setCreate(Create create){
		this.create = create;
	}

	public Create getCreate(){
		return create;
	}

	public void setUpdate(Update update){
		this.update = update;
	}

	public Update getUpdate(){
		return update;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"Record{" + 
			"create = '" + create + '\'' + 
			",update = '" + update + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}