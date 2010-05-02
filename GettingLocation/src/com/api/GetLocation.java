package com.api;

import android.os.Bundle;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.widget.Toast;

public class GetLocation extends MapActivity 
{    
    private LocationManager lm;
    private LocationListener locationListener;

    private MapView mapView;
    private MapController mc;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main); 
        
        //---use the LocationManager class to obtain GPS locations---
        lm = (LocationManager) 
            getSystemService(Context.LOCATION_SERVICE);    
        
        locationListener = new MyLocationListener();
        
        lm.requestLocationUpdates(
            LocationManager.GPS_PROVIDER, 
            0, 
            0, 
            locationListener);
        
       // mapView = (MapView) findViewById(R.id.mapview1);
       // mc = mapView.getController();

    }

    @Override
    protected boolean isRouteDisplayed() {
        // TODO Auto-generated method stub
        return false;
    }        
    
    private class MyLocationListener implements LocationListener 
    {
        @Override
        public void onLocationChanged(Location loc) {
        	double temp1= loc.getLatitude();
        	double temp2= loc.getLongitude();
            if (loc != null) {                
                Toast.makeText(getBaseContext(), 
                    "Location changed : Lat: " + loc.getLatitude() + 
                    " Lng: " + loc.getLongitude(), 
                    Toast.LENGTH_SHORT).show();
                
                GeoPoint p = new GeoPoint(
                        (int) (loc.getLatitude() * 1E6), 
                        (int) (loc.getLongitude() * 1E6));
                mc.animateTo(p);
                mc.setZoom(16);                
                mapView.invalidate();
            }
        }

        @Override
        public void onProviderDisabled(String provider) {
            // TODO Auto-generated method stub
        }

        @Override
        public void onProviderEnabled(String provider) {
            // TODO Auto-generated method stub
        }

        @Override
        public void onStatusChanged(String provider, int status, 
            Bundle extras) {
            // TODO Auto-generated method stub
        }
    }    
}
