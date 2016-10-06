package com.example.usuario.loginrelative;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

/** This is the principal View of the aplication
 *
 * @author Amador Fernández Gónzalez
 *
 * */
public class LoginActivity extends AppCompatActivity implements IloginMVP.View {

    private IloginMVP.Presenter loginMVP;
    private EditText edtPwd;
    private EditText edtUser;
    private Button btnOk;
    private Button btnCancel;
    private CheckBox chkRemenber;
    private static final String TAG = "LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginMVP = new LoginPresenter(this);

        edtUser = (EditText)findViewById(R.id.edtUser);
        edtPwd = (EditText)findViewById(R.id.edtPwd);
        btnOk = (Button)findViewById(R.id.btnOK);
        btnCancel = (Button)findViewById(R.id.btnCancel);

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Call The presenter for the data validations
                loginMVP.validateCredentials(edtPwd.getText().toString(), edtUser.getText().toString());

            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                resetValues();

            }
        });

        if(((LoginApplication)getApplicationContext()).getUser() != null){


            Log.d(TAG,((LoginApplication)getApplicationContext()).getUser().toString());

        }


    }

    private void resetValues() {
        edtPwd.setText("");
        edtUser.setText("");
    }


    @Override
    public void setMessageError(String error) {

        Toast.makeText(this, error, Toast.LENGTH_LONG).show();;

    }

    @Override
    protected void onStop() {
        super.onStop();
        if(((LoginApplication)getApplicationContext()).getUser() != null){


            Log.d(TAG,((LoginApplication)getApplicationContext()).getUser().toString());

        }

    }
}
