package com.example.amador.ejerciciosficheros;

import android.content.res.AssetManager;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class EjC extends AppCompatActivity {

    private EditText edtOne, edtTwo, edtTree, edtFour;
    private Button btnAddExternalFile, btnAddInternalFile;
    private ImageButton ivBtnSum;
    private TextView txvExternal, txvInternal, txvSaveResulInfo;
    private static final String FILE_NAME  = "datos.txt";
    private static final String FILE_EXTERNAL_RESULT_INFO = "operaciones.txt";
    private Memory memoryFiles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejc);
        init();
    }


    private void init() {

        edtOne = (EditText)findViewById(R.id.edtFirstDataC);
        edtTwo = (EditText)findViewById(R.id.edtSecontDataC);
        edtTree = (EditText)findViewById(R.id.edtThirdDataC);
        edtFour = (EditText)findViewById(R.id.edtFourthDataC);
        btnAddExternalFile = (Button)findViewById(R.id.btnAddExternallFile);
        btnAddInternalFile = (Button)findViewById(R.id.btnAddInternalFile);
        txvSaveResulInfo = (TextView)findViewById(R.id.edtSaveResultInfo);
        txvExternal = (TextView)findViewById(R.id.txvExternalPath);
        txvInternal = (TextView)findViewById(R.id.txvInternalPath);
        ivBtnSum = (ImageButton)findViewById(R.id.ibtnSumC);
        edtTree.setText(valueOfRaw());
        edtFour.setText(valueOfAssets());
        edtTree.setEnabled(false);
        edtFour.setEnabled(false);



        if(isFileExits(getFilesDir().getPath())){

            edtOne.setText(readInFile(getFilesDir().getPath()));
            edtOne.setEnabled(false);
            btnAddInternalFile.setVisibility(View.INVISIBLE);
            txvInternal.setText(getFilesDir().getPath() + FILE_NAME);


        }else{

            edtOne.setHint(R.string.not_search_file_press);

        }

        if(isFileExits(Environment.getExternalStorageDirectory().getPath())){


            edtTwo.setText(readInFile(Environment.getExternalStorageDirectory().getPath()));
            edtTwo.setEnabled(false);
            btnAddExternalFile.setVisibility(View.INVISIBLE);
            txvExternal.setText(Environment.getExternalStorageDirectory().getPath() + "/"+ FILE_NAME);

        }else{

            edtTwo.setHint(R.string.not_search_file_press);
        }

        btnAddExternalFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String value = String.valueOf(edtTwo.getText());

                if (canReadWriteExternal()) {


                    if (value.matches("[0-9]+")) {
                        writeInFile(String.valueOf(edtTwo.getText()), Environment.getExternalStorageDirectory().getPath());
                        btnAddExternalFile.setVisibility(View.INVISIBLE);
                        edtTwo.setEnabled(false);
                        txvExternal.setText(Environment.getExternalStorageDirectory().getPath() + FILE_NAME);
                    } else {

                        edtTwo.setText("");
                    }

                }else{

                    Toast.makeText(EjC.this, R.string.not_media_mount, Toast.LENGTH_LONG).show();

                }

            }
        });


        btnAddInternalFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String value = String.valueOf(edtOne.getText());

                if(value.matches("[0-9]+")) {
                    writeInFile(String.valueOf(edtOne.getText()), getFilesDir().getPath());
                    btnAddInternalFile.setVisibility(View.INVISIBLE);
                    edtOne.setEnabled(false);
                    txvInternal.setText(getFilesDir().getPath() + "/" + FILE_NAME);
                }else {

                    edtOne.setText("");
                }

            }
        });

        ivBtnSum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                double[] values = new double[4];

                try {
                    values[0] = Double.parseDouble(String.valueOf(edtOne.getText()));
                    values[1] = Double.parseDouble(String.valueOf(edtTwo.getText()));
                    values[2] = Double.parseDouble(String.valueOf(edtTree.getText()));
                    values[3] = Double.parseDouble(String.valueOf(edtFour.getText()));

                    writeInFile(String.valueOf(Operation.sum(values)), Environment.getExternalStorageDirectory().getPath(), FILE_EXTERNAL_RESULT_INFO);
                    txvSaveResulInfo.setText("El resultado " + readInFile(Environment.getExternalStorageDirectory().getPath(), FILE_EXTERNAL_RESULT_INFO)
                    + " fue guardado en " + FILE_EXTERNAL_RESULT_INFO);



                } catch (NumberFormatException e) {

                    Toast.makeText(EjC.this, R.string.data_error, Toast.LENGTH_LONG).show();
                }


            }
        });


    }

    private String valueOfRaw(){

        InputStream insResource = null;
        BufferedReader bfReaderResource = null;
        String value = "";


        insResource = getResources().openRawResource(R.raw.datos);
        bfReaderResource = new BufferedReader(new InputStreamReader(insResource));

        try {
            value = bfReaderResource.readLine();
        } catch (IOException e) {
            Toast.makeText(EjC.this, R.string.io_error, Toast.LENGTH_LONG).show();
        }finally {

            if(bfReaderResource != null){

                try {
                    bfReaderResource.close();
                } catch (IOException e) {

                }


            }

            return value;
        }


    }

    private String valueOfAssets(){

        AssetManager asManager = getAssets();
        BufferedReader bfReader = null;
        String value = "";

        try {
            bfReader = new BufferedReader(new InputStreamReader(asManager.open("datos")));
            value = bfReader.readLine();


        } catch (IOException e) {
            Toast.makeText(EjC.this, R.string.io_error, Toast.LENGTH_LONG).show();
        }finally {

            if(bfReader != null){

                try {
                    bfReader.close();
                } catch (IOException e) {

                }
            }

            return value;
        }


    }

    public boolean isFileExits(String path){

        File fileInfo = new File(path, FILE_NAME);
        return fileInfo.exists();

    }

    private void writeInFile(String text, String path){

        BufferedWriter bfWriter = null;
        File fileInfo = new File(path, FILE_NAME);


        try {

            bfWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileInfo)));
            bfWriter.write(text);

        } catch (FileNotFoundException e) {

            Toast.makeText(EjC.this, R.string.not_search_file, Toast.LENGTH_LONG).show();

        } catch (IOException e) {

            Toast.makeText(EjC.this, R.string.io_error, Toast.LENGTH_LONG).show();

        }finally {

            if(bfWriter != null){

                try {
                    bfWriter.close();
                } catch (IOException e) {

                }
            }

        }

    }

    private void writeInFile(String text, String path, String fileName){

        BufferedWriter bfWriter = null;
        File fileInfo = new File(path, fileName);


        try {

            bfWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileInfo)));
            bfWriter.write(text);

        } catch (FileNotFoundException e) {

            Toast.makeText(EjC.this, R.string.not_search_file, Toast.LENGTH_LONG).show();

        } catch (IOException e) {

            Toast.makeText(EjC.this, R.string.io_error, Toast.LENGTH_LONG).show();

        }finally {

            if(bfWriter != null){

                try {
                    bfWriter.close();
                } catch (IOException e) {

                }
            }

        }

    }

    private String readInFile(String path){


        BufferedReader bfReader = null;
        String result = "";
        File fileInfo = null;


        try {



            fileInfo = new File(path, FILE_NAME);
            bfReader =  new BufferedReader(new InputStreamReader(new FileInputStream(fileInfo)));
            result = bfReader.readLine();


        } catch (FileNotFoundException e) {

            Toast.makeText(EjC.this, R.string.not_search_file, Toast.LENGTH_LONG).show();

        } catch (IOException e) {

            Toast.makeText(EjC.this, R.string.io_error, Toast.LENGTH_LONG).show();
        }finally {

            if(bfReader != null){

                try {
                    bfReader.close();
                } catch (IOException e) {

                }

            }

            return result;
        }



    }

    private String readInFile(String path, String filename){


        BufferedReader bfReader = null;
        String result = "";
        File fileInfo = null;


        try {



            fileInfo = new File(path, filename);
            bfReader =  new BufferedReader(new InputStreamReader(new FileInputStream(fileInfo)));
            result = bfReader.readLine();


        } catch (FileNotFoundException e) {

            Toast.makeText(EjC.this, R.string.not_search_file, Toast.LENGTH_LONG).show();

        } catch (IOException e) {

            Toast.makeText(EjC.this, R.string.io_error, Toast.LENGTH_LONG).show();
        }finally {

            if(bfReader != null){

                try {
                    bfReader.close();
                } catch (IOException e) {

                }

            }

            return result;
        }



    }

    private boolean canReadWriteExternal(){

        String state = Environment.getExternalStorageState();
        return state.equals(Environment.MEDIA_MOUNTED);
    }


}
