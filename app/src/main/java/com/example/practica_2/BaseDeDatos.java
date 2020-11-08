package com.example.practica_2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class BaseDeDatos extends SQLiteOpenHelper {

    //Constructor de la clase que llama al constructor de SQLiteOpenHelper
    public BaseDeDatos(Context contexto) {
        super(contexto, Constantes.BASE_DATOS, null, 1);
    }

    //Al crearse la clase se inserta una tabla en la base de datos
    @Override
    public void onCreate(SQLiteDatabase db) {
        //Ejecuta una sentencia sql para crear la tabla
        db.execSQL("CREATE TABLE "+Constantes.TABLA_FORMULARIO+" ( int "+Constantes._ID+" PRIMARY KEY AUTOINCREMENT, varchar2 "+Constantes.NOMBRE+" (90), varchar2 "+Constantes.DNI+" (90), varchar2 "+Constantes.CORREO+" (90), varchar2 "+Constantes.NACIONALIDAD+" (90), varchar2 "+Constantes.BOLETIN_NOTICIAS +" (90) );");
    }

    //Al actualizarse se borra la tabla si existe y se vuelve a crear
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //Borra la tabla
        db.execSQL("DROP TABLE IF EXISTS "+Constantes.TABLA_FORMULARIO+";");
        //La vuelve a crear
        db.execSQL("CREATE TABLE IF NOT EXISTS "+Constantes.TABLA_FORMULARIO+" ( int "+Constantes._ID+" PRIMARY KEY AUTOINCREMENT, varchar2 "+Constantes.NOMBRE+" (90), varchar2 "+Constantes.DNI+" (90), varchar2 "+Constantes.CORREO+" (90), varchar2 "+Constantes.NACIONALIDAD+" (90), varchar2 "+Constantes.BOLETIN_NOTICIAS+" (90) );");

    }

    //Con este método se obtienen los datos que hay almacenados en la base de datos
    public ArrayList<DatosRegistro> obtenerDatos()
    {
        //Se crea un arraylist de objetos DatosRegistro
        ArrayList<DatosRegistro> datosRegistros = new ArrayList<DatosRegistro>();
        //Se obtiene una base de datos con permiso de lectura
        SQLiteDatabase new_db = this.getReadableDatabase();
        //Se crea un cursor que almacena el resultado de la consulta
        Cursor datos = new_db.query(Constantes.TABLA_FORMULARIO,new String[]{Constantes._ID,Constantes.NOMBRE,Constantes.DNI,Constantes.CORREO,Constantes.NACIONALIDAD,Constantes.BOLETIN_NOTICIAS},null,null,null,null, Constantes._ID);
        //Se recorre el cursor y se añade un nuevo objeto DatosRegistro al arraylist
        while(datos.moveToNext()==true)
        {
            datosRegistros.add(new DatosRegistro(datos.getInt(0),datos.getString(1),datos.getString(2),datos.getString(3),datos.getString(4),datos.getString(5)));
        }

        //Se cierra la base de datos
        new_db.close();

        //Se retorna el arraylist
        return datosRegistros;
    }

    public long registrarDatos(DatosRegistro datos)
    {
        //Se obtiene una base de datos conn permiso de escritura
        SQLiteDatabase new_db = this.getWritableDatabase();
        //Se crea un ContentValues para almacenar los valores que tendrá la sentencia insert
        ContentValues valores = new ContentValues();

        //Se introducen los valores en el ContentValues, indicando el nombre de la columna y el valor a insertar
        valores.put(Constantes.NOMBRE, datos.getNombre());
        valores.put(Constantes.DNI, datos.getDni());
        valores.put(Constantes.CORREO, datos.getCorreo());
        valores.put(Constantes.NACIONALIDAD, datos.getNacionalidad());
        valores.put(Constantes.BOLETIN_NOTICIAS, datos.getBoletin());

        //Se ejecuta el insert y se guarda el resultado en una variable que retornaremos después
        long resultado = new_db.insert(Constantes.TABLA_FORMULARIO,null, valores);

        //Cerramos la base de datos
        new_db.close();

        //Devolvemos el resultado del insert
        return resultado;
    }

    public void eliminarDatos(String id)
    {
        //Obtenemos una base de datos con permiso de escritura
        SQLiteDatabase new_db = this.getWritableDatabase();
        //Ejecutamos un delete que tenga como cláusula del where _ID = id del objeto a borrar
        new_db.delete(Constantes.TABLA_FORMULARIO, Constantes._ID+"=?", new String[]{id});
        //Cerramos la base de datos
        new_db.close();
    }
}
