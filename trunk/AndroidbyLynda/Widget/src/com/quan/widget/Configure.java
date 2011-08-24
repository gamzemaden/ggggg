package com.quan.widget;

import android.app.Activity;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RemoteViews;

public class Configure extends Activity {
	private Configure context;
	private int widgetID;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.configure);
		setResult(RESULT_CANCELED);

		context = this;

		Bundle extra = getIntent().getExtras();
		if (extra != null) {
			widgetID = extra.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID,
					AppWidgetManager.INVALID_APPWIDGET_ID);
		}
		Log.d("widgetID", String.valueOf(widgetID));

		final AppWidgetManager appWidgetManager = AppWidgetManager
				.getInstance(context);
		final RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.main);
		
		final EditText editText = (EditText) findViewById(R.id.editText1);
		Button button = (Button) findViewById(R.id.button1);
		
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(editText.getText().toString()));
				PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
				remoteViews.setOnClickPendingIntent(R.id.imageButton1, pendingIntent);
				appWidgetManager.updateAppWidget(widgetID, remoteViews);
				
				Intent resultValue = new Intent();
				resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetID);
				setResult(RESULT_OK, resultValue);
				finish();
			}
		});
	}
}
