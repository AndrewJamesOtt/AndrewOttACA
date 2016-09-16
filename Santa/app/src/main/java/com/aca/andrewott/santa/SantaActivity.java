package com.aca.andrewott.santa;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class SantaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_santa);
    }

    public void processClicks(View display) {
        Intent action = null;
        int id = display.getId();

        switch (id) {
            case (R.id.imageButton):
                action = new Intent(Intent.ACTION_DIAL,
                        Uri.parse("tel:877-446-6273"));
                break;
            case (R.id.imageButton2):
                action = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://www.noradsanta.org"));
                break;
            case (R.id.imageButton3):
                action = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("geo:0,0?q=101 Saint Nicholas Dr.," + " North Pole, AK"));
                break;
            default:
                break;
        }
        startActivity(action);
    }
}