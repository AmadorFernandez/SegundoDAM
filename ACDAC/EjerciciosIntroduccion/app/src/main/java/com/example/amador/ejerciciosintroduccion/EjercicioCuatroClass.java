package com.example.amador.ejerciciosintroduccion;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

public class EjercicioCuatroClass extends AppCompatActivity {

    Button btnSumarTiempo, btnRestarTiempo, btnStar;
    TextView txvCrono, txtNCafeces, txtAlerta;
    Switch asignador;
    byte nCafes;
    MediaPlayer mp;
    private static final String CAFES = "Cafés: ";
    int segundos;
    int minutos;
    Crono crono;
    boolean ascendente;
    boolean activo;



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
        btnRestarTiempo = (Button)findViewById(R.id.btnRestarTiempo);
        btnSumarTiempo = (Button)findViewById(R.id.btnSumarTiempo);
        txvCrono = (TextView)findViewById(R.id.txvTiempoCrono);
        asignador = (Switch)findViewById(R.id.swhCrono);
        txtAlerta = (TextView)findViewById(R.id.txvAlerta);
        txtAlerta.setText(""); //Establece el texto vacio para que no se muestre
        txtNCafeces = (TextView)findViewById(R.id.txvNCafeces);
        nCafes = 0;
        txtNCafeces.setText(CAFES + nCafes); //Establece el texto para el contador de cafés
        mp = MediaPlayer.create(getApplicationContext(), R.raw.snd); //Establece el sonido de la alarma
        minutos = 0;
        segundos = 0;
        ascendente = true;
        activo = false;
        actualizarTiempo();

        btnSumarTiempo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Suma y actualiza el tiempo
                minutos++;
                actualizarTiempo();

            }
        });

        btnRestarTiempo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Si no se ha llegado a 0 resta y actualiza el tiempo
                if (minutos > 0){

                    minutos--;
                    actualizarTiempo();
                }
            }
        });

        asignador.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                //Modifica el controlador del sentido de la cuenta
                ascendente = !isChecked;

            }
        });



        btnStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // No permite entrar si se alcanzo el limite de cafés
                if(nCafes < 10) {

                    //Deshabilita los botones e inicializa el tiempo
                    activo = true;
                    btnSumarTiempo.setEnabled(false);
                    btnRestarTiempo.setEnabled(false);
                    btnStar.setEnabled(false);
                    crono = new Crono(minutos * 60 * 1000, 10);
                    crono.start();

                }


            }
        });



    }

    //Actualiza el tiempo
    private void actualizarTiempo() {

        txvCrono.setText(String.format("%02d", minutos) + ":" + String.format("%02d", segundos));
    }


    public class Crono extends CountDownTimer{


      long total, intervalo;


      /**
       * @param millisInFuture    The number of millis in the future from the call
       *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
       *                          is called.
       * @param countDownInterval The interval along the way to receive
       *                          {@link #onTick(long)} callbacks.
       */
      public Crono(long millisInFuture, long countDownInterval) {
          super(millisInFuture, countDownInterval);

          total = millisInFuture;
      }

      @Override
      public void onTick(long millisUntilFinished) {

          // Posición [0] minutos y [1] segundos
          long[] time = new long[2];


          // Verifica el sentido de la cuenta
          if(ascendente){

              intervalo = total - millisUntilFinished;

          }else{

             intervalo = millisUntilFinished;
          }

          //Se fracciona y actualiza el tiempo
          fraccionarTiempo(time);
          txvCrono.setText(String.format("%02d", time[0]) + ":" + String.format("%02d", time[1]));

      }

        //Extrae minutos y segundos del tiempo total
      private void fraccionarTiempo(long[] time) {

          time[1] = (intervalo / 1000) % 60;
          time[0] = (intervalo / 1000) / 60;
      }

      @Override
      public void onFinish() {


          //Reinicia las variables, habila los botones, lanza la alarma y verifica si se alcanzo el límite de cafes
            AlertDialog.Builder popup = new AlertDialog.Builder(EjercicioCuatroClass.this);
            txtAlerta.setText("Pausa terminada loco\n vuelta al curro");
            btnSumarTiempo.setEnabled(true);
            btnRestarTiempo.setEnabled(true);
            btnStar.setEnabled(true);
            txvCrono.setText("00:00");
            nCafes++;
            txtNCafeces.setText(CAFES + nCafes);
            minutos = 0;
            segundos = 0;
            mp.start();

            mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                 @Override
                 public void onCompletion(MediaPlayer mp) {

                  txtAlerta.setText("");
                }
            });

           if(nCafes == 10){

               popup.setTitle("Aviso");
               popup.setMessage("Se alcazo el máximo de cafés permitidos, cierra la aplicación y vuelve a abrirla si quieres más.");
               popup.setPositiveButton("Ok", null);
               popup.show();
           }




      }
  }


}
