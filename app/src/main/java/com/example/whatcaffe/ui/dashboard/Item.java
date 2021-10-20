package com.example.whatcaffe.ui.dashboard;

import android.media.Image;
import android.widget.ImageView;

public class Item {

    String name;
    String address;
    String phone;
    int image;

    public Item(int image, String name, String address, String phone) {
        this.image = image;
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    public int getImage() {
        return image;
    }

    public String getName() { return name; }
    public String getAddress() {
        return address;
    }
    public String getPhone() { return phone; }
}