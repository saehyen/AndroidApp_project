package com.example.whatcaffe.ui.home;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties
public class CafeInfo<mDBReference> {
    String cafeId;
    String cafeName;
    String cafeAddress;
    String cafePhoneNum;

    public String getCafeId() {
        return cafeId;
    }

    public void setCafeId(String cafeId) {
        this.cafeId = cafeId;
    }

    public String getCafeName() {
        return cafeName;
    }

    public void setCafeName(String cafeName) {
        this.cafeName = cafeName;
    }

    public String getCafeAddress() {
        return cafeAddress;
    }

    public void setCafeAddress(String cafeAddress) {
        this.cafeAddress = cafeAddress;
    }

    public String getCafePhoneNum() {
        return cafePhoneNum;
    }

    public void setCafePhoneNum(String cafePhoneNum) {
        this.cafePhoneNum = cafePhoneNum;
    }

    public CafeInfo() {}

    public CafeInfo(String cafeId, String cafeName, String cafeAddress, String cafePhoneNum) {
        this.cafeId = cafeId;
        this.cafeName = cafeName;
        this.cafeAddress = cafeAddress;
        this.cafePhoneNum = cafePhoneNum;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("id", cafeId);
        result.put("name", cafeName);
        result.put("address", cafeAddress);
        result.put("phoneNum", cafePhoneNum);
        return result;
    }
}
