package cs585_hw3.team33.browse;

import android.app.Activity;
import android.app.ActivityGroup;
import android.content.Intent;
import android.os.Bundle;
import cs585_hw3.team33.browse.list.MainBrowseActivity;
import cs585_hw3.team33.browse.map.ShowResultsOnMapActivity;

public class BrowseActivity extends Activity {
	 
	 public void onCreate(Bundle savedInstanceState) {
		 Intent mainIntent = new Intent(this, MainBrowseActivity.class);
		 this.getApplication().startActivity(mainIntent);
		 
		// Intent mapIntent = new Intent(this, ShowResultsOnMapActivity.class);
		// startActivity(mapIntent);
	 }
}
