package cs585_hw3.team33.browse;

import java.util.ArrayList;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import cs585_hw3.team33.R;

public class BrowseActivity extends ListActivity {
	ArrayList<Result> result_list = null;
	ResultAdapter result_adapt;
	
	ProgressDialog viewResultsProgress = null;
	
	public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.browse);
        
        //android.R.drawable.divider_horizontal_dim_dark
        result_list = new ArrayList<Result>();
        result_adapt = new ResultAdapter(this, R.layout.browse_row, result_list);
        setListAdapter(result_adapt);
        
        Thread t = new Thread(null, viewResults, "MagentoBackground");
        t.start();
        viewResultsProgress = ProgressDialog.show(this, 
        		"Please wait...", "Retrieving data ...", true);
        //android.R.drawable.list_selector_background
        
        
	}
	
	private Runnable viewResults = new Runnable() {
		@Override
		public void run() {
			 try{
	             Result r;
	             
	             r = new Result(1,45,50,"Text Stuff");
				 result_list.add(r);
				 r = new Result(2,45,50,"More Stuff");
				 result_list.add(r);
				 for (int i = 3; i < 10; i++) {
					 r = new Result(i,45,50,"This is a long line of stuff that I am telling you about right now I hope you enjoy it.");
					 result_list.add(r);
				 }
				 
				 Thread.sleep(5000);
				 Log.i("ARRAY", ""+ result_list.size());
	   } catch (Exception e) { 
	     Log.e("BACKGROUND_PROC", e.getMessage());
	           }
	           runOnUiThread(returnRes);
		}
	};
	 private Runnable returnRes = new Runnable() {	
        @Override
        public void run() {
            viewResultsProgress.dismiss();
            result_adapt.notifyDataSetChanged();
        }
    };
}
