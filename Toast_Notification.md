# Toast Notification #

怎么Toast Notification:
  1. 在main.xml里加一个button。
  1. 在activity里加入代码：
```
package com.quan.toast;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

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
				Toast toast = Toast.makeText(Main.this, "This is some toast",
						5000);
				toast.setGravity(Gravity.CENTER, 0, 0);
				toast.show();
			}
		});
	}
}
使用Toast toast = Toast.makeText(Main.this, "This is some toast",5000); 建一个toast notification。
使用toast.setGravity(Gravity.CENTER, 0, 0); 设置它的位置。
使用toast.show(); 显示出来
```
  1. 源代码： https://ggggg.googlecode.com/svn/trunk/AndroidbyLynda/Toast/