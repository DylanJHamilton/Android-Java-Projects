//Database Test Class
//This class passes all Data created in the Database Handler.


package com.call43studio.jeepswapshop;

import android.content.Context;
import android.database.Cursor;

public abstract class DatabaseTest {

    //Lets test the Parts Table! his Method adds, creates, updates and deletes data in the Parts Table of the Database!
    public static void testPartsTable(Context context)
    {
        // create the database handler object
        DatabaseHandler dbh = new DatabaseHandler(context);

        // delete any existing cs in the friend table
        dbh.deleteAllListingParts();

        try
        {
            // create Listings to add to Parts table
            ListingParts listing1 = new ListingParts("T1", "M1", "M1", "M1", "P1", "E1", "P1", "S1", "C1", "S1", "Z1", "33333",  Listing.ListingType.PARTS, 0);
            ListingParts listing2 = new ListingParts("N1", "E1", "P1", "S1", "C1", "OH", "M1", "(000)000-1111", "T1", "Y1", "O1", "33333",  Listing.ListingType.PARTS, 0);
            ListingParts listing3 = new ListingParts("N1", "E1", "P1", "S1", "C1", "OH", "M1", "(000)000-1211", "T1", "Y1", "O1", "33333",  Listing.ListingType.PARTS, 0);
            ListingParts listing4 = new ListingParts("N1", "E1", "P1", "S1", "C1", "OH", "M1", "(000)000-1311", "T1", "Y1", "O1", "33333",  Listing.ListingType.PARTS, 0);

            // add the contact to the Parts table
            dbh.addListingParts(listing1);
            dbh.addListingParts(listing2);
            dbh.addListingParts(listing3);
            dbh.addListingParts(listing4);

            // test method getAllListingParts()
            System.out.println("Test getAllListingParts()");
            Cursor cursor = dbh.getAllListingParts();

            // move to the first record in the cursor
            cursor.moveToFirst();

            // loop through the cursor

            while (cursor.isAfterLast() == false)
            {
                System.out.println(cursor.getInt(0) + ", " + cursor.getString(1) + ", "  +
                        cursor.getString(2) + ", " + cursor.getString(3) + ", " +
                        cursor.getString(4) + ", " + cursor.getString(5) + ", " +
                        cursor.getString(6) + ", " + cursor.getString(7) + ", " +
                        cursor.getString(8) + "," + cursor.getString(9) + "," + cursor.getString(10) + "," +
                        cursor.getString(11) + "," + cursor.getString(12) + "," + cursor.getInt(13) );
                cursor.moveToNext();
            }
            System.out.println(" ");

            // test method getListingPartsByName()
            System.out.println("Test getListingPartsByName(\"N3\")");
            ListingParts listing = dbh.getListingPartsByName("N3");
            System.out.println(listing);
            System.out.println(" ");

            // test deleteListingPartsByName()
            System.out.println("Test deleteListingPartsByName(\"N3\")");
            dbh.deleteListingPartsByName("N3");
            System.out.println(" ");

            // test method getAllListingParts()
            System.out.println("Test getAllContactFriend()");
            cursor = dbh.getAllListingParts();

            // move to the first record in the cursor
            cursor.moveToFirst();

            // loop through the cursor

            while (cursor.isAfterLast() == false)
            {
                System.out.println(cursor.getInt(0) + ", " + cursor.getString(1) + ", "  +
                        cursor.getString(2) + ", " + cursor.getString(3) + ", " +
                        cursor.getString(4) + ", " + cursor.getString(5) + ", " +
                        cursor.getString(6) + ", " + cursor.getString(7) + ", " +
                        cursor.getString(8) + "," + cursor.getString(9) + "," + cursor.getString(10) + "," +
                        cursor.getString(11) + "," + cursor.getString(12) + "," + cursor.getInt(13) );
                cursor.moveToNext();
            }
            System.out.println(" ");
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    //Lets Test the Jeep Table! This Method adds, creates, updates and deletes data in the Jeep Table of the Database!
    public static void testListingTable(Context context)
    {
        // create the database handler object
        DatabaseHandler dbh = new DatabaseHandler(context);

        // delete any existing contacts in the Jeep Table
       dbh.deleteAllListingJeep();

        try
        {
        // create Listings to add to Jeep table
        ListingJeep listing1 = new ListingJeep("N1", "E1", "P1", "S1", "C1", "OH", "M1", "(000)000-1011", "T1", "Y1", "O1", "33333",  Listing.ListingType.JEEP, "");
        ListingJeep listing2 = new ListingJeep("N1", "E1", "P1", "S1", "C1", "OH", "M1", "(000)000-1111", "T1", "Y1", "O1", "33333",  Listing.ListingType.PARTS, "");
        ListingJeep listing3 = new ListingJeep("N1", "E1", "P1", "S1", "C1", "OH", "M1", "(000)000-1211", "T1", "Y1", "O1", "33333",  Listing.ListingType.PARTS, "");
        ListingJeep listing4 = new ListingJeep("N1", "E1", "P1", "S1", "C1", "OH", "M1", "(000)000-1311", "T1", "Y1", "O1", "33333",  Listing.ListingType.PARTS, "");

            // add the contact to the Parts table
             dbh.addListingJeep(listing1);
             dbh.addListingJeep(listing2);
             dbh.addListingJeep(listing3);
             dbh.addListingJeep(listing4);

//
            // test method getAllListingTable()
            System.out.println("Test getAllLis()");
            Cursor cursor = dbh.getAllListingJeep();

            // move to the first record in the cursor
            cursor.moveToFirst();

            // loop through the cursor

            while (cursor.isAfterLast() == false)
            {
                System.out.println(cursor.getInt(0) + ", " + cursor.getString(1) + ", "  +
                        cursor.getString(2) + ", " + cursor.getString(3) + ", " +
                        cursor.getString(4) + ", " + cursor.getString(5) + ", " +
                        cursor.getString(6) + ", " + cursor.getString(7) + ", " +
                        cursor.getString(8) + "," + cursor.getString(9) + "," + cursor.getString(10) + "," +
                        cursor.getString(11) + "," + cursor.getString(12) + "," + cursor.getString(13) );
                cursor.moveToNext();
            }
            System.out.println(" ");
//
//            // test method getListingJeepByName()
          System.out.println("Test getListingJeepByName(\"N3\")");
          ListingJeep listing = dbh.getListingJeepByName("N3");
           System.out.println(listing);
           System.out.println(" ");

           // test deleteListingJeepByName()
          System.out.println("Test deleteLisitngJeepByName(\"N3\")");
            dbh.deleteListingJeepByName("N3");
           System.out.println(" ");
//
//            // test method getAllListingJeep()
            System.out.println("Test getAllListingJeep()");
           cursor = dbh.getAllListingJeep();
//
//            // move to the first record in the cursor
            cursor.moveToFirst();
//
//            // loop through the cursor
//
           while (cursor.isAfterLast() == false)
           {
                System.out.println(cursor.getInt(0) + ", " + cursor.getString(1) + ", "  +
                        cursor.getString(2) + ", " + cursor.getString(3) + ", " +
                        cursor.getString(4) + ", " + cursor.getString(5) + ", " +
                        cursor.getString(6) + ", " + cursor.getString(7) + ", " +
                        cursor.getString(8) + "," + cursor.getString(9) + "," + cursor.getString(10) + "," +
                        cursor.getString(11) + "," + cursor.getString(12) + "," + cursor.getString(13) );
                cursor.moveToNext();
            }
           System.out.println(" ");

            dbh.close();
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}
