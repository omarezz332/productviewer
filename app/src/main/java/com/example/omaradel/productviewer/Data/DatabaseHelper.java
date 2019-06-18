package com.example.omaradel.productviewer.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.example.omaradel.productviewer.Data.PetContract.PetEntry.TABLE_NAME;

    public class  DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "product_viewer.db";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);


    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + "(_id INTEGER PRIMARY KEY " +
                ",name_product TEXT,description_product TEXT,price_product TEXT,image_url_product TEXT,SKU TEXT ,CREATED TEXT,MODIFIED TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }

//    public long insertData(String name, String breed, int Gender , int weight) {
//        SQLiteDatabase db = this.getReadableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(COLUMN_PET_NAME, name);
//        contentValues.put(COLUMN_PET_BREED, breed);
//        contentValues.put(COLUMN_PET_GENDER, Gender);
//        contentValues.put(COLUMN_PET_WEIGHT, weight);
//        long result = db.insert(TABLE_NAME,null,contentValues);
//        return result;
//
//
//    }

//    public Cursor getAlldata()
//    {
//        SQLiteDatabase db = getReadableDatabase();
//        Cursor cur=db.rawQuery("SELECT * FROM "+ TABLE_NAME,null);
//        return cur;
//
//    }

//    public boolean ubdateData(String id, String name, String surname, String marks)
//    {
//        SQLiteDatabase db = this.getReadableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(COL_1, id);
//        contentValues.put(COL_2, name);
//        contentValues.put(COL_3, surname);
//        contentValues.put(COL_4, marks);
//        db.update(TABLE_NAME,contentValues,"id = ?",new String[]{id});
//        if(id.length()==0||name.length()==0||surname.length()==0||marks.length()==0)
//            return false;
//        else
//        return true;
//    }
//    public int DeleteData(String id)
//    {
//        SQLiteDatabase db = this.getReadableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(COL_1, id);
//       return db.delete(TABLE_NAME,"ID = ?",new String[]{id});
//    }
}