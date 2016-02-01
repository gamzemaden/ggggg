# Context Menus #

怎么使用上下午菜单：
  1. New menu android xml file. context\_menu.xml.
```
<?xml version="1.0" encoding="utf-8"?>
<menu
  xmlns:android="http://schemas.android.com/apk/res/android">
    
    <item android:id="@+id/item1" android:title="Item 1"/>
    <item android:id="@+id/item2" android:title="Item 2"/>
    <item android:id="@+id/item3" android:title="Item 3"/>
    
    <group android:checkableBehavior="single">
    	<item android:id="@+id/item4" android:title="Item 4"/>
    	<item android:id="@+id/item5" android:title="Item 5"/>
    </group>
</menu>
使用<group> 组合items像checkbox，radio buttons。
```
  1. 在main.xml里加入一个button。
  1. 在activity里加入代码。
```
package com.quan.contextmenus;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.Button;

public class Main extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Button button = (Button) findViewById(R.id.button1);
        registerForContextMenu(button);
    }
    
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
    		ContextMenuInfo menuInfo) {
    	super.onCreateContextMenu(menu, v, menuInfo);
    	MenuInflater inflater = getMenuInflater();
    	inflater.inflate(R.menu.context_menu, menu);
    }
    
    @Override
    public boolean onContextItemSelected(MenuItem item) {
    	if (item.getItemId() == R.id.item1) {
			Log.d("Option", "Option1 was clicked");
		}
    	return super.onContextItemSelected(item);
    }
}

覆盖方法public void onCreateContextMenu(ContextMenu menu, View v,
    		ContextMenuInfo menuInfo)，加载自定义的context_menu.xml到ContextMenu里。
使用registerForContextMenu(button)，把button注册到ContextMenu上。
覆盖方法public boolean onContextItemSelected(MenuItem item)，可以加入监听上下文菜单选择的执行代码。
```
  1. 源代码： https://ggggg.googlecode.com/svn/trunk/AndroidbyLynda/Context_Menus/