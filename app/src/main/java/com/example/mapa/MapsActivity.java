package com.example.mapa;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener, GoogleMap.OnMarkerDragListener, GoogleMap.OnInfoWindowClickListener {

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

        mMap.setOnMapClickListener(this);

        mMap.setOnMarkerDragListener(this);

        mMap.setOnInfoWindowClickListener(this);

        LatLng hipodromo = new LatLng(43.26363, -2.023761);
        Marker marcadorHipodromo = mMap.addMarker(new MarkerOptions()
                .position(hipodromo)
                .title("Hipódromo de San Sebastián")
                .snippet("El hipódromo de las estrellas")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.horse))
                .draggable(true)
                .flat(true)
                 );
        mMap.moveCamera(CameraUpdateFactory.newLatLng(hipodromo));

        LatLng hipodromo2 = new LatLng(43.261028, -2.022826);
        Marker marcadorHipodromo2 = mMap.addMarker(new MarkerOptions()
                .position(hipodromo2)
                .title("Hipódromo de San Sebastián")
                .snippet("Para darse un paseo")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.horse))
                .draggable(true)
                .flat(true)
        );
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

    @Override
    public void onMapClick(LatLng latLng)
    {
        mMap.addMarker(new MarkerOptions()
        .position(latLng)
                .title("Nueva posición")
                .draggable(true)
                .flat(true)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.home))
        );
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
    }

    @Override
    public void onMarkerDragStart(Marker marker)
    {
        marker.hideInfoWindow();
    }

    @Override
    public void onMarkerDrag(Marker marker)
    {

    }

    @Override
    public void onMarkerDragEnd(Marker marker)
    {
        LatLng posicion = marker.getPosition();
        marker.setSnippet(posicion.latitude + ", " + posicion.longitude);
        marker.showInfoWindow();
    }

    @Override
    public void onInfoWindowClick(Marker marker)
    {
        //Toast.makeText(MapsActivity.this, marker.getTitle(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(MapsActivity.this, StreetViewActivity.class);
        intent.putExtra("latitud", marker.getPosition().latitude);
        intent.putExtra("longitud", marker.getPosition().longitude);
        startActivity(intent);
    }
}
