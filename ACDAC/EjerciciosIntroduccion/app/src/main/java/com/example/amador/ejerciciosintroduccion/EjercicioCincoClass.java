package com.example.amador.ejerciciosintroduccion;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.net.JarURLConnection;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class EjercicioCincoClass extends AppCompatActivity {

    Palabra p;
    DiccionarioSingle diccionario;
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
        diccionario = DiccionarioSingle.getDiccionario();


        //Recoge el valor del campo al hacer click
        btnRecogerPalabra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String palabra = edtPalabra.getText().toString();

                //Comprueba los distintos casos posibles Ver metodos
                if(palabra.isEmpty()){

                    lanzarToast("No hay palabra en el campo de texto");


                }else if(validarExpresion(palabra)) {

                    pedirConfirmacion("Confirmación", "¿Desea guardar la palabra?").show();

                }else {

                    lanzarToast("Expresión regular incumplida");
                }

            }
        });

        //I send the diccionary to second Activity
        btnAbrirDiccionario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(), InfoPalabraClass.class);
                startActivity(i);

            }
        });

    }

    //Pide confirmación para guardar la palabra
    private Dialog pedirConfirmacion(String titulo, String mensaje){

        AlertDialog.Builder popup = new AlertDialog.Builder(EjercicioCincoClass.this);

        popup.setTitle(titulo);
        popup.setMessage(mensaje);
        //En caso de aceptar
        popup.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                String texto = edtPalabra.getText().toString().trim();


                    //Comprueba que la palabra no este ya en el diccionario
                    if(!diccionario.contains(texto)){

                        diccionario.add(texto);
                        edtPalabra.setText("");
                        lanzarToast("La palabra "+texto+" fue guardada");

                    }else{

                        lanzarToast("Esa palabra ya estaba incluida en el diccionario");
                    }




            }
        });

        //En caso de rechazar
        popup.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.cancel();

            }});

        return popup.create();

    }

    //Lanza el mensaje que se le indique
    private void lanzarToast(String msg){

        Toast.makeText(EjercicioCincoClass.this, msg, Toast.LENGTH_LONG).show();

    }

    //Valida que solo tenga los caracteres permitidos en una palabra (nada de números ni signos)
    private boolean validarExpresion(String palabra){

        return palabra.matches("^[a-zA-Z_áéíóúñ\\s]*$");

    }









}
