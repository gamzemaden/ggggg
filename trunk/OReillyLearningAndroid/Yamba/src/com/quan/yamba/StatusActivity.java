package com.quan.yamba;

import winterwell.jtwitter.Twitter;
import winterwell.jtwitter.TwitterException;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class StatusActivity extends Activity implements OnClickListener {
	private static final String TAG = "StatusActivity";
	EditText editText;
	Button updateButton;
	Twitter twitter;	
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.status);
        
        editText = (EditText) findViewById(R.id.editTextStatus);
        updateButton = (Button) findViewById(R.id.buttonUpdateStatus);
        
        updateButton.setOnClickListener(this);
        
        twitter = new Twitter("davidsuger","53930000");
        twitter.setAPIRootUrl("http://yamba.marakana.com/api");
    }
    
    class PostToTwitter extends AsyncTask<String, Integer, String>{

		@Override
		protected String doInBackground(String... statuses) {
			try {
				Twitter.Status status = twitter.updateStatus(statuses[0]);
				return status.text;
			} catch (TwitterException e) {
				Log.e(TAG, e.toString());
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
			Toast.makeText(StatusActivity.this, result, Toast.LENGTH_LONG).show();
			super.onPostExecute(result);
		}
    	
    }

	@Override
	public void onClick(View v) {
		String status = editText.getText().toString();
		new PostToTwitter().execute(status);
		Log.d(TAG, "onClicked");
	}
}