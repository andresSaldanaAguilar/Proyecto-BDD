package com.escom.miniterminos.entities;

public class CreditoBean {
    private Integer idCredito;
    private String nombre;
    private String monto;

    public Integer getIdCredito() {
        return this.idCredito;
    }

    public void setIdCredito(Integer idCredito) {
        this.idCredito = idCredito;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMonto() {
        return this.monto;
    }

    public void setMonto(String monto) {
        this.monto = monto;
    }
}
