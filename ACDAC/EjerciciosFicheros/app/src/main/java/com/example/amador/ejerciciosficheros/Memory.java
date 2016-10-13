package com.example.amador.ejerciciosficheros;

import android.widget.Toast;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by usuario on 13/10/16.
 */

public class Memory {

    String path;

    //Const number error
    private static final int FILE_NOT_FOUNT = 1;
    private static final int ERROR_IO = 2;
    private static final int CORRECT = 0;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Memory(String path){

        this.path = path;
    }

    public int writeFile(String fileName, String text){

        int result = 0;
        File file;
        BufferedWriter bw = null;

        file = new File(path, fileName);
        try {
            bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
            bw.write(text);
            result = CORRECT;
        } catch (FileNotFoundException e) {
            result = FILE_NOT_FOUNT;
        } catch (IOException e) {
            result = ERROR_IO;
        }finally {

            if(bw != null){

                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return result;
        }


    }

    public int readInFile(String fileName, String buffer){

        File file;
        StringBuilder result = null;
        int value = 0;
        BufferedReader bf = null;

        file = new File(path, fileName);
        result = new StringBuilder();
        try {
            bf = new BufferedReader(new InputStreamReader(new FileInputStream(file)));

            while ((buffer = bf.readLine()) != null){

                result.append(buffer);

            }

            value = CORRECT;
        } catch (FileNotFoundException e) {
            value = FILE_NOT_FOUNT;
        } catch (IOException e) {
            value = ERROR_IO;
        }finally {

            if(bf != null){

                try {
                    bf.close();
                } catch (IOException e) {

                }

            }

            return value;
        }


    }

    public ArrayList<String> infoFile(String fileName) {


        BufferedReader bfReader = null;
        ArrayList<String> result = null;
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = null;
        File fileInfo = null;

        try {

            result = new ArrayList<String>();
            fileInfo = new File(path, fileName);

            fileInfo = new File(path, fileName);
            bfReader = new BufferedReader(new InputStreamReader(new FileInputStream(fileInfo)));
            result.add("Resultado: " + bfReader.readLine());
            result.add("Ruta: " + fileInfo.getAbsolutePath());
            c.setTimeInMillis(fileInfo.lastModified());
            sdf = new SimpleDateFormat("dd/mm/yyyy HH:mm:ss", new Locale("ES"));
            result.add("Última modificación" + sdf.format(c.getTime()));
            result.add("Tamaño del archivo: " + fileInfo.length() + " bytes");


        } catch (FileNotFoundException e) {

            result = null;

        } catch (IOException e) {

            result = null;

        } finally {

            if (bfReader != null) {

                try {
                    bfReader.close();
                } catch (IOException e) {

                }

            }

            return result;
        }


    }


}
