package com.fire.quotely.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fire.quotely.R;
import com.fire.quotely.adapters.TitleAdapter;
import com.fire.quotely.etc.IntentConstants;
import com.fire.quotely.models.Quote;
import com.fire.quotely.models.Title;
import com.fire.quotely.services.DeviceBootReceiver;
import com.fire.quotely.ui.activities.AddTitleActivity;
import com.fire.quotely.ui.activities.QuotesActivity;
import com.fire.quotely.ui.activities.SignInActivity;
import com.fire.quotely.ui.dialogs.QuoteDialog;
import com.fire.quotely.ui.dialogs.TitleOptionDialog;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

public class TitleFragment extends Fragment implements TitleAdapter.TitleAdapterListener {
    @BindView(R.id.title_recyclerview)
    RecyclerView mTitleRecyclerView;
    private RealmResults<Title> mTitles;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_title, container, false);
        ButterKnife.bind(this, view);



        Intent bootIntent = new Intent(getContext(), DeviceBootReceiver.class);
        getActivity().getApplicationContext().sendBroadcast(bootIntent);

        Realm realm = Realm.getDefaultInstance();

        mTitles = realm.where(Title.class)
                .equalTo("isChosen", true)
                .findAllSorted("title", Sort.ASCENDING);

        mTitleRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mTitleRecyclerView.setAdapter(new TitleAdapter(mTitles, this));

        if (mTitles.size() > 0) {
            int position = (int) (Math.random() * mTitles.size());
            String title = mTitles.get(position).getTitle();

            RealmResults<Quote> quotes = realm.where(Quote.class).equalTo("title", title).findAll();

            position = (int) (Math.random() * quotes.size());
            String quote = quotes.get(position).getQuote();

            QuoteDialog dialog = new QuoteDialog(title, quote);
            dialog.show(getChildFragmentManager(), null);
        }
        return view;
    }


    @OnClick(R.id.fab)
    void addTitle() {
        startActivity(new Intent(getContext(), AddTitleActivity.class));
    }

    @Override
    public void onTitleClick(String title) {
        Intent intent = new Intent(getContext(), QuotesActivity.class);
        intent.putExtra(IntentConstants.EXTRA_TITLE, title);
        startActivity(intent);
    }

    @Override
    public boolean onTitleLongClick(String title) {
        TitleOptionDialog dialog = new TitleOptionDialog();
        dialog.setListener(() -> title);
        dialog.show(getChildFragmentManager(), null);
        return true;
    }
}
