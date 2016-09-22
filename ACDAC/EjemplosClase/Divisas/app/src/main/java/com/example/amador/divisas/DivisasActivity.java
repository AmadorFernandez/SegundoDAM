package com.example.amador.divisas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class DivisasActivity extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    RadioButton dolarEuro;
    RadioButton euroDolar;
    Button btnConversor;
    EditText dolar;
    EditText euro;
    RadioGroup contenedor;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_divisas);
        inicializar();
    }

    @Override
    public void onClick(View view) {

        String resultado = " ";
        double valor = 0.0;
        String campo = "";


        //Se comprueba cual es RadioButton seleccionado
        if(dolarEuro.isChecked()){

            campo = dolar.getText().toString();

            //Comprobamos que el campo es valido para la conversión.
            //De ser valido se convierte y se llama a la clase Conversor para el cambio de divisa
            if(comprobarValidez(campo)) {

                valor = Double.parseDouble(campo);
                resultado = String.format("%.2f", Conversor.dolarAEuro(valor));
                euro.setText(String.valueOf(resultado));
            }

        }else{

            campo = euro.getText().toString();

            if(comprobarValidez(campo)) {

                valor = Double.parseDouble(euro.getText().toString());
                resultado = String.format("%.2f", Conversor.euroADolar(valor));
                dolar.setText(String.valueOf(resultado));
            }

        }

    }

    //Asigna el foco al EdiText correspondiente
    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {

        if(i == R.id.rbDolarEuro){

            dolar.requestFocus();
        }else{

            euro.requestFocus();
        }
    }

    //Inicializa los objetos con las referencias al xml
    protected void inicializar(){

        dolarEuro = (RadioButton)findViewById(R.id.rbDolarEuro);
        euroDolar = (RadioButton)findViewById(R.id.rbEuroDolar);
        btnConversor = (Button)findViewById(R.id.btnConvertir);
        dolar = (EditText)findViewById(R.id.edDolares);
        euro = (EditText)findViewById(R.id.edEuros);
        contenedor = (RadioGroup)findViewById(R.id.rg);
        contenedor.setOnCheckedChangeListener(this);
        btnConversor.setOnClickListener(this);


    }

    //Comprueba si un String esta es valido para su conversión a double, devuelve falso si no lo es y verdadero en caso contrario
    public boolean comprobarValidez(String texto){

        return !texto.isEmpty() && texto != null;
    }
}
