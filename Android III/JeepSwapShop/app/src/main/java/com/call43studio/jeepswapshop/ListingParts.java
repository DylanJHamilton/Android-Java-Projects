//ListingParts Class
//Child Class of the Listing Class. Pulls together all the objects for the Parts DB Table that is later used
//in the Database Handler then passes to the DatabaseTest and then finally, the ItemLists.

package com.call43studio.jeepswapshop;

public class ListingParts extends Listing{

    private int amountParts;


    public ListingParts(String title, String make, String model, String mileage, String price, String email, String phone, String street,
                        String city, String state, String zip, String string, ListingType type, int AmountParts) throws ListingException
    {
        // call the superclass constructor
        super(title, make, model, mileage, price, email, phone, street, city, state, zip, type);

        //assigning the Amount of Parts Field
        this.amountParts = amountParts;
    }

    // toString method
    @Override
    public String toString()
    {
        return super.toString() + ", " + amountParts;
    }

    @Override
    public String toFile()
    {
        return super.toFile() + "," + amountParts;
    }

    public int getParts()
    {
        return amountParts;
    }
}
