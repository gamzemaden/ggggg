package com.quan.yamba;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class BaseActivity extends Activity {
	YambaApplication yambaApplication;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		yambaApplication = (YambaApplication) getApplication();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater menuInflater = getMenuInflater();
		menuInflater.inflate(R.menu.menu, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.itemToggleService:
			if (yambaApplication.isServiceRunning()) {
				stopService(new Intent(this, UpdaterService.class));
			} else {
				startService(new Intent(this, UpdaterService.class));
			}
			break;
		case R.id.itemPrefs:
			startActivity(new Intent(this, PrefsActivity.class)
					.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT));
			break;
		case R.id.itemPurge:
//			((YambaApplication) getApplication()).getStatusData().delete();
			Toast.makeText(this, R.string.msgAllDataPurged, Toast.LENGTH_LONG)
					.show();
			break;
		case R.id.itmeTimeline:
			startActivity(new Intent(this, TimelineActivity.class).addFlags(
					Intent.FLAG_ACTIVITY_SINGLE_TOP).addFlags(
					Intent.FLAG_ACTIVITY_REORDER_TO_FRONT));
			break;
		case R.id.itmeStatus:
			startActivity(new Intent(this, StatusActivity.class)
					.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT));
		}
		return true;
	}

	@Override
	public boolean onMenuOpened(int featureId, Menu menu) {
		MenuItem toggoleItem = menu.findItem(R.id.itemToggleService);
		if (yambaApplication.isServiceRunning()) {
			toggoleItem.setTitle(R.string.titleServiceStop);
			toggoleItem.setIcon(android.R.drawable.ic_media_pause);
		} else {
			toggoleItem.setTitle(R.string.titleServiceStart);
			toggoleItem.setIcon(android.R.drawable.ic_media_play);
		}
		return super.onMenuOpened(featureId, menu);
	}
}
