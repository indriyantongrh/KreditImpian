package com.application.kreditimpian.Model.ModelNotifikasiProducts;

import com.google.gson.annotations.SerializedName;

public class Metadata{

	@SerializedName("note")
	private String note;

	@SerializedName("tenor")
	private String tenor;

	@SerializedName("shipping")
	private Shipping shipping;

	@SerializedName("courier")
	private String courier;

	@SerializedName("installment")
	private Installment installment;

	@SerializedName("id_creditor")
	private String idCreditor;

	@SerializedName("total_pembayaran")
	private String totalPembayaran;

	@SerializedName("down_payment")
	private String downPayment;

	@SerializedName("postal_fee")
	private String postalFee;

	@SerializedName("payment_method")
	private String paymentMethod;

	public void setNote(String note){
		this.note = note;
	}

	public String getNote(){
		return note;
	}

	public void setTenor(String tenor){
		this.tenor = tenor;
	}

	public String getTenor(){
		return tenor;
	}

	public void setShipping(Shipping shipping){
		this.shipping = shipping;
	}

	public Shipping getShipping(){
		return shipping;
	}

	public void setCourier(String courier){
		this.courier = courier;
	}

	public String getCourier(){
		return courier;
	}

	public void setInstallment(Installment installment){
		this.installment = installment;
	}

	public Installment getInstallment(){
		return installment;
	}

	public void setIdCreditor(String idCreditor){
		this.idCreditor = idCreditor;
	}

	public String getIdCreditor(){
		return idCreditor;
	}

	public void setTotalPembayaran(String totalPembayaran){
		this.totalPembayaran = totalPembayaran;
	}

	public String getTotalPembayaran(){
		return totalPembayaran;
	}

	public void setDownPayment(String downPayment){
		this.downPayment = downPayment;
	}

	public String getDownPayment(){
		return downPayment;
	}

	public void setPostalFee(String postalFee){
		this.postalFee = postalFee;
	}

	public String getPostalFee(){
		return postalFee;
	}

	public void setPaymentMethod(String paymentMethod){
		this.paymentMethod = paymentMethod;
	}

	public String getPaymentMethod(){
		return paymentMethod;
	}
}