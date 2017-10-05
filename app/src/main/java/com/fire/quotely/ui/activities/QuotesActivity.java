package com.fire.quotely.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.fire.quotely.R;
import com.fire.quotely.adapters.QuoteDeckAdapter;
import com.fire.quotely.etc.IntentConstants;
import com.fire.quotely.models.Quote;

import io.realm.Realm;
import link.fls.swipestack.SwipeStack;

public class QuotesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quotes);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        String title = getIntent().getStringExtra(IntentConstants.EXTRA_TITLE);
        toolbar.setTitle(title);
        SwipeStack cardStack = findViewById(R.id.swipe_deck);


        cardStack.setListener(new SwipeStack.SwipeStackListener() {
            @Override
            public void onViewSwipedToLeft(int position) {

            }

            @Override
            public void onViewSwipedToRight(int position) {

            }

            @Override
            public void onStackEmpty() {

            }
        });

        Realm realm = Realm.getDefaultInstance();

        QuoteDeckAdapter adapter = new QuoteDeckAdapter(realm.where(Quote.class).equalTo
                ("title", title).findAll(),
                this);

        cardStack.setAdapter(adapter);

    }

}
