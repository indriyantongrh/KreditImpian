package com.application.kreditimpian.Model.ModelCicilan;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;


public class ProductMeta implements Parcelable {

	@SerializedName("number")
	private String number;

	@SerializedName("id_product")
	private String idProduct;

	@SerializedName("filename")
	private String filename;

	@SerializedName("reference_id")
	private String referenceId;

	@SerializedName("price_sale")
	private String priceSale;

	@SerializedName("name")
	private String name;

	@SerializedName("id_transaction")
	private String idTransaction;

	@SerializedName("discount")
	private String discount;

	@SerializedName("id_product_category")
	private String idProductCategory;

	@SerializedName("price_capital")
	private String priceCapital;

	@SerializedName("status")
	private String status;

	public void setNumber(String number){
		this.number = number;
	}

	public String getNumber(){
		return number;
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

	public void setReferenceId(String referenceId){
		this.referenceId = referenceId;
	}

	public String getReferenceId(){
		return referenceId;
	}

	public void setPriceSale(String priceSale){
		this.priceSale = priceSale;
	}

	public String getPriceSale(){
		return priceSale;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setIdTransaction(String idTransaction){
		this.idTransaction = idTransaction;
	}

	public String getIdTransaction(){
		return idTransaction;
	}

	public void setDiscount(String discount){
		this.discount = discount;
	}

	public String getDiscount(){
		return discount;
	}

	public void setIdProductCategory(String idProductCategory){
		this.idProductCategory = idProductCategory;
	}

	public String getIdProductCategory(){
		return idProductCategory;
	}

	public void setPriceCapital(String priceCapital){
		this.priceCapital = priceCapital;
	}

	public String getPriceCapital(){
		return priceCapital;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.number);
		dest.writeString(this.idProduct);
		dest.writeString(this.filename);
		dest.writeString(this.referenceId);
		dest.writeString(this.priceSale);
		dest.writeString(this.name);
		dest.writeString(this.idTransaction);
		dest.writeString(this.discount);
		dest.writeString(this.idProductCategory);
		dest.writeString(this.priceCapital);
		dest.writeString(this.status);
	}

	public ProductMeta() {
	}

	protected ProductMeta(Parcel in) {
		this.number = in.readString();
		this.idProduct = in.readString();
		this.filename = in.readString();
		this.referenceId = in.readString();
		this.priceSale = in.readString();
		this.name = in.readString();
		this.idTransaction = in.readString();
		this.discount = in.readString();
		this.idProductCategory = in.readString();
		this.priceCapital = in.readString();
		this.status = in.readString();
	}

	public static final Parcelable.Creator<ProductMeta> CREATOR = new Parcelable.Creator<ProductMeta>() {
		@Override
		public ProductMeta createFromParcel(Parcel source) {
			return new ProductMeta(source);
		}

		@Override
		public ProductMeta[] newArray(int size) {
			return new ProductMeta[size];
		}
	};
}