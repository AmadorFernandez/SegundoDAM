package com.example.amador.ejerciciosintroduccion;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CambioRatio extends AppCompatActivity {

    String valorEuro;
    String valorDolar;
    Button btnRegreso;
    EditText edtValorEuro;
    EditText edtValorDolar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambio_ratio);
        inicializar();

    }

    protected void inicializar(){

        valorEuro =
        valorDolar = "";
        btnRegreso = (Button)findViewById(R.id.btnRegresoCambioRatio);
        edtValorDolar = (EditText)findViewById(R.id.edtCambioRatioDolar);
        edtValorEuro = (EditText)findViewById(R.id.edtCambioEuro);
        edtValorDolar.setText(String.valueOf(getIntent().getExtras().getDouble("parametroUno")));
        edtValorEuro.setText(String.valueOf(getIntent().getExtras().getDouble("parametroDos")));


        btnRegreso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i;


                if(!edtValorDolar.getText().toString().isEmpty() && !edtValorEuro.getText().toString().isEmpty()){

                    i = getIntent();

                    i.putExtra("valorEuro", edtValorEuro.getText().toString());
                    i.putExtra("valorDolar", edtValorDolar.getText().toString());
                    setResult(RESULT_OK, i);



                }else{

                    setResult(RESULT_CANCELED);

                }

                finish();

            }
        });


    }
}
