package com.amador.relacionficheros;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Environment;
import android.preference.PreferenceActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import cz.msebera.android.httpclient.Header;

public class EjFour extends AppCompatActivity {

    private Button btnConect, btnSave;
    private EditText edtUrl, edtFileName;
    private RadioGroup rg;
    private RadioButton rbJava, rbAAHC, rbVolley;
    private WebView webView;
    private RelativeLayout rltSaveDate;
    private static final String ENCODING = "UTF-8";
    private static final String CONECTION_JAVA = "JAVA";
    private static final String CONECTION_AAHC = "AAHC";
    private static final String CONECTION_VOLLEY = "VOLLEY";
    private String conectionSelected;
    private boolean conectionEnambled;
    private boolean sd;
    private StreamIO streamIO;
    private StringBuilder contentWeb;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ej_four);
        init();
    }


    //Enlaza los objetos con el xml
    private void init() {

        btnConect = (Button)findViewById(R.id.btnConect);
        btnSave = (Button)findViewById(R.id.btnSave);
        edtUrl = (EditText)findViewById(R.id.edtUrl);
        edtFileName = (EditText)findViewById(R.id.edtFileName);
        rltSaveDate = (RelativeLayout)findViewById(R.id.relativeSaveContainer);
        rg = (RadioGroup)findViewById(R.id.rgGroup);
        rbJava = (RadioButton)findViewById(R.id.rbJava);
        rbAAHC = (RadioButton)findViewById(R.id.rbAsincronus);
        rbVolley = (RadioButton)findViewById(R.id.rb_Volley);
        webView = (WebView)findViewById(R.id.web);
        sd = sdAvaiable();
        conectionEnambled = isNetworkAvailable();
        conectionSelected = CONECTION_JAVA;
        WebViewClient client = new WebViewClient(){

            //Cuando termina de cargar el contenido se pone visible el VewView
            @Override
            public void onPageFinished(WebView view, String url) {

                super.onPageFinished(view,url);
                rltSaveDate.setVisibility(View.VISIBLE);

            }
        };

        //Al principio todo esta invisible
        webView.setVisibility(View.INVISIBLE);
        rltSaveDate.setVisibility(View.INVISIBLE);
        webView.setWebViewClient(client);


        //region Establece el tipo de conexión elegida cada vez que se escoge un RadioButton
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId){

                    case R.id.rbJava:
                        conectionSelected = CONECTION_JAVA;
                        break;
                    case R.id.rbAsincronus:
                        conectionSelected = CONECTION_AAHC;
                        break;
                    case R.id.rb_Volley:
                        conectionSelected = CONECTION_VOLLEY;
                        break;

                }

                rltSaveDate.setVisibility(View.INVISIBLE);
                webView.setVisibility(View.INVISIBLE);
            }
        });
        //endregion


        btnConect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isNetworkAvailable())
                    setConection();
                else
                    Toast.makeText(EjFour.this, "No hay una conexión disponible", Toast.LENGTH_LONG).show();




            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                streamIO = new StreamIO(Environment.getExternalStorageDirectory().getAbsolutePath());

                if(TextUtils.isEmpty(edtFileName.getText().toString()))
                    Toast.makeText(EjFour.this, "Tienes que darle un nombre al archivo", Toast.LENGTH_LONG).show();
                else {

                    int result = streamIO.writeFile(edtFileName.getText().toString(), contentWeb.toString(), false);

                    switch (result){

                        case StreamIO.CORRECT_CODE:
                            Toast.makeText(EjFour.this, StreamIO.CORRECT_MSG, Toast.LENGTH_LONG).show();
                            break;

                    }
                }
            }
        });



    }

    private void conectAAHC() {
        String texto = edtUrl.getText().toString();
        final ProgressDialog progress = new ProgressDialog(EjFour.this);



        AsyncHttpClient client = new AsyncHttpClient();
        client.get(texto, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                progress.dismiss();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {

                progress.dismiss();
                webView.loadDataWithBaseURL(null, responseString, "text/html", ENCODING, null);
                contentWeb = new StringBuilder(responseString);


            }

            @Override
            public void onStart() {
                // called before request is started
                progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progress.setMessage("Conectando . . .");
                progress.setCancelable(false);
                progress.show();
            }
        });
    }

    private void conetarVolley(){

        RequestQueue queue = Volley.newRequestQueue(EjFour.this);
        final ProgressDialog progress = new ProgressDialog(EjFour.this);
        String url = edtUrl.getText().toString();
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setMessage("Conectando . . .");
        progress.setCancelable(false);
        progress.show();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        progress.dismiss();
                        webView.loadDataWithBaseURL(null, response, "text/html", ENCODING, null);
                        contentWeb = new StringBuilder(response);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                progress.dismiss();
                Toast.makeText(EjFour.this, error.toString(), Toast.LENGTH_LONG).show();
            }
        });

        queue.add(stringRequest);
    }

    private void setConection() {

        webView.setVisibility(View.VISIBLE);
        switch (conectionSelected){

            case CONECTION_JAVA:
                if(edtUrl.getText().toString().isEmpty())
                    Toast.makeText(EjFour.this, "No hay una ruta establecida", Toast.LENGTH_LONG).show();
                else{

                    if(isNetworkAvailable()){

                        ConectionAsynt conext = new ConectionAsynt(EjFour.this, ENCODING);
                        conext.execute(edtUrl.getText().toString());
                    }

                    else
                        Toast.makeText(EjFour.this, "No hay una conexión disponible", Toast.LENGTH_LONG).show();
                }
                break;
            case CONECTION_AAHC:
                if(edtUrl.getText().toString().isEmpty())
                    Toast.makeText(EjFour.this, "No hay una ruta establecida", Toast.LENGTH_LONG).show();
                else{

                    if(isNetworkAvailable())
                        conectAAHC();
                    else
                        Toast.makeText(EjFour.this, "No hay una conexión disponible", Toast.LENGTH_LONG).show();
                }

                break;
            case CONECTION_VOLLEY:
                if(edtUrl.getText().toString().isEmpty())
                    Toast.makeText(EjFour.this, "No hay una ruta establecida", Toast.LENGTH_LONG).show();
                else{

                    if(isNetworkAvailable())
                        conetarVolley();
                    else
                        Toast.makeText(EjFour.this, "No hay una conexión disponible", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    private boolean isNetworkAvailable(){
        boolean result = false;
        ConnectivityManager cm = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected())
            result = true;

        return result;

    }

    private boolean sdAvaiable(){

        String state = Environment.getExternalStorageState();
        return state.equals(Environment.MEDIA_MOUNTED);
    }

    class ConectionAsynt extends AsyncTask<String, Integer, Result>{


        private ProgressDialog progreso;
        private Context context;
        private String encoding;
        public ConectionAsynt(Context context, String encoding){

            this.context = context;
            this.encoding = encoding;
        }

        protected void onPreExecute() {
            progreso = new ProgressDialog(context);
            progreso.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progreso.setMessage("Conectando . . .");
            progreso.setCancelable(true);
            progreso.setOnCancelListener(new DialogInterface.OnCancelListener() {
                public void onCancel(DialogInterface dialog) {
                    ConectionAsynt.this.cancel(true);
                }
            });
        progreso.show();
        }

        @Override
        protected Result doInBackground(String... params) {
            int i = 1;
            Result result = new Result();
            publishProgress(i);
            result = conectJava(params[0]);
            return result;
        }

        private String read(InputStream entrada){

            BufferedReader in;
            String linea;
            StringBuilder miCadena = new StringBuilder();
            in = new BufferedReader(new InputStreamReader(entrada), 32000);
            try {
                while ((linea = in.readLine()) != null)
                    miCadena.append(linea);
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return miCadena.toString();
        }

        private Result conectJava(String texto){

            URL url;
            HttpURLConnection urlConnection = null;
            int respuesta;
            Result resultado = new Result();
            try {
                url = new URL(texto);
                urlConnection = (HttpURLConnection) url.openConnection();
                respuesta = urlConnection.getResponseCode();
                if (respuesta == HttpURLConnection.HTTP_OK) {
                    resultado.setCode(true);
                    resultado.setConten(read(urlConnection.getInputStream()));


                } else {
                    resultado.setCode(false);
                    resultado.setMenssage("Error en el acceso a la web: " + String.valueOf(respuesta));
                }
            } catch (IOException e) {
                resultado.setCode(false);
                resultado.setMenssage("Excepción: " + e.getMessage());
            } finally {
                try {
                    if (urlConnection != null)
                        urlConnection.disconnect();
                } catch (Exception e) {
                    resultado.setCode(false);
                    resultado.setMenssage("Excepción: " + e.getMessage());
                }

            }

            return resultado;
        }

        @Override
        protected void onPostExecute(Result result) {
            progreso.dismiss();

            if(result.isCode()){

                webView.loadDataWithBaseURL(null, result.getConten(), "text/html", encoding, null);
                contentWeb = new StringBuilder(result.getConten());
            }

        }

        protected void onProgressUpdate(Integer... progress) {
            progreso.setMessage("Conectando " + Integer.toString(progress[0]));
        }
    }

}




