package com.example.usuario.logintextinputlayout;

import android.app.Application;

/**
 * Created by usuario on 6/10/16.
 *
 * Create for keep the persistence of the data for user
 */

public class LoginApplication extends Application {

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }



    @Override
    public void onCreate() {
        super.onCreate();

    }
}
