package com.amador.recyclerManageProduct.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.amador.recyclerManageProduct.R;
import com.amador.recyclerManageProduct.model.Product;
import com.amador.recyclerManageProduct.model.ProductApplication;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Created by usuario on 24/10/16.
 */

public class ProductAdapterRecicler extends RecyclerView.Adapter<ProductAdapterRecicler.ProductViewHolder> {

    private List<Product> data;
    private Context context;

    public ProductAdapterRecicler(Context context) {

        this.context = context;
        data = new ArrayList<Product>(((ProductApplication) context.getApplicationContext()).getProducts());

    }
    public void orderBy(boolean type){
        data.clear();
        if(type) {
            Collections.sort(((ProductApplication) context.getApplicationContext()).getProducts());
            data = new ArrayList<Product>(((ProductApplication) context.getApplicationContext()).getProducts());
        }else {

            Collections.sort(((ProductApplication) context.getApplicationContext()).getProducts(), Collections.<Product>reverseOrder());
            data = new ArrayList<Product>(((ProductApplication) context.getApplicationContext()).getProducts());
        }
        notifyDataSetChanged();
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View item = LayoutInflater.from(context).inflate(R.layout.item_list_product, null);
        return new ProductViewHolder(item);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {

        holder.imv.setImageResource(data.get(position).getImage());
        holder.texNameProduct.setText(data.get(position).getName());
        holder.texBrandProduct.setText(data.get(position).getBrand());
        holder.texPriceProduct.setText(data.get(position).getPriceFormat());


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ProductViewHolder extends  RecyclerView.ViewHolder{

        ImageView imv;
        TextView texNameProduct;
        TextView texBrandProduct;
        TextView texPriceProduct;

        public ProductViewHolder(View item){
            super(item);
            texNameProduct = (TextView)item.findViewById(R.id.textProductName);
            texBrandProduct = (TextView)item.findViewById(R.id.txvDosagePro);
            texPriceProduct = (TextView)item.findViewById(R.id.txvPricePro);
            imv = (ImageView)item.findViewById(R.id.imvProduct);

        }


    }




}
