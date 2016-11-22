package com.amador.recyclerManageProduct.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.amador.recyclerManageProduct.adapter.ProductAdapterRecicler;

import com.amador.recyclerManageProduct.R;
import com.amador.recyclerManageProduct.presenter.AcountSettingActivity;
import com.amador.recyclerManageProduct.presenter.Preference;

public class RecycleProductActivity extends AppCompatActivity {


    private ProductAdapterRecicler adapter;
    private RecyclerView rcvProduct;
    private static final int ADD_PRODUCT = 0;
    private static final int EDIT_PRODUCT = 1;
    private boolean asc;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycle_product_activity);
        init();
    }

    private void init() {

        asc = true;
        adapter = new ProductAdapterRecicler(this);
        rcvProduct = (RecyclerView)findViewById(R.id.rcProduct);
        rcvProduct.setLayoutManager(new LinearLayoutManager(this));
        rcvProduct.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_manage_product, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case R.id.action_add_product:
                startActivity(new Intent(RecycleProductActivity.this, RegisterProductActivity.class));
                break;
            case R.id.action_orderbyname_product:
                adapter.orderBy(asc);
                asc = !asc;
                break;
            case R.id.userPreferences:
                startActivity(new Intent(RecycleProductActivity.this, Preference.class));
                break;
            case R.id.preferencesGenerals:
                startActivity(new Intent(RecycleProductActivity.this, AcountSettingActivity.class));
                break;


        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case ADD_PRODUCT:
                if (resultCode == RESULT_OK)
                    // Add product
                    break;

        }
    }
}
