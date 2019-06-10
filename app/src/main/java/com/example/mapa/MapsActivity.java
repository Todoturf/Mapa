package com.example.mapa;

import android.content.res.Resources;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
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
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        LatLng hipodromo = new LatLng(43.26363, -2.023761);
        mMap.addMarker(new MarkerOptions().position(hipodromo)
                .title("Hipódromo de San Sebastián")
                .snippet("El hipódromo estrella"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(hipodromo));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(hipodromo));
        LatLng hipodromo2 = new LatLng(43.20, -2.00);
        mMap.addMarker(new MarkerOptions().position(hipodromo2)
                .title("Hipódromo de San Sebastián")
                .snippet("El hipódromo estrella"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(hipodromo2));

        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        try {
            // Customise the styling of the base map using a JSON object defined
            // in a raw resource file.
            boolean success = googleMap.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(
                            this, R.raw.style_json));

            if (!success) {
                Log.e("Mapa", "Style parsing failed.");
            }
        } catch (Resources.NotFoundException e) {
            Log.e("Mapa", "No puedo encontrar el estilo. Error: ", e);
        }

    }
}
