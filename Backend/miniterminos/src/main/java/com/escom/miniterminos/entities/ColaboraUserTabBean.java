package com.escom.miniterminos.entities;


public class ColaboraUserTabBean {
	private Integer idUsuario;
	private Integer idTablero;
	
	
	public ColaboraUserTabBean() {
		super();
	}


	public ColaboraUserTabBean(Integer idUsuario, Integer idTablero) {
		super();
		this.idUsuario = idUsuario;
		this.idTablero = idTablero;
	}


	public Integer getIdUsuario() {
		return idUsuario;
	}


	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}


	public Integer getIdTablero() {
		return idTablero;
	}


	public void setIdTablero(Integer idTablero) {
		this.idTablero = idTablero;
	}


	@Override
	public String toString() {
		return "ColaboraUserTabBean [idUsuario=" + idUsuario + ", idTablero=" + idTablero + "]";
	}
	
	
	
	
}
