package com.quan.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.RemoteViews;

public class Main extends AppWidgetProvider {
	/** Called when the activity is first created. */
	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		// TODO Auto-generated method stub
		super.onUpdate(context, appWidgetManager, appWidgetIds);

		for (int i = 0; i < appWidgetIds.length; i++) {
			int appWidgetId = appWidgetIds[i];

			Intent intent = new Intent(Intent.ACTION_VIEW,
					Uri.parse("http://lolcats.com"));
			PendingIntent pendingIntent = PendingIntent.getActivity(context, 0,
					intent, 0);
			
			RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.main);
			remoteViews.setOnClickPendingIntent(R.id.imageButton1, pendingIntent);
			
			appWidgetManager.updateAppWidget(appWidgetId, remoteViews);
		}
	}
}