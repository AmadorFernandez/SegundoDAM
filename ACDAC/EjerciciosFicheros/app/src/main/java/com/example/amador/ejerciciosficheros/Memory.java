package com.example.amador.ejerciciosficheros;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by usuario on 13/10/16.
 */

public class Memory {



    //Const number error
    public static final int CORRECT = 0;
    public static final int FILE_NOT_FOUNT = 1;
    public static final int ERROR_IO = 2;
    public static final int UNSOPORTED_ENCODING = 3;
    //Const message error
    public static final String FILE_NOT_FOUNT_MSG = "Fichero no encontrado";
    public static final String IO_ERROR_MSG = "Error IO";
    public static final String UNSOPORTED_ENCODING_MSG = "Codificación no soportada";
    public static final String CORRECT_MSG = "Tarea realizada con éxito";

    //Camps
    private String path;

    //Getters and Setters
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }


    //Construct
    public Memory(String path){

        this.path = path;
    }

    //Instance methods
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


    //Class methods
    public static boolean isFileExits(String path, String fileName){

        File fileInfo = new File(path, fileName);
        return fileInfo.exists();

    }

    public static String readFile(String absolutePath){

        File file;
        String line = "";
        StringBuilder result = null;
        BufferedReader bf = null;


        file = new File(absolutePath);
        result = new StringBuilder();
        try {
            bf = new BufferedReader(new InputStreamReader(new FileInputStream(file)));

            while ((line = bf.readLine()) != null){

                result.append(line);
                result.append("\n");

            }

        } catch (FileNotFoundException e) {
            result = new StringBuilder(FILE_NOT_FOUNT_MSG);
        } catch (IOException e) {
            result = new StringBuilder(ERROR_IO);
        }finally {

            if(bf != null){

                try {
                    bf.close();
                } catch (IOException e) {

                }

            }

            return result.toString();
        }

    }

    public static String readFile(String absolutePath, String encoding){

        File file;
        String line = "";
        StringBuilder result = null;
        BufferedReader bf = null;


        file = new File(absolutePath);
        result = new StringBuilder();
        try {
            bf = new BufferedReader(new InputStreamReader(new FileInputStream(file), encoding));

            while ((line = bf.readLine()) != null){

                result.append(line);
                result.append("\n");

            }

        } catch (FileNotFoundException e) {
            result = new StringBuilder(FILE_NOT_FOUNT_MSG);
        } catch (IOException e) {
            result = new StringBuilder(ERROR_IO);
        }finally {

            if(bf != null){

                try {
                    bf.close();
                } catch (IOException e) {

                }

            }

            return result.toString();
        }

    }

    public static int copyInFile(String absolutePathOrigin, String absolutePathDestiny, String encoding){

        File fileOrigin = new File(absolutePathOrigin);
        File fileDestiny = new File(absolutePathDestiny);
        BufferedReader br = null;
        BufferedWriter bw = null;
        String line = "";
        int result = 0;

        try {

            br = new BufferedReader(new InputStreamReader(new FileInputStream(fileOrigin), encoding));
            bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileDestiny), encoding));

            while ((line = br.readLine()) != null){

                bw.write(line);
                bw.newLine();
            }

        } catch (FileNotFoundException e) {

            result = FILE_NOT_FOUNT;

        } catch (UnsupportedEncodingException e) {

            result = UNSOPORTED_ENCODING;

        } catch (IOException e) {

            result = ERROR_IO;

        }finally {

            try {
                br.close();
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return result;
        }


    }


}
