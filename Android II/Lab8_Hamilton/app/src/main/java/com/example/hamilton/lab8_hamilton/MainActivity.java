//Project: Lab8_Hamilton
//Name: Dylan Hamilton
//Date: 10/23/18
//Class: MainActivity
//Description: Create new App that correlates with DatePickers.


package com.example.kozlevcar.lab8_hamilton;

//import android.app.DialogFragment;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.format.DateFormat;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

//import java.text.DateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements StartDatePicker.StartDateListener, EndDatePicker.EndDateListener
{

    // declare Java references
    private Button btnStart;
    private Button btnEnd;
    private TextView tvOutput;

    // start and end calendar references
    private Calendar calendarStartDate;
    private Calendar calendarEndDate;



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


        // get current date for both start and end
        calendarStartDate = Calendar.getInstance();
        calendarEndDate = Calendar.getInstance();
        btnStart = findViewById(R.id.btnStart);
        btnEnd = findViewById(R.id.btnEnd);
        tvOutput = findViewById(R.id.tvOutput);

        //Button Start
        btnStart.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                DialogFragment newFragment = new StartDatePicker();
                newFragment.show(getSupportFragmentManager(), "StartDatePicker");
            }
        });

        //Button End
        btnEnd.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                DialogFragment newFragment = new EndDatePicker();
                newFragment.show(getSupportFragmentManager(), "EndDatePicker");
            }
        });


    }

    // called from StartDatePicker onDateSet method
    public void onDisplayStartDate(Calendar someStartDate)
    {
        // save the start date
        calendarStartDate = someStartDate;

        // display the date in the button
        btnStart.setText(DateFormat.format("E MM/dd/yyyy", calendarStartDate));

        // call the daysBetween method and display result
        tvOutput.setText("Days Between: " + daysBetween(calendarStartDate, calendarEndDate));
    }

    // called from StartDatePicker onDateSet method
    public void onDisplayEndDate(Calendar someEndDate)
    {
        // save the start date
        calendarEndDate = someEndDate;

        // display the date in the button
        btnEnd.setText(DateFormat.format("E MM/dd/yyyy", calendarEndDate));

        // call the daysBetween method and display result
        tvOutput.setText("Days Between: " + daysBetween(calendarStartDate, calendarEndDate));
    }




    private int daysBetween(Calendar d1, Calendar d2)
    {
        long dayone = calendarStartDate.getTimeInMillis();
        long daytwo = calendarEndDate.getTimeInMillis();


        long differenceBetween = daytwo - dayone;

        int diffInDays = (int)(differenceBetween / (24 * 60 * 60 * 1000));

        System.out.println("Difference in Days : " + diffInDays);

        return diffInDays;
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
