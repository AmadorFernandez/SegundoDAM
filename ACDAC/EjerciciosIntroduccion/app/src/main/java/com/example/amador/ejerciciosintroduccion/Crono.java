package com.example.amador.ejerciciosintroduccion;

import android.location.GpsStatus;

/**
 * Created by amador on 24/09/16.
 */

 class Crono extends Thread implements CronoListener {


    private int segundos;
    private int minutos;
    private String contador;
    private boolean ascendente;
    private boolean activo;
    private boolean ejecutando;
    private static final long ESPERA = 990;
    private CronoListener l;

    //Propiedades

    public boolean isAscendente() {
        return ascendente;
    }

    public void setAscendente(boolean ascendente) {
        this.ascendente = ascendente;
    }

    public boolean isEjecutando() {
        return ejecutando;
    }

    public void setEjecutando(boolean ejecutando) {
        this.ejecutando = ejecutando;
    }

    public int getMinutos() {
        return minutos;
    }

    public void setMinutos(int minutos) {

        //Impide que se alcanze un valor negativo
        if(minutos >= 0){

            this.minutos = minutos;
            actualizarContador();

        }

    }

    public int getSegundos() {
        return segundos;
    }

    public void setSegundos(int segundos) {

        //Impide que se alcase un valor superior a 59 o menor a 0
        if(segundos > 59){

            this.segundos = 0;
        }

        if(segundos < 0){

            this.segundos = 59;
        }

        this.segundos = segundos;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public String getContador() {
        return contador;
    }

    //Constructores

    public Crono(int segundos, boolean ascendente){
        super();
        this.ascendente = ascendente;
        this.segundos = segundos;
        this.contador = "";
        this.activo = false;
        this.ejecutando = false;
        fraccionarTiempo();
        actualizarContador();

    }

    public Crono(){

        this.ascendente = true;
        this.segundos = 0;
        this.minutos = 0;
        this.ejecutando = false;
        contador = "";
        activo = true;
        actualizarContador();

    }

    //Métodos

    // Manejador para eventos
    public void setOnCronoListener(CronoListener cl){

        this.l = cl;
    }

    //Fracciona el tiempo pasado al construcctor con parametros para separar segundos de los minutos
    private void fraccionarTiempo(){

        int resto = segundos % 60;
        minutos = segundos / 60;
        segundos = resto;

    }

    //Actualiza el contador y lanza el evento tick
    private void actualizarContador(){

        contador = String.format("%02d", minutos) + ":" + String.format("%02d", segundos);

        if(l != null){

            l.onTick(this);
        }




    }

    public void contadorStart(){

        int value = 0;

        while (true){

            // Verifica que no se llegue a 60 ni a -1 y comprueba que el crono no este pausado
            while (segundos <= 59 && segundos >= 0 && activo){

                // Verifica el sentido del cronometraje
                if(ascendente){

                        segundos++;

                }else{

                        segundos--;
                }


                try {

                    //Duerme el hilo un segundo
                    Thread.sleep(ESPERA);
                } catch (InterruptedException e) {
                    //Aún no se mucho de hilos y no se que hacer aquí
                }

                //Evita que si se alcanza un valor incorrecto se alcanzen valores indeseados
                if(segundos != -1 && segundos != 60){

                    actualizarContador();
                }
            }

            //Verifica sentido y si el crono esta pausado
            if(ascendente && activo){

                minutos++;
                segundos = 0;
            }
            else if (activo){

                value = minutos;
                value--;
                this.setMinutos(value);

                if(minutos != 0){

                    segundos = 59;
                }

            }
        }



    }

    //Pone la bandera en falso para que el tiempo no sea alterado (Ver metodo contadorStar)
    public void pausa(){

       activo = false;
    }


    @Override
    public void run() {

        //Asegura que se halla llamado al método por primera vez
        if(!ejecutando){

            ejecutando = true;
            activo = true;
            super.run();
            this.contadorStart(); //Arranca el contador
        }

    }

    @Override
    public void onTick(Crono c) {

    }
}
