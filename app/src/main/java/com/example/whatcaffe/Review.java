package com.example.whatcaffe;

import static android.os.Build.ID;

import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class Review<mDBReference> {
    public String id;
    public String uid;
    public String review;

    public Review(){}
    public Review(String uid, String review) {
        this.uid = uid;
        this.review = review;
    }
    public Review(String uid, String name, String review) {
        this.id = id;
        this.uid = uid;
        this.review = review;
    }
    public Map<String, Object> toMap(){
        HashMap <String,Object> result = new HashMap<>();
        result.put("id", id);
        result.put("review",review );
        result.put("uid", uid);

        return result;
    }


}
