package com.example.usuario.ordendeejecuciondemetodos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private static final String  TAG = "com.example.usuario";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG,"La atividad se ha iniciado ea!!!");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG,"La atividad es visible ea!!!");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG,"La atividad no se ve ea!!!");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG,"La atividad ha finalizado ea!!!");
    }


}
