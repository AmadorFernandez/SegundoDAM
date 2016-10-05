package com.example.amador.ejerciciosficheros;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Environment;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.StringTokenizer;

public class EjB extends AppCompatActivity implements View.OnClickListener {


    private Button btnSum;
    private TextView txvResult, txvInfo;
    private Double a, b;
    private static final String FILE_NAME = "dato.txt";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejb);
        inicializar();

    }

    private void inicializar() {

        btnSum = (Button)findViewById(R.id.btnSumExternal);
        btnSum.setOnClickListener(this);
        txvResult = (TextView) findViewById(R.id.txvResultExternal);
        txvInfo = (TextView) findViewById(R.id.txvInfoExternal);
    }

    @Override
    public void onClick(View v) {


        String vA, vB;
        Double result;

        if(checkExternalWhrite()) {

            vA = ((EditText) findViewById(R.id.edtFirstDataExternal)).getText().toString();
            vB = ((EditText) findViewById(R.id.edtSeconDataExternal)).getText().toString();

            if (checkValues(vA, vB)) {

                result = Operation.sum(a, b);
                writeInFile(String.valueOf(result));
                txvInfo.setText("Resultado: " + readFile() + stracInfoFile());

            } else {

                launchToast("Error en los datos introducidos");

            }
        }else{

            launchToast("La memoria externa no esta disponible");

        }


    }

    private boolean checkValues(String vA, String vB) {

        boolean valido = false;

        try {

            a = Double.parseDouble(vA);
            b = Double.parseDouble(vB);
            valido = true;

        } catch (NumberFormatException e) {

            valido = false;

        }catch(NullPointerException e){

            valido = false;

        }catch (Exception e){

            valido = false;
        }

        finally {

            return valido;


        }




    }

    private void writeInFile(String text){

        File filePath = null;
        File fileInfo = null;
        OutputStreamWriter objWriter = null;

        try {

            filePath = Environment.getExternalStorageDirectory();
            fileInfo = new File(filePath.getAbsolutePath(), FILE_NAME);
            objWriter = new OutputStreamWriter(new FileOutputStream(fileInfo));

            objWriter.write(text);




        } catch (FileNotFoundException e) {

            launchToast("Fichero no encontrado");

        } catch (IOException e) {

            launchToast("Error de entrada/salida");


        }finally {

            if(objWriter != null){

                try {

                    objWriter.close();

                } catch (IOException e) {

                    launchToast("Error al cerrar el fichero");
                }

            }
        }


    }

    private String readFile(){

        String text = "";
        BufferedReader reader = null;
        File filePath = null;
        File fileInfo = null;

        try {


            filePath = Environment.getExternalStorageDirectory();
            fileInfo = new File(filePath.getAbsolutePath(), FILE_NAME);
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileInfo)));
            text = reader.readLine();


        } catch (FileNotFoundException e) {

            launchToast("Fichero no encontrado");

        } catch (IOException e) {

            launchToast("Error de entrada salida");

        }finally {

            if(reader != null){

                try {

                    reader.close();

                } catch (IOException e) {

                    launchToast("Error de entrada salida");

                }finally {

                    return text;
                }


            }

        }

        return text;

    }



    private String stracInfoFile(){

        String info = "\n";
        File filePath = null;
        File fileInfo = null;
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdfParser = new SimpleDateFormat("dd/mm/yyyy HH:mm:ss", new Locale("ES"));


        filePath = Environment.getExternalStorageDirectory();
        fileInfo = new File(filePath.getAbsolutePath(), FILE_NAME);
        c.setTimeInMillis(fileInfo.lastModified());


        info += "Ruta al fichero: "+fileInfo.getAbsolutePath()+"\nFecha de la última modificación: "+
                sdfParser.format(c.getTime())+"\nTamaño del fichero: "+fileInfo.length()+" Bytes";


        return info;


    }

    private void launchToast(String ms){

        Toast.makeText(getApplicationContext(), ms, Toast.LENGTH_LONG).show();

    }


    private boolean checkExternalWhrite(){

        String info = Environment.getExternalStorageState();
        return info.equals(Environment.MEDIA_MOUNTED);


    }

}
































