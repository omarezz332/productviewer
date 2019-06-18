package com.example.omaradel.productviewer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.nio.InvalidMarkException;
import java.util.ArrayList;
import java.util.List;


import static com.example.omaradel.productviewer.MainActivity.DB_marchents_list;
import static com.example.omaradel.productviewer.load_Data.contactList_details;
import static com.example.omaradel.productviewer.load_Data.list_details;


//import static com.example.omaradel.productviewer.load_Data.contactList1;

/**
 * Created by omar adel on 7/8/2018.
 */

public class Details_product extends Fragment {
    //    private ArrayList<ArrayList<Product_marchents>> contactList_detail=new ArrayList<>();
//    private ArrayList<Product_marchents> list_details=new ArrayList<>();

    private List<product> prod = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.details_product, container, false);

        Bundle bundle = getArguments();
        String Pruduct_id = bundle.getString("ID");
        product p = (product) bundle.getSerializable("myobject");

//        load_Data load_data = new load_Data();
//        contactList_detail = load_data.return_contactList_details();
//        list_details = load_data.return_list_details();

        Product_marchents marchents = null;

            for (Product_marchents product_marchents : DB_marchents_list) {
                if (product_marchents.getId_ProductMerchant().contains(Pruduct_id)) {
                    marchents = product_marchents;
                    break;
                }

            }



//        for (product product : products) {
//            if (product.getProductid().contains(Pruduct_id)) {
//                p = product;
//                break;
//            }

       // }

        ImageView imageView = (ImageView) view.findViewById(R.id.image__View);
        TextView sku = view.findViewById(R.id.sku);
        TextView created = view.findViewById(R.id.created);
        TextView modified = view.findViewById(R.id.modified);
        TextView discribtion = view.findViewById(R.id.describtion);



        sku.setText("Sku: " + marchents.getProductMerchant_sku());
        created.setText("created in: " + marchents.getProductMerchant_created());
        modified.setText("modified: " + marchents.getProductMerchant_modified());
        discribtion.setText(p.getDescription());

//
//        Glide.with(getContext())
//                .load(p.getImage_url())
//                .into(imageView);
        Picasso.with(getContext()).load(p.getImage_url()).into(imageView);

        return view;


    }


}