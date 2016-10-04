package com.aca.andrewott.devicedetection;

import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // 1) hold references to our textview widgets
    private TextView txtOrientation;
    private TextView txtResolution;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 2) get a reference to our TextView widgets
        txtOrientation = (TextView) findViewById(R.id.txtOrientation);
        txtResolution = (TextView) findViewById(R.id.txtResolution);
    }

    // 3) method that handles our button and runs detection code
    public void detectDevice(View v){

        // 3a) what is the orientation?
        Display display = getWindowManager().getDefaultDisplay();
        txtOrientation.setText("" + display.getRotation());

        // 3b) what is the resolution?
        Point xy = new Point();
        display.getSize(xy);
        txtResolution.setText("x = " + xy.x + "y = " + xy.y);

}}
