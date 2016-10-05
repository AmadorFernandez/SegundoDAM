package com.amador.LoginTable.controllers;

public class Login_Activity_Controller {
    public static final int OK = 0;
    public static final int PASSWORD_DIGIT = 1;
    public static final int PASSWORD_CASE = 2;
    public static final int PASSWORD_LENGTH = 3;

    public int validateCredentials(String user, String pass) {
        int result = 0;

        if (!pass.matches("^.{0,}([0-9])+.{0,}$")) {
            result = PASSWORD_DIGIT;
        }
        else if (!pass.matches("^.+[a-zA-z]+.+$")) {
            result = PASSWORD_CASE;
        }
        else if (pass.length() <=7) {
            result = PASSWORD_LENGTH;
        }

        return result;
    }
}
