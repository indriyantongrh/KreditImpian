package com.application.kreditimpian.Model.ModelCityRajaOngkir;


import com.google.gson.annotations.SerializedName;


public class ResponseCityRajaOngkir{

	@SerializedName("rajaongkir")
	private Rajaongkir rajaongkir;

	public void setRajaongkir(Rajaongkir rajaongkir){
		this.rajaongkir = rajaongkir;
	}

	public Rajaongkir getRajaongkir(){
		return rajaongkir;
	}
}