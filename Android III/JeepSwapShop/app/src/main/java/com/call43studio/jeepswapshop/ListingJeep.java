//ListingJeep Class
//Child Class of the Listing Class. Pulls together all the objects for the Jeep DB Table that is later used
//in the Database Handler then passes to the DatabaseTest and then finally, the ItemLists.

package com.call43studio.jeepswapshop;

public class ListingJeep extends Listing{

    //Declare Field
    private String year;

    //Constructor with fields for respective DataFlow
    public ListingJeep(String title, String make, String model, String mileage, String price, String email, String phone, String street,
                       String city, String state, String zip, String string, ListingType type, String year) throws ListingException{
        // call the superclass constructor
      super(title, make, model, mileage, price, email, phone, street, city, state, zip, type);

        // assign the field
        this.year = year;
    }




    // toString method
    @Override
    public String toString()
    {
        return super.toString() + ", " + year;
    }

    @Override
    public String toFile()
    {
        return super.toFile() + "," + year;
    }

    // added for database
    public String getYear()
    {
        return year;
    }
    }
