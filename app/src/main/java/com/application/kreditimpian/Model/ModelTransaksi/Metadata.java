package com.application.kreditimpian.Model.ModelTransaksi;


import com.google.gson.annotations.SerializedName;


public class Metadata{

	@SerializedName("note")
	private String note;

	@SerializedName("number_po")
	private String numberPo;

	@SerializedName("id_creditor")
	private Object idCreditor;

	@SerializedName("down_payment")
	private String downPayment;

	@SerializedName("bank_account_owner_name")
	private String bankAccountOwnerName;

	@SerializedName("transfer_date")
	private String transferDate;

	@SerializedName("confirm")
	private String confirm;

	@SerializedName("tenor")
	private String tenor;

	@SerializedName("shipping")
	private Shipping shipping;

	@SerializedName("courier")
	private String courier;

	@SerializedName("transfer_amount")
	private String transferAmount;

	@SerializedName("installment")
	private Installment installment;

	@SerializedName("bank_name")
	private String bankName;

	@SerializedName("resi")
	private String resi;

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

	public void setNumberPo(String numberPo){
		this.numberPo = numberPo;
	}

	public String getNumberPo(){
		return numberPo;
	}

	public void setIdCreditor(Object idCreditor){
		this.idCreditor = idCreditor;
	}

	public Object getIdCreditor(){
		return idCreditor;
	}

	public void setDownPayment(String downPayment){
		this.downPayment = downPayment;
	}

	public String getDownPayment(){
		return downPayment;
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

	public void setTransferAmount(String transferAmount){
		this.transferAmount = transferAmount;
	}

	public String getTransferAmount(){
		return transferAmount;
	}

	public void setInstallment(Installment installment){
		this.installment = installment;
	}

	public Installment getInstallment(){
		return installment;
	}

	public void setBankName(String bankName){
		this.bankName = bankName;
	}

	public String getBankName(){
		return bankName;
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
}