package com.belu.upiicsamath.model;

/**
 * Created by Luis on 25/10/2015.
 */
public class Alumno{

    private int id_boleta;
    private String nombre;
    private String apellido_paterno;
    private String apellido_materno;
    private String licenciatura;

    public Alumno(){}

    public Alumno(int id_boleta, String nombre, String apellido_paterno, String apellido_materno, String licenciatura) {
        this.id_boleta = id_boleta;
        this.nombre = nombre;
        this.apellido_paterno = apellido_paterno;
        this.apellido_materno = apellido_materno;
        this.licenciatura = licenciatura;
    }


    public int getId_boleta() {
        return id_boleta;
    }

    public void setId_boleta(int id_boleta) {
        this.id_boleta = id_boleta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido_paterno() {
        return apellido_paterno;
    }

    public void setApellido_paterno(String apellido_paterno) {
        this.apellido_paterno = apellido_paterno;
    }

    public String getApellido_materno() {
        return apellido_materno;
    }

    public void setApellido_materno(String apellido_materno) {
        this.apellido_materno = apellido_materno;
    }

    public String getLicenciatura() {
        return licenciatura;
    }

    public void setLicenciatura(String licenciatura) {
        this.licenciatura = licenciatura;
    }

}
