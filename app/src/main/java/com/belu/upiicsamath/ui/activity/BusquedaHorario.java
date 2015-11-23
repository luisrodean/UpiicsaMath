package com.belu.upiicsamath.ui.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.belu.upiicsamath.R;
import com.belu.upiicsamath.database.GrupoDAO;
import com.belu.upiicsamath.model.Horario;
import com.belu.upiicsamath.model.Grupo;
import com.belu.upiicsamath.model.Secuencias;
import com.belu.upiicsamath.tool.Constante;
import com.belu.upiicsamath.ui.adapters.AdapterSpinner;
import com.belu.upiicsamath.ui.adapters.GruposAdapter;
import com.belu.upiicsamath.web.VolleySingleton;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BusquedaHorario extends AppCompatActivity {
    private Spinner spLicenciatura;
    private Spinner spSecuencia;
    private GridView grid_grupo;

    //Creación del Array Adapter
    private ArrayList<Grupo> listaGrupo;
    private Horario[] horario;
    private int position;
    private View vista;
    private String licenciatura;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busqueda_horario);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        spLicenciatura = (Spinner) findViewById(R.id.spinner_filtro_lic);
        spSecuencia = (Spinner) findViewById(R.id.spinner_filtro_sec);
        grid_grupo = (GridView) findViewById(R.id.gridView);

        //Asignas el origen de datos desde los recursos
        String[] carreras = {"","Administración Industrial","Ingeniería Industrial","Ingenieria en Transporte","Ciencias de la informática","Ingeniería en Informática"};
        AdapterSpinner adapterlic = new AdapterSpinner(this, carreras);

        //Acción a Spinner
        spLicenciatura.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                licenciatura = parent.getItemAtPosition(position).toString();
                if (!licenciatura.equals("")) {
                    DescargarSecuencia();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //Seteas el adaptador
        spLicenciatura.setAdapter(adapterlic);

//--------------  llenado del grid  -------------------

        grid_grupo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("--> ", "-------- Dialogo abierto --------" );
                dialog();
                setPosition(position);
                setVista(view);
            }
        });
    }

    private void dialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this)
                .setTitle("Hola")
                .setMessage("¿Desea guardar este grupo?")
                .setPositiveButton("Guardar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //Guarda Grupo en BD
                        getAgregarHorario(listaGrupo.get(position).getId_grupo());
                        //Cierra dialogo
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });

        dialog.create();

        dialog.show();
    }

    private void setVista (View vista){
        this.vista = vista;
    }

    private void setPosition (int position){
        this.position = position;
    }

    private void llenadoGrid(Grupo[] grupo) {
        listaGrupo = new ArrayList<>();

        for (int i = 0; i < grupo.length; i++) {
            Grupo grupotem = new Grupo();

            grupotem.setId_grupo(grupo[i].getId_grupo());
            grupotem.setNombre_profesor(grupo[i].getNombre_profesor());
            grupotem.setId_secuencia(grupo[i].getId_secuencia());
            grupotem.setApellido_paterno_profesor(grupo[i].getApellido_paterno_profesor());
            grupotem.setApellido_materno_profesor(grupo[i].getApellido_materno_profesor());
            grupotem.setNombre_uap(grupo[i].getNombre_uap());

            listaGrupo.add(grupotem);
        }

        GruposAdapter gruposAdapter = new GruposAdapter(this, listaGrupo);
        grid_grupo.setAdapter(gruposAdapter);

    }


    private void getGrupos(String licenciatura, String secuencia) {
        HashMap<String, String> map = new HashMap<>();

        // Mapeo previo donde key = a parametros recibidos en el SW
        map.put("licenciatura", licenciatura);
        map.put("secuencia", secuencia);

        // Crear nuevo objeto Json basado en el mapa
        JSONObject jobject = new JSONObject(map);

        // Depurando objeto Json...
        Log.d("-->objeto JSON creado: ", jobject.toString());

        // Actualizar datos en el servidor
        VolleySingleton.getInstance(this).addToRequestQueue(
                new JsonObjectRequest(
                        Request.Method.POST,
                        Constante.GET_GRUPO_BY_LICENCIATURA,
                        jobject,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                // Procesar la respuesta del servidor
                                procesarRespuestaGrupos(response);
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


    private void getAgregarHorario(String Grupo) {
        HashMap<String, String> map = new HashMap<>();

        // Mapeo previo donde key = a parametros recibidos en el SW
        map.put("id_grupo", Grupo);

        // Crear nuevo objeto Json basado en el mapa
        JSONObject jobject = new JSONObject(map);

        // Depurando objeto Json...
        Log.d("-->objeto JSON creado: ", jobject.toString());

        // Actualizar datos en el servidor
        VolleySingleton.getInstance(this).addToRequestQueue(
                new JsonObjectRequest(
                        Request.Method.POST,
                        Constante.GET_HORARIO,
                        jobject,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                // Procesar la respuesta del servidor
                                GuardarHorarioRespuesta(response);
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


    private void GuardarHorarioRespuesta(JSONObject response) {
        try {
            Gson gson = new Gson();
            // Obtener atributo "estado"
            String estado = response.getString("estado");

            switch (estado) {
                case "EXITO": // EXITO
                    // Obtener array "alumno" Json
                    JSONArray mensaje = response.getJSONArray("horario");
                    // Parsear con Gson y guarda en el arreglo de objetos ConsultaHorarios
                    horario = gson.fromJson(mensaje.toString(), Horario[].class);
                    //Coneccion a BD local
                    GrupoDAO bd = new GrupoDAO(this);
                    for (int i = 0; i < horario.length; i++) {
                        //Se imprime en consola los datos recibidos
                        Log.d("--> ", horario[i].getId_dia());
                        Log.d("--> ", horario[i].getHora_inicio());
                        Log.d("--> ", horario[i].getHora_fin());
                        Log.d("--> ", horario[i].getNombre_edificio());
                        Log.d("--> ", horario[i].getSalon());
                        Log.d("--> ", horario[i].getDir_host());
                        Log.d("--> ", horario[i].getPassword());
                        //Agrega horarion a BD Local
                        bd.Agregar(listaGrupo.get(position), horario[i]);
                    }

                    Log.d("--> ", " Horario Agregado :D");
                    Snackbar.make(vista, "Horario Agregado", Snackbar.LENGTH_LONG).show();
                    break;
                case "FALLIDO": // FALLIDO
                    String mensaje2 = response.getString("mensaje");
                    Snackbar.make(vista, mensaje2, Snackbar.LENGTH_LONG).show();
                    Log.d("-->", mensaje2);
                    break;
            }

        } catch (JSONException e) {
            Log.d("-->", e.getMessage());
        }
    }


    private void procesarRespuestaGrupos(JSONObject response) {
        try {
            Gson gson = new Gson();
            // Obtener atributo "estado"
            String estado = response.getString("estado");

            switch (estado) {
                case "EXITO": // EXITO
                    // Obtener array "alumno" Json
                    JSONArray mensaje = response.getJSONArray("grupos");
                    // Parsear con Gson y guarda en el arreglo de objetos ConsultaHorarios
                    Grupo[] grupo = gson.fromJson(mensaje.toString(), Grupo[].class);
                    for (int i = 0; i < grupo.length; i++) {

                        //Se imprime en consola los datos recibidos
                        Log.d("--> ", grupo[i].getId_grupo());
                        Log.d("--> ", grupo[i].getId_secuencia());
                        Log.d("--> ", grupo[i].getNombre_uap());
                        Log.d("--> ", grupo[i].getNombre_profesor());
                        Log.d("--> ", grupo[i].getApellido_paterno_profesor());
                        Log.d("--> ", grupo[i].getApellido_materno_profesor());

                    }
                    llenadoGrid(grupo);
                    break;
                case "FALLIDO": // FALLIDO
                    String mensaje2 = response.getString("mensaje");
                    Snackbar.make(vista, mensaje2, Snackbar.LENGTH_LONG).show();
                    Log.d("-->", mensaje2);
                    break;
            }

        } catch (JSONException e) {
            Log.d("-->", e.getMessage());
        }
    }


    private void DescargarSecuencia() {
        HashMap<String, String> map = new HashMap<>();

        // Mapeo previo donde key = a parametros recibidos en el SW
        map.put("licenciatura", licenciatura);

        // Crear nuevo objeto Json basado en el mapa
        JSONObject jobject = new JSONObject(map);

        // Depurando objeto Json...
        Log.d("-->objeto JSON creado: ", jobject.toString());

        // Actualizar datos en el servidor
        VolleySingleton.getInstance(this).addToRequestQueue(
                new JsonObjectRequest(
                        Request.Method.POST,
                        Constante.GET_SECUENCIA_BY_LICENCIATURA,
                        jobject,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                // Procesar la respuesta del servidor
                                procesarRespuestaSecuencias(response);
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

    private void procesarRespuestaSecuencias(JSONObject response) {
        try {
            Gson gson = new Gson();
            // Obtener atributo "estado"
            String estado = response.getString("estado");

            switch (estado) {
                case "EXITO": // EXITO
                    // Obtener array "alumno" Json
                    JSONArray mensaje = response.getJSONArray("secuencia");
                    // Parsear con Gson y guarda en el arreglo de objetos ConsultaHorarios
                    Secuencias[] secuencia = gson.fromJson(mensaje.toString(), Secuencias[].class);
                    String[] sec = new String[secuencia.length];
                    for (int i = 0; i < secuencia.length; i++) {
                        //Se imprime en consola los datos recibidos
                        Log.d("--> ", secuencia[i].getId_secuencia());
                        sec[i] = secuencia[i].getId_secuencia();
                    }
                    llenadoListaSecuencia(sec);
                    break;
                case "FALLIDO": // FALLIDO
                    String mensaje2 = response.getString("mensaje");
                    Snackbar.make(vista, mensaje2, Snackbar.LENGTH_LONG).show();
                    Log.d("-->", mensaje2);
                    break;
            }

        } catch (JSONException e) {
            Log.d("-->", e.getMessage());
        }
    }

    private void llenadoListaSecuencia(String[] secuencia) {
        AdapterSpinner adapterSec = new AdapterSpinner(this,secuencia);
        spSecuencia.setAdapter(adapterSec);
        spSecuencia.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                getGrupos(licenciatura, parent.getItemAtPosition(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
