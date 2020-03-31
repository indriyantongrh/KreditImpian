package com.application.kreditimpian.Model;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;


public class Shipping implements Parcelable {

	@SerializedName("send")
	private Send send;

	public void setSend(Send send){
		this.send = send;
	}

	public Send getSend(){
		return send;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeParcelable(this.send, flags);
	}

	public Shipping() {
	}

	protected Shipping(Parcel in) {
		this.send = in.readParcelable(Send.class.getClassLoader());
	}

	public static final Parcelable.Creator<Shipping> CREATOR = new Parcelable.Creator<Shipping>() {
		@Override
		public Shipping createFromParcel(Parcel source) {
			return new Shipping(source);
		}

		@Override
		public Shipping[] newArray(int size) {
			return new Shipping[size];
		}
	};
}