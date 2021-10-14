package com.example.whatcaffe.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.whatcaffe.MainActivity;
import com.example.whatcaffe.R;
import com.example.whatcaffe.databinding.FragmentHomeBinding;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.runtime.Permission;

import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

import java.sql.Array;
import java.util.List;


public class HomeFragment extends Fragment implements MapView.CurrentLocationEventListener, MapView.MapViewEventListener {

    private FragmentHomeBinding binding;
    int buttonIndex = 0;
    private Context context;
    private MapView mapView;
    private ViewGroup mapViewContainer;
    private GpsTracker gpsTracker;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        context = container.getContext();
        MapPOIItem markerCurrentLocation = new MapPOIItem();
        MapPOIItem marker1 = new MapPOIItem();
        MapPOIItem marker2 = new MapPOIItem();
        MapPOIItem marker3 = new MapPOIItem();
        MapPOIItem[] marker = new MapPOIItem[3];
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        String[] beansInfo = new String[3];

        //AndPermission으로 위치 권한 받기
        AndPermission.with(getActivity())
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
                        Toast.makeText(getContext(), "Request Permission is denied", Toast.LENGTH_LONG).show();
                    }
                })
                .start();

        // 맵을 HomeFragment에 표시
        mapView = new MapView(getActivity());
        mapViewContainer = root.findViewById(R.id.map_view);
        mapViewContainer.addView(mapView);
        gpsTracker = new GpsTracker(context);

        Button setCurrentLocationButton = root.findViewById(R.id.location_button);

        setCurrentLocationButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                //mapView.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOnWithoutHeading);


                mapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(gpsTracker.getLatitude(), gpsTracker.getLongitude()), true);

                MapPoint mapPointCurrentLocation = MapPoint.mapPointWithGeoCoord(gpsTracker.getLatitude(), gpsTracker.getLongitude());
                markerCurrentLocation.setItemName("현재 위치");
                markerCurrentLocation.setTag(0);
                markerCurrentLocation.setMapPoint(mapPointCurrentLocation);
                markerCurrentLocation.setMarkerType(MapPOIItem.MarkerType.CustomImage);
                markerCurrentLocation.setCustomImageResourceId(R.drawable.currentlocationmarker);
                mapView.addPOIItem(markerCurrentLocation);
            }

        });


        //맵 포인트 위도경도 설정
        MapPoint mapPoint_1 = MapPoint.mapPointWithGeoCoord(35.86985, 128.73295);
        marker1.setItemName("프라우송 \n탄맛");
        marker1.setTag(1);
        marker1.setMapPoint(mapPoint_1);
        marker1.setMarkerType(MapPOIItem.MarkerType.CustomImage);
        marker1.setCustomImageResourceId(R.drawable.caffeemarker);
        beansInfo[0] = marker1.getItemName();
        mapView.addPOIItem(marker1);

        MapPoint mapPoint_2 = MapPoint.mapPointWithGeoCoord(35.87906, 128.73038);
        marker2.setItemName("투썸플레이스 대구혁신도시점 \n중간맛");
        marker2.setTag(2);
        marker2.setMapPoint(mapPoint_2);
        marker2.setMarkerType(MapPOIItem.MarkerType.CustomImage);
        marker2.setCustomImageResourceId(R.drawable.caffeemarker);
        beansInfo[1] = marker2.getItemName();
        mapView.addPOIItem(marker2);

        MapPoint mapPoint_3 = MapPoint.mapPointWithGeoCoord(35.877220, 128.732016);
        marker3.setItemName("핸즈커피 신서혁신 도시점 \n신맛");
        marker3.setTag(3);
        marker3.setMapPoint(mapPoint_3);
        marker3.setMarkerType(MapPOIItem.MarkerType.CustomImage);
        marker3.setCustomImageResourceId(R.drawable.caffeemarker);
        beansInfo[2] = marker3.getItemName();
        mapView.addPOIItem(marker3);

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

        acidicBeansButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {

                        if(beansInfo[0].contains("신맛")) {
                            marker1.setCustomImageResourceId(R.drawable.big_caffeemarker);
                        }
                        if(beansInfo[1].contains("신맛")) {
                            marker2.setCustomImageResourceId(R.drawable.big_caffeemarker);
                        }
                        if(beansInfo[2].contains("신맛"))
                            marker3.setCustomImageResourceId(R.drawable.big_caffeemarker);
            }
        });

        mapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(gpsTracker.getLatitude(), gpsTracker.getLongitude()), true);

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