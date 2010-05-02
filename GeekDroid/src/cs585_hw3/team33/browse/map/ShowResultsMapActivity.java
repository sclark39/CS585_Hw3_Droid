package cs585_hw3.team33.browse.map;
 
import java.util.ArrayList;

import android.os.Bundle;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;

import cs585_hw3.team33.R;
import cs585_hw3.team33.browse.list.Result;

public class ShowResultsMapActivity extends MapActivity {
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.browse_map);
	    Bundle extras = this.getIntent().getExtras();
	    
	    // Set Up Map View
	    MapView mapView = (MapView) findViewById(R.id.mapView);
	    mapView.setBuiltInZoomControls(true);
	   
	    // Add the Icons
	    @SuppressWarnings("unchecked")
		ArrayList<Result> result_list = (ArrayList<Result>)extras.get("results");
	    
	    if (result_list.size() > 0) {
	    	 ItemizedBlogOverlay iconLayer = new ItemizedBlogOverlay( 
	 	    		this.getResources().getDrawable(R.drawable.icon),this);
	 	    mapView.getOverlays().add(iconLayer);
	 	    
	 	    OverlayItem icon;
		    Result res;
		    for (int i = 0; i < result_list.size(); i++)	{
		    	res = result_list.get(i);
		    	icon = new OverlayItem(
			    		new GeoPoint(res.x, res.y), 
			    		"Blog Post #"+res.id,
			    		res.text);
		    	iconLayer.addOverlay(icon);
		    }

	    }
	    
	    MapController mc = mapView.getController();
    	if (extras.containsKey("z_x")) {
	    	mc.animateTo(new GeoPoint(
	    			extras.getInt("z_x"),
	    			extras.getInt("z_y") ));
	    	mc.setZoom(16);
	    	mapView.invalidate();
	    } else
	    	mc.setZoom(3);
	}
	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}

}
