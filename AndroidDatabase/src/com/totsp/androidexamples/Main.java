package com.totsp.androidexamples;

import android.app.Activity;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

public class Main extends Activity {
    
   private TextView output;
   
   private DataHelper dh;
   
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        this.output = (TextView) this.findViewById(R.id.out_text);
        
                this.dh = new DataHelper(this);
        this.dh.deleteAll();
        
        this.dh.insert(1,"500","600","Nasrullah Husami is a good boy");
        this.dh.insert(2,"300","400","Anirudh Rekhi is a bad boy");
        this.dh.insert(3,"700","800","Skyler Clark is a geek");

        List<String> Microblog_Entry_Tuples = this.dh.selectAll();
        StringBuilder sb = new StringBuilder();
        sb.append("Names in database:\n");
        for (String name : Microblog_Entry_Tuples) {
           sb.append(name + "\n");
        }
        
        sb.append("Fetching 2nd name from Database:\n");
        List<String> Microblog_Entry_String = this.dh.fetchNote(1);
        sb.append(Microblog_Entry_String + "\n");
        
        Log.d("EXAMPLE", "names size - " + Microblog_Entry_String.size());
        
        this.output.setText(sb.toString());
    }
}
