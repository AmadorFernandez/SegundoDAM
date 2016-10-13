package com.example.amador.ejerciciosficheros;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class EjB extends AppCompatActivity {

    private static final String FILE_NAME = "datos.txt";
    private EditText edtFirstNumber, edtSecondNumber;
    private ImageButton ibt;
    private TextView txvResultados;
    Memory fileMemory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejb);
        init();
    }


    private void init() {


        txvResultados = (TextView)findViewById(R.id.txvResultadoB);
        ibt = (ImageButton)findViewById(R.id.ibtnSumB);
        edtFirstNumber = (EditText)findViewById(R.id.edtFirstDataB);
        edtSecondNumber = (EditText)findViewById(R.id.edtSecontDataB);

        ibt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(canReadWriteExternal()) {

                    Double a = 0.0;
                    Double b = 0.0;
                    ArrayList<String> list;
                    String linea = "";
                    fileMemory = new Memory(Environment.getExternalStorageDirectory().getAbsolutePath());

                    try {
                        a = Double.parseDouble(String.valueOf(edtFirstNumber.getText()));
                        b = Double.parseDouble(String.valueOf(edtSecondNumber.getText()));

                        fileMemory.writeFile(FILE_NAME ,String.valueOf(Operation.sum(a, b)));
                        list = fileMemory.infoFile(FILE_NAME);


                        for (int i = 0; i < list.size(); i++) {

                            linea += list.get(i) + "\n";
                            txvResultados.setText(linea);

                        }


                    } catch (NumberFormatException e) {
                        Toast.makeText(EjB.this, R.string.data_error, Toast.LENGTH_LONG).show();
                    }

                }else{

                    Toast.makeText(EjB.this, R.string.not_media_mount, Toast.LENGTH_LONG).show();

                }

            }
        });





    }

    private boolean canReadWriteExternal(){

        String state = Environment.getExternalStorageState();
        return state.equals(Environment.MEDIA_MOUNTED);
    }
}
