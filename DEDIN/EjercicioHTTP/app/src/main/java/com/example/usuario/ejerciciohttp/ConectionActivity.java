package com.example.usuario.ejerciciohttp;

import android.os.StrictMode;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class ConectionActivity extends AppCompatActivity implements View.OnClickListener {

    TextInputLayout tinUrl;
    Button btnConectar;
    WebView wvPagina;
    EditText edtUrl;
    RadioGroup rgConection;
    RadioButton rbJavaNet, rbApache;
    TextView txv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conection);
        init();
    }

    private void init() {

        tinUrl = (TextInputLayout)findViewById(R.id.tinUrl);
        wvPagina = (WebView)findViewById(R.id.wvPagina);
        btnConectar = (Button)findViewById(R.id.btnConectar);
        edtUrl = (EditText)findViewById(R.id.edtUrl);
        rgConection = (RadioGroup) findViewById(R.id.rgConection);
        rbJavaNet = (RadioButton)findViewById(R.id.rbJavaNet);
        rbApache = (RadioButton)findViewById(R.id.rbApache);
        txv = (TextView)findViewById(R.id.txvTiempo);
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitNetwork().build());
        btnConectar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String texto = edtUrl.getText().toString();
        long inicio, fin;
        Resultado resultado;
        if (v == btnConectar) {
            inicio = System.currentTimeMillis();
            if (rbJavaNet.isChecked())
                resultado = Conexion.conectarJava(texto);
            else
                resultado = Conexion.conectarApache(texto);
            fin = System.currentTimeMillis();
            if (resultado.isCodigo())
                wvPagina.loadDataWithBaseURL(null, resultado.getContenido(),"text/html", "UTF-8", null);
            else
                wvPagina.loadDataWithBaseURL(null, resultado.getMensaje(),"text/html", "UTF-8", null);
            txv.setText("Duración: " + String.valueOf(fin - inicio) + " milisegundos");
        }
    }
}
