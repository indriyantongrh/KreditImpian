package com.application.kreditimpian.Model.ModelSubDistrictRajaOngkir;


import com.google.gson.annotations.SerializedName;


public class ResponseSubDistrictRajaOngkir{

	@SerializedName("rajaongkir")
	private Rajaongkir rajaongkir;

	public void setRajaongkir(Rajaongkir rajaongkir){
		this.rajaongkir = rajaongkir;
	}

	public Rajaongkir getRajaongkir(){
		return rajaongkir;
	}
}