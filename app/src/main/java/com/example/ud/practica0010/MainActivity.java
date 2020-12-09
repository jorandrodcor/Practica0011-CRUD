package com.example.ud.practica0010;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

import java.sql.SQLData;

public class MainActivity extends AppCompatActivity {
    private SQLiteDatabase mydatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CrearoIniciarDB();
        BorrarTodo();
        Log.e("BD_NumReg", String.valueOf(getNumRegistros()));
        InsertarRegistros();
        Log.e("BD_NumReg", String.valueOf(getNumRegistros()));
        ConsultarRegistros();
        ActualizarRegistro();
        ConsultarRegistros();
        BorrarRegistro();
        Log.e("BD_NumReg", String.valueOf(getNumRegistros()));
    }

    private void ConsultarRegistros(){
        Cursor Consulta01 = mydatabase.rawQuery("SELECT * FROM PERSONA", null);
        int NombreIndex = Consulta01.getColumnIndex("NOMBRE");
        int EdadIndex = Consulta01.getColumnIndex("EDAD");
        int GeneroIndex = Consulta01.getColumnIndex("GENERO");
        Log.e("BD INDICES", "[Nombre: "+NombreIndex+", Edad: "+EdadIndex+", Genero: "+GeneroIndex+"]");
        while(Consulta01.moveToNext()){
            int NumRegistros = Consulta01.getInt(0);
            String Nombre = Consulta01.getString(NombreIndex);
            int Edad = Consulta01.getInt(EdadIndex);
            int Genero = Consulta01.getInt(GeneroIndex);
            Log.e("BD Registro No: ",String.valueOf(NumRegistros));
            Log.e("BD Datos: ","Nombre: "+Nombre+", Edad: "+String.valueOf(Edad)+""+
                    ", Genero: "+String.valueOf(Genero));
        }
        Consulta01.close();
    }//ConsultarRegistros()

    private void CrearoIniciarDB(){
        try{
            mydatabase = this.openOrCreateDatabase("MYDB",MODE_PRIVATE,null);
            mydatabase.execSQL("CREATE TABLE IF NOT EXISTS PERSONA (ID INT, NOMBRE VARCHAR, EDAD INT, GENERO INT)");
            Log.e("BD creada", "Se creo la database y la tabla OK");
        }
        catch (Exception Ex){
            Log.e("ERROR", Ex.getMessage());
        }
    }//CrearoIniciarDB

    private void BorrarRegistro(){
        mydatabase.execSQL("DELETE FROM PERSONA WHERE ID = 1");
        Log.e("BD DelReg", "OK");
    }//ActualizarRegistro()

    private void ActualizarRegistro(){
        mydatabase.execSQL("UPDATE PERSONA SET EDAD = 45 WHERE ID = 1");
        Log.e("BD ActReg", "OK");
    }//BorrarRegistro()

    private int getNumRegistros(){
        Cursor Consulta01 = mydatabase.rawQuery("SELECT COUNT (*) FROM PERSONA", null);
        Consulta01.moveToFirst();
        int NumRegistros = Consulta01.getInt(0);
        Consulta01.close();
        return NumRegistros;
    }//getNumRegistros

    private void BorrarTodo(){
        mydatabase.execSQL("DELETE FROM PERSONA");
        Log.e("BD DelReg", "OK");
    }



    private void InsertarRegistros(){
        //String ID = String.valueOf(getNumRegistros()+1);
        mydatabase.execSQL("INSERT INTO PERSONA (id, nombre, genero, edad)values (1,'Felipe',0,49);");
        mydatabase.execSQL("INSERT INTO PERSONA (id, nombre, genero, edad)values (2,'Luisa',1,18);");
        mydatabase.execSQL("INSERT INTO PERSONA (id, nombre, genero, edad)values (3,'Javier',0,25);");
        Log.e("BD InsReg", "OK");

    }//InsertarRegistros





}