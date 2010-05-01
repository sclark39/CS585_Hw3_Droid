package cs585_hw3.team33.post;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import cs585_hw3.team33.R;

public class PostActivity extends Activity {

	public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.post);
        
        Button button = (Button)findViewById(R.id.PostButton);
        button.setOnClickListener( new OnClickListener() {
        	public void onClick(View v) {
        		submitPost();
        		
        	}
        });
	}
	
	public void submitPost() {

		EditText txt = (EditText)findViewById(R.id.PostText);
        String s = txt.getText().toString();
		System.out.println(s);
		
	}
}
