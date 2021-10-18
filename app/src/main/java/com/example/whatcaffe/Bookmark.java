package com.example.whatcaffe;

import java.util.HashMap;
import java.util.Map;

public class Bookmark<mDBReference> {
    public String id;
    public String name;

    public Bookmark() {}
    public Bookmark(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Map<String, Object> toMap() {
        HashMap <String, Object> result = new HashMap<>();
        result.put("id", id);
        result.put("name", name);

        return result;
    }
}
