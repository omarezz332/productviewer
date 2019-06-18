package com.example.omaradel.productviewer;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.app.Activity;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Adapter;
import android.widget.Toast;

import com.example.omaradel.productviewer.Data.PetContract;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import retrofit2.Callback;
import retrofit2.Response;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.omaradel.productviewer.Data.DatabaseHelper;
import com.example.omaradel.productviewer.Data.PetContract;

import static android.support.constraint.Constraints.TAG;


//    public ArrayList<product> loadJSON()
//    {
//        Service service=Client.getClient();
//        retrofit2.Call<JsonArray> jsonArrayCall=service.readProductArray();
//        jsonArrayCall.enqueue(new Callback<JsonArray>() {
//
//
//            @Override
//            public void onResponse(retrofit2.Call<JsonArray> call, Response<JsonArray> response) {
//                String productsting = response.body().toString();
//                if (productsting != null) {
//                    try {
//                        JSONArray A = new JSONArray(productsting);
//                        for (int i = 0; i < A.length(); i++) {
//                            JSONObject c = A.getJSONObject(i);
//
//                            JSONObject Product = c.getJSONObject("Product");
//                            String id_product = Product.getString("id");
//                            String name_product = Product.getString("name");
//                            String description_product = Product.getString("description");
//                            double price_product = Product.getDouble("price");
//                            String unit_price_product = Product.getString("unit_price");
//                            String product_type_id = Product.getString("product_type_id");
//                            String image_url_product = Product.getString("image_url");
//                            String shopping_list_item_id = Product.getString("shopping_list_item_id");
//                            String shopping_cart_item_id = Product.getString("shopping_cart_item_id");
//                            product p = new product(id_product, name_product, description_product, price_product, unit_price_product, product_type_id, image_url_product, shopping_list_item_id, shopping_cart_item_id);
//                            contactList1.add(p);
//                            contactList2.add(p);
//                            contactList3.add(p);
//
//
//
//
//                            JSONArray ProductMerchants = c.getJSONArray("ProductMerchants");
//                            for (int j = 0; j < ProductMerchants.length(); j++) {
//                                JSONObject d = ProductMerchants.getJSONObject(j);
//
//                                JSONObject Merchant = d.getJSONObject("Merchant");
//                                String id_Merchant = Product.getString("id");
//                                String name_Merchant = Product.getString("name");
//
//                                JSONObject MerchantProduct = d.getJSONObject("MerchantProduct");
//                                String id_MerchantProduct = MerchantProduct.getString("id");
//                                String price_MerchantProduct = MerchantProduct.getString("price");
//                                String upc_MerchantProduct = MerchantProduct.getString("upc");
//                                String sku_MerchantProduct = MerchantProduct.getString("sku");
//                                String buy_url_MerchantProduct = MerchantProduct.getString("buy_url");
//                                String discount_percent_MerchantProduct = MerchantProduct.getString("discount_percent");
//                                String unit_price_MerchantProduct = MerchantProduct.getString("unit_price");
//
//                                JSONObject ProductMerchant = d.getJSONObject("ProductMerchant");
//                                String ProductMerchant_id = ProductMerchant.getString("id");
//                                String id_ProductMerchant = ProductMerchant.getString("product_id");
//                                String ProductMerchant_upc = ProductMerchant.getString("upc");
//                                String ProductMerchant_sku = ProductMerchant.getString("sku");
//                                String ProductMerchant_created = ProductMerchant.getString("created");
//                                String ProductMerchant_modified = ProductMerchant.getString("modified");
//                                String multiple_products_per_page = ProductMerchant.getString("multiple_products_per_page");
//                            }
//
//                        }
//
//                    } catch (final JSONException e) {
//                       Log.e(TAG, "Json parsing error: " + e.getMessage());
////                        runOnUiThread(new Runnable() {
////                            @Override
////                            public void run() {Runnable
////                              Toast.makeText(getApplicationContext(),
////                                        "Json parsing error: " + e.getMessage(),
////                                        Toast.LENGTH_LONG)
////                                        .show();
//                            }
////                        });
//
//                    }
//
//                    Collections.sort(contactList2, new Comparator<product>() {
//                        @Override
//                        public int compare(product o1, product o2) {
//                            if (o1.getPrice() < o2.getPrice()) return -1;
//                            else if (o1.getPrice() == o2.getPrice()) return 0;
//                            else
//                            return 1;
//                        }
//                    });
//                Collections.sort(contactList3, new Comparator<product>() {
//                    @Override
//                    public int compare(product o1, product o2) {
//                        if (o1.getPrice() > o2.getPrice()) return -1;
//                        else if (o1.getPrice() == o2.getPrice()) return 0;
//                        else
//                            return 1;
//                    }
//                });
//
//                }
//
//
//            @Override
//            public void onFailure(retrofit2.Call<JsonArray> call, Throwable t) {
//
//            }
//
//
//        });
//
//        return contactList1;
//    }
//}
public class load_Data extends AsyncTask<Void, Void, Void> {
    private ProgressDialog pDialog;
    private ArrayList<product> contactList1 = new ArrayList<>();

    public static ArrayList<Product_marchents> list_details = new ArrayList<>();
    public static ArrayList<ArrayList<Product_marchents>> contactList_details = new ArrayList<>();
    public static FragmentManager fragmentManager;

    MainActivity mContex;
    RecyclerView recyclerView;
    public Adapter adapter;

    public load_Data(MainActivity mainActivity) {
        this.mContex = mainActivity;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        // Showing progress dialog
        pDialog = new ProgressDialog(mContex);
        pDialog.setMessage("Downloading file. Please wait...");
        pDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pDialog.setTitle("we have all ...");
        pDialog.setCancelable(false);
        pDialog.show();

    }


    @Override
    protected Void doInBackground(Void... voids) {

        Service service = Client.getClient();
        retrofit2.Call<JsonArray> jsonArrayCall = service.readProductArray();
        jsonArrayCall.enqueue(new Callback<JsonArray>() {

            @Override
            public void onResponse(retrofit2.Call<JsonArray> call, Response<JsonArray> response) {
                String productsting = response.body().toString();
                if (productsting != null) {
                    try {
                        JSONArray A = new JSONArray(productsting);
                        for (int i = 0; i < A.length(); i++) {
                            JSONObject c = A.getJSONObject(i);

                            JSONObject Product = c.getJSONObject("Product");
                            String id_product = Product.getString("id");
                            String name_product = Product.getString("name");
                            String description_product = Product.getString("description");
                            double price_product = Product.getDouble("price");
                            String unit_price_product = Product.getString("unit_price");
                            String product_type_id = Product.getString("product_type_id");
                            String image_url_product = Product.getString("image_url");
                            String shopping_list_item_id = Product.getString("shopping_list_item_id");
                            String shopping_cart_item_id = Product.getString("shopping_cart_item_id");

                            product p = new product(id_product, name_product, description_product, price_product, unit_price_product, product_type_id, image_url_product, shopping_list_item_id, shopping_cart_item_id);

                            contactList1.add(p);






                            JSONArray ProductMerchants = c.getJSONArray("ProductMerchants");
                            for (int j = 0; j < 1; j++) {
                                JSONObject d = ProductMerchants.getJSONObject(j);

                                JSONObject Merchant = d.getJSONObject("Merchant");
                                String id_Merchant = Product.getString("id");
                                String name_Merchant = Product.getString("name");

                                JSONObject MerchantProduct = d.getJSONObject("MerchantProduct");
                                String id_MerchantProduct = MerchantProduct.getString("id");
                                String price_MerchantProduct = MerchantProduct.getString("price");
                                String upc_MerchantProduct = MerchantProduct.getString("upc");
                                String sku_MerchantProduct = MerchantProduct.getString("sku");
                                String buy_url_MerchantProduct = MerchantProduct.getString("buy_url");
                                String discount_percent_MerchantProduct = MerchantProduct.getString("discount_percent");
                                String unit_price_MerchantProduct = MerchantProduct.getString("unit_price");

                                JSONObject ProductMerchant = d.getJSONObject("ProductMerchant");
                                String ProductMerchant_id = ProductMerchant.getString("id");
                                String id_ProductMerchant = ProductMerchant.getString("product_id");
                                String ProductMerchant_upc = ProductMerchant.getString("upc");
                                String ProductMerchant_sku = ProductMerchant.getString("sku");
                                String ProductMerchant_created = ProductMerchant.getString("created");
                                String ProductMerchant_modified = ProductMerchant.getString("modified");
                                String multiple_products_per_page = ProductMerchant.getString("multiple_products_per_page");


                                Product_marchents pro_march = new Product_marchents(ProductMerchant_id, id_ProductMerchant, ProductMerchant_upc, ProductMerchant_sku, ProductMerchant_created, ProductMerchant_modified, multiple_products_per_page);
                                list_details.add(j, pro_march);
                            }



                        }


                        mContex.runOnUiThread(new Runnable() {
                            public void run() {
                                Log.d("UI thread", "I am the UI thread");
                                mContex.fill_Recy1(contactList1);
                                mContex.fill_Recy2(list_details);


                            }
                        });
                        if (pDialog.isShowing())
                            pDialog.dismiss();

                    } catch (final JSONException e) {
                        Log.e(TAG, "Json parsing error: " + e.getMessage());
//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {Runnable
//                              Toast.makeText(getApplicationContext(),
//                                        "Json parsing error: " + e.getMessage(),
//                                        Toast.LENGTH_LONG)
//                                        .show();
                    }
//                       });

                }


            }


            @Override
            public void onFailure(retrofit2.Call<JsonArray> call, Throwable t) {

            }


        });


        return null;
    }


    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);

//        mContex.fill_recy(contactList1);


    }
}
