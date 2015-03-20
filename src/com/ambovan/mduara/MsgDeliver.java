package com.ambovan.mduara;

import java.util.Calendar;

import com.ambovan.mduara.R.id;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.provider.MediaStore.Images.Thumbnails;
import android.support.v4.widget.CursorAdapter;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MsgDeliver extends CursorAdapter {
   String sender,rec,media_name;
   TextView head;
   Cursor c_all;
	@SuppressWarnings("deprecation")
	public MsgDeliver(Context context, Cursor c, String sender,String receiver) {
		super(context, c);
		this.sender = sender;
		this.rec = receiver;
		c_all = c;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void bindView(View arg0, final Context arg1, final Cursor arg2) {
		/*
		*/
		
		
		
		
		 Calendar c = Calendar.getInstance();
	        int second = c.get(Calendar.SECOND);
	        int hrs = c.get(Calendar.HOUR);
	        int min = c.get(Calendar.MINUTE);
	        int date = c.get(Calendar.DATE);
	        int month = c.get(Calendar.MONTH);
	        int yrs = c.get(Calendar.YEAR);
	        
	        String date_s =  date + "/"+ month + "/" + yrs;
	       
			
			
		TextView h = (TextView)arg0.findViewById(R.id.textView1);
		h.setText(arg2.getString(arg2.getColumnIndex(arg2.getColumnName(0))));
		TextView h2 = (TextView)arg0.findViewById(R.id.textView2);
		h2.setText(arg2.getString(arg2.getColumnIndex(arg2.getColumnName(3))));
		 TextView hv = (TextView)arg0.findViewById(R.id.textViewVideo);
			hv.setText(arg2.getString(arg2.getColumnIndex(arg2.getColumnName(3))));
			 TextView hi = (TextView)arg0.findViewById(R.id.textViewImage);
				hi.setText(arg2.getString(arg2.getColumnIndex(arg2.getColumnName(3))));
				
				
		TextView h3 = (TextView)arg0.findViewById(R.id.sender_msg);
		h3.setText(arg2.getString(arg2.getColumnIndex(arg2.getColumnName(0))));
		TextView h4 = (TextView)arg0.findViewById(R.id.sender_textDescription);
		h4.setText(" alarm imetumwa na "+arg2.getString(arg2.getColumnIndex(arg2.getColumnName(1))));
		TextView h5 = (TextView)arg0.findViewById(R.id.sender_time);
		h5.setText(arg2.getString(arg2.getColumnIndex(arg2.getColumnName(3))));
		TextView h6 = (TextView)arg0.findViewById(R.id.sender_date);
		TextView h7 = (TextView)arg0.findViewById(R.id.sender_name);
		h7.setText(arg2.getString(arg2.getColumnIndex(arg2.getColumnName(1))));
		 if(arg2.getString(arg2.getColumnIndex(arg2.getColumnName(4)))
	    		   .equals(date + "/"+ month + "/" + yrs)){
	    	   
	    	      h6.setText("today");
	              }
	       else if(arg2.getString(arg2.getColumnIndex(arg2.getColumnName(4)))
	    		   .equals((date - 1) + "/"+ month + "/" + yrs)){
	    	   
	    	   h6.setText("yesteday");
	    	   
	       }
	       
	       else if(arg2.getString(arg2.getColumnIndex(arg2.getColumnName(4)))
	    		   .equals((date - 2) + "/"+ month + "/" + yrs)){
	    	   
	    	   h6.setText("2day ago");
	       }
	       
	       else{
	    	   
	    	   h6.setText(arg2.getString(arg2.getColumnIndex(arg2.getColumnName(4))));
	       }
				
				
				
		
		ImageView v = (ImageView)arg0.findViewById(R.id.imageVideo);
		ImageView i = (ImageView)arg0.findViewById(R.id.imageImage);
		//ImageView vbt = (ImageView)arg0.findViewById(R.id.imageBroadcast);
		ImageView vbi = (ImageView)arg0.findViewById(R.id.imageBroadcast_i);
		ImageView vbv = (ImageView)arg0.findViewById(R.id.imageBroadcast_v);
		
		 RelativeLayout lp = (RelativeLayout)arg0.findViewById(R.id.parent);
		 RelativeLayout lv= (RelativeLayout)arg0.findViewById(R.id.parent_v);
		 RelativeLayout li = (RelativeLayout)arg0.findViewById(R.id.parent_i);
		 LinearLayout lbt = (LinearLayout)arg0.findViewById(R.id.all_t);
		 LinearLayout lbv = (LinearLayout)arg0.findViewById(R.id.all_v);
		 LinearLayout lbi = (LinearLayout)arg0.findViewById(R.id.all_i);
		 
		 TextView snbv = (TextView)arg0.findViewById(R.id.sender_name_v1);
		       snbv.setText(arg2.getString(arg2.getColumnIndex(arg2.getColumnName(1))));
		 TextView snbv2 = (TextView)arg0.findViewById(R.id.videoBroadcast_sendernameDescription_v);
		       snbv2.setText("video imetumwa na " + arg2.getString(arg2.getColumnIndex(arg2.getColumnName(1))));
		 TextView snbv3 = (TextView)arg0.findViewById(R.id.sender_time_v);
		       snbv3.setText(arg2.getString(arg2.getColumnIndex(arg2.getColumnName(3))));
		 TextView snbv4 = (TextView)arg0.findViewById(R.id.sender_date_v);
		 
		 TextView snbv5 = (TextView)arg0.findViewById(R.id.sender_msg_v);
	       snbv5.setText(arg2.getString(arg2.getColumnIndex(arg2.getColumnName(6))));
		      
		 media_name = arg2.getString(arg2.getColumnIndex(arg2.getColumnName(1)));
		       if(arg2.getString(arg2.getColumnIndex(arg2.getColumnName(4)))
		    		   .equals(date + "/"+ month + "/" + yrs)){
		    	   
		    	      snbv4.setText("today");
		              }
		       else if(arg2.getString(arg2.getColumnIndex(arg2.getColumnName(4)))
		    		   .equals((date - 1) + "/"+ month + "/" + yrs)){
		    	   
		    	   snbv4.setText("yesteday");
		    	   
		       }
		       
		       else if(arg2.getString(arg2.getColumnIndex(arg2.getColumnName(4)))
		    		   .equals((date - 2) + "/"+ month + "/" + yrs)){
		    	   
		    	   snbv4.setText("2day ago");
		       }
		       
		       else{
		    	   
		    	   snbv4.setText(arg2.getString(arg2.getColumnIndex(arg2.getColumnName(4))));
		       }
		 TextView snbi = (TextView)arg0.findViewById(R.id.image_sender_i);
		       snbi.setText(arg2.getString(arg2.getColumnIndex(arg2.getColumnName(1))));
		 TextView snbi2 = (TextView)arg0.findViewById(R.id.imageBroadcast_sendernameDescription_i);
		       snbi2.setText("image imetumwa na " + arg2.getString(arg2.getColumnIndex(arg2.getColumnName(1))));
		 TextView snbi3 = (TextView)arg0.findViewById(R.id.sender_time_i);
		       snbi3.setText(arg2.getString(arg2.getColumnIndex(arg2.getColumnName(3))));
		 TextView snbi4 = (TextView)arg0.findViewById(R.id.sender_date_i);
		       
		 TextView snbi5 = (TextView)arg0.findViewById(R.id.sender_msg_i);
		       snbi5.setText(arg2.getString(arg2.getColumnIndex(arg2.getColumnName(6))));
		       
		       if(arg2.getString(arg2.getColumnIndex(arg2.getColumnName(4)))
		    		   .equals(date + "/"+ month + "/" + yrs)){
		    	   
		    	      snbi4.setText("today");
		              }
		       else if(arg2.getString(arg2.getColumnIndex(arg2.getColumnName(4)))
		    		   .equals((date - 1) + "/"+ month + "/" + yrs)){
		    	   
		    	   snbi4.setText("yesteday");
		    	   
		       }
		       
		       else if(arg2.getString(arg2.getColumnIndex(arg2.getColumnName(4)))
		    		   .equals((date - 2) + "/"+ month + "/" + yrs)){
		    	   
		    	   snbi4.setText("2day ago");
		       }
		       
		       else{
		    	   
		    	   snbi4.setText(arg2.getString(arg2.getColumnIndex(arg2.getColumnName(4))));
		       }
		       
			//added for separator
			/*
			 LinearLayout lay = (LinearLayout)arg0.findViewById(R.id.ya_inje);
					LayoutInflater inflater = LayoutInflater.from(arg1);
					View view = inflater.inflate(R.layout.list_separator,lay , false);
					 lay.addView(view,0);
					 head = (TextView)arg0.findViewById(R.id.head1);
					 head.setText(arg2.getString(arg2.getColumnIndex(arg2.getColumnName(4))));
					 
					 String thisDate = arg2.getString(arg2.getColumnIndex(arg2.getColumnName(4)));
		                String prevDate = null;

		                // get previous item's date, for comparison
		                if (arg2.getPosition() > 0 && arg2.moveToPrevious()) {
		                    prevDate = arg2.getString(arg2.getColumnIndex(arg2.getColumnName(4)));
		                    arg2.moveToNext();
		                }

		                // enable section heading if it's the first one, or 
		                // different from the previous one
		                if (prevDate == null || !prevDate.equals(thisDate)) {
		                    view.setVisibility(View.VISIBLE);
		                } else {
		                    view.setVisibility(View.GONE);
		                }
		        */
		      
		if(arg2.getString(arg2.getColumnIndex(arg2.getColumnName(1))).equals(sender)&&
				arg2.getString(arg2.getColumnIndex(arg2.getColumnName(5))).equals("text")){
			
		
			if(arg2.getString(arg2.getColumnIndex(arg2.getColumnName(2))).equals("BroadCast")){
				LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
		                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		                params.gravity = Gravity.CENTER;
		                params.leftMargin = 20;
		                params.rightMargin = 20;
		            
		              
		               
		        lv.setVisibility(View.GONE);
		        li.setVisibility(View.GONE);
		        lp.setVisibility(View.GONE);
		        
		        lbv.setVisibility(View.GONE);
		        lbi.setVisibility(View.GONE);
		        lbt.setVisibility(View.VISIBLE);
		        lbt.setBackgroundResource(R.drawable.round_corner_c);
		       lbt.setLayoutParams(params);
		       
			}
			
			else{
	        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
	                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
	                params.gravity = Gravity.LEFT;
	               
	        lv.setVisibility(View.GONE);
	        li.setVisibility(View.GONE);
	        lp.setVisibility(View.VISIBLE);
	        
	        lbv.setVisibility(View.GONE);
	        lbi.setVisibility(View.GONE);
	        lbt.setVisibility(View.GONE);
	        lp.setBackgroundResource(R.drawable.bubble_b);
	       lp.setLayoutParams(params);
	       
			}
	       
	      
		}
		else if(arg2.getString(arg2.getColumnIndex(arg2.getColumnName(1))).equals(sender)&&
				arg2.getString(arg2.getColumnIndex(arg2.getColumnName(5))).equals("media_video")){
			
          if(arg2.getString(arg2.getColumnIndex(arg2.getColumnName(2))).equals("BroadCast")){
        	  
        	  LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
  	                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
  	                params.gravity = Gravity.CENTER;
  	                params.leftMargin = 20;
	                params.rightMargin = 20;
  	       
  	                
        	lv.setVisibility(View.GONE);
  	        lp.setVisibility(View.GONE);
  	        li.setVisibility(View.GONE);
  	        
  	        lbt.setVisibility(View.GONE);
	        lbi.setVisibility(View.GONE);
	        lbv.setVisibility(View.VISIBLE);
  	        lbv.setBackgroundResource(R.drawable.round_corner_c);
  	        lbv.setLayoutParams(params);
  	       
  	       Bitmap bmThumbnail;
  	        bmThumbnail = ThumbnailUtils.createVideoThumbnail(
  	        		arg2.getString(arg2.getColumnIndex(arg2.getColumnName(0))),
  	        		Thumbnails.MICRO_KIND);
  	        vbv.setImageBitmap(bmThumbnail);
  	        
  	       
			}
			
			else{
			
	        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
	                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
	                params.gravity = Gravity.LEFT;
	                
	       
	       
	        lv.setVisibility(View.VISIBLE);
	        lp.setVisibility(View.GONE);
	        li.setVisibility(View.GONE);
	        lbv.setVisibility(View.GONE);
	        lbi.setVisibility(View.GONE);
	        lbt.setVisibility(View.GONE);
	        lv.setBackgroundResource(R.drawable.bubble_b);
	       lv.setLayoutParams(params);
	       
	       Bitmap bmThumbnail;
	        bmThumbnail = ThumbnailUtils.createVideoThumbnail(
	        		arg2.getString(arg2.getColumnIndex(arg2.getColumnName(0))),
	        		Thumbnails.MICRO_KIND);
	        v.setImageBitmap(bmThumbnail);
			}
	       
			
		}
	
		else if(arg2.getString(arg2.getColumnIndex(arg2.getColumnName(1))).equals(sender)&&
				arg2.getString(arg2.getColumnIndex(arg2.getColumnName(5))).equals("media_image")){
			
             if(arg2.getString(arg2.getColumnIndex(arg2.getColumnName(2))).equals("BroadCast")){
            	 LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
 		                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
 		                params.gravity = Gravity.CENTER;
 		               params.leftMargin = 20;
 		                params.rightMargin = 20;
 		       
 		        
 		        li.setVisibility(View.GONE);
 		        lv.setVisibility(View.GONE);
 		        lp.setVisibility(View.GONE);
 		       lbv.setVisibility(View.GONE);
		        lbt.setVisibility(View.GONE);
		        lbi.setVisibility(View.VISIBLE);
 		        lbi.setBackgroundResource(R.drawable.round_corner_c);
 		       lbi.setLayoutParams(params);
 		       
 		       vbi.setImageURI(Uri.parse(arg2.getString(arg2.getColumnIndex(arg2.getColumnName(0)))));
 		      
 		      
			}
			
			else{
		
			 LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
		                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		                params.gravity = Gravity.LEFT;
		                
		       
		        
		        li.setVisibility(View.VISIBLE);
		        lv.setVisibility(View.GONE);
		        lp.setVisibility(View.GONE);
		        lbv.setVisibility(View.GONE);
		        lbi.setVisibility(View.GONE);
		        lbt.setVisibility(View.GONE);
		        li.setBackgroundResource(R.drawable.bubble_b);
		       li.setLayoutParams(params);
		       
		       i.setImageURI(Uri.parse(arg2.getString(arg2.getColumnIndex(arg2.getColumnName(0)))));
		      
			}
	        
		}
		
		else if(!arg2.getString(arg2.getColumnIndex(arg2.getColumnName(1))).equals(sender)&&
				arg2.getString(arg2.getColumnIndex(arg2.getColumnName(5))).equals("text")){
			
         if(arg2.getString(arg2.getColumnIndex(arg2.getColumnName(2))).equals("BroadCast")){
        	 
        	 LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
 	                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
 	                params.gravity = Gravity.CENTER;
 	                params.leftMargin = 20;
	                params.rightMargin = 20;
 	       
 	       
 	        lp.setVisibility(View.GONE);
 	        lv.setVisibility(View.GONE);
 	        li.setVisibility(View.GONE);
 	       lbv.setVisibility(View.GONE);
	        lbt.setVisibility(View.GONE);
	        lbi.setVisibility(View.VISIBLE);
 	        lbi.setBackgroundResource(R.drawable.round_corner_c);
 	       lbi.setLayoutParams(params);
 	       
		     	}
			
			else{
			
	        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
	                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
	                params.gravity = Gravity.RIGHT;
	                
	       
	       
	        lp.setVisibility(View.VISIBLE);
	        lv.setVisibility(View.GONE);
	        li.setVisibility(View.GONE);
	        lbv.setVisibility(View.GONE);
	        lbi.setVisibility(View.GONE);
	        lbt.setVisibility(View.GONE);
	        lp.setBackgroundResource(R.drawable.bubble_a);
	       lp.setLayoutParams(params);
			}
	      
		}
		
		else if(!arg2.getString(arg2.getColumnIndex(arg2.getColumnName(1))).equals(sender)&&
				arg2.getString(arg2.getColumnIndex(arg2.getColumnName(5))).equals("media_video")){
			
		
        if(arg2.getString(arg2.getColumnIndex(arg2.getColumnName(2))).equals("BroadCast")){
        	    

	        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
	                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
	                params.gravity = Gravity.CENTER;
	                params.leftMargin = 20;
	                params.rightMargin = 20;
	       
	      
	        lv.setVisibility(View.GONE);
	        lp.setVisibility(View.GONE);
	        li.setVisibility(View.GONE);
	        lbt.setVisibility(View.GONE);
	        lbi.setVisibility(View.GONE);
	        lbv.setVisibility(View.VISIBLE);
	        lbv.setBackgroundResource(R.drawable.round_corner_c);
	       lbv.setLayoutParams(params);
	       
	       Bitmap bmThumbnail;
	        bmThumbnail = ThumbnailUtils.createVideoThumbnail(
	        		arg2.getString(arg2.getColumnIndex(arg2.getColumnName(0))),
	        		Thumbnails.MICRO_KIND);
	        vbv.setImageBitmap(bmThumbnail);
	      
			}
			
			else{
				
			
	        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
	                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
	                params.gravity = Gravity.RIGHT;
	                
	       
	      
	        lv.setVisibility(View.VISIBLE);
	        lp.setVisibility(View.GONE);
	        li.setVisibility(View.GONE);
	        lbv.setVisibility(View.GONE);
	        lbi.setVisibility(View.GONE);
	        lbt.setVisibility(View.GONE);
	        lv.setBackgroundResource(R.drawable.bubble_a);
	       lv.setLayoutParams(params);
	       
	       Bitmap bmThumbnail;
	        bmThumbnail = ThumbnailUtils.createVideoThumbnail(
	        		arg2.getString(arg2.getColumnIndex(arg2.getColumnName(0))),
	        		Thumbnails.MICRO_KIND);
	        v.setImageBitmap(bmThumbnail);
			}
	       
		}
		
		else if(!arg2.getString(arg2.getColumnIndex(arg2.getColumnName(1))).equals(sender)&&
				arg2.getString(arg2.getColumnIndex(arg2.getColumnName(5))).equals("media_image")){
			
          if(arg2.getString(arg2.getColumnIndex(arg2.getColumnName(2))).equals("BroadCast")){
        	     
        	  LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
		                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		                params.gravity = Gravity.CENTER;
		                params.leftMargin = 20;
		                params.rightMargin = 20;
		       
		       
		        li.setVisibility(View.GONE);
		        lv.setVisibility(View.GONE);
		        lp.setVisibility(View.GONE);
		        lbv.setVisibility(View.GONE);
		        lbt.setVisibility(View.GONE);
		        lbi.setVisibility(View.VISIBLE);
		        lbi.setBackgroundResource(R.drawable.round_corner_c);
		       lbi.setLayoutParams(params);
		       
		       vbi.setImageURI(Uri.parse(arg2.getString(arg2.getColumnIndex(arg2.getColumnName(0)))));
		       
		      
			
		       
			}
			
			else{
				
			
			 LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
		                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		                params.gravity = Gravity.RIGHT;
		                
		       
		       
		        li.setVisibility(View.VISIBLE);
		        lv.setVisibility(View.GONE);
		        lp.setVisibility(View.GONE);
		        lbv.setVisibility(View.GONE);
		        lbi.setVisibility(View.GONE);
		        lbt.setVisibility(View.GONE);
		        li.setBackgroundResource(R.drawable.bubble_a);
		       li.setLayoutParams(params);
		       
		       i.setImageURI(Uri.parse(arg2.getString(arg2.getColumnIndex(arg2.getColumnName(0)))));
		       
			}
		}
		
		
	
	      
	}

	@Override
	public View newView(final Context arg0, final Cursor c, final ViewGroup arg2) {
		LayoutInflater inflater = LayoutInflater.from(arg2.getContext());
		final View view = inflater.inflate(R.layout.listmsg, arg2, false);
	    

		
		
		
		return view;
	}
	

	
	
public String getItemName(Cursor cursor) {
		
		String name = cursor.getString(cursor.getColumnIndex(cursor.getColumnName(0)));
		return name;
		
	}

public int getItemId(Cursor cursor) {
	int id = cursor.getInt(cursor.getColumnIndex(cursor.getColumnName(7)));
	return id;
}

public String getItemSenderName(Cursor cursor) {

	String name = cursor.getString(cursor.getColumnIndex(cursor.getColumnName(1)));
	return name;
}

public String getItemMsg(Cursor cursor) {
	String msg = cursor.getString(cursor.getColumnIndex(cursor.getColumnName(0)));
	return msg;
}
public String getItemReceiver(Cursor cursor) {
	String receiver = cursor.getString(cursor.getColumnIndex(cursor.getColumnName(2)));
	return receiver;
}
public String getItemMediaTest(Cursor cursor) {
	String mediaTest = cursor.getString(cursor.getColumnIndex(cursor.getColumnName(5)));
	return mediaTest;
}

}
