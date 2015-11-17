package com.belu.upiicsamath.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.belu.upiicsamath.model.Alumno;

/**
 * Created by Betza Ojeda on 13/11/2015.
 */
public class AlumnoDAO {
    //Abrimos la base de datos 'UpiicsaHorario' en modo escritura
    UpicsaHorarioSQLiteHelper db_horario;

    public AlumnoDAO(Context contexto) {
        db_horario = new UpicsaHorarioSQLiteHelper(contexto, "HorarioUpiicsa", null, 1);
    }

    //Metodo para sobreescribir la base de datos
    SQLiteDatabase db = db_horario.getWritableDatabase();
/*
    //Insertamos los datos en la tabla Usuarios
    public void Agregar() {
         String sql = "INSERT INTO Alumno (id_boleta,nombre,apellido_paterno,apellido_materno,licenciatura) VALUES ('" + id_boleta + "' , '" + nombre + "' , '"+ apellido_paterno +"' , '" + apellido_ materno + "', '" +licenciatura + "') ";
         db.execSQL(sql);
    }

    //Eliminar un registro
    public void Eliminar(){
        db.execSQL("DELETE FROM Usuarios WHERE codigo=6 ");
        //Alternativa 2: método update()
      	ContentValues valores = new ContentValues();
      	valores.put("nombre", nom);
      	db.update("Usuarios", valores, "codigo=" + cod, null);
    }


    public void Actualizar(){

        db.execSQL("UPDATE Usuarios SET nombre='usunuevo' WHERE codigo=6 ");
    }
    //Cerramos la base de datos
    db.close();
    */
    }

