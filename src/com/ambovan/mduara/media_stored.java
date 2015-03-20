package com.ambovan.mduara;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class media_stored extends SQLiteOpenHelper {

	public media_stored(Context context) {
		super(context, "mediaStored.db", null, 2);
		
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(
				"create table MediaStoreTable " +
				"(name text,image text,_id integer primary key)"
				);
		
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		db.execSQL("DROP TABLE IF EXISTS MediaStoreTable");
		onCreate(db);

	}

}
