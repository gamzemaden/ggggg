package com.quan.yamba;

import winterwell.jtwitter.Twitter;
import winterwell.jtwitter.TwitterException;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class StatusActivity extends BaseActivity implements OnClickListener,
		TextWatcher, LocationListener {
	private static final String TAG = "StatusActivity";
	private static final long LOCATION_MIN_TIME = 3600000; // One hour
	private static final float LOCATION_MIN_DISTANCE = 1000; // One kilometter
	EditText editText;
	Button updateButton;
	TextView textCount;
	LocationManager locationManager;
	Location location;
	String provider;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.status);

		editText = (EditText) findViewById(R.id.editTextStatus);
		updateButton = (Button) findViewById(R.id.buttonUpdateStatus);
		updateButton.setOnClickListener(this);

		textCount = (TextView) findViewById(R.id.textViewCount);
		textCount.setText(Integer.toString(140));
		textCount.setTextColor(Color.GREEN);
		editText.addTextChangedListener(this);

		Log.d(TAG, getFilesDir().toString());
	}

	@Override
	protected void onResume() {
		super.onResume();

		provider = yambaApplication.getProvider();
		if (!YambaApplication.LOCATION_PROVIDER_NONE.equals(provider)) {
			locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
			Log.d(TAG, "provider: " + provider);
		}
		if (locationManager != null) {
			Log.d(TAG, "locationManager: " + locationManager.toString());
			location = locationManager.getLastKnownLocation(provider);
			locationManager.requestLocationUpdates(provider, LOCATION_MIN_TIME,
					LOCATION_MIN_DISTANCE, this);
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
		if (locationManager != null) {
			locationManager.removeUpdates(this);
		}
	}

	class PostToTwitter extends AsyncTask<String, Integer, String> {

		@Override
		protected String doInBackground(String... statuses) {
			try {
				YambaApplication yamba = (YambaApplication) getApplication();
				if (location != null) {
					double latlong[] = { location.getLatitude(),
							location.getLongitude() };
					yamba.getTwitter().setMyLocation(latlong);
					Log.d(TAG, latlong[0] + " : " + latlong[1]);
				}
				Twitter.Status status = yamba.getTwitter()
						.updateStatus(statuses[0]);
				return status.text;
			} catch (TwitterException e) {
				Log.e(TAG, "Twitter set status failed: " + e.toString());
				e.printStackTrace();
				return "Failed to post";
			}
		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			// Not used in this case
			super.onProgressUpdate(values);
		}

		@Override
		protected void onPostExecute(String result) {
			if (location != null) {
				Toast.makeText(StatusActivity.this, result + location.getLatitude(), Toast.LENGTH_LONG).show();
			}else{
				Toast.makeText(StatusActivity.this, result + " provider: " + provider +" no position", Toast.LENGTH_LONG).show();
			}
		}

	}

	@Override
	public void onClick(View v) {
		String status = editText.getText().toString();

		new PostToTwitter().execute(status);
		Log.d(TAG, "onClicked");
	}

	@Override
	public void afterTextChanged(Editable statusText) {
		int count = 140 - statusText.length();
		textCount.setText(Integer.toString(count));
		textCount.setTextColor(Color.GREEN);
		if (count < 10) {
			textCount.setTextColor(Color.YELLOW);
		}
		if (count < 0) {
			textCount.setTextColor(Color.RED);
		}

	}

	@Override
	public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
	}

	@Override
	public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
	}

	@Override
	public void onLocationChanged(Location location) {
		this.location = location;
	}

	@Override
	public void onProviderDisabled(String provider) {
		if (this.provider.equals(provider)) {
			locationManager.removeUpdates(this);
		}
	}

	@Override
	public void onProviderEnabled(String provider) {
		if (this.provider.equals(provider)) {
			locationManager.requestLocationUpdates(this.provider,
					LOCATION_MIN_TIME, LOCATION_MIN_DISTANCE, this);
		}
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub

	}
}