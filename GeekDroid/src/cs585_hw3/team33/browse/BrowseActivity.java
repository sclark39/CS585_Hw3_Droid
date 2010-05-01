package cs585_hw3.team33.browse;

import java.util.ArrayList;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import cs585_hw3.team33.R;

public class BrowseActivity extends ListActivity {
	ArrayList<Result> result_list = null;
	ResultAdapter result_adapt;
	
	ProgressDialog viewResultsProgress = null;
	
	public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.browse);
        
        Button button = (Button)findViewById(R.id.QueryButton);
        button.setOnClickListener( new OnClickListener() {
        	public void onClick(View v) {
        		executeQuery();
        	}
        });
        
        result_list = new ArrayList<Result>();
        result_adapt = new ResultAdapter(this, R.layout.browse_row, result_list);
        setListAdapter(result_adapt);
        
	}
	
	public void executeQuery() {  
		result_list.clear();
        
		Runnable getResults = new Runnable() {
			@Override
			public void run() {
				
				String keywords = ((EditText)findViewById(R.id.keywordTxt)).getText().toString();
				String kStr = ((EditText)findViewById(R.id.kTxt)).getText().toString();
				int k = (kStr.equals("")? Integer.MAX_VALUE : Integer.parseInt(kStr));
		          
		        try{
		             Result r;
		             
		             for (int i = 1; i < 10 && i <= k; i++) {
						 r = new Result(i,45,50,"This is a long line of stuff that I am telling you about right now I hope you enjoy it.");
						 result_list.add(r);
					 }
					 
					 Thread.sleep(500); // We need to remove this before we submit.
					 
					 Log.i("ARRAY", ""+ result_list.size());
				 } catch (Exception e) { 
					 Log.e("BACKGROUND_PROC", e.getMessage());
		         }
				 runOnUiThread(returnRes);
			}
		};
		Thread t = new Thread(null, getResults, "BackgroundQuery");
        
        t.start();
        viewResultsProgress = ProgressDialog.show(this, 
        		"Please wait...", "Retrieving data ...", true);
	}
	
	 private Runnable returnRes = new Runnable() {	
        @Override
        public void run() {
            viewResultsProgress.dismiss();
            result_adapt.notifyDataSetChanged();
        }
    };
}
