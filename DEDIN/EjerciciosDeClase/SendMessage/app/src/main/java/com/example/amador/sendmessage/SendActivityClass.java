package com.example.amador.sendmessage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SendActivityClass extends AppCompatActivity  {

    private Button btnEnvioCon;
    private EditText edMensajeCon;
    private String mensaje;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send);
        enlazarMemorias();

    }

    // Inicialize the instances
    protected void enlazarMemorias(){

        btnEnvioCon = (Button)findViewById(R.id.btnEnvio);
        edMensajeCon = (EditText)findViewById(R.id.edTexto);
        mensaje = " ";

    }


    // Contain the secuences of instruccion that it will be executed
    public void getEvent(View view) {


        Intent llamada = new Intent(SendActivityClass.this, ReceiverActivityClass.class);
        mensaje = edMensajeCon.getText().toString();

        // it condition verfy that the message is not in empty
        if(!mensaje.isEmpty()){

            // Go
            llamada.putExtra("parametro", mensaje);
            startActivity(llamada); //Lanzamos la llamada a la segunda actividad


        }else{

            // Informs the user that the text is empty
            Toast.makeText(getApplicationContext(), "No hay texto introducido pringao", Toast.LENGTH_SHORT).show();
        }

    }
}
