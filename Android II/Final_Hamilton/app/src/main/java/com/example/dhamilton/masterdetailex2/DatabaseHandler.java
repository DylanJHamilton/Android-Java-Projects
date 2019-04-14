//Project :     Final_Hamilton
// Author:      Dylan J. Hamilton
// Date:        12/9/2018
// File:        DtabaseHandler.java
// Description: This class handles all database activity
// Updates:     10/24/18
//              Add friend table
// 11/8/18 replace the arrayList with a SQLite cursor. Add an add dialog.

package com.example.jkozlevcar.masterdetailex2;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper
{
    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 2;

    // Database Name
    private static final String DATABASE_NAME = "contacts";

    // table names
    private static final String TABLE_BUSINESS = "business";

    // course Table columns names
    private static final String COL_ID = "_id";
    private static final String COL_NAME = "name";
    private static final String COL_EMAIL = "email";
    private static final String COL_PHONE = "phone";
    private static final String COL_STREET = "street";
    private static final String COL_CITY = "city";
    private static final String COL_STATE = "state";
    private static final String COL_ZIP = "zip";
    private static final String COL_COMPANY = "company";

    // Lab9
    private static final String TABLE_FRIEND = "friend";
    private static final String COL_YEAR_MET = "yearmet";

    // constructor
    public DatabaseHandler(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        System.out.println("SQLite onCreate called");

        // SQLite Create syntax
        //CREATE TABLE NameOfTable(Column1 Type, Column2 Type);

        // declare string with SQL command to create the business table
        String CREATE_BUSINESS_TABLE = "CREATE TABLE " + TABLE_BUSINESS + "("
                + COL_ID + " INTEGER PRIMARY KEY,"
                + COL_NAME + " TEXT,"
                + COL_EMAIL + " TEXT,"
                + COL_PHONE + " TEXT,"
                + COL_STREET + " TEXT,"
                + COL_CITY + " TEXT,"
                + COL_STATE + " TEXT,"
                + COL_ZIP + " TEXT,"
                + COL_COMPANY + " TEXT"
                + ")";

        //Execute a single SQL statement that is NOT a SELECT or any other SQL statement that returns data.
        db.execSQL(CREATE_BUSINESS_TABLE);

        // Lab9
        // declare string with SQL command to create the business table
        String CREATE_FRIEND_TABLE = "CREATE TABLE " + TABLE_FRIEND + "("
                + COL_ID + " INTEGER PRIMARY KEY,"
                + COL_NAME + " TEXT,"
                + COL_EMAIL + " TEXT,"
                + COL_PHONE + " TEXT,"
                + COL_STREET + " TEXT,"
                + COL_CITY + " TEXT,"
                + COL_STATE + " TEXT,"
                + COL_ZIP + " TEXT,"
                + COL_YEAR_MET + " INTEGER"
                + ")";

        // Execute a single SQL statement that is NOT a SELECT or any other SQL statement that returns data.
        db.execSQL(CREATE_FRIEND_TABLE);
    }

    // Lab9
    // add a friend contact to the friend table
    public void addContactFriend(ContactFriend contact)
    {
        // get the database from the SQLiteHelper
        SQLiteDatabase db = this.getWritableDatabase();

        // ContentValues is used to store a set of key/values that the ContentResolver can process.
        // These values will be inserted into the matching columns
        ContentValues values = new ContentValues();
        values.put(COL_NAME, contact.getName());                    // contact name
        values.put(COL_EMAIL, contact.getEmail());                  // contact email
        values.put(COL_PHONE, contact.getPhone());                  // contact phone
        values.put(COL_STREET, contact.getAddress().getStreet());   // contact street
        values.put(COL_STATE, contact.getAddress().getState());     // contact state
        values.put(COL_CITY, contact.getAddress().getCity());       // contact city
        values.put(COL_ZIP, contact.getAddress().getZip());         // contact zip
        values.put(COL_YEAR_MET, contact.getYearMet());              // contact year met

        // Inserting Row
        // SQLite Syntax
        // INSERT INTO TableName(ColumnValue, ColumnValue)

        // Use the SQLite insert method
        // first arg is table name
        // second arg If you specify null, like in this code sample,
        // the framework does not insert a row when there are no values.
        db.insert(TABLE_FRIEND, null, values);
        db.close(); // Closing database connection
    }

    // get ContactFriend entry from friend table and return as ContactFriend object
    public ContactFriend getContactFriendByName(String someName)
    {
        // create reference to ContactFriend for return
        ContactFriend contact = null;

        try
        {
            // get the database from the SQLiteHelper
            SQLiteDatabase db = this.getReadableDatabase();

            // SQLite query command
            // query(String table, String[] columns, String selection, String[] selectionArgs,
            //          String groupBy, String having, String orderBy, String limit)
            Cursor cursor = db.query(TABLE_FRIEND, new String[]{COL_ID,
                            COL_NAME, COL_EMAIL, COL_PHONE, COL_STREET, COL_CITY, COL_STATE, COL_ZIP, COL_YEAR_MET}, COL_NAME + "=?",
                    new String[]{someName}, null, null, null, null);

            // if the cursor is not null and count is > 0, move to the first position.
            if (cursor != null && cursor.getCount() > 0)
            {
                cursor.moveToFirst();

                // create ContactFriend object from cursor
                contact = new ContactFriend(cursor.getString(1), cursor.getString(2),
                        cursor.getString(3), cursor.getString(4), cursor.getString(5),
                        cursor.getString(6), cursor.getString(7), ContactBusiness.ContactType.FRIEND, cursor.getInt(8));
            } else
            {
                System.out.println("getContactFriendByName cursor is null");
                contact = null;
            }

            // close the cursor
            cursor.close();

            // close the database
            db.close();

            // return contact
            return contact;
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            // return contact
            return contact;
        }

    }

    // delete a ContacFriend by name from the friend table
    public void deleteContactFriendByName(String someName)
    {
        // get a writeable database
        SQLiteDatabase db = this.getWritableDatabase();

        // use SQLite convenience method to delete
        // delete(String table, String whereClause, String[] whereArgs)
        db.delete(TABLE_FRIEND, COL_NAME + " = ?",
                new String[] { someName });

        db.close(); // Close database connection
    }

    // get all ContactFriends from the friend table
    public Cursor getAllContactFriend()
    {
//        String selectQuery = "SELECT " + COL_ID + ", " + COL_NAME + ", " + COL_EMAIL + ", " +
//                COL_PHONE + ", " + COL_STREET + ", " + COL_STATE + ", " + COL_ZIP + ", " + COL_YEAR_MET +

        String selectQuery = "SELECT *" +
                " FROM " + TABLE_FRIEND + " ORDER BY " + COL_NAME;

        // get the database from the SQLiteHelper
        SQLiteDatabase db = this.getReadableDatabase();

        // execute a raw SQLite query
        Cursor cursor = db.rawQuery(selectQuery, null);

        // return the Cursor
        return cursor;
    }

    // delete all the friend contact entries in the friend table
    public void deleteAllContactFriend()
    {
        // get the datatbase
        SQLiteDatabase db = this.getWritableDatabase();

        // delete the friend contacts and close
        // 2nd arg is where clause
        // 3rd arg is where values
        db.delete(TABLE_FRIEND, null, null);
        db.close(); // Closing database connection
    }

    // add a business contact to the business table
    public void addContactBusiness(ContactBusiness contact)
    {
        // get the database from the SQLiteHelper
        SQLiteDatabase db = this.getWritableDatabase();

        // ContentValues is used to store a set of key/values that the ContentResolver can process.
        // These values will be inserted into the matching columns
        ContentValues values = new ContentValues();
        values.put(COL_NAME, contact.getName());                    // contact name
        values.put(COL_EMAIL, contact.getEmail());                  // contact email
        values.put(COL_PHONE, contact.getPhone());                  // contact phone
        values.put(COL_STREET, contact.getAddress().getStreet());   // contact street
        values.put(COL_STATE, contact.getAddress().getState());     // contact state
        values.put(COL_CITY, contact.getAddress().getCity());       // contact city
        values.put(COL_ZIP, contact.getAddress().getZip());         // contact zip
        values.put(COL_COMPANY, contact.getCompany());              // contact company

        // Inserting Row
        // SQLite Syntax
        // INSERT INTO TableName(ColumnValue, ColumnValue)

        // Use the SQLite insert method
        // first arg is table name
        // second arg If you specify null, like in this code sample,
        // the framework does not insert a row when there are no values.
        db.insert(TABLE_BUSINESS, null, values);
        db.close(); // Closing database connection
    }

    // get ContactBusiness entry from business table and return as ContactBusiness object
    public ContactBusiness getContactBusinessByName(String someName)
    {
        // create reference to ContactBusiness for return
        ContactBusiness contact = null;

        try
        {
            // get the database from the SQLiteHelper
            SQLiteDatabase db = this.getReadableDatabase();

            // SQLite query command
            // query(String table, String[] columns, String selection, String[] selectionArgs,
            //          String groupBy, String having, String orderBy, String limit)
            Cursor cursor = db.query(TABLE_BUSINESS, new String[]{COL_ID,
                            COL_NAME, COL_EMAIL, COL_PHONE, COL_STREET, COL_CITY, COL_STATE, COL_ZIP, COL_COMPANY}, COL_NAME + "=?",
                    new String[]{someName}, null, null, null, null);

            // if the cursor is not null and count is > 0, move to the first position.
            if (cursor != null && cursor.getCount() > 0)
            {
                cursor.moveToFirst();

                // create ContactBusiness object from cursor
                contact = new ContactBusiness(cursor.getString(1), cursor.getString(2),
                        cursor.getString(3), cursor.getString(4), cursor.getString(5),
                        cursor.getString(6), cursor.getString(7), ContactBusiness.ContactType.BUSINESS, cursor.getString(8));
            } else
            {
                System.out.println("getContactBusinessByName cursor is null");
                contact = null;
            }

            // close the cursor
            cursor.close();

            // close the database
            db.close();

            // return contact
            return contact;
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            // return contact
            return contact;
        }

    }

    // delete a ContacBusiness by name from the business table
    public void deleteContactBusinessByName(String someName)
    {
        // get a writeable database
        SQLiteDatabase db = this.getWritableDatabase();

        // use SQLite convenience method to delete
        // delete(String table, String whereClause, String[] whereArgs)
        db.delete(TABLE_BUSINESS, COL_NAME + " = ?",
                new String[] { someName });

        db.close(); // Close database connection
    }

    // get all ContactBusiness from the business table
    public Cursor getAllContactBusiness()
    {
//        String selectQuery = "SELECT " + COL_ID + ", " + COL_NAME + ", " + COL_EMAIL + ", " +
//                COL_PHONE + ", " + COL_STREET + ", " + COL_STATE + ", " + COL_ZIP + ", " + COL_COMPANY +

        String selectQuery = "SELECT *" +
                " FROM " + TABLE_BUSINESS + " ORDER BY " + COL_NAME;

        // get the database from the SQLiteHelper
        SQLiteDatabase db = this.getReadableDatabase();

        // execute a raw SQLite query
        Cursor cursor = db.rawQuery(selectQuery, null);

        // return the Cursor
        return cursor;
    }

    // delete all the business contact entries in the business table
    public void deleteAllContactBusiness()
    {
        // get the datatbase
        SQLiteDatabase db = this.getWritableDatabase();

        // delete the business contacts and close
        // 2nd arg is where clause
        // 3rd arg is where values
        db.delete(TABLE_BUSINESS, null, null);
        db.close(); // Closing database connection
    }

    // Called when the database needs to be upgraded.
    // The implementation should use this method to drop tables, add tables,
    // or do anything else it needs to upgrade to the new schema version.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        System.out.println("SQLite onUpgrade called");

        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BUSINESS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FRIEND);

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
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BUSINESS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FRIEND);

        // Create tables again
        onCreate(db);
    }
}
