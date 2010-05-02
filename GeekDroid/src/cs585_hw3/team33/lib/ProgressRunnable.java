package cs585_hw3.team33.lib;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.util.Log;
import android.widget.Toast;

abstract public class ProgressRunnable {
	abstract public void onGo();
	abstract public void onEnd();
	
	Activity caller;
	ProgressDialog dialog = null;
	Thread t;	
	CharSequence m1,m2;
	
	public ProgressRunnable(CharSequence m1, CharSequence m2) {
		this.m1 = m1;
		this.m2 = m2;
	}
	public void startThread(Activity caller, String threadName) {
		this.caller = caller;
		
		t = new Thread(null, goRun, threadName);
        t.start();        
        dialog = ProgressDialog.show(caller, m1, m2, true);
        
	}
	public Thread getThread() {
		return t;
	}
	
	private ArrayList<Toast> toastDialogs = new ArrayList<Toast>();
	public void reportToast(String message) {
		toastDialogs.add( Toast.makeText(caller, message, Toast.LENGTH_SHORT) );
	}
	private String reportMessage = null;
	public void reportAlert(String message) {
		if (reportMessage == null) 
			reportMessage = message;
	}
	public void makeAlertToast() {
		if (reportMessage != null)  {
			reportToast(reportMessage);
			reportMessage = null;
		}
	}
	private Runnable goRun = new Runnable() {
		public void run() {
			onGo();
			caller.runOnUiThread(endRun);
		}
	};
			
	 private Runnable endRun = new Runnable() {	
        @Override
        public void run() {
        	onEnd();        	
        	dialog.dismiss();
        	if (reportMessage != null) {
        		AlertDialog reportDialog;
    			try {
    				reportDialog = new AlertDialog.Builder(caller).create();
    				reportDialog.setTitle("Error");
    				reportDialog.setMessage(reportMessage);
    				reportDialog.setButton("OK", new DialogInterface.OnClickListener(){
    					public void onClick(DialogInterface dialog, int which) {
    						return;
    					}
    				});
        			reportDialog.show();
    			}
    			catch (Exception e) {
    				Log.d("fsdar",e.toString());
    			};
        	}
        	else {
        		for (Toast t : toastDialogs)
        			t.show();        		
        	}
        }
    };

}
