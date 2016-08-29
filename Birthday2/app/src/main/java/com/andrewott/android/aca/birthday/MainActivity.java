package com.andrewott.android.aca.birthday;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String birthday = new String("02/15/1995");

        System.out.print("Month : ");
        System.out.println(birthday.substring(0, 2));

        System.out.print("Day : ");
        System.out.println(birthday.substring(3, 5));

        System.out.print("Year : ");
        System.out.println(birthday.substring(6));


        CakeSize bigCake = new CakeSize();



        bigCake.height = 71;
        bigCake.weight = 150;
        bigCake.depth = 34;

        bigCake.showAttributes();






    }

}
