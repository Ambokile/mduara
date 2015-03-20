package com.ambovan.mduara;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

public class VideoPlayerActivity extends ActionBarActivity {
  String path_in;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_video_player);

		path_in = getIntent().getStringExtra("path");
		Uri url = Uri.parse(path_in);
		
		TextView tx = (TextView)findViewById(R.id.video_subTitle);
		VideoView v = (VideoView) findViewById(R.id.video_player);
		 MediaController mediaController = new MediaController(this);
	        mediaController.setAnchorView(v);
	        v.setMediaController(mediaController);
	        v.setVideoURI(url);
	        //v.seekTo(100);
	        v.start();
		
	}
}
