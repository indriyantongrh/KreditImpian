package com.application.kreditimpian.Model.ModelNotifikasiFeatures;

import com.google.gson.annotations.SerializedName;

public class Shipping{

	private static Shipping shipping;

	@SerializedName("send")
	private Send send;

	public static Shipping getInstance(){
		if (shipping == null){
			shipping = new Shipping();
		}
		return shipping;
	}

	public void setSend(Send send){
		this.send = send;
	}

	public Send getSend(){
		return send;
	}
}