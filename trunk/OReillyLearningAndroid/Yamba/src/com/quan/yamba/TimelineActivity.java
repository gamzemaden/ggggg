package com.quan.yamba;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.SimpleCursorAdapter.ViewBinder;
import android.widget.TextView;
import android.widget.Toast;

public class TimelineActivity extends BaseActivity {

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

		if (yambaApplication.getPrefs().getString("username", null) == null) {
			startActivity(new Intent(this, PrefsActivity.class));
			Toast.makeText(this, R.string.msgSetupPrefs, Toast.LENGTH_LONG)
					.show();
		}

		listTimeline = (ListView) findViewById(R.id.listTimeline);
		super.onCreate(savedInstanceState);
	}

	@Override
	protected void onDestroy() {
		yambaApplication.getStatusData().close();
		super.onDestroy();
	}

	@Override
	protected void onResume() {
		this.setupList();
		super.onResume();
	}

	private void setupList() {
		cursor = yambaApplication.getStatusData().getStatusUpdates();
		startManagingCursor(cursor);

		adapter = new SimpleCursorAdapter(this, R.layout.row, cursor, FROM, TO);
		adapter.setViewBinder(VIEW_BINDER);
		listTimeline.setAdapter(adapter);
	}

	static final ViewBinder VIEW_BINDER = new ViewBinder() {

		@Override
		public boolean setViewValue(View view, Cursor cursor, int columnIndex) {
			if (view.getId() != R.id.textCreatedAt)
				return false;

			long timestamp = cursor.getLong(columnIndex);
			CharSequence relTime = DateUtils.getRelativeTimeSpanString(
					view.getContext(), timestamp);
			((TextView) view).setText(relTime);
			return true;
		}
	};

}
