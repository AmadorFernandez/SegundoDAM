package com.example.amador.ejerciciosintroduccion;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class EjercicioDosClass extends AppCompatActivity {

    Button btnConvertir;
    EditText edtConvertir;
    TextView txvResultEJ2;
    double valor;
    private static final double VALOR_PULGADAS = 0.39;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejercicio_dos);
        inicializar();
    }

    private void inicializar() {

        btnConvertir = (Button)findViewById(R.id.btnConvertCmPulgadas);
        edtConvertir = (EditText)findViewById(R.id.edtCmAPulgadas);
        txvResultEJ2 = (TextView)findViewById(R.id.txvResulEJ2);
        valor = 0.0;
        btnConvertir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                conversion();

            }
        });
    }

    private void conversion(){

        if(!edtConvertir.getText().toString().isEmpty()){

            valor = Double.parseDouble(edtConvertir.getText().toString());
            valor *= VALOR_PULGADAS;
            txvResultEJ2.setText(String.valueOf(valor)+ "Pulgadas");
        }

    }
}
