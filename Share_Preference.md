# Share Preference #

怎么用share preference存数据:
  1. 在main.xml里加入一个EditText:
  1. 在activity里加入代码
```
package com.quan.sharepreferences;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.widget.EditText;

public class Main extends Activity {
	
	EditText editText;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        editText = (EditText) findViewById(R.id.editText1);
        SharedPreferences sharedPreferences = getSharedPreferences("MYPREFS", 0);
        editText.setText(sharedPreferences.getString("editTextValue", ""));
    }
    
    @Override
    protected void onStop() {
    	super.onStop();
    	SharedPreferences sharedPreferences = getSharedPreferences("MYPREFS", 0);
    	Editor editor = sharedPreferences.edit();
    	editor.putString("editTextValue", editText.getText().toString());
    	editor.commit();
    }
}
使用SharedPreferences sharedPreferences = getSharedPreferences("MYPREFS", 0); 得到名称为"MYPrefs"的sharepreferences， 如果这个名字的preference没有找到，就在editor。commit()时建一个。

使用sharedPreferences.getString("editTextValue", "") 得到preferences里key: "editTextValue"对应的String，如果没有这个key，就用默认的。

使用Editor editor = sharedPreferences.edit(); 得到sharepreferences的editor。
使用editor.putString("editTextValue", editText.getText().toString()); 把输入的值存到sharepreferences里。
使用editor.commit(); 提交对sharepreferences的改变。
```
  1. 源代码： https://ggggg.googlecode.com/svn/trunk/AndroidbyLynda/Shared_Preferences/