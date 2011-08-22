package com.quan.status;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
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
				NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
				Notification notification = new Notification(
						android.R.drawable.stat_notify_more,
						"This is important.\n Hello baby.", System
								.currentTimeMillis());
				Context context = Main.this;
				CharSequence title = "You have been notified";
				CharSequence details = "Continue with what you were doing";
				Intent intent = new Intent(context, Main.class);
				PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
				notification.setLatestEventInfo(context, title, details, pendingIntent);
				
				notificationManager.notify((int) System.currentTimeMillis(), notification);
			}
		});
	}
}