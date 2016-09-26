package com.example.amador.ejerciciosintroduccion;

import java.util.Comparator;

/**
 * Created by amador on 26/09/16.
 */

public class Palabra implements Comparator<Palabra> {


    private String texto;
    private int nCaracteres;
    private boolean esPalindromo;

    public int getnCaracteres() {
        return nCaracteres;
    }

    public boolean isEsPalindromo() {
        return esPalindromo;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {

        if(texto.toLowerCase() != this.texto.toLowerCase()){

            this.texto = texto;
            nCaracteres = texto.length();
            esPalindromo = comprobarPalindromo(texto);
        }

    }

    public Palabra(String texto) {

        this.texto = texto;
        this.nCaracteres = nCaracteres;
        this.esPalindromo = comprobarPalindromo(texto);
        nCaracteres = texto.length();

    }

    public Palabra(){

        this.nCaracteres = 0;
        this.esPalindromo = false;
        this.texto = null;
    }

    private boolean comprobarPalindromo(String texto) {

        StringBuilder copia = new StringBuilder(texto);
        return texto.toLowerCase() == copia.reverse().toString().toLowerCase();
    }

    @Override
    public int compare(Palabra lhs, Palabra rhs) {

        return new Integer(lhs.getnCaracteres()).compareTo(rhs.getnCaracteres());

    }



}
