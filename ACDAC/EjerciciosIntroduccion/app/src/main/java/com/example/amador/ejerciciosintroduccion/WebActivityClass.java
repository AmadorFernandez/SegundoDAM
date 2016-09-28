package com.example.amador.ejerciciosintroduccion;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebActivityClass extends AppCompatActivity {

    private static final String URL = "http://dle.rae.es/";
    WebView wb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        inicializar();
    }

    //Inicializa y prepara el WebView para navegar a la RAE
    private void inicializar() {

        WebViewClient wc;
        wb = (WebView)findViewById(R.id.web);
        wb.getSettings().setJavaScriptEnabled(true);
        wb.getSettings().setLoadWithOverviewMode(true);
        wb.getSettings().setUseWideViewPort(true);
        wb.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        wb.setWebViewClient(new WebViewClient());
        wb.loadUrl(URL+getIntent().getExtras().get("parametroUno"));
    }
}
