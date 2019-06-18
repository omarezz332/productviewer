package com.example.omaradel.productviewer;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

//import static com.example.omaradel.productviewer.load_Data.contactList1;

/**
 * Created by omar adel on 7/8/2018.
 */

public class Fragment_allproduct extends Fragment {
    private GridLayoutManager layoutManager;

    private MyAdabter adabter;
    private ArrayList<product> products;//object of the product list
    RecyclerView recyclerView;

    //   public static ArrayList<product> contactList = new ArrayList<>();


    public static Fragment_allproduct getInstance(ArrayList<product> list) {
        Fragment_allproduct prod = new Fragment_allproduct();
        prod.products = list;
        return prod;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_allproduct, container, false);


        recyclerView = view.findViewById(R.id.recycl_list);

        layoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(layoutManager);
        fill_recy(products);

//        recyclerView.setAdapter(new MyAdabter(products,getActivity(), new MyAdabter.OnItemClickListener() {
//
//            @Override public void onItemClick(product item,int posistion) {
//                Toast.makeText(getContext(), "Item Clicked", Toast.LENGTH_LONG).show();
//                product product=products.get(posistion);
//                Bundle bundle=new Bundle();
//                bundle.putString("ID",product.getProductid());
//                bundle.putSerializable("myobject",product);
//
//                Details_product details_product=new Details_product();
//                details_product.setArguments(bundle);
//
//            }
//        }));


  productListAdabtor adabtor = new productListAdabtor(getActivity(), R.layout.list_item, (ArrayList<product>) products);
//           lv.setAdapter(adabtor);

        return view;

    }

    public void fill_recy(final ArrayList<product> contactList1) {

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

//                RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
//                itemAnimator.setAddDuration(1000);
//                itemAnimator.setRemoveDuration(1000);
//                recyclerView.setItemAnimator(itemAnimator);


                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.add(R.id.fragment_continer, details_product);
                fragmentTransaction.addToBackStack("myscreen");
                fragmentTransaction.commit();

            }


        }));

    }


}
