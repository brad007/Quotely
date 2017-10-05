package com.fire.quotely.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.fire.quotely.R;
import com.fire.quotely.adapters.AddTitlesAdapter;
import com.fire.quotely.models.Title;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.Sort;

public class AddTitleActivity extends AppCompatActivity {

    @BindView(R.id.title_recyclerview)
    RecyclerView mTitleRecyclerView;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_title);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);

        Realm realm = Realm.getDefaultInstance();

        mTitleRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mTitleRecyclerView.setAdapter(new AddTitlesAdapter(realm.where(Title.class).findAllSorted
                ("title", Sort.ASCENDING)));
    }

}
