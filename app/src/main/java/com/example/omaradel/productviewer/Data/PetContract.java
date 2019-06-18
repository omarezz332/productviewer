package com.example.omaradel.productviewer.Data;


import android.net.Uri;
import android.provider.BaseColumns;

/**
 * API Contract for the Pets app.
 */
public final class PetContract {
    public static final String CONTENT_AUTHORITY = "com.example.omaradel.productviewer";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String PATH_PETS = "pets1";

    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    private PetContract() {
    }

    /**
     * Inner class that defines constant values for the pets database table.
     * Each entry in the table represents a single pet.
     */
    public static final class PetEntry implements BaseColumns {

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_PETS);
        /**
         * Name of database table for pets
         */
        public final static String TABLE_NAME = "product";


        /**
         * Unique ID number for the pet (only for use in the database table).
         * <p>
         * Type: INTEGER
         */
        public final static String _ID = BaseColumns._ID;


        public final static String COLUMN_PET_NAME = "name_product";

        public final static String COLUMN_PET_description = "description_product";

        public final static String COLUMN_PET_price = "price_product";

        public final static String COLUMN_PET_image = "image_url_product";

        public final static String COLUMN_PET_SKU = "SKU";

        public final static String COLUMN_PET_CREATED = "CREATED";

        public final static String COLUMN_PET_MODIFIED = "MODIFIED";


    public static String[] projection = {_ID
                , COLUMN_PET_NAME
                , COLUMN_PET_description
                , COLUMN_PET_price
                , COLUMN_PET_image
                ,COLUMN_PET_SKU
                ,COLUMN_PET_CREATED
                ,COLUMN_PET_MODIFIED

        };

    }

}

