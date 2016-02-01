# Options Menu #

怎么使用选项菜单：

  1. New menu android xml file. mymenu.xml.
```
<?xml version="1.0" encoding="utf-8"?>
<menu xmlns:android="http://schemas.android.com/apk/res/android">

	<item android:id="@+id/item1" android:title="Option 1"
		android:icon="@android:drawable/ic_menu_compass" />

	<item android:id="@+id/item2" android:title="Option 2"
		android:icon="@android:drawable/ic_menu_call" />
</menu>
android:id="@+id/item1" 创建新id：item1 
```
  1. 在activity里加入代码。
```
package com.quan.optionsmenu;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class Main extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	MenuInflater inflater = getMenuInflater();
    	inflater.inflate(R.menu.mymenu, menu);	
    	return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	if (item.getItemId() == R.id.item1) {
			Log.d("Option", "Option 1 was clicked");
		}
    	return super.onOptionsItemSelected(item);
    }
}
覆盖方法onCreateOptionsMenu(Menu menu)，加载mymenu.xml。
覆盖方法onOptionsItemSelected(MenuItem item)，可以加入监听菜单选择的执行代码。
```
  1. 源代码：https://ggggg.googlecode.com/svn/trunk/AndroidbyLynda/Options_Menu/