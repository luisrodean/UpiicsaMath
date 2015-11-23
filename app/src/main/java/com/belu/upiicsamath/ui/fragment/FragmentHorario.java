package com.belu.upiicsamath.ui.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridView;

import com.belu.upiicsamath.database.GrupoDAO;
import com.belu.upiicsamath.model.Grupo;
import com.belu.upiicsamath.model.HorarioAdapter;
import com.belu.upiicsamath.tool.Fecha;
import com.belu.upiicsamath.ui.activity.BusquedaHorario;
import com.belu.upiicsamath.R;
import com.belu.upiicsamath.model.Horario;
import com.belu.upiicsamath.ui.adapters.DatosAdapter;

import java.util.ArrayList;

public class FragmentHorario extends Fragment {

    //Constructor de Fragment Horario
   public FragmentHorario() {
    }

    private RecyclerView recyclerView;
    private FloatingActionButton fab;
    //Se declara objeto datosAdapter de tipo DatosAdapter
    private DatosAdapter datosAdapter;

    //Elemento indispensable en un Fragment
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_horario, container, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.reciclador);
        fab = (FloatingActionButton) rootView.findViewById(R.id.agregar_horario);
        return rootView;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity().getApplicationContext(), BusquedaHorario.class));
            }
        });

        //Se instancia un objeto de tipo ArrayList llamado datos
        GrupoDAO DatosDAO = new GrupoDAO(getActivity().getApplicationContext());
        Fecha fecha = new Fecha();
        //ArrayList<HorarioAdapter> datos = DatosDAO.getHorarioPorDia(fecha.getDiaSemana());
        ArrayList<HorarioAdapter> datos = DatosDAO.getHorarioPorDia("Lunes");
        datosAdapter = new DatosAdapter(datos);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(datosAdapter);

    }


}

