package com.example.amador.prueba;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    TextView tex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tex = (TextView)findViewById(R.id.ii);
        StringBuilder linea = new StringBuilder();

        for(int i = 0; i < 10000; i++){

            linea.append("*");

            tex.setText(linea.toString());

        }


    }
}
