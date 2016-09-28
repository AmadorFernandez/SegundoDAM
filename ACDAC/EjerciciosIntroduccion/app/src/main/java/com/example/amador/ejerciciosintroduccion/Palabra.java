package com.example.amador.ejerciciosintroduccion;

import android.util.Log;

import java.util.Comparator;
import java.util.StringTokenizer;

/**
 * Created by amador on 26/09/16.
 */
//Clase para obtener y almacenar los valores que nos interesan de la palabra
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

        //Comprueba que la nueva palabra sea diferente de la anterior, valida y actualiza los campos
        if(texto.toLowerCase() != this.texto.toLowerCase() && Palabra.esValida(texto)){

            this.texto = texto;
            nCaracteres = texto.length();
            esPalindromo = comprobarPalindromo(texto);
        }

    }

    //Constructor que llama a los distintos metodos para extraer la informacion
    public Palabra(String texto) {

        if(Palabra.esValida(texto)) {
            this.texto = texto;
            this.esPalindromo = comprobarPalindromo(texto);
            nCaracteres = texto.length();
        }

    }

    //Inicializa las variables a un valor correspondiente a una palabra sin texto
    public Palabra(){

        this.nCaracteres = 0;
        this.esPalindromo = false;
        this.texto = null;
    }



    //Determina si la palabra es o no palindromo
    private boolean comprobarPalindromo(String texto) {

        StringBuilder copia = new StringBuilder(texto);
        return texto.toLowerCase().equals(copia.reverse().toString());
    }



    //Verifica los caracteres de la palabra y que el texto enviado no contenga mas de una palabra
    public static boolean esValida(String palabra){

        String separdores = "[ .,;?!¡¿\\'\\\"\\\\[\\\\]]+\"";
        String[] com = palabra.split(separdores);
        return com.length == 1;
    }

    //Enseña a comparar este objetos a los metodos de lal clase Collections
    @Override
    public int compare(Palabra lhs, Palabra rhs) {

        return new Integer(lhs.getnCaracteres()).compareTo(rhs.getnCaracteres());
    }



}
