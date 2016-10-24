package com.amador.manageproducts.view;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

import com.amador.manageproducts.R;
import com.amador.manageproducts.interfaces.IRegisterProductMVP;
import com.amador.manageproducts.presenter.RegisterPresenter;

public class RegisterProductActivity extends AppCompatActivity implements IRegisterProductMVP.View {


    private IRegisterProductMVP.Presenter presenter;
    private EditText edtName, edtBrand, edtDescrition, edtDosage, edtPrice, edtStock;
    private TextInputLayout tinName, tinBrand, tinDescription, tinDosage, tinPrice, tinStock;
    private ImageView imvProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_product);
        init();
    }

    private void init() {

        presenter = new RegisterPresenter(this);
        edtName = (EditText)findViewById(R.id.edtNameProduct);
        edtBrand = (EditText)findViewById(R.id.edtBrandProduct);
        edtDescrition = (EditText)findViewById(R.id.edtDescriptionProduct);
        edtDosage = (EditText)findViewById(R.id.edtDosageProduct);
        edtPrice = (EditText)findViewById(R.id.edtSaleProduct);
        edtStock = (EditText)findViewById(R.id.edtStockProduct);
        tinName = (TextInputLayout)findViewById(R.id.tinNameProduct);
        tinBrand = (TextInputLayout)findViewById(R.id.tinBrandProduct);
        tinDescription = (TextInputLayout)findViewById(R.id.tinDescriptionProduct);
        tinDosage = (TextInputLayout)findViewById(R.id.tinDosageProduct);
        tinPrice = (TextInputLayout)findViewById(R.id.tinSaleProduct);
        tinName = (TextInputLayout)findViewById(R.id.tinNameProduct);
        tinStock = (TextInputLayout)findViewById(R.id.tinStockProduct);

    }

    @Override
    public void setMessageError(String msg, int idView) {

        switch (idView){

            case R.id.edtNameProduct:
                tinName.setError(msg);
                break;
            case R.id.edtBrandProduct:
                tinBrand.setError(msg);
                break;
            case R.id.edtDescriptionProduct:
                tinDescription.setError(msg);
                break;
            case R.id.edtDosageProduct:
                tinDosage.setError(msg);
                break;
            case R.id.edtSaleProduct:
                tinPrice.setError(msg);
                break;
            case R.id.edtStockProduct:
                tinStock.setError(msg);

        }

    }
}
