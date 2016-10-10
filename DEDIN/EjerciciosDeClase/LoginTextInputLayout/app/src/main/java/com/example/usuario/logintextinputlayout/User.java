package com.example.usuario.logintextinputlayout;

/**
 * Created by usuario on 6/10/16.
 *
 * Class for encapsulate the data of User
 */

public class User {

    private String userName;
    private String userPwd;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public User(String userName, String userPwd) {

        this.userName = userName;
        this.userPwd = userPwd;
    }

    @Override
    public String toString() {
        return userName +" "+userPwd;

    }
}
