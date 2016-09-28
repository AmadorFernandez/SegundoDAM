package com.example.amador.ejerciciosintroduccion;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

    //Inicializa los objetos y apunta el boton a su evento click
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

    //Convierte el valor (Ver el codigo)
    private void conversion(){

        try {
            //Verifica que el texto no este en blanco
            if (!edtConvertir.getText().toString().isEmpty()) {

                //Convierte el texto a tipo double, después a puldas y muestra el resultado
                valor = Double.parseDouble(edtConvertir.getText().toString());
                valor *= VALOR_PULGADAS;
                txvResultEJ2.setText(String.valueOf(valor) + "Pulgadas");
            }
        }catch (NumberFormatException e){

            Toast.makeText(EjercicioDosClass.this, "Formato incorrecto", Toast.LENGTH_LONG).show();

        }catch (NullPointerException e){

            Toast.makeText(EjercicioDosClass.this, "Parametro nulo", Toast.LENGTH_LONG).show();

        }catch (Exception e){

            Toast.makeText(EjercicioDosClass.this, "Se produjo el siguiente error "+e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }
}
