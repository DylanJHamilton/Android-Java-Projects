// Project:		Lab5 2018
// Class Name:	MainActivity
// Date:        8/9/18
// Author:     Hamilton
// Description:
// Build a simple tip calculator using radio buttons for the tip amount.
// Update:
// Add id for radio group
// Create method to do calculation
// Add onCheckChanged listener to radio group so that tip is calculated when changed
// Add onKeyListener listener to amount editText
//
// Update to Lab4:
// Replace tip radio buttons with seek bar. 0 to 30. Default 15
// Add spinner for number of people. 1 to 5. Default 1.
//
//Update to Lab5
//


package com.example.jkozlevcar.lab3;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{
    // declare Java references to objects
    private EditText etAmount;
    private EditText etTotal;
    private EditText etTip;
    private Spinner spSplit;
    private SeekBar sbTip;
    private SharedPreferences preferenceSettings;
    private SharedPreferences.Editor preferenceEditor;
    private static final int PREFERENCE_MODE_PRIVATE = 0;

    //key string for default tip
    private final String DEFAULT_TIP = "default_tip";

    private int defaultTip;
    private Context context = this;





    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
               //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();

                //added for lab 5 to create the intent in the OnClickListener for the FAB
                Intent intent = new Intent(context, PreferenceActivity.class);

                //starts the activity
                startActivity(intent);


            }
        });

        // create Java objects and connect to GUI resources
        etAmount = findViewById(R.id.etAmount);
        etTotal = findViewById(R.id.etTotal);
        etTip = findViewById(R.id.etTip);

        // set no focus on the total editTExt
        etTotal.setFocusable(false);
        etTip.setFocusable(false);


        // NOTE: onKeyListener doesn't work for soft keyboards.
        // a custom editText is required to get soft keyboards backspace key
        etAmount.setOnKeyListener(new View.OnKeyListener()
        {
            // View: The view the key has been dispatched to.
            // int: The code for the physical key that was pressed
            // KeyEvent: The KeyEvent object containing full information about the event.
            // Returns: True if the listener has consumed the event, false otherwise.
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event)
            {
                System.out.println("onKey called");
                calculateTip();
                // must be false so the event is passed to the editText
                return false;
            }
        });

        // called when the check is selected on the keyboard.
        etAmount.setOnEditorActionListener(new TextView.OnEditorActionListener()
        {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event)
            {
                System.out.println("onEditorAction called");
                return false;
            }
        });

        // added for Lab4
        spSplit = findViewById(R.id.spSplit);
        sbTip = findViewById(R.id.sbTip);

        // add listeners
        spSplit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                // call calculateTip
                calculateTip();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });

        sbTip.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
            {
                // call calculateTip
                calculateTip();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar)
            {
                    //gets the data from the preference after the Preference Activity is done
                    defaultTip = preferenceSettings.getInt("DEFAULT_TIP", 10);

                    //sets default tip in the seek bar
                    sbTip.setProgress(defaultTip);
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar)
            {

            }
        });

        preferenceSettings = getSharedPreferences("lab5", PREFERENCE_MODE_PRIVATE);
        preferenceEditor = preferenceSettings.edit();

        defaultTip = preferenceSettings.getInt("DEFAULT TIP", 10);

        sbTip.setProgress(defaultTip);


    }

    // method to calculate tip using if logic and radio button isChecked method
    private void calculateTip()
    {
        // declare local variables
        double amount = 0;
        double total = 0;
        double tip = 0;
        int split = 1;
        double tipPercent = 15;

        // get the split
        split = spSplit.getSelectedItemPosition() + 1;

        // get the tip percentage
        tipPercent = sbTip.getProgress();

        try
        {
            // get the amount
            amount = Double.parseDouble(etAmount.getText().toString());

            // calculate tip and amount
            tip = amount * tipPercent / 100;
            total = (amount + tip) / split;
        }
        catch(Exception e)
        {
            // do nothing
        }

        // display the total in the total editText
        etTotal.setText(String.format("$%.2f", total));

        // display tip in the tip editText
        etTip.setText(String.format("$%.2f (%2.0f%%)", tip, tipPercent));
    }

   

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
