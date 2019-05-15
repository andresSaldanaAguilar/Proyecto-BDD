package com.escom.miniterminos.entities;

public class MiembroUserPortBean {
	private Integer idUsuario;
	private Integer idPortafolio;
	
	
	public MiembroUserPortBean() {
		super();
	}


	public MiembroUserPortBean(Integer idUsuario, Integer idPortafolio) {
		super();
		this.idUsuario = idUsuario;
		this.idPortafolio = idPortafolio;
	}


	public Integer getIdUsuario() {
		return idUsuario;
	}


	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}


	public Integer getIdPortafolio() {
		return idPortafolio;
	}


	public void setIdPortafolio(Integer idPortafolio) {
		this.idPortafolio = idPortafolio;
	}


	@Override
	public String toString() {
		return "MiembroUserPortBean [idUsuario=" + idUsuario + ", idPortafolio=" + idPortafolio + "]";
	}
	
	
}
