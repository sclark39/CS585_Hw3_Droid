package cs585_hw3.team33.browse.map;
 
import android.os.Bundle;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;

import cs585_hw3.team33.R;

public class ShowResultsMapActivity extends MapActivity {
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.browse_map);
	    
	    // Set Up Map View
	    MapView mapView = (MapView) findViewById(R.id.mapview);
	    mapView.setBuiltInZoomControls(true);
	    ItemizedBlogOverlay iconLayer = new ItemizedBlogOverlay(
	    		this.getResources().getDrawable(R.drawable.icon),this);
	    mapView.getOverlays().add(iconLayer);
	    
	    // Add the Icons
	    /*ArrayList<Result> result_list = (ArrayList<Result>)this.getIntent().getExtras().get("results");
	    OverlayItem icon;
	    Result res;
	    for (int i = 0; i < result_list.size(); i++)	{
	    	res = result_list.get(i);
	    	icon = new OverlayItem(
		    		new GeoPoint(res.x, res.y), 
		    		"Blog Post #"+res.id,
		    		res.text);
	    	iconLayer.addOverlay(icon);
	    }*/
	    OverlayItem overlayitem = new OverlayItem(
	    		new GeoPoint(19240000,-99120000), 
	    		"Hola, Mundo!", "I'm in Mexico City!");
	    iconLayer.addOverlay(overlayitem);
	    
	    OverlayItem overlayitem2 = new OverlayItem(
	    		new GeoPoint(35410000, 139460000), 
	    		"Sekai, konichiwa!", "I'm in Japan!");	    
	    iconLayer.addOverlay(overlayitem2);
	    
	}
	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}

}
