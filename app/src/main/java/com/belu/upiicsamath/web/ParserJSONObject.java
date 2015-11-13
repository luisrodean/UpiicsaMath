package com.belu.upiicsamath.web;

import android.app.Activity;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.belu.upiicsamath.model.Alumno;
import com.belu.upiicsamath.model.ConsultaHorario;
import com.belu.upiicsamath.tool.Constante;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Luis on 12/11/2015.
 */
public class ParserJSONObject {
    private Activity activity;
    private JSONObject response;

    public ParserJSONObject(Activity activity) {
        this.activity = activity;
    }

    private void setResponse(JSONObject response) {
        this.response = response;
    }

    /**
     * Guarda los cambios de un alumno.
     * <p/>
     * Si está en modo inserción, entonces crea una nueva
     * meta en la base de datos
     */
    public void EnviarDatos(String url, HashMap<String, String> map ) {

        // Crear nuevo objeto Json basado en el mapa
        JSONObject jobject = new JSONObject(map);

        // Depurando objeto Json...
        Log.d("-->objeto JSON creado: ", jobject.toString());

        // Actualizar datos en el servidor
        VolleySingleton.getInstance(activity).addToRequestQueue(
                new JsonObjectRequest(
                        Request.Method.POST,
                        url,
                        jobject,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                // Procesar la respuesta del servidor
                                setResponse(response);
                                procesarRespuesta();
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.d("-->Error Volley: ", error.getMessage());
                            }
                        }

                ) {
                    @Override
                    public Map<String, String> getHeaders() {
                        Map<String, String> headers = new HashMap<String, String>();
                        headers.put("Content-Type", "application/json; charset=utf-8");
                        headers.put("Accept", "application/json");
                        return headers;
                    }

                    @Override
                    public String getBodyContentType() {
                        return "application/json; charset=utf-8" + getParamsEncoding();
                    }
                }
        );


    }



    /**
     * Interpreta los resultados de la respuesta y así
     * realizar las operaciones correspondientes
     */
    private void procesarRespuesta() {
        try {
            ConsultaHorario horario = new ConsultaHorario();
            Gson gson = new Gson();
            // Obtener atributo "estado"
            String estado = response.getString("estado");

            switch (estado) {
                case "EXITO": // EXITO
                    // Obtener array "alumno" Json
                    JSONObject mensaje = response.getJSONObject("horario");
                    // Parsear con Gson y guarda en objeto Alumno
                    horario = gson.fromJson(mensaje.toString(), ConsultaHorario.class);
                    //Se imprime en consola los datos recibidos
                    Log.d("--> ", horario.getId_grupo());
                    Log.d("--> ", horario.getId_secuencia());
                    Log.d("--> ", horario.getNombre_uap());
                    Log.d("--> ", horario.getNombre_profesor());
                    Log.d("--> ", horario.getApellido_paterno_profesor());
                    Log.d("--> ", horario.getApellido_materno_profesor());
                    Log.d("--> ", horario.getId_dia());
                    Log.d("--> ", horario.getHora_inicio());
                    Log.d("--> ", horario.getHora_fin());
                    Log.d("--> ", horario.getNombre_edificio());
                    Log.d("--> ", horario.getSalon());
                    Log.d("--> ", horario.getDir_host());
                    Log.d("--> ", horario.getPassword());
                    break;
                case "FALLIDO": // FALLIDO
                    String mensaje2 = response.getString("mensaje");
                    //Toast.makeText(getActivity().getApplicationContext(),mensaje2,Toast.LENGTH_LONG).show();
                    Log.d("-->", mensaje2);
                    break;
            }

        } catch (JSONException e) {
            Log.d("-->", e.getMessage());
        }
    }
}
