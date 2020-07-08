package com.application.kreditimpian.Model.ModelNotifikasiV2;

import com.google.gson.annotations.SerializedName;

public class RecordCreateTimestamp{

	@SerializedName("date")
	private String date;

	@SerializedName("timezone")
	private String timezone;

	@SerializedName("timezone_type")
	private Integer timezoneType;

	public void setDate(String date){
		this.date = date;
	}

	public String getDate(){
		return date;
	}

	public void setTimezone(String timezone){
		this.timezone = timezone;
	}

	public String getTimezone(){
		return timezone;
	}

	public void setTimezoneType(Integer timezoneType){
		this.timezoneType = timezoneType;
	}

	public Integer getTimezoneType(){
		return timezoneType;
	}
}