package com.fire.quotely.adapters;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fire.quotely.R;
import com.fire.quotely.models.Title;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.OrderedRealmCollection;
import io.realm.RealmRecyclerViewAdapter;

/**
 * Created by LLL-Brad on 10/3/2017.
 */

public class TitleAdapter extends RealmRecyclerViewAdapter<Title, TitleAdapter.TitleViewHolder> {

    private TitleAdapterListener mListener;

    public interface TitleAdapterListener {
        void onTitleClick(String title);

        boolean onTitleLongClick(String title);
    }

    public TitleAdapter(@Nullable OrderedRealmCollection<Title> data, TitleAdapterListener listener) {
        super(data, true);
        mListener = listener;
    }

    @Override
    public TitleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_title, parent, false);

        return new TitleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TitleViewHolder holder, int position) {
        final Title title = getItem(position);
        holder.titleTextView.setText(title.getTitle());
        holder.titleQuoteTextView.setText(title.getQuote());
        holder.titleLogoTextView.setText(title.getAcrynom());
        holder.itemTitle.setOnClickListener(view -> mListener.onTitleClick(title.getTitle()));

        holder.itemTitle.setOnLongClickListener(view -> mListener.onTitleLongClick(title.getTitle()));


    }

    public static class TitleViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_title)
        LinearLayout itemTitle;
        @BindView(R.id.title_logo_textview)
        TextView titleLogoTextView;
        @BindView(R.id.title_textview)
        TextView titleTextView;
        @BindView(R.id.title_quote_textview)
        TextView titleQuoteTextView;

        public TitleViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setTitle(String title) {
            titleTextView.setText(title);
        }

        public void setQuote(String quote) {
            titleQuoteTextView.setText(quote);
        }

        public void setLogo(String logo) {
            titleLogoTextView.setText(logo);
        }

    }
}
