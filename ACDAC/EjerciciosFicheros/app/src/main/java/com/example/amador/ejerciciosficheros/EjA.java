package com.example.amador.ejerciciosficheros;

import android.content.Context;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class EjA extends AppCompatActivity implements View.OnClickListener {

    private Button btnSum;
    private TextView txvResult, txvInfo;
    private Double a, b;
    private static final String FILE_NAME = "dato.txt";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eja);
        inicializar();
    }

    private void inicializar() {

        btnSum = (Button)findViewById(R.id.btnSum);
        btnSum.setOnClickListener(this);
        txvResult = (TextView) findViewById(R.id.txvResult);
        txvInfo = (TextView) findViewById(R.id.txvInfo);
    }



    @Override
    public void onClick(View v) {

        String vA, vB;
        Double result;

        vA = ((EditText)findViewById(R.id.edtFirstData)).getText().toString();
        vB = ((EditText)findViewById(R.id.edtSeconData)).getText().toString();

        if(checkValues(vA, vB)){

            result = Operation.sum(a, b);
            writeInFile(String.valueOf(result));
            txvInfo.setText("Resultado: "+readFile()+stracInfoFile());

        }else{

            launchToast("Error en los datos introducidos");

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

        FileOutputStream fileWrite = null;

        try {

            fileWrite = openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
            fileWrite.write(text.getBytes());

        } catch (FileNotFoundException e) {

            launchToast("Fichero no encontrado");

        } catch (IOException e) {

            launchToast("Error de entrada/salida");


        }finally {

            if(fileWrite != null){

                try {

                    fileWrite.close();

                } catch (IOException e) {

                    launchToast("Error al cerrar el fichero");
                }

            }
        }


    }

    private String readFile(){

        String text = "";
        BufferedReader reader = null;

        try {


            reader = new BufferedReader(new InputStreamReader(openFileInput(FILE_NAME)));
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
        File fileInfo = null;
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdfParser = new SimpleDateFormat("dd/mm/yyyy HH:mm:ss", new Locale("ES"));


        fileInfo = new File(getFilesDir(), FILE_NAME);
        c.setTimeInMillis(fileInfo.lastModified());


        info += "Ruta al fichero: "+fileInfo.getAbsolutePath()+"\nFecha de la última modificación: "+
                sdfParser.format(c.getTime())+"\nTamaño del fichero: "+fileInfo.length()+" Bytes";


        return info;


    }

    private void launchToast(String ms){

        Toast.makeText(getApplicationContext(), ms, Toast.LENGTH_LONG).show();

    }
}
