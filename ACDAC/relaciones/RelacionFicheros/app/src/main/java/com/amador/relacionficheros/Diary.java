package com.amador.relacionficheros;

import java.io.Serializable;
import java.util.ArrayList;


//Clase Singleton para guardar los amigos
public class Diary extends ArrayList<Friend> {

    private static Diary diary;

    public static Diary getDiary(){

        if(diary == null){

            diary = new Diary();
        }

        return diary;
    }

    private Diary(){


    }
}
