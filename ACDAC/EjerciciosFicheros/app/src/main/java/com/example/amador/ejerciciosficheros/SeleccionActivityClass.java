package com.example.amador.ejerciciosficheros;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SeleccionActivityClass extends AppCompatActivity implements View.OnClickListener {

    Button btnA, btnB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccion_class);
        init();
    }

    private void init() {

        btnA = (Button)findViewById(R.id.btnEjA);
        btnB = (Button)findViewById(R.id.btnEjB);
        btnA.setOnClickListener(this);
        btnB.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        Intent i;

        switch (v.getId()){

            case R.id.btnEjA:
                i = new Intent(SeleccionActivityClass.this, EjA.class);
                startActivity(i);
                break;
            case R.id.btnEjB:
                i = new Intent(SeleccionActivityClass.this, EjB.class);
                startActivity(i);
                break;
        }

    }
}
