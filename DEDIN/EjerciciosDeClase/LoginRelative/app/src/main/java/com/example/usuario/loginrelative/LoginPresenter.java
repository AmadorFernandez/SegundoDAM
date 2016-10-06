package com.example.usuario.loginrelative;

import android.content.Context;
import android.text.TextUtils;

/**
 * Created by usuario on 6/10/16.
 */

public class LoginPresenter implements IloginMVP.Presenter {



    private IloginMVP.View view;

    public LoginPresenter(IloginMVP.View view){

        this.view = view;

    }
    public void validateCredentials(String pass, String user) {

        // if the user or the password launch message at user
        if(TextUtils.isEmpty(user) || TextUtils.isEmpty(pass)){

            view.setMessageError(((Context)view).getResources().getString(R.string.data_empty));

         //Least number
        }else if (!pass.matches("^.{0,}([0-9])+.{0,}$")) {
            view.setMessageError(((Context)view).getResources().getString(R.string.data_no_number));
        }
        // least upperCase and one lowerCase
        else if (!pass.matches("^.+[a-zA-z]+.+$")) {
            view.setMessageError(((Context)view).getResources().getString(R.string.data_case));
        }
        //8 characters minimun
        else if (pass.length() <=7) {
            view.setMessageError(((Context)view).getResources().getString(R.string.data_length));
        }else {

            //Save the user in the class LoginApplication
            ((LoginApplication)((Context)view).getApplicationContext()).setUser(new User(user, pass));

        }

    }
}
