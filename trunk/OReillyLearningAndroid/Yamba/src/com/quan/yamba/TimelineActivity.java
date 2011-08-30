package com.quan.yamba;

import com.quan.yamba.StatusData.DbHelper;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

public class TimelineActivity extends Activity {

	DbHelper dbHelper;
	SQLiteDatabase db;
	Cursor cursor;
	TextView textTimeline;
	StatusData statusData = new StatusData(this);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.timeline_basic);

		textTimeline = (TextView) findViewById(R.id.textTimeline);

		dbHelper = statusData.new DbHelper(this);
		db = dbHelper.getReadableDatabase();

		super.onCreate(savedInstanceState);
	}

	@Override
	protected void onDestroy() {
		db.close();
		super.onDestroy();
	}

	@Override
	protected void onResume() {
		cursor = db.query(StatusData.TABLE, null, null, null, null, null,
				StatusData.C_CREATED_AT + " DESC");
		startManagingCursor(cursor);

		String user, text, output;
		while (cursor.moveToNext()) {
			user = cursor.getString(cursor.getColumnIndex(StatusData.C_USER));
			text = cursor.getString(cursor.getColumnIndex(StatusData.C_TEXT));
			output = String.format("%s: %s\n", user, text);
			textTimeline.append(output);
		}
		super.onResume();
	}

}
