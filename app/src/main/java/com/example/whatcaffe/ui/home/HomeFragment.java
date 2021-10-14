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
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;


public class HomeFragment extends Fragment implements MapView.CurrentLocationEventListener, MapView.MapViewEventListener {

    private FragmentHomeBinding binding;
    int buttonIndex = 0;
    private Context context;
    private MapView mapView;
    private ViewGroup mapViewContainer;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        context = container.getContext();

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // 맵을 HomeFragment에 표시
        mapView = new MapView(getActivity());
        mapViewContainer = root.findViewById(R.id.map_view);
        mapViewContainer.addView(mapView);

        mapView.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOnWithoutHeading);


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