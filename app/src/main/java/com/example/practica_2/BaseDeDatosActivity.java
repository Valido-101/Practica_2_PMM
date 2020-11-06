package com.example.practica_2;

import android.app.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class BaseDeDatosActivity extends Activity implements ListView.OnItemLongClickListener{

    ListView lst_view;
    AdaptadorRegistro adaptador;
    BaseDeDatos bbdd = new BaseDeDatos(this);
    ArrayList<DatosRegistro> datos_bbdd = bbdd.obtenerDatos();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_de_datos);

        lst_view = (ListView) findViewById(R.id.lista_bbdd);

        adaptador=new AdaptadorRegistro(this, datos_bbdd);

        lst_view.setAdapter(adaptador);

        lst_view.setOnItemLongClickListener(this);
    }


    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

        TextView txt_view = (TextView) view.findViewById(R.id.bbdd_id);

        bbdd.eliminarDatos(txt_view.getText().toString());

        datos_bbdd.remove(position);

        adaptador.notifyDataSetChanged();

        return false;
    }
}