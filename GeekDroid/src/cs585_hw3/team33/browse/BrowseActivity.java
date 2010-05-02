package cs585_hw3.team33.browse;

import java.util.ArrayList;

import android.app.Activity;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import cs585_hw3.team33.R;
import cs585_hw3.team33.browse.list.Result;
import cs585_hw3.team33.browse.list.ResultAdapter;
import cs585_hw3.team33.browse.map.ShowResultsMapActivity;
import cs585_hw3.team33.lib.ProgressRunnable;

public class BrowseActivity extends ListActivity {
	ArrayList<Result> result_list = null;
	ResultAdapter result_adapt;
	
	ProgressDialog viewResultsProgress = null;

	Activity me = this;
	public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.browse);
        
        result_list = new ArrayList<Result>();
        result_adapt = new ResultAdapter(this, R.layout.browse_row, result_list);
        setListAdapter(result_adapt);
       
        ((Button)findViewById(R.id.QueryButton)).setOnClickListener( queryListener );
        ((Button)findViewById(R.id.MapButton)).setOnClickListener( mapListener );
	}
	

	public void executeQuery() {  
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
	}
	
	private OnClickListener queryListener = new OnClickListener() {
		public void onClick(View v) {
			ProgressRunnable getResults  
			= new ProgressRunnable("Please wait...", "Finding blogs...") {
				@Override
				public void onGo() {
					result_list.clear();
					executeQuery();
				}
				public void onEnd() {
		            result_adapt.notifyDataSetChanged();
		            ((EditText)findViewById(R.id.keywordTxt)).setText("");
		    		((EditText)findViewById(R.id.kTxt)).setText("");
		    		
				}
			};
			getResults.startThread(me,"BackgroundQuery");
		}
	};
	private OnClickListener mapListener = new OnClickListener() {
		public void onClick(View v) {			
			String keywords = ((EditText)findViewById(R.id.keywordTxt)).getText().toString();
			String kStr = ((EditText)findViewById(R.id.kTxt)).getText().toString();
			
			
			if (result_list.size() == 0 || !keywords.equals("") || !kStr.equals("")) {					
				ProgressRunnable getResults  
				= new ProgressRunnable("Please wait...", "Finding blogs...") {
					@Override
					public void onGo() {
						result_list.clear();
						executeQuery();
					}
					public void onEnd() {
			            result_adapt.notifyDataSetChanged();
			            ((EditText)findViewById(R.id.keywordTxt)).setText("");
			    		((EditText)findViewById(R.id.kTxt)).setText("");
			    		
			    		Intent mapIntent = new Intent(me, ShowResultsMapActivity.class);
			    		mapIntent.putExtra("results",result_list);
			    		startActivity(mapIntent);
					}
				};
				getResults.startThread(me,"BackgroundQuery");
			} else {
				Intent mapIntent = new Intent(me, ShowResultsMapActivity.class);
				mapIntent.putExtra("results",result_list);
				startActivity(mapIntent);
				
			}
		}
	};
	
}
