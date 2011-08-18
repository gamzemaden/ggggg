package com.quan.resources;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

public class Main extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        ImageView iv = (ImageView) findViewById(R.id.imageView1);
        iv.setImageResource(R.drawable.quan);
        
    }
}