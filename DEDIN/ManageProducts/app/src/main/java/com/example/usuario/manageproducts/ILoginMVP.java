package com.example.usuario.manageproducts;

/**
 * Created by amador on 19/10/16.
 */

public interface ILoginMVP {

    int OK = 0;
    int PASSWORD_DIGIT = 1;
    int PASSWORD_CASE = 2;
    int PASSWORD_LENGTH = 3;
    int PASSWORD_DATA_EMTY = 4;




    /**
     * This interface have to implements for the View
     * */
    interface View{

        //The msg message and validated field id
        void setMessage(String msg, int idView);

    }

    /**
     * This interface have to implements for the presenter
     *
     * */
    interface Presenter{

        //For validate the user and the password.
        void validateCredentials(String pass, String user);

    }
}
