//Project :     Final_Hamilton
// Author:      Dylan J. Hamilton
// Date:        12/8/2018
// File:        ContactBusiness.java
// Description: This class represents a business contact
// Updates:
// 11/8/18 replace the arrayList with a SQLite cursor.

package com.example.jkozlevcar.masterdetailex2;

public class ContactBusiness extends Contact
{
    // private field
    private String company;

    // constructor. Parameters for all fields
    public ContactBusiness(String name, String email, String phone,
                           String street, String city, String state, String zip,
                           Contact.ContactType type, String company) throws ContactException
    {
        // call the superclass constructor
        super(name, email, phone, street, city, state, zip, type);

        // assign the field
        this.company = company;
    }

    // toString method
    @Override
    public String toString()
    {
        return super.toString() + ", " + company;
    }

    @Override
    public String toFile()
    {
        return super.toFile() + "," + company;
    }

    // added for database
    public String getCompany()
    {
        return company;
    }
}


