package com.example.databaseinsertion;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper{

	public DBHelper(Context context) {
		super(context,"dbtest",null,1);
	}
	@Override
	public void onCreate(SQLiteDatabase db){
		db.execSQL("create table tasks(_id integer primary key autoincrement, task text)");
		
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("drop table if exists tasks");
		onCreate(db);
	}

}
