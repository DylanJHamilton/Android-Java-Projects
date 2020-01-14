//Listing Class
//The parent class of ListingJeep, ListingParts and the Public CLass for the ListingException.
//This pulls all objects together to work with the Database Handler that is later tied into the ItemList classes that displays the data
//applied in the app.

package com.call43studio.jeepswapshop;

public abstract class Listing implements Comparable<Listing> {
    //Declaration of instance fields
    private String title;
    private String make;
    private String model;
    private String mileage;
    private String price;
    private String email;
    private String phone;
    private Address address;
    private ListingType type;


    //Private Static Class Fiekld
    private static int count;

    //Finalization Method
    public static void decrementCount() { --count; };

    //Object Declarations Constructor and Creations in Listing Class
    public Listing(String title, String make, String model, String mileage, String price, String email, String phone, String street,
                   String city, String state, String zip, ListingType type) throws ListingException{

        //Declarations
        this.title = title;
        this.make = make;
        this.model= model;
        this.mileage = mileage;
        this.price = price;
        this.email = email;
        this.phone = phone;

        //Create the Inner Address Objects
        this.address = new Address(street, city, state, zip);
        this.type = type;

        ++count;


    }

    //Get and Set Method Declarations
    //Count
    public static int getCount() { return count; }

    //Address
    public Address getAddress() {return address; }
    public void setAddress(Address address) { this.address = address; }

    //Title
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    //Make
    public String getMake() { return make; }
    public void setMake() { this.make = make; }

    //Model
    public String getModel() { return model; }
    public void setModel() { this.model = model; }

    //Mileage
    public String getMileage() { return mileage; }
    public void setMileage() { this.mileage = mileage; }

    //Price
    public String getPrice() { return price; }
    public void setPrice() { this.price = price; }


    //Email
    public String getEmail() { return email; }
    public void setEmail() { this.email = email; }

    //Phone
    public String getPhone() { return phone; }
    public void setPhone() { this.phone = phone; }



    //Declare toString method.
    public String toString() {
        return title + ": " + make + ", " + model + ", " + "," + mileage + "," + price + "," + email + "," + phone +
                address.toString() + ", " + type;
    }

   //Declare toFile method.
   public String toFile(){
       return title + ": " + make + ", " + model + ", " + "," + mileage + "," + price + "," + email + "," + phone +
               address.toString() + ", " + type;
    }


    //Declaration of compareTo
  public int compareTo(Listing listingObj){return this.title.compareTo(listingObj.getTitle());}


  //Public Declaration for Address
  public class Address
  {
      // private fields
      private String street;
      private String city;
      private String state;
      private String zip;

      // public constructor
      public Address(String street, String city, String state, String zip) throws ListingException
      {
          this.street = street;
          this.city = city;
          this.state = state;
          this.zip = zip;

          // call validate methods
          validateStreet();
          validateCity();
          validateState();
          validateZip();
      }

      // public get/set methods.
      public String getStreet()
      {
          return street;
      }

      public void setStreet(String street)
      {
          this.street = street;
          validateStreet();
      }

      public String getCity()
      {
          return city;
      }

      public void setCity(String city)
      {
          this.city = city;
          validateCity();
      }

      public String getState()
      {
          return state;
      }

      public void setState(String state) throws ListingException
      {
          this.state = state;
          validateState();
      }

      public String getZip()
      {
          return zip;
      }

      public void setZip(String zip)  throws ListingException
      {
          this.zip = zip;
          validateZip();
      }

      // toString method to display the fields in the class
      public String toString()
      {
          return street + ", " + city + ", " + state + ", " + zip;
      }

      // Lab12
      public String toFile()
      {
          return street + "," + city + "," + state + "," + zip;
      }

      // validate methods added for Lab 7

      public void validateStreet()
      {
          // if length longer than 10 characters, truncate it.
          street = street.length() <= 10? street : street.substring(0, 10);
      }

      public void validateCity()
      {
          // make the first letter uppercase
          city = city.substring(0, 1).toUpperCase() + city.substring(1);
      }

      public void validateState() throws ListingException
      {
          // change to uppercase
          state = state.toUpperCase();
          if(state.length() != 2)
          {
              throw new ListingException("State: Not 2 characters");
          }
      }

      public void validateZip() throws ListingException
      {
          //use regex for match zip code
          if(!zip.matches("^\\d{5}$"))
          {
              throw new ListingException("Zip: Incorrect format");
          }
      }
  }

    //Declaration of ListingType Enumerator
    public static enum ListingType{
        JEEP, PARTS
    }


}
