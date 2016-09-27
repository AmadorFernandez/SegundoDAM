package com.example.amador.ejerciciosintroduccion;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class InfoPalabraClass extends AppCompatActivity {

    Button btnBorrarPalabra, btnInfoPalabra;
    ListView livListaPalabras;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_info_palabra);
            inicializar();


    }

    private void inicializar() {

        View header = (View)getLayoutInflater().inflate(R.layout.layout_cabezera, null);
        ArrayList<String> palabras = getIntent().getExtras().getStringArrayList("parametroUno");
        DiccionarioAdapter adapter = new DiccionarioAdapter(this, palabras);
        btnBorrarPalabra = (Button)findViewById(R.id.imvBorrarPalabra);
        btnInfoPalabra = (Button)findViewById(R.id.imvInfoPalabra);
        livListaPalabras = (ListView)findViewById(R.id.listPalabras);
        livListaPalabras.addHeaderView(header);
        livListaPalabras.setAdapter(adapter);


    }


}
