package com.aca.andrewott.notetoself;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // animation member variables
    Animation mAnimFlash;
    Animation mFadeIn;

    private NoteAdapter mNoteAdapter;
    private boolean mSound;
    private int mAnimOption;
    private SharedPreferences mPrefs;

    @Override
    protected void onPause(){
        super.onPause();

        mNoteAdapter.saveNotes();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNoteAdapter = new NoteAdapter();
        ListView listNote = (ListView) findViewById(R.id.listView);
        listNote.setAdapter(mNoteAdapter);

        listNote.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            // handle clicks on the listview
            @Override
            public void onItemClick (AdapterView<?> adapter, View view, int whichItem, long id) {
            /*
            create a temporary note
            which has reference to the Note
            that has just been clicked
             */
                Note tempNote = mNoteAdapter.getItem(whichItem);

                // new dialog window
                DialogShowNote dialog = new DialogShowNote();
                //send in a new reference to the note
                dialog.sendNoteSelected(tempNote);
                // show the dialog window with the note in it
                dialog.show(getFragmentManager(), "");
            }
        });
    }



    public void createNewNote(Note n) {
        mNoteAdapter.addNote(n);
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
            Intent intent = new Intent(this,SettingsActivity.class);
            startActivity(intent);
            return true;
        }

        if (id == R.id.action_add) {
            DialogNewNote dialog = new DialogNewNote();
            dialog.show(getFragmentManager(), "456");
            return true;
        }


        return super.onOptionsItemSelected(item);
    }
    public class NoteAdapter extends BaseAdapter {

        // call to save our users data
        public void saveNotes(){
            try{
                mSerializer.save(noteList);
            }catch(Exception e){
                Log.e("Error Saving Notes","", e);
            }
        }
        private JSONSerializer mSerializer;
        List<Note> noteList = new ArrayList<Note>();
        public NoteAdapter(){

            mSerializer = new JSONSerializer("NoteToSelf.json",
                    MainActivity.this.getApplicationContext());

            try {
                noteList = mSerializer.load();
            } catch (Exception e) {
                noteList = new ArrayList<>();
                Log.e("Error loading notes: ", "", e);
            }

        }


        @Override
        public int getCount() {
            return noteList.size();
        }
        @Override
        public Note getItem(int whichItem) {
            return noteList.get(whichItem);
        }
        @Override
        public long getItemId(int whichItem) {
            return whichItem;
        }
        @Override
        public View getView(int whichItem, View view, ViewGroup viewGroup) {
            // Has view been inflated already
            if(view == null){

                // If not, do so here
                // First create a LayoutInflater
                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                // Now instantiate view using inflater.inflate
                // using the listitem layout
                view = inflater.inflate(R.layout.listitem, viewGroup,false);
                // The false parameter is necessary
                // because of the way that we want to use listitem

            }// End if
            // grab a reference to all our TextView and ImageView widgets
            TextView txtTitle = (TextView) view.findViewById(R.id.txtTitle);
            TextView txtDescription = (TextView) view.findViewById(R.id.txtDescription);
            ImageView ivImportant = (ImageView) view.findViewById(R.id.imageViewImportant);
            ImageView ivTodo = (ImageView) view.findViewById(R.id.imageViewTodo);
            ImageView ivIdea = (ImageView) view.findViewById(R.id.imageViewIdea);

            // hides any ImageView widgets that are not relevant
            Note tempNote = noteList.get(whichItem);

            /*
            Now, we just need to apply the appropriate animation to the appropriate part of our UI.
            We can do so in the NoteAdapter inner class in the getView method, just after we
            initialize tempNote with the details of the note we are currently dealing with.
            We are then in a position to call isImportant to make a decision about which animation
            to play.
             */
            if (tempNote.isImportant() && mAnimOption != SettingsActivity.NONE) {
                view.setAnimation(mAnimFlash);
            }else{
                view.setAnimation(mFadeIn);
            }

            if(!tempNote.isImportant()){
                ivImportant.setVisibility(View.GONE);
            }
            if (!tempNote.isTodo()){
                ivTodo.setVisibility(View.GONE);
            }
            if (!tempNote.isIdea()){
                ivIdea.setVisibility(View.GONE);
            }

            //add the text to the heading and description
            txtTitle.setText(tempNote.getTitle());
            txtDescription.setText(tempNote.getDescription());

            return view;
        }
        public void addNote(Note n){
            noteList.add(n);
            notifyDataSetChanged();
        }
    }
    @Override
    protected void onResume(){
        super.onResume();

        mPrefs = getSharedPreferences("Note to self", MODE_PRIVATE);
        mSound = mPrefs.getBoolean("sound", true);
        mAnimOption = mPrefs.getInt("anim option", SettingsActivity.FAST);

        /*
        Now, let's initialize these animations based on the user's current settings.
        The best place to do this is in onResume because that is where we load the
        settings, and it is guaranteed to run every time MainActivity is run, whether
        that is because the app has just started or because the user is just returning from
        the settings screen (perhaps having just changed the settings).
         */
        mAnimFlash = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.flash);
        mFadeIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);

        // Set the rate of flash based on settings
        if(mAnimOption == SettingsActivity.FAST){

            mAnimFlash.setDuration(100);
            Log.i("anim = ",""+ mAnimOption);
        }else if(mAnimOption == SettingsActivity.SLOW){

            Log.i("anim = ",""+ mAnimOption);
            mAnimFlash.setDuration(1000);
        }

        mNoteAdapter.notifyDataSetChanged();
    }

}
/*
We know how to animate widgets now, but what about shapes or images that I create myself?
ImageView can hold any image you like. Just add the image to the drawable folder and then
set the appropriate src property on the ImageView. You can then animate whatever image is
being shown in the ImageView widget.

What if I want more flexibility than this, more like a drawing app or even a game?
To implement this kind of functionality, you will need to learn another general
computing concept (threads) as well as some more Android classes (such as Paint, Canvas,
and SurfaceView). You will learn how to draw anything from a single pixel to shapes,
and then how to move them around the screen in a later lesson.
*/

/*
SUMMARY:
Now, we have another app-enhancing trick up our sleeves, and we have seen that animations
in Android are quite straightforward. We design an animation in XML and add the file to
the anim folder. Next, we get a reference to the animation in XML with an Animation object
in our Java code.

We can then use a reference to a widget in our UI and set an animation to it using
setAnimation and passing in the Animation object. We actually commence the animation by
calling startAnimation on the reference to the widget.
 */




















