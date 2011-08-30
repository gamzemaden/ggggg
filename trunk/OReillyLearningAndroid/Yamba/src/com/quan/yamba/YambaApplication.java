package com.quan.yamba;

import java.util.List;

import winterwell.jtwitter.Twitter;
import winterwell.jtwitter.Twitter.Status;
import android.app.Application;
import android.content.ContentValues;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.preference.PreferenceManager;
import android.util.Log;

public class YambaApplication extends Application implements
		OnSharedPreferenceChangeListener {
	private boolean serviceRunning;
	private static final String TAG = YambaApplication.class.getSimpleName();
	public Twitter twitter;
	private SharedPreferences prefs;
	private StatusData statusData;

	public boolean isServiceRunning(){
		return serviceRunning;
	}
	
	public void setServiceRunning(boolean serviceRunning){
		this.serviceRunning = serviceRunning;
	}
	
	@Override
	public void onCreate() {
		this.prefs = PreferenceManager.getDefaultSharedPreferences(this);
		this.prefs.registerOnSharedPreferenceChangeListener(this);
		this.statusData = new StatusData(this);
		Log.i(TAG, "onCreated");
		
        //��Ĭ�ϵ�apiRoot��http://yamba.marakana.com/api
        if (prefs.getString("apiRoot", "") == "") {
            Editor editor = prefs.edit();
            editor.putString("apiRoot", "http://yamba.marakana.com/api");
            editor.commit();
		}  
		super.onCreate();
	}

	@Override
	public void onTerminate() {
		Log.i(TAG, "onTerminated");
		super.onTerminate();
	}

	public synchronized Twitter getTwitter() {
		if (this.twitter == null) {
			String username, password, apiRoot;
			username = prefs.getString("username", "");
			password = prefs.getString("password", "");
			apiRoot = prefs.getString("apiRoot", "");

			this.twitter = new Twitter(username, password);
			this.twitter.setAPIRootUrl(apiRoot);
		}
		return this.twitter;
	}

	@Override
	public synchronized void onSharedPreferenceChanged(SharedPreferences arg0,
			String arg1) {
		this.twitter = null;
	}
	
	public StatusData getStatusData(){
		return statusData;
	}
	
	public synchronized int fetchStatusUpdates(){
		Log.d(TAG, "Fetching status updates");
		Twitter twitter = this.getTwitter();
		if(twitter == null){
			Log.d(TAG, "Twitter connection info not initialized");
			return 0;
		}
		try {
			List<Status> statusUpdates = twitter.getFriendsTimeline();
			long latestStatusCreatedAtTime = this.getStatusData().getLatestStatusCreatedAtTime();
			int count = 0;
			ContentValues contentValues = new ContentValues();
			for (Status status : statusUpdates) {
				contentValues.put(StatusData.C_ID, status.getId());
				long createdAt = status.getCreatedAt().getTime();
				contentValues.put(StatusData.C_CREATED_AT, createdAt);
				contentValues.put(StatusData.C_TEXT, status.getText());
				contentValues.put(StatusData.C_USER, status.getUser().getName());
				Log.d(TAG, "Got update with id " + status.getId() + ". Saving");
				this.getStatusData().insertOrIgnore(contentValues);
				if(latestStatusCreatedAtTime < createdAt){
					count++;
				}
			}
			Log.d(TAG, count > 0 ? "Got" + count + " status updates" : "No new staus updates");
			return count;
		} catch (RuntimeException e) {
			Log.e(TAG, "Failed to fetch status updates", e);
			return 0;
		}
	}

}