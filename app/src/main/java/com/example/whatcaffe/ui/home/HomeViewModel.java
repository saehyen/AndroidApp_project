package com.example.whatcaffe.ui.home;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private MutableLiveData<List<Place>> mutableCaffePlaceList;
    private List<Place> caffePlace = null;


    private final static String SEARCH_KEYWORD = "대구 신서동 커피";
    private final static String API_KEY = "";
    private final static String LOG_TAG = "Kakao Map App";
    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }

    public LiveData<List<Place>> getPlaces() {
        if (mutableCaffePlaceList == null) {
            mutableCaffePlaceList = new MutableLiveData<List<Place>>();
            loadPlace();
        }

        return mutableCaffePlaceList;
    }

    public void getCaffeListData() {
        KakaoAPIInterface spotInterface =  ApiClient.getApiClient().create(KakaoAPIInterface.class);
        Call<CaffeListData> call = spotInterface.getSearchKeyword(API_KEY, SEARCH_KEYWORD);

        call.enqueue(new Callback<CaffeListData>()
        {
            //연결 성공 시에 싱행되는 부분
            @Override
            public void onResponse(@NonNull Call<CaffeListData> call, @NonNull Response<CaffeListData> response)
            {
                Log.e("onSuccess", String.valueOf(response.raw()));
                caffePlace = response.body().documents;
                loadPlace();
            }

            @Override
            public void onFailure(@NonNull Call<CaffeListData> call, @NonNull Throwable t)
            {
                Log.e(LOG_TAG, "Request Fail : " + t.getMessage());
            }
        });
    }

    private void loadPlace() {
        if (caffePlace == null) {
            return;
        }

        mutableCaffePlaceList.setValue(caffePlace);
    }
}