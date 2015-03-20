package com.ambovan.mduara;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class CommentsAdapter extends SQLiteOpenHelper {

	public CommentsAdapter(Context context) {
		super(context, "comments.db", null, 5);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(
				"create table CommentsTable " +
				"(sender text,receiver text,comments text,comments_id text,time text, date text ,comments_type text,_id integer primary key)"
				);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		db.execSQL("DROP TABLE IF EXISTS CommentsTable");
		onCreate(db);

	}

}
