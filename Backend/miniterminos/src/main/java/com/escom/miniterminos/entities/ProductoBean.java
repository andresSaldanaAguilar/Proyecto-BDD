package com.escom.miniterminos.entities;

public class ProductoBean {
    private Integer idProducto;
    private String nombre;
    private String descripcion;
    private Double precioUnitario;
    private String marca;
    private Integer idSubcategoria; 

    public Integer getIdProducto() {
        return this.idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getPrecioUnitario() {
        return this.precioUnitario;
    }

    public void setPrecioUnitario(Double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public String getMarca() {
        return this.marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Integer getIdSubcategoria() {
        return this.idSubcategoria;
    }

    public void setIdSubcategoria(Integer idSubcategoria) {
        this.idSubcategoria = idSubcategoria;
    }    
}
