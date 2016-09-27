package com.example.amador.ejerciciosintroduccion;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.net.JarURLConnection;

public class EjercicioCincoClass extends AppCompatActivity {

    Palabra p;
    GestPalabras diccionario;
    EditText edtPalabra;
    Button btnRecogerPalabra;


   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejercicio_cinco);
        inicializar();

    }

    private void inicializar() {

        edtPalabra = (EditText)findViewById(R.id.edtPalabra);
        btnRecogerPalabra = (Button)findViewById(R.id.btnComprobarPalabra);
        diccionario = new GestPalabras();

    }

    public void comenzar(){

    }







}
