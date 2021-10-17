package com.example.whatcaffe.ui.dashboard;

public class Item {

    String name;
    String address;
    String phone;

    public Item(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
    }


    public String getName() { return name; }
    public String getAddress() {
        return address;
    }
    public String getPhone() { return phone; }
}