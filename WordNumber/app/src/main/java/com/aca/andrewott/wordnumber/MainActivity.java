package com.aca.andrewott.wordnumber;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText mEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button convertButton = (Button) findViewById(R.id.convertButton);
        mEditText = (EditText) findViewById(R.id.editText);
        TextView resultText = (TextView) findViewById(R.id.resultText);

        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                long num = 0;
                char firstChar = mEditText.getText().toString().toLowerCase().charAt(0);
                char secondChar = mEditText.getText().toString().toLowerCase().charAt(1);

                switch (firstChar) {
                    case 'o':
                        num = 1L;
                        break;
                    case 't':
                        if (secondChar == 'w') {
                            num = 2L;
                        } else if (secondChar == 'h') {
                            num = 3L;
                        } else if (secondChar == 'e') {
                            num = 10L;
                        }
                        break;
                    case 'f':
                        if (secondChar == 'o') {
                            num = 4L;
                        } else if (secondChar == 'i') {
                            num = 5L;
                        }
                        break;
                    case 's':
                        if (secondChar == 'i') {
                            num = 6L;
                        } else if (secondChar == 'e')
                            num = 7L;
                        break;
                    case 'e':
                        num = 8L;
                        break;
                    case 'n':
                        num = 9L;
                        break;
                        }
                }
            });
    }

}
