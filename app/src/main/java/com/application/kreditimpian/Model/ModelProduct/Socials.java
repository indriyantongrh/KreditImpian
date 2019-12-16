package com.application.kreditimpian.Model.ModelProduct;


import com.google.gson.annotations.SerializedName;


public class Socials{

	@SerializedName("youtube")
	private String youtube;

	@SerializedName("facebook")
	private String facebook;

	@SerializedName("linkedin")
	private String linkedin;

	@SerializedName("instagram")
	private String instagram;

	public void setYoutube(String youtube){
		this.youtube = youtube;
	}

	public String getYoutube(){
		return youtube;
	}

	public void setFacebook(String facebook){
		this.facebook = facebook;
	}

	public String getFacebook(){
		return facebook;
	}

	public void setLinkedin(String linkedin){
		this.linkedin = linkedin;
	}

	public String getLinkedin(){
		return linkedin;
	}

	public void setInstagram(String instagram){
		this.instagram = instagram;
	}

	public String getInstagram(){
		return instagram;
	}
}