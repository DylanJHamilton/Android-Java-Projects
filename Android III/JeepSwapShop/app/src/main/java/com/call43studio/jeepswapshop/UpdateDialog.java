package com.call43studio.jeepswapshop;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

public class UpdateDialog {
    // create a reference for the inflator
    private LayoutInflater inflater;

    // used to generate test data.
    private static int count = 0;

    // references for Java GUI objects
    private EditText et_title;
    private EditText et_make;
    private EditText et_model;
    private EditText et_mileage;
    private EditText et_price;
    private EditText et_email;
    private EditText et_phone;
    private EditText et_street;
    private EditText et_city;
    private EditText et_state;
    private EditText et_zip;
    private EditText et_year;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        // create the dialog builder
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // set the title
        builder.setTitle("Update Contact Business");

        // Get the layout inflater object
        inflater = getActivity().getLayoutInflater();

//        // Inflate and set the layout for the dialog
//        // Pass null as the parent view because its going in the dialog layout
//        builder.setView(inflater.inflate(R.layout.add_dialog, null));
//

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        View dialogLayout = inflater.inflate(R.layout.add_dialog, null);
        builder.setView(dialogLayout);

        // create the Java objects and tie to the dialog GUI
        et_title = dialogLayout.findViewById(R.id.etTitle);
        et_make = dialogLayout.findViewById(R.id.etMake);
        et_model = dialogLayout.findViewById(R.id.etModel);
        et_mileage = dialogLayout.findViewById(R.id.etMileage);
        et_price = dialogLayout.findViewById(R.id.etPrice);
        et_phone = dialogLayout.findViewById(R.id.etPhone);
        et_email = dialogLayout.findViewById(R.id.etEmail);
        et_street = dialogLayout.findViewById(R.id.etStreet);
        et_city = dialogLayout.findViewById(R.id.etCity);
        et_state = dialogLayout.findViewById(R.id.etState);
        et_zip = dialogLayout.findViewById(R.id.etZip);
        et_year = dialogLayout.findViewById(R.id.etYear);


        // set the name uneditable.
        et_title.setEnabled(false);

        // copy data from arguments to edit texts
        // get the argument bundle
        Bundle args = getArguments();

        // get the string array with the details from the arg bundle
        String[] details = args.getStringArray(ItemDetailFragment.ARG_ITEM_ID);

        // copy the data from the detail array to the edit texts
        et_title.setText(details[0]);
        et_make.setText(details[1]);
        et_model.setText(details[2);
        et_mileage.setText(details[3]);
        et_price.setText(details[4]);
        et_email.setText(details[5]);
        et_phone.setText(details[6]);
        et_street.setText(details[7]);
        et_city.setText(details[8]);
        et_state.setText(details[9]);
        et_zip.setText(details[10]);
        et_year.setText(details[11]);

        // add a positive button
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                System.out.println("Update dialog onClick called");

                // get the editTexts from the dialog as local variables
//                EditText etName = (EditText)AddContactBusinessDialog.this.getDialog().findViewById(R.id.etName);

                // create a Contact business object from the user input
                try
                {
                    ListingJeep cbObject = new ListingJeep(
                            et_title.getText().toString(),
                            et_make.getText().toString(),
                            et_model.getText().toString(),
                            et_mileage.getText().toString(),
                            et_price.getText().toString(),
                            et_email.getText().toString(),
                            et_phone.getText().toString(),
                            et_street.getText().toString(),
                            et_city.getText().toString(),
                            et_state.getText().toString(),
                            et_zip.getText().toString(),
                            cursor.getString(12), Listing.ListingType.JEEP,
                            et_year.getText().toString());

                    // debug
                    System.out.println(cbObject.toString());

                    // call method in mainActivity to add a contact business to the database
                    addListener.updateDialogPositiveClick(cbObject);
                }
                catch(Exception e)
                {
                    System.out.println("Catch: " + e.getMessage());
                }
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {

            }
        });

        return builder.create();
    }

    // public interface used to define callback methods
    // that will be called in the MainActivity
    public interface UpdateDialogListener
    {
        // these method will be implemented in the MainActivity
        public void updateDialogPositiveClick(ListingJeep cbObject);
        public void updateDialogNegativeClick();
    }

    // Use this reference of the interface to deliver action events
    private UpdateDialogListener addListener;

    // Override the Fragment.onAttach() method to instantiate the NoticeDialogListener
    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        // Verify that the host activity implements the callback interface
        try
        {
            // Instantiate the AddDialogListener so we can send events to the host
            addListener = (UpdateDialogListener) context;
        }
        catch (ClassCastException e)
        {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(context.toString()
                    + " must implement AddDialogListener");
        }
    }


}
