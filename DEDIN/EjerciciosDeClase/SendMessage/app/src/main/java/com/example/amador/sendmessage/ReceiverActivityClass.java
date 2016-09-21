package com.example.amador.sendmessage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ReceiverActivityClass extends AppCompatActivity {


    String memRecibido = " ";
    TextView tvMostrar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receiver);
        asignarMemoria();
        accion();
    }

    protected void accion(){

        // Extrae el string que se agrego al intent de la actividad llamante por su nombre
        memRecibido = getIntent().getExtras().getString("parametro");
        tvMostrar.setText(memRecibido);
    }

    // Asigna la memoria y enlaza el objeto con la vista
    void asignarMemoria(){

        tvMostrar = (TextView)findViewById(R.id.tvMostrar);
    }
}
