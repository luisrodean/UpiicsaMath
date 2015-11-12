



package com.belu.upiicsamath.model;

/**
 * Created by Luis on 11/10/2015.
 */
public class ConsultaHorario {
    private String id_grupo;
    private String id_secuencia;
    private String nombre_uap;
    private String nombre_profesor;
    private String apellido_paterno_profesor;
    private String apellido_materno_profesor;
    private String id_dia;
    private String hora_inicio;
    private String hora_fin;
    private String nombre_edificio;
    private String salon;
    private String dir_host;
    private String password;

    public ConsultaHorario() {
    }

    public ConsultaHorario(String id_grupo, String id_secuencia, String nombre_uap, String nombre_profesor, String apellido_paterno_profesor, String apellido_materno_profesor, String id_dia, String hora_inicio, String hora_fin, String nombre_edificio, String salon, String dir_host, String password) {
        this.id_grupo = id_grupo;
        this.id_secuencia = id_secuencia;
        this.nombre_uap = nombre_uap;
        this.nombre_profesor = nombre_profesor;
        this.apellido_paterno_profesor = apellido_paterno_profesor;
        this.apellido_materno_profesor = apellido_materno_profesor;
        this.id_dia = id_dia;
        this.hora_inicio = hora_inicio;
        this.hora_fin = hora_fin;
        this.nombre_edificio = nombre_edificio;
        this.salon = salon;
        this.dir_host = dir_host;
        this.password = password;
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

    public String getId_dia() {
        return id_dia;
    }

    public void setId_dia(String id_dia) {
        this.id_dia = id_dia;
    }

    public String getHora_inicio() {
        return hora_inicio;
    }

    public void setHora_inicio(String hora_inicio) {
        this.hora_inicio = hora_inicio;
    }

    public String getHora_fin() {
        return hora_fin;
    }

    public void setHora_fin(String hora_fin) {
        this.hora_fin = hora_fin;
    }

    public String getNombre_edificio() {
        return nombre_edificio;
    }

    public void setNombre_edificio(String nombre_edificio) {
        this.nombre_edificio = nombre_edificio;
    }

    public String getSalon() {
        return salon;
    }

    public void setSalon(String salon) {
        this.salon = salon;
    }

    public String getDir_host() {
        return dir_host;
    }

    public void setDir_host(String dir_host) {
        this.dir_host = dir_host;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}