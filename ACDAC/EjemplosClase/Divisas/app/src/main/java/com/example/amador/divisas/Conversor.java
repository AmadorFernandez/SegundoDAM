package com.example.amador.divisas;

/**
 * Created by amador on 22/09/16.
 */

public class Conversor {

    //Ratios de conversión
    private static final double RATIO_DOLAR = 1.13;
    private static final double RATIO_EURO = 0.89;

    //Lo dice el nombre
    public static double euroADolar(double euro){

        return euro / RATIO_EURO;
    }

    //Lee el nombre
    public static double dolarAEuro(double dolar){

        return dolar / RATIO_DOLAR;
    }


}
