package com.example.usrgam.archivos;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import control.LeerEscribirArchivo;
import modelo.Persona;

public class MainActivity extends AppCompatActivity {

    private LeerEscribirArchivo lea = new LeerEscribirArchivo();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void crearArchivo(View v) {
        //crearArchivoPlano();
        lea.escribirArchivo(new Persona("Ronny", "Cabrera", "1723029441"), "persona p");
    }

    public void leerArchivo(View v) {
        //leerArchivoPlano();
        Persona p = lea.leerArchivo("persona p");
        Toast.makeText(getApplicationContext(), p.toString(), Toast.LENGTH_LONG).show();
    }

    private void crearArchivoPlano() {
        OutputStreamWriter escribir = null;
        try {
            escribir = new OutputStreamWriter(openFileOutput("fichero.txt", Context.MODE_PRIVATE));
            escribir.write("texto prueba");
        } catch (FileNotFoundException e) {
            Log.e("error archivo : ", e.toString());
            //e.printStackTrace();
        } catch (IOException e) {
            Log.e("error escribir : ", e.toString());
            //e.printStackTrace();
        }
        finally {
            if(escribir != null) {
                try {
                    escribir.close();
                } catch (IOException e) {
                    Log.e("error escribir : ", e.toString());
                    //e.printStackTrace();
                }
            }
        }
    }

    private void leerArchivoPlano() {
        InputStreamReader leer = null;
        BufferedReader br = null;
        try {
            leer = new InputStreamReader(openFileInput("fichero.txt"));
            br = new BufferedReader(leer);
            String texto = br.readLine();
            while (texto != null) {
                Toast.makeText(getApplicationContext(), texto, Toast.LENGTH_LONG);
                texto = br.readLine();
            }
        } catch (FileNotFoundException e) {
            Log.e("error not found : ", e.toString());
            //e.printStackTrace();
        } catch (IOException e) {
            Log.e("error IO : ", e.toString());
            //e.printStackTrace();
        }
    }
}
