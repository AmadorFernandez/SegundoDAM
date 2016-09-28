package com.example.amador.ejerciciosintroduccion;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class InfoPalabraClass extends AppCompatActivity {


    ListView livListaPalabras;
    ArrayList<String> palabras;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_info_palabra);
            inicializar();
    }

    //Inicializa los objetos, extrae el intent de la activity emisora, carga la cabezera en el ListView y le asigna su adapter
    //Ver clase DiccionarioAdapter
    private void inicializar() {

        View header = (View)getLayoutInflater().inflate(R.layout.layout_cabezera, null);
        palabras = getIntent().getExtras().getStringArrayList("parametroUno");
        DiccionarioAdapter adapter = new DiccionarioAdapter(InfoPalabraClass.this, palabras);
        livListaPalabras = (ListView)findViewById(R.id.listPalabras);
        livListaPalabras.addHeaderView(header);
        livListaPalabras.setAdapter(adapter);



    }


}
