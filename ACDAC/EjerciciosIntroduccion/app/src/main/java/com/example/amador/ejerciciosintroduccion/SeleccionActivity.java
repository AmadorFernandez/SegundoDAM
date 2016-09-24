package com.example.amador.ejerciciosintroduccion;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SeleccionActivity extends AppCompatActivity {


    Button btnUno;
    Button btnDos;
    Button btnTres;
    Button btnCuatro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccion);
        inicializar();
    }

    private void inicializar() {

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

    }


}
