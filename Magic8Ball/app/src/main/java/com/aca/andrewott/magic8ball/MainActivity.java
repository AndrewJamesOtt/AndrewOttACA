package com.aca.andrewott.magic8ball;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    String primaryList[] = {"SUROS Regime","The Last Word","Eidolon Ally",
            "Righteous VII","Hawksaw","Grim Citizen","Eysaluna","Hawkmoon",
            "The First Curse","Thorn","SUROS PDX-45","Stranger's Rifle",
            "Arminius-D","Does Not Bow","Red Spectre","Atheon's Epilogue",
            "Shadow Price","Her Memory","Anguish of Drystan","Wolfslayers Claw",
            "Abyss Defiant","An Answering Chord","The Dealbreaker","Haakon's Hatchet",
            "Up For Anything","Her Right Hand","Soulstealer's Claw","Doctor Nope",
            "Doctrine of Passing","Righteous VII","Vanquisher VIII","SUROS ARI-41",
            "For The People","Zarinaea-D","Antipodal Hindsight","Black Parallax AS"};

    String secondaryList[] = {"Omolon Thesan FR4","Long Far Gone","Panta Rhei",
            "Hitchhiker FR4","The Vortex","The Vacancy","Elevating Vision",
            "Ashraven's Flight","Midha's Reckoning","Techeun Rage","Darkblade's Spite",
            "Susanoo","The Crash","Hide and Seek-42","The Comedian","Found Verdict",
            "Invisible Hand M7","Judgment VI","Felwinter's Lie","Two To The Morgue",
            "Secret Handshake","Hard Luck Charm","Party Crasher +1","Matador 64",
            "Dry Rot 32", "Rude Awakening DOA","Swordbreaker","Wolfborne Oath",
            "Her Courtesy","Astral Horizon","Invective","Universal Remote","The 4th Horseman",
            "Lord of Wolves","Final Boss","Epitaph 2261","The Chosen","Broken Truth LR1",
            "Efrideet's Spear","Prudence II","Violator XII","Havoc Pigeon"};

    String heavyList[] = {"Diluvian 10/4x","Ruin Wake","The Variable","Objection IV",
            "The Unseeing Eye","Bretomart's Stand","Qullium's Terminus","Bane of the Taken",
            "Baron's Ambition","Choleric Dragon SRT-49","The Smolder","Ceres Lost BMJ-46",
            "The Vertigo","The Tamarind","Tormod's Bellows","Elulium's Frenzy","The Nightmare",
            "SUROS JLB-42","SUROS JLB-47","The Hothead","Arc Edge","Arc Infinite Edge","Sol Edge",
            "Solar Infinite Edge","Void Edge","Void Infinite Edge","Dreadfang","Bolt-Caster",
            "Dark-Drinker","Raze-Lighter"};


    String diceOne[] = {"Decisive Victory","You've Won","Close-Call","Barely Lost","Try Harder","You Bad Fam"};

    String diceTwo[] = {"+2 Agility","+2 Intellect","+2 Strength","-2 Agility","-2 Intellect","-2 Strength"};

    // 1st "dice"
    TextView mTxtDiceRollOne;
    TextView mTxtFirstRoll;
    Button mButtonDiceRoll;

    // 2nd "dice"
    TextView mTxtDiceRollTwo;
    TextView mTxtSecondRoll;
    Button mButtonDiceRoll2;

    // for primary weapon display
    TextView mTxtPrimary;
    Button mBtnShowPrimary;

    // for secondary weapon display
    TextView mTxtSecondary;
    Button mBtnShowSecondary;

    // for heavy weapon display
    TextView mTxtHeavy;
    Button mBtnShowHeavy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // primary
        mTxtPrimary = (TextView) findViewById(R.id.txtPrimary);
        mBtnShowPrimary = (Button) findViewById(R.id.btnShowPrimary);

        // secondary
        mTxtSecondary = (TextView) findViewById(R.id.txtSecondary);
        mBtnShowSecondary = (Button) findViewById(R.id.btnShowSecondary);

        // heavy
        mTxtHeavy = (TextView) findViewById(R.id.txtHeavy);
        mBtnShowHeavy = (Button) findViewById(R.id.btnShowHeavy);

        mTxtDiceRollOne = (TextView) findViewById(R.id.txtDiceRollOne);
        mTxtFirstRoll = (TextView) findViewById(R.id.txtFirstRoll);
        mButtonDiceRoll = (Button) findViewById(R.id.buttonDiceRoll);

        mTxtDiceRollTwo = (TextView) findViewById(R.id.txtDiceRollTwo);
        mTxtSecondRoll = (TextView) findViewById(R.id.txtSecondRoll);
        mButtonDiceRoll2 = (Button) findViewById(R.id.buttonDiceRoll2);

        // onclick for primary
        mBtnShowPrimary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view) {
                int index = new Random().nextInt(primaryList.length);
                mTxtPrimary.setText(primaryList[index]);
            }});
        // onclick for secondary. replace "sweatyList" with secondary string
        mBtnShowSecondary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view) {
                int index = new Random().nextInt(secondaryList.length);
                mTxtSecondary.setText(secondaryList[index]);
            }});
        // onclick for heavy
        mBtnShowHeavy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view) {
                int index = new Random().nextInt(heavyList.length);
                mTxtHeavy.setText(heavyList[index]);
            }});
        // onclick for perks and buffs
        mButtonDiceRoll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view) {
                int index = new Random().nextInt(diceOne.length);
                mTxtDiceRollOne.setText(diceOne[index]);
            }});
        mButtonDiceRoll2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view) {
                int index2 = new Random().nextInt(diceTwo.length);
                mTxtDiceRollTwo.setText(diceTwo[index2]);
            }});




        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
