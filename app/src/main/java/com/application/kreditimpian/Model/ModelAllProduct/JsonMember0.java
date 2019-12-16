package com.application.kreditimpian.Model.ModelAllProduct;

import com.google.gson.annotations.SerializedName;




public class JsonMember0{

	@SerializedName("emails")
	private String emails;

	@SerializedName("messengers")
	private Messengers messengers;

	@SerializedName("image")
	private String image;

	@SerializedName("id_company")
	private String idCompany;

	@SerializedName("name")
	private String name;

	@SerializedName("photo")
	private String photo;

	@SerializedName("id")
	private String id;

	@SerializedName("socials")
	private Socials socials;

	@SerializedName("job_title")
	private String jobTitle;

	public void setEmails(String emails){
		this.emails = emails;
	}

	public String getEmails(){
		return emails;
	}

	public void setMessengers(Messengers messengers){
		this.messengers = messengers;
	}

	public Messengers getMessengers(){
		return messengers;
	}

	public void setImage(String image){
		this.image = image;
	}

	public String getImage(){
		return image;
	}

	public void setIdCompany(String idCompany){
		this.idCompany = idCompany;
	}

	public String getIdCompany(){
		return idCompany;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setPhoto(String photo){
		this.photo = photo;
	}

	public String getPhoto(){
		return photo;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setSocials(Socials socials){
		this.socials = socials;
	}

	public Socials getSocials(){
		return socials;
	}

	public void setJobTitle(String jobTitle){
		this.jobTitle = jobTitle;
	}

	public String getJobTitle(){
		return jobTitle;
	}
}