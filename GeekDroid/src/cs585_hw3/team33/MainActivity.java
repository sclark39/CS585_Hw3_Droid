package cs585_hw3.team33;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TabHost;
import cs585_hw3.team33.browse.list.MainBrowseActivity;
import cs585_hw3.team33.manage.ManageActivity;
import cs585_hw3.team33.post.PostActivity;

public class MainActivity extends TabActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
        Resources res = getResources(); // Resource object to get Drawables
        TabHost tabHost = getTabHost();  // The activity TabHost
        TabHost.TabSpec spec;  // Resusable TabSpec for each tab
        Intent intent;  // Reusable Intent for each tab

        // Initialize a TabSpec for each tab and add it to the TabHost
        intent = new Intent().setClass(this, ManageActivity.class);
        spec = tabHost.newTabSpec("manage")
        .setIndicator(res.getText(R.string.manage_tab_lbl),null)
        .setContent(intent);
        tabHost.addTab(spec);

        // Do the same for the other tabs
        intent = new Intent().setClass(this, PostActivity.class);
        spec = tabHost.newTabSpec("post")
        .setIndicator(res.getText(R.string.post_tab_lbl),null)
        .setContent(intent);
        tabHost.addTab(spec);

        intent = new Intent().setClass(this, MainBrowseActivity.class);
        spec = tabHost.newTabSpec("browse")
        .setIndicator(res.getText(R.string.browse_tab_lbl),null)
        .setContent(intent);
        tabHost.addTab(spec);

        tabHost.setCurrentTab(0);
    }
}