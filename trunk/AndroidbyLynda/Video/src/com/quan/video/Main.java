package com.quan.video;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.widget.VideoView;

public class Main extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		VideoView videoView = (VideoView) findViewById(R.id.videoView1);
		videoView.setVideoURI(Uri.parse("android.resource://com.quan.video/"
				+ R.raw.monkey));
		videoView.start();
	}
}