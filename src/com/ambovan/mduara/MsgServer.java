package com.ambovan.mduara;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MsgServer extends SQLiteOpenHelper {

	public MsgServer(Context context) {
		super(context, "msgdb.db", null, 3);
		Log.e("bd", "db created");
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(
				"create table MsgTable " +
				"(msg text,sender text,receiver text,time text,date text,media_test text,media_about text,_id integer primary key,status text)"
				);
		
		Log.e("tb", "table created");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		db.execSQL("DROP TABLE IF EXISTS MsgTable");
		onCreate(db);

	}
	
	@Override
	public void onDowngrade(SQLiteDatabase db, int arg1, int arg2) {
		db.execSQL("DROP TABLE IF EXISTS MsgTable");
		onCreate(db);

	}
	
}
