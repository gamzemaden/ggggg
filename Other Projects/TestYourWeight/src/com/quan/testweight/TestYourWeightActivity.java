package com.quan.testweight;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class TestYourWeightActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        
        Button button =(Button) findViewById(R.id.button1);
        button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				String sex;
		        RadioButton radioButton = (RadioButton) findViewById(R.id.radioButton1);
		        if(radioButton.isChecked()){
		        	sex = "男性";
		        }
		        else
		        	sex = "女性";
		        
		        double height;
		        EditText editText = (EditText) findViewById(R.id.editText1);
		        height = Double.parseDouble(editText.getText().toString());
		        
		        Intent intent = new Intent();
	        	intent.setClass(TestYourWeightActivity.this, Result.class);
	        	Bundle bundle = new Bundle();
	        	bundle.putString("sex", sex);
	        	bundle.putDouble("height", height);
	        	intent.putExtras(bundle);
	        	startActivity(intent);
			}
		});
        
        	
    }
}