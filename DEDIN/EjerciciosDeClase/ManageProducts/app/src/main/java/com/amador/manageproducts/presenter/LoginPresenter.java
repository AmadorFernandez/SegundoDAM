package com.amador.manageproducts.presenter;


import android.content.Context;
import android.text.TextUtils;

import com.amador.manageproducts.R;
import com.amador.manageproducts.interfaces.ILoginMvp;

public class LoginPresenter implements ILoginMvp.Presenter {

    private ILoginMvp.View view;

    public LoginPresenter(ILoginMvp.View view) {
        this.view = view;
    }

    @Override
    public boolean validateCredentials(String user, String pass) {

        boolean result = false;
        //Rules to login an user and his password
        if (TextUtils.isEmpty(user)) {
            view.setLoginMessageError(((Context)view).getResources().getString(R.string.error_data_user_empty), R.id.edtLoginUser);
        }
        else if (TextUtils.isEmpty(pass)) {
            view.setLoginMessageError(((Context)view).getResources().getString(R.string.error_data_pass_empty), R.id.edtLoginPass);
        }
        else if (user.length() <=5) {
            view.setLoginMessageError(((Context)view).getResources().getString(R.string.error_user_length), R.id.edtLoginUser);
        }
        else if (pass.length() <=7) {
            view.setLoginMessageError(((Context)view).getResources().getString(R.string.error_password_length), R.id.edtLoginPass);
        }
        else if (!pass.matches("^.*[0-9]+.*$")) {
            view.setLoginMessageError(((Context)view).getResources().getString(R.string.error_password_digit), R.id.edtLoginPass);
        }
        else if (!pass.matches("^.*(?=.*[a-z])(?=.*[A-Z]).*$")) {
            view.setLoginMessageError(((Context)view).getResources().getString(R.string.error_password_case), R.id.edtLoginPass);
        }
        else if (user.length() > 16) {
            view.setLoginMessageError(((Context)view).getResources().getString(R.string.error_long_user), R.id.edtLoginUser);
        }
        else if (pass.length() > 40) {
            view.setLoginMessageError(((Context)view).getResources().getString(R.string.error_long_pass), R.id.edtLoginPass);
        }
        else {

            result = true;
        }

        return result;
    }
}
