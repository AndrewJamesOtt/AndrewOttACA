package com.aca.andrewott.simplefragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_layout);

        // get a fragment manager
        FragmentManager fManager = getFragmentManager();

        // create a fragment using the manager
        // Passing the id of the layout to hold it
        Fragment frag = fManager.findFragmentById(R.id.fragmentHolder);

        // Check that the fragment has not already been initialized
        if (frag == null){

            // initialize the fragment based on our SimpleFragment
            frag = new SimpleFragment();
            fManager.beginTransaction()
                    .add(R.id.fragmentHolder, frag)
                    .commit();
        }
    }
}
