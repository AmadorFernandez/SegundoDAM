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

public class ProductAdapterCorrerct extends ArrayAdapter <Product> {

    public ProductAdapterCorrerct(Context context) {
        super(context, R.layout.item_list_product, ((ProductApplication)context.getApplicationContext()).getProducts());


    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View vi = convertView;
        ProductHolder productHolder;

        //Inflate the view if not null
        if(vi == null) {
            LayoutInflater inlate = LayoutInflater.from(getContext());
            vi = inlate.inflate(R.layout.item_list_product, parent, false);
            productHolder = new ProductHolder();
            productHolder.imv = (ImageView)vi.findViewById(R.id.imvProduct);
            productHolder.texNameProduct = (TextView)vi.findViewById(R.id.textProductName);
            productHolder.texBrandProduct = (TextView)vi.findViewById(R.id.txvDosagePro);
            productHolder.texPriceProduct = (TextView)vi.findViewById(R.id.txvPricePro);
            vi.setTag(productHolder);


        }else{

            productHolder = (ProductHolder)vi.getTag();
        }

        //Select color
        if(position % 2 == 0){

            vi.setBackgroundResource(R.color.colorPrimaryDark);

        }else{

            vi.setBackgroundResource(R.color.colorPrimaryLight);
        }

        productHolder.imv.setImageResource(getItem(position).getImage());
        productHolder.texNameProduct.setText(getItem(position).getName());
        productHolder.texBrandProduct.setText(getItem(position).getBrand());
        productHolder.texPriceProduct.setText(getItem(position).getPriceFormat());

        //Assigned data widget
        return vi;

    }

    class ProductHolder{

        ImageView imv;
        TextView texNameProduct;
        TextView texBrandProduct;
        TextView texPriceProduct;


    }
}
