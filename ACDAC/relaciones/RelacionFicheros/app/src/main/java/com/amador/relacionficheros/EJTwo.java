package com.amador.relacionficheros;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class EJTwo extends AppCompatActivity {



    private TextView txvCronometer;
    private TextView[] textInfoAlarms;
    private String alarms = "1;Paso por el primer kilometro;4;Paso por el segundo kilometro;7;Paso por el tercer kilometro;9;Paso por el ultimo kilometro";
    private int contAlarm = 0;
    private StreamIO streamIO;
    private static final String FILE_NAME  = "alarmas.txt";
    private String[] totalAlarms;
    private String[] totalTextAlarms;
    private String[] totalTimeAlarms;
    private Chrono chrono;
    private Button btnReinice;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejtwo);
        init();

    }

    private void init() {

        //Verifica la SD
        if(valideExternalSD()) {

            btnReinice = (Button) findViewById(R.id.btnReiceTotalAlarms);
            totalTimeAlarms = new String[4];
            totalTextAlarms = new String[4];
            txvCronometer = (TextView) findViewById(R.id.txvTimeChronometer);
            //Asigna el directorio de trabajo al objeto
            streamIO = new StreamIO(Environment.getExternalStorageDirectory().getAbsolutePath());
            //Escribe las alarmas
            streamIO.writeFile(FILE_NAME, alarms, false);
            String[] text = new String[1];
            //Lee el archivo y separa las alarmas
            streamIO.readInFile(FILE_NAME, text);
            totalAlarms = text[0].split(";");
            //Ver codigo de los metodos
            separeText();
            chargeAlarms();
            groupText();


            btnReinice.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //Oculta el boton y reinicia el ejercicio
                    btnReinice.setVisibility(View.INVISIBLE);
                    groupText();
                    chargeAlarms();
                    separeText();
                }
            });

        }else{

            Toast.makeText(EJTwo.this, getResources().getString(R.string.not_have_sd), Toast.LENGTH_LONG).show();
        }

    }


    @Override
    protected void onStop() {
        //Detiene el crono se se sale de la activity
        super.onStop();
        chrono.cancel();
    }

    private void groupText() {

        //Agrupa los textos y los formatea
        textInfoAlarms = new TextView[4];
        textInfoAlarms[0] = (TextView)findViewById(R.id.txvFirstAlarm);
        textInfoAlarms[1] = (TextView)findViewById(R.id.txvSecondAlarm);
        textInfoAlarms[2] = (TextView)findViewById(R.id.txvThirdAlarm);
        textInfoAlarms[3] = (TextView)findViewById(R.id.txvFourAlarm);
        textInfoAlarms[0].setTextColor(getResources().getColor(R.color.colorPrimaryText));
        textInfoAlarms[1].setTextColor(getResources().getColor(R.color.colorPrimaryText));
        textInfoAlarms[2].setTextColor(getResources().getColor(R.color.colorPrimaryText));
        textInfoAlarms[3].setTextColor(getResources().getColor(R.color.colorPrimaryText));

        String aux;
        int cont = 0;


         //Asigna los textos para las alarmas
         for(int i = 0; i < textInfoAlarms.length; i++){

             textInfoAlarms[i].setText(totalAlarms[cont++]);
             aux = textInfoAlarms[i].getText().toString();
             textInfoAlarms[i].setText(aux+" "+totalAlarms[cont++]);

         }
    }

    private void chargeAlarms() {

        //Carga el crono con la alarma indicada
        chrono = new Chrono(Long.parseLong(totalTimeAlarms[contAlarm]) * 1000 * 60, 10);
        chrono.start();
    }

    private void separeText() {

        int contTime = 0;
        int contText = 0;

        //Separa los tiempos de las alarmas de los campos de textos y los coloca en su correspondiente array
        for(int i = 0; i < totalAlarms.length; i++){

            if(i%2 == 0){

                totalTimeAlarms[contTime++] = totalAlarms[i];

            }else {

                totalTextAlarms[contText++] = totalAlarms[i];
            }
        }
    }

    private boolean valideExternalSD(){

        //Verifica si tenemos SD y si se puede escribir en ella
        String result = Environment.getExternalStorageState();
        return result.equals(Environment.MEDIA_MOUNTED);

    }




    class Chrono extends CountDownTimer{


       long timeAlarm;

       public Chrono(long millisInFuture, long countDownInterval) {
           super(millisInFuture, countDownInterval);

           timeAlarm = millisInFuture;
       }

       @Override
       public void onTick(long millisUntilFinished) {

           //Separa los minutos y los segundos y los manda a la pantalla
           timeAlarm = millisUntilFinished;
           long min = (millisUntilFinished / 1000) / 60;
           long sec = (millisUntilFinished / 1000) % 60;
           txvCronometer.setText(String.format("%02d", min) + ":" + String.format("%02d", sec));


       }

       @Override
       public void onFinish() {



           //Si estamos en las tres primeras alarmas pone en roja la alarma agotada y carga la siguiente
           if(contAlarm < 3){

               MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.snd);
               mp.start();
               textInfoAlarms[contAlarm++].setTextColor(getResources().getColor(R.color.error_color));
               chargeAlarms();
            //Si es la ultima alarma lanza el tono y pone visible el boton para el reseteo
           }else if(contAlarm == 3){

                    MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.snd);
                    mp.start();
                    textInfoAlarms[contAlarm].setTextColor(getResources().getColor(R.color.error_color));
                    contAlarm = 0;
                    btnReinice.setVisibility(View.VISIBLE);


           }

       }
   }



}
