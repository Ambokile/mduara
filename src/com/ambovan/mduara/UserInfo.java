package com.ambovan.mduara;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class UserInfo extends SQLiteOpenHelper {

	public UserInfo(Context context) {
		super(context, "UserInfo.db", null, 2);
		Log.e("meassage", "database created");
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(
				"create table UserInfoTable " +
				"(user_name text,mobile_number text,profile_image text,_id integer primary key)"
				);
		
		Log.e("tb", "table created");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS UserInfoTable");
		onCreate(db);
	}

	
}
