package com.example.usuario.ejerciciohttp;


import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;




/**
 * Created by usuario on 27/10/16.
 */

public class Conexion {

    public static Resultado conectarJava(String texto) {
        URL url;
        HttpURLConnection urlConnection = null;
        int respuesta;
        Resultado resultado = new Resultado();
        try {
            url = new URL(texto);
            urlConnection = (HttpURLConnection) url.openConnection();
            respuesta = urlConnection.getResponseCode();
            if (respuesta == HttpURLConnection.HTTP_OK) {
                resultado.setCodigo(true);
                resultado.setContenido(leer(urlConnection.getInputStream()));
            } else {
                resultado.setCodigo(false);
                resultado.setMensaje("Error en el acceso a la web: " + String.valueOf(respuesta));
            }
        } catch (IOException e) {
            resultado.setCodigo(false);
            resultado.setMensaje("Excepción: " + e.getMessage());
        } finally {
            try {
                if (urlConnection != null)
                    urlConnection.disconnect();
            } catch (Exception e) {
                resultado.setCodigo(false);
                resultado.setMensaje("Excepción: " + e.getMessage());
            }
            return resultado;
        }
    }

    private static String leer(InputStream entrada) throws IOException {
        BufferedReader in;
        String linea;
        StringBuilder miCadena = new StringBuilder();
        in = new BufferedReader(new InputStreamReader(entrada), 32000);
        while ((linea = in.readLine()) != null)
            miCadena.append(linea);
        //miCadena.append(linea).append('\n');
        in.close();
        return miCadena.toString();
    }

    private String ConectarApache(String url) {
        StringBuffer pagina = new StringBuffer();
        String line = "";
        try {
            HttpClient client = new DefaultHttpClient();
            HttpGet request = new HttpGet(url);
            HttpResponse response = client.execute(request);
            BufferedReader rd = new BufferedReader(
                    new InputStreamReader(response.getEntity().getContent()));
            while ((line = rd.readLine()) != null)
                pagina.append(line);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pagina.toString();
    }

    public static Resultado conectarApache(String texto) {
        CloseableHttpClient cliente = null;
        HttpPost peticion;
        HttpResponse respuesta;
        int valor;
        Resultado resultado = new Resultado();
        try {
            //cliente = new DefaultHttpClient();
            cliente = HttpClientBuilder.create().build();
            peticion = new HttpPost(texto);
            respuesta = cliente.execute(peticion);
            valor = respuesta.getStatusLine().getStatusCode();
            if (valor == HttpURLConnection.HTTP_OK) {
                resultado.setCodigo(true);
                resultado.setContenido(leer(respuesta.getEntity().getContent()));
            } else {
                resultado.setCodigo(false);
                resultado.setMensaje("Error en el acceso a la web: " + String.valueOf(valor));
            }
            cliente.close();
        } catch (IOException e) {
            resultado.setCodigo(false);
            resultado.setMensaje("Excepción: " + e.getMessage());
            if (cliente != null)
                try {
                    cliente.close();
                } catch (IOException excep) {
                    resultado.setCodigo(false);
                    resultado.setMensaje("Excepción: " + excep.getMessage());
                }


        }

        return resultado;
    }
}
