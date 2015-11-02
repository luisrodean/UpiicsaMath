package com.belu.upiicsamath.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.belu.upiicsamath.R;
import com.belu.upiicsamath.model.Alumno;
import com.belu.upiicsamath.ui.activity.Principal;


public class Fragment_Login_Bienvenida extends Fragment {

    private Alumno alumno;
    private TextView nombre;
    private TextView boleta;
    private Button btnFinalizar;

    public Fragment_Login_Bienvenida(Alumno alumno) {
        this.alumno = alumno;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_login_bienvenida, container, false);
        nombre = (TextView) vista.findViewById(R.id.tv_loging_bienvenida_nombre);
        boleta = (TextView) vista.findViewById(R.id.tv_loging_bienvenida_boleta);
        btnFinalizar = (Button) vista.findViewById(R.id.btn_loging_bienvenida_finalizar);
        return vista;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        nombre.setText(alumno.getNombre());
        boleta.setText(Integer.toString(alumno.getId_boleta()));
        btnFinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finlizar();
            }
        });

    }

    private void finlizar() {
        Intent intent = new Intent(getActivity().getApplicationContext(), Principal.class);
        intent.putExtra("Nombre", alumno.getNombre());
        intent.putExtra("Apellido", alumno.getApellido_paterno());
        intent.putExtra("Boleta", Integer.toString(alumno.getId_boleta()));
        startActivity(intent);
        getActivity().finish();
    }
}
