package com.fire.quotely.ui.dialogs;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

/**
 * Created by LLL-Brad on 10/4/2017.
 */

@SuppressLint("ValidFragment")
public class QuoteDialog extends DialogFragment {

    private String mTitle;
    private String mQuote;

    public QuoteDialog(String title,String quote) {
        mTitle = title;
        mQuote = quote;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
       // View view = getActivity().getLayoutInflater().inflate(R.layout.item_quote, null, false);
       // TextView textView = view.findViewById(R.id.quote_textview);
       // textView.setText(mQuote);
        builder.setTitle(mTitle);
        builder.setMessage(mQuote);
       // builder.setView(view);
        return builder.create();
    }
}
