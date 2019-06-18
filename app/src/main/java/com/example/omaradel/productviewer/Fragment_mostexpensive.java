package com.example.omaradel.productviewer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


/**
 * Created by omar adel on 7/8/2018.
 */

public class Fragment_mostexpensive extends Fragment {

    private GridLayoutManager layoutManager;
    public  ArrayList<product> products;//object of the product list
    private MyAdabter adabter;
    RecyclerView recyclerView;

    @Nullable
    public static Fragment getInstance(ArrayList<product> list) {
        Fragment_mostexpensive mostexpensive = new Fragment_mostexpensive();
        mostexpensive.products = list;
        return mostexpensive;
    }


    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_mostexpensive, container, false);
//        ListView lv=view.findViewById(R.id.listview3);
//        productListAdabtor adabtor=new productListAdabtor(getActivity(),R.layout.list_item, (ArrayList<product>) products);
//        lv.setAdapter(adabtor);
        Collections.sort(products, new Comparator<product>() {
            @Override
            public int compare(product o1, product o2) {
                if (o1.getPrice() > o2.getPrice()) return -1;
                else if (o1.getPrice() == o2.getPrice()) return 0;
                else
                    return 1;
            }
        });


        recyclerView = view.findViewById(R.id.recycl_list3);
       // new load_Data(getActivity(),recyclerView).execute();
        layoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(layoutManager);
        fill_recy(products);
//        recyclerView.setAdapter(new MyAdabter(products,getActivity(), new MyAdabter.OnItemClickListener() {
//            @Override public void onItemClick(product item,int position) {
//                Toast.makeText(getContext(), "Item Clicked", Toast.LENGTH_LONG).show();
//                Details_product details_product=new Details_product();
//                product product=products.get(position);
//                Bundle bundle=new Bundle();
//                bundle.putString("ID",product.getProductid());
//                bundle.putSerializable("myobject",product);
//
//                details_product.setArguments(bundle);
//
//            }
//        }));
//        Details_product details_product=new Details_product();
//        FragmentManager fragmentManager=getFragmentManager();
//        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
//        fragmentTransaction.add(R.id.fragment_continer,details_product);
//        fragmentTransaction.addToBackStack("myscreen");
//        fragmentTransaction.commit();

        return view;
    }
    public  void fill_recy(final ArrayList<product> contactList1)
    {
        Details_product details_product = new Details_product();
        recyclerView.setAdapter(new MyAdabter(contactList1, getContext(), new MyAdabter.OnItemClickListener() {

            @Override
            public void onItemClick(product item, int posistion) {
                Toast.makeText(getContext(), "Item Clicked", Toast.LENGTH_LONG).show();
                product product = contactList1.get(posistion);
                Bundle bundle = new Bundle();
                bundle.putString("ID", product.getProductid());
                bundle.putSerializable("myobject", product);
                Details_product details_product = new Details_product();
                details_product.setArguments(bundle);


                FragmentManager fragmentManager=getFragmentManager();
                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                fragmentTransaction.add(R.id.fragment_continer,details_product);
                fragmentTransaction.addToBackStack("myscreen");
                fragmentTransaction.commit();

            }


        }));

    }
}
