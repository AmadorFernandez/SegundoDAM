package com.amador.LoginTable;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.amador.LoginTable.controllers.LoginMVC;
import com.amador.LoginTable.controllers.Login_Activity_Controller;

public class Login_Activity extends AppCompatActivity {

    private LoginMVC loginMVC;
    private EditText edtUser;
    private EditText edtPass;
    private Button btnOk;
    private Button btnCancel;


    // Inicializate
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginMVC = new Login_Activity_Controller();

        edtUser = (EditText)findViewById(R.id.edtUser);
        edtPass = (EditText)findViewById(R.id.edtPass);
        btnOk = (Button)findViewById(R.id.btnOk);
        btnCancel = (Button)findViewById(R.id.btnCancel);

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = edtUser.getText().toString();
                String pass = edtPass.getText().toString();

                //Verify that the camps is correct
                if (TextUtils.isEmpty(user) || TextUtils.isEmpty(pass)) {
                    Toast.makeText(Login_Activity.this, getResources().getString(R.string.empty_data), Toast.LENGTH_SHORT).show();
                }
                else {
                    //Capture the validation
                    int result = loginMVC.validateCredentials(pass);
                    switch (result) {
                        case Login_Activity_Controller.PASSWORD_DIGIT:
                            Toast.makeText(Login_Activity.this, getResources().getString(R.string.password_digit), Toast.LENGTH_SHORT).show();
                            break;
                        case Login_Activity_Controller.PASSWORD_CASE:
                            Toast.makeText(Login_Activity.this, getResources().getString(R.string.password_case), Toast.LENGTH_SHORT).show();
                            break;
                        case Login_Activity_Controller.PASSWORD_LENGTH:
                            Toast.makeText(Login_Activity.this, getResources().getString(R.string.password_length), Toast.LENGTH_SHORT).show();
                            break;
                        case Login_Activity_Controller.OK:
                            //Se lanza la actividad después del login
                            break;
                    }
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetValues();
            }
        });
    }

    private void resetValues() {
        edtPass.setText("");
        edtUser.setText("");
    }
}
