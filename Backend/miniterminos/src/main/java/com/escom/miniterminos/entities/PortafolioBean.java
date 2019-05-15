package com.escom.miniterminos.entities;

public class PortafolioBean {
	private Integer idPortafolio;
	private String portafolio;
	private Integer estado;
	private String fechaCreacion;
	private String swag;
	private Integer idUsuario;
	
	
	public PortafolioBean() {
		super();
	}


	public PortafolioBean(Integer idPortafolio, String portafolio, Integer estado, String fechaCreacion, String swag,
			Integer idUsuario) {
		super();
		this.idPortafolio = idPortafolio;
		this.portafolio = portafolio;
		this.estado = estado;
		this.fechaCreacion = fechaCreacion;
		this.swag = swag;
		this.idUsuario = idUsuario;
	}


	public Integer getIdPortafolio() {
		return idPortafolio;
	}


	public void setIdPortafolio(Integer idPortafolio) {
		this.idPortafolio = idPortafolio;
	}


	public String getPortafolio() {
		return portafolio;
	}


	public void setPortafolio(String portafolio) {
		this.portafolio = portafolio;
	}


	public Integer getEstado() {
		return estado;
	}


	public void setEstado(Integer estado) {
		this.estado = estado;
	}


	public String getFechaCreacion() {
		return fechaCreacion;
	}


	public void setFechaCreacion(String fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}


	public String getSwag() {
		return swag;
	}


	public void setSwag(String swag) {
		this.swag = swag;
	}


	public Integer getIdUsuario() {
		return idUsuario;
	}


	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}


	@Override
	public String toString() {
		return "PortafolioBean [idPortafolio=" + idPortafolio + ", portafolio=" + portafolio + ", estado=" + estado
				+ ", fechaCreacion=" + fechaCreacion + ", swag=" + swag + ", idUsuario=" + idUsuario + "]";
	}
	
	
}