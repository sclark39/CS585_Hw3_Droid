package cs585_hw3.team33.browse.map;
 
import java.util.List;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

import cs585_hw3.team33.R;

public class ShowResultsOnMapActivity extends MapActivity {
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.browse_map);
	    MapView mapView = (MapView) findViewById(R.id.mapview);
	    mapView.setBuiltInZoomControls(true);
	    List<Overlay> mapOverlays = mapView.getOverlays();
	    Drawable drawable = this.getResources().getDrawable(R.drawable.icon);
	    HelloItemizedOverlay itemizedoverlay = new HelloItemizedOverlay(drawable,null,null);
	    mapOverlays.add(itemizedoverlay);
	    GeoPoint point = new GeoPoint(19240000,-99120000);
	    OverlayItem overlayitem = new OverlayItem(point, "Hola, Mundo!", "I'm in Mexico City!");
	    GeoPoint point2 = new GeoPoint(35410000, 139460000);
	    itemizedoverlay.addOverlay(overlayitem);
	    
	    OverlayItem overlayitem2 = new OverlayItem(point2, "Sekai, konichiwa!", "I'm in Japan!");	    
	    itemizedoverlay.addOverlay(overlayitem2);
	    
	}
	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

}
