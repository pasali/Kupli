package com.pasali.kupli;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class AccDAO {

	private SQLiteDatabase db;
	private DataBaseHelper dbHelper;

	public AccDAO(Context context) {
		dbHelper = new DataBaseHelper(context);
	}


	public void addAcc(Account acc){
		db = dbHelper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(DataBaseHelper.COLUMN_NAME, acc.getName());
		values.put(DataBaseHelper.COLUMN_USER, acc.getUser());
		values.put(DataBaseHelper.COLUMN_PASS, acc.getPass());
		db.insert(DataBaseHelper.TABLE_ACC, null, values);
		dbHelper.close(); 
	}

	public Account getAcc(long id) {
		db = dbHelper.getReadableDatabase();
		Cursor cursor = db.rawQuery("SELECT * FROM "+ DataBaseHelper.TABLE_ACC +" WHERE _id= ?", new String[] { String.valueOf(id) });
		Account acc = null;
		if (cursor != null && cursor.moveToFirst()){
			acc = new Account(cursor.getInt(0),cursor.getString(1),
				cursor.getString(2), cursor.getString(3));
		}
		cursor.close();
		dbHelper.close();
		return acc;
	}
	public ArrayList<Account> getAllAccs() {
		ArrayList<Account> accs = new ArrayList<Account>();
		db = dbHelper.getReadableDatabase();
		Cursor cursor = db.rawQuery("select * from " + DataBaseHelper.TABLE_ACC, null);
		if (cursor.moveToFirst()) {
            do {
                Account acc = new Account();
                acc.setId(Integer.parseInt(cursor.getString(0)));
                acc.setName(cursor.getString(1));
                acc.setUser(cursor.getString(2));
                acc.setPass(cursor.getString(3));
                accs.add(acc);
            } while (cursor.moveToNext());
        }
		dbHelper.close();
		return accs;		
	}
	
	public HashMap<String,String> getAllNames() {
		HashMap<String,String> accs = new HashMap<String,String>();
		db = dbHelper.getReadableDatabase();
		Cursor cursor = db.rawQuery("select * from " + DataBaseHelper.TABLE_ACC, null);
		if (cursor.moveToFirst()) {
            do {
                accs.put(cursor.getString(1),String.valueOf(cursor.getInt(0)));
            } while (cursor.moveToNext());
        }
		dbHelper.close();
		return accs;		
	}
	
	public void emptyTable(){
		db = dbHelper.getWritableDatabase();
		db.delete(DataBaseHelper.TABLE_ACC, null, null);
		dbHelper.close();
	}
	
	public void updateAcc(Account acc){
		db = dbHelper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(DataBaseHelper.COLUMN_NAME, acc.getName());
		values.put(DataBaseHelper.COLUMN_USER, acc.getUser());
		values.put(DataBaseHelper.COLUMN_PASS, acc.getPass());
		db.update(DataBaseHelper.TABLE_ACC, values, DataBaseHelper.COLUMN_ID + " = ?", new String[] { String.valueOf(acc.getId()) });
		dbHelper.close();
	}
	
	public void deleteAcc(long id) {
        db = dbHelper.getWritableDatabase();
        db.delete(DataBaseHelper.TABLE_ACC, DataBaseHelper.COLUMN_ID + " = ?", new String[] { String.valueOf(id) });
        dbHelper.close();
    }
}
