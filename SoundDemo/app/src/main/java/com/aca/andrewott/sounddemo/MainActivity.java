package com.aca.andrewott.sounddemo;

import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

        /*
        To code the Sound Demo app, we first add a few member variables
        for our SoundPool, sound FX IDs, and nowPlaying int as previously
        discussed, and we will also add float to hold a value for the
        volume between 0 (silent) and 1 (full volume relative to the current
        volume of the device). We will also add int called repeats, which
        unsurprisingly holds the value of the number of times we will repeat
        a given sound FX:
        */

    int idFX1 = -1;
    int idFX2 = -1;
    int idFX3 = -1;
    int nowPlaying = -1;

    float volume = .1f;
    int repeats = 2;

    SoundPool sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        Now, in onCreate, we can get a reference and set a click
        listener for our buttons in the usual way:
         */

        Button buttonFX1 = (Button) findViewById(R.id.btnFX1);
        buttonFX1.setOnClickListener(this);

        Button buttonFX2 = (Button) findViewById(R.id.btnFX2);
        buttonFX2.setOnClickListener(this);

        Button buttonFX3 = (Button) findViewById(R.id.btnFX3);
        buttonFX3.setOnClickListener(this);

        Button buttonStop = (Button) findViewById(R.id.btnStop);
        buttonStop.setOnClickListener(this);

        /*
        Still, in onCreate, we can initialize SoundPool (sp) based on
        the version of Android that the device is using:
         */

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();

            sp = new SoundPool.Builder()
                    .setMaxStreams(5)
                    .setAudioAttributes(audioAttributes)
                    .build();
        } else {
            sp = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        }
        /*
        Next, we load each sound FX in turn and initialize our IDs with a value that
        points to the related sound FX that we loaded into SoundPool. The whole thing
        is wrapped in a try-catch block as required:
         */
        try {
            // Create objects of the 2 required classes
            AssetManager assetManager = this.getAssets();
            AssetFileDescriptor descriptor;

            // Load our fx in memory ready for use
            descriptor = assetManager.openFd("fx1.ogg");
            idFX1 = sp.load(descriptor, 0);

            descriptor = assetManager.openFd("fx2.ogg");
            idFX2 = sp.load(descriptor, 0);

            descriptor = assetManager.openFd("fx3.ogg");
            idFX3 = sp.load(descriptor, 0);


        } catch (IOException e) {
            // Print an error message to the console
            Log.e("error", "failed to load sound files");
        }
        /*
        We only need to add code to the onProgressChanged method. Within this method,
        we simply change the value of our volume variable and then use the setVolume
        method on our SoundPool object, passing in the currently playing sound FX and
        the volume of the left and right channels of sound:
         */

        // Now setup the seekbar
        SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int value, boolean fromUser) {
                volume = value / 10f;
                sp.setVolume(nowPlaying, volume, volume);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        /*
        After SeekBar comes Spinner and yet another anonymous class which is used to handle
        user interaction. We use AdapterView.OnItemSelectedListener to override the
        onItemSelected and onNothingSelected methods. All our code goes in the onItemSelected
        method, which creates a temporary String named temp and then uses the Integer.ValueOf
        method to convert String to int, which we can use to initialize the repeats variable:
         */

        // Now for the spinner
        final Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String temp = String.valueOf(spinner.getSelectedItem());
                repeats = Integer.valueOf(temp);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });

        /*
        Now, implement the onClick method. Quite simply, there is a case statement for each button.
        There is a case to play each of our three sound FX, set the volume and set the number of times to repeat a sound.
        Note that the return value for each call to play is stored in nowPlaying.
        When the user clicks on the STOP button, we simply call stop with the current value of nowPlaying,
        causing the most recently started sound FX to stop, as shown in the following code:
         */
    }
        @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnFX1:
                sp.stop(nowPlaying);
                nowPlaying = sp.play(idFX1, volume, volume, 0, repeats, 1);
                break;

            case R.id.btnFX2:
                sp.stop(nowPlaying);
                nowPlaying = sp.play(idFX2, volume, volume, 0, repeats, 1);
                break;

            case R.id.btnFX3:
                sp.stop(nowPlaying);
                nowPlaying = sp.play(idFX3, volume, volume, 0, repeats, 1);
                break;

            case R.id.btnStop:
                sp.stop(nowPlaying);
                break;
        }
    }
    }

