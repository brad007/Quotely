package com.fire.quotely.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.fire.quotely.R;
import com.fire.quotely.models.Quote;

import io.realm.RealmResults;

/**
 * Created by LLL-Brad on 10/3/2017.
 */

public class QuoteDeckAdapter extends BaseAdapter {

    private RealmResults<Quote> data;
    private Context context;

    public QuoteDeckAdapter(RealmResults<Quote> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = view;
        if (v == null) {
            LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
            // normally use a viewholder
            v = inflater.inflate(R.layout.item_quote, viewGroup, false);
        }



        TextView quoteTextView = v.findViewById(R.id.quote_textview);
        quoteTextView.setText(data.get(i).getQuote());
        return v;
    }
}
