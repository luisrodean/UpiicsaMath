package com.belu.upiicsamath.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.belu.upiicsamath.R;
import com.belu.upiicsamath.database.AlumnoDAO;
import com.belu.upiicsamath.model.Alumno;
import com.belu.upiicsamath.tool.Validar;

/**
 * Created by Betza Ojeda on 14/11/2015.
 */
public class RegistroActivity extends Activity {

    //Se declaran variables
    private EditText txt_Id_Boleta;
    private EditText txt_Nombre;
    private EditText txt_ApellidoPaterno;
    private EditText txt_ApellidoMaterno;
    private Spinner spn_Licenciatura;
    private Button btn_Registrar;

    //Creación del Array Adapter
    private ArrayAdapter<CharSequence> Adapter;
    private String seleccion;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_registro_alumno);

        //Obtenemos las referencias a los controles
        //Textos
        txt_Id_Boleta = (EditText) findViewById(R.id.txt_registro_id_boleta);
        txt_Nombre = (EditText) findViewById(R.id.txt_registro_nombre);
        txt_ApellidoPaterno = (EditText) findViewById(R.id.txt_registro_apellido_paterno);
        txt_ApellidoMaterno = (EditText) findViewById(R.id.txt_registro_apellido_materno);

        //Boton
        btn_Registrar = (Button) findViewById(R.id.btn_registro_Registrar);

        //Spinner
        spn_Licenciatura = (Spinner) findViewById(R.id.spn_registro_licenciatura);

        //Accion de boton "btnRegistrar"
        btn_Registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onclickRegistrar(v);
            }
        });

        //Asignas el origen de datos desde los recursos
        Adapter = ArrayAdapter.createFromResource(this, R.array.Carreras, android.R.layout.simple_spinner_item);
        //Asignas el layout a inflar para cada elemento al momento de desplegar la lista
        Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Seteas el adaptador
        spn_Licenciatura.setBackgroundResource(R.color.white);
        spn_Licenciatura.setAdapter(Adapter);

        //Acción a Spinner
        spn_Licenciatura.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                seleccion = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    private void onclickRegistrar(View vista) {
        //Reinicia marca de errores
        txt_Id_Boleta.setError(null);
        txt_Nombre.setError(null);
        txt_ApellidoPaterno.setError(null);
        txt_ApellidoMaterno.setError(null);

        //Se validan los campos vacios y se notifica
        Validar validar = new Validar();

        if (validar.CampoVacio(txt_Id_Boleta, getString(R.string.error_field_required))
                || validar.CampoVacio(txt_Nombre, getString(R.string.error_field_required))
                || validar.CampoVacio(txt_ApellidoPaterno, getString(R.string.error_field_required))
                || validar.CampoVacio(txt_ApellidoMaterno, getString(R.string.error_field_required)))
        {
            validar.getFocusView().requestFocus();
        } else {
            if (seleccion.equals("Seleccione una Licenciatura")) {
                // Valida que no este vacio, sino que sea valido
                Snackbar.make(vista, "Seleccione una Licenciatura", Snackbar.LENGTH_SHORT).show();
            } else {
                if (validar.isOnline(this, vista)) {
                    //Recuperamos los valores de los campos de texto
                    String id_boleta = txt_Id_Boleta.getText().toString();
                    String nombre = txt_Nombre.getText().toString();
                    String apellido_paterno = txt_ApellidoPaterno.getText().toString();
                    String apellido_materno = txt_ApellidoMaterno.getText().toString();

                    AlumnoDAO aldao = new AlumnoDAO(this);
                    aldao.Agregar(id_boleta,nombre,apellido_paterno,apellido_materno,seleccion);

                    //Cambia de actividad y cierra la actual
                    startActivity(new Intent(this,Principal.class));
                    this.finish();
                    //EnviarDatos();
                }
            }

        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }
}
