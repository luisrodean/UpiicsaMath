package com.belu.upiicsamath;

import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

/**
 * Created by Betza Ojeda on 23/10/2015.
 */
public class Titular{

    //Se declaran variables
    private String Horario;
    private String Secuencia;
    private String Salon;
    private String Edificio;

    //Se generan setters y getter con ayuda del generador
    public String getUAP() {
        return UAP;
    }

    public void setUAP(String UAP) {
        this.UAP = UAP;
    }

    private String UAP;

    public String getSecuencia() {
        return Secuencia;
    }

    public void setSecuencia(String secuencia) {
        Secuencia = secuencia;
    }

    public String getSalon() {
        return Salon;
    }

    public void setSalon(String salon) {
        Salon = salon;
    }

    public String getEdificio() {
        return Edificio;
    }

    public void setEdificio(String edificio) {
        Edificio = edificio;
    }

    public String getHorario() {
        return Horario;
    }

    public void setHorario(String horario) {
        Horario = horario;
    }



}
