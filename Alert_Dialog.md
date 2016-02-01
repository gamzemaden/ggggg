# Alert Dialog #


怎样使用alert dialog:
  1. 在main.xml里加一个button。
  1. 在activity里加入代码。
```
package com.quan.alertdialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
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
			public void onClick(View arg0) {

				AlertDialog.Builder builder = new AlertDialog.Builder(Main.this);
				builder.setMessage("Are you sure you want to exit?");
				builder.setCancelable(false);
				builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						Main.this.finish();
					}
				});
				builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.cancel();
					}
				});
				
				AlertDialog alertDialog = builder.create();
				alertDialog.show();
				
			}
		});
	}
}

使用AlertDialog.Builder builder = new AlertDialog.Builder(Main.this);创建一个AlertDialog的builder。
之后设置builder的内容。

使用builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						Main.this.finish();
					}
				}); 
设置alertdialog里的按钮，可以加入监听按钮选择的执行代码。

使用AlertDialog alertDialog = builder.create();用builder创建一个alertdialog
使用alertDialog.show();显示创建的alertdialog。
```
  1. 源代码： https://ggggg.googlecode.com/svn/trunk/AndroidbyLynda/Alert_Dialog/