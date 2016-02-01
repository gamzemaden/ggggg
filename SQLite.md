# SQLite #

怎么使用数据库:
  1. 在main.xml里加入一个TextView:
  1. 在activity里加入代码
```
package com.quan.sqlite;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class Mani extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		SQLiteDatabase sqLiteDatabase = openOrCreateDatabase("MyDB3",
				MODE_PRIVATE, null);
		sqLiteDatabase
				.execSQL("CREATE TABLE IF NOT EXISTS MyTable (LastName VARCHAR, FristName VARCHAR, Age INT(3));");
		sqLiteDatabase
				.execSQL("INSERT INTO MyTable VALUES ('Lee','Quan','29');");
		sqLiteDatabase.execSQL("INSERT INTO MyTable VALUES ('He','Yu','27');");

		Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM MyTable;", null);
		cursor.moveToFirst();
		String str = cursor.getString(cursor.getColumnIndex("FristName")) + " "
				+ cursor.getString(cursor.getColumnIndex("LastName"));
		Log.d("SQLinfo", "str");
		cursor.moveToNext();
		str += "; "+cursor.getString(cursor.getColumnIndex("FristName")) + " "
				+ cursor.getString(cursor.getColumnIndex("LastName"));
		
		TextView textView = (TextView) findViewById(R.id.textView1);
		textView.setText(str);
		sqLiteDatabase.close();
	}
}
使用	SQLiteDatabase sqLiteDatabase = openOrCreateDatabase("MyDB3",MODE_PRIVATE, null); 得到数据库。
使用sqLiteDatabase.execSQL 执行SQL语句。
使用Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM MyTable;", null); 返回查询结果的cursor。
使用cursor.getString(cursor.getColumnIndex("FristName"))得到查询结果里的值。
```
  1. 源代码： https://ggggg.googlecode.com/svn/trunk/AndroidbyLynda/SQLite/