package com.fire.quotely;

import android.app.Application;

import com.fire.quotely.etc.QuoteData;
import com.fire.quotely.models.Quote;
import com.fire.quotely.models.Title;

import io.realm.Realm;

/**
 * Created by LLL-Brad on 10/3/2017.
 */

public class QuotelyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        Realm realm = Realm.getDefaultInstance();

        if(realm.where(Title.class).findAll().size() != 3){
            realm.executeTransaction(realm12 -> {
                Title title = new Title();
                title.setAcrynom("HP");
                title.setTitle("Harry Potter");
                title.setQuote("Yer a wizard Harry!");
                title.setChosen(true);
                realm12.insertOrUpdate(title);

                Title title2 = new Title();
                title2.setAcrynom("SW");
                title2.setTitle("Star Wars");
                title2.setQuote("May the Force be with you!");
                title2.setChosen(true);
                realm12.insertOrUpdate(title2);

                Title title3 = new Title();
                title3.setAcrynom("HG");
                title3.setTitle("Hunger Games");
                title3.setQuote("May the odds forever be in your favor.");
                title3.setChosen(true);
                realm12.insertOrUpdate(title3);
            });
        }

        realm.executeTransaction(realm1 -> {



            realm.delete(Quote.class);
            realm.copyToRealm(QuoteData.getHarryPotterQuotes());
            realm.copyToRealm(QuoteData.getHungerGamesQuotes());
            realm.copyToRealm(QuoteData.getStarWarsQuotes());
        });
    }
}
