package com.example.jkozlevcar.lab3;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class PreferenceActivity extends AppCompatActivity {

    // declare Java references
    private TextView tvDefaultTip;
    private SeekBar sbDefaultTip;
    private Button btnSavePreference;

    // references needed for shared preferences
    private SharedPreferences preferenceSettings;
    private SharedPreferences.Editor preferenceEditor;
    private static final int PREFERENCE_MODE_PRIVATE = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preference);

        // create Java objects and tie to UI
        tvDefaultTip = (TextView) findViewById(R.id.tvDefaultTip);
        sbDefaultTip = (SeekBar) findViewById(R.id.sbDefaultTip);
        btnSavePreference = (Button) findViewById(R.id.btnSavePreference);

        // set onSeekBarChangeListener
        sbDefaultTip.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
            {
                tvDefaultTip.setText("Default Tip: " + progress + "%");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar)
            {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar)
            {

            }


        });

        // set up preferences with name and in private mode
        preferenceSettings = getSharedPreferences("lab5", PREFERENCE_MODE_PRIVATE);
        preferenceEditor = preferenceSettings.edit();

        // get the data from the shared preferences with default
        // set the default tip in the seek bar
        sbDefaultTip.setProgress(preferenceSettings.getInt("DEFAULT_TIP", 10));

        // set save preference onClickListener
        btnSavePreference.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // set the preference
                preferenceEditor.putInt("DEFAULT_TIP", sbDefaultTip.getProgress());
                preferenceEditor.apply();

                // finish the activity
                finish();
            }
        });
    }







    }


