package com.belu.upiicsamath.ui.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.belu.upiicsamath.R;
import com.belu.upiicsamath.model.Alumno;
import com.belu.upiicsamath.model.ConsultaHorario;
import com.belu.upiicsamath.tool.Constante;
import com.belu.upiicsamath.ui.adapters.DatosAdapter;
import com.belu.upiicsamath.web.VolleySingleton;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FragmentHorario extends Fragment {

    //Constructor de Fragment Horario
   public FragmentHorario() {
    }

    RecyclerView recyclerView;
    GridView gridview;
    //Se declara objeto datosAdapter de tipo DatosAdapter
    DatosAdapter datosAdapter;
    //PostensaAdapterDB postensaAdapterDB;
    //Realiza busqueda del usuario
    EditText txtSearch;

    //Elemnto indispensable en un Fragment
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_horario, container, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.reciclador);
        return rootView;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //Se instancia un objeto de tipo ArrayList llamado datos
        ArrayList<ConsultaHorario> datos = new ArrayList<>();

        //Se instancia un objeto de tipo datos llamado datos
            ConsultaHorario informacion = new ConsultaHorario();
                informacion.setNombre_edificio("Ingenieria");
                informacion.setSalon("203");
                informacion.setNombre_uap("Auditoria informatica");
                informacion.setId_secuencia("123");
                informacion.setNombre_profesor("Sandoval");
                informacion.setHora_inicio("15:00");
                informacion.setHora_fin("16:00");

        /*
                    informacion.ssetEdificio();
                            setPath(c.getString(c.getColumnIndexOrThrow(Bitmap.Config.CATALOG_ROW_PATH)));
                    informacion.setName(c.getString(c.getColumnIndexOrThrow(Bitmap.Config.CATALOG_ROW_CLAVE)));
                    informacion.setDescription(c.getString(c.getColumnIndexOrThrow(Config.CATALOG_ROW_NOMBRE)));*/
                    datos.add(informacion);

        datosAdapter = new DatosAdapter(datos);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(datosAdapter);
    /*       //Instanciar objetos
        datosAdapter = new DatosAdapter(getActivity(), datos);
        //Activa los filtros de busqueda en el gridview
        gridview.setTextFilterEnabled(true);
        //del objeto gridview se llama el metodo setAdapter y se le asigna datosAdapter
        gridview.setAdapter(datosAdapter);


       txtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
          public void onTextChanged(CharSequence s, int start, int before, int count) {

                ArrayList<Product> datos = new ArrayList<>();
                postensaAdapterDB = new PostensaAdapterDB(getActivity());
                try {
                    postensaAdapterDB.open();
                    Cursor c = postensaAdapterDB.getCursorSearchCatalog(s.toString());
                    if (c.moveToFirst()) {
                        do {

                            Product product = new Product();
                            product.setPath(c.getString(c.getColumnIndexOrThrow(Config.CATALOG_ROW_PATH)));
                            product.setName(c.getString(c.getColumnIndexOrThrow(Config.CATALOG_ROW_CLAVE)));
                            product.setDescription(c.getString(c.getColumnIndexOrThrow(Config.CATALOG_ROW_NOMBRE)));
                            datos.add(product);

                        } while (c.moveToNext());
                        c.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    postensaAdapterDB.close();
                } finally {
                    postensaAdapterDB.close();
                }
                ProductHomeAdapter productAdapter = new ProductHomeAdapter(getActivity(), datos);
                catalogGridview.setAdapter(productAdapter);

            }

            @Override
            public void afterTextChanged(Editable s) {

            };
        });*/
    }



    /**
     * Guarda los cambios de un alumno.
     * <p/>
     * Si está en modo inserción, entonces crea una nueva
     * meta en la base de datos
     */
    public void EnviarDatos() {
        HashMap<String, String> map = new HashMap<>();

        // Mapeo previo donde key = a parametros recibidos en el SW
        map.put("id_secuencia", "5CM81");

        // Crear nuevo objeto Json basado en el mapa
        JSONObject jobject = new JSONObject(map);

        // Depurando objeto Json...
        Log.d("-->objeto JSON creado: ", jobject.toString());

        // Actualizar datos en el servidor
        VolleySingleton.getInstance(getActivity()).addToRequestQueue(
                new JsonObjectRequest(
                        Request.Method.POST,
                        Constante.VALIDAR_LOGIN_USUARIO,
                        jobject,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                // Procesar la respuesta del servidor
                                procesarRespuesta(response);
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
    private void procesarRespuesta(JSONObject response) {
        try {
            Gson gson = new Gson();
            // Obtener atributo "estado"
            String estado = response.getString("estado");

            switch (estado) {
                case "EXITO": // EXITO
                    // Obtener array "alumno" Json
                    JSONObject mensaje = response.getJSONObject("usuario");
                    // Parsear con Gson y guarda en objeto Alumno
                 /*   alumno = gson.fromJson(mensaje.toString(), Alumno.class);
                    //Se imprime en consola los datos recibidos
                    Log.d("--> ", Integer.toString(alumno.getId_boleta()));
                    Log.d("--> ", alumno.getNombre());
                    Log.d("--> ", alumno.getApellido_paterno());
                    Log.d("--> ", alumno.getApellido_materno());
                    Log.d("--> ", alumno.getLicenciatura());
                    Log.d("--> ", alumno.getPass());
                    Log.d("--> ", "Inicio sesion correcto  " + alumno.getNombre());
                    getFragmentManager().beginTransaction().replace(R.id.container_login, new Fragment_Login_Bienvenida(alumno)).commit();
*/
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

