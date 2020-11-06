package com.example.practica_2;

import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.net.DatagramSocket;

//Clase que hereda de Activity e implementa onClickListener
public class Actividad_formulario extends Activity implements View.OnClickListener{


    //Booleano que se encargará de almacenar si el nombre tiene un formato correcto
    private boolean nombre_correcto = false;
    //Para que haya 3 cadenas deberá haber al menos 3 espacios
    private int cont_espacios = 0;
    //Booleanos que se encargarán de almacenar si el dni tiene un formato correcto
    private boolean dni_numeros_correcto = false;
    private boolean dni_letra_correcto = false;
    //Booleano que se encargará de almacenar si el correo tiene un formato correcto
    private boolean correo_correcto = false;
    //Si hay más de una arroba no sirve
    private int num_arrobas = 0;
    //Se guarda la posicion del arroba para saber si el punto esta a la derecha
    private int pos_arroba = 0;
    private String boletin;

    BaseDeDatos bbdd = new BaseDeDatos(this);

    DatosRegistro dr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_formulario);
        //Botón asociado al del layout para poder trabajar con él
        Button btn_enviar = (Button) findViewById(R.id.btn_enviar);
        //Seteo del onClickListener
        btn_enviar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        //Objetos asociados a los distintos elementos del layout para poder trabajar con su contenido
        EditText edt_txt_nombre_apellidos = findViewById(R.id.nombre_apellidos);
        EditText edt_txt_dni = findViewById(R.id.dni);
        EditText edt_txt_correo = findViewById(R.id.correo_electronico);
        Spinner spn_nacionalidad = findViewById(R.id.nacionalidad);
        CheckBox chck_bx_suscripcion = findViewById(R.id.checkBox);

        //Intent que inicia la actividad que muestra los datos del formulario
        Intent intent1 = new Intent(this, Actividad_envio_completado.class);
        //Extra que almacena el texto dentro del EditText nombre_apellidos
        intent1.putExtra("nombre", edt_txt_nombre_apellidos.getText().toString());
        //Extra que almacena el texto dentro del EditText dni
        intent1.putExtra("dni", edt_txt_dni.getText().toString());
        //Extra que almacena el texto dentro del EditText correo
        intent1.putExtra("correo",edt_txt_correo.getText().toString());
        //Extra que almacena el elemento seleccionado dentro del Spinner nacionalidad
        intent1.putExtra("nacionalidad",spn_nacionalidad.getSelectedItem().toString());

        dr = new DatosRegistro();

        //Este if se encarga de comprobar si el checkbox está marcado
        if(chck_bx_suscripcion.isChecked())
        {
            //Si está marcado, el extra suscripción tendrá como valor "Sí"
            intent1.putExtra("suscripcion", "Sí");
            boletin="Sí";
        }
        else
        {
            //De lo contrario, tendrá como valor "No"
            intent1.putExtra("suscripcion","No");
            boletin="No";
        }

        //Este método se encarga de quitarle los espacios al principio y al final del texto de nombre_apellidos
        edt_txt_nombre_apellidos.getText().toString().trim();

        //Este for recorre el texto de nombre_apellidos carácter por carácter
        for(int x=1 ; x<edt_txt_nombre_apellidos.getText().toString().length() ; x++)
        {
            //Si el código ASCII del carácter actual coincide con el del espacio, aumenta el contador de espacios
            if((int)(edt_txt_nombre_apellidos.getText().toString().charAt(x)) == 32)
            {
                cont_espacios++;
            }

        }

        //Si el contador de espacios es igual o superior a dos, significa que hay al menos tres cadenas, por lo que el nombre
        //tiene el formato correcto y el booleano pasa a ser true.
        if(cont_espacios>=2)
        {
            nombre_correcto=true;
        }

        //Este método se encarga de quitarle los espacios al principio y al final del texto del dni
        edt_txt_dni.getText().toString().trim();

        //Primero se recorren todos los caracteres hasta el penúltimo. Si todos son números, el booleano de los números
        //pasa a true. De lo contrario se queda como false
        for(int x=1 ; x<edt_txt_dni.getText().toString().length()-1 ; x++)
        {

            if((int)(edt_txt_dni.getText().toString().charAt(x))>=48 && (int)(edt_txt_dni.getText().toString().charAt(x))<=57)
            {
                dni_numeros_correcto = true;
            }
            else
            {
                dni_numeros_correcto = false;
                //En el momento en que detecta que un carácter no es un número, sale del bucle
                break;
            }

        }

        //Si el último carácter es una letra mayúscula, el booleano de la letra del dni pasa a ser true
        if((int)edt_txt_dni.getText().toString().charAt(edt_txt_dni.getText().toString().length()-1)>=65 && (int)edt_txt_dni.getText().toString().charAt(edt_txt_dni.getText().toString().length()-1)<=90)
        {
            dni_letra_correcto = true;
        }

        //Este método se encarga de quitarle los espacios al principio y al final del texto del correo
        edt_txt_correo.getText().toString().trim();

        //Este for recorre el texto de dentro del correo
        for(int x=1 ; x<edt_txt_correo.getText().toString().length() ; x++)
        {
            //Si algún carácter coincide con el código del arroba, se incrementa el número de arrobas y se guarda la posición de la misma
            if((int)(edt_txt_correo.getText().toString().charAt(x)) == 64)
            {
                num_arrobas++;
                pos_arroba = x;
            }
        }

        //Si sólo hay una arroba, se pasa a comprobar si hay un punto a la derecha de ésta
        if(num_arrobas==1)
        {
            //Se recorre el texto del correo a partir de la posición de la arroba (esto nos asegura que el punto está a la derecha)
            for(int x=pos_arroba ; x<edt_txt_correo.getText().toString().length() ; x++)
            {
                //Si el código ASCII del carácter actual coincide con el del punto, el booleano que comprueba si tiene un formato
                //correcto pasa a ser true
                if((int)(edt_txt_correo.getText().toString().charAt(x)) == 46)
                {
                    correo_correcto = true;
                }
            }
        }

        //Si todos los booleanos son true se inicia la activity donde se muestran los datos, de lo contrario se indica cuál es el
        //parámetro incorrecto con un toast o simplemente se indica que hay varios si hay más de uno erróneo
        if(nombre_correcto == true && dni_letra_correcto == true && dni_numeros_correcto == true && correo_correcto == true)
        {
            BaseDeDatos bbdd = new BaseDeDatos(this);
            bbdd.registrarDatos(new DatosRegistro(edt_txt_nombre_apellidos.getText().toString(),edt_txt_dni.getText().toString(),edt_txt_correo.getText().toString(),spn_nacionalidad.getSelectedItem().toString(),boletin));
            //Esto es lo que inicia la activity
            startActivity(intent1);
        }
        else
        {
            if(nombre_correcto == false)
            {
                //Mensaje emergente indicando el fallo
                Toast message_nombre = Toast.makeText(getApplicationContext(),"Parámetro Nombre y Apellidos incorrecto",Toast.LENGTH_SHORT);
                message_nombre.show();
            }
            else
            {
                if(dni_numeros_correcto == false || dni_letra_correcto == false)
                {
                    //Mensaje emergente indicando el fallo
                    Toast message_dni = Toast.makeText(getApplicationContext(),"Parámetro DNI incorrecto",Toast.LENGTH_SHORT);
                    message_dni.show();
                }
                else
                {
                    if(correo_correcto == false)
                    {
                        //Mensaje emergente indicando el fallo
                        Toast message_correo = Toast.makeText(getApplicationContext(),"Parámetro Correo electrónico incorrecto",Toast.LENGTH_SHORT);
                        message_correo.show();
                    }
                    else
                    {
                        //Mensaje emergente indicando el fallo
                        Toast message_varios = Toast.makeText(getApplicationContext(),"Múltiples parámetros incorrectos",Toast.LENGTH_SHORT);
                        message_varios.show();
                    }
                }
            }
        }
    }
}