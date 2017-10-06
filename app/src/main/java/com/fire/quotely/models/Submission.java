package com.fire.quotely.models;

import com.google.firebase.auth.FirebaseUser;

/**
 * Created by LLL-Brad on 10/5/2017.
 */

public class Submission {
    private String title;
    private String tagline;
    private String acronym;
    private FirebaseUser user;
    private int upVote;
    private int downVote;
    private int totalVote;
    private String id;

    public Submission() {
    }

    public Submission(String title, String tagline, String acronym, FirebaseUser user, int upVote, int downVote, int totalVote, String id) {
        this.title = title;
        this.tagline = tagline;
        this.acronym = acronym;
        this.user = user;
        this.upVote = upVote;
        this.downVote = downVote;
        this.totalVote = totalVote;
        this.id = id;
    }

    public String getId() {

        return id;
    }

    public int getUpVote() {

        return upVote;
    }

    public void setUpVote(int upVote) {
        this.upVote = upVote;
    }

    public int getDownVote() {
        return downVote;
    }

    public void setDownVote(int downVote) {
        this.downVote = downVote;
    }

    public int getTotalVote() {
        return totalVote;
    }

    public void setTotalVote(int totalVote) {
        this.totalVote = totalVote;
    }

    public Submission(String title, String tagline, String acronym, FirebaseUser user) {

        this.title = title;
        this.tagline = tagline;
        this.acronym = acronym;
        this.user = user;
    }

    public String getTitle() {

        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public String getAcronym() {
        return acronym;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }

    public FirebaseUser getUser() {
        return user;
    }

    public void setUser(FirebaseUser user) {
        this.user = user;
    }

    public void setId(String id) {
        this.id = id;
    }
}
