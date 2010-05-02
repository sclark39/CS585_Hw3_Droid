package cs585_hw3.team33;

import java.util.List;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TabHost;
import cs585_hw3.team33.browse.BrowseActivity;
import cs585_hw3.team33.lib.DatabaseHelper;
import cs585_hw3.team33.manage.ManageActivity;
import cs585_hw3.team33.post.PostActivity;

public class MainActivity extends TabActivity {
	public DatabaseHelper dh;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
        dh = new DatabaseHelper(this);
  /*      // Initialize the Database
        dh = new DataHelper(this);
        
        if (!dh.IsOpen())
        	dh.CreateDB();
        
        dh.deleteAll();
        
        dh.insert(1,"500","600","Nasrullah Husami is a good boy");
        dh.insert(2,"300","400","Anirudh Rekhi is a bad boy");
        dh.insert(3,"700","800","Skyler Clark is a geek");

        List<String> Microblog_Entry_Tuples = this.dh.selectAll();
        StringBuilder sb = new StringBuilder();
        sb.append("Names in database:\n");
        for (String name : Microblog_Entry_Tuples) {
           sb.append(name + "\n");
        }
        */
        
      // Make the Tabs        
        Resources res = getResources(); // Resource object to get Drawables
        TabHost tabHost = getTabHost();  // The activity TabHost
        TabHost.TabSpec spec;
        Intent intent;  

        // Initialize a TabSpec for each tab and add it to the TabHost
        intent = new Intent().setClass(this, ManageActivity.class);
        spec = tabHost.newTabSpec("manage")
        .setIndicator(res.getText(R.string.manage_tab_lbl),null)
        .setContent(intent);
        tabHost.addTab(spec);

        intent = new Intent().setClass(this, PostActivity.class);
        spec = tabHost.newTabSpec("post")
        .setIndicator(res.getText(R.string.post_tab_lbl),null)
        .setContent(intent);
        tabHost.addTab(spec);

        intent = new Intent().setClass(this, BrowseActivity.class);
        spec = tabHost.newTabSpec("browse")
        .setIndicator(res.getText(R.string.browse_tab_lbl),null)
        .setContent(intent);
        tabHost.addTab(spec);

        tabHost.setCurrentTab(0);
    }
}