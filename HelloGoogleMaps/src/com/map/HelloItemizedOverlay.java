package com.map;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;

import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;


public class HelloItemizedOverlay extends ItemizedOverlay {
	Context mContext;
	
	private ArrayList<OverlayItem> mOverlays = new ArrayList<OverlayItem>();	
	public HelloItemizedOverlay(Drawable defaultMarker, Context context,OverlayItem overlayitem2 ) {
		
		super(boundCenterBottom(defaultMarker));
		mContext = context;
		// TODO Auto-generated constructor stub
	}

	
	@Override
	protected OverlayItem createItem(int i) {
		return mOverlays.get(i);
	}

	@Override
	public int size() {
		return mOverlays.size();
		
	}
		  
		
	public void addOverlay(OverlayItem overlay) {
	    mOverlays.add(overlay);
	    populate();
	    
	}
	@Override
	protected boolean onTap(int index) {
	  OverlayItem item = mOverlays.get(index);
	  AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
	  dialog.setTitle(item.getTitle());
	  dialog.setMessage(item.getSnippet());
	  dialog.show();
	  return true;
	}
	
}
