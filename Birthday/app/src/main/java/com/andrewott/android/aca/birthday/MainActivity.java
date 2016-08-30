package com.andrewott.android.aca.birthday;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public class Test{
        public static void main(String args[]){
            String Str = new String("Welcome to Tutorialspoint.com");

            System.out.print("Return Value :" );
            System.out.println(Str.substring(10) );

            System.out.print("Return Value :" );
            System.out.println(Str.substring(10, 15) );



}
