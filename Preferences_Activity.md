# Preferences Activity #

怎么用Preferences Activity:
  1. 在main.xml里加入一个Button和一个TextView:
  1. New Preference android xml file. prefs.xml
```
<?xml version="1.0" encoding="utf-8"?>
<PreferenceScreen
  xmlns:android="http://schemas.android.com/apk/res/android">
    
    <CheckBoxPreference 
    	android:key="first" 
    	android:title="First Option"
    	android:summary="This is the first oprtion."/>
    <CheckBoxPreference 
    	android:key="second" 
    	android:title="Second Option"
    	android:summary="This is the second oprtion."/>    
    
</PreferenceScreen>

使用<CheckBoxPreference > 加入PreferenceScreenUI。
```
  1. 新建一个PreferenceActivity. Preferences.java
```
package com.quan.preferencesactivity;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class Preferences extends PreferenceActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.prefs);
	}
}
使用addPreferencesFromResource(R.xml.prefs); load prefs.xml 到PreferenceActivity里。
```
  1. 在AndroidManifest.xml里加入Preferences activity。 <activity android:name=".Preferences">
  1. 在Main activity里加入代码。
```
package com.quan.preferencesactivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Main extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		SharedPreferences sharedPreferences = PreferenceManager
				.getDefaultSharedPreferences(this);
		boolean frist = sharedPreferences.getBoolean("first", false);
		TextView textView = (TextView) findViewById(R.id.textView1);
		textView.setText(String.valueOf(frist));
		
		Button button = (Button) findViewById(R.id.button1);
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(Main.this, Preferences.class);
				startActivity(intent);

			}
		});
	}
}
使用SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this); 得到默认的sharepreferences。
使用boolean frist = sharedPreferences.getBoolean("first", false); 取sharepreferences里存的对应的key为first的值。
使用Intent intent = new Intent(Main.this, Preferences.class); startActivity(intent); 切换到preferenceactivity。
每个app对应一个sharepreferences，设置的值会自动存到里面去。
```
  1. 源代码：https://ggggg.googlecode.com/svn/trunk/AndroidbyLynda/Preferences_Activity/