package com.belu.upiicsamath;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class FragmentAviso extends Fragment {

    EditText user , pass;
    Button insert;

    public FragmentAviso() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_avisos, container, false);
        user = (EditText) vista.findViewById(R.id.txtuser);
        pass = (EditText) vista.findViewById(R.id.txtpass);
        insert = (Button) vista.findViewById(R.id.btnacep);
        return vista;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

}
