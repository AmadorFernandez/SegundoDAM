package com.amador.relacionficheros;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Environment;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class EjTree extends AppCompatActivity {

    private Button btnCal;
    private Calendar calenSelect;
    private Calendar actualDate;
    private TextView txvResult;
    private DatePicker datePiker;
    private RelativeLayout relative;
    EditText edtMedia;
    TextInputLayout tinMedia;
    private StreamIO stream;
    private static final String FILE_NAME  = "datos_menstruapp.txt";





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ej_tree);
        init();




    }

    private void init() {

        btnCal = (Button)findViewById(R.id.btnAddMestruapp);
        datePiker = (DatePicker)findViewById(R.id.datePiker);
        relative = (RelativeLayout)findViewById(R.id.relativeResult);
        txvResult = (TextView)findViewById(R.id.txvResultDays);
        edtMedia = (EditText)findViewById(R.id.edtMediaMenstruapp);
        tinMedia = (TextInputLayout)findViewById(R.id.tinMenstruapp);
        actualDate = new GregorianCalendar(Locale.getDefault());


        //Verifica si hay SD
        if(verifyExternalSD()){

            //Prepara el objeto para los ficheros
            stream = new StreamIO(Environment.getExternalStorageDirectory().getAbsolutePath());

            btnCal.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //Recoge la media indicada
                    String media = edtMedia.getText().toString();

                    //Verifica que no este vacia
                    if(media.isEmpty()){

                        tinMedia.setError(getResources().getString(R.string.text_empty));

                    }else{

                        //Carga el calendario con al fecha indicada por el usuario
                        calenSelect = new GregorianCalendar(datePiker.getYear(), datePiker.getMonth(), datePiker.getDayOfMonth());
                        startCal(media); //Ver codigo
                    }
                }
            });


        }else {

            Toast.makeText(EjTree.this, getResources().getString(R.string.not_have_sd), Toast.LENGTH_LONG).show();

        }

        //Si se ha lanzado en el mensaje de error en el campo de texto esto hace que se elimine al escribir
        edtMedia.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                tinMedia.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    private void startCal(String m) {

        int media = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String aux = "";
        String line = "";
        String warnig = " (ojo que es hoy)";
        Calendar[] dates = new Calendar[4]; //Guardará las fechas de ovulacion

        if(calenSelect.getTimeInMillis() > actualDate.getTimeInMillis()){

            Toast.makeText(EjTree.this, getResources().getString(R.string.date_superior), Toast.LENGTH_LONG).show();

        }else{

            //Convertimos a entero (No hay riesgo porque el campo solo deja meter numeros
            media = Integer.parseInt(m);
            calenSelect.add(Calendar.DAY_OF_MONTH, media / 2); //Ajustamos el perido de ovulacion
            //Cada posicion del array guarda una de las fechas
            dates[0] = (Calendar)calenSelect.clone();
            dates[0].add(Calendar.DAY_OF_MONTH, -2);
            dates[1] = (Calendar)calenSelect.clone();
            dates[1].add(Calendar.DAY_OF_MONTH, -1);
            dates[2] = calenSelect; //La segunda guarda la fecha de la que partimos para el calculo
            dates[3] = (Calendar)calenSelect.clone();
            dates[3].add(Calendar.DAY_OF_MONTH, 1);
            //Esto lo pide el enunciado puedes verificar que el fichero se creo y se guardarón las dos fechas
            stream.writeFile(FILE_NAME, sdf.format(dates[0].getTime())+"\n", false);
            stream.writeFile(FILE_NAME, sdf.format(dates[3].getTime()), true);
            //Pone visible el ViewGroup que va ha mostrar los resultados
            relative.setVisibility(View.VISIBLE);
            txvResult.setText("");


            //Recorre las fechas para mostrarlas
            for(int i = 0; i < dates.length; i++){

                aux = sdf.format(dates[i].getTime());
                line = sdf.format(actualDate.getTime());

                //Verifica si es la fecha de hoy
                if(aux.equals(line)){

                    txvResult.append(line+warnig+"\n");



                }else{


                    txvResult.append(aux+"\n");

                }
            }

        }
    }

    private boolean verifyExternalSD(){

        //Lo tipico para asegurarnos de que hay externa
        String result = Environment.getExternalStorageState();
        return result.equals(Environment.MEDIA_MOUNTED);
    }
}
