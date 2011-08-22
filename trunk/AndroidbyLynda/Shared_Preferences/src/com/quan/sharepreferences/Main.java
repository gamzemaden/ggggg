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