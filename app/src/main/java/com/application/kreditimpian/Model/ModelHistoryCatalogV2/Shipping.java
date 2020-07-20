package com.application.kreditimpian.Model.ModelHistoryCatalogV2;

import com.google.gson.annotations.SerializedName;

public class Shipping{

	@SerializedName("send")
	private Send send;

	public void setSend(Send send){
		this.send = send;
	}

	public Send getSend(){
		return send;
	}
}