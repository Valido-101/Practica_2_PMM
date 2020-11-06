package com.example.practica_2;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class AdaptadorRegistro extends BaseAdapter {

    private Context context; // Contexto de la aplicacion
    private ArrayList<DatosRegistro> listaRegistros; // Lista de objetos DatosRegistro
    private LayoutInflater inflater; // Inflador del contexto de la aplicacion

    // Constructor

    public AdaptadorRegistro(Activity context, ArrayList<DatosRegistro> listaRegistros){
        this.context = context;
        this.listaRegistros = listaRegistros;
        this.inflater = LayoutInflater.from(context);
    }

    // Se le llama cada en la construcción del ListView
    // Va elemento a elemento hasta rellenar el ListView con el ArrayList

    // Buscar método en la API correspondiente: parámetros de entrada y de salida

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //Comprobar si la vista existe

        //Si no existe, la creamos con el layout inflater
        if(convertView.findViewById(convertView.getId())==null)
        {
            convertView = inflater.inflate(R.layout.plantilla_activity_bbdd,parent,false);
        }

        TextView id = convertView.findViewById(R.id.bbdd_id);
        TextView nombre = convertView.findViewById(R.id.bbdd_nombre);
        TextView dni = convertView.findViewById(R.id.bbdd_dni);
        TextView correo = convertView.findViewById(R.id.bbdd_correo);
        TextView nacionalidad = convertView.findViewById(R.id.bbdd_nacionalidad);
        TextView boletin = convertView.findViewById(R.id.bbdd_boletin_noticias);

        id.setText(String.valueOf(listaRegistros.get(position).getId()));
        nombre.setText(listaRegistros.get(position).getNombre());
        dni.setText(listaRegistros.get(position).getDni());
        correo.setText(listaRegistros.get(position).getCorreo());
        nacionalidad.setText(listaRegistros.get(position).getNacionalidad());
        boletin.setText(listaRegistros.get(position).getBoletin());

        return convertView;
    }



    @Override
    public int getCount() {
        return listaRegistros.size();
    }

    @Override
    public Object getItem(int position) {
        return listaRegistros.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

}
