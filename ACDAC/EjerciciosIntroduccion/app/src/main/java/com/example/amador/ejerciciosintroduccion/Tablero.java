package com.example.amador.ejerciciosintroduccion;

import java.util.Random;

/**
 * Created by amador on 25/09/16.
 */

public class Tablero {



    private char [][] mTablero;
    private int filas;
    private int columnas;
    private static final char VIVA = '*';
    private static final char MUERTA = ' ';
    Random rnd = new Random();


    public char[][] getmTablero() {
        return mTablero;
    }

    public Tablero(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        mTablero = new char[filas][columnas];
        cargarTablero();
    }

    private void cargarTablero(){


        int valor = 0;

        for (int i = 0; i < mTablero.length; i++){

            for(int j = 0; j < mTablero[i].length; j++){

                valor = rnd.nextInt(2);

                if(valor == 1){

                    mTablero[i][j] = VIVA;

                }else{

                    mTablero[i][j] = MUERTA;
                }
            }

        }


    }

    public void avanzarGeneracion(){


        char[][] copia = new char[filas][columnas];


        for (int i = 0; i < mTablero.length; i++){

            for(int j = 0; j < mTablero[i].length; j++){


                copia[i][j] = comprobarVida(i,j, mTablero);

            }

        }

        mTablero = copia;

    }

    private char comprobarVida(int i, int j, char[][] mTablero) {

        char[][] subMatriz = new char [3][3];
        int nVivas = 0;
        char celula = ' ';
        char estado = ' ';


        subMatriz[0][0] = mTablero[((i-1) + mTablero.length) % mTablero.length][((j-1) + mTablero[i].length) % mTablero[i].length];
        subMatriz[0][1] = mTablero[((i-1) + mTablero.length) % mTablero.length][j];
        subMatriz[0][2] = mTablero[((i-1) + mTablero.length) % mTablero.length][(j +1) % mTablero.length];

        subMatriz[1][0] = mTablero[i][((j-1 + mTablero[i].length)) % mTablero[i].length];
        subMatriz[1][1] = mTablero[i][j];
        subMatriz[1][2] = mTablero[(i+1)%mTablero.length][(j+1) % mTablero[i].length];

        subMatriz[2][0] = mTablero[i][((j-1) + mTablero[i].length) % mTablero[i].length];
        subMatriz[2][1] = mTablero[i][j];
        subMatriz[2][2] = mTablero[i][((j+1) % mTablero[i].length)];



        for (int f = 0; f < subMatriz.length; f++){

            for (int c = 0; c < subMatriz[f].length; c++){

                if(c == 1 && f == 1){

                    celula = subMatriz[f][c];

                }else{

                    if(subMatriz[f][c] == VIVA){

                        nVivas++;
                    }

                }

            }

        }

        if(celula == VIVA && nVivas > 3){

            estado = MUERTA;

        }else if(celula == MUERTA && nVivas == 3){

            estado = VIVA;

        }else if(celula == VIVA && (nVivas == 2 || nVivas == 3)){

            estado = MUERTA;
        }

        return estado;

    }


}
