package com.quan.testweight;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class Result extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);		
		setContentView(R.layout.result);
		
		TextView textView = (TextView) findViewById(R.id.rtextView1);
		Bundle bundle = this.getIntent().getExtras();
				
		String sex = bundle.getString("sex");
		double height = bundle.getDouble("height");
		
		String str = "你是" + sex + "./n" + "你的身高是：" + height + "CM";
		textView.setText(str);
	}
}
