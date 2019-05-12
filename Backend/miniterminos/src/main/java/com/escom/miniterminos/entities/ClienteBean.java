package com.escom.miniterminos.entities;

public class ClienteBean {
    private Integer idCliente;
    private String nombre;
    private String apPaterno;
    private String apMaterno;
    private String sexo;
    private String email; 
    private Double salario;

    public Integer getIdCliente() {
        return this.idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getapPaterno() {
        return this.apPaterno;
    }

    public void setapPaterno(String apPaterno) {
        this.apPaterno = apPaterno;
    }

    public String getapMaterno() {
        return this.apMaterno;
    }

    public void setapMaterno(String apMaterno) {
        this.apMaterno = apMaterno;
    }

    public String getsexo() {
        return this.sexo;
    }

    public void setsexo(String sexo) {
        this.sexo = sexo;
    } 

    public String getemail() {
        return this.email;
    }

    public void setemail(String email) {
        this.email = email;
    } 

    public Double getsalario() {
        return this.salario;
    }

    public void setsalario(Double salario) {
        this.salario = salario;
    } 
}
