package com.devhelper.app.models;

public class LinkItem {
    private String title;
    private String url;

    public LinkItem(String title, String url) {
        this.title = title;
        this.url = url;
    }


    @Override
    public String toString() {
        return title + " - " + url;
    }
}