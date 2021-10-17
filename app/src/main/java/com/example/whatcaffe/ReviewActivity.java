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
        DatabaseReference ref = database.getReference("DB");

        reviewText = (EditText) findViewById(R.id.ReviewText);
        buttonReview = (Button) findViewById(R.id.Reviewbutton);
        // 객체 생성
        DatabaseReference reviewsRef = ref.child("reviews");
        Map<String, Review> reviews = new HashMap<>();

        buttonReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // SignUpActivity 연결
                //myRef.setValue(reviewText.getText().toString());
                // 객체에 정보넣기
                reviews.put("파스쿠찌",new Review("Id","김세현",reviewText.getText().toString()));
                // 데이터베이스에 넣기
                reviewsRef.setValue(reviews);

                // 알림말 및 화면이동
                Toast.makeText(getApplicationContext(),"리뷰 작성이 완료되었습니다. ",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ReviewActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        // Read from the database
    ref.addValueEventListener(new ValueEventListener() {
    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
            // This method is called once with the initial value and again
            // whenever data at this location is updated.
            String value = dataSnapshot.getValue(String.class);
            Log.d(TAG, "Value is: " + value);

    }

    @Override
    public void onCancelled(DatabaseError error) {
            // Failed to read value
            Log.w(TAG, "Failed to read value.", error.toException());
            }
            });
        }
}

