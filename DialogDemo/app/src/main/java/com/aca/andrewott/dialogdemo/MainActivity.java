package com.aca.andrewott.dialogdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // We only handle one button
                // So no switching required
                MyDialog myDialog = new MyDialog();
                myDialog.show(getFragmentManager(), "123");
                // This calls onCreateDialog
                // Don't worry about the strange looking 123
                // We will find out about this later
            }
        });

    }
}
