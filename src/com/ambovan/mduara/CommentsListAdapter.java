package com.ambovan.mduara;

import java.util.Calendar;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.provider.MediaStore.Images.Thumbnails;
import android.support.v4.widget.CursorAdapter;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class CommentsListAdapter extends CursorAdapter {
String sender,id;
	@SuppressWarnings("deprecation")
	public CommentsListAdapter(Context context, Cursor c,String sender,String id) {
		super(context, c);
		this.sender = sender;
		this.id = id;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void bindView(View view, Context context, Cursor cursor) {

		
			
		
	
	       
		//cursor.getString(cursor.getColumnIndex(cursor.getColumnName(6))).equals("text")
	
	       if(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(6))).equals("text")&&
	    		   String.valueOf(cursor.getInt(cursor.getColumnIndex(cursor.getColumnName(5)))).equals(id)){
	    	   
	    	TextView h = (TextView)view.findViewById(R.id.textView1);
	   		h.setText(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(0))));
	   		TextView h2 = (TextView)view.findViewById(R.id.textView2);
	   		h2.setText(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(3))));
	   		RelativeLayout li = (RelativeLayout)view.findViewById(R.id.parent);
	   		 
	   		 LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
	   	                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
	   	                params.gravity = Gravity.LEFT;
	   	     li.setVisibility(View.VISIBLE);
	   	       
	    	   li.setLayoutParams(params);
		        li.setBackgroundResource(R.drawable.bubble_b);
		       
	       }
	       
	       else if(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(6))).equals("text")&&
	    		   String.valueOf(cursor.getInt(cursor.getColumnIndex(cursor.getColumnName(5)))).equals(id)){
	    	   
	    	   TextView h = (TextView)view.findViewById(R.id.textView1);
	   		h.setText(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(0))));
	   		TextView h2 = (TextView)view.findViewById(R.id.textView2);
	   		h2.setText(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(3))));
	   		 RelativeLayout li = (RelativeLayout)view.findViewById(R.id.parent);
	   		 
	   		
	   	     LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(
	   	    	                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
	   	    	                params2.gravity = Gravity.RIGHT;
	   	    	               
	   	       
	   	        li.setVisibility(View.VISIBLE);
	   	       
	    	   li.setLayoutParams(params2);
		        li.setBackgroundResource(R.drawable.bubble_a);
	       }
	}

	@Override
	public View newView(Context context, Cursor cursor, ViewGroup parent) {
		 LayoutInflater inflater = LayoutInflater.from(parent.getContext());
		 View view = inflater.inflate(R.layout.listcomments, parent,false);
		return view;
	}

}
