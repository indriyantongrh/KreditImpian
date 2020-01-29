package com.application.kreditimpian.Model.ModelProductBaru;


import com.google.gson.annotations.SerializedName;


public class JsonMember0{

	@SerializedName("price_unit")
	private String priceUnit;

	@SerializedName("price_additional")
	private String priceAdditional;

	@SerializedName("id_product")
	private String idProduct;

	@SerializedName("name")
	private String name;

	@SerializedName("id")
	private String id;

	@SerializedName("sku")
	private String sku;

	@SerializedName("stock")
	private String stock;

	@SerializedName("barcode")
	private Object barcode;

	@SerializedName("value")
	private String value;

	@SerializedName("orientation")
	private String orientation;

	@SerializedName("metadata")
	private String metadata;

	@SerializedName("filename")
	private String filename;

	@SerializedName("mime")
	private String mime;

	@SerializedName("primary")
	private String primary;

	public void setPriceUnit(String priceUnit){
		this.priceUnit = priceUnit;
	}

	public String getPriceUnit(){
		return priceUnit;
	}

	public void setPriceAdditional(String priceAdditional){
		this.priceAdditional = priceAdditional;
	}

	public String getPriceAdditional(){
		return priceAdditional;
	}

	public void setIdProduct(String idProduct){
		this.idProduct = idProduct;
	}

	public String getIdProduct(){
		return idProduct;
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

	public void setSku(String sku){
		this.sku = sku;
	}

	public String getSku(){
		return sku;
	}

	public void setStock(String stock){
		this.stock = stock;
	}

	public String getStock(){
		return stock;
	}

	public void setBarcode(Object barcode){
		this.barcode = barcode;
	}

	public Object getBarcode(){
		return barcode;
	}

	public void setValue(String value){
		this.value = value;
	}

	public String getValue(){
		return value;
	}

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

	public void setPrimary(String primary){
		this.primary = primary;
	}

	public String getPrimary(){
		return primary;
	}
}