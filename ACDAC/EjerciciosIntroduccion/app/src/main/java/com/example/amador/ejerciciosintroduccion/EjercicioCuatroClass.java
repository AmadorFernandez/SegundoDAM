package com.example.amador.ejerciciosintroduccion;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

public class EjercicioCuatroClass extends AppCompatActivity {



    Crono crono;
    Button btnSumarTiempo, btnRestarTiempo, btnStar, btnPausa;
    TextView txvCrono, txtNCafeces, txtAlerta;
    Switch asignador;
    byte nCafes;
    MediaPlayer mp;
    private static final String CAFES = "Cafés: ";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejercicio_cuatro);
        inicializar();

    }

    //Método para inicializar y apuntar los objetos a los eventos
    //Es muy largo por el uso de clases anónimas
    private void inicializar() {

        btnStar = (Button)findViewById(R.id.btnLanzarChrono);
        btnPausa = (Button)findViewById(R.id.btnPausarChrono);
        btnPausa.setEnabled(false); //Desavilita el boton para que nunca se de la opción de Start y puse al mismo tiempo
        btnRestarTiempo = (Button)findViewById(R.id.btnRestarTiempo);
        btnSumarTiempo = (Button)findViewById(R.id.btnSumarTiempo);
        txvCrono = (TextView)findViewById(R.id.txvTiempoCrono);
        asignador = (Switch)findViewById(R.id.swhCrono);
        txtAlerta = (TextView)findViewById(R.id.txvAlerta);
        txtAlerta.setText(""); //Establece el texto vacio para que no se muestre
        txtNCafeces = (TextView)findViewById(R.id.txvNCafeces);
        crono = new Crono();
        nCafes = 0;
        txvCrono.setText(crono.getContador());
        txtNCafeces.setText(CAFES + nCafes); //Establece el texto para el contador de cafés
        mp = MediaPlayer.create(getApplicationContext(), R.raw.snd); //Establece el sonido de la alarma


        asignador.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                //Establece el sentido de marcha del crono
                crono.setAscendente(!asignador.isChecked());
            }
        });

        btnPausa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Comprueba que el crono este creado y habilta los botones permitidos en este caso
                if(crono != null){

                    crono.pausa();
                    btnStar.setEnabled(true);
                    btnRestarTiempo.setEnabled(true);
                    btnSumarTiempo.setEnabled(true);
                    btnPausa.setEnabled(false);
                }

            }
        });

        btnSumarTiempo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int value = 0;

                if(crono != null){

                    //Aumenta los minutos
                    value = crono.getMinutos();
                    value++;
                    crono.setMinutos(value);
                }

            }
        });

        btnRestarTiempo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int value = 0;

                if(crono != null){

                    //Disminuye los minutos
                    value = crono.getMinutos();
                    value--;
                    crono.setMinutos(value);
                }
            }
        });

        btnStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Verifica que el crono este creado y no este ya ejecutando
                 if(crono != null && !crono.isEjecutando()){

                    crono.start(); //Arranca el crono

                }else {

                     //Pausa el crono ya que el hilo esta en ejecucion y no se puede volver a llamar a star()
                     crono.setActivo(true);

                 }

                //Habilita los botones permitidos en este caso y deshabilita los que no
                btnStar.setEnabled(false);
                btnPausa.setEnabled(true);
                btnRestarTiempo.setEnabled(false);
                btnSumarTiempo.setEnabled(false);

            }
        });



        //Apunta el evento tick
        crono.setOnCronoListener(new CronoListener() {
            @Override
            public void onTick(Crono c) {

                //Usamos este método para que el hilo del crono pueda modificar variables en el hilo principal
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {


                        AlertDialog.Builder popup = new AlertDialog.Builder(EjercicioCuatroClass.this);
                        //Muestra el tiempo a cada tick
                        txvCrono.setText(crono.getContador());
                        //Comprueba si se puede lanzar la alarma
                         if(crono.getSegundos() == 0 && crono.getMinutos() == 0 && !mp.isPlaying() && crono.isActivo()){



                             mp.start();
                             nCafes++;
                             txtNCafeces.setText(CAFES + nCafes);
                             crono.pausa();
                             reiniciarEstado();

                             //Comprueba si se alcanzo el máximo de cafés
                             if(nCafes == 10){

                                 popup.setTitle("AVISO");
                                 popup.setMessage("No más cafes por el dia de hoy");
                                 popup.setPositiveButton("OK", null);
                                 popup.show();

                             }

                         }

                    }
                });

            }
        });


    }

    //Reinicia el crono y los botones al estado principal
    public void reiniciarEstado(){

        crono.setMinutos(0);
        crono.setSegundos(0);
        txtAlerta.setText("");
        btnStar.setEnabled(true);
        btnRestarTiempo.setEnabled(true);
        btnSumarTiempo.setEnabled(true);
        btnPausa.setEnabled(false);

    }

    //Liberamos los recursos del hilo
    @Override
    protected void onStop() {
        super.onStop();
        crono = null;


    }
}
