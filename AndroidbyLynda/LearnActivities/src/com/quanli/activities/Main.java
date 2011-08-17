package com.quanli.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Main extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Button b = (Button) findViewById(R.id.button1);
        
        b.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				startActivity(new Intent(Main.this, Second.class));
			}
		});
        
    }
}