package com.pasali.kupli;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {

	private AccDAO dbhandler;
	private HashMap<String,String> liste_elemanlari;
	private ListView list;
	private ArrayList<String> keys;
	private ArrayAdapter<String> adapter;

	@SuppressWarnings("unchecked")
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		dbhandler = new AccDAO(this);
		liste_elemanlari = dbhandler.getAllNames();
		keys = new ArrayList<String>(liste_elemanlari.keySet());
		setContentView(R.layout.activity_main);
		list = (ListView) findViewById(R.id.listView1);
		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, keys);
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				adapter.notifyDataSetChanged();
			}
		});
		list.setAdapter(adapter);
		list.setTextFilterEnabled(true);
		list.setClickable(true);
		list.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parnet,
					android.view.View view, int position, long id) {
				Intent showIntent = new Intent(getApplicationContext(),
						ShowActivity.class);
				String value= list.getItemAtPosition(position).toString();
				String dbId = liste_elemanlari.get(value);
				showIntent.putExtra("id", dbId);
				startActivity(showIntent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.itemAdd:
			Intent saveIntent = new Intent(getApplicationContext(),
					SaveActivity.class);
			startActivity(saveIntent);
			return true;
		default:
			return false;
		}

	}

}
