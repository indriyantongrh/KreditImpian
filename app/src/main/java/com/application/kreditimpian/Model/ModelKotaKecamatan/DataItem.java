package com.application.kreditimpian.Model.ModelKotaKecamatan;


import com.google.gson.annotations.SerializedName;


public class DataItem{

	@SerializedName("id_geodirectory_kecamatan")
	private String idGeodirectoryKecamatan;

	@SerializedName("nama_kecamatan")
	private String namaKecamatan;

	@SerializedName("id_geodirectory_kota")
	private String idGeodirectoryKota;

	@SerializedName("nama_kota")
	private String namaKota;

	public void setIdGeodirectoryKecamatan(String idGeodirectoryKecamatan){
		this.idGeodirectoryKecamatan = idGeodirectoryKecamatan;
	}

	public String getIdGeodirectoryKecamatan(){
		return idGeodirectoryKecamatan;
	}

	public void setNamaKecamatan(String namaKecamatan){
		this.namaKecamatan = namaKecamatan;
	}

	public String getNamaKecamatan(){
		return namaKecamatan;
	}

	public void setIdGeodirectoryKota(String idGeodirectoryKota){
		this.idGeodirectoryKota = idGeodirectoryKota;
	}

	public String getIdGeodirectoryKota(){
		return idGeodirectoryKota;
	}

	public void setNamaKota(String namaKota){
		this.namaKota = namaKota;
	}

	public String getNamaKota(){
		return namaKota;
	}
}