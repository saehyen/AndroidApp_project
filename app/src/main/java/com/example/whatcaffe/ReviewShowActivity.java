package com.example.whatcaffe;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import com.example.whatcaffe.ui.home.CafeInfo;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ReviewShowActivity extends AppCompatActivity {
    Map<String, Review> reviews = new HashMap<>();

    private String reviewtext;

    // 데이터베이스 연결
    public FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference ref = database.getReference("Reviews");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_show);



        // 현재 접속한 사용자의 이메일 받아오기
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String email = user != null ? user.getEmail() : null;
        int idx = email.indexOf("@");
        String nickname = email.substring(0,idx);
        // 현재 접속한 사용자의 uid 받아오기
        String uid = user != null ? user.getUid() : null;


        // 카페에 있는 정보 가져오기
        ref.child(nickname).child("review").addValueEventListener(new ValueEventListener() {
            // 성공했을 때
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                reviewtext = String.valueOf(dataSnapshot.getValue());
                TextView review = (TextView) findViewById(R.id.reviewTextView);
                review.setText(reviewtext);
            }

            // 실패했을 떄
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
            }
        });

    }
}