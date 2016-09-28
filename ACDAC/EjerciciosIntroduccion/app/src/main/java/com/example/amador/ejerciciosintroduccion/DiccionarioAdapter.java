package com.example.amador.ejerciciosintroduccion;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by amador on 27/09/16.
 */

public class DiccionarioAdapter extends ArrayAdapter {

    Context context;
    ArrayList<String> datos;
    Button btnBorrarPalabra, btnInfoPalabra;




    //Envia al constructor de la clase padre los parametros e inicializa los suyos
   public DiccionarioAdapter(Context context, ArrayList<String> datos){
       super(context,R.layout.layout_list_palabras, datos);
       this.context = context;
       this.datos = datos;

   }

   //Metodo llamado cada vez que se añade un item al ListView
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        final String texto = datos.get(position); //Extrae el texto de la coleccion
        LayoutInflater inflater = LayoutInflater.from(context); //Instancia el inflater
        View vi = inflater.inflate(R.layout.layout_list_palabras, null); //Infla el View
        btnBorrarPalabra = (Button)vi.findViewById(R.id.imvBorrarPalabra);
        final TextView txvPalabra = (TextView)vi.findViewById(R.id.txvMostrarPalabra);
        btnInfoPalabra = (Button)vi.findViewById(R.id.imvInfoPalabra);
        txvPalabra.setText(new Palabra(texto).getTexto());

        //Apuntamos los View a los eventos (Ver metodos)
        btnBorrarPalabra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                pedirConfirmacion("AVISO", "La palabra "+texto+" será borrada permanentemente", position).show();
            }
        });

        btnInfoPalabra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Palabra p = new Palabra(datos.get(position));

            }
        });

        btnInfoPalabra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mostrarInfoPalabra(new Palabra(txvPalabra.getText().toString())).show();
            }
        });


        return vi;

    }

    //Pide confirmacion al usuario antes de borrar (Consejo de Sebas)
    private Dialog pedirConfirmacion(String titulo, String msg, final int posData){

        final AlertDialog.Builder popup = new AlertDialog.Builder(context);
        popup.setTitle(titulo);
        popup.setMessage(msg);


        popup.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                datos.remove(posData);
                notifyDataSetChanged(); //Notifica los cambios en el contenido del adapter al ListView
                dialog.cancel();
            }
        });

        popup.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.cancel();

            }
        });

        return   popup.create();

    }

    //Muestra la informacion de la palabra
    private Dialog mostrarInfoPalabra(Palabra p){

        final AlertDialog.Builder popup = new AlertDialog.Builder(context);
        popup.setTitle(p.getTexto());
        boolean palindromo = p.isEsPalindromo();
        String msgPalindromo = "NO";
        final String palabra = p.getTexto();

        if(palindromo){

            msgPalindromo = "SI";
        }

        popup.setMessage("Nº de caracteres: "+p.getnCaracteres()+"\n" +
                "Palindromo: "+msgPalindromo);

        popup.setPositiveButton("Ir a la RAE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                //Abre la vista con el WebView en caso de que el usuario desee ir a la RAE
                Intent i = new Intent(context, WebActivityClass.class);
                i.putExtra("parametroUno", palabra);
                context.startActivity(i);

            }
        });

        popup.setNegativeButton("Volver", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.cancel();

            }});

        return popup.create();

    }


}
