package com.quan.buttoncontrol;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;

public class Main extends Activity implements OnClickListener{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Button b = (Button) findViewById(R.id.button1);
        b.setOnClickListener(this);
        
        ImageButton ib = (ImageButton) findViewById(R.id.imageButton1);
        ib.setOnClickListener(this);
    }

	@Override
	public void onClick(View v) {
		Log.d("Liquan", "You click me.");
		if(v.getId() == R.id.imageButton1){
			Log.d("Liquan", "Image button was clicked.");
		}
	}
}