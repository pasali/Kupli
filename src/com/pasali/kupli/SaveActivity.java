package com.pasali.kupli;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SaveActivity extends Activity {

	private AccDAO saveDao;
	private Account newAcc;
	private EditText service, user, pass;

	@SuppressWarnings("deprecation")
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_save);
		saveDao = new AccDAO(this);
		newAcc = new Account();
		Button save = (Button) findViewById(R.id.saveButton);
		save.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				service = (EditText) findViewById(R.id.serviceText);
				user = (EditText) findViewById(R.id.userText);
				pass = (EditText) findViewById(R.id.passText);
				newAcc.setName(service.getText().toString());
				newAcc.setUser(user.getText().toString());
				newAcc.setPass(pass.getText().toString());
				saveDao.addAcc(newAcc);	
				AlertDialog alertDialog = new AlertDialog.Builder(
						SaveActivity.this).create();
				alertDialog.setTitle("Kayıt Onayı");
				alertDialog.setMessage("Hesabınızı başarı ile kaydettiniz.");
				alertDialog.setIcon(R.drawable.tick);
				alertDialog.setButton("Tamam", new DialogInterface.OnClickListener(){
					public void onClick(DialogInterface dialog, int which) {
						Intent main = new Intent(getApplicationContext(), MainActivity.class);
						startActivity(main);
					}
				});
				alertDialog.show();
			}
		});

		
	}

}
