package com.amador.manageproducts.view;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;

import com.amador.manageproducts.adapter.ProductAdapterA;
import com.amador.manageproducts.R;
import com.amador.manageproducts.adapter.ProductAdapterB;
import com.amador.manageproducts.adapter.ProductAdapterCorrerct;

public class ListProductActivity extends ListActivity {

  //  private ArrayAdapter<Product> adapter;
   // ProductAdapterA adapter;
  //  ProductAdapterB adapter;
      ProductAdapterCorrerct adapter;

    private FloatingActionButton btnLaunchAddProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_product_activity);
        init();
    }

    private void init() {

        /*
        //Case 1: Adapter not customized and parameterised
        adapter = new ArrayAdapter<Product>(this, android.R.layout.simple_list_item_1, ((ProductApplication)getApplication()).getProducts());
        getListView().setAdapter(adapter);
        //=========================================//
        */

        //Case 2 A:
        //adapter = new ProductAdapterA(ListProductActivity.this);

        //Case 2 B:
        // adapter = new ProductAdapterB(ListProductActivity.this);

        //Case optime:
        adapter = new ProductAdapterCorrerct(ListProductActivity.this);
        getListView().setAdapter(adapter);
        btnLaunchAddProduct = (FloatingActionButton)findViewById(R.id.floatBtnAddProduct);
        btnLaunchAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(ListProductActivity.this, RegisterProductActivity.class));
            }
        });
    }


}
