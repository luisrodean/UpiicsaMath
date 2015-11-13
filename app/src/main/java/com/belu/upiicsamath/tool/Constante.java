package com.belu.upiicsamath.tool;

/**
 * Created by Luis on 25/10/2015.
 */
public class Constante {
    //Calve de Mensajeria google
    private static final String CLAVE = "AIzaSyB979auEd3qtlumRXcdphBM8mpH8JStuXk";
    /**
     * Puerto que utiliza para la conexión.
     */
    private static final String PUERTO_HOST = ":8080";
    /**
     * Dirección IP de webservice
     */
    private static final String URL = "upiicsa.hol.es/";
    private static final String FOLDER = "upiicsa_web_service/";
    /**
     * URLs de Web Service
     */
    public static final String GET_HORARIO = "http://" + URL + FOLDER + "get_horario.php";
    public static final String VALIDAR_LOGIN_USUARIO = "http://" + URL + FOLDER + "validar_login_usuario.php";

}
