# Audio Playback #

怎么播放音频:
  1. 在main.xml里加入一个button。
  1. 在activity里加入代码
```
package com.quan.soundplayback;

import android.app.Activity;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Main extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		Button button = (Button) findViewById(R.id.button1);
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				MediaPlayer mediaPlayer = MediaPlayer.create(Main.this,
						R.raw.birthdaysong);
				mediaPlayer.start();
			}
		});
	}
}
使用MediaPlayer mediaPlayer = MediaPlayer.create(Main.this,R.raw.birthdaysong); 建一个mediaplayer。
播放音频mediaPlayer.start();
```
  1. 源代码： https://ggggg.googlecode.com/svn/trunk/AndroidbyLynda/Sound_Playback/