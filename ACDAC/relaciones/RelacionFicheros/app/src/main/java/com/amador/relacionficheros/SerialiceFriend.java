package com.amador.relacionficheros;




import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


//Clase para serializar los amigos y guardarlos en el fichero
public class SerialiceFriend {

    //Campos
    private String path;
    private String filename;

    //Const
    public final static String ERROR_IO_MSG = "Error en la escritura";
    public final static String SAVE_OK_MSG = "Tu amiguete ha sido guardado";
    public final static int SAVE_OK_CODE = 0;
    public final static int ERROR_IO_CODE = 1;

    //Getters and setters
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

    //Constructor
    public SerialiceFriend(String path, String filename) {
        this.path = path;
        this.filename = filename;
    }

    //Métodos
    public int saveFriend(){

        int result = SAVE_OK_CODE;
        File file = new File(this.path, this.filename);
        ObjectOutputStream outputStream = null;


        try {
            outputStream = new ObjectOutputStream(new FileOutputStream(file));

            //Guarda todos los objetos que esten en la lista
            for (int i = 0; i < Diary.getDiary().size(); i++){

                outputStream.writeObject(Diary.getDiary().get(i));
            }


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

    public void extractDiary(){

        File file = new File(this.path, this.filename);
        ObjectInputStream objectInputStream = null;
        boolean flag = false;
        Diary.getDiary().clear(); //Limpia la lista para evitar repeticiones

        try {
                //Si es la primera vez que se ha iniciado la app en el dispositivo creamos el fichero
                if(!file.exists()){

                    file.createNewFile();
                    flag = true; //La bandera nos avisa de si es la primera vez que se inicio la app
                }

            //Si no es la primera vez ya podemos intentar una lectura
            if(!flag) {

                objectInputStream = new ObjectInputStream(new FileInputStream(file));
                //Saldrá del bucle cuando se produzca la excepcion
                while (true) {

                    //Añade objetos a la lista
                    Diary.getDiary().add((Friend) objectInputStream.readObject());
                }

            }

        } catch (Exception e) {

            try {
                //Cierra el flujo y de estar a null se controla en el catch
                objectInputStream.close();
            } catch (Exception e1) {

            }

        }




    }
 }
