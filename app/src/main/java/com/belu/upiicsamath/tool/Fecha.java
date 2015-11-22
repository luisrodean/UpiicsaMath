package com.belu.upiicsamath.tool;

import android.text.format.Time;

import java.util.Date;

/**
 * Created by Luis on 02/11/2015.
 */
public class Fecha extends Date{

    private int dia;
    private int diaSemana;
    private int mes;
    private int anio;
    private int hora;
    private int min;
    private int seg;

    public Fecha (){
        hora_actual();
    }

    public String getFecha(){
        return dia + "/" + mes + "/" +anio;
    }

    public int getDia() {
        return dia;
    }

    public String getDiaSemana() {
        String dia = null;
        switch (diaSemana){
            case 0:
                dia = "Domingo";
            break;
            case 1:
                dia = "Lunes";
            break;
            case 2:
                dia = "Martes";
                break;
            case 3:
                dia = "Miércoles";
                break;
            case 4:
                dia = "Jueves";
                break;
            case 5:
                dia = "Viernes";
                break;
            case 6:
                dia = "Sabado";
                break;
        }

        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public int getHora() {
        return hora;
    }

    public void setHora(int hora) {
        this.hora = hora;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getSeg() {
        return seg;
    }

    public void setSeg(int seg) {
        this.seg = seg;
    }

    private void hora_actual(){
        //https://www.youtube.com/watch?v=I2eYBtLWGzc
        Time today = new Time(Time.getCurrentTimezone());
        today.setToNow();
        dia = today.monthDay;             // Day of the month (1-31)
        diaSemana = today.weekDay;
        mes = today.month;              // Month (0-11)
        anio = today.year;                // Year
        hora = today.hour;  // Current time
        min = today.minute;
        seg = today.second;

    }

}
