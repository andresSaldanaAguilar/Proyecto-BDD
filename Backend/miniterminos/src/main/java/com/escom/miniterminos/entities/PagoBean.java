package com.escom.miniterminos.entities;

import java.sql.Date;

public class PagoBean {
    private Integer idPago;
    private Integer idcredito;
    private Integer idtienda;
    private Integer idcliente;
    private Date fechaPago;
    private Integer idproducto; 

    public Integer getIdPago() {
        return this.idPago;
    }

    public void setIdPago(Integer idPago) {
        this.idPago = idPago;
    }

    public Integer getidcredito() {
        return this.idcredito;
    }

    public void setidcredito(Integer idcredito) {
        this.idcredito = idcredito;
    }

    public Integer getidtienda() {
        return this.idtienda;
    }

    public void setidtienda(Integer idtienda) {
        this.idtienda = idtienda;
    }

    public Integer getidcliente() {
        return this.idcliente;
    }

    public void setidcliente(Integer idcliente) {
        this.idcliente = idcliente;
    }

    public Date getfechaPago() {
        return this.fechaPago;
    }

    public void setfechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public Integer getidproducto() {
        return this.idproducto;
    }

    public void setidproducto(Integer idproducto) {
        this.idproducto = idproducto;
    }    
}
