package com.example.usuario.manageproducts;

import android.content.Context;
import android.text.TextUtils;

public class LoginPresenter implements ILoginMVP.Presenter {



    private ILoginMVP.View view;

    public LoginPresenter(ILoginMVP.View view){

        this.view = view;

    }
    public void validateCredentials(String pass, String user) {

        // if the user or the password launch message at user
        if(TextUtils.isEmpty(user)){

            view.setMessage(((Context)view).getResources().getString(R.string.data_empty), R.id.edtUser);

            //Least number
        }else if(TextUtils.isEmpty(pass)){

            view.setMessage(((Context)view).getResources().getString(R.string.data_empty), R.id.edtPwd);
        }
        else if (!pass.matches("^.{0,}([0-9])+.{0,}$")) {
            view.setMessage(((Context)view).getResources().getString(R.string.passDigit), R.id.edtPwd);
        }
        // least upperCase and one lowerCase
        else if (!pass.matches("^.+[a-zA-z]+.+$")) {
            view.setMessage(((Context)view).getResources().getString(R.string.passCase), R.id.edtPwd);
        }
        //8 characters minimun
        else if (pass.length() <=7) {
            view.setMessage(((Context)view).getResources().getString(R.string.passMinLength), R.id.edtPwd);
        }else {




        }

    }
}