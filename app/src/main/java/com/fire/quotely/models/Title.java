package com.fire.quotely.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by LLL-Brad on 10/3/2017.
 */

public class Title extends RealmObject {
    @PrimaryKey
    private String title;
    private String quote;
    private String acrynom;
    private boolean isChosen;

    public Title() {
    }

    public Title(String title, String quote, String acrynom, boolean isChosen) {
        this.title = title;
        this.quote = quote;
        this.acrynom = acrynom;
        this.isChosen = isChosen;
    }

    public boolean isChosen() {

        return isChosen;
    }

    public void setChosen(boolean chosen) {
        isChosen = chosen;
    }

    public String getAcrynom() {

        return acrynom;
    }

    public void setAcrynom(String acrynom) {
        this.acrynom = acrynom;
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

    @Override
    public String toString() {
        return title;
    }
}
