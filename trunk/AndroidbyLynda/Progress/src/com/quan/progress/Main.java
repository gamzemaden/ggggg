package com.quan.progress;

import android.app.Activity;
import android.app.ProgressDialog;
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
        
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("Just wait...");
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(true);
        
        Button button = (Button) findViewById(R.id.button1);   
        button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				progressDialog.show();				
			}
		});
        
        final ProgressDialog progressDialog2 = new ProgressDialog(this);
        progressDialog2.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog2.setMessage("Waiting...");
        progressDialog2.setIndeterminate(false);
        
        Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				progressDialog2.show();
				progressDialog2.setProgress(80);
			}
		});
    }
}