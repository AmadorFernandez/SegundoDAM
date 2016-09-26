package com.example.amador.ejerciciosintroduccion;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by amador on 26/09/16.
 */

public class GestPalabras {

    private List<Palabra> contenedor;

    public List<Palabra> getContenedor() {
        return contenedor;
    }

    public GestPalabras(){

        this.contenedor = new ArrayList<Palabra>();
    }

    public void anadirPalabra(Palabra p){

        contenedor.add(p);
    }

    public void ordenarMenorAMayor(){

        Collections.sort(contenedor, new Palabra());
    }

    public void ordenarDeMayorAMenor(){

       Collections.sort(contenedor, Collections.<Palabra>reverseOrder());
    }

}
