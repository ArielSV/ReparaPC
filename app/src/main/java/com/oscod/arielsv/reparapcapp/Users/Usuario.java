package com.oscod.arielsv.reparapcapp.Users;

import java.io.Serializable;

/**
 * Created by ArielSV on 07/06/17.
 */

public class Usuario {

    String nombre;
    String apaterno;
    String amaterno;
    String telefono;
    String ciudad;
    String email;
    String id;

    public Usuario(String nombre, String apaterno, String amaterno, String telefono, String ciudad, String email, String id) {
        this.nombre = nombre;
        this.apaterno = apaterno;
        this.amaterno = amaterno;
        this.telefono = telefono;
        this.ciudad = ciudad;
        this.email = email;
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApaterno() {
        return apaterno;
    }

    public void setApaterno(String apaterno) {
        this.apaterno = apaterno;
    }

    public String getAmaterno() {
        return amaterno;
    }

    public void setAmaterno(String amaterno) {
        this.amaterno = amaterno;
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

    public String getID() {
        return id;
    }

    public void setID(String id) {
        this.id = id;
    }




    public Usuario(){

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
