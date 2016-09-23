package com.example.amador.sendmessage;

import android.app.Notification;
import android.content.ContentProvider;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Scene;
import android.widget.TextView;

import static java.lang.System.gc;


/**
 * Clase que envía un mensaje a otra activity
 * @see android.app.Activity
 * @author Amador Fernández
 * @version 1.0
 * */
public class ReceiverActivityClass extends AppCompatActivity {


    String memRecibido = " ";
    TextView tvMostrar;



    /**
     * Método que crea la instancia
     * @param savedInstanceState @see {@link Bundle}
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receiver);
        asignarMemoria();
        accion();




    }



    protected void accion(){

        // Extrae el string que se agrego al intent de la actividad llamante por su nombre
       // memRecibido = getIntent().getExtras().getString("parametro");
        memRecibido = getIntent().getExtras().getString("message");
        tvMostrar.setText(memRecibido);


    }

    // Asigna la memoria y enlaza el objeto con la vista
    void asignarMemoria(){

        tvMostrar = (TextView)findViewById(R.id.tvMostrar);
    }
}
