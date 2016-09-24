package com.example.amador.ejerciciosintroduccion;

/**
 * Created by amador on 23/09/16.
 */

public class Conversor {

    private double ratioEuro;
    private double ratioDolar;

    public Conversor(){

        this.ratioEuro = 1.11f; // A día 24/09/2016 1 Euro son 1.11 USD
        this.ratioDolar = 0.89f; // A día 24/09/2016 1 USD son 0.89 USD

    }

    public double getRatioEuro() {
        return ratioEuro;
    }

    public void setRatioEuro(double ratioEuro) {
        this.ratioEuro = ratioEuro;
    }

    public double getRatioDolar() {
        return ratioDolar;
    }

    public void setRatioDolar(double ratioDolar) {
        this.ratioDolar = ratioDolar;
    }

    public double dolarAEuro(double dolar){

        return dolar / this.ratioEuro;
    }

    public double euroADolar(double euro){

        return euro / this.ratioDolar;
    }




}
