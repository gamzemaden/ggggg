# Camera #

怎么调用Camera:
  1. 在main.xml里加入一个button，一个imageview:
```
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:orientation="vertical"
    android:layout_width="fill_parent"
    android:layout_height="fill_parent"
    >
    <Button android:id="@+id/button1" android:layout_width="wrap_content" android:layout_height="wrap_content" android:text="Take Photo"></Button>
    <ImageView android:src="@drawable/icon" android:id="@+id/imageView1" android:layout_width="match_parent" android:layout_height="match_parent"></ImageView>
</LinearLayout>
```
  1. 在activity里加入代码
```
package com.quan.camera;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

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
				Intent intent = new Intent(
						android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
				startActivityForResult(intent, 0);
			}
		});
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		
		ImageView imageView = (ImageView) findViewById(R.id.imageView1);
		Bitmap bitmap = (Bitmap) data.getExtras().get("data");
		imageView.setImageBitmap(bitmap);
	}
}
使用Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE); 指定目的是调用相机照相
使用startActivityForResult(intent, 0); 打开相机返回结果
使用protected void onActivityResult(int requestCode, int resultCode, Intent data), 接收返回的结果
使用Bitmap bitmap = (Bitmap) data.getExtras().get("data"); 得到返回的照片
```
  1. 源代码： https://ggggg.googlecode.com/svn/trunk/AndroidbyLynda/Video/