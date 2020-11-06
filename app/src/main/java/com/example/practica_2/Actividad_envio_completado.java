package com.example.practica_2;

import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

//Clase que hereda de activity
public class Actividad_envio_completado extends Activity {


    //Método que se ejecuta al crearse la activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_envio_completado);

        //Intent en el que se guarda el que ha lanzado la actividad desde Actividad_formulario.java
        Intent intent_completado = getIntent();

        //Textview en el que vamos a guardar el Extra "nombre"
        TextView datos_nombre = findViewById(R.id.datos_nombre);
        //Seteamos el texto del elemento para que muestre el contenido del extra
        datos_nombre.setText("-"+intent_completado.getStringExtra("nombre"));

        //Textview en el que vamos a guardar el Extra "dni"
        TextView datos_dni = findViewById(R.id.datos_dni);
        //Seteamos el texto del elemento para que muestre el contenido del extra
        datos_dni.setText("-"+intent_completado.getStringExtra("dni"));

        //Textview en el que vamos a guardar el Extra "correo"
        TextView datos_correo = findViewById(R.id.datos_correo);
        //Seteamos el texto del elemento para que muestre el contenido del extra
        datos_correo.setText("-"+intent_completado.getStringExtra("correo"));

        //Textview en el que vamos a guardar el Extra "nacionalidad"
        TextView datos_nacionalidad = findViewById(R.id.datos_nacionalidad);
        //Seteamos el texto del elemento para que muestre el contenido del extra
        datos_nacionalidad.setText("-"+intent_completado.getStringExtra("nacionalidad"));

        //Textview en el que vamos a guardar el Extra "suscripcion"
        TextView datos_suscripcion = findViewById(R.id.datos_suscripcion);
        //Seteamos el texto del elemento para que muestre el contenido del extra
        datos_suscripcion.setText("-"+intent_completado.getStringExtra("suscripcion"));
    }

}