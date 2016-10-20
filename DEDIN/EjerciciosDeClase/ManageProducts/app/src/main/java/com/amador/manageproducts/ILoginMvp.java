package com.amador.manageproducts;

/**
 * Created by Amador on 2016-10-06
 */
interface ILoginMvp {

    interface View {
        void setLoginMessageError(String message, int idView);
    }

    interface Presenter {
        boolean validateCredentials(String user, String pass);
    }
}
