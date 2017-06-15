package com.oscod.arielsv.reparapcapp.Users;

/**
 * Created by ArielSV on 07/06/17.
 */

public class Usuario {

    String nombre;
    String apellidop;
    String apellidom;
    String telefono;
    String ciudad;
    String email;
    int id;


    public Usuario(String nombre, String apellidop, String apellidom, String telefono, String ciudad, String email,int id) {
        this.nombre = nombre;
        this.apellidop = apellidop;
        this.apellidom = apellidom;
        this.telefono = telefono;
        this.ciudad = ciudad;
        this.email = email;
        this.id = id;
    }

    public Usuario(){

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidop() {
        return apellidop;
    }

    public void setApellidop(String apellidop) {
        this.apellidop = apellidop;
    }

    public String getApellidom() {
        return apellidom;
    }

    public void setApellidom(String apellidom) {
        this.apellidom = apellidom;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

  /*  @Override
    public String toString() {
        return "Usuarios{" +
                "apellido='" + apellidop + '\'' +
                ", comentarios='" + apellidom + '\'' +
                ", nombre=" + nombre +
                ", puesto=" + telefono +
                '}';
    } */




}
