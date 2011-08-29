package com.quan.yamba;

import java.util.List;

import winterwell.jtwitter.Twitter;
import winterwell.jtwitter.TwitterException;

import android.app.Service;
import android.content.Intent;
import android.content.res.Resources.Theme;
import android.os.IBinder;
import android.util.Log;

public class UpdaterService extends Service {
	private static final String TAG = "UpdaterService";

	static final int DELAY = 60000;
	private boolean runFlag = false;
	private Updater updater;
	private YambaApplication yamba;

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		this.yamba = (YambaApplication) getApplication();
		this.updater = new Updater();
		Log.d(TAG, "onCreated");
		super.onCreate();
	}

	@Override
	public void onDestroy() {
		this.runFlag = false;
		this.updater.interrupt();
		this.updater = null;
		this.yamba.setServiceRunning(false);

		Log.d(TAG, "onDestroyed");
		super.onDestroy();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		this.runFlag = true;
		this.updater.start();
		this.yamba.setServiceRunning(true);

		Log.d(TAG, "OnStarted");
		return super.onStartCommand(intent, flags, startId);
	}

	private class Updater extends Thread {
		List<Twitter.Status> timeline;

		public Updater() {
			super("UpdaterService-Updater");
		}

		@Override
		public void run() {
			UpdaterService updaterService = UpdaterService.this;
			while (updaterService.runFlag) {
				Log.d(TAG, "Updater running");
				try {
					try {
						timeline = yamba.getTwitter().getFriendsTimeline();
					} catch (TwitterException e) {
						Log.e(TAG, "Failed to connect to twitter service", e);
					}
					for (Twitter.Status status : timeline) {
						Log.d(TAG, String.format("%s : %s", status.user.name,
								status.text));
					}

					Log.d(TAG, "Updater ran");
					Thread.sleep(DELAY);
				} catch (InterruptedException e) {
					updaterService.runFlag = false;
				}
			}

		}
	}

}
