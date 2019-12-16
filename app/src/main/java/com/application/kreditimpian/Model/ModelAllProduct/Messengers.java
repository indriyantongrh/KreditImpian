package com.application.kreditimpian.Model.ModelAllProduct;

import com.google.gson.annotations.SerializedName;




public class Messengers {

	@SerializedName("whatsapp")
	private String whatsapp;

	@SerializedName("skype")
	private String skype;

	@SerializedName("line")
	private String line;

	@SerializedName("wechat")
	private String wechat;

	@SerializedName("telegram")
	private String telegram;

	public void setWhatsapp(String whatsapp){
		this.whatsapp = whatsapp;
	}

	public String getWhatsapp(){
		return whatsapp;
	}

	public void setSkype(String skype){
		this.skype = skype;
	}

	public String getSkype(){
		return skype;
	}

	public void setLine(String line){
		this.line = line;
	}

	public String getLine(){
		return line;
	}

	public void setWechat(String wechat){
		this.wechat = wechat;
	}

	public String getWechat(){
		return wechat;
	}

	public void setTelegram(String telegram){
		this.telegram = telegram;
	}

	public String getTelegram(){
		return telegram;
	}
}