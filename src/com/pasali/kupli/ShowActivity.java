package com.pasali.kupli;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.TextView;

public class ShowActivity extends Activity {

	private TextView user, pass;
	private String username, password;
	private Account acc = null;
	private long id;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show);
		Bundle extras = getIntent().getExtras(); 
		if(extras !=null) {
		    id = extras.getLong("id");
		}
		acc = new AccDAO(this).getAcc(121);
		
		user = (TextView)findViewById(R.id.textView2);
		user.setText(acc.getUser());
		pass = (TextView)findViewById(R.id.textView4);
		pass.setText(acc.getPass());
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.show, menu);
		return true;
	}

}
