package com.application.kreditimpian.Model.ModelSubDistrict;


import com.google.gson.annotations.SerializedName;


public class ResponseSubdistrict{

	@SerializedName("rajaongkir")
	private Rajaongkir rajaongkir;

	public void setRajaongkir(Rajaongkir rajaongkir){
		this.rajaongkir = rajaongkir;
	}

	public Rajaongkir getRajaongkir(){
		return rajaongkir;
	}
}