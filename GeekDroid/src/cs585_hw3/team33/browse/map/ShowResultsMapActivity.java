package cs585_hw3.team33.browse.map;
 
import java.util.ArrayList;
import java.util.List;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

import cs585_hw3.team33.R;
import cs585_hw3.team33.browse.list.Result;

public class ShowResultsMapActivity extends MapActivity {
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.browse_map);
	    
	    // Set Up Map View
	    MapView mapView = (MapView) findViewById(R.id.mapview);
	    mapView.setBuiltInZoomControls(true);
	    HelloItemizedOverlay iconLayer = new HelloItemizedOverlay(
	    		this.getResources().getDrawable(R.drawable.icon),
	    		null,null);
	    mapView.getOverlays().add(iconLayer);
	    
	    // Add the Icons
	    ArrayList<Result> result_list 
	      =	(ArrayList<Result>)this.getIntent().getExtras().get("results");
	    Result res;
	    OverlayItem icon;
	    for (int i = 0; i < result_list.size(); i++)	{
	    	res = result_list.get(i);
	    	icon = new OverlayItem(
		    		new GeoPoint(res.x, res.y), 
		    		"Blog Post #"+res.id,
		    		res.text);
	    	iconLayer.addOverlay(icon);
	    }
	    
	}
	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}

}
