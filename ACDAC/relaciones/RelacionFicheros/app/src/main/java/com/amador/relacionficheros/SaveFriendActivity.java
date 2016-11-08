package com.amador.relacionficheros;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.Serializable;
import java.util.regex.Pattern;

public class SaveFriendActivity extends AppCompatActivity {

    private static final String FILE_NAME  = "agenda.txt";
    private EditText edtNameFrien, edtTlfFriend, edtEmailFriend;
    private TextInputLayout tinName, tintlf, tinEmail;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_friend);
        init();

    }

    private void init() {

        edtNameFrien = (EditText)findViewById(R.id.edtNameFriend);
        edtEmailFriend = (EditText)findViewById(R.id.edtEmailFriend);
        edtTlfFriend = (EditText)findViewById(R.id.edtThelehoneFriend);
        btnSave = (Button)findViewById(R.id.btnSaveFriend);
        tinName = (TextInputLayout)findViewById(R.id.tinNameFriend);
        tintlf = (TextInputLayout)findViewById(R.id.tinThelephoneFriend);
        tinEmail = (TextInputLayout)findViewById(R.id.tinTheEmailFriend);


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String mail = edtEmailFriend.getText().toString();

                //Valida el nombre
                if(edtNameFrien.getText().toString().isEmpty()){

                    tinName.setError(getResources().getString(R.string.text_empty));

                    //Valida el telefono (6 digitos minimo)
                }else if(edtTlfFriend.getText().toString().length() < 6){

                    tintlf.setError(getResources().getString(R.string.revise_text));

                    //Valida el patron del email
                }else if(!Patterns.EMAIL_ADDRESS.matcher(mail).matches()){

                    tinEmail.setError(getResources().getString(R.string.no_email));

                }else{

                    SerialiceFriend seralice = new SerialiceFriend(getApplicationContext().getFilesDir().getAbsolutePath(), FILE_NAME);
                    Friend f = new Friend(edtNameFrien.getText().toString(), edtTlfFriend.getText().toString(), edtEmailFriend.getText().toString());
                    Diary d = Diary.getDiary();
                    d.add(f);
                    seralice.saveFriend();
                    finish();

                }


            }
        });

        //Las tres clases anonimas siguientes son necesarias para limpiar el mensaje de error en caso de haber intentado meter
        //un campo invalido
        edtNameFrien.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                tinName.setError(null);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



        edtEmailFriend.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                tinEmail.setError(null);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        edtTlfFriend.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                tintlf.setError(null);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        edtNameFrien.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                tinName.setError(null);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }
}
