package com.example.whatcaffe.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.example.whatcaffe.R;
import com.example.whatcaffe.databinding.FragmentHomeBinding;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.runtime.Permission;

import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

import java.util.List;


public class HomeFragment extends Fragment implements MapView.CurrentLocationEventListener, MapView.MapViewEventListener {

    private FragmentHomeBinding binding;
    int buttonIndex = 0;
    private Context context;
    private MapView mapView;
    private ViewGroup mapViewContainer;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        context = container.getContext();
        MapPOIItem marker_1 = new MapPOIItem();
        MapPOIItem marker_2 = new MapPOIItem();
        MapPOIItem marker_3 = new MapPOIItem();
        MapPOIItem marker_4 = new MapPOIItem();
        MapPOIItem marker_5 = new MapPOIItem();
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //AndPermission으로 위치 권한 받기
        AndPermission.with(this)
                .runtime()
                .permission(
                        Permission.ACCESS_FINE_LOCATION,
                        Permission.ACCESS_COARSE_LOCATION)
                .onGranted(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> permissions) {

                    }
                })
                .onDenied(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> permissions) {
                    }
                })
                .start();

        // 맵을 HomeFragment에 표시
        mapView = new MapView(getActivity());
        mapViewContainer = root.findViewById(R.id.map_view);
        mapViewContainer.addView(mapView);
        mapView.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOnWithoutHeading);

        //맵 포인트 위도경도 설정
        MapPoint mapPoint_1 = MapPoint.mapPointWithGeoCoord(35.86984, 128.73297);
        marker_1.setItemName("프라우송");
        marker_1.setTag(0);
        marker_1.setMapPoint(mapPoint_1);
        marker_1.setMarkerType(MapPOIItem.MarkerType.BluePin); // 기본으로 제공하는 BluePin 마커 모양.
        marker_1.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin); // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.
        mapView.addPOIItem(marker_1);

        MapPoint mapPoint_2 = MapPoint.mapPointWithGeoCoord(35.87901, 128.73027);
        marker_2.setItemName("투썸플레이스 대구혁신도시점");
        marker_2.setTag(1);
        marker_2.setMapPoint(mapPoint_2);
        marker_2.setMarkerType(MapPOIItem.MarkerType.BluePin); // 기본으로 제공하는 BluePin 마커 모양.
        marker_2.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin); // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.
        mapView.addPOIItem(marker_2);

        MapPoint mapPoint_3 = MapPoint.mapPointWithGeoCoord(35.87719, 128.73147);
        marker_3.setItemName("핸즈커피 신서혁신 도시점");
        marker_3.setTag(2);
        marker_3.setMapPoint(mapPoint_3);
        marker_3.setMarkerType(MapPOIItem.MarkerType.BluePin); // 기본으로 제공하는 BluePin 마커 모양.
        marker_3.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin); // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.
        mapView.addPOIItem(marker_3);

        MapPoint mapPoint_4 = MapPoint.mapPointWithGeoCoord(35.87719, 128.73147);
        marker_4.setItemName("핸즈커피 신서혁신 도시점");
        marker_4.setTag(2);
        marker_4.setMapPoint(mapPoint_4);
        marker_4.setMarkerType(MapPOIItem.MarkerType.BluePin); // 기본으로 제공하는 BluePin 마커 모양.
        marker_4.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin); // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.
        mapView.addPOIItem(marker_4);

        // '원두 맛' 버튼을 누르면 차례대로 '신 맛', '중간 맛', '탄 맛' 버튼이 생성.
        Button beansButton = root.findViewById(R.id.add_button);
        Button acidicBeansButton = root.findViewById(R.id.acidic_beans_button);
        Button mediumBeansButton = root.findViewById(R.id.medium_beans_button);
        Button smokyBeansButton = root.findViewById(R.id.smoky_beans_button);

        beansButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (buttonIndex == 0) {
                    acidicBeansButton.setVisibility(View.VISIBLE);
                    mediumBeansButton.setVisibility(View.VISIBLE);
                    smokyBeansButton.setVisibility(View.VISIBLE);
                    buttonIndex = 1;
                } else if (buttonIndex == 1) {
                    acidicBeansButton.setVisibility(View.INVISIBLE);
                    mediumBeansButton.setVisibility(View.INVISIBLE);
                    smokyBeansButton.setVisibility(View.INVISIBLE);
                    buttonIndex = 0;
                }
            }
        });


        // '현재위치' 버튼을 터치하면 지도를 현재 위치 기준으로 위치한다.
        Button setCurrentLocationButton = (Button) root.findViewById(R.id.location_button);

        setCurrentLocationButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                mapView.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOnWithoutHeading);
            }
        });

        return root;
    }

    @Override
    public void onCurrentLocationUpdate(MapView mapView, MapPoint mapPoint, float v) {

    }

    @Override
    public void onCurrentLocationDeviceHeadingUpdate(MapView mapView, float v) {

    }

    @Override
    public void onCurrentLocationUpdateFailed(MapView mapView) {

    }

    @Override
    public void onCurrentLocationUpdateCancelled(MapView mapView) {

    }

    @Override
    public void onMapViewInitialized(MapView mapView) {

    }

    @Override
    public void onMapViewCenterPointMoved(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewZoomLevelChanged(MapView mapView, int i) {

    }

    @Override
    public void onMapViewSingleTapped(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewDoubleTapped(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewLongPressed(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewDragStarted(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewDragEnded(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewMoveFinished(MapView mapView, MapPoint mapPoint) {

    }
}