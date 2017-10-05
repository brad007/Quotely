package com.fire.quotely.models;

import io.realm.RealmObject;

/**
 * Created by LLL-Brad on 10/4/2017.
 */

public class Quote extends RealmObject{
    String quote;
    String title;

    public Quote() {
    }


    public Quote( String title, String quote) {
        this.quote = quote;
        this.title = title;
    }

    public String getTitle() {

        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getQuote() {

        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }
}
