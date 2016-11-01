package com.example.usuario.ejerciciohttp;

/**
 * Created by usuario on 27/10/16.
 */

public class Resultado {

    private boolean codigo;
    private String contenido;
    private String mensaje;

    public boolean isCodigo() {
        return codigo;
    }

    public void setCodigo(boolean codigo) {
        this.codigo = codigo;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
