# Network Access #

怎么访问网络:
  1. 在AndroidManifest.xml里加入访问inrtenet的权限。
```
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
      package="com.quan.networkaccess"
      android:versionCode="1"
      android:versionName="1.0">
    <uses-sdk android:minSdkVersion="10" />
    <uses-permission android:name="android.permission.INTERNET"></uses-permission>

    <application android:icon="@drawable/icon" android:label="@string/app_name">
        <activity android:name=".Main"
                  android:label="@string/app_name">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />
                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>

    </application>
</manifest>
```
  1. 在main.xml里加入一个EditText,一个button,一个TextView:
  1. 在activity里加入代码
```
package com.quan.networkaccess;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Main extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		final EditText editText = (EditText) findViewById(R.id.editText1);
		final Button button = (Button) findViewById(R.id.button1);
		final TextView textView = (TextView) findViewById(R.id.textView1);

		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				try {
					URL url = new URL(editText.getText().toString());
					URLConnection urlConnection = url.openConnection();
					BufferedReader bufferedReader = new BufferedReader(
							new InputStreamReader(urlConnection
									.getInputStream()));
					String line = "";
					while ((line = bufferedReader.readLine()) != null) {
						textView.append(line);
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		});
	}
}
使用URL url = new URL(editText.getText().toString()); 通过用户的输入创建URL
使用URLConnection urlConnection = url.openConnection(); 打开connection
使用BufferedReader bufferedReader = new BufferedReader(
							new InputStreamReader(urlConnection
									.getInputStream())); 建一个bufferedReader。
使用while ((line = bufferedReader.readLine()) != null) {
						textView.append(line); 把返回结果写到TextView里。
```
  1. 源代码： https://ggggg.googlecode.com/svn/trunk/AndroidbyLynda/Network_Access/