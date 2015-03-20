package com.ambovan.mduara;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class mediaAdapter extends CursorAdapter {

	@SuppressWarnings("deprecation")
	public mediaAdapter(Context context, Cursor c) {
		super(context, c);
		
	}

	@Override
	public void bindView(View arg0, Context arg1, Cursor arg2) {
		TextView media_name = (TextView)arg0.findViewById(R.id.name);
		media_name.setText(arg2.getString(arg2.getColumnIndexOrThrow(arg2.getColumnName(0))));
	}

	@Override
	public View newView(Context arg0, Cursor arg1, ViewGroup arg2) {
	    LayoutInflater inflater = LayoutInflater.from(arg2.getContext());
	    View v = inflater.inflate(R.layout.media_drawer_left_content, arg2, false);
		return v;
	}

}
