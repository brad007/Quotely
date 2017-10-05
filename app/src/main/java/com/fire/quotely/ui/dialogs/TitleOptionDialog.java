package com.fire.quotely.ui.dialogs;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import com.fire.quotely.R;
import com.fire.quotely.models.Title;

import io.realm.Realm;

/**
 * Created by LLL-Brad on 10/3/2017.
 */

public class TitleOptionDialog extends DialogFragment {

    public static final int DELETE = 0;
    private TitleOptionDialogListener mListener;

    public interface TitleOptionDialogListener {
        String onTitleDelete();
    }

    public void setListener(TitleOptionDialogListener listener) {mListener = listener;}


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Title Options");
        builder.setItems(R.array.title_options, (dialogInterface, i) -> {
            switch (i) {
                case DELETE:
                    Realm realm = Realm.getDefaultInstance();
                    realm.executeTransaction(realm1 -> realm.where(Title.class)
                            .equalTo("title", mListener.onTitleDelete())
                            .findFirst().setChosen(false));
                    break;
            }
        });
        builder.setNegativeButton("Cancel", (dialogInterface, i) -> {
        });
        return builder.create();
    }
}
