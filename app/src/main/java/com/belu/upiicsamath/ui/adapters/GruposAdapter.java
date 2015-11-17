package com.belu.upiicsamath.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.belu.upiicsamath.R;
import com.belu.upiicsamath.model.Grupo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Luis on 16/11/2015.
 */
public class GruposAdapter extends ArrayAdapter<Grupo> {

    ArrayList<Grupo> grupoArrayList;
    LayoutInflater layoutInflater;

    public GruposAdapter(Context context, ArrayList<Grupo> grupoArrayList) {
        super(context, -1, grupoArrayList);
        this.grupoArrayList = grupoArrayList;
        layoutInflater = LayoutInflater.from(context);
    }

    public Grupo getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        final Grupo grupo = getItem(position);

        int layout = R.layout.item_listabusqueda_horario;

        convertView = layoutInflater.inflate(layout, null);
        viewHolder = new ViewHolder(convertView);

            viewHolder.txtId_grupo.setText(grupoArrayList.get(position).getId_grupo());
            viewHolder.txtProfesor.setText(grupoArrayList.get(position).getNombre_profesor());
        convertView.setTag(viewHolder);

        return convertView;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtId_grupo;
        TextView txtProfesor;
        FrameLayout color;

        public ViewHolder(View itemView) {
            super(itemView);

            txtId_grupo = (TextView) itemView.findViewById(R.id.item_grupo_tvGrupo);
            txtProfesor = (TextView) itemView.findViewById(R.id.item_grupo_tvProfesor);
            color = (FrameLayout) itemView.findViewById(R.id.item_grupo_BarraColorGrupo);
        }
    }
}
