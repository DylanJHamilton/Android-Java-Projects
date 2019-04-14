// Project:		CustomDialogEx1
// Class Name:	CustomDialog
// Date:        10/13/18
// Author:      Dylan Hamilton
// Description:
// Build a simple custom dialog with an address edit view.


package com.example.jkozlevcar.lab2;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.widget.EditText;


public class CustomDialog extends DialogFragment
{
    // create a reference for the inflator
    private LayoutInflater inflater;

    // @NonNull indicates a variable, parameter, or return value that cannot be null.
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        // create the dialog builder
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // Get the layout inflater object
        inflater = getActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(inflater.inflate(R.layout.custom_dialog, null));

        // set the title
        builder.setTitle("Custom Email Dialog");

        // add a positive button
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                // get the email editText from the dialog, long way
//                EditText etEmail = (EditText)CustomDialog.this.getDialog().findViewById(R.id.etEmail);

                // get the email editText from the dialog
                // cast is no longer needed
                EditText etPlayer = getDialog().findViewById(R.id.etPlayer);

                // get the text and display it
                System.out.println("Email: " + etPlayer.getText());

                // call method in mainActivity
                cdListener.customDialogPositiveClick(etPlayer.getText().toString());
            }
        });

        return builder.create();
    }

    // public interface used to define callback methods
    // that will be called in the MainActivity
    public interface CustomDialogListener
    {
        // these method will be implemented in the MainActivity
        public void customDialogPositiveClick(String email);
        public void customDialogNegativeClick();
    }

    // Use this reference of the interface to deliver action events
    private CustomDialogListener cdListener;

    // Override the Fragment.onAttach() method to instantiate the NoticeDialogListener
    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        // Verify that the host activity implements the callback interface
        try
        {
            // Instantiate the CustomDialogListener so we can send events to the host
            cdListener = (CustomDialogListener) context;
        }
        catch (ClassCastException e)
        {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(context.toString()
                    + " must implement CustomDialogListener");
        }
    }
}
