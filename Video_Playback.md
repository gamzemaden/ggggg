# Video Playback #

怎么播放音频:
  1. 在main.xml里加入一个VideoView:
```
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:orientation="vertical"
    android:layout_width="fill_parent"
    android:layout_height="fill_parent"
    >
    <VideoView android:layout_height="wrap_content" android:layout_width="match_parent" android:id="@+id/videoView1"></VideoView>
</LinearLayout>
```
  1. 在activity里加入代码
```
package com.quan.video;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
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
		videoView.setMediaController(new MediaController(Main.this));
		videoView.start();
		videoView.requestFocus();
	}
}
使用videoView.setVideoURI(Uri.parse("android.resource://com.quan.video/"	+ R.raw.monkey)); 指定要load的video。
使用videoView.setMediaController(new MediaController(Main.this)); 设置播放控制
使用videoView.start(); 播放video。
```
  1. 源代码： https://ggggg.googlecode.com/svn/trunk/AndroidbyLynda/Video/