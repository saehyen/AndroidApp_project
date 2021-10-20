package com.example.whatcaffe.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.whatcaffe.R;
import com.example.whatcaffe.databinding.FragmentHomeBinding;
import net.daum.mf.map.api.CalloutBalloonAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.runtime.Permission;
import android.view.MenuItem;
import net.daum.mf.map.api.CalloutBalloonAdapter;
import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;


public class HomeFragment extends Fragment implements MapView.CurrentLocationEventListener, MapView.MapViewEventListener {

    private FragmentHomeBinding binding;
    int buttonIndex = 0;
    private Context context;
    private MapView mapView = null;
    private ViewGroup mapViewContainer = null;
    private GpsTracker gpsTracker;
    private HomeViewModel homeViewModel;
    String[] cafeBeans = new String[15];
    Double[] cafeX = new Double[15];
    Double[] cafeY = new Double[15];

    class CustomCalloutBalloonAdapter implements CalloutBalloonAdapter {
        private final View mCalloutBalloon;
        public CustomCalloutBalloonAdapter() {
            mCalloutBalloon = getLayoutInflater().inflate(R.layout.custom_callout_balloon, null);
        }

        @Override
        public View getCalloutBalloon(MapPOIItem poiItem) {
            ((TextView) mCalloutBalloon.findViewById(R.id.title)).setText(poiItem.getItemName());
            return mCalloutBalloon;
        }

        @Override
        public View getPressedCalloutBalloon(MapPOIItem poiItem) {
            return null;
        }
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        Log.d("Kakao Map App", "onCreateView is called...");

        context = container.getContext();
        MapPOIItem markerCurrentLocation = new MapPOIItem();
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();



        // 맵을 HomeFragment에 표시
        mapView = new MapView(getActivity());
        mapViewContainer = root.findViewById(R.id.map_view);
        mapViewContainer.addView(mapView);
        gpsTracker = new GpsTracker(context);
        mapView.setCurrentLocationEventListener(this);
        mapView.setMapViewEventListener(this);

        homeViewModel.getPlaces().observe(getViewLifecycleOwner(), new Observer<List<Place>>() {
            @Override
            public void onChanged(List<Place> places) {
                createPlaceMarker(mapView, places);
            }
        });



        Button setCurrentLocationButton = root.findViewById(R.id.location_button);

        setCurrentLocationButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                MapPoint mapPointCurrentLocation = MapPoint.mapPointWithGeoCoord(gpsTracker.getLatitude(), gpsTracker.getLongitude());
                mapView.setMapCenterPoint(mapPointCurrentLocation, true);
                markerCurrentLocation.setItemName("현재 위치");
                markerCurrentLocation.setTag(0);
                markerCurrentLocation.setMapPoint(mapPointCurrentLocation);
                markerCurrentLocation.setMarkerType(MapPOIItem.MarkerType.CustomImage);
                markerCurrentLocation.setCustomImageResourceId(R.drawable.currentlocationmarker);
                mapView.addPOIItem(markerCurrentLocation);
            }

        });

        // '원두 맛' 버튼을 누르면 차례대로 '신 맛', '중간 맛', '탄 맛' 버튼이 생성.
        Button beansButton = root.findViewById(R.id.add_button);
        Button acidicBeansButton = root.findViewById(R.id.acidic_beans_button);
        Button mediumBeansButton = root.findViewById(R.id.medium_beans_button);
        Button smokyBeansButton = root.findViewById(R.id.smoky_beans_button);
        Button resetButton = root.findViewById(R.id.reset_button);
        Button decafButton = root.findViewById(R.id.decaf_beans_button);

        beansButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (buttonIndex == 0) {
                    acidicBeansButton.setVisibility(View.VISIBLE);
                    mediumBeansButton.setVisibility(View.VISIBLE);
                    smokyBeansButton.setVisibility(View.VISIBLE);
                    resetButton.setVisibility(View.VISIBLE);
                    decafButton.setVisibility(View.VISIBLE);
                    buttonIndex = 1;
                } else if (buttonIndex == 1) {
                    acidicBeansButton.setVisibility(View.INVISIBLE);
                    mediumBeansButton.setVisibility(View.INVISIBLE);
                    smokyBeansButton.setVisibility(View.INVISIBLE);
                    resetButton.setVisibility(View.INVISIBLE);
                    decafButton.setVisibility(View.INVISIBLE);
                    buttonIndex = 0;
                }
            }
        });
        mapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(gpsTracker.getLatitude(), gpsTracker.getLongitude()), true);
        mapView.setCalloutBalloonAdapter(new CustomCalloutBalloonAdapter());

        acidicBeansButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                    mapView.removeAllPOIItems();
                    for (int i = 0; i < cafeBeans.length; i++) {
                        if (!cafeBeans[i].contains("신맛")) {
                            continue;
                        }
                        MapPOIItem item = new MapPOIItem();

                        String[] strArr = cafeBeans[i].split("\\+");
                        item.setItemName(strArr[0]);

                        MapPoint pointCafe = MapPoint.mapPointWithGeoCoord(cafeY[i], cafeX[i]);

                        item.setMarkerType(MapPOIItem.MarkerType.CustomImage);
                        item.setCustomImageResourceId(R.drawable.acidicmarker);
                        item.setMapPoint(pointCafe);
                        mapView.addPOIItem(item);
                    }
            }
        });

        mediumBeansButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                mapView.removeAllPOIItems();
                for (int i = 0; i < cafeBeans.length; i++) {
                    if (!cafeBeans[i].contains("중간맛")) {
                        continue;
                    }
                    MapPOIItem item = new MapPOIItem();

                    String[] strArr = cafeBeans[i].split("\\+");
                    item.setItemName(strArr[0]);

                    MapPoint pointCafe = MapPoint.mapPointWithGeoCoord(cafeY[i], cafeX[i]);

                    item.setMarkerType(MapPOIItem.MarkerType.CustomImage);
                    item.setCustomImageResourceId(R.drawable.mediummarker);
                    item.setMapPoint(pointCafe);
                    mapView.addPOIItem(item);
                }
            }
        });

        smokyBeansButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                mapView.removeAllPOIItems();
                for (int i = 0; i < cafeBeans.length; i++) {
                    if (!cafeBeans[i].contains("탄맛")) {
                        continue;
                    }
                    MapPOIItem item = new MapPOIItem();

                    String[] strArr = cafeBeans[i].split("\\+");
                    item.setItemName(strArr[0]);

                    MapPoint pointCafe = MapPoint.mapPointWithGeoCoord(cafeY[i], cafeX[i]);

                    item.setMarkerType(MapPOIItem.MarkerType.CustomImage);
                    item.setCustomImageResourceId(R.drawable.smokymarker);
                    item.setMapPoint(pointCafe);
                    mapView.addPOIItem(item);
                }
            }
        });

        decafButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                mapView.removeAllPOIItems();
                for (int i = 0; i < cafeBeans.length; i++) {
                    if (!cafeBeans[i].contains("디카페인")) {
                        continue;
                    }
                    MapPOIItem item = new MapPOIItem();

                    String[] strArr = cafeBeans[i].split("\\+");
                    item.setItemName(strArr[0]);

                    MapPoint pointCafe = MapPoint.mapPointWithGeoCoord(cafeY[i], cafeX[i]);

                    item.setMarkerType(MapPOIItem.MarkerType.CustomImage);
                    item.setCustomImageResourceId(R.drawable.decafmarker);
                    item.setMapPoint(pointCafe);
                    mapView.addPOIItem(item);
                }
            }
        });

        resetButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                mapView.removeAllPOIItems();
                for (int i = 0; i < cafeBeans.length; i++) {

                    MapPOIItem item = new MapPOIItem();

                    String[] strArr = cafeBeans[i].split("\\+");
                    item.setItemName(strArr[0]);

                    MapPoint pointCafe = MapPoint.mapPointWithGeoCoord(cafeY[i], cafeX[i]);

                    item.setMarkerType(MapPOIItem.MarkerType.CustomImage);
                    item.setCustomImageResourceId(R.drawable.caffeemarker);
                    item.setMapPoint(pointCafe);
                    mapView.addPOIItem(item);
                }
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
        Log.d("Kakao Map App", "onMapViewInitialized is called...");
        if (homeViewModel != null) {
            Log.d("Kakao Map App", "Caffe List Data is Requesting...");
            homeViewModel.getCaffeListData();
        }
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

    private void createPlaceMarker(MapView map, List<Place> places) {
        String[] count = {"탄맛 디카페인", "중간맛 신맛 디카페인", "신맛 디카페인", "신맛", "탄맛 디카페인", "중간맛", "중간맛", "탄맛 디카페인", "탄맛 디카페인", "탄맛", "신맛", "탄맛", "탄맛", "중간맛", "탄맛"};
        for (int i = 0; i < places.size(); i++) {
            Place place = places.get(i);
            MapPOIItem item = new MapPOIItem();
            String distance = String.format("%.3f", getDistance(gpsTracker.getLatitude(), gpsTracker.getLongitude(), Double.parseDouble(place.y), Double.parseDouble(place.x)));
            MapPoint point = MapPoint.mapPointWithGeoCoord(Double.parseDouble(place.y), Double.parseDouble(place.x));
            item.setItemName(place.place_name + "\n거리: " + distance + "km \n" + "원두: " + count[i]);
            item.setTag(0);
            item.setMapPoint(point);
            item.setMarkerType(MapPOIItem.MarkerType.CustomImage);
            item.setCustomImageResourceId(R.drawable.caffeemarker);
            map.addPOIItem(item);
            cafeBeans[i] = item.getItemName();
            cafeX[i] = Double.parseDouble(place.x);
            cafeY[i] = Double.parseDouble(place.y);
        }

    }

    public double getDistance(double currentLat, double currentLng, double targetLat, double targetLng) {

        double theta = currentLng - targetLng;
        double dist = Math.sin(deg2rad(currentLat)) * Math.sin(deg2rad(targetLat)) + Math.cos(deg2rad(currentLat)) * Math.cos(deg2rad(targetLat)) * Math.cos(deg2rad(theta));

        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        dist = dist * 1.609344;

        return dist;
    }

    // This function converts decimal degrees to radians
    private static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    // This function converts radians to decimal degrees
    private static double rad2deg(double rad) {
        return (rad * 180 / Math.PI);
    }
}

