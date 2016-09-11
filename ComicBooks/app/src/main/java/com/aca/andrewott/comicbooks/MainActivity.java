package com.aca.andrewott.comicbooks;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    EditText mTitleInput;
    EditText mIssueNumberInput;
    EditText mConditionInput;
    EditText mBasePriceInput;
    TextView mResultsText;
    Button mAddButton;

    Comic[] comix = new Comic[4];  // Set up a Comic array that will hold 3 comics

    //Set up hash map
    HashMap quality = new HashMap();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        float price1 = 3.00F;
        quality.put("mint", price1);

        float price2 = 2.00F;
        quality.put("near mint", price2);

        float price3 = 1.50F;
        quality.put("very fine", price3);

        float price4 = 1.00F;
        quality.put("fine", price4);

        float price5 = 0.50F;
        quality.put("good", price5);

        float price6 = 0.25F;
        quality.put("poor", price6);

        mTitleInput = (EditText) findViewById(R.id.titleInput);
        mIssueNumberInput = (EditText) findViewById(R.id.issueNumberInput);
        mConditionInput = (EditText) findViewById(R.id.conditionInput);
        mBasePriceInput = (EditText) findViewById(R.id.basePriceInput);
        mResultsText = (TextView) findViewById(R.id.outputText);
        mAddButton = (Button) findViewById(R.id.addButton);

        mAddButton.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {

                String title = mTitleInput.getText().toString();
                String issueNumber = mIssueNumberInput.getText().toString();
                String condition = mConditionInput.getText().toString();
                Float basePrice = Float.parseFloat(mBasePriceInput.getText().toString());

                comix[3] = new Comic(title, issueNumber, condition, basePrice);
                comix[3].setPrice( (Float) quality.get(comix[3].condition));

                for (int i = 0; 1 < comix.length; i++) {
                    mResultsText.append("Title: " + comix[i].title + "\n");
                    mResultsText.append("Issue Number " + comix[i].issueNumber + "\n");
                    mResultsText.append("Condition " + comix[i].condition + "\n");
                    mResultsText.append("Base Price " + comix[i].basePrice + "\n");
                }
            }
        });


        Comic[] comix = new Comic[3];

        // Add comics to the collection
        comix[0] = new Comic("Amazing Spider-Man", "1A", "very fine", 12_000.00F);
        comix[0].setPrice( (Float) quality.get(comix[0].condition));

        comix[1] = new Comic("The Incredible Hulk", "181", "near mint", 680.00F);
        comix[1].setPrice( (Float) quality.get(comix[1].condition));

        comix[2] = new Comic("Cerebus", "1A", "good", 190.00F);
        comix[2].setPrice( (Float) quality.get(comix[2].condition));

    }
}

class Comic {
    String title;
    String issueNumber;
    String condition;
    float basePrice;
    float price;

    Comic (String inTitle, String inIssueNumber, String inCondition, float inBasePrice) {
        title = inTitle;
        issueNumber = inIssueNumber;
        condition = inCondition;
        basePrice = inBasePrice;
    }

    void setPrice(float factor) { price = basePrice * factor; }


}