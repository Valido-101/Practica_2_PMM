package com.example.practica_2;

import android.app.Activity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class BaseDeDatosActivity extends Activity implements ListView.OnItemLongClickListener{

    //Creamos los elementos necesarios para la creación y relleno de la activity
    ListView lst_view;
    AdaptadorRegistro adaptador;
    BaseDeDatos bbdd = new BaseDeDatos(this);
    ArrayList<DatosRegistro> datos_bbdd = bbdd.obtenerDatos();

    //Al crear la clase se crea un objeto AdaptadorRegistro que tiene como parámetros un contexto y un objeto BaseDeDatos
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_de_datos);

        //Se guarda el listview del layout
        lst_view = (ListView) findViewById(R.id.lista_bbdd);

        //Se rellena el adaptador con un contexto y un arraylist de DatosRegistro
        adaptador=new AdaptadorRegistro(this, datos_bbdd);

        //Establecemos el adaptador creado al listview
        lst_view.setAdapter(adaptador);

        //Esablecemos un OnItemLongClickListener al listview
        lst_view.setOnItemLongClickListener(this);

        //Recorre elemento por elemento el arraylist y rellena los elementos del listview
        for(int x=0;x<datos_bbdd.size();x++)
        {
            adaptador.getView(x,findViewById(R.id.lista_bbdd),(ViewGroup) findViewById(R.id.lista_bbdd).getParent());
        }
    }


    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

        //Obtenemos el textview que contiene el id del registro
        TextView txt_view = (TextView) view.findViewById(R.id.bbdd_id);

        //Ejecutamos el método eliminarDatos de la clase BaseDeDatos
        bbdd.eliminarDatos(txt_view.getText().toString());

        //Borramos el registro del listview en la posición indicada
        datos_bbdd.remove(position);

        //Notificamos al adaptador de que los datos de origen se han modificado
        adaptador.notifyDataSetChanged();

        return true;
    }
}