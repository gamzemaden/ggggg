package com.quan.networkaccess;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Main extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		final EditText editText = (EditText) findViewById(R.id.editText1);
		final Button button = (Button) findViewById(R.id.button1);
		final TextView textView = (TextView) findViewById(R.id.textView1);

		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				try {
					URL url = new URL(editText.getText().toString());
					URLConnection urlConnection = url.openConnection();
					BufferedReader bufferedReader = new BufferedReader(
							new InputStreamReader(urlConnection
									.getInputStream()));
					String line = "";
					while ((line = bufferedReader.readLine()) != null) {
						textView.append(line);
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		});
	}
}