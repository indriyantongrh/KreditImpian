package com.application.kreditimpian.Model.ModelAllProduct;

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

	@SerializedName("insurance")
	private String insurance;

	@SerializedName("featured")
	private String featured;

	@SerializedName("allow_pingbacks")
	private String allowPingbacks;

	@SerializedName("allow_comments")
	private String allowComments;

	@SerializedName("length")
	private String length;

	@SerializedName("width")
	private String width;

	@SerializedName("excerpt")
	private String excerpt;

	@SerializedName("height")
	private String height;

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

	public void setInsurance(String insurance){
		this.insurance = insurance;
	}

	public String getInsurance(){
		return insurance;
	}

	public void setFeatured(String featured){
		this.featured = featured;
	}

	public String getFeatured(){
		return featured;
	}

	public void setAllowPingbacks(String allowPingbacks){
		this.allowPingbacks = allowPingbacks;
	}

	public String getAllowPingbacks(){
		return allowPingbacks;
	}

	public void setAllowComments(String allowComments){
		this.allowComments = allowComments;
	}

	public String getAllowComments(){
		return allowComments;
	}

	public void setLength(String length){
		this.length = length;
	}

	public String getLength(){
		return length;
	}

	public void setWidth(String width){
		this.width = width;
	}

	public String getWidth(){
		return width;
	}

	public void setExcerpt(String excerpt){
		this.excerpt = excerpt;
	}

	public String getExcerpt(){
		return excerpt;
	}

	public void setHeight(String height){
		this.height = height;
	}

	public String getHeight(){
		return height;
	}
}