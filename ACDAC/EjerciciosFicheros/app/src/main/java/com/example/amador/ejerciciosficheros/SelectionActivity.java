package com.example.amador.ejerciciosficheros;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class SelectionActivity extends AppCompatActivity {


    private ImageView imvA, imvB, imvC, imvD;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);
        init();


    }

    private void init() {

        imvA = (ImageView)findViewById(R.id.btnA);
        imvB = (ImageView)findViewById(R.id.btnB);
        imvC = (ImageView)findViewById(R.id.btnC);
        imvD = (ImageView)findViewById(R.id.btnD);


        imvA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(SelectionActivity.this, EjA.class);
                startActivity(i);


            }
        });
    }


}
