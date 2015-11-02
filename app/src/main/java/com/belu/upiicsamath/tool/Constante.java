package com.belu.upiicsamath.tool;

/**
 * Created by Luis on 25/10/2015.
 */
public class Constante {
    /**
     * Puerto que utiliza para la conexión.
     */
    private static final String PUERTO_HOST = ":8080";
    /**
     * Dirección IP de webservice
     */
    private static final String IP = "192.168.0.7";
    private static final String FOLDER = "/wsmathupiicsa/";
    /**
     * URLs de Web Service
     */
    public static final String OBTENER_USUARIO_POR_ID = "http://" + IP + PUERTO_HOST + FOLDER + "obtener_alumno_por_id.php";
    public static final String VALIDAR_LOGIN_USUARIO = "http://" + IP + PUERTO_HOST + FOLDER + "validar_login_usuario.php";

}
