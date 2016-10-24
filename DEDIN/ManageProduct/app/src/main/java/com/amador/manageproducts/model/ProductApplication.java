package com.amador.manageproducts.model;

import android.app.Application;

import com.amador.manageproducts.R;
import com.amador.manageproducts.model.Product;

import java.util.ArrayList;
import java.util.List;


/**
 * Create by Amador
 * */
public class ProductApplication extends Application {

    private ArrayList<Product> products;

    public void saveProduct(Product p){

        products.add(p);
    }

    public List<Product> getProducts(){

        return products;
    }

    public boolean deleteProduct(Product p){

        return products.remove(p);
    }

    @Override
    public void onCreate() {
        super.onCreate();


        products = new ArrayList<Product>();
        saveProduct(new Product("Iboprofeno", "Telo quitato", "Telva", "No te cueles que te acostumbras", 12.95, 20, R.drawable.pill ));
        saveProduct(new Product("Hemoal", "Alivia que no veas", "Hemoal", "No te cueles que te acostumbras", 20.95, 20, R.drawable.pill ));
        saveProduct(new Product("Nolotil", "Pa las muelas lo meho", "Trigo limpio", "No te cueles que te acostumbras", 95.95, 20, R.drawable.pill ));
        saveProduct(new Product("Buscapina", "Te da un sueñesito mu rico", "Quita dolores", "No te cueles que te acostumbras", 122.95, 20, R.drawable.pill ));
        saveProduct(new Product("Aspirina", "La de toa la via cohone", "Test", "No te cueles que te acostumbras", 129.95, 20, R.drawable.pill ));
        saveProduct(new Product("Diazepan", "Ideal para francotiradores", "Telva", "No te cueles que te acostumbras", 192.95, 20, R.drawable.pill ));
        saveProduct(new Product("Iboprofeno", "Telo quitato", "Telva", "No te cueles que te acostumbras", 12.95, 20, R.drawable.pill ));
        saveProduct(new Product("Hemoal", "Alivia que no veas", "Hemoal", "No te cueles que te acostumbras", 20.95, 20, R.drawable.pill ));
        saveProduct(new Product("Nolotil", "Pa las muelas lo meho", "Trigo limpio", "No te cueles que te acostumbras", 95.95, 20, R.drawable.pill ));
        saveProduct(new Product("Buscapina", "Te da un sueñesito mu rico", "Quita dolores", "No te cueles que te acostumbras", 122.95, 20, R.drawable.pill ));
        saveProduct(new Product("Aspirina", "La de toa la via cohone", "Test", "No te cueles que te acostumbras", 129.95, 20, R.drawable.pill ));
        saveProduct(new Product("Diazepan", "Ideal para francotiradores", "Telva", "No te cueles que te acostumbras", 192.95, 20, R.drawable.pill ));
    }
}
