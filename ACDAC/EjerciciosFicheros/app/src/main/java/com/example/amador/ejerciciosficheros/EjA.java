package com.example.amador.ejerciciosficheros;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class EjA extends AppCompatActivity {


    private static final String FILE_NAME = "datos.txt";
    private EditText edtFirstNumber, edtSecondNumber;
    private ImageButton ibt;
    private TextView txvResultados;
    Memory fileMemory;

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
                fileMemory = new Memory(getFilesDir().getAbsolutePath());

                try {
                    a = Double.parseDouble(String.valueOf(edtFirstNumber.getText()));
                    b = Double.parseDouble(String.valueOf(edtSecondNumber.getText()));

                     fileMemory.writeFile(FILE_NAME,String.valueOf(Operation.sum(a, b)));
                     list = fileMemory.infoFile(FILE_NAME);


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




}
