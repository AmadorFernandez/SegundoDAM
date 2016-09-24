package com.example.amador.ejerciciosintroduccion;

import android.location.GpsStatus;

/**
 * Created by amador on 24/09/16.
 */

public class Crono extends Thread implements CronoListener {


    private CronoListener l;
    private int segundos;
    private int minutos;
    private String contador;
    private boolean ascendente;
    boolean activo;
    private static final long ESPERA = 990;

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public String getContador() {
        return contador;
    }

    public Crono(int segundos, boolean ascendente){
        super();
        this.ascendente = ascendente;
        this.segundos = segundos;
        this.contador = "";
        this.activo = true;
        fraccionarTiempo();
        actualizarContador();

    }

    public void setOnCronoListener(CronoListener cl){

        this.l = cl;
    }

    private void fraccionarTiempo(){

        int resto = segundos % 60;
        minutos = segundos / 60;
        segundos = resto;

    }

    private void actualizarContador(){

        contador = String.format("%02d", minutos) + ":" + String.format("%02d", segundos);

        if(l != null){


            l.onTick(this);
        }




    }

    private void contadorStart(){


        while (activo){

            while (segundos <= 59 && segundos >= 0){

                if(ascendente){

                        segundos++;

                }else {

                        segundos--;
                }


                try {

                    Thread.sleep(ESPERA);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if(segundos != -1 && segundos != 60){

                    actualizarContador();
                }
            }

            if(ascendente){

                minutos++;
                segundos = 0;
            }
            else{

                minutos--;
                segundos = 59;
            }
        }



    }

    @Override
    public void run() {
        super.run();
        this.contadorStart();
    }

    @Override
    public void onTick(Crono c) {

    }
}
