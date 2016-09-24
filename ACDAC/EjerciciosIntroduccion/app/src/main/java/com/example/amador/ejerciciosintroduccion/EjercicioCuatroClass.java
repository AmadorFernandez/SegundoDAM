package com.example.amador.ejerciciosintroduccion;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

public class EjercicioCuatroClass extends AppCompatActivity {



    Crono crono;
    Button btnSumarTiempo, btnRestarTiempo, btnStar;
    TextView txvCrono;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejercicio_cuatro);
        inicializar();

    }

    private void inicializar() {

        btnStar = (Button)findViewById(R.id.btnLanzarChrono);
        btnSumarTiempo = (Button)findViewById(R.id.btnSumarTiempo);
        txvCrono = (TextView)findViewById(R.id.txvTiempoCrono);
        crono = new Crono(3556, true);

        btnStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                crono.start();
            }
        });


        crono.setOnCronoListener(new CronoListener() {
            @Override
            public void onTick(Crono c) {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        txvCrono.setText(crono.getContador());

                    }
                });

            }
        });

    }
}
