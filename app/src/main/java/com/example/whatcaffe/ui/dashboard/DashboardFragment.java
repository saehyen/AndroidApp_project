package com.example.whatcaffe.ui.dashboard;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.whatcaffe.R;
import com.example.whatcaffe.databinding.FragmentDashboardBinding;
import com.example.whatcaffe.ui.home.*;

import java.util.ArrayList;
import java.util.List;

public class DashboardFragment extends Fragment {
    private ArrayList<Item> items = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        initDataset();

        Context context = view.getContext();
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(context, items);
        recyclerView.setAdapter(adapter);

        return view;
    }

    /*사용할 데이터를 미리 준비해 놓는다. 준비하는 형태에 따라 구현방법이 조금 달라 질 수 있다.
    1. 수동으로 Item을 입력해서 추가 하도록 할 수 있다.
    2. 온라인에서 DB에서 일괄 가져 올 수 도 있다.
    어떻게든 itmes 배열에 데이터를 형식에 맞게 넣어 어답터와 연결만 하면 화면에 내용이 출력될것이다.
    */
    private void initDataset() {
        items.clear();

        items.add(new Item(R.drawable.mass_coffee_1, "매스커피 신서혁신점", "대구 동구 신서동 1149", "053-965-9666"));
        items.add(new Item(R.drawable.mega_coffee_1, "매가커피 대구동호점", "대구 동구 신서동 530-5", "053-964-9921"));
        items.add(new Item(R.drawable.mainstay_1, "메인스테이", "대구 동구 신서동 1166-2", "053-965-6181"));
        items.add(new Item(R.drawable.ppakdabang_1, "빽다방 대구동호점", "대구 동구 신서동 538-1", "053-965-2224"));
        items.add(new Item(R.drawable.ppakdabang_2, "빽다방 대구혁신1호점", "대구 동구 신서동 1148-1", "053-965-9399"));
        items.add(new Item(R.drawable.starbucks_1, "스타벅스 대구각산역DT점", "대구 동구 신서동 695", "1522-3232"));
        items.add(new Item(R.drawable.starbucks_2, "스타벅스 반야월이마트점", "대구 동구 신서동 520-1", "1522-3232"));
        items.add(new Item(R.drawable.ssolcoffee, "쏠커피", "대구 동구 신서동 1188", "053-965-9399"));
        items.add(new Item(R.drawable.eidya_coffee_1, "이디야커피 대구각산역점", "대구 동구 신서동 528-2", "053-965-9399"));
        items.add(new Item(R.drawable.eidya_coffee_2, "이디야커피 대구반야월점", "대구 동구 신서동 1091-60", "053-965-2369"));
        items.add(new Item(R.drawable.cafedamant, "카페디아몽", "대구 동구 신서동 553-14", "053-962-1732"));
        items.add(new Item(R.drawable.coffeesmithdraft, "커피스미스드래프트 비젼스퀘어점", "대구 동구 신서동 1188", "053-964-9834"));
        items.add(new Item(R.drawable.cafecreators, "커피앤크레이터스", "대구 동구 신서동 526-3", "053-283-1600"));
        items.add(new Item(R.drawable.atwosomplace, "투썸플레이스 대구혁신도시점", "대구 동구 신서동 1147", "053-964-2377"));
        items.add(new Item(R.drawable.handscoffee, "핸즈커피 신서혁신도시점", "대구 동구 신서동 1188", "053-962-3476"));
    }

}