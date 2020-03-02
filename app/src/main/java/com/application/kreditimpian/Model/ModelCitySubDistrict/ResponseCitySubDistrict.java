package com.application.kreditimpian.Model.ModelCitySubDistrict;


import com.google.gson.annotations.SerializedName;


public class ResponseCitySubDistrict{

	@SerializedName("rajaongkir")
	private Rajaongkir rajaongkir;

	public void setRajaongkir(Rajaongkir rajaongkir){
		this.rajaongkir = rajaongkir;
	}

	public Rajaongkir getRajaongkir(){
		return rajaongkir;
	}
}