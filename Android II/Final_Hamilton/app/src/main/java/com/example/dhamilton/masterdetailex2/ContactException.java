//Project :     Final_Hamilton
// Author:      Dylan J. Hamilton
// Date:        12/9/2018
// File:        ContactException.java
// Description: This class is an exception for Contact creation
// Updates:
// 11/8/18 replace the arrayList with a SQLite cursor.

package com.example.jkozlevcar.masterdetailex2;

public class ContactException extends Exception
{
    public ContactException(String message)
    {
        super(message);
    }
}
