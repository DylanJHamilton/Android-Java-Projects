//Project :     Final_Hamilton
// Author:      Dylan J. Hamilton
// Date:        12/9/2018
// File:        ContactFriend.java
// Description: This class represents a friend contact
// Updates:
// 11/8/18 replace the arrayList with a SQLite cursor.

package com.example.jkozlevcar.masterdetailex2;

public class ContactFriend extends Contact
{
    // private field
    private int yearMet;

    // constructor. Parameters for all fields
    public ContactFriend(String name, String email, String phone,
                         String street, String city, String state, String zip,
                         ContactType type, int yearMet) throws ContactException
    {
        // call the superclass constructor
        super(name, email, phone, street, city, state, zip, type);

        // assign the field
        this.yearMet = yearMet;
    }

    // toString method
    @Override
    public String toString()
    {
        return super.toString() + ", " + yearMet;
    }

    @Override
    public String toFile()
    {
        return super.toFile() + "," + yearMet;
    }

    public int getYearMet()
    {
        return yearMet;
    }
}

