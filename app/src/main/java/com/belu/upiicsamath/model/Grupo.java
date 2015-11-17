package com.belu.upiicsamath.model;

/**
 * Created by Luis on 13/11/2015.
 */
public class Grupo {
    private String id_grupo;
    private String id_secuencia;
    private String nombre_uap;
    private String nombre_profesor;
    private String apellido_paterno_profesor;
    private String apellido_materno_profesor;

    public Grupo() {}

    public Grupo(String id_grupo, String id_secuencia, String nombre_uap, String nombre_profesor, String apellido_paterno_profesor, String apellido_materno_profesor) {
        this.id_grupo = id_grupo;
        this.id_secuencia = id_secuencia;
        this.nombre_uap = nombre_uap;
        this.nombre_profesor = nombre_profesor;
        this.apellido_paterno_profesor = apellido_paterno_profesor;
        this.apellido_materno_profesor = apellido_materno_profesor;
    }

    public String getId_grupo() {
        return id_grupo;
    }

    public void setId_grupo(String id_grupo) {
        this.id_grupo = id_grupo;
    }

    public String getId_secuencia() {
        return id_secuencia;
    }

    public void setId_secuencia(String id_secuencia) {
        this.id_secuencia = id_secuencia;
    }

    public String getNombre_uap() {
        return nombre_uap;
    }

    public void setNombre_uap(String nombre_uap) {
        this.nombre_uap = nombre_uap;
    }

    public String getNombre_profesor() {
        return nombre_profesor;
    }

    public void setNombre_profesor(String nombre_profesor) {
        this.nombre_profesor = nombre_profesor;
    }

    public String getApellido_paterno_profesor() {
        return apellido_paterno_profesor;
    }

    public void setApellido_paterno_profesor(String apellido_paterno_profesor) {
        this.apellido_paterno_profesor = apellido_paterno_profesor;
    }

    public String getApellido_materno_profesor() {
        return apellido_materno_profesor;
    }

    public void setApellido_materno_profesor(String apellido_materno_profesor) {
        this.apellido_materno_profesor = apellido_materno_profesor;
    }
}
