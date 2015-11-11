



package com.belu.upiicsamath.model;

/**
 * Created by Luis on 11/10/2015.
 */
public class Datos {
    private String uap;
    private String edificio;
    private String salon;
    private String dweek;
    private String hinicio;
    private String hfin;
    private String secuencia;

    public String getProfesor() {
        return profesor;
    }

    public void setProfesor(String profesor) {
        this.profesor = profesor;
    }

    private String profesor;
    private int id;

    public Datos(){
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Datos(String uap, String edificio, String salon, String dweek, String hinicio, String hfin, String secuencia, int id, String Profesor) {
        this.uap = uap;
        this.edificio = edificio;
        this.salon = salon;
        this.dweek = dweek;
        this.hinicio = hinicio;
        this.hfin = hfin;
        this.secuencia = secuencia;
        this.id = id;
    }

    public String getUap() {
        return uap;
    }

    public String getEdificio() {
        return edificio;
    }

    public String getSalon() {
        return salon;
    }

    public String getDweek() {
        return dweek;
    }

    public String getHinicio() {
        return hinicio;
    }

    public String getHfin() {
        return hfin;
    }

    public String getSecuencia() {

        return secuencia;
    }

    public void setUap(String uap) {
        this.uap = uap;
    }

    public void setEdificio(String edificio) {
        this.edificio = edificio;
    }

    public void setSalon(String salon) {
        this.salon = salon;
    }

    public void setDweek(String dweek) {
        this.dweek = dweek;
    }

    public void setHinicio(String hinicio) {
        this.hinicio = hinicio;
    }

    public void setHfin(String hfin) {
        this.hfin = hfin;
    }

    public void setSecuencia(String secuencia) {
        this.secuencia = secuencia;
    }

}
