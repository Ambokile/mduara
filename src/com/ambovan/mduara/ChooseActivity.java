package com.ambovan.mduara;

import java.util.Calendar;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.VideoView;

public class ChooseActivity extends ActionBarActivity {
String path,code,id;
MsgServer msgserver ;
CommentsAdapter ca;

String msg,receiver,sender,time_i,date_i;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_choose);

		
		RelativeLayout layout = (RelativeLayout)findViewById(R.id.first);
		path = getIntent().getStringExtra("path_out");
	
		receiver = getIntent().getStringExtra("rec_out");
		sender =getIntent().getStringExtra("sender");
		code = getIntent().getStringExtra("msdcode");
		
		if(code.equals("28dk07")){
			 msgserver = new MsgServer(this);
			if(getIntent().getStringExtra("fileType").startsWith("video/") ){
				Toast.makeText(getApplication(),path, Toast.LENGTH_SHORT).show();
				
				VideoView v = (VideoView)findViewById(R.id.videoView1);
				ImageView im = (ImageView)findViewById(R.id.Image1);
				
				v.setVisibility(View.VISIBLE);
				
				im.setVisibility(View.GONE);
				
				 v.setVideoPath(path);
			
				v.start();
				
				Button send = (Button)findViewById(R.id.send);
				send.setOnClickListener(new View.OnClickListener() {
					
					
					
					@Override
					public void onClick(View arg0) {
						EditText media_info = (EditText)findViewById(R.id.media_msg);
						String media_info_text = media_info.getText().toString();
						
						 Calendar c = Calendar.getInstance();
					        int second = c.get(Calendar.SECOND);
					        int hrs = c.get(Calendar.HOUR);
					        int min = c.get(Calendar.MINUTE);
					        int date = c.get(Calendar.DATE);
					        int month = c.get(Calendar.MONTH);
					        int yrs = c.get(Calendar.YEAR);
					        
					        String time = hrs +":" + min +":"+ second;
					        String date_s =  date + "/"+ month + "/" + yrs;
					        
						
						
					    time_i = time.toString();
					    date_i = date_s.toString();
					    
					    
	  
					    
					    String mimeType = null;
						
						
						   String exte = MimeTypeMap.getFileExtensionFromUrl(Uri.encode(path));
							MimeTypeMap mime = MimeTypeMap.getSingleton();
							
						  
							if(mime.hasExtension(exte)){
								 mimeType = mime.getMimeTypeFromExtension(exte);
								 Toast.makeText(getApplication(), mimeType, Toast.LENGTH_SHORT).show();
								
								if(mimeType.startsWith("video/")){
									
									 if(saveMedia(path, sender, receiver, time_i, date_i,"media_video",media_info_text)){
									    	
									    	Intent i = new Intent();
									    	i.setClass(ChooseActivity.this, MainActivity.class);
									    	if(getParent() == null){
									    		setResult(Activity.RESULT_OK, i);
									    	}
									    	else{
									    		getParent().setResult(Activity.RESULT_OK,i);
									    	}
									    	
									      finish();
									    }
										  
										 
									    }
										  
										 
									 else{
										 Intent i = new Intent();
									    	i.setClass(ChooseActivity.this, MainActivity.class);
									    	if(getParent() == null){
									    		setResult(Activity.RESULT_CANCELED, i);
									    	}
									    	else{
									    		getParent().setResult(Activity.RESULT_CANCELED,i);
									    	}
									    	
									      finish();
									      }	 
								}	
								
							  else{
								  Intent i = new Intent();
							    	i.setClass(ChooseActivity.this, MainActivity.class);
							    	if(getParent() == null){
							    		setResult(Activity.RESULT_CANCELED, i);
							    	}
							    	else{
							    		getParent().setResult(Activity.RESULT_CANCELED,i);
							    	}
							    	
							      finish();
							      }	
						  
						 
					
					}
				});
				
				
			}
			 
			else if(getIntent().getStringExtra("fileType").startsWith("image/") ){
				Toast.makeText(getApplication(),path, Toast.LENGTH_SHORT).show();
				
				VideoView v = (VideoView)findViewById(R.id.videoView1);
				
				ImageView im = (ImageView)findViewById(R.id.Image1);
				
				v.setVisibility(View.GONE);
				
				im.setVisibility(View.VISIBLE);
				im.setImageURI(Uri.parse(path));
				 
				Button send = (Button)findViewById(R.id.send);
				send.setOnClickListener(new View.OnClickListener() {
					EditText media_info = (EditText)findViewById(R.id.media_msg);
					String media_info_text = media_info.getText().toString();
					
					@Override
					public void onClick(View arg0) {
						 Calendar c = Calendar.getInstance();
					        int second = c.get(Calendar.SECOND);
					        int hrs = c.get(Calendar.HOUR);
					        int min = c.get(Calendar.MINUTE);
					        int date = c.get(Calendar.DATE);
					        int month = c.get(Calendar.MONTH);
					        int yrs = c.get(Calendar.YEAR);
					        
					        String time = hrs +":" + min +":"+ second;
					        String date_s =  date + "/"+ month + "/" + yrs;
						
						
					    time_i = time.toString();
					    date_i = date_s.toString();
					    
					    
					    String mimeType = null;
						
						
						   String exte = MimeTypeMap.getFileExtensionFromUrl(Uri.encode(path));
							MimeTypeMap mime = MimeTypeMap.getSingleton();
							
						  
							if(mime.hasExtension(exte)){
								 mimeType = mime.getMimeTypeFromExtension(exte);
								 Toast.makeText(getApplication(), mimeType, Toast.LENGTH_SHORT).show();
								
								if(mimeType.startsWith("image/")){
									
								
									 if(saveMedia(path, sender, receiver, time_i, date_i,"media_image",media_info_text)){
									    	
									    	Intent i = new Intent();
									    	i.setClass(ChooseActivity.this, MainActivity.class);
									    	if(getParent() == null){
									    		setResult(Activity.RESULT_OK, i);
									    	}
									    	else{
									    		getParent().setResult(Activity.RESULT_OK,i);
									    	}
									    	
									      finish();
									    }
										  
										 
									 else{
										 Intent i = new Intent();
									    	i.setClass(ChooseActivity.this, MainActivity.class);
									    	if(getParent() == null){
									    		setResult(Activity.RESULT_CANCELED, i);
									    	}
									    	else{
									    		getParent().setResult(Activity.RESULT_CANCELED,i);
									    	}
									    	
									      finish();
									      }	 
								}	
								
							  else{
								  Intent i = new Intent();
							    	i.setClass(ChooseActivity.this, MainActivity.class);
							    	if(getParent() == null){
							    		setResult(Activity.RESULT_CANCELED, i);
							    	}
							    	else{
							    		getParent().setResult(Activity.RESULT_CANCELED,i);
							    	}
							    	
							      finish();
							      }	 
								}
							
					    
					    
					}
				});
				
			
			}
			
			else if(getMimeType(path).equals("audio/")){
				
			}
			
		}
		else if(code.equals("27dk07")){
		    id = getIntent().getStringExtra("comment-id");
			ca = new CommentsAdapter(this);
			if(getIntent().getStringExtra("fileType").startsWith("video/") ){
				Toast.makeText(getApplication(),path, Toast.LENGTH_SHORT).show();
				
				VideoView v = (VideoView)findViewById(R.id.videoView1);
				ImageView im = (ImageView)findViewById(R.id.Image1);
				
				v.setVisibility(View.VISIBLE);
				
				im.setVisibility(View.GONE);
				
				 v.setVideoPath(path);
			
				v.start();
				
				Button send = (Button)findViewById(R.id.send);
				send.setOnClickListener(new View.OnClickListener() {
					
					
					
					@Override
					public void onClick(View arg0) {
						EditText media_info = (EditText)findViewById(R.id.media_msg);
						String media_info_text = media_info.getText().toString();
						
						 Calendar c = Calendar.getInstance();
					        int second = c.get(Calendar.SECOND);
					        int hrs = c.get(Calendar.HOUR);
					        int min = c.get(Calendar.MINUTE);
					        int date = c.get(Calendar.DATE);
					        int month = c.get(Calendar.MONTH);
					        int yrs = c.get(Calendar.YEAR);
					        
					        String time = hrs +":" + min +":"+ second;
					        String date_sent =  date + "/"+ month + "/" + yrs;
					        
						
						
					   
					    
	  
					    
					    String mimeType = null;
						
						
						   String exte = MimeTypeMap.getFileExtensionFromUrl(Uri.encode(path));
							MimeTypeMap mime = MimeTypeMap.getSingleton();
							
						  
							if(mime.hasExtension(exte)){
								 mimeType = mime.getMimeTypeFromExtension(exte);
								 Toast.makeText(getApplication(), mimeType, Toast.LENGTH_SHORT).show();
								
								if(mimeType.startsWith("video/")){
									
									 if(saveComments(path, sender, receiver, time, date_sent,id,"media_video")){
									    	
									    	Intent i = new Intent();
									    	i.setClass(ChooseActivity.this, MainActivity.class);
									    	if(getParent() == null){
									    		setResult(Activity.RESULT_OK, i);
									    	}
									    	else{
									    		getParent().setResult(Activity.RESULT_OK,i);
									    	}
									    	
									      finish();
									    }
										  
										 
									    }
										  
										 
									 else{
										 Intent i = new Intent();
									    	i.setClass(ChooseActivity.this, MainActivity.class);
									    	if(getParent() == null){
									    		setResult(Activity.RESULT_CANCELED, i);
									    	}
									    	else{
									    		getParent().setResult(Activity.RESULT_CANCELED,i);
									    	}
									    	
									      finish();
									      }	 
								}	
								
							  else{
								  Intent i = new Intent();
							    	i.setClass(ChooseActivity.this, MainActivity.class);
							    	if(getParent() == null){
							    		setResult(Activity.RESULT_CANCELED, i);
							    	}
							    	else{
							    		getParent().setResult(Activity.RESULT_CANCELED,i);
							    	}
							    	
							      finish();
							      }	
						  
						 
					
					}
				});
				
				
			}
			 
			else if(getIntent().getStringExtra("fileType").startsWith("image/") ){
				Toast.makeText(getApplication(),path, Toast.LENGTH_SHORT).show();
				
				VideoView v = (VideoView)findViewById(R.id.videoView1);
				
				ImageView im = (ImageView)findViewById(R.id.Image1);
				
				v.setVisibility(View.GONE);
				
				im.setVisibility(View.VISIBLE);
				im.setImageURI(Uri.parse(path));
				 
				Button send = (Button)findViewById(R.id.send);
				send.setOnClickListener(new View.OnClickListener() {
					EditText media_info = (EditText)findViewById(R.id.media_msg);
					String media_info_text = media_info.getText().toString();
					
					@Override
					public void onClick(View arg0) {
						 Calendar c = Calendar.getInstance();
					        int second = c.get(Calendar.SECOND);
					        int hrs = c.get(Calendar.HOUR);
					        int min = c.get(Calendar.MINUTE);
					        int date = c.get(Calendar.DATE);
					        int month = c.get(Calendar.MONTH);
					        int yrs = c.get(Calendar.YEAR);
					        
					        String time = hrs +":" + min +":"+ second;
					        String date_s =  date + "/"+ month + "/" + yrs;
						
						
					    time_i = time.toString();
					    date_i = date_s.toString();
					    
					    
					    String mimeType = null;
						
						
						   String exte = MimeTypeMap.getFileExtensionFromUrl(Uri.encode(path));
							MimeTypeMap mime = MimeTypeMap.getSingleton();
							
						  
							if(mime.hasExtension(exte)){
								 mimeType = mime.getMimeTypeFromExtension(exte);
								 Toast.makeText(getApplication(), mimeType, Toast.LENGTH_SHORT).show();
								
								if(mimeType.startsWith("image/")){
									
								
									 if(saveMedia(path, sender, receiver, time_i, date_i,id,"media_image")){
									    	
									    	Intent i = new Intent();
									    	i.setClass(ChooseActivity.this, MainActivity.class);
									    	if(getParent() == null){
									    		setResult(Activity.RESULT_OK, i);
									    	}
									    	else{
									    		getParent().setResult(Activity.RESULT_OK,i);
									    	}
									    	
									      finish();
									    }
										  
										 
									 else{
										 Intent i = new Intent();
									    	i.setClass(ChooseActivity.this, MainActivity.class);
									    	if(getParent() == null){
									    		setResult(Activity.RESULT_CANCELED, i);
									    	}
									    	else{
									    		getParent().setResult(Activity.RESULT_CANCELED,i);
									    	}
									    	
									      finish();
									      }	 
								}	
								
							  else{
								  Intent i = new Intent();
							    	i.setClass(ChooseActivity.this, MainActivity.class);
							    	if(getParent() == null){
							    		setResult(Activity.RESULT_CANCELED, i);
							    	}
							    	else{
							    		getParent().setResult(Activity.RESULT_CANCELED,i);
							    	}
							    	
							      finish();
							      }	 
								}
							
					    
					    
					}
				});
				
			
			}
			
			else if(getMimeType(path).equals("audio/")){
				
			}
			
		}
		
		
		
		
		
		
		
	     }
	
	public boolean saveMedia( String msg,String sender,String receiver,String time,String date,String media,String media_info) { 
        boolean r_in = false;
        SQLiteDatabase db;
        db = msgserver.getWritableDatabase();
      
  	ContentValues values = new ContentValues();
  	values.put("msg",msg);
  	
  	values.put("sender",sender);
  	
  	values.put("receiver",receiver);
  	values.put("time",time);
  	values.put("date",date);
  	values.put("media_test",media);
  	values.put("media_about",media_info);
  	try{
  	db.insert("MsgTable", null, values);
  	r_in = true;
  	}catch(Exception e){
  		r_in = false;
  	}
  	
		return r_in;
  	
                                             }
	
	public boolean saveComments( String comments,String sender,String receiver,String time,String date,String comment_id,String type) { 
		 boolean r_in;
	        SQLiteDatabase db;
	        db = ca.getWritableDatabase();
	  	ContentValues values = new ContentValues();
	  	
	  	values.put("sender",sender);
	  	
	  	values.put("receiver",receiver);
	  	values.put("time",time);
	  	values.put("date",date);
	  	values.put("comments_id",comment_id);
	  	values.put("comments", comments);
	  	values.put("comments_type", type);
	  	
	  	try{
	  	db.insert("CommentsTable", null, values);
	  	r_in = true;
	  	}catch(Exception e){
	  		r_in = false;
	  	}
	  	return r_in;
                                             }


	public static String getMimeType(String url)
	{
	    String type = null;
	    String extension = MimeTypeMap.getFileExtensionFromUrl(url);
	    if (extension != null) {
	        MimeTypeMap mime = MimeTypeMap.getSingleton();
	        type = mime.getMimeTypeFromExtension(extension);
	    }
	    return type;
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.choose, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	
}
