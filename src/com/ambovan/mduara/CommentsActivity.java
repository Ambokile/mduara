package com.ambovan.mduara;

import java.util.Calendar;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.LinearLayout.LayoutParams;
import android.os.Build;
import android.provider.MediaStore;

public class CommentsActivity extends ActionBarActivity {
String receiver,sender;String id; CommentsAdapter ca;Button b2;ListView l;CommentsListAdapter cal; MainActivity main_object;String selectedPath = "",fileType;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getActionBar(). setBackgroundDrawable(new ColorDrawable(Color.parseColor("#CC6600")));
		setContentView(R.layout.fragment_comments);
		
		l = (ListView)findViewById(R.id.listComments);
		ca = new CommentsAdapter(this);
		
		receiver = getIntent().getStringExtra("receiver");
		sender = getIntent().getStringExtra("about");
		id = getIntent().getStringExtra("media_id");
		 Toast.makeText(getApplication(), id+ "", Toast.LENGTH_SHORT).show();
		getActionBar().setSubtitle(receiver);

		cal = new CommentsListAdapter(this,getAllComments(sender),receiver,id);
		l.setAdapter(cal);
		
		
         b2 = (Button)findViewById(R.id.send_button);
     
		 final EditText editText = (EditText)findViewById(R.id.comment_msg);
         editText.addTextChangedListener(new TextWatcher(){
        
        
			@Override
			public void afterTextChanged(Editable s) {
				

	              if(!editText.getText().toString().trim().isEmpty()){
	            	  LayoutParams lp = new LayoutParams(0,LayoutParams.WRAP_CONTENT,1.75f);
	     				editText.setLayoutParams(lp);
	     				LayoutParams lp_b = new LayoutParams(0,LayoutParams.WRAP_CONTENT,0.25f);
	            	   b2.setLayoutParams(lp_b);
	     				b2.setVisibility(View.VISIBLE);
	     				 
	              }
	              
	              else{
	            	  
	            	  LayoutParams lp = new LayoutParams(0,LayoutParams.WRAP_CONTENT,2.0f);
	     				editText.setLayoutParams(lp);
	     				LayoutParams lp_b = new LayoutParams(0,LayoutParams.WRAP_CONTENT,0.0f);
	     				b2.setLayoutParams(lp_b);
	            	   b2.setLayoutParams(lp_b);
	     				b2.setVisibility(View.GONE);
	     				 
	     				
		     				editText.setOnKeyListener(new View.OnKeyListener() {
								
								@Override
								public boolean onKey(View arg0, int arg1, KeyEvent arg2) {
									onKeyDown(arg1,arg2);
									return false;
								}
								
								public boolean onKeyDown(int arg1, KeyEvent arg2){
									if(arg1 == arg2.KEYCODE_ENTER){
										return true;
									}
									
									
								return (arg1 == KeyEvent.KEYCODE_ENTER);}
							});
	              }
			
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				if(!editText.getText().toString().trim().isEmpty()){
	            	 
	     				b2.setVisibility(View.GONE);
	     			
	     				
	              }
	              
	              else{
	            	  b2.setVisibility(View.GONE);
	            	  
	     				editText.setOnKeyListener(new View.OnKeyListener() {
							
							@Override
							public boolean onKey(View arg0, int arg1, KeyEvent arg2) {
								onKeyDown(arg1,arg2);
								return false;
							}
							
							public boolean onKeyDown(int arg1, KeyEvent arg2){
								if(arg1 == arg2.KEYCODE_ENTER){
									return true;
								}
								
								
							return (arg1 == KeyEvent.KEYCODE_ENTER);}
						});
	              }
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				  if(!editText.getText().toString().trim().isEmpty()){
					  LayoutParams lp = new LayoutParams(0,LayoutParams.WRAP_CONTENT,1.75f);
	     				editText.setLayoutParams(lp);
	     				LayoutParams lp_b = new LayoutParams(0,LayoutParams.WRAP_CONTENT,0.25f);
	     				editText.setLayoutParams(lp_b);
	            	   b2.setLayoutParams(lp_b);
	     				b2.setVisibility(View.VISIBLE);
	     				  
				  }
	              
	              else{
	            	  b2.setVisibility(View.GONE);
	            	  
                   editText.setOnKeyListener(new View.OnKeyListener() {
							
							@Override
							public boolean onKey(View arg0, int arg1, KeyEvent arg2) {
								onKeyDown(arg1,arg2);
								return false;
							}
							
							public boolean onKeyDown(int arg1, KeyEvent arg2){
								if(arg1 == arg2.KEYCODE_ENTER){
									return true;
								}
								
								
							return (arg1 == KeyEvent.KEYCODE_ENTER);}
						});
	              }
				  
			
			}
			
			
			});
         
      b2.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			
			
			EditText editText = (EditText)findViewById(R.id.editText1);
			 Calendar c = Calendar.getInstance();
		        int second = c.get(Calendar.SECOND);
		        int hrs = c.get(Calendar.HOUR);
		        int min = c.get(Calendar.MINUTE);
		        int date = c.get(Calendar.DATE);
		        int month = c.get(Calendar.MONTH);
		        int yrs = c.get(Calendar.YEAR);
		        
		        String time = hrs +":" + min +":"+ second;
		        String date_sent =  date + "/"+ month + "/" + yrs;
		       String sender = receiver;
		       String comments_type = "text";
		       String receiver = sender;
			String comments = editText.getText().toString();
		    

			  saveComments(comments, sender, receiver, time, date_sent,id,comments_type);
			  
			  cal.swapCursor(getAllComments(receiver));
			  Cursor cursor = getLastComments();
			  
		        if (cursor.moveToFirst()) {
		        do { 
		           
		            int id = cursor.getInt(7);
		           
		            l.setSelection(id);
		           
		       
		        } while (cursor.moveToNext());
		        
		        }
		        if (!cursor.isClosed()) {
		        cursor.close();
		        }
		        
			 
		      
		   
		
			editText.setText("");
			InputMethodManager imm = (InputMethodManager)getSystemService(
				      Context.INPUT_METHOD_SERVICE);
				imm.hideSoftInputFromWindow(b2.getWindowToken(), 0);
				
			
			
		}
	});   
      
      b2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				EditText editText = (EditText)findViewById(R.id.comment_msg);
				 Calendar c = Calendar.getInstance();
			        int second = c.get(Calendar.SECOND);
			        int hrs = c.get(Calendar.HOUR);
			        int min = c.get(Calendar.MINUTE);
			        int date = c.get(Calendar.DATE);
			        int month = c.get(Calendar.MONTH);
			        int yrs = c.get(Calendar.YEAR);
			        
			        String time = hrs +":" + min +":"+ second;
			        String date_sent =  date + "/"+ month + "/" + yrs;
			       
				String comments = editText.getText().toString();
			  
			    saveComments(comments,sender,receiver,time,date_sent,id,"text");
				  
				  cal.swapCursor(getAllComments(receiver));
				  Cursor cursor = getLastComments();
				  
			        if (cursor.moveToFirst()) {
			        do { 
			           
			            int id = cursor.getInt(7);
			           
			            l.setSelection(id);
			           
			       
			        } while (cursor.moveToNext());
			        
			        }
			        if (!cursor.isClosed()) {
			        cursor.close();
			        }
			        
				 
			      
			   
			
				editText.setText("");
				InputMethodManager imm = (InputMethodManager)getSystemService(
					      Context.INPUT_METHOD_SERVICE);
					imm.hideSoftInputFromWindow(b2.getWindowToken(), 0);
					
				
			}
		});
        
       
      
		
	}

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.comments, menu);
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
	
	
	public void onActivityResult(int requestCode, int resultCode, Intent data){
		
		
    	if(resultCode == RESULT_OK){
    		
    		if(requestCode == 1){
    			 System.out.println("SELECT_VIDEO");
 	            Uri selectedVideoUri = data.getData();
 	            selectedPath = getPath(selectedVideoUri);
 	            System.out.println("SELECT_VIDEO Path : " + selectedPath);
 	           GoToMediaChoice( selectedPath);

    		}
    	}
	}
	
public void GoToMediaChoice(String path){
    	
        Intent intent = new Intent(this,ChooseActivity.class);
        intent.putExtra("path_out",path);
        intent.putExtra("rec_out",receiver);
        intent.putExtra("sender", sender);
        intent.putExtra("fileType", fileType);
        intent.putExtra("msdcode", "27dk07");
        intent.putExtra("comment-id", id);
        startActivityForResult(intent, 2);
       
    }

public String getPath(Uri selectedVideoUri) {
	
	String filePath = null;
	int fileSize = 0;
	 String[] projection = null; 
	    @SuppressWarnings("deprecation")
		Cursor cursor = managedQuery(selectedVideoUri, projection, null, null, null);
	    cursor.moveToFirst(); 
	    
	    if(cursor.getString(cursor.getColumnIndexOrThrow( MediaStore.Files.FileColumns.MIME_TYPE)) != "video/"){
	    	 fileType = cursor.getString(cursor.getColumnIndexOrThrow( MediaStore.Files.FileColumns.MIME_TYPE));
	    	 filePath = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA));
    	    fileSize = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.SIZE));
    	   // long duration = TimeUnit.MILLISECONDS.toSeconds(cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DURATION)));
    	    Toast.makeText(getApplication(),cursor.getString(cursor.getColumnIndexOrThrow( MediaStore.Files.FileColumns.MIME_TYPE)) , Toast.LENGTH_SHORT).show();
	    }
	    
	    else if(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.MIME_TYPE)) != "images/"){
	    	fileType = cursor.getString(cursor.getColumnIndexOrThrow( MediaStore.Files.FileColumns.MIME_TYPE));
	    	 filePath = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA));
	    	 fileSize = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.SIZE));
	    	 Toast.makeText(getApplication(),cursor.getString(cursor.getColumnIndexOrThrow( MediaStore.Files.FileColumns.MIME_TYPE)) , Toast.LENGTH_SHORT).show();
	    	   }
	    

	    
	    
	return filePath;
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

	

    public Cursor getAllComments(String receiver) {
    	
    	final String[] queryParameters = new String[1];
    	queryParameters[0] = receiver;
       	String[] columns = {"comments","sender","receiver","time","date","comments_id","comments_type","_id"};
    	 String sender_col = "sender";
    	 String receiver_col = "receiver";
    	SQLiteDatabase db;
        db = ca.getWritableDatabase();
       
           
       return db.query( "CommentsTable",
    		              columns, 
    		              //receiver_col+"='"+String.valueOf(this.receiver)+"'",
    		             null,
    		              null, 
    		              null, 
    		              null, 
    		              null, 
    		              null);
       
    }
    
    public Cursor getLastComments() { 
    	SQLiteDatabase db;
        db = ca.getWritableDatabase();
        return db.rawQuery(
                                  "select * from " + "CommentsTable order by _id desc limit 1", 
                                   null
                                 );
    }
    
    
   
	
}
