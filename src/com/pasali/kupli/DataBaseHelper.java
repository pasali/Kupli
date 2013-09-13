package com.pasali.kupli;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataBaseHelper extends  SQLiteOpenHelper {

	public static final String TABLE_ACC = "accounts";
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_NAME = "name";
	public static final String COLUMN_USER = "user";
	public static final String COLUMN_PASS = "pass";
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "accountsDB";
	
	public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	private static final String DATABASE_CREATE = "create table " + TABLE_ACC
			+ "(" + COLUMN_ID + " integer primary key autoincrement, "
			+ COLUMN_NAME + " text not null, " + COLUMN_USER
			+ " text not null," + COLUMN_PASS + " text not null" + ");";

	public  void onCreate(SQLiteDatabase database) {
		database.execSQL(DATABASE_CREATE);
	}

	public  void onUpgrade(SQLiteDatabase database, int oldVersion,
			int newVersion) {
		Log.w(DataBaseHelper.class.getName(), "Upgrading database from version "
				+ oldVersion + " to " + newVersion
				+ ", which will destroy all old data");
		database.execSQL("DROP TABLE IF EXISTS " + TABLE_ACC);
		onCreate(database);
	}
}
