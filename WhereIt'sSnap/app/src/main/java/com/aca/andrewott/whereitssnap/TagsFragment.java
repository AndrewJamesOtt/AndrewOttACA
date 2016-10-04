package com.aca.andrewott.whereitssnap;

import android.app.ListFragment;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.SimpleCursorAdapter;

/**
 * Created by andrewott on 9/26/16.
 */
public class TagsFragment extends ListFragment{

    private ActivityComs mActivityComs;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DataManager d = new DataManager(getActivity().getApplicationContext());
        Cursor c = d.getTags();

        // Create a new adapter
        SimpleCursorAdapter cursorAdapter = new SimpleCursorAdapter(getActivity(),
                android.R.layout.simple_list_item_1, c,
                new String[] { DataManager.TABLE_ROW_TAG },
                new int[] { android.R.id.text1 }, 0);

        // Attach the Cursor to the adapter
        setListAdapter(cursorAdapter);
    }
}
