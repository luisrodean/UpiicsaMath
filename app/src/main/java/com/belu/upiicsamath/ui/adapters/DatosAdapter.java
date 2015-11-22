package com.belu.upiicsamath.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.belu.upiicsamath.R;
import com.belu.upiicsamath.model.Horario;
import com.belu.upiicsamath.model.HorarioAdapter;

import java.util.ArrayList;


public class DatosAdapter extends RecyclerView.Adapter<DatosAdapter.datosViewHolder>{

    private ArrayList<HorarioAdapter> item;

    public DatosAdapter(ArrayList<HorarioAdapter> item) {
        this.item = item;
    }

    @Override
    public datosViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.itemlistahorario, viewGroup, false);
        datosViewHolder datos= new datosViewHolder(v);
        return datos;
    }

    @Override
    public void onBindViewHolder(datosViewHolder ViewHolder, int i) {
        ViewHolder.profesor.setText(item.get(i).getNombre_profesor());
        ViewHolder.uap.setText(item.get(i).getNombre_uap());
        ViewHolder.secuencia.setText(item.get(i).getId_secuencia());
        ViewHolder.salon.setText(item.get(i).getSalon());
        ViewHolder.edificio.setText(item.get(i).getNombre_edificio());
        ViewHolder.hInicio.setText(item.get(i).getHora_inicio());
        ViewHolder.hFin.setText(item.get(i).getHora_fin());
    }

    @Override
    public int getItemCount() {
        return item.size();
    }

    public class datosViewHolder extends RecyclerView.ViewHolder{
        TextView profesor, secuencia, uap, edificio, salon, hInicio, hFin;

        public datosViewHolder(View itemView) {
            super(itemView);

            profesor=(TextView)itemView.findViewById(R.id.tvProfesor);
            uap=(TextView)itemView.findViewById(R.id.tvUAP);
            secuencia=(TextView)itemView.findViewById(R.id.tvSecuencia);
            salon=(TextView)itemView.findViewById(R.id.tvSalon);
            edificio=(TextView)itemView.findViewById(R.id.tvEdificio);
            hInicio=(TextView)itemView.findViewById(R.id.tvHorario);


        }
    }

}
