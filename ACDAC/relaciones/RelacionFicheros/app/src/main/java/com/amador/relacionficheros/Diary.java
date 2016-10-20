package com.amador.relacionficheros;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by amador on 20/10/16.
 */

public class Diary extends ArrayList<Friend> {

    static Diary diary;

    public static Diary getDiary(){

        if(diary == null){

            diary = new Diary();
        }

        return diary;
    }

    private Diary(){}
}
