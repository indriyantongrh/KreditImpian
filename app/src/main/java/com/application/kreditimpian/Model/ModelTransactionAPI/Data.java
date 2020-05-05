package com.application.kreditimpian.Model.ModelTransactionAPI;

import com.google.gson.annotations.SerializedName;

public class Data{

	@SerializedName("note")
	private String note;

	@SerializedName("tenor_tahun")
	private Object tenorTahun;

	@SerializedName("tenor_bulan")
	private String tenorBulan;

	@SerializedName("shipping")
	private String shipping;

	@SerializedName("courier")
	private String courier;

	@SerializedName("installment")
	private Object installment;

	@SerializedName("id_creditor")
	private String idCreditor;

	@SerializedName("total_pembayaran")
	private int totalPembayaran;

	@SerializedName("down_payment")
	private Object downPayment;

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

	public void setTenorTahun(Object tenorTahun){
		this.tenorTahun = tenorTahun;
	}

	public Object getTenorTahun(){
		return tenorTahun;
	}

	public void setTenorBulan(String tenorBulan){
		this.tenorBulan = tenorBulan;
	}

	public String getTenorBulan(){
		return tenorBulan;
	}

	public void setShipping(String shipping){
		this.shipping = shipping;
	}

	public String getShipping(){
		return shipping;
	}

	public void setCourier(String courier){
		this.courier = courier;
	}

	public String getCourier(){
		return courier;
	}

	public void setInstallment(Object installment){
		this.installment = installment;
	}

	public Object getInstallment(){
		return installment;
	}

	public void setIdCreditor(String idCreditor){
		this.idCreditor = idCreditor;
	}

	public String getIdCreditor(){
		return idCreditor;
	}

	public void setTotalPembayaran(int totalPembayaran){
		this.totalPembayaran = totalPembayaran;
	}

	public int getTotalPembayaran(){
		return totalPembayaran;
	}

	public void setDownPayment(Object downPayment){
		this.downPayment = downPayment;
	}

	public Object getDownPayment(){
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