package com.amador.manageproducts.presenter;

import com.amador.manageproducts.interfaces.IRegisterProductMVP;

/**
 * Created by amador on 20/10/16.
 */

public class RegisterPresenter implements IRegisterProductMVP.Presenter {

    IRegisterProductMVP.View view;

    public RegisterPresenter(IRegisterProductMVP.View view){

        view = view;

    }

    @Override
    public boolean validateData(String name, String description, String dosage, String brand, double price, int stock, int image) {
        boolean result = true;




        return result;
    }
}
