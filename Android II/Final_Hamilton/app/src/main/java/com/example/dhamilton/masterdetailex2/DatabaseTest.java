//Project :     Final_Hamilton
// Author:      Dylan J. Hamilton
// Date:        12/9/2018
// File:        DatabaseTest.java
// Description: This class tests creating, adding, deleting and listing of the business table
// Updates:     10/24/18
//              Add friend table
// 11/8/18 replace the arrayList with a SQLite cursor.

package com.example.jkozlevcar.masterdetailex2;


import android.content.Context;
import android.database.Cursor;

// class is abstract because there is no need to create an object
// It is only used to hold methods to test the database
public abstract class DatabaseTest
{
    // Lab9
    public static void testFriendTable(Context context)
    {
        // create the database handler object
        DatabaseHandler dbh = new DatabaseHandler(context);

        // delete any existing contacts in the friend table
        dbh.deleteAllContactFriend();

        try
        {
            // create contacts to add to friend table
            ContactFriend contact1 = new ContactFriend("N1", "E1", "P1", "S1", "C1", "OH", "11111", Contact.ContactType.FRIEND, 2001);
            ContactFriend contact2 = new ContactFriend("N2", "E2", "P2", "S2", "C2", "PA", "22222", Contact.ContactType.FRIEND, 2002);
            ContactFriend contact3 = new ContactFriend("N3", "E3", "P3", "S3", "C3", "AL", "33333", Contact.ContactType.FRIEND, 2003);
            ContactFriend contact4 = new ContactFriend("N4", "E4", "P4", "S4", "C4", "MI", "44444", Contact.ContactType.FRIEND, 2004);

            // add the contact to the friend table
            dbh.addContactFriend(contact1);
            dbh.addContactFriend(contact2);
            dbh.addContactFriend(contact3);
            dbh.addContactFriend(contact4);

            // test method getAllContactFriend()
            System.out.println("Test getAllContactFriend()");
            Cursor cursor = dbh.getAllContactFriend();

            // move to the first record in the cursor
            cursor.moveToFirst();

            // loop through the cursor

            while (cursor.isAfterLast() == false)
            {
                System.out.println(cursor.getInt(0) + ", " + cursor.getString(1) + ", "  +
                        cursor.getString(2) + ", " + cursor.getString(3) + ", " +
                        cursor.getString(4) + ", " + cursor.getString(5) + ", " +
                        cursor.getString(6) + ", " + cursor.getString(7) + ", " +
                        cursor.getInt(8) );
                cursor.moveToNext();
            }
            System.out.println(" ");

            // test method getContactFriendByName()
            System.out.println("Test getContactFriendByName(\"N3\")");
            ContactFriend contact = dbh.getContactFriendByName("N3");
            System.out.println(contact);
            System.out.println(" ");

            // test deleteContactFriendByName()
            System.out.println("Test deleteContactFriendByName(\"N3\")");
            dbh.deleteContactFriendByName("N3");
            System.out.println(" ");

            // test method getAllContactFriend()
            System.out.println("Test getAllContactFriend()");
            cursor = dbh.getAllContactFriend();

            // move to the first record in the cursor
            cursor.moveToFirst();

            // loop through the cursor

            while (cursor.isAfterLast() == false)
            {
                System.out.println(cursor.getInt(0) + ", " + cursor.getString(1) + ", "  +
                        cursor.getString(2) + ", " + cursor.getString(3) + ", " +
                        cursor.getString(4) + ", " + cursor.getString(5) + ", " +
                        cursor.getString(6) + ", " + cursor.getString(7) + ", " +
                        cursor.getString(8) );
                cursor.moveToNext();
            }
            System.out.println(" ");
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    // this method creates, adds, deletes and lists the business table
    public static void testBusinessTable(Context context)
    {
        // create the database handler object
        DatabaseHandler dbh = new DatabaseHandler(context);

        // delete any existing contacts in the business table
        dbh.deleteAllContactBusiness();

        try
        {
            // create contacts to add to business table
            ContactBusiness contact1 = new ContactBusiness("N1", "E1", "P1", "S1", "C1", "OH", "11111", Contact.ContactType.BUSINESS, "C1");
            ContactBusiness contact2 = new ContactBusiness("N2", "E2", "P2", "S2", "C2", "PA", "22222", Contact.ContactType.BUSINESS, "C2");
            ContactBusiness contact3 = new ContactBusiness("N3", "E3", "P3", "S3", "C3", "AL", "33333", Contact.ContactType.BUSINESS, "C3");
            ContactBusiness contact4 = new ContactBusiness("N4", "E4", "P4", "S4", "C4", "MI", "44444", Contact.ContactType.BUSINESS, "C4");

            // add the contact to the business table
            dbh.addContactBusiness(contact1);
            dbh.addContactBusiness(contact2);
            dbh.addContactBusiness(contact3);
            dbh.addContactBusiness(contact4);

            // test method getAllContactBusiness()
            System.out.println("Test getAllContactBusiness()");
            Cursor cursor = dbh.getAllContactBusiness();

            // move to the first record in the cursor
            cursor.moveToFirst();

            // loop through the cursor

            while (cursor.isAfterLast() == false)
            {
                System.out.println(cursor.getInt(0) + ", " + cursor.getString(1) + ", "  +
                     cursor.getString(2) + ", " + cursor.getString(3) + ", " +
                     cursor.getString(4) + ", " + cursor.getString(5) + ", " +
                     cursor.getString(6) + ", " + cursor.getString(7) + ", " +
                     cursor.getString(8) );
                cursor.moveToNext();
            }
            System.out.println(" ");

            // test method getContactBusinessByName()
            System.out.println("Test getContactBusinessByName(\"N3\")");
            ContactBusiness contact = dbh.getContactBusinessByName("N3");
            System.out.println(contact);
            System.out.println(" ");

            // test deleteContactBusinessByName()
            System.out.println("Test deleteContactBusinessByName(\"N3\")");
            dbh.deleteContactBusinessByName("N3");
            System.out.println(" ");

            // test method getAllContactBusiness()
            System.out.println("Test getAllContactBusiness()");
            cursor = dbh.getAllContactBusiness();

            // move to the first record in the cursor
            cursor.moveToFirst();

            // loop through the cursor

            while (cursor.isAfterLast() == false)
            {
                System.out.println(cursor.getInt(0) + ", " + cursor.getString(1) + ", "  +
                        cursor.getString(2) + ", " + cursor.getString(3) + ", " +
                        cursor.getString(4) + ", " + cursor.getString(5) + ", " +
                        cursor.getString(6) + ", " + cursor.getString(7) + ", " +
                        cursor.getString(8) );
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
