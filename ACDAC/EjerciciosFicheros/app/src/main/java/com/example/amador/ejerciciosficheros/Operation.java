package com.example.amador.ejerciciosficheros;

/**
 * Created by amador on 8/10/16.
 */

public class Operation {

    public static Double sum(Double a, Double b){

        return a + b;

    }

    public static Double sum(double[] values){

        Double result = 0.0;

        for(int i = 0; i < values.length; i++){

            result += values[i];
        }

        return result;

    }
}
