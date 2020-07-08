package com.application.kreditimpian.Model.ModelNotifikasiProducts;

import com.google.gson.annotations.SerializedName;

public class Images{

	@SerializedName("side")
	private Side side;

	@SerializedName("top")
	private Top top;

	@SerializedName("main")
	private Main main;

	@SerializedName("front")
	private Front front;

	@SerializedName("detail")
	private Detail detail;

	public void setSide(Side side){
		this.side = side;
	}

	public Side getSide(){
		return side;
	}

	public void setTop(Top top){
		this.top = top;
	}

	public Top getTop(){
		return top;
	}

	public void setMain(Main main){
		this.main = main;
	}

	public Main getMain(){
		return main;
	}

	public void setFront(Front front){
		this.front = front;
	}

	public Front getFront(){
		return front;
	}

	public void setDetail(Detail detail){
		this.detail = detail;
	}

	public Detail getDetail(){
		return detail;
	}
}