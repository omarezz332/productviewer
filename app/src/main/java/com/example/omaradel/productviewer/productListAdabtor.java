package com.example.omaradel.productviewer;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;


public class productListAdabtor extends ArrayAdapter<product>{
    private ArrayList<product>products;

    public productListAdabtor(@NonNull Context context, int resource, @NonNull ArrayList<product> objects) {
        super(context, resource, objects);
        products=objects;

    }



    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).
                    inflate(R.layout.card_view, parent, false);
        }

        product product = products.get(position);

TextView name=convertView.findViewById(R.id.name);
name.setText(product.getName());

TextView price=convertView.findViewById(R.id.price);
        String stringdouble= Double.toString(product.getPrice());
price.setText(stringdouble);


        ImageView imageView=convertView.findViewById(R.id.image_view);
        Glide.with(getContext())
                .load(product.getImage_url())
                .into(imageView);
        return convertView;

    }

}
