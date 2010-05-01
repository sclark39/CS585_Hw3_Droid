package cs585_hw3.team33.lib;

import android.app.Activity;
import android.app.ProgressDialog;

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
        }
    };

}
