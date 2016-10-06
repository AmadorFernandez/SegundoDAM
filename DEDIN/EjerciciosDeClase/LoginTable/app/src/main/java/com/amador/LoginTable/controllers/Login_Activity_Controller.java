package com.amador.LoginTable.controllers;

/**
 *
 * Class that implements LoginMVC
 * @author: Amador Fenández González
 *
 * */
public class Login_Activity_Controller implements LoginMVC {
    public static final int OK = 0;
    public static final int PASSWORD_DIGIT = 1;
    public static final int PASSWORD_CASE = 2;
    public static final int PASSWORD_LENGTH = 3;

    /**
     * Validate that one String
     Validates that a password complies with the rules defined
     *
     * */
    public int validateCredentials(String pass) {
        int result = 0;

        // least number
        if (!pass.matches("^.{0,}([0-9])+.{0,}$")) {
            result = PASSWORD_DIGIT;
        }
        // least upperCase and one lowerCase
        else if (!pass.matches("^?=.+[a-zA-z]+.+$")) {
            result = PASSWORD_CASE;
        }
        //8 characters minimun
        else if (pass.length() <=7) {
            result = PASSWORD_LENGTH;
        }

        return result;
    }
}
