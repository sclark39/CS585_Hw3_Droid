package cs585_hw3.team33.manage;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import cs585_hw3.team33.R;

public class ManageActivity extends Activity {
	public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.manage);
        
        ((Button)findViewById(R.id.CreateButton))
        	.setOnClickListener( new OnClickListener() {
	        	public void onClick(View v) {
	        		createDB();
	        		}});
        ((Button)findViewById(R.id.PopulateButton))
	    	.setOnClickListener( new OnClickListener() {
	        	public void onClick(View v) {
	        		populateDB();
	        	}});
        ((Button)findViewById(R.id.DropButton))
	    	.setOnClickListener( new OnClickListener() {
	        	public void onClick(View v) {
	        		dropDB();
	        	}});
        
	}
	
	public void createDB() {
		// fill in
	}
	public void populateDB() {
		// fill in
	}
	public void dropDB() {
		// fill in
	}
			
	
	
}
