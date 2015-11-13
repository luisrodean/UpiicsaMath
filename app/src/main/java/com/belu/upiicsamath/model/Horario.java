



package com.belu.upiicsamath.model;

/**
 * Created by Luis on 11/10/2015.
 */
public class Horario {
    private String id_dia;
    private String hora_inicio;
    private String hora_fin;
    private String nombre_edificio;
    private String salon;
    private String dir_host;
    private String password;


    public Horario() {
    }

    public Horario(String id_dia, String hora_inicio, String hora_fin, String nombre_edificio, String salon, String dir_host, String password) {
        this.id_dia = id_dia;
        this.hora_inicio = hora_inicio;
        this.hora_fin = hora_fin;
        this.nombre_edificio = nombre_edificio;
        this.salon = salon;
        this.dir_host = dir_host;
        this.password = password;
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