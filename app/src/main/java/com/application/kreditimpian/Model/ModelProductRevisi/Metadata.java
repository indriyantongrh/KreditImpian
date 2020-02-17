package com.application.kreditimpian.Model.ModelProductRevisi;


import com.google.gson.annotations.SerializedName;


public class Metadata{

	@SerializedName("sharing_button")
	private String sharingButton;

	@SerializedName("length_value")
	private String lengthValue;

	@SerializedName("like_button")
	private String likeButton;

	@SerializedName("weight_value")
	private String weightValue;

	@SerializedName("weight")
	private String weight;

	@SerializedName("height_value")
	private String heightValue;

	@SerializedName("width_value")
	private String widthValue;

	@SerializedName("tags")
	private String tags;

	public void setSharingButton(String sharingButton){
		this.sharingButton = sharingButton;
	}

	public String getSharingButton(){
		return sharingButton;
	}

	public void setLengthValue(String lengthValue){
		this.lengthValue = lengthValue;
	}

	public String getLengthValue(){
		return lengthValue;
	}

	public void setLikeButton(String likeButton){
		this.likeButton = likeButton;
	}

	public String getLikeButton(){
		return likeButton;
	}

	public void setWeightValue(String weightValue){
		this.weightValue = weightValue;
	}

	public String getWeightValue(){
		return weightValue;
	}

	public void setWeight(String weight){
		this.weight = weight;
	}

	public String getWeight(){
		return weight;
	}

	public void setHeightValue(String heightValue){
		this.heightValue = heightValue;
	}

	public String getHeightValue(){
		return heightValue;
	}

	public void setWidthValue(String widthValue){
		this.widthValue = widthValue;
	}

	public String getWidthValue(){
		return widthValue;
	}

	public void setTags(String tags){
		this.tags = tags;
	}

	public String getTags(){
		return tags;
	}
}