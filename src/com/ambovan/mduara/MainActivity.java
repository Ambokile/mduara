package com.ambovan.mduara;

import java.util.ArrayList;
import java.util.Calendar;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.webkit.MimeTypeMap;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {
     String fileType;
	 private DrawerLayout LeftDrawer;
	 private ActionBarDrawerToggle leftDrawerToggle;
	 ArrayList<Contact> listContacts;
	 UserInfo user_info;
	 MsgServer msgserver ;
	 String msg,receiver,sender,time_i,date_i,name;
	 MsgDeliver mc;
	 media_stored mediaServer;
	 String selectedPath = "";
	 Button b2,fungua,save_userInfo ;
	 ListView lvContacts,l_media,l;
	 mediaAdapter md;
	 ViewGroup mediaList,EmptyMedia;
	 LinearLayout  lyContacts ;
	 Cursor CurrentCursor;
	 ImageView v1;
	 private Menu mMenu;
	 Cursor cursor_server_broadCast,cursor_server;
	 CommentsAdapter ca;
	 String forward;
	 String forward_receiver_name;
	 String forward_msg;
	 public String mediaTest; 
	 Handler mHandler;
	 private int timeExecute = 6000;
	 private boolean continueToRun = true;
    @SuppressWarnings("static-access")
	@SuppressLint("NewApi") protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActionBar(). setBackgroundDrawable(new ColorDrawable(Color.parseColor("#CC6600")));
       
       
        setContentView(R.layout.fragment_main);
        mHandler = new Handler();
        mHandler.postDelayed(mRunnable, timeExecute);
        user_info = new UserInfo(this);
        msgserver = new MsgServer(this);
        ca = new CommentsAdapter(this);
        mediaServer = new media_stored(this);
        l = (ListView)findViewById(R.id.list);
        final LinearLayout lay = (LinearLayout)findViewById(R.id.header);
		lay.setBackgroundColor(Color.TRANSPARENT);
		final LinearLayout lay_list = (LinearLayout)findViewById(R.id.outerOne);
		
        
        LeftDrawer = (DrawerLayout)findViewById(R.id.drawer_layout);
        LeftDrawer.setDrawerLockMode(LeftDrawer.LOCK_MODE_UNLOCKED);
        
        leftDrawerToggle = new  ActionBarDrawerToggle(this, LeftDrawer, R.drawable.ic_drawer, 0,0){
        
        	public void onDrawerClosed(View view){
        		
        		MenuItem item_search = mMenu.findItem(R.id.search);
        		if(forward == "1234"){
        			 
        	    	   Toast.makeText(getApplication(),forward_receiver_name,Toast.LENGTH_SHORT).show();
        	    	   Toast.makeText(getApplication(),forward_msg,Toast.LENGTH_SHORT).show();
        	    	   Toast.makeText(getApplication(),sender,Toast.LENGTH_SHORT).show();
        	       	   
        	    	   Calendar ca = Calendar.getInstance();
        		        int second = ca.get(Calendar.SECOND);
        		        int hrs = ca.get(Calendar.HOUR);
        		        int min = ca.get(Calendar.MINUTE);
        		        int date = ca.get(Calendar.DATE);
        		        int month = ca.get(Calendar.MONTH);
        		        int yrs = ca.get(Calendar.YEAR);
        		        
        		        String time = hrs +":" + min +":"+ second;
        		        String date_sent =  date + "/"+ month + "/" + yrs;
        		   
        		       saveMsg(forward_msg,sender , receiver, time, date_sent,mediaTest,"seen");
        			  
        			  mc.swapCursor(getAllMsg(forward_receiver_name));
        			  Cursor cursor = getLastMsg();
        			  
        		        if (cursor.moveToFirst()) {
        		        do { 
        		           
        		        	 if(forward_receiver_name != "BroadCast"){
        			  				getActionBar().setSubtitle(name);
        								
        			  			}
        			  			
        			  	   else{
        			  				getActionBar().setSubtitle(null);
            	  					item_search.setVisible(true);
        			  			}
        		        	 
        		        	         
        		        	 getActionBar().setIcon(R.drawable.ic_launcher);
        					 getActionBar().setTitle("Mduara");
        					 leftDrawerToggle.setDrawerIndicatorEnabled(true);
        		             int id_1 = cursor.getInt(7);
        		             l.setSelection(id_1);
        		             
        		           
        		       
        		        } while (cursor.moveToNext());
        		        
        		        }
        		        if (!cursor.isClosed()) {
        		        cursor.close();
        		        }forward = "1235";
        		        
        		}else{
        		int id = 0;
        		android.app.ActionBar  b= getActionBar();
        		super.onDrawerClosed(view);
        		b.setTitle("Mduara");
        		b. setBackgroundDrawable(new ColorDrawable(Color.parseColor("#CC6600")));
        		if(b.getSubtitle() == null){
        			lay.setVisibility(View.VISIBLE);
        			receiver = "BroadCast";
        			mc.swapCursor(getAllMsg(receiver));
  					item_search.setVisible(true);
        			
        	       }
        		else{
        			 lay.setVisibility(View.GONE);
        			 item_search.setVisible(false);
        		  }
        		}
        	}
        	
        	public void onDrawerOpened(View view){
        		
        		super.onDrawerOpened(view);
        		getActionBar().setTitle("Contact");
        		getActionBar().setSubtitle(null);
        	    getActionBar().
        	        setBackgroundDrawable(new ColorDrawable(Color.parseColor("#CC6600")));
        	    MenuItem item_search = mMenu.findItem(R.id.search);
					 item_search.setVisible(true);
        	     
        	}
        };
        LeftDrawer.setDrawerListener(leftDrawerToggle);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);
        				
        final EditText editText = (EditText)findViewById(R.id.editText1);
           editText.addTextChangedListener(new TextWatcher(){
          Button b = (Button)findViewById(R.id.button1);
          Button b2 = (Button)findViewById(R.id.button2);
          Button b3 = (Button)findViewById(R.id.button3);
       
          
			@Override
			public void afterTextChanged(Editable s) {
				

	              if(!editText.getText().toString().trim().isEmpty()){
	            	 
	            	  b.setVisibility(View.GONE);
	     				b2.setVisibility(View.VISIBLE);
	     				b3.setVisibility(View.GONE);
	     				  LayoutParams lp = new LayoutParams(0,LayoutParams.WRAP_CONTENT,1.73f);
	     				editText.setLayoutParams(lp);
	     				
	              }
	              
	              else{
	            	  b2.setVisibility(View.GONE);
	            	  b3.setVisibility(View.VISIBLE);
	     				b.setVisibility(View.VISIBLE);
	     				 LayoutParams lp = new LayoutParams(0,LayoutParams.WRAP_CONTENT,1.48f);
		     				editText.setLayoutParams(lp);
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
	            	  b.setVisibility(View.GONE);
	     				b2.setVisibility(View.VISIBLE);
	     				b3.setVisibility(View.GONE);
	     				
	              }
	              
	              else{
	            	  b2.setVisibility(View.GONE);
	            	  b3.setVisibility(View.VISIBLE);
	     				b.setVisibility(View.VISIBLE);
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
	            	  b.setVisibility(View.GONE);
	     				b2.setVisibility(View.VISIBLE);
	     				b3.setVisibility(View.GONE);
				  }
	              
	              else{
	            	  b2.setVisibility(View.GONE);
	            	  b3.setVisibility(View.VISIBLE);
	     				b.setVisibility(View.VISIBLE);
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
           
           
           listContacts = new ContactFetcher(this).fetchAll();
           ContactAdapter adapterContacts = new ContactAdapter(this, listContacts);
          
 			  
 			  
           Cursor c = getRecord();
           
           
    	   int count = c.getCount();
    	   if(count == 0){
    		
           RelativeLayout la1 = ( RelativeLayout)findViewById(R.id.notConnected);
           la1.setVisibility(View.VISIBLE);
           RelativeLayout la2 = ( RelativeLayout)findViewById(R.id.connected);
           la2.setVisibility(View.GONE);
           
           save_userInfo  = (Button)findViewById(R.id.save_userInfo);
           
           save_userInfo.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				EditText userName_editText = (EditText)findViewById(R.id.ownName);
				EditText userNumber_editText = (EditText)findViewById(R.id.ownPhoneNumber);
				String userName = userName_editText.getText().toString();
				String phone = userNumber_editText.getText().toString();
				
				if(saveUserInfo(userName,phone)){
					
					reload();
				}
				
				else{
					
					
				}
				
			}
		});
            
             
    	   }else{
    		   
    		   if (c.moveToFirst()) {
			        do { 
			           
			        	 sender = c.getString(1);
			        	 receiver = "BroadCast";
			        	
			        	 v1 = (ImageView)findViewById(R.id.profileImg); 
			        	 
			        	 
			             //v1.setImageURI(Uri.parse(c.getString(2)));}
			             
			           
			      // Toast.makeText(getApplication(), sender, Toast.LENGTH_SHORT).show();
			        } while (c.moveToNext());
			        
			        }
			        if (!c.isClosed()) {
			        c.close();
			        }
			        
			android.app.ActionBar action_bar = getActionBar();
			mc = new MsgDeliver(this,getAllMsg(receiver),sender,receiver);
			md = new mediaAdapter(this,getAllMedia());
    	   getActionBar().setDisplayHomeAsUpEnabled(true);
           getActionBar().setHomeButtonEnabled(true);
          
           LeftDrawer.setDrawerLockMode(LeftDrawer.LOCK_MODE_LOCKED_CLOSED);
    	  lyContacts = (LinearLayout)findViewById(R.id.leftDrawerList);
    		
    		LayoutInflater inflaterHeader = getLayoutInflater();
            ViewGroup header = (ViewGroup) inflaterHeader.inflate(
          		  R.layout.list_head, lyContacts, false);
              lyContacts.addView(header,0);
              
              
             
   			        
              LayoutInflater inflaterMediaList = getLayoutInflater();
              mediaList = (ViewGroup) inflaterMediaList.inflate(
            		  R.layout.media_drawer_left, lyContacts, false);
              mediaList.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
			        }
			});
                lyContacts.addView(mediaList,1);
                l_media = (ListView)mediaList.findViewById(R.id.media_listView);
                
                
              LayoutInflater inflaterContactHeaderList = getLayoutInflater();
              ViewGroup contactListHeader = (ViewGroup) inflaterContactHeaderList.inflate(
            		  R.layout.contact_header_list, lyContacts, false);
              contactListHeader.setClickable(true);
              contactListHeader.setOnClickListener(new View.OnClickListener() {
  				
  				@Override
  				public void onClick(View arg0) {
  			
  				}
  				
  				
  			});
              
              lyContacts.addView(contactListHeader,2);
              
              
              
              LayoutInflater inflaterContactList = getLayoutInflater();
              ViewGroup ContactsList = (ViewGroup) inflaterContactList.inflate(
            		  R.layout.leftdrawer, lyContacts, false);
              
              lyContacts.addView(ContactsList,3);
              
              
             
                
    		 
             
    		lvContacts = (ListView)ContactsList.findViewById(R.id.leftDrawer);
    		
    		  RelativeLayout la2 = ( RelativeLayout)findViewById(R.id.connected);
    		  la2.setVisibility(View.VISIBLE);
    		 RelativeLayout la1 = ( RelativeLayout)findViewById(R.id.notConnected);
          la1.setVisibility(View.GONE);
           lvContacts.setVisibility(View.VISIBLE);
           
           
            
              
                 
                 
                 
                		   //.inflate(this,R.layout.media_drawer_left_content, mediaList).findViewById(R.id.media_listView);
           //media 
           
           get_mediarefresh();
                   
    		  l.setAdapter(mc);
    		 
  			l.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      
  				@Override
  				public void onItemClick(final AdapterView<?> parent, View vw, final int position,
  						long arg3) {
  	              
					
					
  			if(receiver != "BroadCast"){
  				getActionBar().setSubtitle(name);
  				final Cursor cursor = mc.getCursor();
					 cursor.moveToPosition(position);
					getActionBar().setIcon(R.drawable.ic_launcher);
				 getActionBar().setTitle("Mduara");
				 leftDrawerToggle.setDrawerIndicatorEnabled(true);
  			}
  			
  			else{
  				getActionBar().setSubtitle(null);
  				final Cursor cursor = mc.getCursor();
					 cursor.moveToPosition(position);
					getActionBar().setIcon(R.drawable.ic_launcher);
				 getActionBar().setTitle("Mduara");
				 leftDrawerToggle.setDrawerIndicatorEnabled(true);
  			}
  					 
  					final Cursor cursor = mc.getCursor();
  					 cursor.moveToPosition(position);
  					getActionBar().setIcon(R.drawable.ic_launcher);
 					 getActionBar().setTitle("Mduara");
 					 leftDrawerToggle.setDrawerIndicatorEnabled(true);
 					MenuItem item_search = mMenu.findItem(R.id.search);
  					MenuItem item_comments = mMenu.findItem(R.id.comments);
  					MenuItem item_add_group = mMenu.findItem(R.id.group_add);
  					MenuItem item_share = mMenu.findItem(R.id.share);
  					MenuItem item_remove = mMenu.findItem(R.id.remove);
					     item_remove.setVisible(false);
  						 item_comments.setVisible(false);
  						item_search.setVisible(true);
  						 item_add_group.setVisible(false);
  					
  						 item_share.setVisible(false);
  					 play_video(cursor);
				
  				}
  				});
  		
  			
  			l.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

				@Override
				public boolean onItemLongClick(AdapterView<?> parent, View view,
						final int position, long id) {
					    MenuItem item_comments = mMenu.findItem(R.id.comments);
	  					MenuItem item_add_group = mMenu.findItem(R.id.group_add);
	  					MenuItem item_share = mMenu.findItem(R.id.share);
	  					MenuItem item_remove = mMenu.findItem(R.id.remove);
	  					MenuItem item_forward = mMenu.findItem(R.id.forward);
	  					MenuItem item_search = mMenu.findItem(R.id.search);
	  					
					final Cursor cursor = mc.getCursor();
 					 cursor.moveToPosition(position);
 					getActionBar().setIcon(R.drawable.ic_action_accept_w);
 				
 					 getActionBar().setTitle(null);
 					 leftDrawerToggle.setDrawerIndicatorEnabled(false);
					if(getActionBar().getSubtitle() != null){
						getActionBar().setSubtitle(null);
						
	  					 item_forward = mMenu.findItem(R.id.forward);
	  					 item_remove = mMenu.findItem(R.id.remove);
  					         item_remove.setVisible(true);
	  					     item_forward.setVisible(true);
	  					     l.setEnabled(false);
						
	  					     
	  					   ImageView imageView = (ImageView) findViewById(android.R.id.home);
	  						//Drawable dr = imageView.getDrawable();
	  						
	  						imageView.setOnClickListener(new View.OnClickListener() {
								
								@Override
								public void onClick(View v) {
									
									
								       
									
						  			if(receiver != "BroadCast"){
						  				getActionBar().setSubtitle(name);
											getActionBar().setIcon(R.drawable.ic_launcher);
										 getActionBar().setTitle("Mduara");
										 leftDrawerToggle.setDrawerIndicatorEnabled(true);
						  			}
						  			
						  			else{
						  				getActionBar().setSubtitle(null);
											getActionBar().setIcon(R.drawable.ic_launcher);
										 getActionBar().setTitle("Mduara");
										 leftDrawerToggle.setDrawerIndicatorEnabled(true);
						  			}
						  					 
						  					
						  					getActionBar().setIcon(R.drawable.ic_launcher);
						 					 getActionBar().setTitle("Mduara");
						 					 leftDrawerToggle.setDrawerIndicatorEnabled(true);
						 					MenuItem item_remove = mMenu.findItem(R.id.remove);
						  					MenuItem item_forward = mMenu.findItem(R.id.forward);
						  					
					  					         item_remove.setVisible(false);
						  						 item_forward.setVisible(false);
						  					    
						  					   l.setEnabled(true);
								}
							});
					}else{
						
						
		  					     item_remove.setVisible(true);
		  						 item_comments.setVisible(true);
		  						 item_add_group.setVisible(true);
		  						 item_share.setVisible(false);
		  						 item_search.setVisible(false);
		  						 l.setEnabled(false);
		  						
		  						ImageView imageView = (ImageView) findViewById(android.R.id.home);
		  						//Drawable dr = imageView.getDrawable();
		  						
		  						imageView.setOnClickListener(new View.OnClickListener() {
									
									@Override
									public void onClick(View v) {
										
										
									       
										
							  			if(receiver != "BroadCast"){
							  				getActionBar().setSubtitle(name);
											 leftDrawerToggle.setDrawerIndicatorEnabled(true);
											 l.setEnabled(true);
							  			}
							  			
							  			else{
							  				getActionBar().setSubtitle(null);
											leftDrawerToggle.setDrawerIndicatorEnabled(true);
											l.setEnabled(true);
							  			}
							  					 
							  					
							  					getActionBar().setIcon(R.drawable.ic_launcher);
							 					getActionBar().setTitle("Mduara");
							 					leftDrawerToggle.setDrawerIndicatorEnabled(true);
							  					MenuItem item_comments = mMenu.findItem(R.id.comments);
							  					MenuItem item_add_group = mMenu.findItem(R.id.group_add);
							  					MenuItem item_share = mMenu.findItem(R.id.share);
							  					MenuItem item_remove = mMenu.findItem(R.id.remove);
							  					MenuItem item_search = mMenu.findItem(R.id.search);
						  					         item_remove.setVisible(false);
							  						 item_comments.setVisible(false);
							  					     item_add_group.setVisible(false);
							  					     item_search.setVisible(true);
							  					    
							  					   
									}
								});
		  						
					}
					 
  					
  					
  					 
					return true;
				}
			});
  			l.setOnScrollListener(new OnScrollListener(){

				@Override
				public void onScroll(AbsListView view, int firstVisibleItem,
						int visibleItemCount, int totalItemCount) {
			     }

				@SuppressWarnings("deprecation")
				@Override
				public void onScrollStateChanged(AbsListView view,
						int scrollState) {
					
					if(scrollState ==  SCROLL_STATE_TOUCH_SCROLL){
						l.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
						ImageView img = (ImageView)findViewById(R.id.wallImage);
						img.setAlpha(127);
						for(int i = 0;i<=lay.getHeight();i++){
							Toast.makeText(getApplication(),lay.getHeight()+"", Toast.LENGTH_SHORT).show();
						}
					
						
					}
					
					if(scrollState ==  SCROLL_STATE_IDLE){
						l.setScrollBarStyle(View.SCROLLBARS_OUTSIDE_OVERLAY);
						ImageView img = (ImageView)findViewById(R.id.wallImage);
						img.setAlpha(255);
						
						
						
					}
						

					
					
				}});
    		 		
    		 lvContacts.setAdapter(adapterContacts);
    		 
    		 
    		 l_media.setOnItemClickListener(new AdapterView.OnItemClickListener() {

    	   			@Override
    	   			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
    	   					long arg3) {
    	   			
    	   				TextView txt = (TextView)arg1.findViewById(R.id.name);
    	   				receiver = txt.getText().toString();
    	   				LeftDrawer.closeDrawers();
    					android.app.ActionBar  b= getActionBar();
    					b.setSubtitle(receiver);
    					refr();
    	   			}
    	   		});
    	              
    	          
    	 			  
    	 			 
    	 		 
    	           lvContacts.setOnItemClickListener(new AdapterView.OnItemClickListener() {

    				@Override
    				public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
    						long arg3) {
    					Contact contact = (Contact) arg0.getItemAtPosition(arg2);
    					LeftDrawer.closeDrawers();
    					android.app.ActionBar  b= getActionBar();
    					name = contact.name;
    					b.setSubtitle(name);
    					receiver = contact.number;
    					refr();
    					
    					//Toast.makeText(getApplication(),contact.number, Toast.LENGTH_SHORT).show();
    				}
    			});
    	           
    	           
    		 

    	           final Button b2 = (Button)findViewById(R.id.button2);
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
    				        String date_s =  date + "/"+ month + "/" + yrs;
    				       
    					msg = editText.getText().toString();
    				    time_i = time.toString();
    				    date_i = date_s.toString();
    				    String media = "text";
    				    

    					  saveMsg(msg, sender, receiver, time_i, date_i,media,"seen");
    					  
    					  mc.swapCursor(getAllMsg(receiver));
    					  Cursor cursor = getLastMsg();
    					  
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
    	          
    	         
    	           
    	           Button b = (Button)findViewById(R.id.button1);
    	           b.setOnClickListener(new View.OnClickListener() {
    				
    				@Override
    				public void onClick(View arg0) {
    					
    					Intent attachIntent = new Intent();
    					attachIntent.setType("video/*,image/*,audio/*");
    					attachIntent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
    					attachIntent.setAction(Intent.ACTION_PICK);
    					Intent attachChooser =Intent.createChooser(attachIntent, "Select Video/Audio/Image");
    					startActivityForResult(attachChooser, 4);
    					
    				}
    			});
    	           
    	           
    	          
    	          
    	       v1.setOnLongClickListener(new View.OnLongClickListener() {
    			
    			@Override
    			public boolean onLongClick(View arg0) {
    				Intent attachIntent = new Intent();
    				attachIntent.setType("image/*");
    				attachIntent.setAction(Intent.ACTION_PICK);
    				Intent attachChooser =Intent.createChooser(attachIntent, "Select Profile Image");
    				startActivityForResult(attachChooser, 5);
    				return false;
    			}
    		});
    	       v1.setOnClickListener(new View.OnClickListener() {
    			
    			@Override
    			public void onClick(View arg0) {
    				
    				
    				
    			}
    		});
    		
    	          
    	   }

            
    	  
          
           }
    
    private final Runnable mRunnable = new Runnable() {

     	 
     	

		public void run() {

			new GetMsg().execute("");
			 if(continueToRun == true){
     		 
     	    mHandler.postDelayed(mRunnable, timeExecute);
     	   }

     	 }
     	    
     	};
     	
   
    public void reload() {

        Intent intent = getIntent();
        overridePendingTransition(0, 0);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        finish();

        overridePendingTransition(0, 0);
        startActivity(intent);
    }

    
    
    public boolean play_video(Cursor cursor){
    	
    	String mimeType = null;
		
		 String video_local_name = mc.getItemName(cursor);
	  

	  
	   String exte = MimeTypeMap.getFileExtensionFromUrl(Uri.encode(video_local_name));
		MimeTypeMap mime = MimeTypeMap.getSingleton();
		
	  
		if(mime.hasExtension(exte)){
			 mimeType = mime.getMimeTypeFromExtension(exte);
			 Toast.makeText(getApplication(), mimeType, Toast.LENGTH_SHORT).show();
			if(mimeType.startsWith("video/")){
				
				Intent i = new Intent(this,VideoPlayerActivity.class);
				i.putExtra("path",video_local_name );
				startActivity(i);
			}
			else if(mimeType.startsWith("image/")){
				
				Intent i = new Intent(this,ImageViewerActivity.class);
				i.putExtra("path",video_local_name );
				startActivity(i);
				
			}
			else{
				
				return true;
			}
		}
		
		return false;
    }
    
    public void get_mediarefresh(){
    	
    	Cursor media_cursor = getAllMedia();
        
        
 	   int count_media = media_cursor.getCount();
 	   if(count_media == 0){
 		   l_media.setVisibility(View.GONE);
 		   LayoutInflater inflaterEmptyMedia = getLayoutInflater();
            EmptyMedia = (ViewGroup) inflaterEmptyMedia.inflate(
          		  R.layout.head_empty, mediaList, false);
            mediaList.addView(EmptyMedia,1);
 	   }else{
 		   
 		   if (media_cursor.moveToFirst()) {
			        do { 
			        	
			            mediaList.removeView(EmptyMedia);
			        	l_media.setVisibility(View.VISIBLE);
			        	 l_media.setAdapter(md);
			        } while (media_cursor.moveToNext());
			        
			        }
			        if (!media_cursor.isClosed()) {
			        	media_cursor.close();
			        }
    		    
 	   }
    }
    
   
    public void add_media(View v){
    	String media_name = mc.media_name;
    	
    	if(saveMediaStore(media_name,"home/juu")){
    		Toast.makeText(getApplication(), "saved", Toast.LENGTH_SHORT).show();
    		 md.swapCursor(getAllMedia());
   		  Cursor cursor = getLastMedia();
   		  
   	        if (cursor.moveToFirst()) {
   	        do { 
   	           
   	            int id = cursor.getInt(2);
   	           
   	            l_media.setSelection(id);
   	         LeftDrawer.openDrawer(lyContacts);
   	         get_mediarefresh();
   	           
   	            
   	           } while (cursor.moveToNext());
   	        
   	        }
   	        if (!cursor.isClosed()) {
   	        cursor.close();
   	        }
   	        
   	       
    	}
    	
    	else{
    		Toast.makeText(getApplication(), "not save try again", Toast.LENGTH_SHORT).show();
    	}
    	
    }
  
    public void comments(View v){}
    
   
    public void refr(){
       
    	mc.swapCursor(getAllMsg(receiver));
    	
		  Cursor cursor = getLastMsg();
		  
	        if (cursor.moveToFirst()) {
	        do { 
	           
	            int id = cursor.getInt(7);
	           
	            l.setSelection(id);
	            if(upDateMsgStatus(cursor)){
	            	Toast.makeText(getApplication(), "updated", Toast.LENGTH_SHORT).show();
	            }
	            
	           
	       
	        } while (cursor.moveToNext());
	        
	        }
	        if (!cursor.isClosed()) {
	        cursor.close();
	        }
	        
		 
    }
    
    
    public void onActivityResult(int requestCode, int resultCode, Intent data){
    	if(resultCode == RESULT_OK){
    		
    		if(requestCode == 4){
    			 System.out.println("SELECT_VIDEO");
 	            Uri selectedVideoUri = data.getData();
 	            selectedPath = getPath(selectedVideoUri);
 	            System.out.println("SELECT_VIDEO Path : " + selectedPath);
 	           GoToVideoDisplay( selectedPath);

    		}
    		
    		if(requestCode == 5){
   			 System.out.println("SELECT_IMAGE");
	            Uri selectedVideoUri = data.getData();
	            selectedPath = getPath(selectedVideoUri);
	            System.out.println("SELECT_VIDEO Path : " + selectedPath);
	           GoToImageProfile( selectedPath);

   		}
    		
    		if(requestCode == 2){
    			mc.swapCursor(getAllMsg(receiver));
			  Cursor cursor = getLastMsg();
			  
		        if (cursor.moveToFirst()) {
		        do { 
		           
		            int id = cursor.getInt(7);
		           
		            l.setSelection(id);
		           
		       
		        } while (cursor.moveToNext());
		        
		        }
		        if (!cursor.isClosed()) {
		        cursor.close();
		        }
		        
			 
    		}
    	}
    
    	else if(resultCode == RESULT_CANCELED){
    		  if(requestCode == 2){
    			  Toast.makeText(getApplication(), "not sent, try again", Toast.LENGTH_SHORT).show();
    		  }
    	}
    }
    
public void GoToVideoDisplay(String path){
    	
    	
        Intent intent = new Intent(MainActivity.this,ChooseActivity.class);
        intent.putExtra("path_out",path);
        intent.putExtra("rec_out",receiver);
        intent.putExtra("sender", sender);
        intent.putExtra("msdcode", "28dk07");
        intent.putExtra("fileType", fileType);
        startActivityForResult(intent, 2);
       
    }

public void GoToImageProfile(String path){
	
	if(upDateUserInfo(path)){
		 Cursor c = getRecord();
		 
		 if (c.moveToFirst()) {
		        do { 
		           v1.setImageURI(Uri.parse(c.getString(2)));
		        	 
		        	 
		           
		      // Toast.makeText(getApplication(), sender, Toast.LENGTH_SHORT).show();
		        } while (c.moveToNext());
		        
		        }
		        if (!c.isClosed()) {
		        c.close();
		        }
         
      
		
	}
	
	/*
    Intent intent = new Intent(MainActivity.this,ChooseActivity.class);
    intent.putExtra("path_out",path);
    intent.putExtra("rec_out",receiver);
    intent.putExtra("sender", sender);
    intent.putExtra("fileType", fileType);
    startActivityForResult(intent, 2);*/
   
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

    public void home(View v){
    	LeftDrawer.closeDrawers();
		android.app.ActionBar  b= getActionBar();
		b.setSubtitle(null);
		if(b.getSubtitle() == null){
			receiver = "getActionBar().setSubtitle(null);";
			mc.swapCursor(getAllMsg(receiver));
			  Cursor cursor = getLastMsg();
			  
		        if (cursor.moveToFirst()) {
		        do { 
		           
		            int id = cursor.getInt(7);
		           
		            l.setSelection(id);
		           
		       
		        } while (cursor.moveToNext());
		        
		        }
		        if (!cursor.isClosed()) {
		        cursor.close();
		        }
		        
		}
		
    }
    @Override
    public void onPostCreate(Bundle savedInstanceState){
    	super.onPostCreate(savedInstanceState);
    	leftDrawerToggle.syncState();
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        leftDrawerToggle.onConfigurationChanged(newConfig);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        mMenu = menu;
        MenuItem item_search = mMenu.findItem(R.id.search);
        item_search.setVisible(true);
        return super.onCreateOptionsMenu(menu);
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
        
        if(id == R.id.search){
        	  
        }
        
        if (id == R.id.remove){
        	Cursor cursor = mc.getCursor();
        	int Msgid = mc.getItemId(cursor);
        	Toast.makeText(getApplication(), "message with id "+Msgid, Toast.LENGTH_SHORT).show();
        	if(delete(Msgid)){
        		mc.swapCursor(getAllMsg(receiver));
  		        if (cursor.moveToFirst()) {
  		        do { 
  		           
  		        	if(receiver != "BroadCast"){
  		  				getActionBar().setSubtitle(name);
  							getActionBar().setIcon(R.drawable.ic_launcher);
  						 getActionBar().setTitle("Mduara");
  						 leftDrawerToggle.setDrawerIndicatorEnabled(true);
  		  			}
  		  			
  		  			else{
  		  				getActionBar().setSubtitle(null);
  							getActionBar().setIcon(R.drawable.ic_launcher);
  						 getActionBar().setTitle("Mduara");
  						 leftDrawerToggle.setDrawerIndicatorEnabled(true);
  		  			}
  		  					 
  		  					getActionBar().setIcon(R.drawable.ic_launcher);
  		 					 getActionBar().setTitle("Mduara");
  		 					 leftDrawerToggle.setDrawerIndicatorEnabled(true);
  		  					MenuItem item_comments = mMenu.findItem(R.id.comments);
  		  					MenuItem item_add_group = mMenu.findItem(R.id.group_add);
  		  					MenuItem item_share = mMenu.findItem(R.id.share);
  		  					MenuItem item_remove = mMenu.findItem(R.id.remove);
  		  				    MenuItem item_forward = mMenu.findItem(R.id.forward);
  		  				    MenuItem item_search = mMenu.findItem(R.id.search);
						         item_search.setVisible(true);
  							     item_remove.setVisible(false);
  		  						 item_comments.setVisible(false);
  		  						 item_add_group.setVisible(false);
  		  						 item_share.setVisible(false);
  		  						 item_forward.setVisible(false);
  		  						 l.setEnabled(true);
  		  						 delete_all();
  		  					
  		        } while (cursor.moveToNext());
  		        
  		        }
  		        if (!cursor.isClosed()) {
  		        cursor.close();
  		        }
  		        
  			 
      		
        		Toast.makeText(getApplication(), "message deleted", Toast.LENGTH_SHORT).show();
        		
        	}
        	else{
        		Toast.makeText(getApplication(), "message not deleted", Toast.LENGTH_SHORT).show();
        	}
        }
       if(id == R.id.group_add){
    	   Cursor c = mc.getCursor();
    	   String media_name = mc.getItemSenderName(c);
    	   int idMedia  = mc.getItemId(c);
       	   cursor_server = c;
       	if(saveMediaStore(media_name,"home/juu")){
       		Toast.makeText(getApplication(), "saved", Toast.LENGTH_SHORT).show();
       		 md.swapCursor(getAllMedia());
      		  
      	        if (c.moveToFirst()) {
      	        do { 
      	           
      	           
      	           l_media.setSelection(idMedia);
      	         LeftDrawer.openDrawer(lyContacts);
      	         get_mediarefresh();
      	           
      	       if(receiver != "BroadCast"){
		  				getActionBar().setSubtitle(name);
							
		  			}
      	       else{
		  				getActionBar().setSubtitle(null);
							
		  			}
      	         
      	     getActionBar().setIcon(R.drawable.ic_launcher);
				 getActionBar().setTitle("Mduara");
				 leftDrawerToggle.setDrawerIndicatorEnabled(true);
				 
				MenuItem item_comments = mMenu.findItem(R.id.comments);
				MenuItem item_add_group = mMenu.findItem(R.id.group_add);
				MenuItem item_share = mMenu.findItem(R.id.share);
				MenuItem item_remove = mMenu.findItem(R.id.remove);
				MenuItem item_search = mMenu.findItem(R.id.search);
		         item_search.setVisible(true);
				     item_remove.setVisible(false);
					 item_comments.setVisible(false);
				
					 item_add_group.setVisible(false);
				
					 item_share.setVisible(false);
					 l.setEnabled(false);
      	            
      	           } while (c.moveToNext());
      	        
      	        }
      	        if (!c.isClosed()) {
      	        c.close();
      	        }
      	        
      	       
       	}
       	
       	else{
       		Toast.makeText(getApplication(), "not save try again", Toast.LENGTH_SHORT).show();
       	}
       	
       }
        
       if(id == R.id.comments){
    	   
    	   Cursor c = mc.getCursor();
    	   String media_name = mc.getItemSenderName(c);
    	   int idMedia  = mc.getItemId(c);
    	   String id_changed = String.valueOf(idMedia);
    	   Intent i =new Intent();
    	   i.setClass(this,CommentsActivity.class);
    	   i.putExtra("receiver", this.receiver);
    	   i.putExtra("media_id", id_changed);
    	   startActivity(i);
    	   
    	   if(receiver != "BroadCast"){
	  				getActionBar().setSubtitle(name);
	  					  			}
	  			
	  	   else{
	  				getActionBar().setSubtitle(null);
			
	  			}
	  					 
	  					getActionBar().setIcon(R.drawable.ic_launcher);
	 					 getActionBar().setTitle("Mduara");
	 					 leftDrawerToggle.setDrawerIndicatorEnabled(true);
	  					MenuItem item_comments = mMenu.findItem(R.id.comments);
	  					MenuItem item_add_group = mMenu.findItem(R.id.group_add);
	  					MenuItem item_share = mMenu.findItem(R.id.share);
	  					MenuItem item_remove = mMenu.findItem(R.id.remove);
	  					MenuItem item_search = mMenu.findItem(R.id.search);
	  					
				         item_search.setVisible(true);
						     item_remove.setVisible(false);
	  						 item_comments.setVisible(false);
	  						 item_add_group.setVisible(false);
	  						 item_share.setVisible(false);
	  						 l.setEnabled(true);
	  						
    	  
       }
       
       if(id == R.id.forward){
    	   forward = "1234";
    	   Cursor c = mc.getCursor();
    	   forward_receiver_name = mc.getItemReceiver(c);
    	   forward_msg = mc.getItemMsg(c);
    	   mediaTest = mc.getItemMediaTest(c);
	        LeftDrawer.openDrawer(lyContacts);
		   
	        MenuItem item_forward = mMenu.findItem(R.id.forward);
		     MenuItem item_remove = mMenu.findItem(R.id.remove);
		     item_forward.setVisible(false);
			 item_remove.setVisible(false);
			 l.setEnabled(true);
    	   
       }
        if(leftDrawerToggle.onOptionsItemSelected(item)){
        	android.app.ActionBar  b= getActionBar();
    		if(b.getSubtitle() == null){
    			receiver = "BroadCast";
    			mc.swapCursor(getAllMsg(receiver));
    			  Cursor cursor = getLastMsg();
    			  
    		        if (cursor.moveToFirst()) {
    		        do { 
    		           
    		            int Id = cursor.getInt(7);
    		           
    		            l.setSelection(Id);
    		           
    		       
    		        } while (cursor.moveToNext());
    		        
    		        }
    		        if (!cursor.isClosed()) {
    		        cursor.close();
    		        }
    		}
        	return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    public Cursor getRecord(){
    	SQLiteDatabase db;
    	db = user_info.getWritableDatabase();
    	return db.rawQuery("select * from UserInfoTable", null);
    }

    
    public boolean saveMsg( String msg,String sender,String receiver,String time,String date,String media, String status) { 
        boolean r_in;
        SQLiteDatabase db;
        db = msgserver.getWritableDatabase();
  	ContentValues values = new ContentValues();
  	values.put("msg",msg);
  	
  	values.put("sender",sender);
  	
  	values.put("receiver",receiver);
  	values.put("time",time);
  	values.put("date",date);
  	values.put("media_test",media);
  	values.put("status",status);
  	
  	try{
  	db.insert("MsgTable", null, values);
  	r_in = true;
  	}catch(Exception e){
  		r_in = false;
  	}
  	return r_in;
  	
                                             }
    
    public boolean saveMediaStore( String name,String image) { 
        boolean r_in;
        SQLiteDatabase db;
        db = mediaServer.getWritableDatabase();
  	ContentValues values = new ContentValues();
  	values.put("name",name);
  	
  	values.put("image",image);
  	
  	
  	try{
  	db.insert("MediaStoreTable", null, values);
  	r_in = true;
  	}catch(Exception e){
  		r_in = false;
  	}
  	return r_in;
  	
                                             }
    

    public Cursor getAllMsg(String receiver) {
    	Toast.makeText(getApplication(), this.receiver, Toast.LENGTH_LONG).show();
    	final String[] queryParameters = new String[1];
    	queryParameters[0] = receiver;
    	String sender = "0712020408";
    	String[] columns = {"msg","sender","receiver","time","date","media_test","media_about","_id"};
    	 String sender_col = "sender";
    	 String receiver_col = "receiver";
    	SQLiteDatabase db;
        db = msgserver.getWritableDatabase();
       
        /*
        return db.rawQuery(
                                  "select * from MsgTable where receiver =? order by _id",
                                  queryParameters
                                 );*/
                             // sender_col+"='"+sender+"'"    
       return db.query( "MsgTable",
    		              columns, 
    		              receiver_col+"='"+String.valueOf(this.receiver)+"'",
    		             // null,
    		              null, 
    		              null, 
    		              null, 
    		              null, 
    		              null);
       
    }
    
    public Cursor getLastMsg() { 
    	SQLiteDatabase db;
        db = msgserver.getWritableDatabase();
        return db.rawQuery(
                                  "select * from " + "MsgTable order by _id desc limit 1", 
                                   null
                                 );
    }
    
    public boolean saveUserInfo(String Name,String number){
    	
   	 boolean r_in;
        SQLiteDatabase db;
        db = user_info.getWritableDatabase();
  	ContentValues values = new ContentValues();
  	values.put("user_name", Name);
  	
  	values.put("mobile_number",number);
  	
  
  	try{
  	db.insert("UserInfoTable", null, values);
  	r_in = true;
  	}catch(Exception e){
  		r_in = false;
  	}
  
		return r_in;
   	
   }
    
    public Cursor getAllMedia(){
    	
    	 SQLiteDatabase db;
         db = mediaServer.getWritableDatabase();
    	 return db.rawQuery(
                 "select * from MediaStoreTable order by _id",
                  null
                );
    }
    
    public Cursor getLastMedia() { 
    	SQLiteDatabase db;
        db = mediaServer.getWritableDatabase();
        return db.rawQuery(
                                  "select * from " + "MediaStoreTable order by _id desc limit 1", 
                                   null
                                 );
    }
    
    public boolean upDateUserInfo(String imagePath){
    	
    	 boolean r_in;
         SQLiteDatabase db;
         db = user_info.getWritableDatabase();
         ContentValues values = new ContentValues();
         values.put("profile_image", imagePath);
         try{
         db.update("UserInfoTable", values, null, null);
         r_in = true;
         }catch(Exception e){
        	 r_in = false;
         }
		return r_in;
         
    }
    
    public boolean upDateMsgStatus(Cursor cursor){
    	int id = mc.getItemId(cursor);
   	 boolean r_in;
        SQLiteDatabase db;
        db = msgserver.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("status", "seen");
        try{
        db.update("MsgTable", values, null, null);
        r_in = true;
        }catch(Exception e){
       	 r_in = false;
        }
		return r_in;
        
   }
    
    
    public boolean delete(int id){
         boolean r_in;
         SQLiteDatabase db;
         db = msgserver.getWritableDatabase();
         try{
         db.delete("MsgTable",
        			 "_id="+String.valueOf(id),
        			 null);
         r_in = true;}
         catch(Exception e){
        	 r_in = false;
         }
        	return r_in;
    }
  
    public boolean delete_all(){
        boolean r_in;
        SQLiteDatabase db;
        db = ca.getWritableDatabase();
        try{
        db.delete("CommentsTable",
       			  null,
       			 null);
        r_in = true;}
        catch(Exception e){
       	 r_in = false;
        }
       	return r_in;
   }
    
   public class GetMsg extends AsyncTask<String,String,String>{

	@Override
	protected String doInBackground(String... arg0) {
		
		return null;
	}
	
	 @Override
     protected void onPostExecute(String file_url) {

		 mc.swapCursor(getAllMsg(receiver));
		 Cursor cursor = getLastMsg();
		  
	        if (cursor.moveToFirst()) {
	        do { 
	           
	        	
	        } while (cursor.moveToNext());
	        
	        }
	        if (!cursor.isClosed()) {
	        cursor.close();
	        }
	        
	        Toast.makeText(getApplication(),"test",Toast.LENGTH_SHORT).show();	  
	        
	 }
	   
   }
 
   
}
