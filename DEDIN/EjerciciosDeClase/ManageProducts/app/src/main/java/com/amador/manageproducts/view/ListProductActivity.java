package com.amador.manageproducts.view;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.ArrayAdapter;

import com.amador.manageproducts.model.ProductApplication;
import com.amador.manageproducts.R;
import com.amador.manageproducts.model.Product;

public class ListProductActivity extends ListActivity {

    private ArrayAdapter<Product> adapter;
    private FloatingActionButton btnLaunchAddProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_product_activity);
        init();
    }

    private void init() {
        //Case 1: Adapter not customized and parameterised
        adapter = new ArrayAdapter<Product>(this, android.R.layout.simple_list_item_1, ((ProductApplication)getApplication()).getProducts());
        getListView().setAdapter(adapter);
        //=========================================//
        btnLaunchAddProduct = (FloatingActionButton)findViewById(R.id.floatBtnAddProduct);
        btnLaunchAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(ListProductActivity.this, RegisterProductActivity.class));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(adapter != null){

            adapter.notifyDataSetChanged();
        }

    }
}
