package com.aca.andrewott.imagegallery;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;
    PagerAdapter adapter;

    int[] images;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // references to images and put them in our array
        images = new int[] {
                R.drawable.image1,
                R.drawable.image2,
                R.drawable.image3,
                R.drawable.image4,
                R.drawable.image5,
                R.drawable.image6 };

        // get reference to the ViewPager in the layout
        viewPager = (ViewPager) findViewById(R.id.pager);

        // Initialize our PagerAdapter
        adapter = new ImagePagerAdapter(MainActivity.this, images);

        // bind the PagerAdapter to the ViewPager
        viewPager.setAdapter(adapter);
    }
}
