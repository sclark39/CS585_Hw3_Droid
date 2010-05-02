package cs585_hw3.team33.manage;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import cs585_hw3.team33.MainActivity;
import cs585_hw3.team33.R;
import cs585_hw3.team33.lib.ProgressRunnable;

public class ManageActivity extends Activity {
	public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.manage);
        
        findViewById(R.id.CreateButton)
        	.setOnClickListener( createListener );
        findViewById(R.id.PopulateButton)
	    	.setOnClickListener( popListener );
        findViewById(R.id.DropButton)
	    	.setOnClickListener( dropListener );
    }
	
	public void createDB(ProgressRunnable pr) {
		// fill in
		MainActivity m = ((MainActivity)this.getParent());
		if (!m.dh.isOpen())
			m.dh.createDB();
        
		try{		
			Thread.sleep(1000); // We need to remove this before we submit.
		 } catch (Exception e) { 
        }
	}
	public void populateDB(ProgressRunnable pr) {
		MainActivity m = ((MainActivity)this.getParent());
		if (m.dh.isOpen())
			m.dh.populateDB();
		else
			pr.reportAlert("No database open to populate.");
        
		// fill in
		try{
			Thread.sleep(1000); // We need to remove this before we submit.
		 } catch (Exception e) { 
        }
	}
	public void dropDB(ProgressRunnable pr) {
		MainActivity m = ((MainActivity)this.getParent());
		if (m.dh.isOpen())
			m.dh.dropDB();
		else
			pr.reportAlert("No database open to drop.");
		
		// fill in
		try{			
			Thread.sleep(1000); // We need to remove this before we submit.
		 } catch (Exception e) { 
        }
	}
	
	

	Activity me = this;
	private OnClickListener createListener = new OnClickListener() {
		public void onClick(View v) {
			ProgressRunnable getResults = 
				new ProgressRunnable("Please wait...", "Creating database ...") {
					public void onGo() {
						createDB(this);
					}
					public void onEnd() {			            					
					}
			};
			getResults.startThread(me,"BackgroundManageDB");
		}
	};
	private OnClickListener popListener = new OnClickListener() {
		public void onClick(View v) {
			ProgressRunnable getResults = 
				new ProgressRunnable("Please wait...", "Populating database ...") {
					public void onGo() {
						populateDB(this);
					}
					public void onEnd() {			            					
					}
			};
			getResults.startThread(me,"BackgroundManageDB");
		}
	};		
	private OnClickListener dropListener = new OnClickListener() {
		public void onClick(View v) {
			ProgressRunnable getResults = 
				new ProgressRunnable("Please wait...", "Dropping database ...") {
					public void onGo() {
						dropDB(this);
					}
					public void onEnd() {			            					
					}
			};
			getResults.startThread(me,"BackgroundManageDB");
		}
	};	
	
}
