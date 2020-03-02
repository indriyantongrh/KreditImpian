package com.application.kreditimpian.Model.ModelSubDistrict;


import com.google.gson.annotations.SerializedName;


public class Rajaongkir{

	@SerializedName("query")
	private Query query;

	@SerializedName("results")
	private Results results;

	@SerializedName("status")
	private Status status;

	public void setQuery(Query query){
		this.query = query;
	}

	public Query getQuery(){
		return query;
	}

	public void setResults(Results results){
		this.results = results;
	}

	public Results getResults(){
		return results;
	}

	public void setStatus(Status status){
		this.status = status;
	}

	public Status getStatus(){
		return status;
	}
}