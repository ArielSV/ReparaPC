package com.oscod.arielsv.reparapcapp.Equipos;

/**
 * Created by ariel on 18/06/17.
 */

public class Equipos {
    String descripcion;
    String equipo;
    String estado;
    String id;
    String idusuario;
    String marca;
    String observaciones;
    String password;

    public Equipos() {}
    public Equipos(String descripcion, String equipo, String estado, String id, String idusuario, String marca, String observaciones, String password) {
        this.descripcion = descripcion;
        this.equipo = equipo;
        this.estado = estado;
        this.id = id;
        this.idusuario = idusuario;
        this.marca = marca;
        this.observaciones = observaciones;
        this.password = password;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEquipo() {
        return equipo;
    }

    public void setEquipo(String equipo) {
        this.equipo = equipo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(String idusuario) {
        this.idusuario = idusuario;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
