package com.application.kreditimpian.Model.ModelNotifikasiFeature;

import com.google.gson.annotations.SerializedName;

public class Metadata{

	@SerializedName("note")
	private String note;

	@SerializedName("number_po")
	private String numberPo;

	@SerializedName("method")
	private String method;

	@SerializedName("product_image")
	private String productImage;

	@SerializedName("description")
	private String description;

	@SerializedName("id_creditor")
	private String idCreditor;

	@SerializedName("total_pembayaran")
	private String totalPembayaran;

	@SerializedName("down_payment")
	private String downPayment;

	@SerializedName("product_name")
	private String productName;

	@SerializedName("tenor")
	private String tenor;

	@SerializedName("shipping")
	private Shipping shipping;

	@SerializedName("courier")
	private String courier;

	@SerializedName("sumber_pesanan")
	private String sumberPesanan;

	@SerializedName("installment")
	private Installment installment;

	@SerializedName("category")
	private String category;

	@SerializedName("resi")
	private String resi;

	@SerializedName("postal_fee")
	private String postalFee;

	@SerializedName("payment_method")
	private String paymentMethod;

	@SerializedName("bank_account_owner_name")
	private String bankAccountOwnerName;

	@SerializedName("transfer_date")
	private String transferDate;

	@SerializedName("confirm")
	private String confirm;

	@SerializedName("transfer_amount")
	private String transferAmount;

	@SerializedName("bank_name")
	private String bankName;

	public void setNote(String note){
		this.note = note;
	}

	public String getNote(){
		return note;
	}

	public void setNumberPo(String numberPo){
		this.numberPo = numberPo;
	}

	public String getNumberPo(){
		return numberPo;
	}

	public void setMethod(String method){
		this.method = method;
	}

	public String getMethod(){
		return method;
	}

	public void setProductImage(String productImage){
		this.productImage = productImage;
	}

	public String getProductImage(){
		return productImage;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
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

	public void setProductName(String productName){
		this.productName = productName;
	}

	public String getProductName(){
		return productName;
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

	public void setSumberPesanan(String sumberPesanan){
		this.sumberPesanan = sumberPesanan;
	}

	public String getSumberPesanan(){
		return sumberPesanan;
	}

	public void setInstallment(Installment installment){
		this.installment = installment;
	}

	public Installment getInstallment(){
		return installment;
	}

	public void setCategory(String category){
		this.category = category;
	}

	public String getCategory(){
		return category;
	}

	public void setResi(String resi){
		this.resi = resi;
	}

	public String getResi(){
		return resi;
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

	public void setBankAccountOwnerName(String bankAccountOwnerName){
		this.bankAccountOwnerName = bankAccountOwnerName;
	}

	public String getBankAccountOwnerName(){
		return bankAccountOwnerName;
	}

	public void setTransferDate(String transferDate){
		this.transferDate = transferDate;
	}

	public String getTransferDate(){
		return transferDate;
	}

	public void setConfirm(String confirm){
		this.confirm = confirm;
	}

	public String getConfirm(){
		return confirm;
	}

	public void setTransferAmount(String transferAmount){
		this.transferAmount = transferAmount;
	}

	public String getTransferAmount(){
		return transferAmount;
	}

	public void setBankName(String bankName){
		this.bankName = bankName;
	}

	public String getBankName(){
		return bankName;
	}
}