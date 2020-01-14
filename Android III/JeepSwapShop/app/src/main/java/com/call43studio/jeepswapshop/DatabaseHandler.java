//Database Handler Class
//Creates the Database, Tables and Columns and has Methods that will be later applied to test the data.
//This is the essential piece of the App.

package com.call43studio.jeepswapshop;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {

    //All Static Variable Declarations
    //DB Versions
    private static final int DATABASE_VERSION = 2;

    //DB Name
    private static final String DATABASE_NAME = "Jeep Swap Shop";

    //Begin Declarations and Methods for both Tables and Respective Columns
    //Table Name and Declaration

    //Table Jeep
    private static final String TABLE_JEEP = "jeep";

    //Table Parts
    private static final String TABLE_PARTS = "parts";

    //Table Column Declarations
    private static final String COL_ID = "id";
    private static final String COL_TITLE = "title";
    private static final String COL_MAKE = "make";
    private static final String COL_MODEL = "model";
    private static final String COL_MILEAGE = "mileage";
    private static final String COL_PRICE = "price";
    private static final String COL_EMAIL = "email";
    private static final String COL_PHONE = "phone";
    private static final String COL_STREET = "street";
    private static final String COL_CITY = "city";
    private static final String COL_STATE = "state";
    private static final String COL_ZIP = "zip";
    private static final String COL_YEAR = "year"; //From Jeep Table
    private static final String COL_PARTAMOUNT = "pAmount"; //From Parts Table
    //End Table  and Column Declarations


    //Constructor for DB Handler
    public DatabaseHandler(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

   //onCreate for DB
    @Override
    public void onCreate(SQLiteDatabase db) {
            System.out.println("SQLite onCreate called");


            //CREATE TABLE NAME OF TABLES COLUMN 1 Type, Column 2 Type
        String CREATE_JEEP_TABLE = "CREATE TABLE " + TABLE_JEEP + "("
                + COL_ID + " INTEGER PRIMARY KEY,"
                + COL_TITLE + " TEXT,"
                + COL_MAKE + " TEXT,"
                + COL_MODEL + " TEXT,"
                + COL_MILEAGE + " TEXT,"
                + COL_PRICE + " TEXT,"
                + COL_EMAIL + " TEXT,"
                + COL_PHONE + " TEXT,"
                + COL_STREET + " TEXT,"
                + COL_CITY + " TEXT,"
                + COL_STATE + " TEXT,"
                + COL_ZIP + " TEXT,"
                + COL_YEAR + " TEXT"
                + ")";

        //Execute a single SQL statement that is NOT a SELECT or any other SQL statement that returns data.
        db.execSQL(CREATE_JEEP_TABLE);

        // Lab9
        // declare string with SQL command to create the business table
        String CREATE_PARTS_TABLE = "CREATE TABLE " + TABLE_PARTS + "("
                + COL_ID + " INTEGER PRIMARY KEY,"
                + COL_TITLE + " TEXT,"
                + COL_MAKE + " TEXT,"
                + COL_MODEL + " TEXT,"
                + COL_MILEAGE + " TEXT,"
                + COL_PRICE + " TEXT,"
                + COL_EMAIL + " TEXT,"
                + COL_PHONE + " TEXT,"
                + COL_STREET + " TEXT,"
                + COL_CITY + " TEXT,"
                + COL_STATE + " TEXT,"
                + COL_ZIP + " TEXT,"
                + COL_PARTAMOUNT + " TEXT"
                + ")";

        // Execute a single SQL statement that is NOT a SELECT or any other SQL statement that returns data.
        db.execSQL(CREATE_PARTS_TABLE);
    }



    //Begin Methods for JEEP TABLE
    //Adds a Jeep to the DB
    public void addListingJeep(ListingJeep listing)
    {
        // get the database from the SQLiteHelper
        SQLiteDatabase db = this.getWritableDatabase();

        // ContentValues is used to store a set of key/values that the ContentResolver can process.
        // These values will be inserted into the matching columns
        ContentValues values = new ContentValues();
        values.put(COL_TITLE, listing.getTitle());                    // Title
        values.put(COL_MAKE, listing.getMake());                      // Make
        values.put(COL_MODEL, listing.getModel());                    // Model
        values.put(COL_MILEAGE, listing.getMileage());                // Mileage
        values.put(COL_PRICE, listing.getPrice());                    // Price
        values.put(COL_EMAIL, listing.getEmail());                    // Email
        values.put(COL_PHONE, listing.getPhone());                    // Phone
        values.put(COL_STREET, listing.getAddress().getStreet());     // Street
        values.put(COL_STATE, listing.getAddress().getState());       // State
        values.put(COL_CITY, listing.getAddress().getCity());         // City
        values.put(COL_ZIP, listing.getAddress().getZip());           // Zip
        values.put(COL_YEAR, listing.getYear());

        // Inserting Row
        // SQLite Syntax
        // INSERT INTO TableName(ColumnValue, ColumnValue)

        // Use the SQLite insert method
        // first arg is table name
        // second arg If you specify null, like in this code sample,
        // the framework does not insert a row when there are no values.
        db.insert(TABLE_JEEP, null, values);
        db.close(); // Closing database connection
    }

    //Gets ListingJeep entry from Jeep Table and returns as a Jeep Object
    public ListingJeep getListingJeepByName(String someTitle)
    {
        // create reference to ContactFriend for return
        ListingJeep listing = null;

        try
        {
            // get the database from the SQLiteHelper
            SQLiteDatabase db = this.getReadableDatabase();

            // SQLite query command
            // query(String table, String[] columns, String selection, String[] selectionArgs,
            //          String groupBy, String having, String orderBy, String limit)
            Cursor cursor = db.query(TABLE_JEEP, new String[]{COL_ID,
                            COL_TITLE, COL_MAKE, COL_MODEL, COL_MILEAGE, COL_PRICE, COL_EMAIL, COL_PHONE, COL_STREET, COL_CITY, COL_STATE, COL_ZIP, COL_YEAR}, COL_TITLE + "=?",
                    new String[]{someTitle}, null, null, null, null);

            // if the cursor is not null and count is > 0, move to the first position.
            if (cursor != null && cursor.getCount() > 0)
            {
                cursor.moveToFirst();

                // create Jeep Listing object from cursor
                listing = new ListingJeep(cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4),
                        cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getString(8),
                        cursor.getString(9), cursor.getString(10), cursor.getString(11), cursor.getString(12),
                        ListingJeep.ListingType.JEEP, cursor.getString(13));
            } else
            {
                System.out.println("getContactFriendByName cursor is null");
                listing = null;
            }

            // close the cursor
            cursor.close();

            // close the database
            db.close();

            // return contact
            return listing;
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            // return contact
            return listing;
        }

    }

    //Delete a Listing by Name / Title in the DB
    public void deleteListingJeepByName(String someTitle)
    {
        // get a writeable database
        SQLiteDatabase db = this.getWritableDatabase();

        // use SQLite convenience method to delete
        // delete(String table, String whereClause, String[] whereArgs)
        db.delete(TABLE_JEEP, COL_TITLE + " = ?",
                new String[] { someTitle });

        db.close(); // Close database connection
    }

    // Gets all of the Jeep Listings from the Jeep Table
    public Cursor getAllListingJeep()
    {

        //SelectQuery SELECT FROM TABLE_JEEP ORDER BY Title of Jeep
        String selectQuery = "SELECT *" +
                " FROM " + TABLE_JEEP + " ORDER BY " + COL_TITLE;

        // get the database from the SQLiteHelper
        SQLiteDatabase db = this.getReadableDatabase();

        // execute a raw SQLite query
        Cursor cursor = db.rawQuery(selectQuery, null);

        // return the Cursor
        return cursor;
    }

    //Deletes ALL OF THE DATA ENTIRES IN THE JEEP TABLE
    public void deleteAllListingJeep()
    {
        // get the datatbase
        SQLiteDatabase db = this.getWritableDatabase();

        // delete the friend contacts and close
        // 2nd arg is where clause
        // 3rd arg is where values
        db.delete(TABLE_JEEP, null, null);
        db.close(); // Closing database connection
    }

    //End Jeep Table's Methods


    //Begin Parts Table's Methods
    //Adds a Part to the DB
    public void addListingParts(ListingParts listing)
    {
        // get the database from the SQLiteHelper
        SQLiteDatabase db = this.getWritableDatabase();

        // ContentValues is used to store a set of key/values that the ContentResolver can process.
        // These values will be inserted into the matching columns
        ContentValues values = new ContentValues();
        values.put(COL_TITLE, listing.getTitle());                    // Title
        values.put(COL_MAKE, listing.getMake());                      // Make
        values.put(COL_MODEL, listing.getModel());                    // Model
        values.put(COL_MILEAGE, listing.getMileage());                // Mileage
        values.put(COL_PRICE, listing.getPrice());                    // Price
        values.put(COL_EMAIL, listing.getEmail());                    // Email
        values.put(COL_PHONE, listing.getPhone());                    // Phone
        values.put(COL_STREET, listing.getAddress().getStreet());     // Street
        values.put(COL_STATE, listing.getAddress().getState());       // State
        values.put(COL_CITY, listing.getAddress().getCity());         // City
        values.put(COL_ZIP, listing.getAddress().getZip());           // Zip
        values.put(COL_YEAR, listing.getParts());

        // Inserting Row
        // SQLite Syntax
        // INSERT INTO TableName(ColumnValue, ColumnValue)

        // Use the SQLite insert method
        // first arg is table name
        // second arg If you specify null, like in this code sample,
        // the framework does not insert a row when there are no values.
        db.insert(TABLE_PARTS, null, values);
        db.close(); // Closing database connection
    }

    //Public Parts Listing
    public ListingParts getListingPartsByName(String someTitle)
    {
        // create reference to ContactFriend for return
        ListingParts listing = null;

        try
        {
            // get the database from the SQLiteHelper
            SQLiteDatabase db = this.getReadableDatabase();

            // SQLite query command
            // query(String table, String[] columns, String selection, String[] selectionArgs,
            //          String groupBy, String having, String orderBy, String limit)
            Cursor cursor = db.query(TABLE_JEEP, new String[]{COL_ID,
                            COL_TITLE, COL_MAKE, COL_MODEL, COL_MILEAGE, COL_PRICE, COL_EMAIL, COL_PHONE, COL_STREET, COL_CITY, COL_STATE, COL_ZIP, COL_PARTAMOUNT}, COL_TITLE + "=?",
                    new String[]{someTitle}, null, null, null, null);

            // if the cursor is not null and count is > 0, move to the first position.
            if (cursor != null && cursor.getCount() > 0)
            {
                cursor.moveToFirst();

                // create Jeep Listing object from cursor
                listing = new ListingParts(cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4),
                        cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getString(8),
                        cursor.getString(9), cursor.getString(10), cursor.getString(11), cursor.getString(12),
                        ListingJeep.ListingType.PARTS, cursor.getInt(13));
            } else
            {
                System.out.println("Listing Parts is Null");
                listing = null;
            }

            // close the cursor
            cursor.close();

            // close the database
            db.close();

            // return contact
            return listing;
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            // return contact
            return listing;
        }

    }

    //Delete a Listing by Name / Title in the DB
    public void deleteListingPartsByName(String someTitle)
    {
        // get a writeable database
        SQLiteDatabase db = this.getWritableDatabase();

        // use SQLite convenience method to delete
        // delete(String table, String whereClause, String[] whereArgs)
        db.delete(TABLE_PARTS, COL_TITLE + " = ?",
                new String[] { someTitle });

        db.close(); // Close database connection
    }

    // Gets all of the Part Listings from the Jeep Table
    public Cursor getAllListingParts()
    {

        //SelectQuery SELECT FROM TABLE_PARTS ORDER BY Title of Part
        String selectQuery = "SELECT *" +
                " FROM " + TABLE_PARTS + " ORDER BY " + COL_TITLE;

        // get the database from the SQLiteHelper
        SQLiteDatabase db = this.getReadableDatabase();

        // execute a raw SQLite query
        Cursor cursor = db.rawQuery(selectQuery, null);

        // return the Cursor
        return cursor;
    }

    //Deletes ALL OF THE DATA ENTIRES IN THE PARTS TABLE
    public void deleteAllListingParts()
    {
        // get the datatbase
        SQLiteDatabase db = this.getWritableDatabase();

        // delete the friend contacts and close
        // 2nd arg is where clause
        // 3rd arg is where values
        db.delete(TABLE_PARTS, null, null);
        db.close(); // Closing database connection
    }



    //End Parts Table's Methods

    // Called when the database needs to be upgraded.
    // The implementation should use this method to drop tables, add tables,
    // or do anything else it needs to upgrade to the new schema version.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        System.out.println("SQLite onUpgrade called");

        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_JEEP);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PARTS);

        // Create tables again
        onCreate(db);
    }

    // Called when the database needs to be downgraded.
    // This is strictly similar to onUpgrade(SQLiteDatabase, int, int) method,
    // but is called whenever current version is newer than requested one.
    // However, this method is not abstract, so it is not mandatory for a customer to implement it.
    // If not overridden, default implementation will reject downgrade and throws SQLiteException
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        System.out.println("SQLite onDowngrade called");

        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_JEEP);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PARTS);

        // Create tables again
        onCreate(db);
    }
}


