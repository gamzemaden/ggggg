# Tween Animation #


怎么使用动画效果:
  1. new animation android xml file. animation.xml
```
<?xml version="1.0" encoding="utf-8"?>
<set xmlns:android="http://schemas.android.com/apk/res/android"
	android:interpolator="@android:anim/accelerate_interpolator">
	<scale 
		android:fromXScale="0.5"
		android:toXScale="4.0"
		android:fromYScale="0.5"
		android:toYScale="2.0"
		android:pivotX="50%"
		android:pivotY="50%"
		android:duration="4000"
		/>
    <rotate android:fromDegrees="0"
    	android:toDegrees="180"
    	android:pivotX="50%"
    	android:pivotY="50%"
    	android:duration="2000"
    	android:startOffset="2000"
    	/>

</set>
```
  1. 加一个button到main.xml里。
  1. 在activity里加入让button使用效果的代码。
```
package com.quan.tween;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

public class Main extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		final Button button = (Button) findViewById(R.id.button1);
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Animation animation = AnimationUtils.loadAnimation(Main.this,
						R.anim.animation);
				button.startAnimation(animation);
			}
		});

		final Button button2 = (Button) findViewById(R.id.button2);
		button2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Animation animation2 = AnimationUtils.loadAnimation(Main.this,
						R.anim.animation2);
				button2.startAnimation(animation2);
			}
		});
```
  1. 源代码 https://ggggg.googlecode.com/svn/trunk/AndroidbyLynda/Tween/