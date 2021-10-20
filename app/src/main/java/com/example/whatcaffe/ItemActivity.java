package com.example.whatcaffe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.whatcaffe.ui.dashboard.Item;
import com.example.whatcaffe.ui.home.*;

import java.util.ArrayList;

public class ItemActivity extends AppCompatActivity {
    private Intent intent;
    private int number;
    private TextView cafeName;
    private TextView cafeAddress;
    private TextView cafeBeans;
    private TextView cafePhone;
    private ImageView cafeImage;
    private Button reviewbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item2);
        intent = getIntent();
        number = intent.getIntExtra("number", 0);
        reviewbutton = (Button) findViewById(R.id.Reviewbutton);
        reviewbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(v.getContext(), ReviewWriteActivity.class);
                v.getContext().startActivity(intent);

                finish();
            }
        });

        cafeName = findViewById(R.id.cafeName);
        cafeAddress = findViewById(R.id.cafeAddress);
        cafeBeans = findViewById(R.id.cafeBeans);
        cafePhone = findViewById(R.id.cafePhoneNum);
        cafeImage = findViewById(R.id.cafeImage);


        switch (number) {
            case 0:
                cafeImage.setImageResource(R.drawable.mass_coffee_1);
                cafeName.setText("매스커피 신서혁신점");
                cafeAddress.setText("대구 동구 신서동 1149");
                cafeBeans.setText("중간맛");
                cafePhone.setText("053-965-9666");
                break;
            case 1:
                cafeImage.setImageResource(R.drawable.mega_coffee_1);
                cafeName.setText("메가커피 대구동호점");
                cafeAddress.setText("대구 동구 신서동 530-5");
                cafeBeans.setText("중간맛");
                cafePhone.setText("053-964-9921");
                break;
            case 2:
                cafeImage.setImageResource(R.drawable.mainstay_1);
                cafeName.setText("메인스테이");
                cafeAddress.setText("대구 동구 신서동 1166-2");
                cafeBeans.setText("신맛");
                cafePhone.setText("053-965-6181");
                break;
            case 3:
                cafeImage.setImageResource(R.drawable.ppakdabang_1);
                cafeName.setText("빽다방 대구동호점");
                cafeAddress.setText("대구 동구 신서동 538-1");
                cafeBeans.setText("탄맛");
                cafePhone.setText("053-965-2224");
                break;
            case 4:
                cafeImage.setImageResource(R.drawable.ppakdabang_2);
                cafeName.setText("빽다방 대구혁신1호점");
                cafeAddress.setText("대구 동구 신서동 1148-1");
                cafeBeans.setText("탄맛");
                cafePhone.setText("053-965-9399");
                break;
            case 5:
                cafeImage.setImageResource(R.drawable.starbucks_1);
                cafeName.setText("스타벅스 대구각산역DT점");
                cafeAddress.setText("대구 동구 신서동 695");
                cafeBeans.setText("탄맛 디카페인");
                cafePhone.setText("1522-3232");
                break;
            case 6:
                cafeImage.setImageResource(R.drawable.starbucks_2);
                cafeName.setText("스타벅스 반야월이마트점");
                cafeAddress.setText("대구 동구 신서동 520-1");
                cafeBeans.setText("탄맛 디카페인");
                cafePhone.setText("1522-3232");
                break;
            case 7:
                cafeImage.setImageResource(R.drawable.ssolcoffee);
                cafeName.setText("쏠커피");
                cafeAddress.setText("대구 동구 신서동 1118");
                cafeBeans.setText("탄맛");
                cafePhone.setText("053-963-2948");
                break;
            case 8:
                cafeImage.setImageResource(R.drawable.eidya_coffee_1);
                cafeName.setText("이디야커피 대구각산역점");
                cafeAddress.setText("대구 동구 신서동 528-2");
                cafeBeans.setText("탄맛 디카페인");
                cafePhone.setText("053-964-2224");
                break;
            case 9:
                cafeImage.setImageResource(R.drawable.eidya_coffee_2);
                cafeName.setText("이디야커피 대구반야월점");
                cafeAddress.setText("대구 동구 신서동 1091-60");
                cafeBeans.setText("탄맛 디카페인");
                cafePhone.setText("053-965-2369");
                break;
            case 10:
                cafeImage.setImageResource(R.drawable.cafedamant);
                cafeName.setText("카페디아몽");
                cafeAddress.setText("대구 동구 신서동 553-14");
                cafeBeans.setText("신맛");
                cafePhone.setText("053-962-1732");
                break;
            case 11:
                cafeImage.setImageResource(R.drawable.coffeesmithdraft);
                cafeName.setText("커피스미스드래프트 비젼스퀘어점");
                cafeAddress.setText("대구 동구 신서동 1188");
                cafeBeans.setText("탄맛 디카페인");
                cafePhone.setText("053-964-9834");
                break;
            case 12:
                cafeImage.setImageResource(R.drawable.coffeesmithdraft);
                cafeName.setText("커피앤크레이터스");
                cafeAddress.setText("대구 동구 신서동 526-3");
                cafeBeans.setText("탄맛");
                cafePhone.setText("053-283-1600");
                break;
            case 13:
                cafeImage.setImageResource(R.drawable.atwosomplace);
                cafeName.setText("투썸플레이스 대구혁신도시점");
                cafeAddress.setText("대구 동구 신서동 1147");
                cafeBeans.setText("중간맛 신맛 디카페인");
                cafePhone.setText("053-964-2377");
                break;
            case 14:
                cafeImage.setImageResource(R.drawable.handscoffee);
                cafeName.setText("핸즈커피 신서혁신점");
                cafeAddress.setText("대구 동구 신서동 1188");
                cafeBeans.setText("신맛 디카페인");
                cafePhone.setText("053-962-3476");
                break;
        }
    }
}