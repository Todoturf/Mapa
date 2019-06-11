package com.example.mapa;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.OnStreetViewPanoramaReadyCallback;
import com.google.android.gms.maps.StreetViewPanorama;
import com.google.android.gms.maps.StreetViewPanoramaFragment;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class StreetViewActivity extends FragmentActivity implements OnStreetViewPanoramaReadyCallback
{

    private StreetViewPanorama streetView;

    LatLng posicion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_street_view);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.

        StreetViewPanoramaFragment streetViewPanoramaFragment =
                (StreetViewPanoramaFragment) getFragmentManager()
                        .findFragmentById(R.id.fragmentStreet);
        streetViewPanoramaFragment.getStreetViewPanoramaAsync(this);

        Bundle extras = getIntent().getExtras();
        double latitud = extras.getDouble("latitud");
        double longitud = extras.getDouble("longitud");
        posicion = new LatLng(latitud, longitud);
    }

    @Override
    public void onStreetViewPanoramaReady(StreetViewPanorama streetViewPanorama)
    {
        streetView = streetViewPanorama;
        //LatLng hipodromo = new LatLng(43.26363, -2.023761);
        streetView.setPosition(posicion);
    }
}
