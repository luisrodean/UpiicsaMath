package com.belu.upiicsamath.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.belu.upiicsamath.R;

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

    //Creación de Arreglo Carreras
    private String[] Carreras;

    //Creación del Array Adapter
    ArrayAdapter<CharSequence> Adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_registro_alumno);

        /*Instanciar arreglo Carreras
        Carreras = new String[]{"Administración Industrial",
                                "Ingeniería Industrial",
                                "Ingenieria en Transporte",
                                "Ciencias de la Informática",
                                "Ingeniería en Informática"};
        */


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

                //Recuperamos los valores de los campos de texto
                String id_boleta = txt_Id_Boleta.getText().toString();
                String nombre= txt_Nombre.getText().toString();
                String apellido_paterno = txt_ApellidoPaterno.getText().toString();
                String apellido_materno = txt_ApellidoMaterno.getText().toString();
            }
        });

        //Asignas el origen de datos desde los recursos
        Adapter = ArrayAdapter.createFromResource(this, R.array.Carreras, android.R.layout.simple_spinner_item);
        //Asignas el layout a inflar para cada elemento al momento de desplegar la lista
        Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Seteas el adaptador
        spn_Licenciatura.setAdapter(Adapter);

        //Acción a Spinner
        spn_Licenciatura.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               String seleccion = parent.getItemAtPosition(position).toString();

               //Mostramos la selección actual del Spinner
               Toast.makeText(getApplication().getApplicationContext(), "Haz seleccionado: " + seleccion ,Toast.LENGTH_LONG).show();
           }

           @Override
           public void onNothingSelected(AdapterView<?> parent) {

           }
        });

        }


        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            return super.onCreateOptionsMenu(menu);
        }
}
