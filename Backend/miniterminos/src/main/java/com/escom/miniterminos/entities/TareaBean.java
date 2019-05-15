package com.escom.miniterminos.entities;

public class TareaBean {
	private Integer idTarea;
	private String Swag;
	private String FechaMod;
	private Integer ValorNegocios;
	private String Titulo;
	private String Prioridad;
	private String Estado;
	private String FechaCreacion;
	private String TipoTarea;
	private String Progreso;
	private Integer idColumna;
	private Integer idTablero;
	private Integer idUsuario;
	private String Bloqueo;
	
	
	public TareaBean() {
		super();
	}


	public TareaBean(Integer idTarea, String swag, String fechaMod, Integer valorNegocios, String titulo,
			String prioridad, String estado, String fechaCreacion, String tipoTarea, String progreso, Integer idColumna,
			Integer idTablero, Integer idUsuario, String bloqueo) {
		super();
		this.idTarea = idTarea;
		Swag = swag;
		FechaMod = fechaMod;
		ValorNegocios = valorNegocios;
		Titulo = titulo;
		Prioridad = prioridad;
		Estado = estado;
		FechaCreacion = fechaCreacion;
		TipoTarea = tipoTarea;
		Progreso = progreso;
		this.idColumna = idColumna;
		this.idTablero = idTablero;
		this.idUsuario = idUsuario;
		Bloqueo = bloqueo;
	}


	public Integer getIdTarea() {
		return idTarea;
	}


	public void setIdTarea(Integer idTarea) {
		this.idTarea = idTarea;
	}


	public String getSwag() {
		return Swag;
	}


	public void setSwag(String swag) {
		Swag = swag;
	}


	public String getFechaMod() {
		return FechaMod;
	}


	public void setFechaMod(String fechaMod) {
		FechaMod = fechaMod;
	}


	public Integer getValorNegocios() {
		return ValorNegocios;
	}


	public void setValorNegocios(Integer valorNegocios) {
		ValorNegocios = valorNegocios;
	}


	public String getTitulo() {
		return Titulo;
	}


	public void setTitulo(String titulo) {
		Titulo = titulo;
	}


	public String getPrioridad() {
		return Prioridad;
	}


	public void setPrioridad(String prioridad) {
		Prioridad = prioridad;
	}


	public String getEstado() {
		return Estado;
	}


	public void setEstado(String estado) {
		Estado = estado;
	}


	public String getFechaCreacion() {
		return FechaCreacion;
	}


	public void setFechaCreacion(String fechaCreacion) {
		FechaCreacion = fechaCreacion;
	}


	public String getTipoTarea() {
		return TipoTarea;
	}


	public void setTipoTarea(String tipoTarea) {
		TipoTarea = tipoTarea;
	}


	public String getProgreso() {
		return Progreso;
	}


	public void setProgreso(String progreso) {
		Progreso = progreso;
	}


	public Integer getIdColumna() {
		return idColumna;
	}


	public void setIdColumna(Integer idColumna) {
		this.idColumna = idColumna;
	}


	public Integer getIdTablero() {
		return idTablero;
	}


	public void setIdTablero(Integer idTablero) {
		this.idTablero = idTablero;
	}


	public Integer getIdUsuario() {
		return idUsuario;
	}


	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}


	public String getBloqueo() {
		return Bloqueo;
	}


	public void setBloqueo(String bloqueo) {
		Bloqueo = bloqueo;
	}


	@Override
	public String toString() {
		return "TareaBean [idTarea=" + idTarea + ", Swag=" + Swag + ", FechaMod=" + FechaMod + ", ValorNegocios="
				+ ValorNegocios + ", Titulo=" + Titulo + ", Prioridad=" + Prioridad + ", Estado=" + Estado
				+ ", FechaCreacion=" + FechaCreacion + ", TipoTarea=" + TipoTarea + ", Progreso=" + Progreso
				+ ", idColumna=" + idColumna + ", idTablero=" + idTablero + ", idUsuario=" + idUsuario + ", Bloqueo="
				+ Bloqueo + "]";
	}
	
	
	
	
	

}
