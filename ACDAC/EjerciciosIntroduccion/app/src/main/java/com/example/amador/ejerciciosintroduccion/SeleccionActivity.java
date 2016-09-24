package com.example.amador.ejerciciosintroduccion;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SeleccionActivity extends AppCompatActivity {


    Button btnUno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccion);
        inicializar();
    }

    private void inicializar() {

        btnUno = (Button)findViewById(R.id.btnEjUno);

    }

    public void getOnClick(View v){

        Intent intent = new Intent(getApplicationContext(), EjercicioUnoClass.class);
        startActivity(intent);

    }
}
