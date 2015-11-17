package com.belu.upiicsamath.database;

import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
/**
 * Created by Betza Ojeda on 13/11/2015.
 */
public class UpicsaHorarioSQLiteHelper extends SQLiteOpenHelper {

    //Sentencia SQL para crear la tabla de Alumno
    String sqlCreate = "CREATE TABLE IF NOT EXISTS Alumno (id_boleta INTEGER, nombre TEXT, apellido_paterno TEXT, apellido_materno TEXT, licencitura TEXT)";

    public UpicsaHorarioSQLiteHelper(Context contexto, String nombre,
                                SQLiteDatabase.CursorFactory factory, int version) {
        super(contexto, nombre, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Se ejecuta la sentencia SQL de creación de la tabla
        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAnterior, int versionNueva) {


        //Se elimina la versión anterior de la tabla
        db.execSQL("DROP TABLE IF EXISTS Alumno");

        //Se crea la nueva versión de la tabla
        db.execSQL(sqlCreate);
    }
}

