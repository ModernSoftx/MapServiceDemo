package com.nain.mapservicedemo;


import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.nain.mapservicedemo.databinding.ActivityMapsBinding;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    UiSettings mapSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng chicago = new LatLng(41.8781, -87.6298);   //Negative for W (west) or S (south)
        mMap.addMarker(new MarkerOptions().position(chicago).title("Chicago, Illinois"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(chicago));

        //mMap.animateCamera(CameraUpdateFactory.zoomTo(15));  // 10 for City 15 for Streets


        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener(){
            @Override
            public void onMapClick(@NonNull LatLng point){

                Geocoder geoCoder = new Geocoder(getBaseContext(), Locale.getDefault());

                try {
                    List<Address> addresses = geoCoder.getFromLocation(point.latitude, point.longitude, 1);   // 1 : only 1 location is needed
                    String add = "";
                    if (addresses.size() > 0){
                        for (int i = 0; i <= addresses.get(0).getMaxAddressLineIndex(); i++)
                            add += addresses.get(0).getAddressLine(i) + "\n";
                    }
                    Toast.makeText(getBaseContext(), add, Toast.LENGTH_SHORT).show();

                } catch (IOException e){
                    e.printStackTrace();
                }

            }
        });





        mapSettings = mMap.getUiSettings();
        mapSettings.setZoomControlsEnabled(true);


    }
}