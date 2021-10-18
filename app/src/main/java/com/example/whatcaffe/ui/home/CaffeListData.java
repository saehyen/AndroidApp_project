package com.example.whatcaffe.ui.home;

import java.util.List;

public class CaffeListData {
    PlaceMeta metadata;
    public List<Place> documents;

    public PlaceMeta getMetadata() {
        return metadata;
    }

    public void setMetadata(PlaceMeta metadata) {
        this.metadata = metadata;
    }

    public List<Place> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Place> documents) {
        this.documents = documents;
    }
}

class PlaceMeta{
    int total_count;            // 검색어에 검색된 문서 수
    int pageable_count;        // total_count 중 노출 가능 문서 수, 최대 45 (API에서 최대 45개 정보만 제공)
    Boolean is_end;             // 현재 페이지가 마지막 페이지인지 여부, 값이 false면 page를 증가시켜 다음 페이지를 요청할 수 있음
    RegionInfo same_name;          // 질의어의 지역 및 키워드 분석 정보
}

class RegionInfo {
    List<String> region;     // 질의어에서 인식된 지역의 리스트, ex) '중앙로 맛집' 에서 중앙로에 해당하는 지역 리스트
    String keyword;  // 질의어에서 지역 정보를 제외한 키워드, ex) '중앙로 맛집' 에서 '맛집'
    String selected_region;   // 인식된 지역 리스트 중, 현재 검색
}

class Place {
    String id;           // 장소 ID
    String place_name;    // 장소명, 업체명
    String category_name;   // 카테고리 이름
    String category_group_code;    // 중요 카테고리만 그룹핑한 카테고리 그룹 코드
    String category_group_name;   // 중요 카테고리만 그룹핑한 카테고리 그룹명
    String phone;       // 전화번호
    String address_name;   // 전체 지번 주소
    String road_address_name;    // 전체 도로명 주소1
    String x;         // X 좌표값 혹은 longitude
    String y;            // Y 좌표값 혹은 latitude
    String place_url;    // 장소 상세페이지 URL
    String distance;      // 중심좌표까지의 거리. 단, x,y 파라미터를 준 경우에만 존재. 단위는 meter
}





