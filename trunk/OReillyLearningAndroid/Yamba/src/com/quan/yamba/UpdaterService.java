package com.quan.yamba;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.util.Log;

public class UpdaterService extends IntentService {
	private static final String TAG = "UpdaterService";

	public static final String NEW_STATUS_INTENT = "com.quan.yamba.NEW_STATUS";
	public static final String NEW_STATUS_EXTRA_COUNT = "NEW_STATUS_EXTRA_COUNT";
	public static final String RECEIVE_TIMELINE_NOTIFICATIONS = "com.quan.yamba.RECEIVE_TIMELINE_NOTIFICATIONS";

	private NotificationManager notificationManager;
	private Notification notification;

	public UpdaterService() {
		super(TAG);
		Log.d(TAG, "UpdaterService constructed");
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		Intent intent2;
		this.notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		this.notification = new Notification(android.R.drawable.stat_notify_chat,
				"", 0);

		Log.d(TAG, "onHandleIntent'ing");

		YambaApplication yambaApplication = (YambaApplication) getApplication();
		int newUpdates = yambaApplication.fetchStatusUpdates();
		if (newUpdates > 0) {
			Log.d(TAG, "We have a new status");
			intent2 = new Intent(NEW_STATUS_INTENT);
			intent2.putExtra(NEW_STATUS_EXTRA_COUNT, newUpdates);
			sendBroadcast(intent2, RECEIVE_TIMELINE_NOTIFICATIONS);
			sendTimelineNotification(newUpdates);
		}
	}

	private void sendTimelineNotification(int timelineUpdateCount) {
		Log.d(TAG, "send TimelineNotification'ing");
		PendingIntent pendingIntent = PendingIntent.getActivity(this, -1,
				new Intent(this, TimelineActivity.class),
				PendingIntent.FLAG_UPDATE_CURRENT);
		this.notification.when = System.currentTimeMillis();
		this.notification.flags = Notification.FLAG_AUTO_CANCEL;
		CharSequence notificationTitle = this
				.getText(R.string.msgNotificationTitle);
		CharSequence notificationSummary = String.format(
				this.getText(R.string.msgNotificationMessage).toString(), timelineUpdateCount);
		this.notification.setLatestEventInfo(this, notificationTitle,
				notificationSummary, pendingIntent);
		this.notificationManager.notify(0, this.notification);
		Log.d(TAG, "sendTimelineNotificationed");
	}

}
