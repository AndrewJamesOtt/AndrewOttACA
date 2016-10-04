package com.aca.andrewott.notetoself;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;

/**
 * Created by andrewott on 9/15/16.
 */
public class DialogNewNote extends DialogFragment {

    private static final int CAMERA_REQUEST = 123;
    private ImageView mImageViewPhoto;

    // The filepath for the photo
    String mCurrentPhotoPath;

    // Where the captured image is stored
    private Uri mImageUri = Uri.EMPTY;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // All the code goes here
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_new_note, null);

        final EditText editTitle = (EditText)
                dialogView.findViewById(R.id.editTitle);
        final EditText editDescription = (EditText)
                dialogView.findViewById(R.id.editDescription);
        final CheckBox checkBoxIdea = (CheckBox)
                dialogView.findViewById(R.id.checkBoxIdea);
        final CheckBox checkBoxTodo = (CheckBox)
                dialogView.findViewById(R.id.checkBoxTodo);
        final CheckBox checkBoxImportant = (CheckBox)
                dialogView.findViewById(R.id.checkBoxImportant);

        Button btnCancel = (Button) dialogView.findViewById(R.id.btnCancel);
        Button btnOK = (Button) dialogView.findViewById(R.id.btnOK);

        builder.setView(dialogView).setMessage("Add a new note");

        // handle the cancel button.
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        // handle the OK button
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // create a new note
                Note newNote = new Note();

                // set its variable to match the users entries on the form
                newNote.setTitle(editTitle.getText().toString());
                newNote.setDescription(editDescription.getText().toString());
                newNote.setIdea(checkBoxIdea.isChecked());
                newNote.setTodo(checkBoxTodo.isChecked());
                newNote.setImportant(checkBoxImportant.isChecked());

                // reference to MainActivity
                MainActivity callingActivity = (MainActivity) getActivity();

                // pass newNote back to MainActivity
                callingActivity.createNewNote(newNote);

                // quit the dialog
                dismiss();
            }
        });
        return builder.create();
    }




















}
