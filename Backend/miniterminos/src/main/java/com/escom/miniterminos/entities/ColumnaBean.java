package com.escom.miniterminos.entities;

public class ColumnaBean {
	private Integer idColumna;
	private String Nombre;
	private Integer NumColumna;
	private Integer LimitesWIP;
	private Integer idTablero;
	
	
	public ColumnaBean() {
		super();
	}


	public ColumnaBean(Integer idColumna, String nombre, Integer numColumna, Integer limitesWIP, Integer idTablero) {
		super();
		this.idColumna = idColumna;
		Nombre = nombre;
		NumColumna = numColumna;
		LimitesWIP = limitesWIP;
		this.idTablero = idTablero;
	}


	public Integer getIdColumna() {
		return idColumna;
	}


	public void setIdColumna(Integer idColumna) {
		this.idColumna = idColumna;
	}


	public String getNombre() {
		return Nombre;
	}


	public void setNombre(String nombre) {
		Nombre = nombre;
	}


	public Integer getNumColumna() {
		return NumColumna;
	}


	public void setNumColumna(Integer numColumna) {
		NumColumna = numColumna;
	}


	public Integer getLimitesWIP() {
		return LimitesWIP;
	}


	public void setLimitesWIP(Integer limitesWIP) {
		LimitesWIP = limitesWIP;
	}


	public Integer getIdTablero() {
		return idTablero;
	}


	public void setIdTablero(Integer idTablero) {
		this.idTablero = idTablero;
	}


	@Override
	public String toString() {
		return "ColumnaBean [idColumna=" + idColumna + ", Nombre=" + Nombre + ", NumColumna=" + NumColumna
				+ ", LimitesWIP=" + LimitesWIP + ", idTablero=" + idTablero + "]";
	}
	
	

}
