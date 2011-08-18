package com.quan.customlist;

import java.util.List;


import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class Main extends ListActivity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

	/*	setListAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, getResources()
						.getStringArray(R.array.countries)));*/

		class MyAdapter extends ArrayAdapter<String> {

			public MyAdapter(Context context, int resource,
					int textViewResourceId, String[] strings) {
				super(context, resource, textViewResourceId, strings);
				// TODO Auto-generated constructor stub
			}

			@Override
			public View getView(int position, View convertView, ViewGroup parent) {

				LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
				View row = inflater.inflate(R.layout.list_item, parent, false);
				String[] items = getResources().getStringArray(
						R.array.countries);

				ImageView iv = (ImageView) row.findViewById(R.id.imageView1);
				TextView tv = (TextView) row.findViewById(R.id.textView1);

				tv.setText(items[position]);

				if (items[position].equals("United States")) {
					iv.setImageResource(R.drawable.usa);
				} else if (items[position].equals("Brazil")) {
					iv.setImageResource(R.drawable.brazil);
				}else if (items[position].equals("Eygpt")) {
					iv.setImageResource(R.drawable.eygpt);
				}else if (items[position].equals("Japan")) {
					iv.setImageResource(R.drawable.japan);
				}else if (items[position].equals("Russia")) {
					iv.setImageResource(R.drawable.russia);
				}
				
				
				return row;
			}

		}
		
		setListAdapter(new MyAdapter(this,
				android.R.layout.simple_list_item_1, R.id.textView1,
				getResources().getStringArray(R.array.countries)));
	}
}