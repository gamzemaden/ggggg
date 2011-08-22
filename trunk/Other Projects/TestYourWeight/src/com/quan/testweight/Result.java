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

		String str = "你是" + sex + ".\n" + "你的身高是" + height + "CM." + "\n" + "你的标准体重是：" + weight(sex,height);
		textView.setText(str);
	}

	private String weight(String sex, double height) {
		double weight;
		
		if (sex.equals("男性")) {
			weight = (height - 80) * 0.7;
		} else {
			weight = (height - 70) * 0.6;
		}
		
		return weight + "公斤";
	}
}
