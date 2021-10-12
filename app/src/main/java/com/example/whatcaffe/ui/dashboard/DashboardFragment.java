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

import java.util.ArrayList;

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
        //초기화
        items.clear();
        items.add(new Item("프라우송", "http://image_url.jpg", "대구 동구 안심로90길 12(괴전동)"));
        items.add(new Item("투썸플레이스", "http://image_url.jpg", "동구 첨단로8길 1 1층... "));
        items.add(new Item("핸즈커피 신서혁신도시점", "http://image_url.jpg", "대구 동구 이노벨리로 322 비젼스퀘어..."));
        items.add(new Item("커피스미스드래프트 비젼스퀘어점", "http://image_url.jpg", "대구 동구 이노벨리로 322 1층 135호..."));
        items.add(new Item("카페봄봄 신서 혁신점", "http://image_url.jpg", "..."));
        items.add(new Item("프라우송", "http://image_url.jpg", "대구 동구 안심로90길 12(괴전동)"));
        items.add(new Item("투썸플레이스", "http://image_url.jpg", "동구 첨단로8길 1 1층... "));
        items.add(new Item("핸즈커피 신서혁신도시점", "http://image_url.jpg", "대구 동구 이노벨리로 322 비젼스퀘어..."));

        items.add(new Item("카페봄봄 신서 혁신점", "http://image_url.jpg", "..."));
        items.add(new Item("프라우송", "http://image_url.jpg", "대구 동구 안심로90길 12(괴전동)"));
        items.add(new Item("투썸플레이스", "http://image_url.jpg", "동구 첨단로8길 1 1층... "));
        items.add(new Item("핸즈커피 신서혁신도시점", "http://image_url.jpg", "대구 동구 이노벨리로 322 비젼스퀘어..."));

        items.add(new Item("카페봄봄 신서 혁신점", "http://image_url.jpg", "..."));
        items.add(new Item("프라우송", "http://image_url.jpg", "대구 동구 안심로90길 12(괴전동)"));
        items.add(new Item("투썸플레이스", "http://image_url.jpg", "동구 첨단로8길 1 1층... "));
        items.add(new Item("핸즈커피 신서혁신도시점", "http://image_url.jpg", "대구 동구 이노벨리로 322 비젼스퀘어..."));

        items.add(new Item("카페봄봄 신서 혁신점", "http://image_url.jpg", "..."));
    }

}