package com.example.mike.journal;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;

public class EntryAdapter extends ResourceCursorAdapter {

    // constructor for the entryadapter
    public EntryAdapter(Context context, Cursor cursor) {
        super(context, R.layout.entry_row, cursor);
    }

    // sets the right text on the listview items
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView moodView = view.findViewById(R.id.moodView);
        TextView timeStampView = view.findViewById(R.id.timestampView);
        TextView titleView = view.findViewById(R.id.title);

        titleView.setText(cursor.getString(cursor.getColumnIndex(EntryDatabase.COLUMN_NAME_TITLE)));
        moodView.setText(cursor.getString(cursor.getColumnIndex(EntryDatabase.COLUMN_NAME_MOOD)));
        timeStampView.setText(cursor.getString(cursor.getColumnIndex(EntryDatabase.COLUMN_NAME_TIMESTAMP)));
    }
}
