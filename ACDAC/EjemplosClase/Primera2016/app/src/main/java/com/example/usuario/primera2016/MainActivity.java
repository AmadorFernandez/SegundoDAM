package com.example.usuario.primera2016;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn;
    TextView textView;
    String date = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        referenciar();

    }

    protected void referenciar(){
        btn = (Button)findViewById(R.id.btnBonton);
        textView = (TextView)findViewById(R.id.tvFecha);
        btn.setOnClickListener(this);
        textView.setText(new Date().toString());
        actualizar();


    }

    void actualizar(){
        textView.setText(new Date().toString());

    }

    @Override
    public void onClick(View v) {
        actualizar();

    }
}
