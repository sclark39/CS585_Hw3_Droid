package cs585_hw3.team33.browse;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class BrowseActivity extends Activity {
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TextView textview = new TextView(this);
        textview.setText("This is the Browse tab");
        setContentView(textview);
    }
}
