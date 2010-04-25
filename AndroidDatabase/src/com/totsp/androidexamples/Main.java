package com.totsp.androidexamples;

import android.app.Activity;
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
        this.dh.insert("Nasrullah Husami");
        this.dh.insert("Anirudh Rekhi");
        this.dh.insert("Skyler Clark");
        List<String> names = this.dh.selectAll();
        StringBuilder sb = new StringBuilder();
        sb.append("Names in database:\n");
        for (String name : names) {
           sb.append(name + "\n");
        }
        
        sb.append("Fetching 2nd name from Database:\n");
        List<String> name1 = this.dh.fetchNote(2);
        sb.append(name1 + "\n");
        
        Log.d("EXAMPLE", "names size - " + names.size());
        
        this.output.setText(sb.toString());
        
    }
}
