package com.example.whatcaffe;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class ReviewActivity extends AppCompatActivity {
    private Button buttonReview;
    private EditText reviewText;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("Cafe/메가커피 대구동호점/Reviews");
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String email = user != null ? user.getEmail(): null;
        String uid = user != null ? user.getUid(): null;
        // 로그인한 유저의 정보 가져오기

        int idx = email.indexOf("@");
        String nickname = email.substring(0,idx);
        reviewText = (EditText) findViewById(R.id.ReviewText);
        buttonReview = (Button) findViewById(R.id.Reviewbutton);
        // 객체 생성
        Map<String, Review> reviews = new HashMap<>();

        buttonReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // SignUpActivity 연결
                //myRef.setValue(reviewText.getText().toString());
                // 객체에 정보넣기
                reviews.put(nickname,new Review(uid,reviewText.getText().toString()));
                // 데이터베이스에 넣기
                ref.setValue(reviews);

                // 알림말 및 화면이동
                Toast.makeText(getApplicationContext(),"리뷰 작성이 완료되었습니다. ",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ReviewActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
