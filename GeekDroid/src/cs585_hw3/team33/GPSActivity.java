package cs585_hw3.team33;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;

public class GPSActivity extends MapActivity 
{    
    private LocationManager lm;
    private LocationListener locationListener;

    private MapView mapView;
    private MapController mc;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.browse_map); 
        
        //---use the LocationManager class to obtain GPS locations---
        lm = (LocationManager) 
            getSystemService(Context.LOCATION_SERVICE);    
        
        locationListener = new MyLocationListener();
        
       
        mapView = (MapView) findViewById(R.id.mapView);
        mc = mapView.getController();

        Toast.makeText(getBaseContext(), 
                "Test",Toast.LENGTH_SHORT).show();
    }
    
    @Override
    public void onResume() {
    	 super.onResume();
    	 lm.requestLocationUpdates(
    	            LocationManager.GPS_PROVIDER, 
    	            1000, 
    	            0, 
    	            locationListener);    	        
    }
    @Override
    public void onPause() {
    	super.onPause();
    	lm.removeUpdates(locationListener);
    	
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