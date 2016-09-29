package com.example.amador.ejerciciosintroduccion;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SeleccionActivity extends AppCompatActivity {


    Button btnUno;
    Button btnDos;
    Button btnTres;
    Button btnCuatro;
    Button btnCinco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccion);
        inicializar();


    }

    //Selecciona la Activity a lanzar en cada caso
    private void inicializar(){

        btnUno = (Button)findViewById(R.id.btnEjUno);
        btnUno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(), EjercicioUnoClass.class);
                startActivity(i);

            }
        });
        btnDos = (Button)findViewById(R.id.btnEjDos);
        btnDos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(), EjercicioDosClass.class);
                startActivity(i);

            }
        });
        btnTres = (Button)findViewById(R.id.btnEjTres);
        btnTres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(), EjercicioTresClass.class);
                startActivity(i);

            }
        });
        btnCuatro = (Button)findViewById(R.id.btnEjCuatro);
        btnCuatro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), EjercicioCuatroClass.class);
                startActivity(i);
            }
        });
        btnCinco = (Button)findViewById(R.id.btnEjCinco);
        btnCinco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), EjercicioCincoClass.class);
                startActivity(i);

            }
        });


    }


}
