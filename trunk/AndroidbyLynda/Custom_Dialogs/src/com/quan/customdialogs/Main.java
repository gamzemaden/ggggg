package com.quan.customdialogs;

import android.app.Activity;
import android.app.Dialog;
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
        
        Button button = (Button) findViewById(R.id.button1);
        button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Dialog dialog = new Dialog(Main.this);
				dialog.setContentView(R.layout.dialog);
				dialog.setTitle("This is important");
				dialog.show();
			}
		});
    }
}