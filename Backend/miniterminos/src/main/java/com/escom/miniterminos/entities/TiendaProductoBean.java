package com.escom.miniterminos.entities;

public class TiendaProductoBean {
    private Integer idtienda;
    private Integer idproducto;
    private Integer noExistencias;

    public Integer getidtienda() {
        return this.idtienda;
    }

    public void setidtienda(Integer idtienda) {
        this.idtienda = idtienda;
    }

    public Integer getidproducto() {
        return this.idproducto;
    }

    public void setidproducto(Integer idproducto) {
        this.idproducto = idproducto;
    }

    public Integer getnoExistencias() {
        return this.noExistencias;
    }

    public void setnoExistencias(Integer noExistencias) {
        this.noExistencias = noExistencias;
    }
}