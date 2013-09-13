package com.pasali.kupli;

import java.util.ArrayList;

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
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ListView list;
		AccDAO a = new AccDAO(this);
		ArrayList<String> liste_elemanlari = a.getAllNames();
		
		setContentView(R.layout.activity_main);
		list = (ListView) findViewById(R.id.listView1);
		list.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, liste_elemanlari));
		list.setTextFilterEnabled(true);
		list.setClickable(true);
		list.setOnItemClickListener(new OnItemClickListener() {
			 @Override
			    public void onItemClick(AdapterView<?> parnet, android.view.View view,
			            int position, long id) {
			        Toast.makeText(getApplicationContext(), ((TextView) view).getText(),
			                Toast.LENGTH_SHORT).show();
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
			Intent i = new Intent(getApplicationContext(), SaveActivity.class);
			startActivity(i);
			return true;
		default:
			return false;
		}
		
	}

}
