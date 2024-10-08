package com.example.greenify;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.greenify.ImageCarouselAdapter;
import com.example.greenify.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Timer;
import java.util.TimerTask;

public class HomeFragment extends Fragment implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ViewPager2 viewPager;
    private int[] images = {R.drawable.julio_quijano, R.drawable.farm1, R.drawable.farm2}; // Add your image resources here
    private int currentPage = 0;
    private Timer timer;
    private final long DELAY_MS = 500; // Delay in milliseconds before the carousel starts
    private final long PERIOD_MS = 2000; // Period of carousel swipe in milliseconds

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Set up ViewPager for the image carousel
        viewPager = view.findViewById(R.id.viewPager);
        ImageCarouselAdapter adapter = new ImageCarouselAdapter(images);
        viewPager.setAdapter(adapter);

        // Automate the carousel
        final Handler handler = new Handler();
        final Runnable update = () -> {
            if (currentPage == images.length) {
                currentPage = 0;
            }
            viewPager.setCurrentItem(currentPage++, true);
        };

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(update);
            }
        }, DELAY_MS, PERIOD_MS);

        // Set up the map
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }

        return view;
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;

        // Coordinates for Quijano Fruits and Vegetables Farm
        LatLng quijanoFarm = new LatLng(10.9406131, 123.3538836);

        // Add a marker at Quijano Farm and move the camera
        mMap.addMarker(new MarkerOptions().position(quijanoFarm).title("Quijano Fruits and Vegetables Farm"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(quijanoFarm, 15)); // Zoom level 15 for a closer view
    }
}
