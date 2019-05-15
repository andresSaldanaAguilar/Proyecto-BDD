package com.escom.miniterminos.entities;

public class TableroBean {
	private Integer idTablero;
	private String Nombre;
	private Integer idPortafolio;
	
	
	public TableroBean() {
		super();
	}


	public TableroBean(Integer idTablero, String nombre, Integer idPortafolio) {
		super();
		this.idTablero = idTablero;
		Nombre = nombre;
		this.idPortafolio = idPortafolio;
	}


	public Integer getIdTablero() {
		return idTablero;
	}


	public void setIdTablero(Integer idTablero) {
		this.idTablero = idTablero;
	}


	public String getNombre() {
		return Nombre;
	}


	public void setNombre(String nombre) {
		Nombre = nombre;
	}


	public Integer getIdPortafolio() {
		return idPortafolio;
	}


	public void setIdPortafolio(Integer idPortafolio) {
		this.idPortafolio = idPortafolio;
	}


	@Override
	public String toString() {
		return "TableroBean [idTablero=" + idTablero + ", Nombre=" + Nombre + ", idPortafolio=" + idPortafolio + "]";
	}
	
	
	
}
