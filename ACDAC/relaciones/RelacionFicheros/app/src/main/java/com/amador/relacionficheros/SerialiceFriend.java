package com.amador.relacionficheros;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

/**
 * Created by amador on 20/10/16.
 */

public class SerialiceFriend {

    private String path;
    private String filename;

    public final static String ERROR_IO_MSG = "Error en la escritura";
    public final static String SAVE_OK_MSG = "Tu amiguete ha sido guardado";

    public final static int SAVE_OK_CODE = 0;
    public final static int ERROR_IO_CODE = 1;


    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public SerialiceFriend(String path, String filename) {
        this.path = path;
        this.filename = filename;
    }

    public int saveFriend(Friend f){

        int result = SAVE_OK_CODE;
        File file = new File(this.path, this.filename);
        ObjectOutputStream outputStream = null;

        try {
            outputStream = new ObjectOutputStream(new FileOutputStream(file));
            outputStream.writeObject(f);
        } catch (IOException e) {
            result = ERROR_IO_CODE;
        }finally {

            if(outputStream != null){

                try {
                    outputStream.close();
                } catch (IOException e) {

                }
            }
        }


        return result;

    }

    public Diary extractDiary(){

        File file = new File(this.path, this.filename);
        ObjectInputStream objectInputStream = null;
        Diary diary = Diary.getDiary();


        try {

                objectInputStream = new ObjectInputStream(new FileInputStream(file));
                while (true){

                    diary.add((Friend)objectInputStream.readObject());
                }

        } catch (IOException e) {

        } catch (ClassNotFoundException e) {

        }

        return diary;
    }
 }
