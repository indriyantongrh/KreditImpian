package com.application.kreditimpian.Model.ModelProductBaru;


import com.google.gson.annotations.SerializedName;


public class JsonMember3{

	@SerializedName("orientation")
	private String orientation;

	@SerializedName("metadata")
	private String metadata;

	@SerializedName("id_product")
	private String idProduct;

	@SerializedName("filename")
	private String filename;

	@SerializedName("mime")
	private String mime;

	@SerializedName("id")
	private String id;

	@SerializedName("primary")
	private String primary;

	public void setOrientation(String orientation){
		this.orientation = orientation;
	}

	public String getOrientation(){
		return orientation;
	}

	public void setMetadata(String metadata){
		this.metadata = metadata;
	}

	public String getMetadata(){
		return metadata;
	}

	public void setIdProduct(String idProduct){
		this.idProduct = idProduct;
	}

	public String getIdProduct(){
		return idProduct;
	}

	public void setFilename(String filename){
		this.filename = filename;
	}

	public String getFilename(){
		return filename;
	}

	public void setMime(String mime){
		this.mime = mime;
	}

	public String getMime(){
		return mime;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setPrimary(String primary){
		this.primary = primary;
	}

	public String getPrimary(){
		return primary;
	}
}