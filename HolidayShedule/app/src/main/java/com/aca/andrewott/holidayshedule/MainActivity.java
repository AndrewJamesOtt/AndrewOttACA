package com.aca.andrewott.holidayshedule;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.BitSet;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        HolidaySchedule cal = new HolidaySchedule();
        String day = "54";

        if (!day.equals("")) {
            try {
                int whichDay = Integer.parseInt(day);
                if (cal.isHoliday(whichDay)) {
                    System.out.println("Day Number " + day + " is a holiday, is it not?");
                } else {
                    System.out.println("Day Number " + day + " isn't really a holiday, but we can pretend...");
                }
            } catch (NumberFormatException nfe) {
                System.out.println("Error yo: " + nfe.getMessage());
            }
        }
    }

    public class HolidaySchedule {
        BitSet sked;

        public HolidaySchedule(){
            sked = new BitSet(365);
            int[] holiday = { 1, 15, 50, 148, 185, 246, 281, 316, 326, 359 };

            for (int i = 0; i < holiday.length; i++) {
                addHoliday(holiday[i]);
            }
        }

        public void addHoliday(int dayToAdd) {
            sked.set(dayToAdd);
        }

        public boolean isHoliday(int dayToCheck) {
            return sked.get(dayToCheck);
        }
    }






















}
