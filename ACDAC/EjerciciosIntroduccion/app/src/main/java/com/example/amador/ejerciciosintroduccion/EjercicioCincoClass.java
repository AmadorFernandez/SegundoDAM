package com.example.amador.ejerciciosintroduccion;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.net.JarURLConnection;

public class EjercicioCincoClass extends AppCompatActivity {

    TextView txvTablero;
    ImageButton btnComenzar;
    Button btnPuto;
    String tableroAdaptado;
    Tablero juego;

   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejercicio_cinco);
        inicializar();

    }

    private void inicializar() {

        txvTablero = (TextView)findViewById(R.id.txvTablero);
        btnComenzar = (ImageButton)findViewById(R.id.imbtnComenzar);
        btnPuto = (Button)findViewById(R.id.btnPuto);
        btnComenzar.setClickable(true);
        tableroAdaptado = " ";
        juego = new Tablero(18, 50);


        btnPuto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                comenzar();

                tableroAdaptado = "";
                juego.avanzarGeneracion();
            }
        });

        btnComenzar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                        tableroAdaptado = "";
                        comenzar();
                        juego.avanzarGeneracion();



            }
        });

    }

    public void comenzar(){



        for(int i = 0; i < juego.getmTablero().length; i++){

            for(int j = 0; j < juego.getmTablero()[i].length; j++){

                tableroAdaptado = tableroAdaptado + juego.getmTablero()[i][j];

            }

        }

        txvTablero.setText(tableroAdaptado.toString());


    }




}
