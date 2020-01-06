package com.application.kreditimpian.Model.ModelMember;


import com.google.gson.annotations.SerializedName;


public class ResultItem{

	@SerializedName("number")
	private String number;

	@SerializedName("image")
	private String image;

	@SerializedName("metadata")
	private Metadata metadata;

	@SerializedName("statistic")
	private Object statistic;

	@SerializedName("record")
	private Record record;

	@SerializedName("id")
	private String id;

	@SerializedName("fullname")
	private String fullname;

	@SerializedName("id_geodirectory")
	private Object idGeodirectory;

	public void setNumber(String number){
		this.number = number;
	}

	public String getNumber(){
		return number;
	}

	public void setImage(String image){
		this.image = image;
	}

	public String getImage(){
		return image;
	}

	public void setMetadata(Metadata metadata){
		this.metadata = metadata;
	}

	public Metadata getMetadata(){
		return metadata;
	}

	public void setStatistic(Object statistic){
		this.statistic = statistic;
	}

	public Object getStatistic(){
		return statistic;
	}

	public void setRecord(Record record){
		this.record = record;
	}

	public Record getRecord(){
		return record;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setFullname(String fullname){
		this.fullname = fullname;
	}

	public String getFullname(){
		return fullname;
	}

	public void setIdGeodirectory(Object idGeodirectory){
		this.idGeodirectory = idGeodirectory;
	}

	public Object getIdGeodirectory(){
		return idGeodirectory;
	}

	@Override
 	public String toString(){
		return 
			"ResultItem{" + 
			"number = '" + number + '\'' + 
			",image = '" + image + '\'' + 
			",metadata = '" + metadata + '\'' + 
			",statistic = '" + statistic + '\'' + 
			",record = '" + record + '\'' + 
			",id = '" + id + '\'' + 
			",fullname = '" + fullname + '\'' + 
			",id_geodirectory = '" + idGeodirectory + '\'' + 
			"}";
		}
}