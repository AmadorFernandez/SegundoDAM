package com.example.amador.ejerciciosintroduccion;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by amador on 27/09/16.
 */

public class DiccionarioAdapter extends ArrayAdapter {

    Context context;
    ArrayList<String> datos;




   public DiccionarioAdapter(Context context, ArrayList<String> datos){
       super(context,R.layout.layout_list_palabras, datos);

       this.context = context;
       this.datos = datos;

   }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        String texto = datos.get(position);
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.layout_cabezera, null);
        View vi = inflater.inflate(R.layout.layout_list_palabras, null);
        Palabra p;
        TextView txvPalabra = (TextView)vi.findViewById(R.id.txvMostrarPalabra);
        txvPalabra.setText(new Palabra(texto).getTexto());

        return vi;

    }
}
