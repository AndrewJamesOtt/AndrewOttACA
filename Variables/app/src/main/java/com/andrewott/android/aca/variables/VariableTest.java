package com.andrewott.android.aca.variables;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class VariableTest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_variable_test);

        int sparkPlugs = 8;
        boolean doesItRun = true;
        byte tires = 4;
        short gears = 6;
        long milesDriven = 100890;
        float pistonDepthInches = 16;
        char bodyWidth = 60;
        double trimWidth = 3.1;


        System.out.println("Spark plugs in action: " + sparkPlugs);
        System.out.println("Does it haul ass?: " + doesItRun);
        System.out.println("Tires rolling: " + tires);
        System.out.println("Gears available: " + gears);
        System.out.println("Miles this baby has gone: " + milesDriven);
        System.out.println("How deep dem pistons iz: " + pistonDepthInches);
        System.out.println("How big dat booty be: " + bodyWidth);
        System.out.println("Width of dat body kit: " + trimWidth);

    }
}
