package com.application.kreditimpian.Model.ModelNotifikasi;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;


public class Installment implements Parcelable {

	@SerializedName("0")
	private String jsonMember0;

	@SerializedName("1")
	private String jsonMember1;

	@SerializedName("2")
	private String jsonMember2;

	@SerializedName("3")
	private String jsonMember3;

	@SerializedName("4")
	private String jsonMember4;

	@SerializedName("5")
	private String jsonMember5;

	@SerializedName("6")
	private String jsonMember6;

	public void setJsonMember0(String jsonMember0){
		this.jsonMember0 = jsonMember0;
	}

	public String getJsonMember0(){
		return jsonMember0;
	}

	public void setJsonMember1(String jsonMember1){
		this.jsonMember1 = jsonMember1;
	}

	public String getJsonMember1(){
		return jsonMember1;
	}

	public void setJsonMember2(String jsonMember2){
		this.jsonMember2 = jsonMember2;
	}

	public String getJsonMember2(){
		return jsonMember2;
	}

	public void setJsonMember3(String jsonMember3){
		this.jsonMember3 = jsonMember3;
	}

	public String getJsonMember3(){
		return jsonMember3;
	}

	public void setJsonMember4(String jsonMember4){
		this.jsonMember4 = jsonMember4;
	}

	public String getJsonMember4(){
		return jsonMember4;
	}

	public void setJsonMember5(String jsonMember5){
		this.jsonMember5 = jsonMember5;
	}

	public String getJsonMember5(){
		return jsonMember5;
	}

	public void setJsonMember6(String jsonMember6){
		this.jsonMember6 = jsonMember6;
	}

	public String getJsonMember6(){
		return jsonMember6;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.jsonMember0);
		dest.writeString(this.jsonMember1);
		dest.writeString(this.jsonMember2);
		dest.writeString(this.jsonMember3);
		dest.writeString(this.jsonMember4);
		dest.writeString(this.jsonMember5);
		dest.writeString(this.jsonMember6);
	}

	public Installment() {
	}

	protected Installment(Parcel in) {
		this.jsonMember0 = in.readString();
		this.jsonMember1 = in.readString();
		this.jsonMember2 = in.readString();
		this.jsonMember3 = in.readString();
		this.jsonMember4 = in.readString();
		this.jsonMember5 = in.readString();
		this.jsonMember6 = in.readString();
	}

	public static final Parcelable.Creator<Installment> CREATOR = new Parcelable.Creator<Installment>() {
		@Override
		public Installment createFromParcel(Parcel source) {
			return new Installment(source);
		}

		@Override
		public Installment[] newArray(int size) {
			return new Installment[size];
		}
	};
}