package com.amador.recyclerManageProduct.view;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.amador.recyclerManageProduct.presenter.LoginPresenter;
import com.amador.recyclerManageProduct.R;
import com.amador.recyclerManageProduct.interfaces.ILoginMvp;

public class Login_Activity extends AppCompatActivity implements ILoginMvp.View {

    private ILoginMvp.Presenter loginMvp;
    private EditText edtUser;
    private EditText edtPass;
    private TextInputLayout tilUser;
    private TextInputLayout tilPass;
    private TextView txvSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        loginMvp = new LoginPresenter(this);
        txvSignUp = (TextView)findViewById(R.id.txvSignUp);
        edtUser = (EditText)findViewById(R.id.edtLoginUser);
        edtPass = (EditText)findViewById(R.id.edtLoginPass);
        tilUser = (TextInputLayout)findViewById(R.id.tilUser);
        tilPass = (TextInputLayout)findViewById(R.id.tilPass);
        Button btnLogin = (Button) findViewById(R.id.btnLogin);
        Typeface font = Typeface.createFromAsset(getAssets(), "mi_font.ttf");
        edtUser.setTypeface(font);

        if (btnLogin != null) {
            btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                 boolean result = loginMvp.validateCredentials(edtUser.getText().toString(), edtPass.getText().toString());
                    Intent i = new Intent(Login_Activity.this, RecycleProductActivity.class);

                    if(result){

                        startActivity(i);

                    }
                }
            });
        }

        txvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Login_Activity.this, SingUpActivity.class));
            }
        });

        edtUser.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tilUser.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {
                //
            }
        });

        edtPass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tilPass.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {
                //
            }
        });
    }

    @Override
    public void setLoginMessageError(String message, int idView) {
        switch (idView) {
            case R.id.edtLoginUser:
                tilUser.setError(message);
                break;
            case R.id.edtLoginPass:
                tilPass.setError(message);
                break;
        }
    }
}
