package com.example.amador.ejerciciosintroduccion;

import android.content.Intent;
import android.os.Bundle;
import android.renderscript.Double2;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import javax.xml.datatype.Duration;

/**
 * Created by amador on 23/09/16.
 */

public class EjercicioUnoClass extends AppCompatActivity {

    RadioButton dolarEuro;
    RadioButton euroDolar;
    Button btnConversor;
    EditText dolar;
    EditText euro;
    EditText ratios;
    RadioGroup contenedor;
    Conversor convert;
    Button btnCambioRatio;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejercicio_uno);
        inicializar();
    }

    //Recupera los ratios escogidos si se solicito el cambio
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        double valorDolar = 0.0;
        double valorEuro = 0.0;

        //Coprueba si todo fue correcto en la anterior actividad
        if(resultCode == RESULT_OK){

            //Recupera los valores y los reasigna
            valorDolar = Double.parseDouble(data.getExtras().getString("valorDolar"));
            valorEuro = Double.parseDouble(data.getExtras().getString("valorEuro"));
            convert.setRatioDolar(valorDolar);
            convert.setRatioEuro(valorEuro);
            actualizarRatio();
            Toast.makeText(this, "Los ratios fuerón cambiados con éxito", Toast.LENGTH_LONG).show();

        }
    }

    //Inicializa los objetos con las referencias al xml
    protected void inicializar(){

        ratios = (EditText)findViewById(R.id.edtRatio);
        btnCambioRatio = (Button)findViewById(R.id.btnCambioRatio);
        convert = new Conversor();
        dolarEuro = (RadioButton)findViewById(R.id.rbDolarEuro);
        euroDolar = (RadioButton)findViewById(R.id.rbEuroDolar);
        btnConversor = (Button)findViewById(R.id.btnConvertir);
        dolar = (EditText)findViewById(R.id.edDolares);
        euro = (EditText)findViewById(R.id.edEuros);
        contenedor = (RadioGroup)findViewById(R.id.rg);
        contenedor.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                //Mueve el foco según la opción indicada
                if(i == R.id.rbDolarEuro){

                    dolar.requestFocus();
                }else{

                    euro.requestFocus();
                }


            }
        });

        actualizarRatio();

    }

    //Actualiza el ratio y lo muestra
    private void actualizarRatio() {

        String linea = String.valueOf(String.format("%.2f",convert.getRatioEuro())+" EURO = "+String.format("%.2f",convert.getRatioDolar())+" USD");
        ratios.setText(linea);

    }

    //Realiza la converción o llama al metodo para lanzar la activity
    public void onClickConversor(View v){

        switch (v.getId()){

            case R.id.btnConvertir:
                conversion();
                break;
            case R.id.btnCambioRatio:
                lanzarCambioRatioActivity();
                break;

        }

    }

    //Lanza la activity para cambiar el ratio
    private void lanzarCambioRatioActivity() {

            Intent i = new Intent(this, CambioRatio.class);
            i.putExtra("parametroUno", convert.getRatioDolar());
            i.putExtra("parametroDos", convert.getRatioEuro());
            startActivityForResult(i, 1);

    }

    //Realiza la conversion (ver codigo)
    private void conversion() {


        String resultado = " ";
        double valor = 0.0;
        String campo = "";
        String msgError = " ";

        try {

            //Se comprueba cual es RadioButton seleccionado
            if (dolarEuro.isChecked()) {

                campo = dolar.getText().toString();

                //Comprobamos que el campo es valido para la conversión.
                //De ser valido se convierte y se llama a la clase Conversor para el cambio de divisa
                if (comprobarValidez(campo)) {

                    valor = Double.parseDouble(campo);
                    resultado = String.format("%.2f", convert.dolarAEuro(valor));
                    euro.setText(String.valueOf(resultado));
                }

            } else {

                campo = euro.getText().toString();

                if (comprobarValidez(campo)) {

                    valor = Double.parseDouble(campo);
                    resultado = String.format("%.2f", convert.euroADolar(valor));
                    dolar.setText(String.valueOf(resultado));
                }

            }
        //Controlamos posibles errores
        }catch (NumberFormatException e){

            lanzarMensaje("Formato introducido incorrecto");

        }catch (NullPointerException ex){

            lanzarMensaje("Parametro nulo");

        }catch (Exception e){

            lanzarMensaje("Se produjo el siguiente error "+e.getMessage());

        }
    }

    //Lanza el mensaje indicado al usuario
    private void lanzarMensaje(String msgError) {

        Toast.makeText(this, msgError, Toast.LENGTH_LONG).show();
    }

    //Comprueba si un String esta es valido para su conversión a double, devuelve falso si no lo es y verdadero en caso contrario
    public boolean comprobarValidez(String texto){

        return !texto.isEmpty() && texto != null && texto != ".";
    }
}


