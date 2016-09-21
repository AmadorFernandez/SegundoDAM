package com.example.amador.sendmessage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SendActivityClass extends AppCompatActivity implements View.OnClickListener {

    Button btnEnvioCon;
    EditText edMensajeCon;
    String mensaje;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send);
        enlazarMemorias();
        apuntarEventoBoton();

    }

    // Apunta el boton al su evento click
    private void apuntarEventoBoton() {

        btnEnvioCon.setOnClickListener(this);
    }


    // Asignar las memorias de los objetos y los vincula
    protected void enlazarMemorias(){

        btnEnvioCon = (Button)findViewById(R.id.btnEnvio);
        edMensajeCon = (EditText)findViewById(R.id.edTexto);
        mensaje = "";

    }


    // Contiene la secuencia de instrucciones a ejecutar cuando el usuario pulse el botón
    @Override
    public void onClick(View view) {

        /* Objeto para la llamada uso del constructor de dos parametros
         * el primero es el Context y el segundo es la clase a la que va destinado */
        Intent llamada = new Intent(getApplicationContext(), ReceiverActivityClass.class);
        mensaje = edMensajeCon.getText().toString(); //Asigna el texto del control a la variable

        // Comprueba si se ha escrito algo o no (Se ha podido pulsar el boton por accidente)
        if(!mensaje.isEmpty()){

            // Asigna el nombre "parametro" al mensaje para recuperarlo por su nombre desde la siguiente actividad
            llamada.putExtra("parametro", mensaje);
            startActivity(llamada); //Lanzamos la llamada a la segunda actividad


        }else{

            // Avisa al usuario que ha pulsado sin introducir ningun texto
            Toast.makeText(getApplicationContext(), "No hay texto introducido pringao", Toast.LENGTH_SHORT).show();
        }

    }
}
