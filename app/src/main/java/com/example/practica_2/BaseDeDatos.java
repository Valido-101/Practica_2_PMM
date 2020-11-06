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

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Ejecuta una sentencia sql para crear la tabla
        db.execSQL("CREATE TABLE "+Constantes.TABLA_FORMULARIO+" ( int "+Constantes._ID+" PRIMARY KEY AUTOINCREMENT, varchar2 "+Constantes.NOMBRE+" (90), varchar2 "+Constantes.DNI+" (90), varchar2 "+Constantes.CORREO+" (90), varchar2 "+Constantes.NACIONALIDAD+" (90), varchar2 "+Constantes.BOLETIN_NOTICIAS +" (90) );");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //Borra la tabla
        db.execSQL("DROP TABLE IF EXISTS "+Constantes.TABLA_FORMULARIO+";");
        //La vuelve a crear
        db.execSQL("CREATE TABLE IF NOT EXISTS "+Constantes.TABLA_FORMULARIO+" ( int "+Constantes._ID+" PRIMARY KEY AUTOINCREMENT, varchar2 "+Constantes.NOMBRE+" (90), varchar2 "+Constantes.DNI+" (90), varchar2 "+Constantes.CORREO+" (90), varchar2 "+Constantes.NACIONALIDAD+" (90), varchar2 "+Constantes.BOLETIN_NOTICIAS+" (90) );");

    }

    public ArrayList<DatosRegistro> obtenerDatos()
    {
        ArrayList<DatosRegistro> datosRegistros = new ArrayList<DatosRegistro>();
        SQLiteDatabase new_db = this.getReadableDatabase();
        Cursor datos = new_db.query(Constantes.TABLA_FORMULARIO,new String[]{Constantes._ID,Constantes.NOMBRE,Constantes.DNI,Constantes.CORREO,Constantes.NACIONALIDAD,Constantes.BOLETIN_NOTICIAS},null,null,null,null, Constantes._ID);
        while(datos.moveToNext()==true)
        {
            datosRegistros.add(new DatosRegistro(datos.getInt(0),datos.getString(1),datos.getString(2),datos.getString(3),datos.getString(4),datos.getString(5)));
        }

        new_db.close();

        return datosRegistros;
    }

    public long registrarDatos(DatosRegistro datos)
    {
        SQLiteDatabase new_db = this.getWritableDatabase();
        ContentValues valores = new ContentValues();

        valores.put(Constantes.NOMBRE, datos.getNombre());
        valores.put(Constantes.DNI, datos.getDni());
        valores.put(Constantes.CORREO, datos.getCorreo());
        valores.put(Constantes.NACIONALIDAD, datos.getNacionalidad());
        valores.put(Constantes.BOLETIN_NOTICIAS, datos.getBoletin());

        return new_db.insert(Constantes.TABLA_FORMULARIO,null, valores);
    }

    public void eliminarDatos(String id)
    {
        SQLiteDatabase new_db = this.getWritableDatabase();
        new_db.delete(Constantes.TABLA_FORMULARIO, Constantes._ID+"=?", new String[]{id});
        new_db.close();
    }
}
