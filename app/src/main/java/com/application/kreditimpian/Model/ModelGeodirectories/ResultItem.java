package com.application.kreditimpian.Model.ModelGeodirectories;


import com.google.gson.annotations.SerializedName;


public class ResultItem{

	@SerializedName("id_source")
	private String idSource;

	@SerializedName("name")
	private String name;

	@SerializedName("id")
	private String id;

	@SerializedName("postal")
	private Object postal;

	@SerializedName("id_parent")
	private String idParent;

	@SerializedName("type")
	private String type;

	public void setIdSource(String idSource){
		this.idSource = idSource;
	}

	public String getIdSource(){
		return idSource;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setPostal(Object postal){
		this.postal = postal;
	}

	public Object getPostal(){
		return postal;
	}

	public void setIdParent(String idParent){
		this.idParent = idParent;
	}

	public String getIdParent(){
		return idParent;
	}

	public void setType(String type){
		this.type = type;
	}

	public String getType(){
		return type;
	}
}