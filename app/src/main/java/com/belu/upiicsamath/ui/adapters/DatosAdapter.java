package com.belu.upiicsamath.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.belu.upiicsamath.R;
import com.belu.upiicsamath.model.Datos;
import com.development.postensa.R;
import com.development.postensa.models.Product;
import com.development.postensa.ui.activity.ZoomImage;
import com.development.postensa.util.BitmapTransform;
import com.squareup.picasso.Picasso;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;


/**
 * Created by Jhordan on 01/03/15.
 */
public class DatosAdapter extends ArrayAdapter<Datos> {

    ArrayList<Datos> datosArrayList;
    LayoutInflater layoutInflater;

    public DatosAdapter(Context context, ArrayList<Datos> datosArrayList) {

        super(context, -1, datosArrayList);
        this.datosArrayList = datosArrayList;
        layoutInflater = LayoutInflater.from(context);

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final ViewHolder holder;
        final Datos datos = getItem(position);
        int layout = R.layout.itemlistahorario;

        if (convertView == null) {

            convertView = layoutInflater.inflate(layout, null);
            holder = new ViewHolder();

            holder.tvSecuencia = (TextView)convertView.findViewById(R.id.tvSecuencia);
            holder.tvEdificio = (TextView)convertView.findViewById(R.id.tvEdificio);
            holder.tvProfesor = (TextView)convertView.findViewById(R.id.tvProfesor);
            holder.tvHorario = (TextView)convertView
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.txtTitleProduct.setText(product.getName());
        try {

            holder.txtDescriptionProduct.setText(new String(product.getDescription().getBytes("ISO-8859-1"), "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        holder.imageViewProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Zoom = new Intent(getContext(), ZoomImage.class);
                Zoom.putExtra("url",product.getPath());
                getContext().startActivity(Zoom);
            }
        });

        Picasso.with(getContext()).load("file://" + product.getPath())
                .transform(new BitmapTransform(BitmapTransform.MAX_WIDTH, BitmapTransform.MAX_HEIGHT))
                .resize(BitmapTransform.SIZE, BitmapTransform.SIZE)
                .centerInside()
                .into(holder.imageViewProduct);


        return convertView;
    }

    public Datos getItem(int position) {
        return super.getItem(position);
    }

    public static class ViewHolder {
        TextView tvSecuencia;
        TextView tvProfesor;
        TextView tvHorario;
        TextView tvSalon;
        TextView tvUAP;
        TextView tvEdificio;
    }
}
