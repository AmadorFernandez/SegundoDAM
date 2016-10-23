package com.amador.relacionficheros;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by amador on 20/10/16.
 */

public class Friend implements Serializable {

    private String id;
    private String name;
    private String telephono;
    private String email;

    public String getTelephono() {
        return telephono;
    }

    public void setTelephono(String telephono) {
        this.telephono = telephono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }



    public Friend(String name, String telephono, String email) {

        this.name = name;
        this.telephono = telephono;
        this.email = email;
        this.id = UUID.randomUUID().toString();

    }

    @Override
    public boolean equals(Object o) {

        boolean result = false;
        Friend f;

        if(o != null){

            if(o instanceof Friend){

                f = (Friend)o;

                if(this.id == f.id){

                    result = true;
                }
            }
        }

        return result;

    }



}
