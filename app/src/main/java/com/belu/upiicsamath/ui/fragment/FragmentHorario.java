package com.belu.upiicsamath.ui.fragment;

import android.app.Fragment;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Config;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridView;

import com.belu.upiicsamath.R;
import com.belu.upiicsamath.model.Datos;
import com.belu.upiicsamath.ui.adapters.DatosAdapter;

import java.sql.SQLException;
import java.util.ArrayList;

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
        ArrayList<Datos> datos = new ArrayList<>();

        //Se instancia un objeto de tipo datos llamado datos
            Datos informacion = new Datos();
                informacion.setEdificio("Ingenieria");
                informacion.setSalon("203");
                informacion.setUap("Auditoria informatica");
                informacion.setSecuencia("123");
                informacion.setProfesor("Sandoval");
                informacion.setHinicio("15:00");
                informacion.setHfin("16:00");

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