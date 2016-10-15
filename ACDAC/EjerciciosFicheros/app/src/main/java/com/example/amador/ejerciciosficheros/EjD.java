package com.example.amador.ejerciciosficheros;

import android.content.Intent;
import android.os.Environment;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class EjD extends AppCompatActivity {

    private static final int REQUEST_CODE_OPEN_FILE = 1;
    private static final String UTF_8 = "UTF-8";
    private static final String UTF_16 = "UTF-16";
    private static final String ISO_8859_15 = "ISO-8859-15";
    private String selectionEncoding;
    private RadioGroup rgCoding;
    private Button btnOpenExplorerFiles, btnCopyFile;
    private TextView txvContenFile;
    private EditText edtPathFileOrigin, edtPathFileDestnty;
    TextInputLayout tiFileNameOrigin, tiFileNameDestiny;
    Memory mFiles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejd);
        init();
    }

    private void init() {


        rgCoding = (RadioGroup)findViewById(R.id.tgGroup);
        btnOpenExplorerFiles = (Button)findViewById(R.id.btnExplore);
        btnCopyFile = (Button)findViewById(R.id.btnSave);
        txvContenFile = (TextView)findViewById(R.id.edtContenFile);
        edtPathFileOrigin = (EditText)findViewById(R.id.edtFileNameD);
        edtPathFileDestnty = (EditText)findViewById(R.id.edtFileNameSave);
        tiFileNameOrigin = (TextInputLayout)findViewById(R.id.inputLayoudD1);
        tiFileNameDestiny = (TextInputLayout)findViewById(R.id.inputLayoudD2);
        selectionEncoding = UTF_8;


        btnOpenExplorerFiles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                i.setType("file/*");
                if(i.resolveActivity(getPackageManager()) != null){

                    startActivityForResult(i, REQUEST_CODE_OPEN_FILE);
                }else{

                    edtPathFileOrigin.setError(getResources().getString(R.string.no_aplication_explorer_file));
                }


            }
        });


        rgCoding.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId){

                    case R.id.rbUTF8:
                        selectionEncoding = UTF_8;
                        break;
                    case R.id.rbUTF16:
                        selectionEncoding = UTF_16;
                        break;
                    case R.id.rbISO:
                        selectionEncoding = ISO_8859_15;
                        break;
                }
            }
        });

        btnCopyFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String pathOrigin = edtPathFileOrigin.getText().toString();
                String savePath = edtPathFileDestnty.getText().toString();
                int result = 10;

                if(!isTextValid(savePath)){

                    edtPathFileDestnty.setError(getResources().getString(R.string.data_error));
                    edtPathFileDestnty.requestFocus();

                }else if(!isTextValid(pathOrigin)) {

                    edtPathFileOrigin.setError(getResources().getString(R.string.data_error));
                    edtPathFileOrigin.requestFocus();


                }else{

                    result = Memory.copyInFile(pathOrigin, Environment.getExternalStorageDirectory().getAbsolutePath()+"/"+savePath, selectionEncoding);

                    checkAswer(result);
                }
            }
        });

    }

    private void checkAswer(int result) {

        switch (result){

            case Memory.CORRECT:
                Toast.makeText(EjD.this, Memory.CORRECT_MSG, Toast.LENGTH_LONG).show();
                break;
            case Memory.ERROR_IO:
                Toast.makeText(EjD.this, Memory.IO_ERROR_MSG, Toast.LENGTH_LONG).show();
                break;
            case Memory.FILE_NOT_FOUNT:
                Toast.makeText(EjD.this, Memory.FILE_NOT_FOUNT_MSG, Toast.LENGTH_LONG).show();
                break;
            case Memory.UNSOPORTED_ENCODING:
                Toast.makeText(EjD.this, Memory.UNSOPORTED_ENCODING_MSG, Toast.LENGTH_LONG).show();
                break;

        }
    }

    private boolean isTextValid(String savePath) {

        return !savePath.isEmpty() && savePath != null;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        String path = "";
        String fileContent = "";

        if(requestCode == REQUEST_CODE_OPEN_FILE){

            if(resultCode == RESULT_OK){

                path = data.getData().getPath();
                edtPathFileOrigin.setText(path);
                fileContent = Memory.readFile(path, selectionEncoding);

                if(!fileContent.equals(Memory.FILE_NOT_FOUNT_MSG) && !fileContent.equals(Memory.IO_ERROR_MSG)){

                    txvContenFile.setText(fileContent);

                }else{

                    txvContenFile.setError(fileContent);
                }



            }else {

                Toast.makeText(this, getResources().getString(R.string.no_select_file), Toast.LENGTH_SHORT).show();
            }
        }
    }
}
