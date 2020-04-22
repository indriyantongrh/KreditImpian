package com.application.kreditimpian.Model.ModelMultiguna;

import com.google.gson.annotations.SerializedName;

public class DataItem{

	@SerializedName("loan")
	private String loan;

	@SerializedName("images")
	private String images;

	@SerializedName("id_member")
	private String idMember;

	@SerializedName("id_transaction")
	private String idTransaction;

	@SerializedName("id_creditor")
	private String idCreditor;

	@SerializedName("vehicle_type")
	private String vehicleType;

	@SerializedName("vehicles")
	private String vehicles;

	@SerializedName("id_member_transaction")
	private String idMemberTransaction;

	@SerializedName("vehicle_brand")
	private String vehicleBrand;

	@SerializedName("tenor")
	private String tenor;

	@SerializedName("vehicle_year")
	private String vehicleYear;

	@SerializedName("cicilan")
	private String cicilan;

	@SerializedName("reference_row4id")
	private String referenceRow4id;

	@SerializedName("location")
	private String location;

	@SerializedName("vehicle_price")
	private String vehiclePrice;

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@SerializedName("number")
	private String number;

	@SerializedName("status")
	private String status;

	public void setLoan(String loan){
		this.loan = loan;
	}

	public String getLoan(){
		return loan;
	}

	public void setImages(String images){
		this.images = images;
	}

	public String getImages(){
		return images;
	}

	public void setIdMember(String idMember){
		this.idMember = idMember;
	}

	public String getIdMember(){
		return idMember;
	}

	public void setIdTransaction(String idTransaction){
		this.idTransaction = idTransaction;
	}

	public String getIdTransaction(){
		return idTransaction;
	}

	public void setIdCreditor(String idCreditor){
		this.idCreditor = idCreditor;
	}

	public String getIdCreditor(){
		return idCreditor;
	}

	public void setVehicleType(String vehicleType){
		this.vehicleType = vehicleType;
	}

	public String getVehicleType(){
		return vehicleType;
	}

	public void setVehicles(String vehicles){
		this.vehicles = vehicles;
	}

	public String getVehicles(){
		return vehicles;
	}

	public void setIdMemberTransaction(String idMemberTransaction){
		this.idMemberTransaction = idMemberTransaction;
	}

	public String getIdMemberTransaction(){
		return idMemberTransaction;
	}

	public void setVehicleBrand(String vehicleBrand){
		this.vehicleBrand = vehicleBrand;
	}

	public String getVehicleBrand(){
		return vehicleBrand;
	}

	public void setTenor(String tenor){
		this.tenor = tenor;
	}

	public String getTenor(){
		return tenor;
	}

	public void setVehicleYear(String vehicleYear){
		this.vehicleYear = vehicleYear;
	}

	public String getVehicleYear(){
		return vehicleYear;
	}

	public void setCicilan(String cicilan){
		this.cicilan = cicilan;
	}

	public String getCicilan(){
		return cicilan;
	}

	public void setReferenceRow4id(String referenceRow4id){
		this.referenceRow4id = referenceRow4id;
	}

	public String getReferenceRow4id(){
		return referenceRow4id;
	}

	public void setLocation(String location){
		this.location = location;
	}

	public String getLocation(){
		return location;
	}

	public void setVehiclePrice(String vehiclePrice){
		this.vehiclePrice = vehiclePrice;
	}

	public String getVehiclePrice(){
		return vehiclePrice;
	}
}