//Project: Lab8_Hamilton
//Name: Dylan Hamilton
//Date: 10/23/18
//Class: StartDatePicker
//Description:  Enables Start Date Functionality



package com.example.kozlevcar.lab8_hamilton;

import android.app.DatePickerDialog;
import android.app.Dialog;
//import android.icu.util.Calendar;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;

import java.util.Calendar;

public class StartDatePicker extends DialogFragment implements DatePickerDialog.OnDateSetListener
{
    // create a static reference to calendar object
    // it is not created and destroyed each time the DatePickerFragment is created.
    private static Calendar calendar;

    // declare static variables for month, day and year
    // used to initialize the datePicker
    private static int year;
    private static int month;
    private static int day;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        // Use the current date as the default date in the picker
        // if calendar has no value
        if(calendar == null)
        {
            // get current date
            calendar = Calendar.getInstance();

            // assign current month, day and year to static variables
            year = calendar.get(Calendar.YEAR);
            month = calendar.get(Calendar.MONTH);
            day = calendar.get(Calendar.DAY_OF_MONTH);
        }

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    // this method is the listener for the date picker
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth)
    {
        // copy the user supplied month, day and year to the static instance variables
        this.year = year;
        this.day = dayOfMonth;
        this.month = month;

        // update the calendar object with the user supplied date
        calendar.set(year, month, dayOfMonth);

        // get the MainActivity and call the public setDate method
        // Its prefered to use a listener
//          MainActivity ma = (MainActivity)getActivity();
//          ma.onDisplayDate(calendar);

        dpListener.onDisplayStartDate(calendar);
    }

    // public interface used to define callback methods
    // that will be called in the MainActivity
    public interface StartDateListener
    {
        // these method will be implemented in the MainActivity
        public void onDisplayStartDate(Calendar aDate);
    }

    // Use this instance of the interface to deliver action events to main activity
    private StartDateListener dpListener;

    // Override the Fragment.onAttach() method to instantiate the NoticeDialogListener
    @Override
//    public void onAttach(Activity activity) // is deprecated
    public void onAttach(Context context)
    {
        super.onAttach(context);
        // Verify that the host activity implements the callback interface
        try
        {
            // Instantiate the MissleDialogListener so we can send events to the host
            dpListener = (StartDateListener) context;
        }
        catch (ClassCastException e)
        {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(context.toString()
                    + " must implement StartDateListener");
        }
    }
}
