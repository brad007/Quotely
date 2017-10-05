package com.fire.quotely.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.fire.quotely.R;
import com.fire.quotely.adapters.TitleAdapter;
import com.fire.quotely.etc.IntentConstants;
import com.fire.quotely.models.Quote;
import com.fire.quotely.models.Title;
import com.fire.quotely.services.DeviceBootReceiver;
import com.fire.quotely.ui.dialogs.QuoteDialog;
import com.fire.quotely.ui.dialogs.TitleOptionDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

public class MainActivity extends AppCompatActivity implements TitleAdapter.TitleAdapterListener {
    @BindView(R.id.title_recyclerview)
    RecyclerView mTitleRecyclerView;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    private RealmResults<Title> mTitles;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        Intent bootIntent = new Intent(MainActivity.this, DeviceBootReceiver.class);
        getApplicationContext().sendBroadcast(bootIntent);
        Realm realm = Realm.getDefaultInstance();

        mTitles = realm.where(Title.class)
                .equalTo("isChosen", true)
                .findAllSorted("title", Sort.ASCENDING);

        mTitleRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mTitleRecyclerView.setAdapter(new TitleAdapter(mTitles, this));

        if (mTitles.size() > 0) {
            int position = (int) (Math.random() * mTitles.size());
            String title = mTitles.get(position).getTitle();

            RealmResults<Quote> quotes = realm.where(Quote.class).equalTo("title", title).findAll();

            position = (int) (Math.random() * quotes.size());
            String quote = quotes.get(position).getQuote();

            QuoteDialog dialog = new QuoteDialog(title, quote);
            dialog.show(getSupportFragmentManager(), null);
        }
    }

    @OnClick(R.id.fab)
    void addTitle() {
        startActivity(new Intent(MainActivity.this, AddTitleActivity.class));
    }

    @Override
    public void onTitleClick(String title) {
        Intent intent = new Intent(MainActivity.this, QuotesActivity.class);
        intent.putExtra(IntentConstants.EXTRA_TITLE, title);
        startActivity(intent);
    }

    @Override
    public boolean onTitleLongClick(String title) {
        TitleOptionDialog dialog = new TitleOptionDialog();
        dialog.setListener(() -> title);
        dialog.show(getSupportFragmentManager(), null);
        return true;
    }
}
