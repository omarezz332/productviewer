package com.example.omaradel.productviewer;

import android.app.Activity;
import android.app.LoaderManager;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.example.omaradel.productviewer.Data.DatabaseHelper;
import com.example.omaradel.productviewer.Data.PetContract;

import java.util.ArrayList;

import static com.example.omaradel.productviewer.Data.PetContract.PetEntry.COLUMN_PET_CREATED;
import static com.example.omaradel.productviewer.Data.PetContract.PetEntry.COLUMN_PET_MODIFIED;
import static com.example.omaradel.productviewer.Data.PetContract.PetEntry.COLUMN_PET_NAME;
import static com.example.omaradel.productviewer.Data.PetContract.PetEntry.COLUMN_PET_SKU;
import static com.example.omaradel.productviewer.Data.PetContract.PetEntry.COLUMN_PET_description;
import static com.example.omaradel.productviewer.Data.PetContract.PetEntry.COLUMN_PET_image;
import static com.example.omaradel.productviewer.Data.PetContract.PetEntry.COLUMN_PET_price;
import static com.example.omaradel.productviewer.Data.PetContract.PetEntry.CONTENT_URI;
import static com.example.omaradel.productviewer.Data.PetContract.PetEntry._ID;
import static com.example.omaradel.productviewer.Data.PetContract.PetEntry.projection;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,LoaderManager.LoaderCallbacks<Cursor> {
    public static final String PRODUCT_ID = "PRODUCT_ID ";//a public ID to control productid from detailActivity.
    public CoordinatorLayout coordinatorLayout;
    private static String webUrl = "https://www.facebook.com/H-Sport-1388674971422183/";//facebook of h+ sport
    private static String email = "info@hplussport.com"; //the email adress of h+ sport to send to
    private ActionBarDrawerToggle toggle;
    private DrawerLayout drawerLayout;
    private String TAG = MainActivity.class.getSimpleName();
    // URL to get contacts JSON
    private static String url = "http://www.nweave.com/wp-content/uploads/2012/09/featured.txt";


    private ArrayList<product> list1_allproduct = new ArrayList<>();
    private ArrayList<Product_marchents> Details_list = new ArrayList<Product_marchents>();
    public static ArrayList<Product_marchents>  DB_marchents_list = new ArrayList<Product_marchents>();

    private ArrayList<product> DB_product_list = new ArrayList<>();

    //  RecyclerView recyclerView=findViewById(R.id.recycl_list) ;
    Fragment_allproduct fragment_allproduct = new Fragment_allproduct();

    public DatabaseHelper helper = new DatabaseHelper(this);
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout = (DrawerLayout) findViewById(R.id.drowerlayout);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView navigationView = findViewById(R.id.nav_viw);
        navigationView.setNavigationItemSelectedListener(this);
//        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_continer,
//                new Fragment_allproduct()).commit()

        navigationView.setCheckedItem(R.id.all_products);
        // new load_Data().execute();
//        load_Data load_data = new load_Data(this,recyclerView);
//        list2_mostcheap = load_data.sort_contactList2();
//        list3_mostexpensive = load_data.sort_contactList3();

        if(get_database().getCount()==0)
     new load_Data(this).execute();
        else
        check_database();


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (toggle.onOptionsItemSelected(item))
            return true;
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.all_products:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_continer,
                        Fragment_allproduct.getInstance(DB_product_list)).commit();
                // Fragment_allproduct.getInstance(list) == fragment frag = new Fragment(list);
                break;
            case R.id.Most_Cheapest_products:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_continer,
                        Fragment_mostcheap.getInstance(DB_product_list)).commit();
                break;
            case R.id.Most_Expensive_products:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_continer,
                        Fragment_mostexpensive.getInstance(DB_product_list)).commit();
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    public void fill_Recy1(final ArrayList<product> contactList1) {
        list1_allproduct = contactList1;




    }

    public void fill_Recy2(ArrayList<Product_marchents> contactList2) {
        Details_list = contactList2;
        check_database();

    }


//    public void fill_Recy3(ArrayList<product> contactList3) {
//        list3_mostexpensive = contactList3;
//    }

    private void check_database() {


        if (get_database().getCount()== 0)
            insert_products_DB();

       // get_data_fromDB();
        getLoaderManager().initLoader(1,null,this);

    }
    private void insert_products_DB() {
        ContentValues Values = new ContentValues();

        for (int i = 0; i < list1_allproduct.size(); i++) {

            Values.put(PetContract.PetEntry._ID, list1_allproduct.get(i).getProductid());
            Values.put(PetContract.PetEntry.COLUMN_PET_NAME, list1_allproduct.get(i).getName());
            Values.put(PetContract.PetEntry.COLUMN_PET_description, list1_allproduct.get(i).getDescription());
            Values.put(PetContract.PetEntry.COLUMN_PET_price, list1_allproduct.get(i).getPrice());
            Values.put(PetContract.PetEntry.COLUMN_PET_image, list1_allproduct.get(i).getImage_url());
            Values.put(PetContract.PetEntry._ID,Details_list.get(i).getId_ProductMerchant());
            Values.put(PetContract.PetEntry.COLUMN_PET_SKU,Details_list.get(i).getProductMerchant_sku());
            Values.put(PetContract.PetEntry.COLUMN_PET_CREATED,Details_list.get(i).getProductMerchant_created());
            Values.put(PetContract.PetEntry.COLUMN_PET_MODIFIED,Details_list.get(i).getProductMerchant_modified());


            Uri newUri = getContentResolver().insert(PetContract.PetEntry.CONTENT_URI, Values);
        }


    }

    private void get_data_fromDB() {
        Cursor cursor = get_database();
        if (cursor.moveToFirst()) {
            do {
                product product = new product();
                Product_marchents marchents=new Product_marchents();

                product.setProductid(cursor.getString(0));
                marchents.setId_ProductMerchant(cursor.getString(0));
                product.setName(cursor.getString(1));
                product.setDescription(cursor.getString(2));
                double price= Double.parseDouble(cursor.getString(3));
                product.setPrice(price);
                product.setImage_url(cursor.getString(4));
                DB_product_list.add(product);

                marchents.setProductMerchant_sku(cursor.getString(5));
                marchents.setProductMerchant_created(cursor.getString(6));
                marchents.setProductMerchant_modified(cursor.getString(7));
                DB_marchents_list.add(marchents);



            }
            while (cursor.moveToNext());
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_continer,
                    Fragment_allproduct.getInstance(DB_product_list)).commit();

        }

    }

    private Cursor get_database() {

        cursor = getContentResolver().query( CONTENT_URI
                , projection
                , null
                , null
                , null);
        return cursor;

    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        if(id==1)
        return new CursorLoader(this,CONTENT_URI,projection,null,null,null);
        else
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        get_data_fromDB();
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
