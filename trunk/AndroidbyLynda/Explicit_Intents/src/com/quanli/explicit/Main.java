package com.quanli.explicit;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Main extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        final EditText et = (EditText) findViewById(R.id.editText1);
        Button b = (Button) findViewById(R.id.button1);
        
        b.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Main.this, Second.class);
				intent.putExtra("thetext", et.getText().toString());
				startActivity(intent);
			}
		});
        
    }
}