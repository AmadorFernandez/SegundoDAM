package com.amador.relacionficheros;

/**
 * Created by amador on 5/11/16.
 */

public class Result {

    private boolean code;
    private String menssage;
    private String conten;

    public boolean isCode() {
        return code;
    }

    public String getMenssage() {
        return menssage;
    }

    public String getConten() {
        return conten;
    }

    public void setMenssage(String menssage) {
        this.menssage = menssage;
    }

    public void setConten(String conten) {
        this.conten = conten;
    }

    public void setCode(boolean code) {

        this.code = code;
    }

    public Result(boolean code, String menssage, String conten) {
        this.code = code;
        this.menssage = menssage;
        this.conten = conten;
    }

    public Result() {

    }
}
