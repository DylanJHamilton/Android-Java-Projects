// Project:		Lab3_Hamilton
// Class Name:	MainActivity
// Date:       9/11/18
// Author:     Dylan J. Hamilton
// Description:
// Build a simple tip calculator using radio buttons for the tip amount.


package com.call43studio.lab3_hamilton;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity
{

    //declare Java references
    private EditText tvAmount;
    private EditText tvTotal;
    private EditText tvTip;
    private RadioGroup rgTip;
    private RadioButton rb10Percent;
    private RadioButton rb15Percent;
    private RadioButton rb20Percent;
    private RadioButton rb25Percent;
    private RadioButton rb30Percent;




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
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //creates Java Objects and ties to the GUI Resources
        tvAmount = findViewById(R.id.tvAmount);
        tvTip = findViewById(R.id.tvTip);
        tvTotal = findViewById(R.id.tvTotal);
        rb10Percent = findViewById(R.id.rb10Percent);
        rb15Percent = findViewById(R.id.rb15Percent);
        rb20Percent = findViewById(R.id.rb20Percent);
        rb25Percent = findViewById(R.id.rb25Percent);
        rb30Percent = findViewById(R.id.rb30Percent);
        rgTip = findViewById(R.id.rgTip);

       //makes 15 percent the default and sanity check for the build!
        rb15Percent.setChecked(true);
        //disables user editing on tip and total
        tvTotal.setFocusable(false);
        tvTip.setFocusable(false);

    //calls Listeners
        rgTip.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                // calls method to calculate tip
                toCalculate();
            }


        });

        tvAmount = findViewById(R.id.tvAmount);
        tvAmount.setOnKeyListener(new View.OnKeyListener()
        {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event)
            {

                //calls method to calculate
                toCalculate();


                return false;
            }
        });
    }

    //method to calculate the tip
   public void toCalculate(){

        double amount = 0;
        double tip = 0;
        double total = 0;

        try{
            amount = Double.parseDouble(tvAmount.getText().toString());


            if (rb10Percent.isChecked()){
                tip = amount * 0.1;
                total = amount * 1.1;
            }

            if(rb15Percent.isChecked()){
                tip = amount * 0.15;
                total = amount * 1.15;
            }

            if(rb20Percent.isChecked()){
                tip = amount * 0.2;
                total = amount * 1.2;
            }

            if(rb25Percent.isChecked()){
                tip = amount * 0.25;
                total = amount * 1.25;
            }

            if(rb30Percent.isChecked()){
                tip = amount * 0.3;
                total = amount * 1.3;
            }
        }


        catch(Exception e){

        }

        tvTotal.setText(String.format("$%.2f", total));
        tvTip.setText(String.format("$%.2f", tip));
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
