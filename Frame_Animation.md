# Fram Animation #


怎么使用drawable动画:
  1. New Animation android xml file. animation.xml
```
<?xml version="1.0" encoding="utf-8"?>
<animation-list android:oneshot="true" xmlns:android="http://schemas.android.com/apk/res/android">

	<item android:drawable="@drawable/camp_fire1" android:duration="30"/>
	<item android:drawable="@drawable/camp_fire2" android:duration="30"/>
	<item android:drawable="@drawable/camp_fire3" android:duration="30"/>
	<item android:drawable="@drawable/camp_fire4" android:duration="30"/>
	<item android:drawable="@drawable/camp_fire5" android:duration="30"/>
	<item android:drawable="@drawable/camp_fire6" android:duration="30"/>
	<item android:drawable="@drawable/camp_fire7" android:duration="30"/>
	<item android:drawable="@drawable/camp_fire8" android:duration="30"/>
	<item android:drawable="@drawable/camp_fire9" android:duration="30"/>
	<item android:drawable="@drawable/camp_fire10" android:duration="30"/>
	<item android:drawable="@drawable/camp_fire11" android:duration="30"/>
	<item android:drawable="@drawable/camp_fire12" android:duration="30"/>
	<item android:drawable="@drawable/camp_fire13" android:duration="30"/>
	<item android:drawable="@drawable/camp_fire14" android:duration="30"/>
	<item android:drawable="@drawable/camp_fire15" android:duration="30"/>
	<item android:drawable="@drawable/camp_fire16" android:duration="30"/>
	<item android:drawable="@drawable/camp_fire17" android:duration="30"/>
	

</animation-list>
动画播放默认是循环的，android:oneshot="true" 控制只播放一次。
```
  1. 把动画的帧图片拷到drawable-hdpi文件夹
  1. 加一个图片控件到main.xml里
  1. activity里加入启动动画的代码。
```
package com.quan.frame;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class Main extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        final ImageView iv = (ImageView) findViewById(R.id.imageView1); 
        iv.setBackgroundResource(R.anim.animation);
        
        iv.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				AnimationDrawable anim = (AnimationDrawable) iv.getBackground();
				anim.start();
			}
		});
    }
}
```
  1. 源代码 http://ggggg.googlecode.com/svn/trunk/AndroidbyLynda/Frame_Animation/