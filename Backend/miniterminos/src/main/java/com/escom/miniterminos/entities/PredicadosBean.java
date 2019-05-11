package com.escom.miniterminos.entities;

public class PredicadosBean {
	private String predicados;
	
	public String getPredicados() {	
		return this.predicados;
	}

	public void setPredicados(String predicados) {
		this.predicados = predicados;
	}
	
	public String[] getArrayPredicados() {
		return this.predicados.split("\n");
	}
}
