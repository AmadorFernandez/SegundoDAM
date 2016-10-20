package com.amador.manageproducts;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.amador.manageproducts.model.Product;

public class ListProductActivity extends ListActivity {

    private ArrayAdapter<Product> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_product_activity);
        init();
    }

    private void init() {

        //Caso 1: Adapter no personalizado y parametrizado
        adapter = new ArrayAdapter<Product>(this, android.R.layout.simple_list_item_1, ((ProductApplication)getApplication()).getProducts());
        getListView().setAdapter(adapter);
    }
}
