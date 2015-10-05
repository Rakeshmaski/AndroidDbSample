package com.example.databaseinsertion;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	DBHelper dbhelper;
	EditText et;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		et = (EditText) findViewById(R.id.editText1);
		dbhelper = new DBHelper(this);
	}

	public void add(View v) {
		String data = et.getText().toString();
		SQLiteDatabase db = dbhelper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("task", data);
		long id = db.insert("tasks", null, values);
		Toast.makeText(getApplicationContext(), "Inserted Sucessfully\nUpon Insertion=" + id, Toast.LENGTH_SHORT)
				.show();
	}

	public void read(View v) {
		SQLiteDatabase db = dbhelper.getReadableDatabase();
		Cursor c = db.query("tasks", null, null, null, null, null, null);
		c.moveToFirst();
		int idIndex = c.getColumnIndex("_id");
		int taskIndex = c.getColumnIndex("task");
		while (c.moveToNext()) {
			String id = c.getString(idIndex);
			String task = c.getString(taskIndex);
			Toast.makeText(getApplicationContext(), "Values are\n id=" + id + ";task=" + task, Toast.LENGTH_LONG)
					.show();
		}
	}

	public void delete(View v) {
		SQLiteDatabase db = dbhelper.getReadableDatabase();
		String data = et.getText().toString();
		String sql = ("delete from tasks where task='" + data + "'");
		System.out.println("check=" + sql);
		db.execSQL(sql);
		Toast.makeText(getApplicationContext(), "deleted table sucessfully", Toast.LENGTH_SHORT).show();
	}

}
