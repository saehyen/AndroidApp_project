package com.example.whatcaffe.ui.dashboard;

public class Item {

    String name;
    String photo;
    String summary;
    String bean;

    public Item(String name, String photo, String summary,String bean) {
        this.name = name;
        this.photo = photo;
        this.summary = summary;
        this.bean = bean;
    }


    public String getName() { return name; }
    public String getPhoto() {
        return photo;
    }
    public String getSummary() {
        return summary;
    }
    public String getBean() { return bean;  }
}