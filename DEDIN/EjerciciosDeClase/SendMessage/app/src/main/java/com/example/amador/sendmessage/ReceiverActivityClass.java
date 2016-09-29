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


    private String memRecibido = " ";
    private TextView tvMostrar;



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

       // It extrac the message
        tvMostrar.setText(memRecibido);


    }

    // Greets the user with the received text
    void asignarMemoria(){

        tvMostrar = (TextView)findViewById(R.id.tvMostrar);
        memRecibido = getIntent().getExtras().getString("parametro");
    }
}
