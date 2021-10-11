package com.example.whatcaffe.ui.dashboard;

public class Item {

    String name;
    String photo;
    String summary;

    public Item(String name, String photo, String summary) {
        this.name = name;
        this.photo = photo;
        this.summary = summary;
    }

    public String getName() {
        return name;
    }

    public String getPhoto() {
        return photo;
    }

    public String getSummary() {
        return summary;
    }
}