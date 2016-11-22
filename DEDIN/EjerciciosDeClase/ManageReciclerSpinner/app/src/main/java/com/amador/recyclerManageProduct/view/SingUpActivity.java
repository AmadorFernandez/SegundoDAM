package com.amador.recyclerManageProduct.view;

import android.content.res.TypedArray;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.RadioGroup;
import android.widget.Spinner;


import com.amador.recyclerManageProduct.R;

public class SingUpActivity extends AppCompatActivity {

    private Spinner spCounty;
    private Spinner spCity;
    private Button btnSignup;
    private RadioGroup typeClient;
    private TextInputLayout tilNameCompany;
    private AdapterView.OnItemSelectedListener spinnerListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sin_up);

        spCounty = (Spinner) findViewById(R.id.spnProvincia);
        spCity = (Spinner) findViewById(R.id.spnLocalidad);


        tilNameCompany = (TextInputLayout) findViewById(R.id.tilCompany);

        typeClient = (RadioGroup) findViewById(R.id.rgCompany);
        typeClient.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch (checkedId) {
                    case R.id.rbtClient:
                        showCompany(false);
                        break;
                    case R.id.rbtCompany:
                        showCompany(true);
                        break;
                }
            }
        });

        loadSpinnerCounty();


    }

    private void showCompany(boolean b) {
        tilNameCompany.setVisibility(b ? View.VISIBLE : View.GONE);
    }

    public void signup(View view) {

    }

    private void loadSpinnerCounty() {

        spinnerListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {


                if(view != null){

                    CheckedTextView item = (CheckedTextView)view;
                    Spinner spinner = (Spinner)item.getParent();
                    switch (spinner.getId()) {
                        case R.id.spnProvincia:
                            loadSpinnerCity(position);
                            break;
                        case R.id.spnLocalidad:
                            break;
                    }
                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        };


      ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(SingUpActivity.this,
               R.array.provincias, android.R.layout.simple_spinner_dropdown_item);
        spCounty.setOnItemSelectedListener(spinnerListener);
        spCounty.setAdapter(adapter);


    }

    private void loadSpinnerCity(int pos) {

        TypedArray arraylocalities = getResources().obtainTypedArray(R.array.array_provincia_a_localidades);
        CharSequence[] localities = arraylocalities.getTextArray(pos);
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(SingUpActivity.this, android.R.layout.simple_spinner_dropdown_item,
                localities);

        spCity.setAdapter(adapter);
    }
}
