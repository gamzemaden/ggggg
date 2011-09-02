package com.quan.yamba;

import com.quan.yamba.StatusData.DbHelper;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.SimpleCursorAdapter.ViewBinder;
import android.widget.TextView;

public class TimelineActivity extends Activity {

	DbHelper dbHelper;
	SQLiteDatabase db;
	Cursor cursor;
	ListView listTimeline;
	SimpleCursorAdapter adapter;
	static final String[] FROM = { StatusData.C_CREATED_AT, StatusData.C_USER,
			StatusData.C_TEXT };
	static final int[] TO = { R.id.textCreatedAt, R.id.textUser, R.id.textText };
	StatusData statusData = new StatusData(this);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.timeline);

		listTimeline = (ListView) findViewById(R.id.listTimeline);

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

		adapter = new SimpleCursorAdapter(this, R.layout.row, cursor, FROM, TO);
		adapter.setViewBinder(VIEW_BINDER);
		listTimeline.setAdapter(adapter);
		super.onResume();
	}

	static final ViewBinder VIEW_BINDER = new ViewBinder() {

		@Override
		public boolean setViewValue(View view, Cursor cursor, int columnIndex) {
			if (view.getId() != R.id.textCreatedAt)
				return false;

			long timestamp = cursor.getLong(columnIndex);
			CharSequence relTime = DateUtils.getRelativeTimeSpanString(
					view.getContext(), timestamp);
			((TextView)view).setText(relTime);
			return true;
		}
	};

}
