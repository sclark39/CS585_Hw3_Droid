package cs585_hw3.team33.browse.list;

import java.util.ArrayList;

import cs585_hw3.team33.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ResultAdapter extends ArrayAdapter<Result> {

	private ArrayList<Result> items;
	private int rowLayoutID;
	
	public ResultAdapter(Context context, int rowLayoutID,
			ArrayList<Result> items) {
		super(context, rowLayoutID, items);
		this.items = items;
		this.rowLayoutID = rowLayoutID;
		// TODO Auto-generated constructor stub
	}

	@Override
    public View getView(int position, View convertView, ViewGroup parent) {
            View v = convertView;
            if (v == null) {
                LayoutInflater vi = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = vi.inflate(this.rowLayoutID, null);
            }
            Result r = items.get(position);
            if (r != null) {
                    TextView idTxt = (TextView) v.findViewById(R.id.ID_Text);
                    TextView locTxt = (TextView) v.findViewById(R.id.Loc_Text);
                    TextView blogTxt = (TextView) v.findViewById(R.id.Blog_Text);
                    
                    if (idTxt != null) 
                    	idTxt.setText("Blog #"+ r.id);                            
                    if(locTxt != null)
                        locTxt.setText("("+ r.x + ", " + r.y + ")");
                    if(blogTxt != null)
                        blogTxt.setText(r.text);

            }
            
            return v;
    }
	
}
