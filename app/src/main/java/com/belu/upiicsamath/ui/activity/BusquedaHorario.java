package com.belu.upiicsamath.ui.activity;

import android.os.Bundle;
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
import com.belu.upiicsamath.model.Horario;
import com.belu.upiicsamath.model.Grupo;
import com.belu.upiicsamath.tool.Constante;
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
    private ArrayAdapter<CharSequence> Adapter;
    private ArrayList<Grupo> listaGrupo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busqueda_horario);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        spLicenciatura = (Spinner) findViewById(R.id.spinner_filtro_lic);
        spSecuencia = (Spinner) findViewById(R.id.spinner_filtro_sec);
        grid_grupo = (GridView) findViewById(R.id.gridView);

        //Asignas el origen de datos desde los recursos
        Adapter = ArrayAdapter.createFromResource(this, R.array.Carreras, android.R.layout.simple_spinner_item);
        //Asignas el layout a inflar para cada elemento al momento de desplegar la lista
        Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //Acción a Spinner
        spLicenciatura.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String seleccion = parent.getItemAtPosition(position).toString();
                //Mostramos la selección actual del Spinner
                Toast.makeText(getApplication().getApplicationContext(), "Haz seleccionado: " + seleccion, Toast.LENGTH_LONG).show();
                getGrupos(seleccion, "5CM81");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<String> adapterSec = new ArrayAdapter<>(
                getSupportActionBar().getThemedContext(),
                android.R.layout.simple_spinner_dropdown_item,
                new String[]{"5CM81", "5CM80", "4CM81"});

        //Seteas el adaptador
        spLicenciatura.setAdapter(Adapter);
        spSecuencia.setAdapter(adapterSec);


//--------------  llenado del grid  -------------------

/*
        getGrupos("Ciencias de la informática", "5CM81");
       //despues de seleccionar se guarda en BD
        getAgregarHorario("5CM80-27");
  */
    }


    private void llenadoGrid(Grupo[] grupo){
        listaGrupo = new ArrayList<>();

        for(int i=0; i< grupo.length;i++){
            Grupo grupotem = new Grupo();

            grupotem.setId_grupo(grupo[i].getId_grupo());
            grupotem.setNombre_profesor(grupo[i].getNombre_profesor());
            grupotem.setId_secuencia(grupo[i].getId_secuencia());
            grupotem.setApellido_paterno_profesor(grupo[i].getApellido_paterno_profesor());
            grupotem.setApellido_materno_profesor(grupo[i].getApellido_materno_profesor());
            grupotem.setNombre_uap(grupo[i].getNombre_uap());

            listaGrupo.add(grupotem);
        }

        GruposAdapter gruposAdapter = new GruposAdapter(this,listaGrupo);
        grid_grupo.setAdapter(gruposAdapter);
    }
    private void mensaje (String mensaje){
        Toast.makeText(this,mensaje,Toast.LENGTH_LONG).show();
    }


    /**
     * Guarda los cambios de un alumno.
     * <p/>
     * Si está en modo inserción, entonces crea una nueva
     * meta en la base de datos
     */
    private void getGrupos(String licenciaatura, String secuencia) {
        HashMap<String, String> map = new HashMap<>();

        // Mapeo previo donde key = a parametros recibidos en el SW
        map.put("licenciatura", licenciaatura);
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

    /**
     * Guarda los cambios de un alumno.
     * <p/>
     * Si está en modo inserción, entonces crea una nueva
     * meta en la base de datos
     */
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
                                GurdarHoraioRespuesta(response);
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
     *
     * @param response Objeto Json con la respuesta
     */
    private void GurdarHoraioRespuesta(JSONObject response) {
        try {
            Gson gson = new Gson();
            // Obtener atributo "estado"
            String estado = response.getString("estado");

            switch (estado) {
                case "EXITO": // EXITO
                    // Obtener array "alumno" Json
                    JSONArray mensaje = response.getJSONArray("horario");
                    // Parsear con Gson y guarda en el arreglo de objetos ConsultaHorarios
                    Horario[] horario = gson.fromJson(mensaje.toString(), Horario[].class);
                    for(int i=0; i < horario.length;i++) {
                        //Se imprime en consola los datos del grupo seleccionado
                        Log.d("--> ", listaGrupo.get(i).getId_grupo());
                        Log.d("--> ", listaGrupo.get(i).getId_secuencia());
                        Log.d("--> ", listaGrupo.get(i).getNombre_uap());
                        Log.d("--> ", listaGrupo.get(i).getNombre_profesor());
                        Log.d("--> ", listaGrupo.get(i).getApellido_paterno_profesor());
                        Log.d("--> ", listaGrupo.get(i).getApellido_materno_profesor());
                        //Se imprime en consola los datos recibidos
                        Log.d("--> ", horario[i].getId_dia());
                        Log.d("--> ", horario[i].getHora_inicio());
                        Log.d("--> ", horario[i].getHora_fin());
                        Log.d("--> ", horario[i].getNombre_edificio());
                        Log.d("--> ", horario[i].getSalon());
                        Log.d("--> ", horario[i].getDir_host());
                        Log.d("--> ", horario[i].getPassword());
                    }
                    break;
                case "FALLIDO": // FALLIDO
                    String mensaje2 = response.getString("mensaje");
                    Toast.makeText(this, mensaje2, Toast.LENGTH_LONG).show();
                    Log.d("-->", mensaje2);
                    break;
            }

        } catch (JSONException e) {
            Log.d("-->", e.getMessage());
        }
    }

    /**
     * Interpreta los resultados de la respuesta y así
     * realizar las operaciones correspondientes
     *
     * @param response Objeto Json con la respuesta
     */
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
                    for(int i=0; i < grupo.length;i++) {

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
                    Toast.makeText(this, mensaje2, Toast.LENGTH_LONG).show();
                    Log.d("-->", mensaje2);
                    break;
            }

        } catch (JSONException e) {
            Log.d("-->", e.getMessage());
        }
    }

}
