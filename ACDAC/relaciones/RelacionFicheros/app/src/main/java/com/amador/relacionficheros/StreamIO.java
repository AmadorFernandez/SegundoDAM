package com.amador.relacionficheros;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


/**
 *
 * @author Amador Fernández González
 * @version 0.1
 * This class allows read and write files
 *
 * */
public class StreamIO {


    //Const number error
    public static final int CORRECT_CODE = 0;
    public static final int FILE_NOT_FOUNT_CODE = 1;
    public static final int ERROR_IO_CODE = 2;
    public static final int UNSOPORTED_ENCODING_CODE = 3;
    public static final int FILE_EXIST_CODE = 4;

    //Const message error
    public static final String FILE_NOT_FOUNT_MSG = "File not found";
    public static final String IO_ERROR_MSG = "Error IO";
    public static final String UNSOPORTED_ENCODING_MSG = "Unsoported encoding";
    public static final String CORRECT_MSG = "Work completed successfully";
    public static final String FILE_EXISTS_MSG = "The file already exists";


    //Camps
    private String path; //Directory on the object to work

    //Getters and Setters
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    //Constuctor
    public StreamIO(String path) {
        this.path = path;
    }

    /**
     *Create a file in the directory that is working on the object,
     * using the file class @see File
     *
     * @param fileName: The name for file
     * @return ERROR_IO_CODE if an error of IO, FILE_EXIST_CODE occurs if the file already exists or CORRECT_CODE if the file was created correctly.
     *
     * */
    public int createFile(String fileName){

        File file = new File(path, fileName);
        int resul = CORRECT_CODE;
        boolean create;

        try {

                create = file.createNewFile();

                 if(create){

                 resul = CORRECT_CODE;

                 }else{

                    resul = FILE_EXIST_CODE;
                 }

        } catch (IOException e) {
            resul = ERROR_IO_CODE;
        }


        return resul;
    }

    /**
     *
     *Enter the text shown in a file, in the absence of this, believe it.
     * @param fileName: The name for file
     * @param text: text for the file
     * @param append: True if you want to add text to the file, false is want to destroy what had the file.
     * @return ERROR_IO_CODE if an error of IO, FILE_EXIST_CODE occurs if the file already exists or CORRECT_CODE if the file was created correctly.
     *
     * */
    public int writeFile(String fileName, String text, boolean append){

        File file = new File(path, fileName);
        int result = CORRECT_CODE;
        BufferedWriter bw = null;


        try {
            bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, append)));
            bw.write(text);
            result = CORRECT_CODE;
        } catch (FileNotFoundException e) {
            result = FILE_NOT_FOUNT_CODE;
        } catch (IOException e) {
            result = ERROR_IO_CODE;
        }finally {

            if(bw != null){

                try {
                    bw.close();
                } catch (IOException e) {

                }
            }

            return result;
        }


    }

    /**
     *
     *Read the text of a file by adding the result in an object that is to be eviar as a parameter.
     * @param fileName: Name of the file to be read.
     * @param buffer: Text object where the file is saved.
     * @return ERROR_IO_CODE if an error of IO, FILE_EXIST_CODE occurs if the file already exists or CORRECT_CODE if the file was created correctly.
     *
     * */
    public int readInFile(String fileName, String[] buffer){

        File file;
        String line = null;
        int value = 0;
        BufferedReader bf = null;


        file = new File(path, fileName);
        line = "";
        try {
            bf = new BufferedReader(new InputStreamReader(new FileInputStream(file)));

            for (int i = 0; i < buffer.length;i++){

                line = bf.readLine();

                if(line != null){

                    buffer[i] = line;
                }
            }
            value = CORRECT_CODE;
        } catch (FileNotFoundException e) {
            value = FILE_NOT_FOUNT_CODE;
        } catch (IOException e) {
            value = ERROR_IO_CODE;
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

    /**
     *
     *Checks whether a file exists in the working directory object
     * @param fileName: Name of the file
     * @return true if file exists, false if file is not exists
     *
     * */
    public boolean existsFile(String fileName){

        File file = new File(path, fileName);
        return file.exists();
    }


}
