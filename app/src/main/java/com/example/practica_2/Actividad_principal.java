package com.example.practica_2;

import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

//Esta es la clase principal que se ejecuta primero. Hereda de la clase activity e implementa la interfaz View.OnClickListener, que
//permite que el botón haga algo al ser pulsado
public class Actividad_principal extends Activity implements View.OnClickListener {



    //Método que se ejecuta al crearse la actividad
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Al crear la actividad, se aplican los elementos y configuraciones detallados en el xml correspondiente
        setContentView(R.layout.activity_actividad_principal);
        //Se crean dos botones que hacen referencia a los del xml para poder trabajar con ellos mediante el método
        //findViewById
        Button btn1 = (Button) findViewById(R.id.btn_formulario);
        Button btn2 = (Button) findViewById(R.id.btn_email);
        Button btn3 = (Button) findViewById(R.id.btn_base_de_datos);
        //Se setean los listeners del método onClick a ambos botones
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
    }

    //Método que se ejecuta al hacer click sobre algún botón
    @Override
    public void onClick(View v) {
        //Se crean dos intents, uno que inicie la actividad formulario y otro que inicie la actividad email
        Intent act_form = new Intent(this,Actividad_formulario.class);
        Intent act_correo = new Intent(this,Activity_email.class);
        Intent act_bbdd = new Intent(this,BaseDeDatosActivity.class);
        //Si el elemento que ha activado el onClick tiene un id que coincide con el del botón del formulario, se inicia la actividad
        //formulario. De lo contrario se inicia la actividad email
        if (v.getId()==findViewById(R.id.btn_formulario).getId())
        {
            startActivity(act_form);
        }

        if(v.getId()==findViewById(R.id.btn_email).getId())
        {
            startActivity(act_correo);
        }

        if(v.getId()==findViewById(R.id.btn_base_de_datos).getId())
        {
            startActivity(act_bbdd);
        }



    }
}