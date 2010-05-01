package cs585_hw3.team33.post;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import cs585_hw3.team33.R;
import cs585_hw3.team33.lib.ProgressRunnable;

public class PostActivity extends Activity {

	public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.post);
        
        Button button = (Button)findViewById(R.id.PostButton);
        button.setOnClickListener( submitListener );
	}
	
	public void submitPost() {

        String s = ((EditText)findViewById(R.id.PostText)).getText().toString();
		System.out.println(s);
		
		try{
            Thread.sleep(1000); // We need to remove this before we submit.
		 } catch (Exception e) { 
        }
	}
	
	Activity me = this;
	private OnClickListener submitListener = new OnClickListener() {
		public void onClick(View v) {
			ProgressRunnable getResults = 
				new ProgressRunnable("Please wait...", "Submitting blog post ...") {
					public void onGo() {
						submitPost();
					}
					public void onEnd() {	
						((EditText)findViewById(R.id.PostText))
							.setText("");
					}
			};
			getResults.startThread(me,"BackgroundManageDB");
		}
	};
}
