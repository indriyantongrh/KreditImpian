package com.application.kreditimpian.Model.ModelOngkoskirim;


import com.google.gson.annotations.SerializedName;


public class Data{

	@SerializedName("jne")
	private Jne jne;

	@SerializedName("tiki")
	private Tiki tiki;

	@SerializedName("pos")
	private Pos pos;

	public void setJne(Jne jne){
		this.jne = jne;
	}

	public Jne getJne(){
		return jne;
	}

	public void setTiki(Tiki tiki){
		this.tiki = tiki;
	}

	public Tiki getTiki(){
		return tiki;
	}

	public void setPos(Pos pos){
		this.pos = pos;
	}

	public Pos getPos(){
		return pos;
	}
}