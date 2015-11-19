package com.belu.upiicsamath.ui.fragment;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.belu.upiicsamath.R;
import com.belu.upiicsamath.model.Alumno;
import com.belu.upiicsamath.tool.Constante;
import com.belu.upiicsamath.web.VolleySingleton;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class FragmentLoging extends Fragment {

    // UI references.
    private EditText txtuser;
    private EditText txtpass;
    private Button btnAcceder;
    private View mProgressView;
    private View mLoginFormView;

    private String user;
    private String pass;
    private Alumno alumno;

    public FragmentLoging() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_loging, container, false);

        txtuser = (EditText) vista.findViewById(R.id.txt_login_usuario);
        txtpass = (EditText) vista.findViewById(R.id.txt_login_password);

        btnAcceder = (Button) vista.findViewById(R.id.btn_loging_acceder);

        mLoginFormView = vista.findViewById(R.id.login_form);
        mProgressView = vista.findViewById(R.id.login_progress);

        return vista;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnAcceder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Login();
            }
        });
    }

    private void Login() {
        //Reinicia marca de errores
        txtuser.setError(null);
        txtpass.setError(null);
        boolean cancel = false;
        View focusView = null;

        // Store values at the time of the login attempt.
        user = txtuser.getText().toString();
        pass = txtpass.getText().toString();

        if (TextUtils.isEmpty(pass)) {
            // Valida que pass no este vacio, sino que sea valido
            txtpass.setError(getString(R.string.error_field_required));
            focusView = txtpass;
            cancel = true;
        }

        if (TextUtils.isEmpty(user)) {
            // Valida que usuario no este vacio, sino que sea valido
            txtuser.setError(getString(R.string.error_field_required));
            focusView = txtuser;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            if(isOnline()) {
                EnviarDatos();
            }
        }

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
        map.put("user", user);
        map.put("pass", pass);

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
                    alumno = gson.fromJson(mensaje.toString(), Alumno.class);
                    //Se imprime en consola los datos recibidos
                    Log.d("--> ", Integer.toString(alumno.getId_boleta()));
                    Log.d("--> ", alumno.getNombre());
                    Log.d("--> ", alumno.getApellido_paterno());
                    Log.d("--> ", alumno.getApellido_materno());
                    Log.d("--> ", alumno.getLicenciatura());
                    Log.d("--> ", "Inicio sesion correcto  " + alumno.getNombre());
                    getFragmentManager().beginTransaction().replace(R.id.container_login, new Fragment_Login_Bienvenida(alumno)).commit();

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


    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getActivity().getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        boolean enabled = true;
        String mensaje = "Conectando";

        if ((netInfo == null || !netInfo.isConnected() || !netInfo.isAvailable())){
            enabled = false;
            mensaje = "Sin conexion a internet";
        }
        Toast.makeText(getActivity().getApplicationContext(),mensaje,Toast.LENGTH_SHORT).show();

        return enabled;
    }
}
