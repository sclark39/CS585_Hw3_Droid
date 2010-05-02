package cs585_hw3.team33.browse.list;

import java.util.ArrayList;

import android.app.Activity;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import cs585_hw3.team33.R;
import cs585_hw3.team33.browse.list.Result;
import cs585_hw3.team33.browse.list.ResultAdapter;
import cs585_hw3.team33.lib.ProgressRunnable;

public class MainBrowseActivity extends ListActivity {
	ArrayList<Result> result_list = null;
	ResultAdapter result_adapt;
	
	ProgressDialog viewResultsProgress = null;
	
	public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.browse);
        
        ((Button)findViewById(R.id.QueryButton))
        	.setOnClickListener( queryListener );
        
        result_list = new ArrayList<Result>();
        result_adapt = new ResultAdapter(this, R.layout.browse_row, result_list);
        setListAdapter(result_adapt);
        
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
	
	Activity me = this;
	private OnClickListener queryListener = new OnClickListener() {
		public void onClick(View v) {
			result_list.clear();
			ProgressRunnable getResults = 
				new ProgressRunnable("Please wait...", "Finding blogs...") {
					@Override
					public void onGo() {
						executeQuery();
					}
					public void onEnd() {
			            result_adapt.notifyDataSetChanged();						
					}
			};
			getResults.startThread(me,"BackgroundQuery");
		}
	};
	
	
}
