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
    public static final String GET_SECUENCIA_BY_LICENCIATURA = "http://" + URL + FOLDER + "get_all_secuencias_by_lic.php";
    public static final String GET_GRUPO_BY_LICENCIATURA = "http://" + URL + FOLDER + "get_all_secuencias.php";
    public static final String GET_HORARIO = "http://" + URL + FOLDER + "get_horario.php";
    public static final String VALIDAR_LOGIN_USUARIO = "http://" + URL + FOLDER + "validar_login_usuario.php";

    /**
     * Query de Base de datos Local
     */
    public static final String sqlTablaAlumno = "CREATE TABLE IF NOT EXISTS Alumno (id_boleta INTEGER, nombre TEXT, apellido_paterno TEXT, apellido_materno TEXT, licenciatura TEXT)";
    public static final String sqlTablaGrupo = "CREATE TABLE IF NOT EXISTS Grupo (id_grupo TEXT, id_secuencia TEXT, nombre_uap TEXT, nombre_profesor TEXT, apellido_paterno_profesor TEXT, apellido_materno_profesor TEXT, id_dia TEXT, hora_inicio TEXT, hora_fin TEXT, nombre_edificio TEXT, salon TEXT, dir_host TEXT NULL, password TEXT NULL)";
    /**
     * Insert Tablas de BD
     */
    public static final String sqlInsertGrupo = "INSERT INTO Grupo (id_grupo, id_secuencia, nombre_uap, nombre_profesor, apellido_paterno_profesor, apellido_materno_profesor, id_dia, hora_inicio, hora_fin, nombre_edificio, salon, dir_host, password) ";
    /**
     * select deTablas de BD
     */
    public static final String sqlSelectGrupo = "SELECT id_secuencia, nombre_uap, nombre_profesor, apellido_paterno_profesor, apellido_materno_profesor, id_dia, hora_inicio, hora_fin, nombre_edificio, salon FROM Grupo WHERE id_dia = ?";
}
