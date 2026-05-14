package com.example.ex9;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager()
                        .findFragmentById(R.id.map);

        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // 📍 Mayiladuthurai location
        LatLng mayiladuthurai = new LatLng(11.1035, 79.6521);

        mMap.addMarker(new MarkerOptions()
                .position(mayiladuthurai)
                .title("Marker in Mayiladuthurai"));

        // 🔥 IMPORTANT: Zoom added (your PDF missed this)
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mayiladuthurai, 12));
    }
}