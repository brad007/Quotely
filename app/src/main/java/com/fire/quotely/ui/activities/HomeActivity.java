package com.fire.quotely.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fire.quotely.R;
import com.fire.quotely.ui.fragments.SubmissionsFragment;
import com.fire.quotely.ui.fragments.TitleFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.nav_view)
    NavigationView mNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        FirebaseAuth.getInstance()
                .addAuthStateListener(firebaseAuth -> {
                    if (firebaseAuth.getCurrentUser() == null) {
                        startActivity(new Intent(HomeActivity.this, SignInActivity.class));
                    }
                });

        changeFragment(new TitleFragment(), R.string.title);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string
                .navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        mNavigationView.setNavigationItemSelectedListener(this);
        TextView nameTextView = mNavigationView.getHeaderView(0).findViewById(R.id
                .nav_username_textview);
        TextView emailTextView = mNavigationView.getHeaderView(0).findViewById(R.id.nav_email_textview);
        ImageView displayImageView = mNavigationView.getHeaderView(0).findViewById(R.id.nav_display_imageview);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        nameTextView.setText(user.getDisplayName() != null ? user.getDisplayName() : "");
        emailTextView.setText(user.getEmail() != null ? user.getEmail() : "");

        if (user.getPhotoUrl() != null)
            Glide.with(HomeActivity.this)
                    .load(user.getPhotoUrl())
                    .into(displayImageView);
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id) {
            case R.id.nav_title:
                changeFragment(new TitleFragment(), R.string.title);
                break;
            case R.id.nav_submissions:
                changeFragment(new SubmissionsFragment(), R.string.submissions);
                break;
            case R.id.nav_share:
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "Hey. I found this great app that let's " +
                        "you read quotes from your favourite series and titles! Find it on the " +
                        "play " +
                        "store at https://play.google.com/store/apps/details?id=com.fire.quotely");
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
                break;
            case R.id.nav_logout:
                FirebaseAuth.getInstance().signOut();
                break;
        }

        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void changeFragment(android.support.v4.app.Fragment fragment, @StringRes int title) {
        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
        mToolbar.setTitle(title);
    }
}
