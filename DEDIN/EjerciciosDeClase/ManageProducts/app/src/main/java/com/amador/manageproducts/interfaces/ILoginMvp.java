package com.amador.manageproducts.interfaces;

/**
 * Created by Amador on 2016-10-06
 */
public interface ILoginMvp {

    interface View {
        void setLoginMessageError(String message, int idView);
    }

    interface Presenter {
        boolean validateCredentials(String user, String pass);
    }
}
