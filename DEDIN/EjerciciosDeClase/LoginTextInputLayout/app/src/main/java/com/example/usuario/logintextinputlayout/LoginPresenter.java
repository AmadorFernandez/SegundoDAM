package com.example.usuario.logintextinputlayout;

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
        if(TextUtils.isEmpty(user)){

            view.setMessage(((Context)view).getResources().getString(R.string.user_empty), R.id.edtUser);

         //Least number
        }else if(TextUtils.isEmpty(pass)){

            view.setMessage(((Context)view).getResources().getString(R.string.pass_empty), R.id.edtPwd);
        }
        else if (!pass.matches("^.{0,}([0-9])+.{0,}$")) {
            view.setMessage(((Context)view).getResources().getString(R.string.data_no_number), R.id.edtPwd);
        }
        // least upperCase and one lowerCase
        else if (!pass.matches("^.+[a-zA-z]+.+$")) {
            view.setMessage(((Context)view).getResources().getString(R.string.data_case), R.id.edtPwd);
        }
        //8 characters minimun
        else if (pass.length() <=7) {
            view.setMessage(((Context)view).getResources().getString(R.string.data_length), R.id.edtPwd);
        }else {


            view.setMessage(((Context)view).getResources().getString(R.string.data_length), 0);
            //Save the user in the class LoginApplication
            ((LoginApplication)((Context)view).getApplicationContext()).setUser(new User(user, pass));

        }

    }
}
