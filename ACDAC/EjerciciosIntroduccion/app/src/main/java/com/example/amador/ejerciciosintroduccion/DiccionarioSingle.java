package com.example.amador.ejerciciosintroduccion;

import java.util.ArrayList;

/**
 * Created by amador on 29/09/16.
 */

//Esta clase esta diseñada para hacer uso del patron Singleton,
//esto permite que pueda manipular los datos diferentes Activitys y sin tener copias de objetos inecesarias
public class DiccionarioSingle extends ArrayList<String> {

    private static  DiccionarioSingle diccionario;

    public static DiccionarioSingle getDiccionario() {



        if(diccionario == null){

           diccionario = new DiccionarioSingle();
        }

        return diccionario;
    }

    private DiccionarioSingle(){


    }
}
