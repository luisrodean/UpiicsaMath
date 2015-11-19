package com.belu.upiicsamath.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.belu.upiicsamath.model.Alumno;

/**
 * Created by Betza Ojeda on 13/11/2015.
 */
public class AlumnoDAO {
    //Abrimos la base de datos 'UpiicsaHorario' en modo escritura
    private UpicsaHorarioSQLiteHelper db_horario;
    private SQLiteDatabase db;
    private Context contexto;

    public AlumnoDAO(Context contexto) {
        this.contexto = contexto;
        db_horario = new UpicsaHorarioSQLiteHelper(contexto, "HorarioUpiicsa", null, 1);
    }



    //Insertamos los datos en la tabla Usuarios
    public void Agregar(String id_boleta, String nombre , String apellido_paterno, String apellido_materno,  String licenciatura) {
        try{
            //Metodo para sobreescribir la base de datos
            db = db_horario.getWritableDatabase();
            String sql = "INSERT INTO Alumno (id_boleta,nombre,apellido_paterno,apellido_materno,licenciatura) VALUES (" + id_boleta + " , '" + nombre + "' , '"+ apellido_paterno +"' , '" + apellido_materno + "', '" +licenciatura + "') ";
            db.execSQL(sql);
            Toast.makeText(contexto, "Alumno Guardado", Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Toast.makeText(contexto, "Error al guardar alumno", Toast.LENGTH_SHORT).show();
        }finally {
            db.close();
        }
    }
/*
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

