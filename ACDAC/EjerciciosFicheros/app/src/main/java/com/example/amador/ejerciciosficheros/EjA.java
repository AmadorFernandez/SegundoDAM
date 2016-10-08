package com.example.amador.ejerciciosficheros;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class EjA extends AppCompatActivity {


    private static final String FILE_NAME = "datos.txt";
    private EditText edtFirstNumber, edtSecondNumber;
    private ImageButton ibt;
    private TextView txvResultados;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eja);
        init();
    }

    private void init() {


        txvResultados = (TextView) findViewById(R.id.txvResultado);
        ibt = (ImageButton) findViewById(R.id.ibtnSum);
        edtFirstNumber = (EditText) findViewById(R.id.edtFirstData);
        edtSecondNumber = (EditText) findViewById(R.id.edtSecontData);

        ibt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Double a = 0.0;
                Double b = 0.0;
                ArrayList<String> list;
                String linea = "";

                try {
                    a = Double.parseDouble(String.valueOf(edtFirstNumber.getText()));
                    b = Double.parseDouble(String.valueOf(edtSecondNumber.getText()));

                    writeInFile(String.valueOf(Operation.sum(a, b)));
                    list = readInFile();


                    for (int i = 0; i < list.size(); i++) {

                        linea += list.get(i) + "\n";
                        txvResultados.setText(linea);

                    }


                } catch (NumberFormatException e) {
                    Toast.makeText(EjA.this, R.string.data_error, Toast.LENGTH_LONG).show();
                }

            }
        });


    }

    private void writeInFile(String text) {

        OutputStreamWriter outWriter = null;


        try {

            outWriter = new OutputStreamWriter(openFileOutput(FILE_NAME, Context.MODE_PRIVATE));
            outWriter.write(text);

        } catch (FileNotFoundException e) {

            Toast.makeText(EjA.this, R.string.not_search_file, Toast.LENGTH_LONG).show();

        } catch (IOException e) {

            Toast.makeText(EjA.this, R.string.io_error, Toast.LENGTH_LONG).show();

        } finally {

            if (outWriter != null) {

                try {
                    outWriter.close();
                } catch (IOException e) {

                }
            }

        }

    }

    private ArrayList<String> readInFile() {


        BufferedReader bfReader = null;
        ArrayList<String> result = null;
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = null;
        File fileInfo = null;

        try {

            result = new ArrayList<String>();

            fileInfo = new File(getFilesDir(), FILE_NAME);
            bfReader = new BufferedReader(new InputStreamReader(openFileInput(FILE_NAME)));
            result.add("Resultado: " + bfReader.readLine());
            result.add("Ruta: " + fileInfo.getAbsolutePath());
            c.setTimeInMillis(fileInfo.lastModified());
            sdf = new SimpleDateFormat("dd/mm/yyyy HH:mm:ss", new Locale("ES"));
            result.add("Última modificación" + sdf.format(c.getTime()));
            result.add("Tamaño del archivo: " + fileInfo.length() + " bytes");


        } catch (FileNotFoundException e) {

            Toast.makeText(EjA.this, R.string.not_search_file, Toast.LENGTH_LONG).show();

        } catch (IOException e) {

            Toast.makeText(EjA.this, R.string.io_error, Toast.LENGTH_LONG).show();
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
