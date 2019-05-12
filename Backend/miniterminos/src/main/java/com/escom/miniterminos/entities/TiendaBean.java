package com.escom.miniterminos.entities;

public class TiendaBean {
    private Integer idTienda;
    private String nombre;
    private String direccion;
    private String estado;
    private Integer cp;
    private String tel; 

    public Integer getIdTienda() {
        return this.idTienda;
    }

    public void setIdTienda(Integer idTienda) {
        this.idTienda = idTienda;
    }

    public String getnombre() {
        return this.nombre;
    }

    public void setnombre(String nombre) {
        this.nombre = nombre;
    }

    public String getdireccion() {
        return this.direccion;
    }

    public void setdireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getestado() {
        return this.estado;
    }

    public void setestado(String estado) {
        this.estado = estado;
    }

    public Integer getcp() {
        return this.cp;
    }

    public void setcp(Integer cp) {
        this.cp = cp;
    }

    public String gettel() {
        return this.tel;
    }

    public void settel(String tel) {
        this.tel = tel;
    }    
}