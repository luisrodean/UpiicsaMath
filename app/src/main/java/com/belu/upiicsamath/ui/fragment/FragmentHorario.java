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

import com.belu.upiicsamath.model.Grupo;
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
    GridView gridview;
    //Se declara objeto datosAdapter de tipo DatosAdapter
    private DatosAdapter datosAdapter;
    //PostensaAdapterDB postensaAdapterDB;
    //Realiza busqueda del usuario
    EditText txtSearch;

    //Elemnto indispensable en un Fragment
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
               /* Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                        */
            }
        });
        //Se instancia un objeto de tipo ArrayList llamado datos
        ArrayList<Horario> datos = new ArrayList<>();

        //Se instancia un objeto de tipo datos llamado datos
        Horario informacion = new Horario();
        Grupo grupo = new Grupo();
                informacion.setNombre_edificio("Ingenieria");
                informacion.setSalon("203");
                grupo.setNombre_uap("Auditoria informatica");
                grupo.setId_secuencia("123");
                grupo.setNombre_profesor("Sandoval");
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


}

