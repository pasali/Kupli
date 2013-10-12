package com.pasali.kupli;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditActivity extends Activity {

	
	private EditText user,pass;
	private String id;
	private Account acc = null;
	private AccDAO editDao;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit);
		
		Bundle extras = getIntent().getExtras(); 
		if(extras !=null) {
		    id = extras.getString("id");
		}
		editDao = new AccDAO(this);
		acc = new AccDAO(this).getAcc(Long.valueOf(id));
		user = (EditText)findViewById(R.id.editText1);
		user.setText(acc.getUser());
		pass = (EditText)findViewById(R.id.editText2);
		pass.setText(acc.getPass());
		
		Button save = (Button) findViewById(R.id.button1);
		save.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				acc.setUser(user.getText().toString());
				acc.setPass(pass.getText().toString());
				editDao.updateAcc(acc);

			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit, menu);
		return true;
	}

}
