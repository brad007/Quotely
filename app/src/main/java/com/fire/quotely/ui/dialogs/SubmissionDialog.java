package com.fire.quotely.ui.dialogs;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.fire.quotely.R;
import com.fire.quotely.etc.FirebaseReferences;
import com.fire.quotely.models.Submission;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by LLL-Brad on 10/5/2017.
 */

public class SubmissionDialog extends DialogFragment {
    @BindView(R.id.title_edittext)
    EditText mTitleEditText;
    @BindView(R.id.tagline_edittext)
    EditText mTagLineEditText;
    @BindView(R.id.acronym_edittext)
    EditText mAcronymEditText;


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Add Submission");
        builder.setPositiveButton("Submit", (dialogInterface, i) -> {
            String title = mTitleEditText.getText().toString();
            String tagline = mTagLineEditText.getText().toString();
            String acronym = mAcronymEditText.getText().toString();
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

            Submission submission = new Submission();
            submission.setTitle(title);
            submission.setTagline(tagline);
            submission.setAcronym(acronym);
            submission.setUser(null);
            submission.setUpVote(0);
            submission.setDownVote(0);
            submission.setTotalVote(0);
            submission.setId("abc");


            FirebaseReferences.submissionsReference().add(submission);

        });

        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_submission, null,
                false);

        ButterKnife.bind(this, view);
        builder.setView(view);
        return builder.create();
    }
}
