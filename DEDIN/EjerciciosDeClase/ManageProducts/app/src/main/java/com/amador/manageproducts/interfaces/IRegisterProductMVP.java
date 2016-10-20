package com.amador.manageproducts.interfaces;

/**
 * Created by amador on 20/10/16.
 */

public interface IRegisterProductMVP {

    interface View{

       void setMessageError(String msg, int idView);

    }

    interface Presenter{

        boolean validateData(String name, String description, String dosage, String brand, double price, int stock, int image);

    }

    interface Model{


    }
}
