package com.example.amador.ejerciciosintroduccion;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

import java.net.URL;

public class EjercicioTresClass extends AppCompatActivity {


    EditText edtUrl;
    Button btnNavegar;
    WebView navegador;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejercicio_tres);
        inicializar();
    }

    //Inicializa los objetos y apunta el boton a su evento (ver codigo)
    private void inicializar() {


        edtUrl = (EditText)findViewById(R.id.edtNavegador);
        btnNavegar = (Button)findViewById(R.id.btnNavegar);
        navegador = (WebView)findViewById(R.id.wvwNavegador);
        edtUrl.setSelection(edtUrl.getText().length());



        btnNavegar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                
                navegador = (WebView)findViewById(R.id.wvwNavegador);
                String url = "";
                WebSettings opciones = navegador.getSettings();
                opciones.setJavaScriptEnabled(true); //Activa las opciones de JS
                url = edtUrl.getText().toString();

                //Verifica que la url no sea vacia
                if(!url.isEmpty()){

                    navegador.loadUrl(url);

                }



            }
        });

    }
}
