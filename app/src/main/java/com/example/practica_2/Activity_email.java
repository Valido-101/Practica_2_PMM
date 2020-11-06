package com.example.practica_2;

import android.app.Activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

//Esta hereda de la clase Activity e implementa la interfaz OnClickListener
public class Activity_email extends Activity implements View.OnClickListener {

    //Objetos asociados a los elementos del layout
    Button btn_enviar = findViewById(R.id.btn_email);
    EditText mensaje = findViewById(R.id.mensaje);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);
        //Seteo del listener de onClick al botón enviar
        btn_enviar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        //Array de direcciones de correo necesario para el intent de envío de correo
        String direcciones_correo [] = {"jesus.valido.zafra@alumnos.fesac.es"};

        //Intent que permitirá enviar el correo
        Intent correo = new Intent(Intent.ACTION_SENDTO);

        //Seteamos el tipo de dato del intent (de tipo URI)
        correo.setData(Uri.parse("mailto:"));
        //Establecemos como extra la dirección de correo a la que llegará el correo
        correo.putExtra(Intent.EXTRA_EMAIL, direcciones_correo);
        //Establecemos como extra el contenido del mensaje del correo
        correo.putExtra(Intent.EXTRA_TEXT, mensaje.getText().toString());
        //Establecemos como asunto "Sugerencias"
        correo.putExtra(Intent.EXTRA_SUBJECT,"Sugerencias");

        //Lanzamos la actividad para que envíe el correo
        startActivity(Intent.createChooser(correo,"Elige el correo:"));
    }
}