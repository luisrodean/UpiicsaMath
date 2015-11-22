package com.belu.upiicsamath.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;
import android.widget.Toast;

import com.belu.upiicsamath.model.Grupo;
import com.belu.upiicsamath.model.Horario;
import com.belu.upiicsamath.model.HorarioAdapter;
import com.belu.upiicsamath.tool.Constante;

import java.util.ArrayList;

/**
 * Created by Betza Ojeda on 13/11/2015.
 */
public class GrupoDAO {
    //Abrimos la base de datos 'UpiicsaHorario' en modo escritura
    private UpicsaHorarioSQLiteHelper db_horario;
    private SQLiteDatabase db;
    private Context contexto;

    public GrupoDAO(Context contexto) {
        this.contexto = contexto;
        db_horario = new UpicsaHorarioSQLiteHelper(contexto, "HorarioUpiicsa", null, 1);
    }



    //Insertamos los datos en la tabla Usuarios
    public void Agregar(Grupo grupo,Horario horario) {
        try{
            //Metodo para sobreescribir la base de datos
            db = db_horario.getWritableDatabase();
            String sql = Constante.sqlInsertGrupo + "VALUES ( '"
                    + grupo.getId_grupo() + "' , '"
                    + grupo.getId_secuencia() + "' , '"
                    + grupo.getNombre_uap() + "' , '"
                    + grupo.getNombre_profesor() + "' , '"
                    + grupo.getApellido_paterno_profesor() + "' , '"
                    + grupo.getApellido_materno_profesor() + "' , '"
                    + horario.getId_dia() + "' , '"
                    + horario.getHora_inicio() + "' , '"
                    + horario.getHora_fin() + "' , '"
                    + horario.getNombre_edificio() + "' , '"
                    + horario.getSalon() + "' , '"
                    + horario.getDir_host() + "' , '"
                    + horario.getPassword()
                    + "') ";
            db.execSQL(sql);
            Log.d("--> ", "Grupo Guardado");
        }catch (SQLiteException e){
            Log.d("--> ", "Error al guardar Grupo  --->  " + e.getMessage());
        }finally {
            db.close();
        }
    }

    public ArrayList<HorarioAdapter> getHorarioPorDia(String dia){
        ArrayList<HorarioAdapter> listaHorario = new ArrayList<>();
        try{
            db = db_horario.getReadableDatabase();
            Cursor c = db.rawQuery( Constante.sqlSelectGrupo , new String[] {dia});
            //Nos aseguramos de que existe al menos un registro
            if (c.moveToFirst()) {
                //Recorremos el cursor hasta que no haya más registros

                do {
                    HorarioAdapter horario = new HorarioAdapter();

                    horario.setId_secuencia(c.getString(0));
                    horario.setNombre_uap(c.getString(1));
                    horario.setNombre_profesor(c.getString(2));
                    horario.setApellido_paterno_profesor(c.getString(3));
                    horario.setApellido_materno_profesor(c.getString(4));
                    horario.setId_dia(c.getString(5));
                    horario.setHora_inicio(c.getString(6));
                    horario.setHora_fin(c.getString(7));
                    horario.setNombre_edificio(c.getString(8));
                    horario.setSalon(c.getString(9));

                    listaHorario.add(horario);
                } while(c.moveToNext());
            }
        }catch (SQLiteException e){
            Toast.makeText(contexto, "Error al guardar Grupo", Toast.LENGTH_SHORT).show();
        }finally {
            db.close();
        }

        return listaHorario;
    }

    //Eliminar un registro
    public void Eliminar(){
        db.execSQL("DELETE FROM Alumno WHERE id_boleta=?");
        db.close();
    }    /*
        //Alternativa 2: método update()
      	ContentValues valores = new ContentValues();
      	valores.put("nombre", nom);
      	db.update("Usuarios", valores, "codigo=" + cod, null);*/



    public void Actualizar(){
        db.execSQL("UPDATE Alumno SET nombre='usunuevo' WHERE codigo=6 ");
        db.close();
        }

    }

