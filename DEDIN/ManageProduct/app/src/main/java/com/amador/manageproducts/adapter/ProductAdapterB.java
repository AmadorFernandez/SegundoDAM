package com.amador.manageproducts.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.amador.manageproducts.R;
import com.amador.manageproducts.model.Product;
import com.amador.manageproducts.model.ProductApplication;

/**
 * Created by usuario on 21/10/16.
 */
public class ProductAdapterB extends ArrayAdapter<Product> {

    public ProductAdapterB(Context context) {
        super(context, R.layout.item_list_product, ((ProductApplication)context.getApplicationContext()).getProducts());


    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View vi = convertView;
        Product p;

        //Inflate the view if not null

        if(vi == null) {
            LayoutInflater inlate = LayoutInflater.from(getContext());
            vi = inlate.inflate(R.layout.item_list_product, parent, false);
        }


        p = getItem(position);

        if(p != null) {
            //Assigned the variables
            ImageView imv = (ImageView)vi.findViewById(R.id.imvProduct);
            TextView texNameProduct = (TextView) vi.findViewById(R.id.textProductName);
            TextView texBrandProduct = (TextView) vi.findViewById(R.id.txvDosagePro);
            TextView texPriceProduct = (TextView) vi.findViewById(R.id.txvPricePro);
            //Assigned text
            texNameProduct.setText(getItem(position).getName());
            texBrandProduct.setText(getItem(position).getBrand());
            texPriceProduct.setText(getItem(position).getPriceFormat());
            imv.setImageResource(getItem(position).getImage());

        }
        //Assigned data widget
        return vi;

    }
}
