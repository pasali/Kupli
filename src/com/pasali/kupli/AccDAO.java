package com.pasali.kupli;

import java.util.ArrayList;

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

	public void close() {
		db.close();
	}

	public void addAcc(Account acc){
		db = dbHelper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(DataBaseHelper.COLUMN_NAME, acc.getName());
		values.put(DataBaseHelper.COLUMN_USER, acc.getUser());
		values.put(DataBaseHelper.COLUMN_PASS, acc.getPass());
		db.insert(DataBaseHelper.TABLE_ACC, null, values);
		close(); 
	}

	public Account getAcc(int id) {
		db = dbHelper.getReadableDatabase();
		Cursor cursor = db.query(DataBaseHelper.TABLE_ACC, new String[] {DataBaseHelper.COLUMN_ID,
				DataBaseHelper.COLUMN_NAME, DataBaseHelper.COLUMN_USER, DataBaseHelper.COLUMN_PASS }, DataBaseHelper.COLUMN_ID + "=?",
				new String[] { String.valueOf(id) }, null, null, null, null);
		if (cursor != null)
			cursor.moveToFirst();

		Account acc = new Account(cursor.getInt(0),cursor.getString(1),
				cursor.getString(2), cursor.getString(3));
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
		return accs;		
	}
	
	public ArrayList<String> getAllNames() {
		ArrayList<String> accs = new ArrayList<String>();
		db = dbHelper.getReadableDatabase();
		Cursor cursor = db.rawQuery("select * from " + DataBaseHelper.TABLE_ACC, null);
		if (cursor.moveToFirst()) {
            do {
                accs.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }
		return accs;		
	}
	
	public void emptyTable(){
		db = dbHelper.getWritableDatabase();
		db.delete(DataBaseHelper.TABLE_ACC, null, null);
	}
	
	public int updateAcc(Account acc){
		db = dbHelper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(DataBaseHelper.COLUMN_NAME, acc.getName());
		values.put(DataBaseHelper.COLUMN_USER, acc.getUser());
		values.put(DataBaseHelper.COLUMN_PASS, acc.getPass());
		return db.update(DataBaseHelper.TABLE_ACC, values, DataBaseHelper.COLUMN_ID + " = ?", new String[] { String.valueOf(acc.getId()) });

	}
	
	public void deleteAcc(int id) {
        db = dbHelper.getWritableDatabase();
        db.delete(DataBaseHelper.TABLE_ACC, DataBaseHelper.COLUMN_ID + " = ?", new String[] { String.valueOf(id) });
        db.close();
    }
}
