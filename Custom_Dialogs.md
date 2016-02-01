# Custom Dialog #

怎么建自定义的Dialog:
  1. New layout android xml file. dialog.xml
```
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout
  xmlns:android="http://schemas.android.com/apk/res/android"
  android:orientation="vertical"
  android:layout_width="match_parent"
  android:layout_height="match_parent" android:padding="20dp">
    <ImageView android:src="@drawable/ff" android:layout_height="wrap_content" android:layout_width="wrap_content" android:id="@+id/imageView1"></ImageView>
    <TextView android:layout_width="wrap_content" android:id="@+id/textView1" android:layout_height="wrap_content" android:layout_marginLeft="20dp" android:layout_marginTop="10dp" android:textSize="20sp" android:text="This is fruit"></TextView>
    
</LinearLayout>
```
  1. 在activity里加入代码
```
package com.quan.customdialogs;

import android.app.Activity;
import android.app.Dialog;
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
				Dialog dialog = new Dialog(Main.this);
				dialog.setContentView(R.layout.dialog);
				dialog.setTitle("This is important");
				dialog.show();
			}
		});
    }
}
```
  1. 源代码： https://ggggg.googlecode.com/svn/trunk/AndroidbyLynda/Custom_Dialogs/