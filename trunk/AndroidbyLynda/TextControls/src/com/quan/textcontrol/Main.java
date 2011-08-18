package com.quan.textcontrol;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;

public class Main extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        EditText et = (EditText) findViewById(R.id.editText1);
        et.getText().toString();
    }
}