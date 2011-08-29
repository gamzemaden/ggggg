package com.quan.yamba;

import winterwell.jtwitter.Twitter;
import android.app.Application;
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
		
        //设默认的apiRoot是http://yamba.marakana.com/api
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

}