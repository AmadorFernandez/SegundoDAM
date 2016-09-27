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
import java.util.ArrayList;

public class EjercicioCincoClass extends AppCompatActivity {

    Palabra p;
    ArrayList<String> diccionario;
    EditText edtPalabra;
    Button btnRecogerPalabra, btnAbrirDiccionario;


   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejercicio_cinco);
        inicializar();

    }

    private void inicializar() {

        edtPalabra = (EditText)findViewById(R.id.edtPalabra);
        btnRecogerPalabra = (Button)findViewById(R.id.btnGuaPalabra);
        btnAbrirDiccionario = (Button)findViewById(R.id.btnAbrirDiccionario);
        diccionario = new ArrayList<String>();


        btnRecogerPalabra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                diccionario.add(edtPalabra.getText().toString());
            }
        });

        btnAbrirDiccionario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(), InfoPalabraClass.class);
                i.putExtra("parametroUno", diccionario);
                startActivity(i);

            }
        });

    }









}
