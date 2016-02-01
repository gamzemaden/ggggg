# Status bar notification #


怎样使用Status bar notification:
  1. 在main.xml里加一个button。
  1. 在activity里加入代码。
```
package com.quan.status;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
				NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
				Notification notification = new Notification(
						android.R.drawable.stat_notify_more,
						"This is important.\n Hello baby.", System
								.currentTimeMillis());

				Context context = Main.this;
				CharSequence title = "You have been notified";
				CharSequence details = "Continue with what you were doing";
				Intent intent = new Intent(context, Main.class);
				PendingIntent pendingIntent = PendingIntent.getActivity(
						context, 0, intent, 0);
				notification.setLatestEventInfo(context, title, details,
						pendingIntent);
				notification.sound = Uri.parse("android.resource://com.quan.status/" + R.raw.birthdaysong);
				notificationManager.notify((int) System.currentTimeMillis(),
						notification);
			}
		});
	}
}
使用NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE); 获得notificationmanager.

使用Notification notification = new Notification(
						android.R.drawable.stat_notify_more,
						"This is important.\n Hello baby.", System
								.currentTimeMillis());创建一个notification。
图标：android.R.drawable.stat_notify_more 
显示的文字："This is important.\n Hello baby."
notification里显示的系统时间：System.currentTimeMillis()

点notification去的目的地：PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

设置声音：notification.sound = Uri.parse("android.resource://com.quan.status/" + R.raw.birthdaysong);

产生notification：notificationManager.notify((int) System.currentTimeMillis(),notification); id不一样产生的notification就不同。
```
  1. 源代码： https://ggggg.googlecode.com/svn/trunk/AndroidbyLynda/Status/