package com.example.whatcaffe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class Item2Activity extends AppCompatActivity {
    private Button reviewbutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item2);
        reviewbutton = findViewById(R.id.Reviewbutton);
        reviewbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Item2Activity.this, ReviewWriteActivity.class);
                startActivity(intent);
            }
        });


//    public void onDefaultToggleClick(View view) {
//        Toast.makeText(this, "onDefaultToggleClick", Toast.LENGTH_SHORT).show();
//    }
    }
}


