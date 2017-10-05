package com.fire.quotely.adapters;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.fire.quotely.R;
import com.fire.quotely.models.Title;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.OrderedRealmCollection;
import io.realm.Realm;
import io.realm.RealmRecyclerViewAdapter;

/**
 * Created by LLL-Brad on 10/3/2017.
 */

public class AddTitlesAdapter extends RealmRecyclerViewAdapter<Title, AddTitlesAdapter.AddTitleViewHolder> {
    public AddTitlesAdapter(@Nullable OrderedRealmCollection<Title> data) {
        super(data, true);
    }

    @Override
    public AddTitleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_add_title,
                parent, false);
        return new AddTitleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AddTitleViewHolder holder, int position) {
        Title title = getItem(position);
        holder.titleTextView.setText(title.getTitle());
        holder.titleQuoteTextView.setText(title.getQuote());
        holder.isChosenCheckBox.setChecked(title.isChosen());
        holder.isChosenCheckBox.setOnCheckedChangeListener((compoundButton, b) -> {
            Realm realm = Realm.getDefaultInstance();
            realm.executeTransaction(realm1 -> {
                title.setChosen(b);
            });
        });
    }

    public static class AddTitleViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.is_chosen_checkbox)
        CheckBox isChosenCheckBox;
        @BindView(R.id.title_textview)
        TextView titleTextView;
        @BindView(R.id.title_quote_textview)
        TextView titleQuoteTextView;

        public AddTitleViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
